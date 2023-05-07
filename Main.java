package bg.tu_varna.sit.b3.f21621559;
import java.util.List;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Room room = new Room(101, 2, 50.0);

        Date checkInDate = new Date();
        Date checkOutDate = new Date(checkInDate.getTime() + (1000 * 60 * 60 * 24 * 3)); // add 3 days to check in date

        boolean isCheckInSuccessful = room.checkIn(checkInDate, checkOutDate, "Test note", null);

        if (isCheckInSuccessful) {
            System.out.println("Check-in successful!");

            // Print guest details
            List<Guest> guests = room.getGuests();
            for (Guest guest : guests) {
                System.out.println("Name: " + guest.getName() + " " + guest.getSurname());
                System.out.println("Check-in date: " + guest.getCheckInDate());
                System.out.println("Check-out date: " + guest.getCheckOutDate());
                System.out.println("Number of guests: " + guest.getNumGuests());
                System.out.println("Note: " + guest.getNote());
            }
        } else {
            System.out.println("Check-in failed!");
        }
    }
}
