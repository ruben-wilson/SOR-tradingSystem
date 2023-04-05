import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import AskOrderTable from "./AskOrderTable";
import BidOrderTable from "./BidOrderTable";
import OrderFrom from "./OrderFrom";


import getOrderBook from ".././apis/GetOrderBook";
// import Graph from "./Graph";



function OrderBook() {



  const { symbol } = useParams();

  const { data, loading, error, reload } = getOrderBook(
    "http://localhost:8080/liveOrderBook", symbol
  );
  


  if (loading) return <h1 className="text-info" >Loading...</h1>;
  if (error) return console.log(Error);
  if (reload) return window.location.reload();

  const handleButtonClick = () => {
    setShowForm(true);
  };

  return (
    <>
      <div className="container-sm">
        <div className="d-flex align-items-center justify-content-center">
          <h1 className="text-info">{symbol}</h1>
        </div>

        <div className="row align-items-start">
          <div className="col m-0 p-0">
            <BidOrderTable bids={data?.bids} />
          </div>
          <div className="col m-0 p-0">
            <AskOrderTable asks={data?.asks} />
          </div>
        </div>

        {/* <Graph/> */}
      </div>
    </>
  );
}

export default OrderBook;
