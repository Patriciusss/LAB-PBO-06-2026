package Tugas6;

public class DVD extends LibraryItem {
    private int duration;

    public DVD(String title, int itemId, int duration) {
    super(title, itemId);
    if (duration <= 0) {
        throw new IllegalArgumentException("Durasi DVD harus lebih dari 0 menit");
    }
    this.duration = duration;
    }

    @Override
    public String getDescription() {
        return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        if (isBorrowed) {
            throw new IllegalArgumentException("DVD " + title + " sedang dipinjam");
        }
        if (days <= 0 || days > 7) {
            throw new IllegalArgumentException("DVD hanya bisa dipinjam maksimal 7 hari");
        }
        isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }
        double denda = daysLate * 25000;
        return denda;
    }

    public int getDuration() {
        return duration;
    }
}