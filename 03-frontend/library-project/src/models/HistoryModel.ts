class HistoryModel {
    id: number;
    userEmail : String;
    checkoutDate : String;
    returnedDate : String;
    title : String;
    author : String;
    description : String;
    img : String;

    constructor(
        id: number,
        userEmail : String,
        checkoutDate : String,
        returnedDate : String,
        title : String,
        author : String,
        description : String,
        img : String
    ){
        this.id = id;
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnedDate = returnedDate;
        this.title = title;
        this.author = author;
        this.description = description;
        this.img = img

    }
}

export default HistoryModel;