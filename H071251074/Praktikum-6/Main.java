package Tugas6;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Item");
            System.out.println("4. Kembalikan Item");
            System.out.println("5. Lihat Status Perpustakaan");
            System.out.println("6. Lihat Log Aktivitas");
            System.out.println("7. Lihat Item yang Dipinjam Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                System.out.println("\n1. Buku");
                System.out.println("2. DVD");
                System.out.print("Jenis: ");
                int jenis = scanner.nextInt();
                scanner.nextLine();

                System.out.print("ID Item: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Judul: ");
                String judul = scanner.nextLine();

                try {
                    if (jenis == 1) {
                        System.out.print("Penulis: ");
                        String penulis = scanner.nextLine();
                        System.out.println(library.addItem(new Book(judul, id, penulis)));
                    } else if (jenis == 2) {
                        System.out.print("Durasi (menit): ");
                        int durasi = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(library.addItem(new DVD(judul, id, durasi)));
                    } else {
                        System.out.println("Pilihan tidak valid");
                    }
                } catch (Exception e) {
                    System.out.println("Gagal menambah item: " + e.getMessage());
                }

            } else if (pilihan == 2) {
                System.out.print("ID Anggota: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nama: ");
                String nama = scanner.nextLine();
                System.out.println(library.addMember(new Member(nama, id)));

            } else if (pilihan == 3) {
                try {
                    System.out.print("ID Anggota: ");
                    int idMember = scanner.nextInt();
                    scanner.nextLine();
                    Member member = library.findMemberById(idMember);

                    System.out.print("ID Item: ");
                    int idItem = scanner.nextInt();
                    scanner.nextLine();
                    LibraryItem item = library.findItemById(idItem);

                    System.out.print("Lama pinjam (hari): ");
                    int hari = scanner.nextInt();
                    scanner.nextLine();

                    String result = member.borrow(item, hari);
                    System.out.println(result);
                    library.getLogger().logActivity(item.getTitle() + " dipinjam oleh " + member.getName());

                } catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
                    System.out.println("Gagal meminjam: " + e.getMessage());
                }

            } else if (pilihan == 4) {
                try {
                    System.out.print("ID Anggota: ");
                    int idMember = scanner.nextInt();
                    scanner.nextLine();
                    Member member = library.findMemberById(idMember);

                    System.out.print("ID Item: ");
                    int idItem = scanner.nextInt();
                    scanner.nextLine();
                    LibraryItem item = library.findItemById(idItem);

                    System.out.print("Hari terlambat (0 jika tepat waktu): ");
                    int telat = scanner.nextInt();
                    scanner.nextLine();

                    String result = member.returnItem(item, telat);
                    System.out.println(result);
                    library.getLogger().logActivity(item.getTitle() + " dikembalikan oleh " + member.getName());

                } catch (NoSuchElementException | IllegalStateException e) {
                    System.out.println("Gagal mengembalikan: " + e.getMessage());
                }

            } else if (pilihan == 5) {
                System.out.println(library.getLibraryStatus());

            } else if (pilihan == 6) {
                System.out.println(library.getAllLogs());

            } else if (pilihan == 7) {
                try {
                    System.out.print("ID Anggota: ");
                    int idMember = scanner.nextInt();
                    scanner.nextLine();
                    Member member = library.findMemberById(idMember);
                    System.out.println(member.getBorrowedItems());
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }

            } else if (pilihan == 8) {
                System.out.println("Terima kasih telah menggunakan sistem!");
                running = false;

            } else {
                System.out.println("Pilihan tidak valid");
            }
        }

        scanner.close();
    }
}