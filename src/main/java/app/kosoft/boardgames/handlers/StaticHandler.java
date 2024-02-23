package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.FileManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StaticHandler implements HttpHandler {
    private final String filePath;

    public StaticHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        staticFileHandle(exchange, filePath);
    }

    public static void staticFileHandle(HttpExchange exchange, String filePath) throws IOException {
        String response = FileManager.getTextFileContent(filePath);
        if (response == null) {
            response = FileManager.getTextFileContent("404.html");
        }
        assert response != null;
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.close();
    }

}
