import { Outlet, Link } from "react-router-dom";
import "../styles/TabMenu.css"


export function Menu() {
  return (
    <div>
      <nav className="tab-menu">
        <ul>
          <li>
            <Link to="/drone-register" className="tab-link" activeclassname="active">
              Drone Register
            </Link>
          </li>
          <li>
            <Link to="/drones" className="tab-link" activeclassname="active">
              Drone List
            </Link>
          </li>
          <li>
            <Link to="/orders" className="tab-link" activeclassname="active">
              Order List
            </Link>
          </li>
        </ul>
      </nav>

      <Outlet />
    </div>
  )
};
