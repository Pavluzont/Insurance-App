import axios from './api/axiosConfig';

import { Owner } from "../shared/interfaces/Owner";

const PATH: string = "owners"

export const OwnerService = {
  async getOwnerByUid(uid: string): Promise<Owner> {
     return await axios
      .get(`${axios.defaults.baseURL}/${PATH}/uid/${uid}`)
          .then(response => response.data)
          .catch(function (error) {
            console.log(error);
          });
  },

  async getOwnerById(id: number): Promise<Owner> {
    return await axios
     .get(`${axios.defaults.baseURL}/${PATH}/${id}`)
         .then(response => response.data)
         .catch(function (error) {
           console.log(error);
         });
 },

  async updateOwner(owner: Owner): Promise<Owner> {
    return await axios
      .put(`${axios.defaults.baseURL}/${PATH}`, owner)
      .then(response => response.data)
  }
}
          