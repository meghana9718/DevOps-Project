import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Main {
    private static StudentService service = new StudentService();

    public static void main(String[] args) throws IOException {
        int port = System.getenv("APP_PORT") != null ? Integer.parseInt(System.getenv("APP_PORT")) : 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        server.createContext("/", exchange -> {
            byte[] response = getFileContent("index.html").getBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        
        server.createContext("/style.css", exchange -> {
            byte[] response = getFileContent("style.css").getBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/css");
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        
        server.createContext("/script.js", exchange -> {
            byte[] response = getFileContent("script.js").getBytes();
            exchange.getResponseHeaders().set("Content-Type", "application/javascript");
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        
        server.createContext("/api/students", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            
            if ("GET".equals(exchange.getRequestMethod())) {
                String json = "[" + service.getStudents().stream()
                    .map(s -> "{\"id\":" + s.getId() + ",\"name\":\"" + s.getName() + "\"}")
                    .collect(Collectors.joining(",")) + "]";
                byte[] response = json.getBytes();
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining());
                String id = body.split("\"id\":")[1].split(",")[0];
                String name = body.split("\"name\":\"")[1].split("\"")[0];
                service.addStudent(Integer.parseInt(id), name);
                byte[] response = "{\"success\":true}".getBytes();
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
            }
            exchange.close();
        });
        
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + port);
    }
    
    private static String getFileContent(String filename) throws IOException {
        return new String(new FileInputStream("/app/public/" + filename).readAllBytes());
    }
}
