import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Container, Row, Card } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'

export default function Orders() {

  const navigate = useNavigate()
  const [ formData, setFormData ] = useState({
    orderType: [],
    amount: '',
    price: '',
  })

  const [ errors, setErrors ] = useState({})
  const [ orders, setOrders ] = useState([])

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value })
    setErrors({ ...errors, [e.target.name]: '' })
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    try {
      await axios.post('/api/neworder', formData)
      navigate('/')
    } catch (error) {
      console.log(error)
      console.log(error.response)
      console.log(error.response.data)
      setErrors(error.response.data)
    }
  }

  useEffect(() => {
    const getOrders = async () => {
      try {
        const { data } = await axios.get('/api/orders')
        setOrders(data)
      } catch (error) {
        console.log(error)
      }
    }
    getOrders()
  }, [])

  return (
<>
    <section className="order-list">
      <Container>
        { orders ?
          
          // map through orders and display them
          orders.map(order => (
              <Card key={order.orderId}>
                  <Card.Body>
                    <p>Order Type: {order.orderType}</p>
                    <p>Amount: {order.amount}</p>
                    <p>Price: {order.price}</p>
                    <p>Order Date: {order.orderDate}</p>
                    <p>Order Status: {order.orderStatus}</p>
                  </Card.Body>
              </Card>
          ))
          :
          <>
          <p>Make an Order</p>
          <Container>
          <Row>
            <form className='col-10 offset-1 col-md-8 offset-md-2 col-lg-6 offset-lg-3 mb-5' onSubmit={handleSubmit}>
              {/* Order Type */}
              <label htmlFor="orderType">OrderType</label>
              <input type="text" name="orderType" className='input' placeholder='OrderType' value={formData.orderType} onChange={handleChange} />
              {errors.orderType && <p className='text-danger'>{errors.orderType}</p>}
              {/* Order Amount */}
              <label htmlFor="amount">Amount</label>
              <input type="amount" name="amount" className='input' placeholder='Amount' value={formData.amount} onChange={handleChange} />
              {errors.amount && <p className='text-danger'>{errors.amount}</p>}
              {/* Order Price */}
              <label htmlFor="price">Price</label>
              <input type="price" name="price" className='input' placeholder='Price' value={formData.price} onChange={handleChange} />
              {errors.price && <p className='text-danger'>{errors.price}</p>}
              {/* Submit */}
              <button type="submit" className="btn w-100">Register</button>
            </form>
          </Row>
        </Container>
        </>
        }
        <Row>
        </Row>
      </Container>
    </section>
    </>
  )
}
