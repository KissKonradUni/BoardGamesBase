package app.kosoft.boardgames;

import app.kosoft.boardgames.handlers.IndexHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        HttpServer server = null;

        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);

            IndexHandler handler = new IndexHandler();
            server.createContext("/", handler);
            server.createContext("/index", handler);

            server.setExecutor(null);
            server.start();

            System.out.println("Server started on port 8080");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}