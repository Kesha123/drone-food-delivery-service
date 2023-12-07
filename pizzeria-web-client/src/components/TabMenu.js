import { Outlet, Link } from "react-router-dom";
import "../styles/TabMenu.css"
import { createOrder } from "../services/backend";


export function TabMenu({
  totalItemsNumber,
  totalPrice,
  totalWeight,
  order,
  setTotalItemsNumber,
  setTotalPrice,
  setTotalWeight,
  setOrder,
  setOrderSwitch,
  orderSwitch
}) {

  const handleCreateOrder = async () => {
    const newOrder = await createOrder(order);
    setOrder([]);
    setTotalItemsNumber(0);
    setTotalPrice(0);
    setTotalWeight(0);
    setOrderSwitch(!orderSwitch)
  }

  return (
    <div>
      <nav className="tab-menu">
        <ul>
          <li>
            <Link to="/login" className="tab-link" activeclassname="active">
              Log In
            </Link>
          </li>
          <li>
            <Link to="/signup" className="tab-link" activeclassname="active">
              Sign Up
            </Link>
          </li>
          <li>
            <Link to="/menu" className="tab-link" activeclassname="active">
              Menu
            </Link>
          </li>
          <li>
            <Link to="/orders" className="tab-link" activeclassname="active">
              My Orders
            </Link>
          </li>
        </ul>
        <>
          <p><b>In the cart</b>: items - {totalItemsNumber}; weight: {totalWeight}g; price: €{Math.round(totalPrice)}</p>
          <button onClick={handleCreateOrder} >Create Order</button>
        </>
      </nav>

      <Outlet />
    </div>
  )
};
