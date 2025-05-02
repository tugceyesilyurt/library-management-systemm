import java.util.Date;

public interface Borrowable {
    int borrow(Date issueDate, Date returnDate) throws Exception;
    double returnItem() throws Exception;
}
