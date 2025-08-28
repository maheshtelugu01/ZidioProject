import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function AdminUserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Users()
      }, []);
    const Users=()=>{api.get("/api/admin/users")
      .then((res) => {
        setUsers(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching users", err);
        setLoading(false);
      });
    }


  const handleAction = (action, email) => {
    api.put(`/api/admin/${action}`, { email })
      .then((res) => {
        alert(res.data);
        Users()
      })
      .catch((err) => alert("Error updating user status"));
  };

  if (loading) return <p>Loading users...</p>;

  return (
    <>
      {users.length === 0 ? (
        <h2 className="text-center">No Users found.</h2>
      ) : (
      <div className="table-card">
        <div className="table-header">
          <h2>Users List</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Email</th>
              <th>Role</th>
              <th>Status</th>
              <th>Actions</th>
              </tr>
          </thead>
          <tbody>
            {users.map((s, idx) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.email}</td>
                <td>{s.role}</td>
                <td>{s.active ? "✅ Active" : "❌ Inactive"}</td>
              <td>
                <button onClick={() => handleAction("activate", s.email)}>Activate</button>
                <button onClick={() => handleAction("deactivate", s.email)}>Deactivate</button>
              </td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      )}
    </>
  );
}