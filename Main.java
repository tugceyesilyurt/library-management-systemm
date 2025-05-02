import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scn = new Scanner(System.in);
        int choice = 0;
        String title, publisher,author,subject;
        int ISBN;
        Item item;
        double lateFine;
        do{
            choice = getSelection();
            switch (choice){
                case 1:
                    System.out.println("Enter the title of the book: ");
                    title = scn.nextLine();
                    System.out.println("Enter the ISBN of the book: ");
                    ISBN = scn.nextInt();
                    System.out.println("Enter the author of the book: ");
                    scn.next();
                    author = scn.nextLine();
                    System.out.println("Enter the late fine of the book: ");
                    lateFine = scn.nextDouble();
                    System.out.println("Enter the subject of the book: ");
                    subject = scn.nextLine();


                    RegularBooks regularBooks = new RegularBooks(ISBN, title, new Date(), author, subject, lateFine);
                    manager.addItem(regularBooks);
                    break;
                case 2:
                    System.out.println("Enter the title of the book: ");
                    title = scn.nextLine();
                    System.out.println("Enter the ISBN of the book: ");
                    ISBN = scn.nextInt();
                    System.out.println("Enter the author of the book: ");
                    scn.next();
                    author = scn.nextLine();
                    System.out.println("Enter the subject of the book: ");
                    scn.next();
                    subject = scn.nextLine();
                    System.out.println("Enter the late fine of the book: ");
                    lateFine = scn.nextDouble();

                    ReservedBooks reservedBooks = new ReservedBooks(ISBN, title, new Date(), author, subject);
                    manager.addItem(reservedBooks);
                    break;
                case 3:
                    System.out.println("Enter the title of the book: ");
                    scn.next();
                    title = scn.nextLine();
                    System.out.println("Enter the ISBN of the book: ");
                    ISBN = scn.nextInt();
                    System.out.println("Enter the publisher of the book: ");
                    scn.next();
                    publisher = scn.nextLine();

                    Periodical periodical = new Periodical(ISBN, title, new Date(), publisher);
                    manager.addItem(periodical);
                    break;
                case 4:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        manager.removeItem(item);

                        System.out.println(item);
                    }else {
                        System.out.println("Item not found");
                    }

                    break;
                case 5:
                    System.out.println("Enter the title of the item: ");
                    title = scn.nextLine();
                    item = manager.searchItem(title);
                    if (item != null) {
                        System.out.println(item);
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 6:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            Date issueDate = selectDate(" issue ");
                            Date returnDate = selectDate(" return ");
                            System.out.println("Item Borrowed and new quantity is " + manager.borrowItem(item, issueDate, returnDate));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 7:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            System.out.println("Item returned and fine is " + manager.returnItem(item));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 8:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            System.out.println("Item reserved and new quantity is " + manager.reserveItem(item));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 9:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            System.out.println("Item unreserved and new quantity is " + manager.cancelReservation(item));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 10:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            System.out.println("Item read and new quantity is " + manager.readItem(item));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 11:
                    item = findExactItemByID(manager);
                    if (item != null) {
                        try {
                            System.out.println("Item unread and new quantity is " + manager.stopReading(item));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Item not found");
                    }
                    break;
                case 12:
                    System.out.println("Quitting...");
                    break;
            }
        }while (choice != 12);
    }

    static int getSelection(){
        System.out.println("\n1) Add a new regular book to the system");
        System.out.println("2) Add a new reserved book to the system");
        System.out.println("3) Add a new periodical to the system");
        System.out.println("4) Remove an existing item (regular book, reserved book, periodical) from the library");
        System.out.println("5) Search for an item based on its title");
        System.out.println("6) Borrow a regular book, based on id");
        System.out.println("7) Return a regular book, based on id");
        System.out.println("8) Hold a reserved book, based on id");
        System.out.println("9) Unhold a reserved book, based on id");
        System.out.println("10) Read a periodical, based on id");
        System.out.println("11) Unread a periodical, based on id");
        System.out.println("12) Quit");
        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static Item findExactItemByID(Manager manager){
        manager.printItems();
        System.out.println("Enter the id of the item: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return manager.searchWithID(id);
    }

    static Date selectDate(String type){
        Date today = new Date();

        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Today ");
        System.out.println(today.toString());
        Date tomorrow = new Date(today.getTime()+ 24 * 60 * 60 * 1000);
        System.out.print("2) Tomorrow ");
        System.out.println(tomorrow.toString());
        Date oneWeek = new Date(today.getTime()+ 7 * 24 * 60 * 60 * 1000);
        System.out.print("3) After One Week from Today ");
        System.out.println(oneWeek.toString());
        Date twoWeek = new Date(today.getTime()+ 14 * 24 * 60 * 60 * 1000);
        System.out.print("4) After Two Week from Today ");
        System.out.println(twoWeek.toString());

        System.out.println("\nEnter" + type + "date: ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                return today;
            case 2:
                return tomorrow;
            case 3:
                return oneWeek;
            case 4:
                return twoWeek;
        }
        return today;
    }
}