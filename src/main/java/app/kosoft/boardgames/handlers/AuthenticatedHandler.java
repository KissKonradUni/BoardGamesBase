package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.SessionManager;
import app.kosoft.boardgames.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public interface AuthenticatedHandler extends HttpHandler {
    @Override
    default void handle(HttpExchange exchange) throws IOException {
        String sessionID = exchange.getRequestHeaders().getFirst("Cookie");
        if (sessionID != null && sessionID.contains("sessionID=")) {
            sessionID = sessionID.split("sessionID=")[1].split(";")[0];
            User user = SessionManager.getUserFromSessionID(sessionID);
            if (user != null)
                handleAuthenticated(exchange, user, sessionID);
            else
                handleUnauthenticated(exchange);
        }
    }

    void handleAuthenticated(HttpExchange exchange, User user, String sessionID) throws IOException;

    void handleUnauthenticated(HttpExchange exchange) throws IOException;
}