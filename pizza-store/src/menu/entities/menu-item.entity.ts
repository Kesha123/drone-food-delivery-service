import { Exclude } from "class-transformer";


export class MenuItem {
    @Exclude()
    _id?: string;
    name: string;
    weight: string;
    price: string;
}