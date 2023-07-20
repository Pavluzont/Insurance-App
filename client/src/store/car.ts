import { defineStore } from 'pinia'

import { CarService } from '../services/CarService';
import { useOwnerStore } from './owner'; 

import { Car } from "../shared/interfaces/Car";
import { useRouter } from 'vue-router';
import { Company } from '../shared/interfaces/Company';
import { Contract } from '../shared/interfaces/Contract';

interface CarState {
  cars: Car[],
  currentCar?: Car | null,
  currentCompany: Company | null,
  currentContract?: Contract | null,
  isLoading: boolean,
}

export const useCarStore = defineStore('carStore', {
  state: (): CarState => ({
    cars: [],
    currentCar: null,
    currentCompany: null,
    currentContract: null,
    isLoading: false,
  }),
  getters: {
    getCars: state => state.cars,
    getCurrentCar: state => state.currentCar,
    getCurrentCompany: state => state.currentCompany,
    getCurrentContract: state => state.currentContract,
    getLoading: state => state.isLoading,
  },
  actions: {
    async getAllCars() {
      try {
        CarService.getAllCars()
          .then((cars: Car[]): void => {
            this.cars = cars;
          });
      } catch (error) {
        console.log(error);
      }
    },
   
    async getAllCarsForOwner() {
      this.cars = [];
      this.isLoading = true;
      const ownerStore = useOwnerStore();
      const ownerId = await ownerStore.getId;

      if (ownerId) {
        try {  
          CarService.getAllCarsByOwnerId(ownerId)
            .then((cars: Car[]): void => {
              this.cars = cars;
            });
        } catch (error) {
          console.log(error);
        }
      }
      setTimeout(() => {
        this.isLoading = false;
      }, 1000)
      
    },

    async createCar(car: Car): Promise<void> {
      try {
        await CarService.addCar(car);
        this.getAllCarsForOwner();
        useRouter().push('/insurance');
      } catch (error) {
        console.log(error);
      }
    },

    addCarToContract(car: Car): void {
      this.currentCar = car;
    },

    async updateCar(car: Car): Promise<void> {
      try {
        CarService.updateCar(car)
        .then((): void => {
          this.getAllCarsForOwner();
        });
      } catch (error) {
        console.log(error);
      }
    },

    addCompany(company: Company): void {
      this.currentCompany = company;
    },

    addContract(contract: Contract): void {
      this.currentContract = contract;
    },

    async deleteCar(car: Car): Promise<void> {
      try {
        CarService.deleteCarById(car.id!)
        .then((): void => {
          this.getAllCarsForOwner();
        });
      } catch (error) {
        console.log(error);
      }
    },
  },
})
