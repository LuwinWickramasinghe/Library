import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

export const AuthProfile: React.FC = () => {
  const { user, isAuthenticated, isLoading } = useAuth0();

  if (isLoading) {
    return <div>Loading ...</div>;
  }

  if(!isAuthenticated){
    console.log("unsuccess login")
  }

  if (!isAuthenticated || !user) {
    return null;  // Explicitly return null when not authenticated
  }

  return (
    <div>
      <img src={user.picture} alt={user.name} />
      <h2>{user.name}</h2>
      <p>{user.email}</p>
    </div>
  );
};

export default AuthProfile;
