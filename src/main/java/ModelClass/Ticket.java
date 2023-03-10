package ModelClass;

import java.util.Objects;

public class Ticket {
    private int id;
    private int event_id;
    private int user_id;

    public Ticket(){};

    public int getId() {
        return id;
    }

    public Ticket(int id, int event_id, int user_id){
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && event_id == ticket.event_id && user_id == ticket.user_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", event_id=" + event_id +
                ", user_id=" + user_id +
                '}';
    }
}
