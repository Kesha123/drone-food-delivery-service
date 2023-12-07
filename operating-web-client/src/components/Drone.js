import React, { useState, useEffect } from 'react';
import { fetchDrones } from '../services/fleet';
import '../styles/Drone.css'


export const DroneList = () => {

    const [ droneList, setDroneList ] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const data = await fetchDrones();
            setDroneList(data);
        }
        fetchData();
    }, [])

    return (
        <div className="drone-list">
            {droneList.map((drone) => (
                <Drone
                    key={drone.id}
                    nickname={drone.nickname}
                    host={drone.host}
                    location={drone.location}
                    chargeLevel={drone.chargeLevel}
                    available={drone.available}
                    order={drone.order}
                />
            ))}
        </div>
    )

}


export const Drone = ({ nickname, host, location, chargeLevel, available, order }) => {

    return (
        <div className="drone">
            <p>Nickname: {nickname}</p>
            <p>Host: {host}</p>
            <p>Location: {location}</p>
            <p>Charge Level: {chargeLevel}</p>
            <p>Available: {available.toString()}</p>
            <p>Order: {order}</p>
        </div>
    );

}
