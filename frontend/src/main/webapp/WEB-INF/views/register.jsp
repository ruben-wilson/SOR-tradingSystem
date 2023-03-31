<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  <style>
  .formcontainer {
  width: 80vw;
  margin: auto; 
  display: flex; 
  padding-top: 15vw;
  align-items: center;
  justify-content: space-around;
  flex-direction: row;
  }
  .infocontainer {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50%;
  margin: 2rem;
  text-align: center;
  }
  .subformcontainer {
  width: 50%;
  margin: 2rem;
  }
  h3 {
    font-size: 1rem;
    width: 66%;
  }
  </style>
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.min.js" integrity="sha384-heAjqF+bCxXpCWLa6Zhcp4fu20XoNIA98ecBC1YkdXhszjoejr5y9Q77hIrv8R9i" crossorigin="anonymous"></script>
  </body>
</html>
<body class="body">

<div class="formcontainer">
  <div class="infocontainer">
    <h1 style="text-align: center;">Create an Account</h1>
    <h3>Already have an account? Log in <a href="/login">here</a></h3>
  </div>
  <form class="subformcontainer" action="/register" method="POST">
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Email address</label>
      <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">First Name</label>
      <input type="text" name="firstName" class="form-control" id="firstname">
    </div>
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Last Name</label>
      <input type="email" name="lastName" class="form-control" id="lastname">
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input type="password" name="password" class="form-control" id="password">
    </div>
    <%-- <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Confirm Password</label>
      <input type="password" class="form-control" id="exampleInputPassword1">
    </div> --%>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>