import { useState } from "react";
import api from "../user/axiosConfig";

export default function InternshipForm() {
  const [Internship, setInternship] = useState({
    title: "",
    description: "",
    companyName: "",
    location: "",
    startDate: "",
    durationWeeks: "",
    stipend: ""
  });

  const handleChange = (e) => {
    setInternship({ ...Internship, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/api/internship/post", Internship);
      alert("Internship posted successfully!");
      setInternship({
        title: "",
        description: "",
        companyName: "",
        location: "",
        startDate: "",
        durationWeeks: "",
        stipend: ""
      })
    } catch (err) {
      alert("Error posting Internship.");
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h2 className="text-center">Internship Form</h2>
      </div>
      <div className="card-body">
        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>Title:</label>
            <input type="text" name="title" required 
            value={Internship.title} placeholder="Enter Title" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Description:</label>
            <input type="text" name="description"  
            value={Internship.description} placeholder="Enter Description" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>CompanyName:</label>
            <input type="text" name="companyName" required 
             value={Internship.companyName} placeholder="Enter CompanyName" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Location:</label>
            <input type="text" name="location" required 
            value={Internship.location} placeholder="Enter Location" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>StartDate:</label>
            <input type="date" name="startDate" required 
            value={Internship.startDate} placeholder="Enter StartDate" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Duration Weeks:</label>
            <input type="number" name="durationWeeks" 
            value={Internship.durationWeeks} placeholder="Enter Duration" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Stipend:</label>
            <input type="number" name="stipend" required
             value={Internship.stipend} placeholder="Enter Stipend" onChange={handleChange} />
          </div>
          <div className="card-footer">
            <button type="submit">Post Internship</button>
          </div>
        </form>
      </div>
    </div>
  );
}