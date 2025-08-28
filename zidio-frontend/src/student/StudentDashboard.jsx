import StudentDetails from "./StudentDetails";
import StudentProfileForm from "./StudentProfileForm";
import StudentList from "./StudentsList";
import SubscriptionFlow from "../subscription/SubscriptionPlanFlow";
import AnalyticsSummary from "../analystics/Analystics";
import LogoutButton from "../user/Logout";
import JobList from "../student/JobsList";
import InternshipList from "../student/InternshipList";
import { Routes,Route,Link} from "react-router-dom";
import FileUpload from "./ResumeUpload";
import StudentJobApplications from "./JobApplications";
import StudentInternshipApplications from "./InternshipApplications";
import Notifications from "../notification/Notifications";
import SubscriptionDetails from "../subscription/SubscriptionDetails";
import PaymentList from "../payment/PaymentDetails";

function Header(){
  return(
    <header className="topbar">
      <h1 className="logo">Student Dashboard</h1>
    <nav className="topnav">
    <ul>
      <li><Link className="nav-link" to="/student/">Student Details</Link></li>
      <li><Link className="nav-link" to="/student/profile">Student Profile</Link></li>
      <li><Link className="nav-link" to="/student/findAll">All Students</Link></li>
      <li><Link className="nav-link" to="/student/job/findAll">All Jobs</Link></li>
      <li><Link className="nav-link" to="/student/internship/findAll">All Internship</Link></li>
      <li><Link className="nav-link" to="/student/job/application/findAll">Job Applications</Link></li>
      <li><Link className="nav-link" to="/student/internship/application/findAll">Internship Applications</Link></li>
      <li><Link className="nav-link" to="/student/analystics">Analystics Summary</Link></li>
      <li><Link className="nav-link" to="/student/upload">Upload Resume</Link></li>
      <li><Link className="nav-link" to="/student/notification">Notifications</Link></li>
      <li><Link className="nav-link" to="/student/subscription/findAll">Subscription Plans</Link></li>
      <li><Link className="nav-link" to="/student/subscription/details">Subscription Details</Link></li>
      <li><Link className="nav-link" to="/student/payment/findAll">Payment List</Link></li>
      <li><Link className="nav-link" to="/student/logout">Logout</Link></li>
    </ul>
    </nav>
    </header>
  )
}

export default function StudentDashboard() {
  return (
    <div>
    <Header/>
    <div className="content">
      <Routes>
        <Route path="/" element={<StudentDetails />} />
        <Route path="profile" element={<StudentProfileForm />} />
        <Route path="findAll" element={<StudentList />} />
        <Route path="job/findAll" element={<JobList />} />
        <Route path="internship/findAll" element={<InternshipList />} />
        <Route path="job/application/findAll" element={<StudentJobApplications />} />
        <Route path="internship/application/findAll" element={<StudentInternshipApplications />} />
        <Route path="analystics" element={<AnalyticsSummary />} />
        <Route path="upload" element={<FileUpload />} />
        <Route path="notification" element={<Notifications />} />
        <Route path="subscription/findAll" element={<SubscriptionFlow />} />
        <Route path="subscription/details" element={<SubscriptionDetails />} />
        <Route path="payment/findAll" element={<PaymentList />} />
        <Route path="logout" element={<LogoutButton />} />

      </Routes>
      </div>
    </div>
  );
}