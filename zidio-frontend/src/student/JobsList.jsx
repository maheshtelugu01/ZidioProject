import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function JobList() {
  const [opportunities, setOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);
  

  useEffect(() => {
    loadJobs()
    }, []);
    const loadJobs=async()=>{
    await api.get("/api/application/job/findAll")
      .then((res) => {
        setOpportunities(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });}
  

   

  const handleApply=async(jobId)=>{
    try{
     const resp= await api.put(`/api/application/job/${jobId}/apply`)
      alert("job Applied Succesfully")
      loadJobs()
    }catch(err){
      alert("job applied already")
    }

  }
  if (loading) return <p>Loading Jobs...</p>;

  return (
    <>
      {opportunities.length === 0 ? (
        <h2 className="text-center">No Jobs found.</h2>
      ) : (
        <div className="table-card">
          <div className="table-header">
            <h2>Posted Jobs</h2>
            </div>
            <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>JobId</th><th>title</th>
              <th>description</th><th>companyName</th><th>location</th><th>jobType</th><th>duration</th><th>status</th><th>skills</th><th>APPLY</th>
            </tr>
          </thead>
          {
            opportunities.map(
              (o)=><tr key={o.id}>
                <td>{o.id}</td><td>{o.title}</td>
                <td>{o.description}</td><td>{o.companyName}</td><td>{o.location}</td><td>{o.jobType}</td><td>{o.duration}</td>
                <td>{o.skills}</td><td>{o.status}</td>
                <td>
                  <button disabled={o.status=="APPLIED"} onClick={()=>handleApply(o.id)}>{o.status==="APPLIED"?"APPLIED":"APPLY"}</button>
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