package DaoClass;

import ModelClass.Events;
import ModelClass.Users;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class eventDao {
    /*Insert into the event table to populate the table, this table auto-increment*/
    public Events insertEvents(Events events){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "insert into event(event_name, price, ticket_limit, event_date, event_time)values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, events.getEvent_name());
            preparedStatement.setFloat(2, events.getTicketPrice());
            preparedStatement.setInt(3, events.getTicket_limit());
            preparedStatement.setDate(4, Date.valueOf(events.getDate()));
            preparedStatement.setTime(5, Time.valueOf(events.getTime()));
            preparedStatement.executeUpdate();

            ResultSet rKey = preparedStatement.getGeneratedKeys();
            if(rKey.next()){
                int generateId = (int)rKey.getLong(1);
                return new Events(generateId, events.getEvent_name(),events.getTicketPrice(),events.getTicket_limit(),
                        events.getDate(), events.getTime());
            }
       }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
   }

   /*we should be able to see all events in event table, this method handles that*/
    public List<Events> getAllEvents(){
        Connection connection = ConnectionSingleton.getConnection();
        List<Events>eventDetails = new ArrayList<>();
        try{
            String sql = "select * from event;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Events events = new Events(rs.getInt("event_id"),rs.getString("event_name"),
                        rs.getFloat("price"), rs.getInt("ticket_limit"), String.valueOf(rs.getDate("event_date")),
                        String.valueOf(rs.getTime("event_time")));
                eventDetails.add(events);

            }
            return eventDetails;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*getEventById enable us get a specific event by the event id*/
    public Events getEventById(int id){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "select * from event where event_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Events events= new Events(rs.getInt("event_id"), rs.getString("event_name"),
                        rs.getFloat("price"),rs.getInt("ticket_limit"),
                        String.valueOf(rs.getDate("event_date")), String.valueOf(rs.getTime("event_time")));
                return events;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void getEventUpdate(int id, Events events){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "update event set event_name=? where event_id=?;";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, events.getEvent_name());
            ps.setInt(2, id);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Events deleteEvent(int id){
        Connection conn = ConnectionSingleton.getConnection();
        try{
            String sql = "delete from event where event_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
