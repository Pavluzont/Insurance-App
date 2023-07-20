import { defineStore } from 'pinia'

import { OwnerService } from '../services/OwnerService';

import { Owner } from '../shared/interfaces/Owner'

interface OwnerState {
  owner: Owner | null
}

export const useOwnerStore = defineStore('ownerStore', {
  state: (): OwnerState => ({
    owner: null,
  }),
  getters: {
    getId: state => state.owner?.id,
    getFirstName: state => state.owner?.firstName,
    getLastName: state => state.owner?.lastName,
    getEmail: state => state.owner?.email,
    getAge: state => state.owner?.age,
    getGender: state => state.owner?.gender,
    getPhoneNumber: state => state.owner?.phoneNumber,
    getAdress: state => state.owner?.address
  },
  actions: {
    async fetchOwnerByUid(uid: string): Promise<void> {
      try {
        OwnerService.getOwnerByUid(uid)
        .then((owner: Owner): void => {
          this.owner = owner;
        });
      } catch (error) {
        console.log(error);
      }
    },
    async updateOwner(owner: Owner): Promise<void> {
      try {
        OwnerService.updateOwner(owner)
        .then((owner: Owner): void => {
          this.owner = owner;
        });
      } catch (error) {
        console.log(error);
      }
    },

    clearOwner(): void {
      this.owner = null;
    }
  },
})
