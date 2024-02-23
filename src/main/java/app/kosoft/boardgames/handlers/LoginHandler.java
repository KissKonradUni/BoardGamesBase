package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.SessionManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Date;

public class LoginHandler extends StaticHandler {
    public LoginHandler() {
        super("login.html");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            try {
                String[] requestBodyValues = new String(exchange.getRequestBody().readAllBytes()).split("&");
                String username = requestBodyValues[0].split("=")[1];
                String password = requestBodyValues[1].split("=")[1];

                String sessionID = SessionManager.tryLogin(username, password);
                if (sessionID != null) {
                    Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
                    exchange.getResponseHeaders().set("Set-Cookie",
                            "sessionID=" + sessionID + "; Expires=" + expirationDate + "; Secure;");
                    exchange.getResponseHeaders().set("Location", "/index");
                    // 302 is the status code for redirection
                    exchange.sendResponseHeaders(302, -1);
                } else {
                    exchange.getResponseHeaders().set("Location", "/login");
                    exchange.sendResponseHeaders(302, -1);
                }
            } catch (Exception e) {
                System.err.println("Invalid login POST request: " + e.getMessage());
            }
        } else
            super.handle(exchange);
    }
}
