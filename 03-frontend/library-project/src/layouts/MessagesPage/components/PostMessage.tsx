import { useOktaAuth } from "@okta/okta-react";
import { useState } from "react";
import MessageModel from "../../../models/MessageModel";

export const PostMessage = () => {
  const { authState } = useOktaAuth();
  const [title, setTitle] = useState("");
  const [question, setQuestion] = useState("");
  const [isWarning, setIsWarning] = useState(false);
  const [isPostSuccess, setIsPostSuccess] = useState(false);

  async function submitQuestion() {
    const url = `${process.env.REACT_APP_API}/messages/secure/add/message`;

    if (authState?.isAuthenticated && title !== "" && question !== "") {
      const messageReqModel: MessageModel = new MessageModel(title, question);

      const requestOptions = {
        method: "POST",
        headers: {
          Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
          "Content-Type": "application/json",
        },

        body: JSON.stringify(messageReqModel),
      };

      const postNewQuestionResponse = await fetch(url, requestOptions);
      if (!postNewQuestionResponse.ok) {
        throw new Error("Somthing went wrong in posting new question");
      }

      setTitle("");
      setQuestion("");
      setIsWarning(false);
      setIsPostSuccess(true);
    } else {
      setIsWarning(true);
      setIsPostSuccess(false);
    }
  }

  return (
    <div className="card mt-3">
      <div className="card-header">Ask question from BookShelf Admins</div>
      <div className="card-body">
        <form method="POST">
          {isWarning && (
            <div className="alert alert-danger" role="alert">
              All fields must be filled out
            </div>
          )}
          {isPostSuccess && (
            <div className="alert alert-success" role="alert">
              Question added successfully
            </div>
          )}

          <div className="mb-3">
            <label className="form-label">Title</label>
            <input
              type="text"
              className="form-control"
              id="exampleFormControlInput1"
              placeholder="Title"
              onChange={(e) => setTitle(e.target.value)}
              value={title}
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Question</label>
            <textarea
              className="form-control"
              id="exampleFormControlTextarea1"
              rows={3}
              onChange={(e) => setQuestion(e.target.value)}
              value={question}
            ></textarea>
          </div>
          <div>
            <button
              type="button"
              className="btn btn-primary mt-3"
              onClick={submitQuestion}
            >
              Submit Question
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
