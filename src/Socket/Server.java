package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 9090;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected.");

                // Tạo mới và thêm ClientHandler vào danh sách clients
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // Đọc tên client từ client đầu tiên gửi tới
                clientName = reader.readLine();
                System.out.println(clientName + " Connected.");

                String message;
                while ((message = reader.readLine()) != null) {
                    broadcastMessage(clientName + ": " + message);
                }
            } catch (IOException e) {
                // Xử lý khi có lỗi xảy ra
                //System.err.println("Lỗi khi đọc từ client: " + e.getMessage());
            } finally {
                // Xóa client ra khỏi danh sách khi client ngắt kết nối
                clients.remove(this);
                System.out.println(clientName + " disconnected.");
                try {
                    socket.close(); // Đóng socket của client
                } catch (IOException e) {
                    //System.err.println("Lỗi khi đóng socket: " + e.getMessage());
                }
            }
        }

        public void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }
}
