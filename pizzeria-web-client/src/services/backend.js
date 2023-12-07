
const BASE_URL = process.env.REACT_APP_BACKEND_URL;

export const fetchMenu = async () => {
    try {
        const url = `${BASE_URL}/menu`;
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e);
        return [];
    }
}

export const registerUser = async (email, password, location) => {
    try {
        const url = `${BASE_URL}/user/create`;
        const body = {
            email: email,
            password: password,
            location: location
        }
        const response = await fetch(url, { method: "POST", body: JSON.stringify(body), headers: { "Content-Type": "application/json" } });
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e);
        return [];
    }
}

export const loginUser = async (email, password) => {
    try {
        const url = `${BASE_URL}/user/login`;
        const body = {
            email: email,
            password: password,
        }
        const response = await fetch(url, { method: "POST", body: JSON.stringify(body), headers: { "Content-Type": "application/json" } });
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e);
        return [];
    }
}

export const createOrder = async (order) => {
    const restaurantLocatin = "Hämeenkatu 1";
    const customerLocation = "Kuntokatu 3";
    try {
        const url = `${BASE_URL}/order/create`;
        const body = {
            restaurantLocatin: restaurantLocatin,
            customerLocation: customerLocation,
            foodOrder: order
        }
        const response = await fetch(url, { method: "POST", body: JSON.stringify(body), headers: { "Content-Type": "application/json" } });
        const data = await response.json();
        return data;
    } catch (e) {
        console.error(e);
        return {};
    }
}

export const fetchOrders = async () => {
    return []
}
