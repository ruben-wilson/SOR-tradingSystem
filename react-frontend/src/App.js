import logo from './logo.svg'
import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './components/Home'
import PageNavBar from './components/PageNavBar'
import PageNotFound from './components/PageNotFound'
import Login from './components/auth/Login'
import Register from './components/auth/Register'
import Orders from './components/Orders'


function App() {
  return (
    <main className='site-wrapper'>
      <BrowserRouter>
        <PageNavBar />
        <Routes>
        <Route path='/' element={<Home /> } />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="*" element={<PageNotFound />} />
        </Routes>
      </BrowserRouter>
    </main>
  )
}

export default App
