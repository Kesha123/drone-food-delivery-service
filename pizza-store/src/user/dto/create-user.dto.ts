import { IsEmail, IsNotEmpty } from "class-validator";
import { User } from "../entities/user.entity";

export class CreateUserDto implements Omit<User, '_id'> {

    @IsEmail()
    email: string;

    @IsNotEmpty()
    password: string;

}
