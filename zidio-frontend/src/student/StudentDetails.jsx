import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function StudentDetails() {
  const [student, setStudent] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const email = localStorage.getItem("email"); // save this at login
    if (!email) {
      setLoading(false);
      return;
    }

    api.get(`/api/student/find/${email}`)
      .then((res) => {
        setStudent(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading student details...</h3>;

  if (!student) return <h3>No student details found.</h3>;

  return (
    <div className="details-box">
      <h2 className="details-title">
        StudentDetails
      </h2>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Id:</span>
          <span className="value">{student.id}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Name:</span>
          <span className="value">{student.name}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Email:</span>
          <span className="value">{student.email}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Course:</span>
          <span className="value">{student.course}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">University:</span>
          <span className="value">{student.university}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">ResumeUrl:</span>
          <span className="value"><a target="_blank">{student.resumeUrl}</a></span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Skills:</span>
          <span className="value">{student.skills}</span>
        </div>
      </div>
    </div>

  );
}