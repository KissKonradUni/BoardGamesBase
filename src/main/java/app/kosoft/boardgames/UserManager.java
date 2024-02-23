package app.kosoft.boardgames;

import java.util.HashMap;

public class UserManager {
    private static final HashMap<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    /**
     * Attempts to log in the user with the given username and password.
     * @param username The username.
     * @param password The password.
     * @return The session ID if the login was successful, or null if the login failed.
     */
    public static String tryLogin(String username, String password) {
        User user = getUser(username);
        if (user != null && user.isPasswordCorrect(password))
            return SessionManager.createSession(user);
        return null;
    }

}
