package app.kosoft.boardgames.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class NotFoundHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // If the URL is /, redirect to /index
        if (exchange.getRequestURI().getPath().equals("/")) {
            exchange.getResponseHeaders().add("Location", "/index");
            exchange.sendResponseHeaders(302, -1);
        } else {
            StaticHandler.staticFileHandle(exchange, "404.html");
        }
    }

}
