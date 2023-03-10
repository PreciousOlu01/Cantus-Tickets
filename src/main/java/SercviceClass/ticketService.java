package SercviceClass;

import DaoClass.ticketDao;
import ModelClass.Ticket;

import java.util.List;

public class ticketService {
    private ticketDao daoTicket;

    public ticketService(){
        daoTicket = new ticketDao();
    }

    public ticketService(ticketDao daoTicket){
        this.daoTicket = daoTicket;
    }

    /*Check if values are valid and if it exists*/
    public Ticket addTicket(Ticket ticket){
        if(ticket != null)
            return daoTicket.insertTicket(ticket);
        else
            return null;
    }

    public List<Ticket>getTicketByEventId(int id){
        return daoTicket.getTicketsByGivenId(id);
    }

    public List<Ticket>getTicketByUserId(int id){
        return daoTicket.getTicketsByUserId(id);
    }
}
