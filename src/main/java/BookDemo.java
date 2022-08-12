import command.Commands;
import exception.AuthorNotFoundException;
import gender.Gender;
import model.Author;
import model.Book;
import model.User;
import model.UserType;
import storage.AutherStorage;
import storage.BooksStorage;
import storage.UserStorage;

import java.util.Scanner;

public class BookDemo implements Commands{
    public static Scanner scanner = new Scanner(System.in);
    private static BooksStorage booksStorage = new BooksStorage();
    private static AutherStorage autherStorage = new AutherStorage();
    private static UserStorage userStorage = new UserStorage();

    private static User currentUser = null;


    public static void main(String[] args) {
        initData();
        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;

            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void initData() {
        User admin = new User("admin", "admin", "admin@mail.com", "admin", UserType.ADMIN);
        userStorage.add(admin);
    }

    private static void login() {
        System.out.println("Please input email, password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        if (emailPassword.length < 2) {
            System.out.println("Please input correct data");
        } else {
            User user = userStorage.getUserByEmail(emailPassword[0]);
            if (user == null) {
                System.out.println("User with " + emailPassword[0] + " does not exists!");
            } else if (user.getPassword().equals(emailPassword[1])) {
                currentUser = user;
                if (user.getUserType() == UserType.ADMIN) {
                    loginAdmin();
                } else if (user.getUserType() == UserType.USER) {
                    loginUser();
                }
            } else {
                System.out.println("Password is wrong!");
            }
        }
    }

    private static void loginUser() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printUserCommand();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_ALL_BOOKS:
                    booksStorage.printAllBooks();
                    break;
                case PRINT_BOOKS_BY_AUTORNAME:
                    printBoobksByAuthorName();
                    break;
                case PRINT_BOOK_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHOR:
                    autherStorage.print();
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private static void register() {

        System.out.println("Please input name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct data");
        } else {
            if (userStorage.getUserByEmail(userData[2]) == null && userData[2].contains("@")) {

                User user = new User();
                user.setName(userData[0]);
                user.setSurName(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setUserType(UserType.USER);
                userStorage.add(user);
                System.out.println("User created!");
            } else {
                System.out.println("User with " + userData[2] + " already exists!");
            }
        }
    }


    private static void loginAdmin() {
        boolean run = true;
        while (run) {
            Commands.printAdminCommand();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;

            }

            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_ALL_BOOKS:
                    booksStorage.printAllBooks();
                    break;
                case PRINT_BOOKS_BY_AUTORNAME:
                    printBoobksByAuthorName();
                    break;
                case PRINT_BOOK_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHOR:
                    autherStorage.print();
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private static void addBook() {
        if (autherStorage.getSize() != 0) {
            autherStorage.print();
            System.out.println("please input index of author");

            Author author = null;

            try {
                int authorIndex = Integer.parseInt(scanner.nextLine());
                author = autherStorage.getAuthorByIndex(authorIndex);
                System.out.println("please input book,s title");
                String title = scanner.nextLine();
                while (title.equals("")) {
                    System.out.println("plase input book,s title");
                    title = scanner.nextLine();
                }
                System.out.println("please input books price");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.println("please input book's count");
                int count = Integer.parseInt(scanner.nextLine());
                System.out.println("please input books genre  ");
                String genre = scanner.nextLine();
                Book book = new Book(title, author, price, count, genre);
                booksStorage.add(book);
                System.out.println("book was added");

            } catch (AuthorNotFoundException | NumberFormatException e) {
                System.out.println("please choose correct number or index");
                autherStorage.print();
                addBook();
            }
        }
    }

    private static void addAuthor() {

        System.out.println("Please input author name");
        String name = scanner.nextLine();
        while (name.equals("")) {
            System.out.println("Please input author name");
            name = scanner.nextLine();
        }
        System.out.println("Please input author surname");
        String authorSurname = scanner.nextLine();
        while (authorSurname.equals("")) {
            System.out.println("Please input author surname");
            authorSurname = scanner.nextLine();
        }
        String authorMail = null;
        try {

            System.out.println("Please input author mail");
            authorMail = scanner.nextLine();

            if (!authorMail.contains("@")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("the mail should contain @ ");
            addAuthor();
        }

        Gender gender = addAuthorGender();

        Author author = new Author(name, authorSurname, authorMail, gender);
        autherStorage.addAuthor(author);
        System.out.println("author crated");
    }

    private static void printBooksByGenre() {
        System.out.println("please input books genre");
        String genre = scanner.nextLine();
        booksStorage.PrintBooksbyGenre(genre);
    }

    private static void printBooksByPriceRange() {
        try {
            System.out.println("please input minPrice");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.println("please input maxPrice");
            int price1 = Integer.parseInt(scanner.nextLine());
            booksStorage.Printbooksbypricerange(price, price1);
        } catch (NumberFormatException e) {
            System.out.println("Plese input only numbers");
        }
    }

    private static Gender addAuthorGender() {
        System.out.println("Please input author gender MALE or FEMALE");
        String gender = null;
        try {
            gender = scanner.nextLine();
            if (gender.equals("MALE") || gender.equals("FEMALE")) {
                System.out.println("gender is correct");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("gender should be MALE or FEMALE");
            addAuthorGender();
        }
        return Gender.valueOf(gender);
    }

    private static void printBoobksByAuthorName() {
        System.out.println("Please input author name");
        String authorName = scanner.nextLine();
        if (authorName != null && !authorName.trim().equals("")) booksStorage.printBooksByAuthorname(authorName.trim());
    }
}
