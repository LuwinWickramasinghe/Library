import { useOktaAuth } from "@okta/okta-react";
import { Link } from "react-router-dom";

export const LibraryServices = () => {

    const { authState } = useOktaAuth();

    return(
        <div className="container my-5">
            <div className="row p-4 align-items-center border shadows-lg">
                <div className="col-lg-7 p-3">
                    <h1 className="display-4 fw-bold">
                        Can't find what are you lokking for?
                    </h1>
                    <p className="lead">
                        If you cannot find what are you looking for, leave a messege for out library admin!
                    </p>
                    <div className="d-grid gap-2 justify-content-md-start mb-4 mg-lg-3">
                        {authState?.isAuthenticated?
                        <Link type="button" className="btn main-color btn-lg px-4 me-md-2 fw-bold text-white" to='/messages'>
                            Library Services
                            </Link>
                        :
                        <Link to={'/login'} className="btn main-color btn-lg text-white">
                            Sign up
                        </Link>
                    }
                        
                    </div>
                    </div>
                    <div className="col-lg-4 offset-lg-1 shadow-lg lost-image"></div>
            </div>

        </div>
    );
}