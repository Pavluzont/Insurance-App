import { Gender } from "../enums/Gender";
import { Address } from "./Address";

export interface Consultant {
    id?: number,
    firstName: string,
    lastName: string,
    email: string,
    age: number,
    gender: Gender,
    phoneNumber: string,
    address: Address
}