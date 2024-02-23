package app.kosoft.boardgames;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {
    private static final String privateRoot;
    private static final String publicRoot;
    static {
        String workingDir = System.getProperty("user.dir");
        privateRoot = workingDir + "/private/";
        System.out.println("Private root: " + privateRoot);

        publicRoot = workingDir + "/public/";
        System.out.println("Public root: " + publicRoot);
    }

    public static boolean isStaticPathValid(Path path) {
        return path.startsWith(publicRoot);
    }

    public static String getTextFileContent(String stringPath) {
        Path path = Paths.get(publicRoot, stringPath);
        if (!isStaticPathValid(path)) {
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

    public static void saveUsers(HashMap<String, User> users) {
        File file = new File(privateRoot + "users.json");
        if (!file.exists()) try {
            if (!file.createNewFile())
                throw new Exception("Failed to create file");
        } catch (Exception e) {
            System.err.println("Error creating file: " + e.getMessage());
            return;
        }

        Gson gson = new Gson();
        String json = gson.toJson(users.values().toArray());
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<User> loadUsers() {
        File file = new File(privateRoot + "users.json");
        if (!file.exists()) {
            System.out.println("No users file found");
            return new ArrayList<>();
        }

        try {
            Scanner scanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine())
                content.append(scanner.nextLine());
            scanner.close();
            String json = content.toString();

            Gson gson = new Gson();
            User[] users = gson.fromJson(json, User[].class);
            return new ArrayList<>(Arrays.asList(users));
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
