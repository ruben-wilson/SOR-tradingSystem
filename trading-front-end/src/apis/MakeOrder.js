import axios from "axios";

const makeOrder = (url, setPrice, setQuantity, setIsBid) => {
    axios
      .post(url, {
        price: parseInt(setPrice),
        quantity: parseInt(setQuantity),
        isBid: setIsBid,
      })
      .catch((error) => {
        console.log(error);
        return null;
      })
      .then((response) => {
        return response.data;
      });
  
};

export default makeOrder;
