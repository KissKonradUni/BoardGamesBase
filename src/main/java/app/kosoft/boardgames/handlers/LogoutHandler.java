package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.SessionManager;
import app.kosoft.boardgames.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LogoutHandler implements AuthenticatedHandler {
    @Override
    public void handleAuthenticated(HttpExchange exchange, User user, String sessionID) throws IOException {
        SessionManager.logOut(sessionID);
        exchange.getResponseHeaders().set("Location", "/index");
        exchange.sendResponseHeaders(302, -1);
    }

    @Override
    public void handleUnauthenticated(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Location", "/index");
        exchange.sendResponseHeaders(302, -1);
    }
}
