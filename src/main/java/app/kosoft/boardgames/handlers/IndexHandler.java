package app.kosoft.boardgames.handlers;

import app.kosoft.boardgames.FileManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class IndexHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = FileManager.getTextFileContent("index.html");
        if (response == null) {
            response = "404 Not Found";
        }
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.close();
    }

}
