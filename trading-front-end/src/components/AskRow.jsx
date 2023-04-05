const AskRow = ({ orders }) => {

  return (
    <>
      {orders.map((order, index) => (
        <tr key={index}>
          <td>{order.price}</td>
          <td>{order.quantity}</td>
          <td>{order.initialQuantity}</td>
          <td>{order.orderType}</td>
          <td>{order.status}</td>
          <th scope="row">{order.date}</th>
        </tr>
      ))}
    </>
  );
};

export default AskRow;
