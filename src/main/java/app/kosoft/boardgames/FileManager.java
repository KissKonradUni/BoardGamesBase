package app.kosoft.boardgames;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManager {
    private static final String staticRoot;
    static {
        String workingDir = System.getProperty("user.dir");
        staticRoot = workingDir + "/static/";
        System.out.println("Static root: " + staticRoot);
    }

    public static boolean isPathValid(Path path) {
        return path.startsWith(staticRoot);
    }

    public static String getTextFileContent(String stringPath) {
        Path path = Paths.get(staticRoot, stringPath);
        if (!isPathValid(path)) {
            System.err.println("Invalid path: " + path);
            return null;
        }
        File file = path.toFile();
        if (!file.exists()) {
            System.err.println("File not found: " + path);
            return null;
        }
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
            scanner.close();
            return content.toString();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

}
