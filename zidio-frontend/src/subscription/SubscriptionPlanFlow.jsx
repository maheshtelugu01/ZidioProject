import  { useState, useEffect } from "react";
import api from "../user/axiosConfig";
import PaymentForm from "../payment/PaymentForm";

const SubscriptionFlow = ({ userId }) => {
  const [plans, setPlans] = useState([]);
  const [selectedPlan, setSelectedPlan] = useState(null);
  const [loading,setLoading]=useState(true);
   
  // Fetch all subscription plans
  useEffect(() => {
    const fetchPlans = async () => {
      try {
        const response = await api.get("/api/usersubscriptionstatus/subscription/findAll");
        setPlans(response.data);
        setLoading(false);
      } catch (error) {
        console.error(error);
        setLoading(false);
      }
    };
    fetchPlans();
  }, []);

  if(loading) return <h2>Loading PleaseWait..</h2>;
 plans.length==0 && <h2 className="text-center">No Plans Avalible</h2>

  return (
    <>
    {!selectedPlan ?(
      <div className="table-card">
        <div className="table-header">
          <h2>Available Plans List</h2>
          </div>
          <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>durationDays</th>
              <th>maxApplications</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {plans.map((s, idx) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.name}</td>
                <td>{s.description}</td>
                <td>{s.price}</td>
                <td>{s.durationDays}</td>
                <td>{s.maxApplications}</td>
                <td>{s.status}</td>
                <td><button onClick={()=>setSelectedPlan(s)}>Subscribe</button></td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
      ):(
        <PaymentForm plan={selectedPlan}/>
      )}
    </>
  );
};

export default SubscriptionFlow;