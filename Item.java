import java.util.Date;

public abstract class Item {
    private int id;
    private int ISBN;
    private String title;
    private Date date;

    private static String[] itemTitles = new String[0];
    private static int[] itemQuantities = new int[0];

    static int totalQuantity = 0;

    Item(int ISBN, String title, Date date) {
        totalQuantity++;
        this.id = totalQuantity;
        this.ISBN = ISBN;
        this.title = title;
        this.date = date;
    }

    static void addItem(String title){
        for (int i = 0; i < itemTitles.length; i++) {
            if (itemTitles[i].equals(title)) {
                itemQuantities[i]++;
                return;
            }
        }

        String[] tempTitles = new String[itemTitles.length + 1];
        int[] tempQuantities = new int[itemQuantities.length + 1];
        if (itemTitles.length != 0){
            for (int i = 0; i < itemTitles.length; i++) {
                tempTitles[i] = itemTitles[i];
                tempQuantities[i] = itemQuantities[i];
            }
        }

        tempTitles[tempTitles.length - 1] = title;
        tempQuantities[tempQuantities.length - 1] = 1;

        itemTitles = tempTitles;
        itemQuantities = tempQuantities;
    }

    static void removeItem(String title){
        for (int i = 0; i < itemTitles.length; i++) {
            if (itemTitles[i].equals(title)) {
                if(itemQuantities[i] != 0) {
                    itemQuantities[i]--;
                    return;
                }
            }
        }
    }

    public int getItemQuantity(){
        for (int i = 0; i < itemTitles.length; i++) {
            if (itemTitles[i].equals(title)) {
                return itemQuantities[i];
            }
        }

        return 0;
    }

    public int giveItem(String title) throws Exception{
        for (int i = 0; i < itemTitles.length; i++) {
            if (itemTitles[i].equals(title)) {
                if (itemQuantities[i] == 0) {
                    throw new NotEnoughQuantityException();
                }

                return itemQuantities[i]--;
            }
        }

        throw new NotEnoughQuantityException();
    }

    public int takeItem(String title){
        for (int i = 0; i < itemTitles.length; i++) {
            if (itemTitles[i].equals(title)) {
                return itemQuantities[i]++;
            }
        }

        return 0;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ID: " + id + " ISBN: " + ISBN + " Title: " + title + " Date: " + date;
    }

    public boolean equals(String title){
        return this.title.equals(title);
    }

    public int getId() {
        return id;
    }
}
