import axios from './api/axiosConfig';

const PATH: string = "owners"

export const AuthService = {
  async createOwner(email: string, password: string, uid: string): Promise<void> {
     return await axios
      .post(`${axios.defaults.baseURL}/${PATH}`, {
            email: email,
            password: password,
            uid: uid
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
  }
}
          