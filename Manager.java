import java.util.ArrayList;
import java.util.Date;

public class Manager {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
        Item.addItem(item.getTitle());
    }

    public boolean removeItem(Item item){
        if (items.contains(item)) {
            items.remove(item);
            Item.removeItem(item.getTitle());
            return true;
        }

        return false;
    }

    public Item searchItem(String title){
        for (Item item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }

        return null;
    }

    public Item searchWithID(int id){
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public int borrowItem(Item item, Date issueDate, Date returnDate) throws Exception {
        if (issueDate.after(returnDate)) {
            throw new InvalidDateException();
        }

        if (item instanceof Borrowable) {
            try {
                return ((Borrowable) item).borrow(issueDate, returnDate);
            }catch (Exception e){
                throw e;
            }
        }

        throw new ItemNotBorrowableException();
    }

    public double returnItem(Item item) throws Exception {
        if (item instanceof Borrowable) {
            try {
                return ((Borrowable) item).returnItem();
            }catch (Exception e){
                throw e;
            }
        }

        throw new ItemNotBorrowedException();
    }

    public int reserveItem(Item item) throws Exception{
        if (item instanceof Reserveable) {
            try {
                return ((Reserveable) item).reserve();
            }catch (Exception e){
                throw e;
            }
        }

        throw new ItemNotReservableException();
    }

    public int cancelReservation(Item item) throws Exception {
        if (item instanceof Reserveable) {
            try {
                return ((Reserveable) item).cancelReservation();
            }catch (Exception e){
                throw e;
            }
        }
        throw new ItemNotReservableException();
    }

    public int readItem(Item item) throws Exception{
        if (item instanceof Periodical) {
            try {
                return ((Periodical) item).read();
            }catch (Exception e){
                throw e;
            }
        }

        throw new ItemNotReadableException();
    }

    public int stopReading(Item item) throws Exception{
        if (item instanceof Periodical) {
            try {
                return ((Periodical) item).stopReading();
            }catch (Exception e){
                throw e;
            }
        }

        throw new ItemNotReadableException();
    }

    public void printItems(){
        for (Item item : items) {
            System.out.println(item + "Item Quantity: " + item.getItemQuantity() + "\n");
        }
    }
}
