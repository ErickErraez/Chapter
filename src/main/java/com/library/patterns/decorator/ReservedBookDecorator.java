import com.library.model.Book;

public class ReservedBookDecorator extends BookDecorator {
    private boolean isReserved;

    public ReservedBookDecorator(Book book) {
        super(book);
        this.isReserved = true; // By default, a reserved book is marked as reserved
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Reserved";
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void cancelReservation() {
        this.isReserved = false;
    }
}