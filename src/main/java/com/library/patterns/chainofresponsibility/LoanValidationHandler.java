import java.util.Date;

public class LoanValidationHandler extends ValidationHandler {
    @Override
    public boolean validate(Object request) {
        if (request instanceof Loan loan) {
            if (loan.getBook() == null) {
                System.out.println("Loan validation failed: Book cannot be null.");
                return false;
            }
            if (loan.getMember() == null) {
                System.out.println("Loan validation failed: Member cannot be null.");
                return false;
            }
            if (loan.getLoanDate() == null || loan.getReturnDate() == null) {
                System.out.println("Loan validation failed: Loan date and return date cannot be null.");
                return false;
            }
            if (loan.getReturnDate().before(loan.getLoanDate())) {
                System.out.println("Loan validation failed: Return date cannot be before loan date.");
                return false;
            }
            System.out.println("Loan validation succeeded.");
            return true;
        }
        return super.validate(request);
    }
}