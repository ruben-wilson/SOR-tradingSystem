<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Make an Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.min.js" integrity="sha384-heAjqF+bCxXpCWLa6Zhcp4fu20XoNIA98ecBC1YkdXhszjoejr5y9Q77hIrv8R9i" crossorigin="anonymous"></script>
  </body>
</html>
<body class="body">
  
  <!-- a form container div tag that has a width of 70% of the body and height and that is shown as a column -->
  <div class="formcontainer" style="
    width: 80%; 
    height: 80%;
    margin: auto; 
    display: flex; 
    flex-direction: column;
    margin-top: 20%;
    margin-bottom: 20%;
    ">
    <h1 style="text-align: center;">Place a new order</h1>
    <form action="/order" method="POST">
    <div class="mb-3">
      <label for="order-type-select">Order Type:</label>
      <div class="selectcustomform">
        <select name="orderType" class="form-select" id="order-type-select" aria-label="Default select example">
          <option disabled selected value="">Select an option</option>
          <option value="buy">Buy</option>
          <option value="sell">Sell</option>
        </select>
      </div>
    </div>
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">Price</label>
      <div class="input-group">
        <span class="input-group-text">$/£</span>
        <input type="text" name="price" class="form-control" aria-label="Amount">
        <span class="input-group-text">.00</span>
      </div>
    </div>
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">Quantity</label>
      <div class="input-group">
        <span class="input-group-text">Amount</span>
        <input type="text" name="amount" class="form-control" aria-label="Amount">
      </div>
    </div>

    <button type="submit" class="btn btn-primary" style="width: 20%; margin: auto;">Submit</button>
      </form>
  </div>

</body>