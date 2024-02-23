package app.kosoft.boardgames.handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class URLStaticHandler extends StaticHandler {
    public URLStaticHandler() {
        super(null);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        StaticHandler.staticFileHandle(exchange, path.substring(1));
    }
}
