import React, { useState, useRef } from 'react';
import { registerDroneFleet } from '../services/fleet';
import '../styles/DroneRegister.css'


export const DroneRegister = ({  }) => {

    const droneNickName = useRef(null);
    const droneCapacity = useRef(null);
    const droneHost = useRef(null);
    const droneLocation = useRef(null);

    const registerDrone = async () => {
        try {
            const newDrone = await registerDroneFleet(
                    droneNickName.current.value,
                    droneCapacity.current.value,
                    droneHost.current.value,
                    droneLocation.current.value
                );
        } catch (e) {
            console.error(e)
        }
        resetValues();
    }

    const resetValues = () => {
      droneNickName.current.value = ""
      droneCapacity.current.value = ""
      droneHost.current.value = ""
      droneLocation.current.value = ""
    }

    return (
      <div className="container">
        <h2 className="heading">Drone Registration</h2>
        <label className="label">
          Nickname:
          <input type="text" ref={droneNickName} className="input" />
        </label>
        <br />
        <label className="label">
          Capacity:
          <input type="number" ref={droneCapacity} className="input" />
        </label>
        <br />
        <label className="label">
          Host:
          <input type="text" ref={droneHost} className="input" />
        </label>
        <br />
        <label className="label">
          Location:
          <input type="text" ref={droneLocation} className="input" />
        </label>
        <br />
        <button onClick={registerDrone} className="button">
          Register New Drone
        </button>
      </div>
    );
}