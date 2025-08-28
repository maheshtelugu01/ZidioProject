 import  { useContext, useState } from "react";
import { AuthContext } from "./AuthProvider";
import { Link } from "react-router-dom";

const RegisterPage = () => {
  const { register } = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("STUDENT");

  const handleSubmit = async (e) => {
    e.preventDefault();
    await register( email, password, role);
  };

  return (
    <div className="card">
      <div className="card-header">
      <h2>Register</h2>
      </div>
      <div className="card-body">
      <form  className="form" onSubmit={handleSubmit}>
         <div className="form-group">
        <label>Email:</label>
        <input value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="form-group">
        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div className="form-group">
        <label>Role:</label>
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="STUDENT">STUDENT</option>
          <option value="RECUITER">RECUITER</option>
          <option value="ADMIN">ADMIN</option>
        </select>
        </div>
        <div className="card-footer">
        <button type="submit">Register</button>
        </div>
      </form>
      <p>Already have an account? <Link to="/login">Login here</Link></p>
    </div>
    </div>
  );
};

export default RegisterPage;