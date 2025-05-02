public class ItemNotReservedException extends Exception {
    public ItemNotReservedException() {
        super("Item is not reserved");
    }
}
