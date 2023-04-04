import { useContext, useEffect, useState } from "react";
import axios from "axios";

import AskOrderTable from "./AskOrderTable";
import BidOrderTable from "./BidOrderTable";
import OrderFrom from "./OrderFrom";

import getOrderBook from ".././apis/GetOrderBook";
import {FormContext} from "../contextProviders/FormContext";


function OrderBook() {

  const { data, loading, error, reload } = getOrderBook(
    "http://localhost:8080/liveOrderBook?symbol=ETHUSD&"
  );
  


  if (loading) return <p>Loading...</p>;
  if (error) return console.log(Error);
  if (reload) return window.location.reload();

  const handleButtonClick = () => {
    setShowForm(true);
  };

  return (
    <>
      <div className="container-sm">
        <div className="row align-items-start">
          <div className="col m-0 p-0">
           <BidOrderTable bids={data?.bids} />
          </div>
          <div className="col m-0 p-0">
            <AskOrderTable asks={data?.asks} />
          </div>
        </div>
      </div>
    </>
  );
}

export default OrderBook;
