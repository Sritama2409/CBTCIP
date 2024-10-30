import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class to store book details
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { this.isIssued = true; }
    public void returnBook() { this.isIssued = false; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

// Library class for admin and user operations
class Library {
    private List<Book> books = new ArrayList<>();
    private static int bookIdCounter = 1;

    // Admin functionalities
    public void addBook(String title, String author) {
        books.add(new Book(bookIdCounter++, title, author));
        System.out.println("Book added successfully.");
    }

    public void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed successfully.");
    }

    public void updateBook(int id, String title, String author) {
        for (Book book : books) {
            if (book.getId() == id) {
                book = new Book(id, title, author);
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // User functionalities
    public void viewBooks() {
        System.out.println("Available Books:");
        books.forEach(System.out::println);
    }

    public void issueBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && !book.isIssued()) {
                book.issueBook();
                System.out.println("Book issued successfully.");
                return;
            }
        }
        System.out.println("Book is already issued or not available.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && book.isIssued()) {
                book.returnBook();
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Book was not issued.");
    }
}

// Main class for running the application
public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose role: 1-Admin, 2-User, 3-Exit");
            int role = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (role == 1) {
                adminMenu();
            } else if (role == 2) {
                userMenu();
            } else if (role == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu: 1-Add Book, 2-Remove Book, 3-Update Book, 4-View Books, 5-Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter book author: ");
                String author = scanner.nextLine();
                library.addBook(title, author);
            } else if (choice == 2) {
                System.out.print("Enter book ID to remove: ");
                int id = scanner.nextInt();
                library.removeBook(id);
            } else if (choice == 3) {
                System.out.print("Enter book ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter new book author: ");
                String author = scanner.nextLine();
                library.updateBook(id, title, author);
            } else if (choice == 4) {
                library.viewBooks();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("User Menu: 1-View Books, 2-Issue Book, 3-Return Book, 4-Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                library.viewBooks();
            } else if (choice == 2) {
                System.out.print("Enter book ID to issue: ");
                int id = scanner.nextInt();
                library.issueBook(id);
            } else if (choice == 3) {
                System.out.print("Enter book ID to return: ");
                int id = scanner.nextInt();
                library.returnBook(id);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}