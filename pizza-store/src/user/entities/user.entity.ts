import { Exclude } from "class-transformer";

export class User {
    _id?: string
    email: string
    location?: string
    password: string
}

export class SerializedUser {
    _id?: string;
    email: string;
    location?: string

    @Exclude({ toPlainOnly: true })
    password: string

    constructor(partial: Partial<SerializedUser>) {
        Object.assign(this, partial);
    }
}