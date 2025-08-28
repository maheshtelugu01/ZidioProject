import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

const StudentJobApplications = () => {
  const [applications, setApplications] = useState([]);
  const [loading,setLoading]=useState(true);

  useEffect(() => {
    fetchApplications();
    }, []); 
    
    const fetchApplications = async () => {
      try{
      const resp = await api.get(`/api/application/job/currentstudent`);
      setApplications(resp.data);
      setLoading(false)
      }catch(err){
        console.log(err)
        setLoading(false)
      }
    };
    
    const handleClick=async(id)=>{
      try{
      const res=await api.put(`/api/application/job/${id}/deactivate`);
      alert("your application deactivated..");
      fetchApplications()
    }catch(err){
      console.log(err)
      alert("error during deactivated");
    }
    }
  if(loading) return <h3>Loading Applications...</h3>;

  return (
       <>
      {applications.length === 0 ? (
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
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {applications.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.studentEmail}</td>
                <td>{s.jobId}</td>
                <td>{s.status}</td>
                <td><button className="btn btn-danger" onClick={()=>handleClick(s.id)}>❌Deactivate</button></td>
                </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      )}
    </>)
};

export default StudentJobApplications;