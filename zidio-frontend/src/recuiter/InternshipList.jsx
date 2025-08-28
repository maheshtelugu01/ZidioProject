import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function InternshipList() {
  const [opportunities, setOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchInternships()
      }, []);
    const fetchInternships=()=>{
    api.get("/api/internship/yourInternships")
      .then((res) => {
        setOpportunities(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
    }
    const handleClick=async(id)=>{
      try{
        await api.put(`/api/internship/${id}/deactivate`)
        alert("Internship Deactivated")
        fetchInternships()
      }catch(err){
        console.log(err)
        alert("error during deactivated")
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
              <th>JobId</th><th>title</th>
              <th>description</th><th>companyName</th><th>location</th><th>startDate</th><th>durationWeeks</th>
              <th>Stipend</th><th>Action</th>
            </tr>
          </thead>
          {
            opportunities.map(
              (o)=><tr key={o.id}>
                <td>{o.id}</td><td>{o.title}</td>
                <td>{o.description}</td><td>{o.companyName}</td><td>{o.location}</td><td>{o.startDate}</td><td>{o.durationWeeks}</td>
                <td>{o.stipend}</td><td><button onClick={()=>handleClick(o.id)} className="btn-danger">❌Deactivate</button></td>
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