import { useEffect, useState } from "react";
import api from "../user/axiosConfig"; // your configured axios

export default function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [loading,setLoading]=useState(true)

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const res = await api.get("/api/notification/findAll"); // use your base URL + token
        setNotifications(res.data);
        setLoading(false);
      } catch (err) {
        console.error("Error fetching notifications:", err);
        setLoading(false);
      }
    };

    fetchNotifications();
  }, []);

   
  if(loading) return <h3>Loading Notifications...</h3>;
  return  (
   <>
      {notifications.length === 0 ? (
        <h2 className="text-center">No Notifications found.</h2>
      ) : (
      <div className="table-card">
        <div className="table-header">
          <h2>Notfications </h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Email</th>
              <th>Message</th>
              <th>Source</th>
              <th>TimeStamp</th>
            </tr>
          </thead>
          <tbody>
            {notifications.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.email}</td>
                <td>{s.message}</td>
                <td>{s.source}</td>
                <td>{new Date(s.createdAt).toLocaleString()}</td>
                </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      )}
    </>)
}