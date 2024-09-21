import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java TCPServer.java <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started and listening on port " + port);

        Socket clientSocket = serverSocket.accept(); 
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String input = in.readLine();

        if (validateInput(input)) {
            String transformedString = reverseAndChangeCase(input);
            out.println(transformedString); 
        } else {
            out.println("Invalid input: Please provide a string with a-z, A-Z or empty space only (up to 80 characters).");
        }

        clientSocket.close();
        System.out.println("Client stopped.");
        serverSocket.close();
        System.out.println("Server stopped.");
    }

    // Reverse the string and change case
    private static String reverseAndChangeCase(String input) {
        StringBuilder reversed = new StringBuilder(input).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (Character.isUpperCase(c)) {
                reversed.setCharAt(i, Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                reversed.setCharAt(i, Character.toUpperCase(c));
            }
        }
        return reversed.toString();
    }

    // Validate input: length up to 80 and only a-z, A-Z or empty space
    private static boolean validateInput(String input) {
        return input.length() <= 80 && input.matches("[a-zA-Z ]+");
    }
}
