package bg.tu_varna.sit.b3.f21621559;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private int roomNumber;
    private int beds;
    private double pricePerNight;
    private List<Guest> guests;
    private List<UnavailableDate> unavailableDates;
    private List<String> activities;

    public Room(int roomNumber, int beds, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        guests = new ArrayList<>();
        unavailableDates = new ArrayList<>();
        activities = new ArrayList<>();
    }

    public boolean checkAvailability(Date checkDate) {
        for (UnavailableDate date : unavailableDates) {
            if (date.getStartDate().compareTo(checkDate) <= 0 && date.getEndDate().compareTo(checkDate) >= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIn(Date checkInDate, Date checkOutDate, String note, Integer numGuests) {
        if (numGuests == null) {
            numGuests = beds;
        }
        if (checkAvailability(checkInDate) && checkAvailability(checkOutDate)) {
            Guest guest = new Guest("", "", checkInDate, checkOutDate, numGuests, note);
            guests.add(guest);
            return true;
        }
        return false;
    }

    public void checkOut() {
        guests.remove(guests.size() - 1);
    }

    public void addActivity(String activity) {
        activities.add(activity);
    }

    public List<String> getActivities() {
        return activities;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<UnavailableDate> getUnavailableDates() {
        return unavailableDates;
    }
}
