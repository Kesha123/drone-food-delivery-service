import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { DroneRegister } from './components/DroneRegister';
import { OrderList } from './components/Order';
import { Menu } from './components/Menu';
import { DroneList } from './components/Drone';

function App() {
  return (
  <BrowserRouter>
    <Routes className="App-header">
      <Route path="/" element={<Menu />}>
        <Route path="drone-register" element={<DroneRegister />} />
        <Route path="drones" element={<DroneList />} />
        <Route path="orders" element={<OrderList />} />
        <Route path="/" element={<OrderList />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
