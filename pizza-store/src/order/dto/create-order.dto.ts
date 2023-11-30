import { Order, OrderStatus } from "../entities/order.entity";


export class CreateOrderDto implements Omit<Order, '_id' | 'drone' | 'createdAt'> {
    staus: OrderStatus;
    customer: string;
    restaurantLocation: string;
    customerLocation: string;
    foodOrder: string;
}