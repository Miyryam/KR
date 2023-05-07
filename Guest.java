package bg.tu_varna.sit.b3.f21621559;
import java.util.Date;

public class Guest {
    private String name;
    private String surname;
    private int numGuests;
    private Date checkInDate;
    private Date checkOutDate;
    private String note;

    public Guest(String name, String surname, Date checkInDate, Date checkOutDate, int numGuests, String note) {
        this.name = name;
        this.surname = surname;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numGuests = numGuests;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getNote() {
        return note;
    }
}
