import RecuiterDetails from "./RecuiterDetails";
import RecuiterProfileForm from "./RecuiterProfileForm";
import RecuiterList from "./RecuiterList";
import JobList from "./JobsList";
import JobForm from "./JobForm";
import InternshipForm from "./InternshipForm";
import InternshipList from "./InternshipList";
import SubscriptionFlow from "../subscription/SubscriptionPlanFlow";
import AnalyticsSummary from "../analystics/Analystics";
import LogoutButton from "../user/Logout";
import {Routes,Route,Link } from "react-router-dom";
import RecuiterInternshipApplications from "./InternshipApplications";
import RecuiterJobApplications from "./JobApplications";
import SubscriptionDetails from "../subscription/SubscriptionDetails";
import PaymentList from "../payment/PaymentDetails";
import Notifications from "../notification/Notifications";

function Header(){
  return(
    <header className="topbar">
    <h1 className="logo">Recuiter Dashboard</h1>
    <nav className="topnav">
    <ul>
      <li><Link className="nav-link" to="/recuiter/">Recuiter Details</Link></li>
      <li><Link className="nav-link" to="/recuiter/profile">Recuiter Profile</Link></li>
      <li><Link className="nav-link" to="/recuiter/job/post">Post Job</Link></li>
      <li><Link className="nav-link" to="/recuiter/internship/post">Post Internship</Link></li>
      <li><Link className="nav-link" to="/recuiter/findAll">All Recuiters</Link></li>
      <li><Link className="nav-link" to="/recuiter/job/findAll">All Jobs</Link></li>
      <li><Link className="nav-link" to="/recuiter/job/applications">Jobs Applications</Link></li>
      <li><Link className="nav-link" to="/recuiter/internship/findAll">All Internship</Link></li>
      <li><Link className="nav-link" to="/recuiter/internship/applications">Internship Applications</Link></li>
      <li><Link className="nav-link" to="/recuiter/notifications">Notifications</Link></li>
      <li><Link className="nav-link" to="/recuiter/analystics">Analystics Summary</Link></li>
      <li><Link className="nav-link" to="/recuiter/subscription/findAll">Subscription Plans</Link></li>
      <li><Link className="nav-link" to="/recuiter/subscription/details">Subscription Details</Link></li>
      <li><Link className="nav-link" to="/recuiter/payment/findAll">Payment List</Link></li>
      <li><Link className="nav-link" to="/recuiter/logout">Logout</Link></li>
    </ul>
    </nav>
    </header>
  )
}
export default function RecuiterDashboard() {
  return (
    <div>
    <Header/>
    <div className="content">
      <Routes>
        <Route path="/" element={<RecuiterDetails />} />
        <Route path="profile" element={<RecuiterProfileForm />} />
        <Route path="findAll" element={<RecuiterList />} />
        <Route path="job/findAll" element={<JobList />} />
        <Route path="job/post" element={<JobForm />} />
        <Route path="internship/findAll" element={<InternshipList />} />
        <Route path="internship/post" element={<InternshipForm />} />
        <Route path="job/applications" element={<RecuiterJobApplications />} />
        <Route path="internship/applications" element={<RecuiterInternshipApplications />} />
        <Route path="notifications" element={<Notifications />} />
        <Route path="subscription/findAll" element={<SubscriptionFlow />} />
        <Route path="subscription/details" element={<SubscriptionDetails />} />
        <Route path="payment/findAll" element={<PaymentList />} />
        <Route path="analystics" element={<AnalyticsSummary />} />
        <Route path="logout" element={<LogoutButton />} />
      </Routes>
    </div>
    </div>
  );
}