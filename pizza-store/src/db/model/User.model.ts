import { Model, Schema, model } from "mongoose";
import { User } from "../../user/entities/user.entity";
import { Injectable } from "@nestjs/common";

export const UserSchema: Schema<User> = new Schema({
    email: { type: String, required: true, unique: true, index: true },
    password: { type: String, required: true },
    location: { type: String, required: false },
})

export const UserModel = model<User>('User', UserSchema);

UserModel.schema.static('findByEmail', function findByEmail(email: String) {
    return this.findOne({ email: email }).lean();
})

export interface IUserModel extends Model<User> {
    findByEmail(email: String): Promise<User>;
}