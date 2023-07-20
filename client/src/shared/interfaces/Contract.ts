import { Company } from "./Company"
import { Consultant } from "./Consultant"
import { Plan } from "./Plan"

export interface Contract {
    id?: number,
    plan?: Plan,
    insuranceCompany?: Company,
    consultant?: Consultant,
    startDate?: Date,
    endDate?: string,
    createdDate?: Date,
    claims?: [],
}
