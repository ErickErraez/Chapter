import com.library.model.Book;

public abstract class BookDecorator extends Book {
    protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        super(decoratedBook.getId(), decoratedBook.getTitle(), decoratedBook.getAuthor(), 
              decoratedBook.getType(), decoratedBook.getFormat(), decoratedBook.getStatus());
        this.decoratedBook = decoratedBook;
    }

    @Override
    public String getTitle() {
        return decoratedBook.getTitle();
    }

    @Override
    public String getAuthor() {
        return decoratedBook.getAuthor();
    }

    @Override
    public String getType() {
        return decoratedBook.getType();
    }

    @Override
    public String getFormat() {
        return decoratedBook.getFormat();
    }

    @Override
    public String getStatus() {
        return decoratedBook.getStatus();
    }

    public abstract String getDescription();
}