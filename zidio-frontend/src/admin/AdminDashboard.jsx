import AdminUserList from "./AdminUserList";
import AdminJobApplications from "./AdminJobApplications";
import AdminInternshipApplications from "./AdminInternshipApplications";
import StudentList from "../student/StudentsList";
import RecuiterList from "../recuiter/RecuiterList";
import InternshipList from "../student/InternshipList";
import CreateSubscriptionPlan from "../subscription/SubscriptionPlanCreation";
import LogoutButton from "../user/Logout";
import JobList from "../student/JobsList";
import { Routes,Route,Link } from "react-router-dom";
import AnalyticsSummary from "../analystics/Analystics";
function Header(){
  return(
    <header className="topbar">
      <h1 className="logo">Admin Dashboard</h1>
      <nav className="topnav">
    <ul>
      <li><Link className="nav-link"  to="/admin/user/findAll">All Users</Link></li>
      <li><Link className="nav-link" to="/admin/student/findAll">All Students</Link></li>
      <li><Link className="nav-link" to="/admin/recuiter/findAll">All Recuiters</Link></li>
      <li><Link className="nav-link" to="/admin/job/findAll">All Jobs</Link></li>
      <li><Link className="nav-link" to="/admin/job/applications">Jobs Applications</Link></li>
      <li><Link className="nav-link" to="/admin/internship/findAll">All Internship</Link></li>
      <li><Link className="nav-link" to="/admin/internship/applications">Internship Applications</Link></li>
      <li><Link className="nav-link" to="/admin/analystics">Analystics Summary</Link></li>
      <li><Link className="nav-link" to="/admin/subscription/post">Create Subscription</Link></li>
      <li><Link className="nav-link" to="/admin/logout">Logout</Link></li>
    </ul>
    </nav>
    </header>
  )
}

export default function AdminDashboard() {
  return (
    <div>
    <Header/>
    <div className="content">
      <Routes>
        
        <Route path="user/findAll" element={<AdminUserList />} />
        <Route path="student/findAll" element={<StudentList />} />
        <Route path="recuiter/findAll" element={<RecuiterList />} />
        <Route path="job/findAll" element={<JobList />} />
        <Route path="internship/findAll" element={<InternshipList />} />
        <Route path="job/applications" element={<AdminJobApplications />} />
        <Route path="internship/applications" element={<AdminInternshipApplications />} />
        <Route path="subscription/post" element={<CreateSubscriptionPlan />} />
        <Route path="analystics" element={<AnalyticsSummary />} />
        <Route path="logout" element={<LogoutButton />} />
      </Routes>
    </div>
    </div>
     
  );
}