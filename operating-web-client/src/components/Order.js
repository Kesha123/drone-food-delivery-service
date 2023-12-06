import React, { useState, useEffect } from 'react';
import { fetchOrders } from '../services/fleet';
import '../styles/Order.css'


export const OrderList = () => {
    const [ orderList, setOrderList ] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const data = await fetchOrders();
            setOrderList(data);
        }
        fetchData();
    }, [])

    return (
        <div className="order-list">
          {orderList.map((order) => (
            <Order
              key={order.id}
              status={order.status}
              drone={order.drone}
              restaurantLocation={order.restaurantLocation}
              customerLocation={order.customerLocation}
              foodOrder={order.foodOrder}
              createdAt={order.createdAt}
            />
          ))}
        </div>
    );
}


export const Order = ({ status, drone, restaurantLocation, customerLocation, foodOrder, createdAt }) => {

    return (
        <div className="order">
          <p>Status: {status}</p>
          <p>Drone: {drone}</p>
          <p>Restaurant Location: {restaurantLocation}</p>
          <p>Customer Location: {customerLocation}</p>
          <p>Food Order: {foodOrder}</p>
          <p>Created At: {(new Date(createdAt)).toUTCString()}</p>
        </div>
    );

}