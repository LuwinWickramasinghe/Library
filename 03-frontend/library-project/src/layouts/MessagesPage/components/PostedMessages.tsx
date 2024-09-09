import { useOktaAuth } from "@okta/okta-react"
import { useEffect, useState } from "react";
import MessageModel from "../../../models/MessageModel";
import { error } from "console";
import { SpinnerLoading } from "../../Utils/SpinnerLoading";

export const PostedMessages = () => {

    const { authState } = useOktaAuth();
    const[ isLoadingMessages, setIsLoadingMessages ] = useState(true);
    const [httpError, setHttpError] = useState(null);

    const[postedMessages, setPostedMessages] = useState<MessageModel[]>([]);

    const [messagesPerPage] = useState(5);
    const [ currentPage, setCurrentPage ] =  useState(1);
    const [totalPages, setTotalPages ] = useState(0);

    useEffect(() => {

        const fetchMessagesPostedByUser =  async () => {
            if(authState && authState.isAuthenticated){
                const url = `http://localhost:8080/api/messages/search/findByUserEmail?userEmail=${authState?.accessToken?.claims.sub}&page=${currentPage- 1}&size=${messagesPerPage}`
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                        'Content-Type': 'application/json'
                    },
    
                };
                const messagesPostedByUserResponse = await fetch(url, requestOptions);
                if(!messagesPostedByUserResponse.ok){
                    throw new Error("somthing went wrong when fetching messages");
                }
                const messagesPostedByUserResponseJson = await messagesPostedByUserResponse.json();
                setPostedMessages(messagesPostedByUserResponseJson._embedded.messages);
                setTotalPages(messagesPostedByUserResponseJson.page.totalPages);
            }
            setIsLoadingMessages(false);

        }
        fetchMessagesPostedByUser().catch((error:any) => {
            setIsLoadingMessages(false);
            setHttpError(error.message);
        })
        window.scrollTo(0,0);

    },[authState, currentPage])

    if(isLoadingMessages){
        return(<SpinnerLoading/>);
    }

    if(httpError){
        return(
            <div className="container m-5">
                <p>{httpError}</p>
            </div>
        );
    }

    const paginate = (pageNumber:number) => setCurrentPage(pageNumber);

}