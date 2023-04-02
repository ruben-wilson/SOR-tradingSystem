import axios from "axios";
import { useEffect, useState } from "react";

const fetchOrderBook = (url) => {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
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
         console.log(response);
         setData(response.data);
       })
       .finally(() => {
        setLoading(false);
       });
  }, [url]);

  return {data: data, loading: loading, error: error};
};

export default fetchOrderBook;
