import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

const AnalyticsSummary = () => {
  const [summary, setSummary] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSummary = async () => {
      try {
        const response = await api.get("/api/analystics/summary");
        setSummary(response.data);
      } catch (err) {
        setError("Failed to fetch summary");
      } finally {
        setLoading(false);
      }
    };

    fetchSummary();
  }, []);

  if (loading) return <div>Loading analytics...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="analytics-summary p-4 bg-white rounded shadow">
      <h2 className="text-xl font-bold mb-4">Analytics Summary</h2>
      <ul>
        <li>Total Students: {summary.totalStudents}</li>
        <li>Total Recuiters: {summary.totalRecuiters}</li>
        <li>Total totalJobs: {summary.totalJobs}</li>
        <li>Total totalInternships: {summary.totalInternships}</li>
        <li>Total totalJobApplications: {summary.totalJobApplications}</li>
        <li>Total totalInternshipApplications: {summary.totalInternshipApplications}</li>
      </ul>
    </div>
  );
};

export default AnalyticsSummary;