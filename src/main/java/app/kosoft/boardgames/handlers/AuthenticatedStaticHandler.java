package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AuthenticatedStaticHandler implements AuthenticatedHandler {
    private final String filePath;

    public AuthenticatedStaticHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void handleAuthenticated(HttpExchange exchange, User user, String sessionID) throws IOException {
        StaticHandler.staticFileHandle(exchange, filePath);
    }

    @Override
    public void handleUnauthenticated(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Location", "/login");
        exchange.sendResponseHeaders(302, -1);
    }
}
