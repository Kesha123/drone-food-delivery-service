import { Model, Schema, model } from "mongoose";
import { Order, OrderStatus } from "../../order/entities/order.entity";


export const OrderSchema: Schema<Order> = new Schema({
        staus: { type: String, enum: OrderStatus, required: true },
        drone: { type: String, required: false },
        customer: { type: String, required: true },
        restaurantLocation: { type: String, required: true },
        customerLocation: { type: String, required: true },
        foodOrder: { type: String, required: true },
    },
    {
        timestamps: true
    }
)

export const OrderModel = model<Order>('Order', OrderSchema)

OrderModel.schema.static('findByStatus', function findByStatus(status: OrderStatus) {
    return this.findOne({ staus: status }).lean();
})

OrderModel.schema.static('findByDrone', function findByDrone(drone: string) {
    return this.findOne({ drone: drone }).lean();
})

OrderModel.schema.static('findByCustomer', function findByCustomer(customerId: string) {
    return this.findOne({ customer: customerId }).lean();
})

export interface IOrderModel extends Model<Order> {
    findByStatus(status: OrderStatus): Promise<Order[]>,
    findByDrone(drone: string): Promise<Order[]>,
    findByCustomer(customerId: string) : Promise<Order[]>,
}