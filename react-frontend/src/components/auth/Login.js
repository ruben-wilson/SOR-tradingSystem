import React from 'react'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import { Container, Row } from 'react-bootstrap'

export default function Login() {

    const navigate = useNavigate()

    const [ formData, setFormData ] = useState({
      username: '',
      password: '',
    })
  
    const [ errors, setErrors ] = useState(false)
  
    const setTokenToLocalStorage = (token) => {
      window.localStorage.setItem('trading-app-token', token)
    }
  
    const handleSubmit = async (e) => {
      e.preventDefault()
      try {
        const { data } = await axios.post('/api/login', formData)
        setTokenToLocalStorage(data.token)
        navigate('/cities')
      } catch (err) {
        console.log(err)
        setErrors(true)
      }
    }
  
    // ? Handle change
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value })
      setErrors(false)
    }
  
  
    return (
      <section className="form-page">
        <Container>
          <Row>
            <form className='col-10 offset-1 col-md-8 offset-md-2 col-lg-6 offset-lg-3 mb-5' onSubmit={handleSubmit}>
              <h1>Login</h1>
              <label htmlFor="email">Email</label>
              <input type="email" name="email" className='input' placeholder='Email' required value={formData.email} onChange={handleChange} />
              <label htmlFor="password">Password</label>
              <input type="password" name="password" className='input' placeholder='Password' required value={formData.password} onChange={handleChange} />
              {errors && <p className='text-danger text-center'>Unauthorised</p>}
              <button type="submit" className="btn w-100">Login</button>
            </form>
          </Row>
        </Container>
      </section>
    )
}
