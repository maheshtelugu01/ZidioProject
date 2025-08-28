import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function AdminJobApplications() {
  const [apps, setApps] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/api/application/jobs")
      .then((res) => {
        setApps(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching job applications", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading job applications...</p>;

  return (
    <>
      {apps.length === 0 ? (
        <h2 className="text-center">No Applications found.</h2>
      ) : (
      <div className="table-card">
        <div className="table-header">
          <h2>Job Applications List</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Email</th>
              <th>OpportunityId</th>
              <th>Status</th>
              
            </tr>
          </thead>
          <tbody>
            {apps.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.studentEmail}</td>
                <td>{s.jobId}</td>
                <td>{s.status}</td>
                
                </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      )}
    </>)
  
}