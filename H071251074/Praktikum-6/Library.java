package Tugas6;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Library {
    private ArrayList<LibraryItem> items;
    private ArrayList<Member> members;
    private LibraryLogger logger;

    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        logger = new LibraryLogger();
    }

    public String addItem(LibraryItem item) {
        items.add(item);
        return item.getTitle() + " berhasil ditambahkan";
    }

    public String addMember(Member member) {
        members.add(member);
        return "Anggota " + member.getName() + " berhasil ditambahkan";
    }

    public LibraryItem findItemById(int itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId() == itemId) {
                return items.get(i);
            }
        }
        throw new NoSuchElementException("Item dengan ID " + itemId + " tidak ditemukan");
    }

    public Member findMemberById(int memberId) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == memberId) {
                return members.get(i);
            }
        }
        throw new NoSuchElementException("Member dengan ID " + memberId + " tidak ditemukan");
    }

    public String getLibraryStatus() {
        if (items.isEmpty()) {
            return "Tidak ada item di perpustakaan";
        }
        String hasil = "+-----+--------------------------------+----------+\n";
        hasil = hasil + "| ID  | Judul                          | Status   |\n";
        hasil = hasil + "+-----+--------------------------------+----------+\n";
        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            hasil = hasil + String.format("| %-3d | %-30s | %-8s |\n", item.getItemId(), item.getTitle(), status);
        }
        hasil = hasil + "+-----+--------------------------------+----------+";
        return hasil;
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public LibraryLogger getLogger() {
        return logger;
    }

    public ArrayList<LibraryItem> getItems() {
        return items;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
}