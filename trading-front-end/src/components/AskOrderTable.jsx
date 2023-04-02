import Row from "./AskRow";


function AskOrderTable ({ asks }) {
  if(asks == null ){
    asks = [];
  }
  return (
    <>
      <div className="d-flex flex-column justify-content-end">
        <div className="container d-flex justify-content-end ">
          <button type="button" className="btn btn-danger pl-4 my-2 mx-4">
            Make a Sell 
          </button>
          <h1>Asks</h1>
        </div>

        <table className="table table-danger">
          <thead>
            <tr>
              <th scope="col">Price</th>
              <th scope="col">Status</th>
              <th scope="col">Order Type</th>
              <th scope="col">Quantity</th>
              <th scope="col">Initial Quantity</th>
              <th scope="col">Date</th>
            </tr>
          </thead>
          <tbody>
            <Row orders={asks} />
          </tbody>
        </table>
      </div>
    </>
  );
}




export default AskOrderTable;
