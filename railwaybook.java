import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class railwaybook{
    private static Map<String, Integer> availableSeats = new HashMap<>();
    private static Map<String, Integer> reservedSeats = new HashMap<>();

    public static void main(String[] args) {
        initializeAvailableSeats();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Reservation System!");
            System.out.println("1. Reserve a seat");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Check seat availability");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    reserveSeat(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    checkAvailability();
                    break;
                case 4:
                    System.out.println("Thank you for using the Reservation System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void initializeAvailableSeats() {
        availableSeats.put("A1", 1);
        availableSeats.put("A2", 1);
        availableSeats.put("B1", 2);
        availableSeats.put("B2", 2);
        availableSeats.put("C1", 3);
        availableSeats.put("C2", 3);
    }

    private static void reserveSeat(Scanner scanner) {
        System.out.print("Enter the seat number: ");
        String seatNumber = scanner.next();

        if (!availableSeats.containsKey(seatNumber)) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (reservedSeats.containsKey(seatNumber)) {
            System.out.println("Seat already reserved!");
            return;
        }

        int seatType = availableSeats.get(seatNumber);
        reservedSeats.put(seatNumber, seatType);
        availableSeats.remove(seatNumber);

        System.out.println("Seat " + seatNumber + " reserved successfully!");
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter the seat number to cancel reservation: ");
        String seatNumber = scanner.next();

        if (!reservedSeats.containsKey(seatNumber)) {
            System.out.println("Invalid seat number or not reserved!");
            return;
        }

        int seatType = reservedSeats.get(seatNumber);
        availableSeats.put(seatNumber, seatType);
        reservedSeats.remove(seatNumber);

        System.out.println("Reservation for seat " + seatNumber + " canceled successfully!");
    }

    private static void checkAvailability() {
        System.out.println("Available Seats:");
        
        for (Map.Entry<String, Integer> entry : availableSeats.entrySet()) {
            String seatNumber = entry.getKey();
            int seatType = entry.getValue();
            
            System.out.println("Seat: " + seatNumber + ", Type: " + getSeatTypeName(seatType));
        }
    }
    
    private static String getSeatTypeName(int type) {
      switch (type) {
          case 1:
              return "Window";
          case 2:
              return "Aisle";
          case 3:
              return "Middle";
          default:
              return "";
      }
  }
}