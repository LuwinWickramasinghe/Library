class AddBookRequest {
    title : string;
    author: string;
    description: string;
    copies: number;
    categories: string;
    img?: string;

    constructor(
        title : string,
        author: string,
        description: string,
        copies: number,
        categories: string,
        img: string,
    ){
        this.title= title;
        this.author = author;
        this.description = description;
        this.copies = copies;
        this.categories = categories;

    }

}

export default AddBookRequest;