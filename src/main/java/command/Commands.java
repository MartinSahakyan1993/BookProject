package command;

public interface Commands {
    int LOGOUT = 0;
    int ADD_BOOK = 1;
    int PRINT_ALL_BOOKS = 2;
    int PRINT_BOOKS_BY_AUTORNAME = 3;
    int PRINT_BOOK_BY_GENRE = 4;
    int PRINT_BOOKS_BY_PRICE_RANGE = 5;
    int ADD_AUTHOR = 6;
    int PRINT_ALL_AUTHOR = 7;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;
    static void printAdminCommand() {
        System.out.println("please input " + LOGOUT+ " for LOGOUT");
        System.out.println("please input " + ADD_BOOK + " for Add Book");
        System.out.println("please input " + PRINT_ALL_BOOKS + " for Print All Books ");
        System.out.println("please input " + PRINT_BOOKS_BY_AUTORNAME + " for Print Books by AuthorName ");
        System.out.println("please input " + PRINT_BOOK_BY_GENRE + " for Print Books by Genre");
        System.out.println("please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for Print books by price range");
        System.out.println("please input " + ADD_AUTHOR + " for add author");
        System.out.println("please input " + PRINT_ALL_AUTHOR + " for print all author");
    }
    static void printUserCommand() {
        System.out.println("please input " + LOGOUT+ " for LOGOUT");
        System.out.println("please input " + ADD_BOOK + " for Add Book");
        System.out.println("please input " + PRINT_ALL_BOOKS + " for Print All Books ");
        System.out.println("please input " + PRINT_BOOKS_BY_AUTORNAME + " for Print Books by AuthorName ");
        System.out.println("please input " + PRINT_BOOK_BY_GENRE + " for Print Books by Genre");
        System.out.println("please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for Print books by price range");
        System.out.println("please input " + ADD_AUTHOR + " for add author");
        System.out.println("please input " + PRINT_ALL_AUTHOR + " for print all author");
    }
    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");
    }

}
