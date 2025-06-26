import java.util.ArrayList;

public class LibraryManagement {


    static class Book {
        int bookId;
        String title;
        String author;
        boolean isIssued;

        Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String toString() {
            return bookId + ": '" + title + "' by " + author + " [" + (isIssued ? "Issued" : "Available") + "]";
        }
    }


    static class User {
        int userId;
        String name;
        ArrayList<Book> issuedBooks;

        User(int userId, String name) {
            this.userId = userId;
            this.name = name;
            this.issuedBooks = new ArrayList<>();
        }

        public String toString() {
            ArrayList<String> bookTitles = new ArrayList<>();
            for (Book b : issuedBooks) {
                bookTitles.add(b.title);
            }
            return "User " + userId + ": " + name + ", Books: " + bookTitles;
        }
    }


    static class Library {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        void addBook(Book book) {
            books.add(book);
        }

        void addUser(User user) {
            users.add(user);
        }

        void issueBook(int bookId, int userId) {
            Book book = null;
            for (Book b : books) {
                if (b.bookId == bookId) {
                    book = b;
                    break;
                }
            }

            User user = null;
            for (User u : users) {
                if (u.userId == userId) {
                    user = u;
                    break;
                }
            }

            if (book == null) {
                System.out.println("Book ID " + bookId + " not found.");
            } else if (book.isIssued) {
                System.out.println("Book '" + book.title + "' is already issued.");
            } else if (user == null) {
                System.out.println("User ID " + userId + " not found.");
            } else {
                book.isIssued = true;
                user.issuedBooks.add(book);
                System.out.println("Book '" + book.title + "' issued to " + user.name + ".");
            }
        }

        void returnBook(int bookId, int userId) {
            User user = null;
            for (User u : users) {
                if (u.userId == userId) {
                    user = u;
                    break;
                }
            }

            if (user == null) {
                System.out.println("User ID " + userId + " not found.");
                return;
            }

            Book bookToReturn = null;
            for (Book b : user.issuedBooks) {
                if (b.bookId == bookId) {
                    bookToReturn = b;
                    break;
                }
            }

            if (bookToReturn == null) {
                System.out.println("Book ID " + bookId + " not issued to " + user.name + ".");
            } else {
                bookToReturn.isIssued = false;
                user.issuedBooks.remove(bookToReturn);
                System.out.println("Book '" + bookToReturn.title + "' returned by " + user.name + ".");
            }
        }

        void displayBooks() {
            System.out.println("\nBooks in Library:");
            for (Book b : books) {
                System.out.println(b);
            }
        }

        void displayUsers() {
            System.out.println("\nUsers:");
            for (User u : users) {
                System.out.println(u);
            }
        }
    }


    public static void main(String[] args) {
        Library lib = new Library();

        lib.addBook(new Book(1, "2024", "Ram co Anandhi"));
        lib.addBook(new Book(2, "The Alchemist", "Paulo Coelho"));

        lib.addUser(new User(101, "Abhi"));
        lib.addUser(new User(102, "Harith"));

        lib.displayBooks();

        lib.issueBook(1, 101);
        lib.issueBook(2, 102);

        lib.displayBooks();

        lib.returnBook(1, 101);

        lib.displayBooks();
        lib.displayUsers();
    }
}
