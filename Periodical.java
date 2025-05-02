import java.util.Date;

public class Periodical extends Item {
    private String publisher;
    private boolean isReading = false;

    Periodical(int ISBN, String title, Date date, String publisher) {
        super(ISBN, title, date);
        this.publisher = publisher;
    }

    public int read() throws Exception{
        try{
            this.giveItem(this.getTitle());
        }catch (Exception e){
            throw e;
        }

        if (isReading) {
            throw new ItemNotReadableException();
        }

        isReading = true;
        return this.getItemQuantity();
    }

    public int stopReading() throws Exception{
        if (!isReading) {
            throw new ItemNotReadException();
        }

        this.takeItem(this.getTitle());
        isReading = false;
        return this.getItemQuantity();
    }

    @Override
    public String toString() {
        return "Periodical " + super.toString() + "Publisher: " + publisher + "";
    }
}
