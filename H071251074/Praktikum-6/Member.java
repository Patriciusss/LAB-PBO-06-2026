package Tugas6;

import java.util.ArrayList;

public class Member {
    private String name;
    private int memberId;
    private ArrayList<LibraryItem> borrowedItems;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedItems = new ArrayList<>();
    }

    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException("Item " + item.getTitle() + " tidak tersedia (sedang dipinjam)");
        }
        String result = item.borrowItem(days);
        borrowedItems.add(item);
        return result;
    }

    public String returnItem(LibraryItem item, int daysLate) {
        boolean found = false;
        for (int i = 0; i < borrowedItems.size(); i++) {
            if (borrowedItems.get(i).getItemId() == item.getItemId()) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalStateException("Item " + item.getTitle() + " tidak dipinjam oleh " + name);
        }

        item.returnItem();
        borrowedItems.remove(item);

        double denda = item.calculateFine(daysLate);
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + denda;
    }

    public String getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            return "Tidak ada item yang dipinjam";
        }

        String hasil = "+-----+--------------------------------+\n";
        hasil = hasil + "| ID  | Judul                          |\n";
        hasil = hasil + "+-----+--------------------------------+\n";
        for (int i = 0; i < borrowedItems.size(); i++) {
            LibraryItem item = borrowedItems.get(i);
            hasil = hasil + String.format("| %-3d | %-30s |\n", item.getItemId(), item.getTitle());
        }
        hasil = hasil + "+-----+--------------------------------+";
        return hasil;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }
}