import  { useState } from "react";
import api from "../user/axiosConfig";

export default function PaymentForm({ plan }) {
  const [paymentType, setPaymentType] = useState("CREDITCARD");
  const [currency, setCurrency] = useState("USD");
  const [loading, setLoading] = useState(false);
  const [payment, setPayment] = useState(null);

  const handlePayment = async () => {
    setLoading(true);

    const paymentRequest = {
      planId: plan.id,
      amount: plan.price,
      currency: currency,
      paymentType: paymentType,
    };

    try {
      const resp = await api.post("api/payment/pay", paymentRequest);
      setPayment(resp.data)
      
    } catch (err) {
      console.error("Payment failed", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div className="card">
        <div className="card-header">
          <h2>Payment Form</h2>
          
        </div>
        <div className="card-body">
          <form className="form" >
            <div className="form-group">
              <label className="block mb-2">
                Plan Id:
                <input
                  value={plan.id}
                  className="ml-2 border p-1 rounded" readOnly/>  
              </label>       </div>
              <div className="form-group">
              <label className="block mb-2">
                Payment Amount:
                <input
                  value={plan.price}
                  className="ml-2 border p-1 rounded" readOnly/>  
              </label>       </div>
            <div className="form-group">
              <label className="block mb-2">
                Payment Type:
                <select
                  value={paymentType}
                  onChange={(e) => setPaymentType(e.target.value)}
                  className="ml-2 border p-1 rounded">
                  <option value="CREDITCARD">Credit Card</option>
                  <option value="DEBITCARD">Debit Card</option>
                  <option value="UPI">UPI</option>
                  <option value="NETBANKING">Net Banking</option>
                </select>
              </label>       </div>
            <div className="form-group">
              <label className="block mb-2">
                Currency:
                <select
                  value={currency}
                  onChange={(e) => setCurrency(e.target.value)}
                  className="ml-2 border p-1 rounded">
                  <option value="USD">USD</option>
                  <option value="INR">INR</option>
                  <option value="EUR">EUR</option>
                </select>
              </label>       
              </div>

            <div className="card-footer">
              <button
                onClick={handlePayment}
                disabled={loading}
                className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
              >
                {loading ? "Processing..." : "Pay & Subscribe"}
              </button>
            </div>
          </form>

        </div>
      </div>
      {payment && (
        <div className="details-box">
      <h2 className="details-title">
        Payment Result
      </h2>
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Payment:</span>
          <span className="value">{payment}</span>
        </div>
      </div>
      
      <div className="details-grid">
        <div className="details-item">
          <span className="label">Status:</span>
          <span className="value">{payment=="SUCCESS"?"ACTIVATED":"PENDING"}</span>
        </div>
      </div>           
        </div>
      )}
    </>
  );
}