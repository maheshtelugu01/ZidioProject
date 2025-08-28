import  { useState } from "react";
import api from "../user/axiosConfig";

export default function JobForm() {
  const initialState={
    title: "",
    description: "",
    companyName:"",
    location:"",
    jobType:"",
    duration:"",
    skills:""
  };
  const [Job, setJob] = useState(initialState)
  

  const handleChange = (e) => {
    setJob({ ...Job, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
 e.preventDefault();
    try {
      await api.post("/api/job/post", Job);
      alert("Job posted successfully!");
      setJob(initialState)
    } catch (err) {
      alert("Error posting Job.");
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h2 >Job Form</h2>
      </div>
      <div className="card-body">
        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>Title:</label>
            <input type="text" value={Job.title} name="title" required placeholder="Enter Title" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Description:</label>
            <input type="text" value={Job.description} name="description" placeholder="Enter Description" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>CompanyName:</label>
            <input type="text" value={Job.companyName} name="companyName" required placeholder="Enter CompanyName" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Location:</label>
            <input type="text" value={Job.location} name="location" required placeholder="Enter Location" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Job Type:</label>
            <input type="text" value={Job.jobType} name="jobType" required placeholder="Enter JobType" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Duration:</label>
            <input type="text" value={Job.duration} name="duration" required placeholder="Enter Duration" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Skills:</label>
            <input type="text" value={Job.skills} name="skills" required placeholder="Enter Skills(comma separated)" onChange={handleChange} />
          </div>
          <div className="card-footer">
            <button type="submit">Post Job</button>
          </div>
        </form>
      </div>
    </div>
  );
}