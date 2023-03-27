<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/styles.css">
<meta charset="UTF-8">
<title>Order form</title>
</head>
<body>

<h1>Place a new order</h1>
		<div class="form">
			<form method="POST" action="/order">
      <p>
        <label for="orderType">Order Type:</label>
        <select name="orderType" id="orderType">
          <option value="buy">Buy</option>
          <option value="sell">Sell</option>
        </select>
        </p>
				<p>
					<label for="price">Price:</label> <input name="price"
						id="price" />
				</p>
				<p>
					<label for="quantity">Quantity:</label> <input name="quantity"
						id="quantity" />
				</p>
				<input type="submit" value="Create Account" />
			</form>
		</div>

</body>