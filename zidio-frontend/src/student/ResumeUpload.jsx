import  { useState } from "react";
import api from "../user/axiosConfig";

const FileUpload = () => {
  const [file, setFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [message, setMessage] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setMessage("");
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage("Please select a file first.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setUploading(true);
      const response = await api.post("/api/student/uploadresume", formData, {
        headers: { "Content-Type": "multipart/form-data" },
            });

      setMessage("File uploaded successfully!");
      console.log("Upload response:", response.data);
    } catch (error) {
      setMessage("File upload failed.");
      console.error(error);
    } finally {
      setUploading(false);
      setFile(null);
    }
  };

  return (
    <div className="p-6 max-w-md mx-auto border rounded-lg shadow">
      <h2 className="text-xl font-bold mb-4">Upload a File</h2>

      <input type="file" name="file" onChange={handleFileChange} className="mb-4" />
      {file && <p>Selected file: {file.name}</p>}

      <button
        onClick={handleUpload}
        disabled={uploading}
        className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded mt-2"
      >
        {uploading ? "Uploading..." : "Upload"}
      </button>

      {message && <p className="mt-2">{message}</p>}
    </div>
  );
};

export default FileUpload;