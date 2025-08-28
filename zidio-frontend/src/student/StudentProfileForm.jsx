import  { useState } from "react";
import api from "../user/axiosConfig";

export default function StudentProfileForm() {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: "",
    university: "",
    resumeUrl: "",
    skills: "",
  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!student.name || !student.email ||!student.resumeUrl){
      alert("please fill some details")
      return;
    }
    try {
      await api.post("/api/student/save", student);
      alert("Student profile saved successfully!");
      setStudent({
        name:"",
        email:"",
        course:"",
        university:"",
        resumeUrl:"",
        skills:""
      })
    } catch (err) {
      alert("Error saving profile.");
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h2 className="text-center">Student Profile</h2>
      </div>
      <div className="card-body">
        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>FullName:</label>
            <input type="text" name="name" 
            value={student.name} placeholder="Enter Full Name" required onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input type="email" name="email" value={student.email} placeholder="Enter Email" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Course:</label>
            <input type="text" name="course" 
            value={student.course} placeholder="Enter Course" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>University:</label>
            <input type="text" name="university" 
            value={student.university} placeholder="Enter University" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Resume Url:</label>
            <input type="text" name="resumeUrl" 
            value={student.resumeUrl} placeholder="Enter ResumeUrl" onChange={handleChange} />
          </div>
          <div className="form-group">
            <label>Skills:</label>
            <input type="text" name="skills" value={student.skills} placeholder="Enter Skills(comma separated)" onChange={handleChange} />
          </div>
          <div className="card-footer">
            <button type="submit">Save Profile</button>
          </div>
        </form>
      </div>
    </div>
  );
}