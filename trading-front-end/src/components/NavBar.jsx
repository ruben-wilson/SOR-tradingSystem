import React from "react";

import { NavLink } from 'react-router-dom'
import { Navbar, Nav, Container } from 'react-bootstrap'
import { userIsAuthenticated } from '../components/utilities/auth'

function NavBar() {
  return (
    // <nav className="navbar navbar-expand-lg bg-body-tertiary">
    //   <div className="container-fluid">
    //     <a className="navbar-brand" href="#">
    //       Navbar
    //     </a>
    //     <button
    //       className="navbar-toggler"
    //       type="button"
    //       data-bs-toggle="collapse"
    //       data-bs-target="#navbarNav"
    //       aria-controls="navbarNav"
    //       aria-expanded="false"
    //       aria-label="Toggle navigation"
    //     >
    //       <span className="navbar-toggler-icon"></span>
    //     </button>
    //     <div className="collapse navbar-collapse" id="navbarNav">
    //       <ul className="navbar-nav">
    //         <li className="nav-item">
    //           <a className="nav-link active" aria-current="page" href="#">
    //             Home
    //           </a>
    //         </li>
    //         <li className="nav-item">
    //           <a className="nav-link" href="#">
    //             Features
    //           </a>
    //         </li>
    //         <li className="nav-item">
    //           <a className="nav-link" href="#">
    //             Pricing
    //           </a>
    //         </li>
    //         <li className="nav-item">
    //           <a className="nav-link disabled">Disabled</a>
    //         </li>
    //       </ul>
    //     </div>
    //   </div>
    // </nav>

<Navbar>
  <Container>
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="me-auto">
        <NavLink exact to="/" className="nav-link">Home</NavLink>
      </Nav>

      {userIsAuthenticated() ?
        <>
          {/* <Nav.Link onClick={handleLogOut}>Logout</Nav.Link> */}
          <NavLink to="/profile" className="nav-link">Profile</NavLink>
          <NavLink to="/orders" className="nav-link">Orders</NavLink>
        </>
        :
        <>
          <NavLink to="/register" className="nav-link">Register</NavLink>
          <NavLink to="/orders" className="nav-link">Orders</NavLink>
          <NavLink to="/login" className="nav-link">Login</NavLink>
        </>
      }
    </Navbar.Collapse>
  </Container>
</Navbar>
  );
}

export default NavBar;