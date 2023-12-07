import React, { useEffect, useState } from 'react';
import  { registerUser } from '../services/backend'
import '../styles/Login.css'

export const Signup = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [location, setLocation] = useState('');

    const handleLogin = (e) => {
      e.preventDefault();
      if (password !== null && email !== null && location !== null) {
        registerUser(email, password, location);
        setEmail('')
        setPassword('')
        setLocation('')
      }
    };

    return (
      <div className="login-container">
        <h2>Create Account</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="Enter your email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Enter your password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <label htmlFor="location">Location:</label>
          <input
            type="location"
            id="location"
            name="location"
            placeholder="Enter your location"
            required
            value={location}
            onChange={(e) => setLocation(e.target.value)}
          />

          <button type="submit">Create Account</button>
        </form>
      </div>
    );
}