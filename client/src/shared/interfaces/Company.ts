import { Address } from "./Address";
import { Consultant } from "./Consultant";
import { Plan } from "./Plan";

export interface Company {
    id?: number, 
    name: string,
    address: Address,
    consultants: Consultant[],
    plans: Plan[],
}