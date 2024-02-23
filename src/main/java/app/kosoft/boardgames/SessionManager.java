package app.kosoft.boardgames;

import com.sun.istack.internal.Nullable;

import java.util.HashMap;

public class SessionManager {
    private static final HashMap<String, User> sessions = new HashMap<>();

    /**
     * Returns the user associated with the given session ID.
     * If the session ID is invalid, returns null.
     * This method is also used to check if a user is logged in.
     * @param sessionId The session ID.
     * @return The user associated with the session ID, or null if the session ID is invalid.
     */
    @Nullable
    public static User getUser(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * Creates a new session for the given user.
     * @param user The user to create a session for.
     * @return The session ID.
     */
    public static String createSession(User user) {
        String sessionId = generateSessionId();
        sessions.put(sessionId, user);
        return sessionId;
    }

    /**
     * Generates a random session ID.
     * @return A random session ID.
     */
    private static String generateSessionId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    /**
     * Logs out the user associated with the given session ID.
     * @param sessionId The session ID.
     */
    private static void logOut(String sessionId) {
        sessions.remove(sessionId);
    }

}
