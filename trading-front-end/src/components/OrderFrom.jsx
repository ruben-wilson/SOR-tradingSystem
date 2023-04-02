import { useState, useEffect, useContext } from "react";
import axios from "axios";

import makeOrder from "../apis/MakeOrder";
import {FormContext} from "../contextProviders/FormContext";

const OrderFrom = (props) => {

  const [price, setPrice] = useState(0);
  const [quantity, setQuantity] = useState(0);



  const handleClick = async (orderPrice, orderQuantity, setIsBid) =>{
    const response = await makeOrder(
      "http://localhost:8080/addOrder",
      orderPrice,
      orderQuantity,
      setIsBid
    );
    
    if(response){
      console.log("post request failed!");
    }

    props.setShowFrom(false);
  }
  
  
  return (
    <>
      <div className="input-group mb-3">
        <span className="input-group-text">Price</span>
        <span className="input-group-text">£</span>
        <input
          type="number"
          className="form-control"
          onChange={(e) => {
            setPrice(e.target.value);
          }}
        />
        <span className="input-group-text">.00</span>
      </div>

      <div className="input-group mb-3">
        <span className="input-group-text">Quantity</span>
        <input
          type="number"
          className="form-control"
          onChange={(e) => {
            setQuantity(e.target.value);
          }}
        />
      </div>

      <button
        type="button"
        className="btn btn-primary"
        onClick={() => {
          handleClick(price, quantity, props.isBid);
        }}
      >
        Make Order
      </button>
    </>
  );
};

export default OrderFrom;
