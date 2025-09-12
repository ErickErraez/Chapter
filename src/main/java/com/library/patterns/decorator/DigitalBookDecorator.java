import com.library.model.Book;

public class DigitalBookDecorator extends BookDecorator {
    private String fileFormat;

    public DigitalBookDecorator(Book book, String fileFormat) {
        super(book);
        this.fileFormat = fileFormat;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Format: " + fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void download() {
        System.out.println("Downloading " + getTitle() + " in " + fileFormat + " format.");
    }
}