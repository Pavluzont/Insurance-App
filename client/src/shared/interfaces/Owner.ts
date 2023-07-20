import type { Address } from "./Address";
import { Gender } from "../enums/Gender";

export interface Owner {
    id?: number,
    uid?: string,
    firstName?: string,
    lastName?: string,
    email?: string,
    age?: number,
    gender?: Gender,
    phoneNumber?: string,
    address?: Address
}