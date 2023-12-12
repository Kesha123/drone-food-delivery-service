
// const BASE_URL = process.env.REACT_APP_FLEET_SERVICE;
const BASE_URL = "http://localhost:8080/dronora";

export const registerDroneFleet = async (nickname, capacity, host, location) => {
    try {
        const url = `${BASE_URL}/drones`;
        const body = {
            nickname: nickname,
            capacity: capacity,
            host: host,
            location: location
        };
        const response = await fetch(url, { method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(body)})
        const data = await response.json();
        console.log(data)
        return data;
    } catch (e) {
        console.error(e)
        return null;
    }
}

export const fetchDrones = async () => {
    try {
        const url = `${BASE_URL}/drones/all`;
        const response = await fetch(url, { method: "GET" });
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e)
        return [];
    }
}

export const fetchOrders = async () => {
    try {
        const url = `${BASE_URL}/flights/all`;
        const response = await fetch(url, { method: "GET" });
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e)
        return [];
    }
}