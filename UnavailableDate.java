package bg.tu_varna.sit.b3.f21621559;
import java.util.Date;

public class UnavailableDate {
    private Date startDate;
    private Date endDate;
    private String note;

    public UnavailableDate(Date startDate, Date endDate, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }
}
