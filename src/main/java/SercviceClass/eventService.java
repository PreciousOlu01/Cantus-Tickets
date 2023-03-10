package SercviceClass;

import DaoClass.eventDao;
import ModelClass.Events;
import ModelClass.Users;

import java.util.List;

public class eventService {
    public eventDao daoEvents;

    public eventService(){
        daoEvents = new eventDao();
    }

    public eventService(eventDao daoEvents){
        this.daoEvents = daoEvents;
    }

    /*view all events persisted in the events table*/
    public List<Events>viewAllEvents(){
        return daoEvents.getAllEvents();
    }

    /*To persist data in the events table, we have to check if the data already exist.
    * if it does not already exist then we persist data in the event table*/
    public Events addEvents(Events events){
       // Events eventCheck = daoEvents.getEventById(events.getId());

        if(events != null ){
            return daoEvents.insertEvents(events);
        }
        return null;
    }

    /*view specific events using eventId*/
    public Events viewEventById(int event_id){
        return daoEvents.getEventById(event_id);
    }

    public Events getUpdate(int id, Events events){
        Events eventId = daoEvents.getEventById(id);
        String event_name = events.getEvent_name();
        if(eventId != null && event_name != "" && events.getEvent_name().length()<255){
            daoEvents.getEventUpdate(id, events);
            return eventId;
        }
        return null;
    }

    public  Events deleteEvents(int id){
        Events del = daoEvents.getEventById(id);
        if(del != null){
            daoEvents.deleteEvent(id);
            return del;
        }
        return  null;
    }
//    public boolean dele(Users user){
//        if(user.getUserName().length()==0 && user.getPassword().length()==0)return false;
//        return daoEvents.deleteMethod(user);
//    }
}
