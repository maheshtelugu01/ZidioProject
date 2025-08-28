import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function RecuiterDetails() {
  const [recuiter, setRecuiter] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const email = localStorage.getItem("email"); // saved at login
    if (!email) {
      setLoading(false);
      return;
    }

    api.get(`/api/recuiter/find/${email}`)
      .then((res) => {
        setRecuiter(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading recruiter details...</h3>;
  if (!recuiter) return <h3>No recruiter details found.</h3>;

  return (
    <div className="details-box">
      <h2 className="details-title">
        Recuiter Details
      </h2>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Id:</span>
          <span className="value">{recuiter.id}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Name:</span>
          <span className="value">{recuiter.recuiterName}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Email:</span>
          <span className="value">{recuiter.recuiterEmail}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">CompanyName:</span>
          <span className="value">{recuiter.companyName}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Designation:</span>
          <span className="value">{recuiter.designation}</span>
        </div>
      </div>
       <div className="details-grid">
        <div className="details-item">
          <span className="label">linkedInUrl:</span>
          <span className="value">{recuiter.linkedInUrl}</span>
        </div>
      </div>
       
    </div>
  );
}