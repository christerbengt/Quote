package Quotes;

// QuoteSender.java
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuoteSender {
    public static void main(String[] args) {
        String[] quotes = {
                "Det är som man brukar säga - om du inte kan slå dem, håll med dem tills du får chansen att slå dem.",
                "Vi har mångdubblat våra ägodelar, men reducerat våra värderingar. Vi pratar för mycket, älskar för sällan och hatar för ofta.",
                "Det finns ingen sådan sak som rättigheter. De är påhittade. Vi hittade på dem. Ja, i detta land har du rättigheter. Tyvärr har många idioter samma rättigheter som du.",
                "Religion är som ett par skor. Hitta ett par som passar dig, men tvinga dem inte på andra.",
                "Tänk på hur dum genomsnittsmänniskan är, och inse sedan att hälften av dem är ännu dummare.",
                "Om Gud hade tänkt att vi skulle flyga hade han gjort det lättare att ta sig till flygplatsen.",
                "Det roliga med regleringar är att de reglerar reguljära människor, som följer reglerna ändå, medan de oregelbundna människorna som orsakar alla problem bara ignorerar dem.",
                "Jag har lika mycket auktoritet som påven. Jag menar, jag har en stor hatt och en märklig cape. Har du sett min stora hatt och märkliga cape?",
                "Folk som ser livet som någonting som ska 'uppskattas' borde verkligen prova att slappna av lite, för det är bara sex och en halv miljard av oss som går omkring på denna döende planet.",
                "När du föds i denna värld får du ett biljettpaket till cirkusen. När du föds i USA får du ett främre parkett-pass till 'The Freak Show'."
        };

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            int port = 4445;
            int quoteIndex = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            System.out.println("Sändare startad. Skickar George Carlin-citat varje minut...");

            while (true) {
                String quote = quotes[quoteIndex];
                String timestamp = LocalDateTime.now().format(formatter);
                String messageWithTime = timestamp + " - " + quote;
                byte[] buffer = messageWithTime.getBytes();

                DatagramPacket packet = new DatagramPacket(
                        buffer, buffer.length, address, port
                );

                socket.send(packet);
                System.out.println("Skickat citat (" + timestamp + "): " + quote);

                quoteIndex = (quoteIndex + 1) % quotes.length;
                Thread.sleep(60000); // Vänta en minut
            }
        } catch (Exception e) {
            System.out.println("Sändarfel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}