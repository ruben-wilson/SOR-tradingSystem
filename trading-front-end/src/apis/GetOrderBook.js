import axios from "axios";
import { useEffect, useState } from "react";

const fetchOrderBook = (url) => {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const timer = setInterval(() => { 
      axios
        .get(url, {
          params: {
            id: 1,
          },
        })
        .catch((error) => {
          setError(error);
        })
        .then((response) => {
          setData(response.data);
        })
        .finally(() => {
          setLoading(false);
        });
    }, 2000);

    return () => {
      clearInterval(timer);
      
    };

  }, []);

  return {data: data, loading: loading, error: error};
};

export default fetchOrderBook;
