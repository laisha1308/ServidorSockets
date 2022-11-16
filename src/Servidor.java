import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static final int PUERTO = 5000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PUERTO);
        System.out.println("Empezando: " + serverSocket);
        try {
            Socket socket = serverSocket.accept();
            try {
                System.out.println("Conexión establecida: " + socket);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true) {
                    String mensaje = entrada.readLine();
                    if (mensaje.equals("FIN")) break;
                    System.out.println("Reproduciendo mensaje" + "\n" + " " + mensaje);
                    salida.println(mensaje);
                }
            } finally {
                System.out.println("Cerrando Servidor");
                socket.close();
            }
        } finally {
            serverSocket.close();
        }
    }
}