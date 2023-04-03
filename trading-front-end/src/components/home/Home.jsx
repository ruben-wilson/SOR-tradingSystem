import React from "react";
import "../home/Home.scss";
import { Button } from "react-bootstrap";
import homewallpaper from "./homewallpaper.jpeg";

export default function Home() {
  return (
    <div
      className="background"
      style={{
        backgroundImage: `url(${homewallpaper})`,
      }}
    >
      <div className="content">
        <div className="text-container">
          <h1>Trading App</h1>
          <h2 className="home-text-sub">A place to track your trades</h2>
          <Button className="home-button" href="/orderbook">
            Check your Orders
          </Button>
        </div>
      </div>
    </div>
  );
}
