import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import appCss from "./App.css?inline"

import NavBar from "./components/NavBar";
import Home from "./components/home/Home";
import OrderBook from "./components/OrderBook";
import {FormContext} from "./contextProviders/FormContext";
import { BrowserRouter, Route, Routes } from "react-router-dom";



function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="App">
      <NavBar />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
        {/* <OrderBook /> */}
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
