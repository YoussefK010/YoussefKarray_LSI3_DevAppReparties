package Client;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            InetAddress serveur = InetAddress.getByName("localhost");
            int port = 1234;

            System.out.print("Nom d'utilisateur : ");
            String nom = sc.nextLine();

            while (true) {
                String texte = sc.nextLine();
                if (texte.equalsIgnoreCase("quit")) break;

                String message = "[" + nom + "] : " + texte;
                byte[] data = message.getBytes(StandardCharsets.UTF_8);
                socket.send(new DatagramPacket(data, data.length, serveur, port));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}