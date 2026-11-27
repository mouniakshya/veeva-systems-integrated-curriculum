import java.util.*;

interface Searchable {
    boolean matchesKeyword(String kw);
}

class Book implements Searchable {
    String isbn;
    String title;
    String author;
    int borrowCount;

    Book(String isbn, String title, String author, int borrowCount) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.borrowCount = borrowCount;
    }

    public boolean matchesKeyword(String kw) {
        String key = kw.toLowerCase();
        return title.toLowerCase().contains(key) || author.toLowerCase().contains(key);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book b = (Book) obj;
        return isbn.equals(b.isbn);
    }

    public int hashCode() {
        return isbn.hashCode();
    }

    public String toString() {
        return title + " (" + borrowCount + ")";
    }
}

class LibraryCatalog {
    Set<Book> books = new HashSet<>();

    boolean addBook(Book b) {
        return books.add(b);
    }

    List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.matchesKeyword(keyword)) {
                result.add(b);
            }
        }
        Collections.sort(result, new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                return Integer.compare(b2.borrowCount, b1.borrowCount);
            }
        });
        return result;
    }

    Optional<Book> getMostBorrowedBook() {
        if (books.isEmpty()) {
            return Optional.empty();
        }
        Book top = null;
        for (Book b : books) {
            if (top == null || b.borrowCount > top.borrowCount) {
                top = b;
            }
        }
        return Optional.of(top);
    }
}

public class library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter Book Details:");
            System.out.print("Enter ISBN: ");
            String isbn = sc.next();
            sc.nextLine();

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Borrow Count: ");
            int count = sc.nextInt();

            Book b = new Book(isbn, title, author, count);

            if (catalog.addBook(b)) {
                System.out.println("Book Added Successfully!");
            } else {
                System.out.println("Duplicate ISBN! Book Ignored.");
            }
        }

        System.out.print("\nEnter keyword to search: ");
        sc.nextLine();
        String keyword = sc.nextLine();

        System.out.println("\nSearch Results:");
        System.out.println(catalog.searchBooks(keyword));

        Optional<Book> most = catalog.getMostBorrowedBook();
        System.out.println("\nMost Borrowed: " + (most.isPresent() ? most.get().title : "None"));

        sc.close();
    }
}
