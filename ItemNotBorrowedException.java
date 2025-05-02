public class ItemNotBorrowedException extends Exception {
    public ItemNotBorrowedException() {
        super("Item is not borrowed");
    }
}
