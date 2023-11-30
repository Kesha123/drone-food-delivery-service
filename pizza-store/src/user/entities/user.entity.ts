import { Exclude } from "class-transformer";

export interface User {
    _id?: string
    email: string
    password: string
}

export class SerializedUser {
    _id?: string;
    email: string;

    @Exclude({ toPlainOnly: true })
    password: string

    constructor(partial: Partial<SerializedUser>) {
        Object.assign(this, partial);
    }
}