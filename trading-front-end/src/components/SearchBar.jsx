import React, {useState} from "react";

import {AiOutlineSearch } from "react-icons/ai"

const SearchBar = () => {

   const styles = {
    backgroundColor: 'blue',
    color: 'white',
    fontSize: '24px',
    padding: '10px',
    borderRadius: '5px',
  };

  const [inputValue, setInputValue] = useState("");

  const [symbolsArray, setSymbolsArray] = useState([]);

  const symbols = [
    {
      symbol: "ETHUSD",
      url: "/orderBook/ETHUSD",
    },
    {
      symbol: "BTCGBP",
      url: "/orderBook/BTCGBP",
    },
    {
      symbol: "BTCUSD",
      url: "/orderBook/BTCUSD",
    },
    {
      symbol: "ETHGBP",
      url: "/orderBook/ETHGBP",
    },
    {
      symbol: "LTCUSD",
      url: "/orderBook/LTCUSD",
    },
  ];



  const onChange = (e) =>{
    setInputValue(e.target.value);
 
    const symbolsToMatch = [
      "ETHUSD",
      "BTCGBP",
      "BTCUSD",
      "LTCUSD",
      "ETHGBP"
    ];

    const regex = new RegExp(`${inputValue.replace(/\s+/g, "")}`, "gi");
    const matches = symbolsToMatch.filter((symbol) => regex.test(symbol));

    for(const symbol of symbols){
      const isPresent = symbolsArray.some(
        (obj) => obj.symbol === symbol.symbol && obj.url === symbol.url
      );
      if (matches.includes(symbol.symbol) && !isPresent){

        setSymbolsArray([...symbolsArray, symbol]);
      }else if (!matches.includes(symbol.symbol) && isPresent) {
        
        const newSymbolsArray = symbolsArray.filter(
          (s) => s.symbol !== symbol.symbol && s.url !== symbol.url
        );

        setSymbolsArray(newSymbolsArray);
      }else if(inputValue === ""){
        console.log("here")
        setSymbolsArray([]);
      }
    }
    
    
  }

  return (
    <>
      <div className="container-fluid">
        <div className="input-group">
          <input
            type="text"
            value={inputValue}
            className="form-control text-info"
            placeholder="Search exchanges"
            onChange={onChange}
          />
          <div className="input-group-append bg-primary rounded-pill">
            <button className="btn btn-secondary bg-info " type="button">
              <AiOutlineSearch />
            </button>
          </div>
        </div>
        <div className="container-fluid bg-white p-0" style={styles}>
          {symbolsArray.map((symbol, index) => (
            <div key={index}>
              <a className="btn" href={symbol.url}>
                <h1 href={symbol.url} className="text-info">
                  {symbol.symbol}
                </h1>
              </a>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default SearchBar;
