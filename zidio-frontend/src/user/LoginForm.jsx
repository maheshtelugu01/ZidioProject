import  { useContext, useState, useEffect } from "react";
import { AuthContext } from "./AuthProvider";
import { Link } from "react-router-dom";

const LoginPage = () => {
  const { login } = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    localStorage.clear();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await login(email, password);
    } catch {
      setError("Login failed. Invalid credentials.");
    }
  };

  return (
    <div className="card">
      <div className="card-header">
      <h2>Login</h2>
      </div>
      <div className="card-body">
      <form onSubmit={handleSubmit} className="form">
        <div className="form-group">
        <label>Email:</label>
        <input value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="form-group">
        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div className="card-footer">
        <button type="submit">Login</button>
        </div>
      </form>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <p>New user? <Link to="/register">Register here</Link></p>
    </div>
    </div>
  );
};

export default LoginPage;