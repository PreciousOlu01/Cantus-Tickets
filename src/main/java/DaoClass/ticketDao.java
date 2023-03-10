package DaoClass;

import ModelClass.Ticket;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ticketDao {
    /*insert into the tickets table*/
    public Ticket insertTicket(Ticket ticket){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "insert into tickets(user_fk, events_fk)values(?,?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            /*set the preparedStatement*/
            ps.setInt(1, ticket.getUser_id());
            ps.setInt(2, ticket.getEvent_id());
            ps.executeUpdate();

            ResultSet keyGen = ps.getGeneratedKeys();
            if(keyGen.next()){
                int generatedId = (int)keyGen.getLong(1);
                return new Ticket(generatedId, ticket.getUser_id(), ticket.getEvent_id());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*Get event name using foreign key*/
    public List<Ticket>getTicketsByGivenId(int ticket_id){
        Connection conn = ConnectionSingleton.getConnection();
        List<Ticket>ticketNumber = new ArrayList<>();
        try{
            String sql = "select * from tickets where events_fk=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket_id);

            ResultSet rSet = ps.executeQuery();
            while(rSet.next()){
                Ticket ticket = new Ticket(rSet.getInt("tickets_id"), rSet.getInt("user_fk"),
                rSet.getInt("events_fk"));
                ticketNumber.add(ticket);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return ticketNumber;
    }

    /*Get all event using user_fk id key*/
    public List<Ticket>getTicketsByUserId(int ticket_id){
        Connection conn = ConnectionSingleton.getConnection();
        List<Ticket>userTicket = new ArrayList<>();
        try{
            String sql = "select * from tickets where user_fk=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket_id);

            ResultSet rSet = ps.executeQuery();
            while(rSet.next()){
                Ticket ticket = new Ticket(rSet.getInt("tickets_id"), rSet.getInt("user_fk"),
                        rSet.getInt("events_fk"));
                userTicket.add(ticket);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userTicket;
    }
}
