import React, { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function InternshipList() {
  const [opportunities, setOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadInternships();
      }, []);
    const loadInternships=async()=>{
    api.get("/api/application/internship/findAll")
      .then((res) => {
        setOpportunities(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
    }

 
  const handleApply=async(jobId)=>{
      try{
       const resp= await api.put(`/api/application/internship/${jobId}/apply`)
       alert("internship applied successfully");
       loadInternships()
      }catch(err){
        alert("internship already applied")
      }
    }

  if (loading) return <p>Loading Internships...</p>;

  return (
    <>
      {opportunities.length === 0 ? (
        <h2 className="text-center">No Internships found.</h2>
      ) : (
        <div className="table-card">
          <div className="table-header">
            <h2>Posted Internships</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>InternshipId</th><th>title</th>
              <th>description</th><th>companyName</th><th>location</th><th>startDate</th><th>durationWeeks</th><th>stipend</th><th>status</th><th>Action</th>
            </tr>
          </thead>
          {
            opportunities.map(
              (o)=><tr key={o.id}>
                <td>{o.id}</td><td>{o.title}</td>
                <td>{o.description}</td><td>{o.companyName}</td><td>{o.location}</td><td>{o.startDate}</td><td>{o.durationWeeks}</td>
                <td>{o.stipend}</td><td>{o.status}</td>
                <td>
                  <button disabled={o.status==="APPLIED"} onClick={()=>handleApply(o.id)}>{o.status==="APPLIED"?"APPLIED":"APPLY"}</button>
                </td>
              </tr>
            )
          }
        </table>
        </div>
        </div>
      )}
    </>
  );
}