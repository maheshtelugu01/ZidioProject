import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

const RecuiterInternshipApplications = () => {
  const [applications, setApplications] = useState([]);
  const [selectedStudent, setSelectedStudent] = useState(null);
  const [loading,setLoading]=useState(true)

  useEffect(() => {
      fetchApplications();
    }, []);
    const fetchApplications = async () => {
      const resp = await api.get("/api/application/internships/Ids");
      setApplications(resp.data);
      setLoading(false);
    };
  

  const handleStudentClick = async (studentEmail) => {
    try {
      const resp = await api.get(`/api/student/find/${studentEmail}`);
      setSelectedStudent(resp.data);

    } catch (err) {
      console.error("Error fetching student info:", err);

    }
  };
const handleAction=async(action,id)=>{
  try{
    const res=await api.put(`/api/application/internship/${id}/${action}`)
    alert(res.data)
    fetchApplications()
    
  }catch(err){
    console.log(err)
    alert("something went wrong");
  }
}
  const handleDownloadResume = async () => {
    if (!selectedStudent?.resumeUrl) return;
    const fileResp = await api.get(`/api/file/download?url=${encodeURIComponent(selectedStudent.resumeUrl)}`, { responseType: "blob" });
    const url = window.URL.createObjectURL(new Blob([fileResp.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download",`${selectedStudent.name}-resume.pdf`);
    document.body.appendChild(link);
    link.click();
    link.remove();
  };
  if(loading) return <h3>Loading Applications</h3>;

  return (
     <>
    {applications.length==0?(
      <h2 className="text-center">No Applications Found</h2>
    ):(
    <div className="table-card">
      <div className="table-header">
      <h2 className="table-header">Internship Applications</h2>
      </div>
      <div className="table-body">
      <table className="custom-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Opportunity Id</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {applications.map(app => (
            <tr key={app.id}>
              <td>{app.id}</td>
              <td>{app.studentEmail}</td>
              <td>
                <button onClick={() => handleStudentClick(app.studentEmail)}>
                  {app.internshipId}
                </button>
              </td>
              <td>{app.status}</td>
              <td><button disabled={app.status==="ACCEPTED"} onClick={()=>handleAction("accept",app.id)}>Accept👍</button>
              <button disabled={app.status==="REJECTED"} onClick={()=>handleAction("reject",app.id)}>Reject❌</button></td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>

      {selectedStudent && (
        <div style={{ marginTop: "20px", padding: "10px", border: "1px solid #ccc" }}>
          <h3>Student Details</h3>
          <p><strong>Name:</strong> {selectedStudent.name}</p>
          <p><strong>Email:</strong> {selectedStudent.email}</p>
          <p><strong>Course:</strong> {selectedStudent.course}</p>
          <p><strong>University:</strong> {selectedStudent.university}</p>
          <p><strong>ResumeUrl:</strong> {selectedStudent.resumeUrl}</p>
          
          <button onClick={handleDownloadResume}>
            Download Resume
          </button>
        </div>
      )}
      </div>
      )}
  </>
  )

};

export default RecuiterInternshipApplications;