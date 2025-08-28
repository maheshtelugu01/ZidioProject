import React, { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function RecuiterList() {
  const [recuiters, setRecuiters] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/api/recuiter/findAll")
      .then((res) => {
        setRecuiters(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching recuiters", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading recuiters...</h3>;

  return (
  <>
      {recuiters.length === 0 ? (
        <h2 className="text-center">No Recuiters found.</h2>
      ) : (
      <div className="table-card">
        <div className="table-header">
          <h2>Recuiters List</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Recuiter Name</th>
              <th>Recuiter Email</th>
              <th>CompanyName</th>
              <th>Designation</th>
              <th>linkedInUrl</th>
            </tr>
          </thead>
          <tbody>
            {recuiters.map((s, idx) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.recuiterName}</td>
                <td>{s.recuiterEmail}</td>
                <td>{s.companyName}</td>
                <td>{s.designation}</td>
                <td>{s.linkedInUrl}</td>
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