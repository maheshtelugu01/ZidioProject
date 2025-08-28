import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function StudentList() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  

  useEffect(() => {
    api.get("/api/student/findAll")
      .then((res) => {
        setStudents(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading students...</h3>;

  return (
      <>
      {students.length === 0 ? (
        <h2 className="text-center">No students found.</h2>
      ) : (
      <div className="table-card">
        <div className="table-header">
          <h2>Students List</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>University</th>
              <th>ResumeUrl</th>
              <th>Skills</th>
            </tr>
          </thead>
          <tbody>
            {students.map((s, idx) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.name}</td>
                <td>{s.email}</td>
                <td>{s.course}</td>
                <td>{s.university}</td>
                <td>{s.resumeUrl}</td>
                <td>{s.skills}</td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      )}
    </>
    
  );
}