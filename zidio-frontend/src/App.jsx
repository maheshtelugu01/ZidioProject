 import { Routes, Route } from "react-router-dom";
import LoginPage from "./user/LoginForm";
import RegisterPage from "./user/RegisterForm";
import StudentDashboard from "./student/StudentDashboard";
import RecruiterDashboard from "./recuiter/RecuiterDashboard";
import NotFound from "./user/NotFound";
import ProtectedRoute from "./user/ProtectedRoute";
import AdminDashboard from "./admin/AdminDashboard";

function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Protect student dashboard */}
      <Route
        path="/student/*"
        element={
          <ProtectedRoute allowedRoles={["STUDENT"]}>
            <StudentDashboard />
          </ProtectedRoute>
        }
      />

      {/* Protect recruiter dashboard */}
      <Route
        path="/recuiter/*"
        element={
          <ProtectedRoute allowedRoles={["RECUITER"]}>
            <RecruiterDashboard />
          </ProtectedRoute>
        }
      />
      <Route
        path="/admin/*"
        element={
          <ProtectedRoute allowedRoles={["ADMIN"]}>
            <AdminDashboard />
          </ProtectedRoute>
        }
      />

      {/* fallback */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;