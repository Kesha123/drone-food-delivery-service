

export interface Order {
    _id?: string;
    staus: OrderStatus;
    drone: string;
    customer: string;
    restaurantLocation: string;
    customerLocation: string;
    foodOrder: string;
    createdAt: string;
}

export enum OrderStatus {
    Waiting = 'WAITING',
    Created = 'CREATED',
    Cooking = 'COOKING',
    Delivering = 'DELIVERING',
    Delivered = 'DELIVERED'
}