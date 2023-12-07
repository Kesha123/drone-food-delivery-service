import React, { useEffect, useState } from 'react';
import  { fetchMenu } from '../services/backend'
import '../styles/Menu.css'

export const Menu = ({
    setTotalItemsNumber,
    setTotalPrice,
    setTotalWeight,
    setOrder,
    totalItemsNumber,
    totalPrice,
    totalWeight,
    order,
    orderSwitch
}) => {

    const [ items, setItems ] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const data = await fetchMenu();
            setItems(data);
        }
        fetchData();
    }, [])

    return(
        <div className="menu">
            {items.map((item) => {
                const image = require("../assets/" + item.name.split(" ").join("-").toLowerCase() + ".jpg");
                return <MenuItem
                    key={item.id}
                    name={item.name}
                    weight={item.weight}
                    price={item.price}
                    image={image}
                    setOrder={setOrder}
                    setTotalItemsNumber={setTotalItemsNumber}
                    setTotalPrice={setTotalPrice}
                    setTotalWeight={setTotalWeight}
                    totalItemsNumber={totalItemsNumber}
                    totalPrice={totalPrice}
                    totalWeight={totalWeight}
                    order={order}
                    orderSwitch={orderSwitch}
                />
            })}
        </div>
    );

}

export const MenuItem = ({
    name,
    weight,
    price,
    image,
    setTotalItemsNumber,
    setTotalPrice,
    setTotalWeight,
    setOrder,
    totalItemsNumber,
    totalPrice,
    totalWeight,
    order,
    orderSwitch
}) => {

    const [quantity, setQuantity] = useState(0);

    useEffect(() => {
        setQuantity(0)
    }, [orderSwitch])

    const handleAdd = () => {
        setTotalItemsNumber(totalItemsNumber + 1);
        setTotalPrice(totalPrice + price);
        setTotalWeight(totalWeight + weight);
        setQuantity(quantity + 1);
        setOrder([...order, name])
    };

    const handleRemove = () => {
        if (quantity > 0) {
            setTotalItemsNumber(totalItemsNumber - 1);
            setTotalPrice(totalPrice - price);
            setTotalWeight(totalWeight - weight)
            setQuantity(quantity - 1);
            order.splice(order.indexOf(name), 1);
            setOrder([...order])
        }
    };

    return (
        <div className="menu-item">
            <p>{name}</p>
            <p>{weight}g</p>
            <p>€{price}</p>
            <img src={image} alt={name} />
            <div className="quantity-controls">
                <button onClick={handleRemove}>-</button>
                <span>{quantity}</span>
                <button onClick={handleAdd}>+</button>
            </div>
        </div>
    );
}