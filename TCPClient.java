import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java TCPClient.java <server-ip> <port>");
            return;
        }

        String serverIP = args[0];
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(serverIP, port);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Enter text: ");
        String input = userInput.readLine();

        if (input.length() > 80 || !input.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid input: Please provide a string with a-z or A-Z only (up to 80 characters).");
        } else {
            out.println(input); 
            System.out.println("Response from server: " + in.readLine()); 
        }

        socket.close();
        System.out.println("Server stopped.");
        System.out.println("Client stopped.");
    }
}
