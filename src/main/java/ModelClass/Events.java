package ModelClass;

import java.util.Objects;

public class Events {
    private int id;
    private String event_name;
    private float ticketPrice;
    private int ticket_limit;
    private String date;
    private String time;

    public Events(){};

    public Events(int id, String event_name, float ticketPrice, int ticket_limit, String date, String time){
        this.id = id;
        this.event_name = event_name;
        this.ticketPrice = ticketPrice;
        this.ticket_limit = ticket_limit;
        this.date = date;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicket_limit() {
        return ticket_limit;
    }

    public void setTicket_limit(int ticket_limit) {
        this.ticket_limit = ticket_limit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events = (Events) o;
        return id == events.id && Float.compare(events.ticketPrice, ticketPrice) == 0 && ticket_limit == events.ticket_limit && Objects.equals(event_name, events.event_name) && Objects.equals(date, events.date) && Objects.equals(time, events.time);
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", event_name='" + event_name + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", ticket_limit=" + ticket_limit +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
