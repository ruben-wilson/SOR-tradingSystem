import React, {useState} from "react";
import "./home.css";


import NavBar from "../NavBar";
import SearchBar from "../SearchBar";

export default function Home() {

  const [inputValue, setInputValue] = useState("");

  const items = [
    "Apples",
    "Bananas",
    "Oranges",
    "Pineapples",
    "Mangoes",
    "Grapes",
  ];


  return (
    <>
      <div className="mainContainer">
        <div className="container-fluid" id="title">
          <p className="text-info" id="titleText">
            Crypto Exchange
          </p>
        </div>

        <div className="container-fluid p-0" id="searchBox">
          <SearchBar />
        </div>
        {/* <div className="image"></div> */}
      </div>
    </>
  );
}
