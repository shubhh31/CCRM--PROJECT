package edu.ccrm.io;
import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path BACKUP_DIR = Paths.get("backups");
    private static final String STUDENTS_FILE = "students.csv";

    private void ensureDirectoriesExist() throws IOException {
        if (Files.notExists(DATA_DIR)) {
            Files.createDirectories(DATA_DIR);
        }
        if (Files.notExists(BACKUP_DIR)) {
            Files.createDirectories(BACKUP_DIR);
        }
    }
    public void exportData() throws IOException {
        ensureDirectoriesExist();
        Path studentsPath = DATA_DIR.resolve(STUDENTS_FILE);
        List<String> studentLines = DataStore.getInstance().students.stream()
                .map(s -> String.join(",",
                        String.valueOf(s.getId()),
                        s.getFullName(),
                        s.getEmail(),
                        s.getRegNo()))
                .collect(Collectors.toList());

        Files.write(studentsPath, studentLines);
        System.out.println("Successfully exported data to " + studentsPath);
    }

    public void importData() throws IOException {
        ensureDirectoriesExist();
        Path studentsPath = DATA_DIR.resolve(STUDENTS_FILE);

        if (Files.notExists(studentsPath)) {
            System.out.println("No data file found to import. Skipping.");
            return;
        }

        try (Stream<String> lines = Files.lines(studentsPath)) {
            List<Student> students = lines.map(line -> {
                String[] parts = line.split(",");
                if (parts.length < 4) return null;
                return new Student(Long.parseLong(parts[0]), parts[1], parts[2], parts[3]);
            }).filter(s -> s != null).collect(Collectors.toList());
            DataStore.getInstance().students.clear();
            DataStore.getInstance().students.addAll(students);
            System.out.println("Successfully imported " + students.size() + " students.");
        }
    }

    public void runBackup() throws IOException {
        ensureDirectoriesExist();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path newBackupDir = BACKUP_DIR.resolve("backup_" + timestamp);
        Files.createDirectories(newBackupDir);

        Path sourceFile = DATA_DIR.resolve(STUDENTS_FILE);
        if (Files.exists(sourceFile)) {
            Files.copy(sourceFile, newBackupDir.resolve(STUDENTS_FILE), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup created successfully at: " + newBackupDir);
            long size = calculateDirectorySize(newBackupDir);
            System.out.printf("Backup size: %d bytes (%.2f KB)\n", size, size / 1024.0);
        } else {
            System.out.println("No data file found to back up.");
        }
    }
    private long calculateDirectorySize(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            return Files.size(path);
        }
        long size = 0;
        try (Stream<Path> stream = Files.list(path)) {
            for (Path p : stream.collect(Collectors.toList())) {
                size += calculateDirectorySize(p); 
            }
        }
        return size;
    }
}
