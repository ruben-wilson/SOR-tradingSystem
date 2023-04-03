import React, { useState } from 'react'

import { Link, useNavigate } from 'react-router-dom'
import { Navbar, Nav, Container } from 'react-bootstrap'

import { userIsAuthenticated } from './auth/auth'

export default function PageNavBar(props) {


  const Navigate = useNavigate()

  const handleLogOut = () => {
    window.localStorage.removeItem('trading-app-token')
    Navigate('/login')
  }

  return (
    <Navbar>
      <Container>
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            
            <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>
          </Nav>

          {userIsAuthenticated() ?
            <>
              <Nav.Link onClick={handleLogOut}>Logout</Nav.Link>
              <Nav.Link as={Link} to="/profile">Profile</Nav.Link>
              <Nav.Link as={Link} to="/orders">Orders</Nav.Link>
            </>
            :
            <>
              <Nav.Link as={Link} to="/register">Register</Nav.Link>
              <Nav.Link as={Link} to="/orders">Orders</Nav.Link>
              <Nav.Link as={Link} to="/login">Login</Nav.Link>
            </>
          }
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}
