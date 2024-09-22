import { useOktaAuth } from "@okta/okta-react";
import { useEffect, useState } from "react";
import MessageModel from "../../../models/MessageModel";
import { SpinnerLoading } from "../../Utils/SpinnerLoading";
import { Pagination } from "../../Utils/Pagination";
import { AdminResponse } from "./AdminResponse";
import AdminMessageRequest from "../../../models/AdminMessageRequest";

export const AdminMessages = () => {
  const { authState } = useOktaAuth();

  const [isLoadingMessages, setIsLoadingMessages] = useState(true);
  const [httpError, setHttpError] = useState(null);

  const [adminMessages, setAdminMessages] = useState<MessageModel[]>([]);
  const [messagesPerPage] = useState(5);

  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalpages] = useState(0);

  const [buttonSubmit, setButtonSubmit] = useState(false);

  useEffect(() => {
    const fetchAdminMessages = async () => {
      if (authState && authState.isAuthenticated) {
        const url = `${
          process.env.REACT_APP_API
        }/messages/search/findByClosed?closed=false&page=${
          currentPage - 1
        }&size=${messagesPerPage}`;
        const requestOptions = {
          method: "GET",
          headers: {
            Authorization: `Bearer ${authState.accessToken?.accessToken}`,
            "Content-Type": "application/json",
          },
        };
        const adminMessagesResponse = await fetch(url, requestOptions);
        if (!adminMessagesResponse.ok) {
          throw new Error("Something went wrong when fetching messages");
        }

        const adminMessagesResponseJson = await adminMessagesResponse.json();

        setAdminMessages(adminMessagesResponseJson._embedded.messages);
        setTotalpages(adminMessagesResponseJson.page.totalPages);
      }
      setIsLoadingMessages(false);
    };
    fetchAdminMessages().catch((error: any) => {
      setIsLoadingMessages(false);
      setHttpError(error.message);
    });
    window.scroll(0, 0);
  }, [authState, currentPage, buttonSubmit]);

  if (isLoadingMessages) {
    return <SpinnerLoading />;
  }
  if (httpError) {
    return (
      <div className="container m-5">
        <p>{httpError}</p>
      </div>
    );
  }

  async function submitResponseToQuestion(id: number, response: string) {
    const url = `${process.env.REACT_APP_API}/messages/secure/admin/message`;
    if (
      authState &&
      authState.isAuthenticated &&
      id !== null &&
      response !== ""
    ) {
      const messageAdminRequestObject: AdminMessageRequest =
        new AdminMessageRequest(id, response);
      const requestOptions = {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(messageAdminRequestObject),
      };

      const messageAdminRequestResponse = await fetch(url, requestOptions);
      if (!messageAdminRequestResponse.ok) {
        throw new Error(" something went wrong when submit response");
      }
      setButtonSubmit(!buttonSubmit);
    }
  }

  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  return (
    <div className="mt-3">
      {adminMessages.length > 0 ? (
        <>
          <h5>Pending Questions</h5>
          {adminMessages.map((message) => (
            <AdminResponse
              message={message}
              key={message.id}
              submitResponseToQuestion={submitResponseToQuestion}
            />
          ))}
        </>
      ) : (
        <h5>No pending questions</h5>
      )}
      {totalPages > 0 && (
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          paginate={paginate}
        />
      )}
    </div>
  );
};
