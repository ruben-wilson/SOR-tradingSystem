import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import appCss from "./App.css";

import NavBar from "./components/NavBar";
import OrderBook from "./components/OrderBook";
import {FormContext} from "./contextProviders/FormContext";



function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="App">
      <NavBar />

      
        <OrderBook />

    </div>
  );
}

export default App;
