package Quotes;

// QuoteReceiver.java
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class QuoteReceiver {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(4445)) {
            byte[] buffer = new byte[2048]; // Ökad bufferstorlek för längre citat
            System.out.println("Mottagare startad. Väntar på George Carlin-citat...");
            System.out.println("------------------------------------------------");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(
                        packet.getData(), 0, packet.getLength()
                );

                System.out.println("\nMottaget Carlin-citat:");
                System.out.println("\"" + received + "\"");
                System.out.println("------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Mottagarfel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

