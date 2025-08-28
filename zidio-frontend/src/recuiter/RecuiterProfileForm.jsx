import  { useState } from "react";
import api from "../user/axiosConfig";

export default function RecuiterProfileForm() {
  const [recuiter, setRecuiter] = useState({
    companyName: "",
    recuiterEmail: "",
    recuiterName: "",
    designation:"",
    linkedInUrl:""
  });

  const handleChange = (e) => {
    setRecuiter({ ...recuiter, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
   if (!recuiter.companyName || !recuiter.recuiterEmail ||!recuiter.recuiterName ||!recuiter.designation) {
      alert("Some fields are required.");
      return;
    }
    try {
      await api.post("/api/recuiter/save",recuiter,{
        headers:{
          "Content-Type":"application/json"
        }
      })
      alert("Recruiter profile saved successfully!");
      setRecuiter({
        companyName: "",
    recuiterEmail: "",
    recuiterName: "",
    designation:"",
    linkedInUrl:""
      })
      
    } catch (err) {
      alert("Error saving recruiter profile.");
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h2 className="text-center">Recuiter Profile</h2>
      </div>
      <div className="card-body">
        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>FullName:</label>
            <input type="text" value={recuiter.recuiterName} name="recuiterName" placeholder="Enter Full Name" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input type="email" value={recuiter.recuiterEmail} name="recuiterEmail" placeholder="Enter Email" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>CompanyName:</label>
            <input type="text" value={recuiter.companyName} name="companyName" placeholder="Enter CompanyName" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Designation:</label>
            <input type="text" value={recuiter.designation} name="designation" placeholder="Enter University" onChange={handleChange} />
          </div>
           <div className="form-group">
            <label>linkedInUrl:</label>
            <input type="text" value={recuiter.linkedInUrl} name="linkedInUrl" placeholder="Enter linkedInUrl" onChange={handleChange} />
          </div>
           
          <div className="card-footer">
            <button type="submit">Save Profile</button>
          </div>
        </form>
      </div>
    </div>
  );
}