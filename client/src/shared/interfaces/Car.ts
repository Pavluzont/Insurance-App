import { Contract } from "./Contract";
import { Owner } from "./Owner";

export interface Car {
    id?: number, 
    name: string,
    model: string,
    vin: string,
    color: string,
    plateNum: string,
    owner?: Owner,
    contract?: Contract
}