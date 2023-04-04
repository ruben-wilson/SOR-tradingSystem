import axios from "axios";
import { useEffect, useState } from "react";

const fetchOrderBook = (url) => {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [reload, setReload] = useState(false);
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
          console.log(error);
          setError(error);
        })
        .then((response) => {
          response.data == "undefined"
            ? setReload(true)
            : setData(response.data);
          
        })
        .finally(() => {
          setLoading(false);
        });
    }, 500);

    return () => {
      clearInterval(timer);
      
    };

  }, []);

  return {data: data, loading: loading, error: error, reload: reload};
};

export default fetchOrderBook;
