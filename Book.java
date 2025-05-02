import java.util.Date;

public abstract class Book extends Item{
    private String author;
    private String subject;

    Book(int ISBN, String title, Date date, String author, String subject) {
        super(ISBN, title, date);
        this.author = author;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString() + "Author: " + author + " Subject: " + subject;
    }
}

class RegularBooks extends Book implements Borrowable {
    private Date issueDate;
    private Date returnDate;
    private double lateFine;

    RegularBooks(int ISBN, String title, Date date, String author, String subject, double lateFine) {
        super(ISBN, title, date, author, subject);
        this.lateFine = lateFine;

    }

    @Override
    public int borrow(Date issueDate, Date returnDate) throws Exception {
        try {
            this.giveItem(this.getTitle());
        }catch (Exception e){
            throw e;
        }

        this.issueDate = issueDate;
        this.returnDate = returnDate;

        return this.getItemQuantity();
    }

    @Override
    public double returnItem() throws Exception {
        if (issueDate == null && returnDate == null) {
            throw new ItemNotBorrowedException();
        }
        Date today = new Date();
        long diff = today.getTime() - returnDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        this.issueDate = null;
        this.returnDate = null;

        this.takeItem(this.getTitle());
        if (diffDays > 0) {
            return diffDays * lateFine;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Regular Book " + super.toString() + "Late Fine: " + lateFine + "";
    }
}

class ReservedBooks extends Book implements Reserveable {
    private boolean isReserved = false;
    static int holdTime = 3;

    ReservedBooks(int ISBN, String title, Date date, String author, String subject) {
        super(ISBN, title, date, author, subject);
    }

    @Override
    public int reserve() throws Exception {
        if (!(this instanceof Reserveable)) {
            throw new ItemNotReservableException();
        }


        try {
            this.giveItem(this.getTitle());
        }catch (Exception e){
            throw e;
        }
        isReserved = true;
        return this.getItemQuantity();
    }

    @Override
    public int cancelReservation() throws Exception {
        if (!isReserved) {
            throw new ItemNotHoldException();
        }

        this.takeItem(this.getTitle());

        isReserved = false;
        return this.getItemQuantity();
    }

    @Override
    public String toString() {
        return "Reserved Book" + super.toString();
    }
}