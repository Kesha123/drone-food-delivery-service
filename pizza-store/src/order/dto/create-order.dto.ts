import { Order, OrderStatus } from "../entities/order.entity";


export class CreateOrderDto implements Omit<Order, '_id' | 'drone' | 'createdAt' | 'customer' | 'status'> {
    restaurantLocation: string;
    customerLocation: string;
    foodOrder: string[];
}