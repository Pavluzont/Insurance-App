import { defineStore } from "pinia";
import router from "../router";
import {
  getAuth,
  signOut,
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
  GoogleAuthProvider, 
  signInWithPopup,
} from "firebase/auth";

import { useOwnerStore } from "./owner";
import { AuthService } from "../services/AuthService";

export const useAuthStore = defineStore('authStore', {
  state: () => ({
    isLoggedIn: false,
    email: "",
    password: "",
    errorMessage: "",
  }),
  getters: {
    getLog(): boolean {
      return this.isLoggedIn;
    },
    getEmail(): string {
      return this.email;
    },
    getPassword(): string {
      return this.password;
    },
  },
  actions: {
    register(): void {
      const auth = getAuth();

      createUserWithEmailAndPassword(auth, this.email, this.password)
        .then((userCredential) => {
          const user = userCredential.user;

          AuthService.createOwner(this.email, this.password, user.uid);
          useOwnerStore().fetchOwnerByUid(user.uid);
          this.isLoggedIn = true;
          router.push('/insurance');
        })
        .catch((error) => {
          const errorCode = error.code;
          this.errorMessage = error.message;
          alert(this.errorMessage);
        });
    },

    login(): void {
      const auth = getAuth();

      signInWithEmailAndPassword(auth, this.email, this.password)
        .then((userCredential) => {
          const user = userCredential.user;

          useOwnerStore().fetchOwnerByUid(user.uid);
          this.isLoggedIn = true;
          router.push('/insurance');
        })
        .catch((error) => {
            console.log(error.code);
            switch (error.code) {
              case "auth/invalid-email":
                this.errorMessage = "Invalid email";
                break;
              case "auth/user-not-found":
                this.errorMessage = "No account with that email was found";
                break;
              case "auth/wrong-password":
                this.errorMessage = "Incorrect password";
                break;
              default:
                this.errorMessage = "Error or password were incorrect";
                break;
            };
        });
    },

    logOut(): void {
      const auth = getAuth();

      signOut(auth).then(() => {
        router.push('/');
        useOwnerStore().clearOwner();
        this.isLoggedIn = false;
      });
    },

    signInWithGoogle() {
      const provider = new GoogleAuthProvider();

      signInWithPopup(getAuth(), provider)
      .then((result) => {
        this.isLoggedIn = true;
        console.log(result.user.email);
        console.log(result.user.uid);
        router.push('/profile');
      })
      .catch((error) => {
        console.log(error.code);
      });
    },
  },
});