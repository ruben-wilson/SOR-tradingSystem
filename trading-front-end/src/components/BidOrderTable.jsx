import { useState } from "react";
import {FormContext} from "../contextProviders/FormContext";
import OrderFrom from "./OrderFrom";
import Row from "./BidRow";

function BidOrderTable( props ) {

  const  [form, setForm]  = useState(false);

  let bids = props.bids;
  if (bids == null) {
    bids = [];
  }

  const handleClick = () => {
    setForm(true);
  };

  return (
    <>
      {form ? (
        <OrderFrom isBid={true} setShowFrom={setForm} />
      ) : (
        <div className="d-flex flex-column">
          <div className="d-flex">
            <h1>Bids</h1>
            <button
              type="button"
              onClick={()=>{setForm(true);}}
              className="btn btn-success pl-4 my-2 mx-4"
            >
              Make a bid
            </button>
          </div>

          <table className="table table-success">
            <thead>
              <tr>
                <th scope="col">Date</th>
                <th scope="col">Order Type</th>
                <th scope="col">Status</th>
                <th scope="col">Initial Quantity</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
              </tr>
            </thead>
            <tbody>
              <Row orders={bids} />
            </tbody>
          </table>
        </div>
      )}
    </>
  );
}

export default BidOrderTable;
