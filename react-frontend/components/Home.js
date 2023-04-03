import React from 'react'
import '../styles/components/home.scss'
import Button from 'react-bootstrap/Button'

export default function Home() {
  return (
    <div className='background' style={{ backgroundImage: `url(${require('../styles/homewallpaper.jpg')})` }}> 
      <div className='content'>
        <div className='text-container'>
          <h1>Trading App</h1>
          <h2 className='home-text-sub'>A place to track your trades</h2>
          <Button className='home-button' href='/orders'>Check your Orders</Button>
        </div>
      </div>
    </div>
  )
}
