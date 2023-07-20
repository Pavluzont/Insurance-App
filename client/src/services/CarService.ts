import axios from './api/axiosConfig';

import { Car } from '../shared/interfaces/Car';
import { OwnerService } from './OwnerService';
import { useOwnerStore } from '../store/owner';

const PATH: string = "cars"

export const CarService = {
  async getAllCars(): Promise<Car[]> {
    return await axios
        .get(`${axios.defaults.baseURL}/${PATH}`)
        .then(response => response.data)
        .catch(function (error) {
        console.log(error);
        });
  },

  async getAllCarsByOwnerId(id: number): Promise<Car[]> {
    return await axios
        .get(`${axios.defaults.baseURL}/${PATH}/${id}/${PATH}`)
        .then(response => response.data)
        .catch(function (error) {
        console.log(error);
        });
  },

  async addCar(car: Car): Promise<void> {
    car.owner = await OwnerService.getOwnerById(useOwnerStore().getId as number);
    return await axios
     .post(`${axios.defaults.baseURL}/${PATH}`, car)
         .then(function (response) {
           console.log(response);
         })
         .catch(function (error) {
           console.log(error);
         });
  },

  async updateCar(car: Car): Promise<Car> {
    return await axios
      .put(`${axios.defaults.baseURL}/${PATH}`, car)
      .then(response => response.data)
  },

  async deleteCarById(id: number): Promise<void> {
    return await axios
        .delete(`${axios.defaults.baseURL}/${PATH}/${id}`)
        .then(response => response.data)
        .catch(function (error) {
          console.log(error);
        });
  },
}
