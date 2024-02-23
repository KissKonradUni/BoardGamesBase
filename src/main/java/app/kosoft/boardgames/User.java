package app.kosoft.boardgames;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPasswordCorrect(String password) {
        String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return this.password.equals(hashPassword);
    }
}
