import  { useEffect, useState } from "react";
import api from "../user/axiosConfig";

export default function PaymentList() {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/api/payment/findAll")
      .then((res) => {
        setPayments(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, []);

  if (loading) return <h3>Loading Payment Details...</h3>;

  return (
     <>
      {payments.length === 0 ? (
        <h2 className="text-center">No Payments found.</h2>
      ) : (
        <div className="table-card">
          <div className="table-header">
            <h2>Payments List</h2>
            </div>
            <div className="table-body">
        <table className="custom-table">
          <thead>
            <tr>
              <th>PaymentId</th>
              <th>email</th><th>planId</th><th>TransactionId</th><th>Amount</th><th>currency</th><th>PaymentStatus</th>
              <th>PaymentType</th><th>PaymentDate</th>
            </tr>
          </thead>
          {
            payments.map(
              (o)=><tr key={o.id}>
                <td>{o.id}</td><td>{o.email}</td>
                <td>{o.planId}</td><td>{o.transactionId}</td><td>{o.amount}</td><td>{o.currency}</td><td>{o.paymentStatus}</td>
                <td>{o.paymentType}</td><td>{new Date(o.paymentDate).toLocaleString()}</td>
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