
const BidRow = ({ orders }) => {
  
  return (
    <>
      {orders.map((order, index) => (
        <tr key={index}>
          <th scope="row">{order.date}</th>
          <td>{order.orderType}</td>
          <td>{order.status}</td>
          <td>{order.initialQuantity}</td>
          <td>{order.quantity}</td>
          <td>{order.price}</td>
        </tr>
      ))}
    </>
  );
}

export default BidRow;