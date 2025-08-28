import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function SubscriptionDetails() {
  const [details, setDetails] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    
    api.get(`/api/usersubscriptionstatus/active`)
      .then((res) => {
        setDetails(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading Subscription details...</h3>;
  if (!details) return <h3>No Subscription details found.</h3>;

  return (
    <div className="details-box">
      <h2 className="details-title">
        Subscription Details
      </h2>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Id:</span>
          <span className="value">{details.id}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Email:</span>
          <span className="value">{details.email}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">PlanId:</span>
          <span className="value">{details.planId}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">PlanName:</span>
          <span className="value">{details.planName}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Subscription Start:</span>
          <span className="value">{new Date(details.subscriptionStart).toLocaleString()}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Subscription End:</span>
          <span className="value">{new Date(details.subscriptionEnd).toLocaleString()}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">MaxApplications:</span>
          <span className="value">{details.maxApplications}</span>
        </div>
      </div>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">UsedApplications:</span>
          <span className="value">{details.usedApplications}</span>
        </div>
      </div>
       
    </div>
  );
}