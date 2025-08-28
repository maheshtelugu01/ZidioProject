import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function JobList() {
  const [opportunities, setOpportunities] = useState([]);
  const [loading, setLoading] = useState(true);


  useEffect(()=>{
    fetchJobs();
  },[])
  const fetchJobs=async()=>{
    try{
    const res=await api.get(`/api/job/yourJobs`)
    setOpportunities(res.data);
      setLoading(false);
    
    }catch(err){
      console.log(err);
      setLoading(false);
    }
  }
  const handleClick=async(id)=>{
    try{
      await api.put(`/api/job/${id}/deactivate`)
      alert("Job Deactivated")
      fetchJobs()
    }catch(err){
      console.log(err)
      alert("error during deactivated..");
    }
  }

  if (loading) return <h3>Loading Jobs...</h3>;

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
              <th>description</th><th>companyName</th><th>location</th><th>jobType</th><th>duration</th><th>skills</th>
              <th>Action</th>
            </tr>
          </thead>
          {
            opportunities.map(
              (o)=><tr key={o.id}>
                <td>{o.id}</td><td>{o.title}</td>
                <td>{o.description}</td><td>{o.companyName}</td><td>{o.location}</td><td>{o.jobType}</td><td>{o.duration}</td>
                <td>{o.skills}</td><td><button className="btn-danger" onClick={()=>handleClick(o.id)}>❌Deactivate</button></td>
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