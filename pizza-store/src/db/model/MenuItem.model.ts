import { Model, Schema, model } from "mongoose";
import { MenuItem } from "../../menu/entities/menu-item.entity";


export const MenuItemSchema: Schema<MenuItem> = new Schema({
    name: { type: String, required: true },
    weight: { type: String, required: true },
    price: { type: String, required: true },
})

export const MenuItemModel = model<MenuItem>('MenuItem', MenuItemSchema);

export interface IMenuItemModel extends Model<MenuItem> {}