 import  { useState } from "react";
import api from "../user/axiosConfig";

const CreateSubscriptionPlan = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [durationDays, setDurationDays] = useState(""); // in days
  const [maxApplications, setMaxApplications] = useState(""); // in days
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!name || !price || !description || !durationDays|| !maxApplications) {
      alert("All fields are required.");
      return;
    }

    try {
      await api.post("/api/subscription/save", {
        name,
        price: parseFloat(price),
        description,
        durationDays: parseInt(durationDays, 10),
        maxApplications
      });
      alert("Plan created successfully!");
      setName("");
      setPrice("");
      setDescription("");
      setDurationDays("");
      setMaxApplications("")
    } catch (error) {
      console.error(error);
      alert("Failed to create plan.")
    }
  };

  return (
    <div className="card">
      <div className="card-header">
      <h2 className="text-center">Create Subscription Plan</h2>
    
      </div>
      <div className="card-body">
      <form onSubmit={handleSubmit} className="form">
        <div className="form-group">
          <label>Plan Name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Price ($):</label>
          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Description:</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          ></textarea>
        </div>

        <div className="form-group">
          <label>DurationDays:</label>
          <input
            type="number"
            value={durationDays}
            onChange={(e) => setDurationDays(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>Max Applications:</label>
          <input
            type="number"
            value={maxApplications}
            onChange={(e) => setMaxApplications(e.target.value)}
          />
        </div>
      <div className="card-footer">
        <button type="submit">Create Plan</button>
        </div>
      </form>
    </div>
    </div>
  );
};

export default CreateSubscriptionPlan;