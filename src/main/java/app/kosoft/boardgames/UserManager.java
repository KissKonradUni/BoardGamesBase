package app.kosoft.boardgames;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages user accounts.
 */
public class UserManager {
    private static final HashMap<String, User> users = new HashMap<>();
    static {
        loadUsers();
    }

    /**
     * Registers a new user with the given username and password.
     * Hashes the password with sha256 before storing it.
     * @param username The username.
     * @param password The password in plain text.
     * @return True if the user was successfully registered, false if the username is already taken.
     */
    public static boolean registerUser(String username, String password) {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return addUser(new User(username, hashedPassword));
    }

    public static boolean addUser(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public static void removeUser(String username) {
        users.remove(username);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static void saveUsers() {
        FileManager.saveUsers(users);
    }

    public static void loadUsers() {
        ArrayList<User> userList = FileManager.loadUsers();
        for (User user : userList) {
            users.put(user.getUsername(), user);
        }
    }

}
