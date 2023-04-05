import { useState } from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import "./App.css"
import NavBar from "./components/NavBar";
import OrderBook from "./components/OrderBook";
import Home from "./components/home/Home"

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>,
  },
  {
    path: "/orderBook/:symbol",
    element: <OrderBook />,
  },
]);



function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="App">
      <NavBar/>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
