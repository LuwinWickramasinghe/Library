import { useAuth0 } from "@auth0/auth0-react";
import React from "react";

const Profile = () => {
  const { user, isAuthenticated, isLoading } = useAuth0();
  console.log(user, isAuthenticated, isLoading )
  if (isLoading) {
    return <div>Loading ...</div>;
  }

  if (!isAuthenticated) {
    
    console.log("login not success")
    return null; // Explicitly return null if the user is not authenticated
  }

  return (
    isAuthenticated && (
      <div>
        <img src={user.picture} alt={user.name} />
        <h2>{user.name}</h2>
        <p>{user.email}</p>
      </div>
    )
  );
};

export default Profile;