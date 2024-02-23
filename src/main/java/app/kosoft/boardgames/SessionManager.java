package app.kosoft.boardgames;

import java.util.HashMap;

/**
 * Manages user sessions.
 */
public class SessionManager {
    private static final HashMap<String, User> sessions = new HashMap<>();

    /**
     * Returns the user associated with the given session ID.
     * If the session ID is invalid, returns null.
     * This method is also used to check if a user is logged in.
     * @param sessionId The session ID.
     * @return The user associated with the session ID, or null if the session ID is invalid.
     */
    public static User getUserFromSessionID(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * Attempts to log in the user with the given username and password.
     * @param username The username.
     * @param password The password.
     * @return The session ID if the login was successful, or null if the login failed.
     */
    public static String tryLogin(String username, String password) {
        User user = UserManager.getUser(username);
        if (user != null && user.isPasswordCorrect(password))
            return SessionManager.createSession(user);
        return null;
    }

    /**
     * Creates a new session for the given user.
     * @param user The user to create a session for.
     * @return The session ID.
     */
    private static String createSession(User user) {
        String sessionId = generateSessionId();
        sessions.put(sessionId, user);
        return sessionId;
    }

    /**
     * Generates a random session ID.
     * It's a random 16-character string.
     * @return A random session ID.
     */
    private static String generateSessionId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    /**
     * Logs out the user associated with the given session ID.
     * @param sessionId The session ID.
     */
    public static void logOut(String sessionId) {
        sessions.remove(sessionId);
    }

}
