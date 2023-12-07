import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { TabMenu } from './components/TabMenu';
import { Login } from './components/Login'
import { Signup } from './components/SignUp'
import { Menu } from './components/Menu'
import { OrderList } from './components/Order'
import { useState } from 'react';

function App() {

  const [ order, setOrder ] = useState([]);
  const [ orderSwitch, setOrderSwitch ] = useState(false);
  const [ totalItemsNumber, setTotalItemsNumber ] = useState(0);
  const [ totalPrice, setTotalPrice ] = useState(0);
  const [ totalWeight, setTotalWeight ] = useState(0);

  return (
    <BrowserRouter>
    <Routes className="App-header">
      <Route path="/" element={
            <TabMenu
              order={order}
              totalItemsNumber={totalItemsNumber}
              totalPrice={totalPrice}
              totalWeight={totalWeight}
              setOrder={setOrder}
              setTotalItemsNumber={setTotalItemsNumber}
              setTotalPrice={setTotalPrice}
              setTotalWeight={setTotalWeight}
              setOrderSwitch={setOrderSwitch}
              orderSwitch={orderSwitch}/>
        }>
        <Route path="login" element={<Login />} />
        <Route path="signup" element={<Signup />} />
        <Route path="menu" element={
              <Menu
                order={order}
                setOrder={setOrder}
                setTotalItemsNumber={setTotalItemsNumber}
                setTotalPrice={setTotalPrice}
                setTotalWeight={setTotalWeight}
                totalItemsNumber={totalItemsNumber}
                totalPrice={totalPrice}
                totalWeight={totalWeight}
                orderSwitch={orderSwitch}/>
        }/>
        <Route path="orders" element={<OrderList />} />
        <Route path="/" element={<Menu />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
