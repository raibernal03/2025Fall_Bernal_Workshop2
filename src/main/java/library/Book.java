package library;

public class Book {
    private final int id;
    private final String isbn;
    private final String title;
    private boolean isCheckedOut = false;
    private String cheackedOutTo = "";

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheackedOutTo() {
        return cheackedOutTo;
    }

    /***
     * Methods
     * - checkOut(name)
     * - checkIn()
     */

    public void checkOut(String name) {
        this.isCheckedOut = true;
        cheackedOutTo = name;
    }

    public void checkIn() {
        this.isCheckedOut = false;
        this.cheackedOutTo = "";
    }
}
