package Tugas6;

public class Book extends LibraryItem {
    private String author;

    public Book(String title, int itemId, String author) {
    super(title, itemId);
    if (author == null || author.isEmpty()) {
        throw new IllegalArgumentException("Nama penulis tidak boleh kosong");
    }
    this.author = author;
    }

    @Override
    public String getDescription() {
        return "Buku: " + title + " oleh " + author + ", ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        if (isBorrowed) {
            throw new IllegalArgumentException("Buku " + title + " sedang dipinjam");
        }
        if (days <= 0 || days > 14) {
            throw new IllegalArgumentException("Buku hanya bisa dipinjam maksimal 14 hari");
        }
        isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }
        double denda = daysLate * 10000;
        return denda;
    }

    public String getAuthor() {
        return author;
    }
}
