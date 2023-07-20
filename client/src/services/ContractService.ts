import axios from './api/axiosConfig';

import { Contract } from '../shared/interfaces/Contract';

const PATH: string = "contracts"

export const ContractService = {
  async addContract(contract: Contract): Promise<Contract> {
    return await axios
     .post(`${axios.defaults.baseURL}/${PATH}`, contract)
      .then(response => response.data)
        .catch(function (error) {
          console.log(error);
        });
  }
}