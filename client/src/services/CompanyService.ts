import { Company } from '../shared/interfaces/Company';
import axios from './api/axiosConfig';

const PATH: string = "companies"

export const CompanyService = {
  async getAllCompanies(): Promise<Company[]> {
    return await axios
        .get(`${axios.defaults.baseURL}/${PATH}`)
        .then(response => response.data)
        .catch(function (error) {
        console.log(error);
        });
  }
}