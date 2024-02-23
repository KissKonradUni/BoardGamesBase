package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.User;
import app.kosoft.boardgames.UserManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class RegisterHandler extends AuthenticatedStaticHandler {
    public RegisterHandler() {
        super("register.html");
    }

    @Override
    public void handleAuthenticated(HttpExchange exchange, User user, String sessionID) throws IOException {
        exchange.getResponseHeaders().set("Location", "/index");
        exchange.sendResponseHeaders(302, -1);
    }

    @Override
    public void handleUnauthenticated(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            try {
                String[] requestBodyValues = new String(exchange.getRequestBody().readAllBytes()).split("&");
                String username = requestBodyValues[0].split("=")[1];
                String password = requestBodyValues[1].split("=")[1];
                if (UserManager.registerUser(username, password)) {
                    exchange.getResponseHeaders().set("Location", "/login");
                    exchange.sendResponseHeaders(302, -1);
                } else {
                    exchange.getResponseHeaders().set("Location", "/register");
                    exchange.sendResponseHeaders(302, -1);
                }
            } catch (Exception e) {
                System.err.println("Invalid register POST request: " + e.getMessage());
            }
        } else
            super.handleAuthenticated(exchange, null, null);
    }
}
