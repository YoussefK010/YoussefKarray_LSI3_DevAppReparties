package Serveur;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ServeurUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(null)) {
            // Liaison explicite avec InetSocketAddress
            socket.bind(new InetSocketAddress(1234));
            System.out.println("Serveur UDP en écoute sur le port 1234...");

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // bloquant

                String message = new String(packet.getData(), 0,
                        packet.getLength(), StandardCharsets.UTF_8);
                System.out.println("De " + packet.getAddress().getHostAddress()
                        + ":" + packet.getPort() + " -> " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}