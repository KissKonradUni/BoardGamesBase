package app.kosoft.boardgames;

import app.kosoft.boardgames.handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        HttpServer server = null;

        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);

            server.createContext("/", new NotFoundHandler());
            server.createContext("/index", new StaticHandler("index.html"));

            server.createContext("/register", new RegisterHandler());
            server.createContext("/login", new LoginHandler());
            server.createContext("/logout", new LogoutHandler());

            server.createContext("/secret", new AuthenticatedStaticHandler("secret.html"));
            server.createContext("/static/", new URLStaticHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("Server started on port 8080");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}