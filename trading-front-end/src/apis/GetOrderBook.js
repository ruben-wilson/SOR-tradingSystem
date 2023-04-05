import axios from "axios";
import { useEffect, useState } from "react";

const fetchOrderBook = (url, symbol) => {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [reload, setReload] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const timer = setInterval(() => { 
      axios
        .get(url, {
          params: {
            symbol: symbol,
          },
        })
        .catch((error) => {
          console.log(error);
          setError(error);
        })
        .then((response) => {
          console.log(response);
          response.data == "undefined"
            ? setReload(true)
            : setData(response.data);
          
        })
        .finally(() => {
          setLoading(false);
        });
    }, 1000);

    return () => {
      clearInterval(timer);
      
    };

  }, []);

  return {data: data, loading: loading, error: error, reload: reload};
};

export default fetchOrderBook;
