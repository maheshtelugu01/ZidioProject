import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

const StudentInternshipApplications = () => {
  const [applications, setApplications] = useState([]);
  const [loading,setLoading]=useState(true);

  useEffect(() => {
    fetchApplications();
  }, []);

  const fetchApplications = async () => {
    try{
    const resp = await api.get(`/api/application/internship/currentstudent`);
    setApplications(resp.data);
    setLoading(false)
    }catch(err){
      console.log(err)
      setLoading(false)
    }
  };

  const handleClick=async(id)=>{
    try{
      await api.put(`api/application/internship/${id}/deactivate`);
      alert("InternshipApplication Deactivated ..");
      fetchApplications();
    }catch(err){
      console.log(err);
      alert("error during deactivate")
    }
  }

  if(loading) return <h3>Loading Applications</h3>;
  return (
    <>
      {applications.length === 0 ? (
        <h2 className="text-center">No Applications found.</h2>
      ) : (
        <div className="table-card">
          <div className="table-header">
            <h2>Internship Applications List</h2>
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
                    <td>{s.internshipId}</td>
                    <td>{s.status}</td>
                    <td><button className="btn-danger" onClick={() => handleClick(s.id)}>❌Deactivate</button></td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>)
};

export default StudentInternshipApplications;