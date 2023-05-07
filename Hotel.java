package bg.tu_varna.sit.b3.f21621559;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Guest> guests;
    private List<UnavailableDate> unavailableDates;

    public Hotel() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        unavailableDates = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void addUnavailableDate(UnavailableDate date) {
        unavailableDates.add(date);
    }

    public List<Room> getAvailableRooms(Date checkDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.checkAvailability(checkDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void checkoutRoom(Room room) {
        room.checkOut();
    }

    public List<String> getReport(Date fromDate, Date toDate) {
        List<String> report = new ArrayList<>();
        for (Room room : rooms) {
            int daysUsed = 0;
            for (Guest guest : room.getGuests()) {
                if (guest.getCheckInDate().compareTo(toDate) <= 0 && guest.getCheckOutDate().compareTo(fromDate) >= 0) {
                    long milliseconds = Math.min(guest.getCheckOutDate().getTime(), toDate.getTime()) - Math.max(guest.getCheckInDate().getTime(), fromDate.getTime());
                    daysUsed += (int) (milliseconds / (1000 * 60 * 60 * 24));
                }
            }
            if (daysUsed > 0) {
                report.add("Room " + room.getRoomNumber() + " was used for " + daysUsed + " days.");
            }
        }
        return report;
    }

    public Room findRoom(int beds, Date checkInDate, Date checkOutDate, boolean preferFewerBeds) {
        List<Room> availableRooms = getAvailableRooms(checkInDate);
        if (availableRooms.isEmpty()) {
            return null;
        }
        Room bestRoom = availableRooms.get(0);
        for (Room room : availableRooms) {
            if (room.getBeds() >= beds) {
                if (preferFewerBeds) {
                    if (room.getBeds() < bestRoom.getBeds()) {
                        bestRoom = room;
                    }
                } else {
                    if (room.getBeds() > bestRoom.getBeds()) {
                        bestRoom = room;
                    }
                }
            }
        }
        if (bestRoom.checkIn(checkInDate, checkOutDate, "", null)) {
            return bestRoom;
        }
        return null;
    }

    public Room findEmergencyRoom(int beds, Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = getAvailableRooms(checkInDate);
        if (availableRooms.isEmpty()) {
            return null;
        }
        for (Room room : availableRooms) {
            if (room.getBeds() >= beds) {
                if (room.checkIn(checkInDate, checkOutDate, "Emergency room", null)) {
                    return room;
                }
            }
        }
        for (int i = 0; i < availableRooms.size() - 1; i++) {
            for (int j = i + 1; j < availableRooms.size(); j++) {
                Room room1 = availableRooms.get(i);
                Room room2 = availableRooms.get(j);
                if (room1.getBeds() + room2.getBeds() >= beds) {
                    Date newCheckInDate = checkInDate;
                    Date newCheckOutDate = checkOutDate;
                    if (room1.checkAvailability(checkInDate) && room2.checkAvailability(checkInDate)) {
                        room1.checkIn(checkInDate, checkOutDate, "Emergency room", null);
                        room2.checkIn(checkInDate, checkOutDate, "Emergency room", null);
                        return room1;
                    } else if (room1.checkAvailability(checkInDate) && room2.checkAvailability(checkOutDate)) {
                        room1.checkIn(checkInDate, checkOutDate, "Emergency room", null);
                        room2.checkIn(checkOutDate, checkOutDate, "Emergency room", null);
                        return room1;
                    } else if (room1.checkAvailability(checkOutDate) && room2.checkAvailability(checkInDate)) {
                        room1.checkIn(checkOutDate, checkOutDate, "Emergency room", null);
                        room2.checkIn(checkInDate, checkOutDate, "Emergency room", null);
                        return room1;
                    } else if (room1.checkAvailability(checkOutDate) && room2.checkAvailability(checkOutDate)) {
                        room1.checkIn(checkOutDate, checkOutDate, "Emergency room", null);
                        room2.checkIn(checkOutDate, checkOutDate, "Emergency room", null);
                        return room1;
                    }
                }
            }
        }
        return null;
    }
}
