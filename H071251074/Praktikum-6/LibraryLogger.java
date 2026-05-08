package Tugas6;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibraryLogger {
    private ArrayList<String> logs;

    public LibraryLogger() {
        logs = new ArrayList<>();
    }

    public String logActivity(String activity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = timestamp + " " + activity;
        logs.add(logEntry);
        return logEntry;
    }

    public String getLogs() {
        if (logs.isEmpty()) {
            return "Tidak ada log aktivitas";
        }
        String hasil = "";
        for (int i = 0; i < logs.size(); i++) {
            if (i == logs.size() - 1) {
                hasil = hasil + logs.get(i);
            } else {
                hasil = hasil + logs.get(i) + "\n";
            }
        }
        return hasil;
    }

    public void clearLogs() {
        logs.clear();
    }
}