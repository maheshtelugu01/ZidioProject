import  { createContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "./axiosConfig";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null); 
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");
    const email = localStorage.getItem("email");
    if (token && role && email) {
      setUser({ token, role, email });
    }
  }, []);

  // Login
  const login = async (email, password) => {
    try {
      const response = await api.post("/api/auth/login", { email, password });
      const { token, role } = response.data;

      localStorage.setItem("token", token);
      localStorage.setItem("role", role);
      localStorage.setItem("email", email);

      setUser({ token, role, email });

      if (role === "STUDENT") {
        navigate("/student");
      } else if (role === "RECUITER") {
        navigate("/recuiter");
      }
      else{
        navigate("/admin")
      }
    } catch (err) {
      throw new Error("Login failed");
    }
  };


  const register = async ( email, password, role) => {
    await api.post("/api/auth/register", {
       email, password, role
    });
    navigate("/login");
  };

  
  const logout = () => {
    localStorage.clear();
    setUser(null);
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};