export interface Order {
    _id?: string;
    status: OrderStatus;
    drone: string;
    customer: string;
    restaurantLocation: string;
    customerLocation: string;
    foodOrder: string[];
    createdAt?: string;
}

export interface FleetServiceOrder extends Order {
    restaurantEndpoint: string;
}

export enum OrderStatus {
    Waiting = 'WAITING',
    Created = 'CREATED',
    Cooking = 'COOKING',
    Delivering = 'DELIVERING',
    Delivered = 'DELIVERED'
}