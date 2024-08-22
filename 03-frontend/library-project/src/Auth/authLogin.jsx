import { useAuth0 } from "@auth0/auth0-react";
import React, { useEffect } from "react";


const LoginButton = () => {
  const { loginWithRedirect } = useAuth0();

  const { getAccessTokenSilently } = useAuth0();

useEffect(() => {
  const getToken = async () => {
    try {
      const token = await getAccessTokenSilently();
      console.log("Token:", token);
    } catch (error) {
      console.error("Error getting token:", error);
    }
  };

  getToken();
}, [getAccessTokenSilently]);


  return <button onClick={() => loginWithRedirect()}>Log In</button>;
};

export default LoginButton;