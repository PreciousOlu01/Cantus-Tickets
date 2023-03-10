package ControllerClass;

import ModelClass.Events;
import ModelClass.Ticket;
import ModelClass.Users;
import SercviceClass.eventService;
import SercviceClass.ticketService;
import SercviceClass.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ControllerEndPoint {
    userService serviceUser;
    eventService serviceEvent;
    ticketService serviceTicket;

    public ControllerEndPoint(){
        serviceUser =new userService();
        serviceEvent = new eventService();
        serviceTicket = new ticketService();
    }

    public Javalin startApp(){
        Javalin app = Javalin.create();

        app.post("/register", this::postRegisterHandler);
        app.post("/login", this:: postLoginHandler);
        app.post("/events", this::postEventsNames);
        app.get("/events", this::getAllEventHandler);
        app.patch("/events/<event_id>",this::patchEventHandler);
        app.delete("/events/<event_id>", this::deleteEventHandler);

//        app.delete("/users", this::deleteUserHandler);
     //   app.get("/events/<event_id>", this::getEventByIdHandler);
//        app.get("/users/<users_id>/tickets", this::getEventByUserIdHandler);

        return app;
    }

    /*This handler enables users to register their account*/
    private void postRegisterHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Users users = map.readValue(ctx.body(), Users.class);
        Users createUser = serviceUser.addUsers(users);
        if(createUser!=null){
            ctx.json(map.writeValueAsString(createUser));
            ctx.status(200);
        }
        else {
            ctx.status(400);
        }
    }

    /*post endpoint to create login details*/
    private void postLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Users users = map.readValue(ctx.body(), Users.class);
        Users createLogin = serviceUser.userLogin(users);
        if(createLogin != null){
            ctx.json(map.writeValueAsString(createLogin));
            ctx.status(200);
        }
        else{
            ctx.status(401);
        }
    }

    /*this method enable Administrative user make a post request to the event table*/
    private void postEventsNames(Context ctx) throws  JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Events events = map.readValue(ctx.body(), Events.class);
        Events createEvent = serviceEvent.addEvents(events);
        if(createEvent!= null){
            ctx.json(map.writeValueAsString(createEvent));
            ctx.status(200);
        }
        else {
            ctx.status(400);
        }
    }

    /*user should be able to get all events using the 'get endpoint'*/
    private void getAllEventHandler(Context ctx){
        List<Events> events = serviceEvent.viewAllEvents();
        //ctx.json(events);
        if(events==null){
            ctx.status(400);
        }
        else{
            ctx.json(events);
        }
    }

    private void patchEventHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Events mapEvent = map.readValue(ctx.body(), Events.class);
        ctx.contentType("application/Json");
        int eventId = Integer.parseInt(ctx.pathParam("event_id"));

        Events eventUpdate = serviceEvent.getUpdate(eventId, mapEvent);
        if(eventUpdate==null){
            ctx.status(400);
        }
        else {
            ctx.json(map.writeValueAsString(eventUpdate));
        }
    }

    private void deleteEventHandler(Context ctx){
        int delId = Integer.parseInt(ctx.pathParam("event_id"));
        Events delEvent = serviceEvent.deleteEvents(delId);
        if(delEvent == null){
            ctx.status(400);
        }
        else{
            ctx.json(delEvent);
            ctx.status(200);
        }
    }

    private void deleteUserHandler(Context ctx)throws JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Users users = map.readValue(ctx.body(), Users.class);
        boolean isDelete = serviceUser.dele(users);
        if(!isDelete){
            ctx.status(400);
        }
        else{
            ctx.status(200);
        }
    }

    /*user should be able to get a particular event name using event_id*/
//    private void getEventByIdHandler(Context ctx){
//        int event_ids = Integer.parseInt(ctx.pathParam("event_id"));
//        Events events = serviceEvent.viewEventById(event_ids);
//        if(events==null){
//            ctx.status(400);
//        }
//        else{
//            ctx.json(serviceEvent.viewEventById(event_ids));
//            ctx.status(200);
//        }
//    }
    /*This code is correct but won't work for some technical issues*/
//    private void getEventByIdHandler(Context ctx)throws JsonProcessingException{
//        ObjectMapper map = new ObjectMapper();
////        String strId = ctx.pathParam("event_id");
////        int id= (int)Integer.valueOf(strId);
//        int id = Integer.parseInt(ctx.pathParam("event_id"));
//        Events events = serviceEvent.viewEventById(id);
//        if(events==null){
//            ctx.status(400);
//        }
//        else{
//            ctx.json(map.writeValueAsString(events));
//            ctx.status(200);
//        }
//    }
    /*user should be able to access their event history using their user_id*/
//    private void getEventByUserIdHandler(Context ctx) throws JsonProcessingException{
//        int userId = Integer.parseInt(ctx.pathParam("user_id"));
//        List<Ticket>tickets = serviceTicket.getTicketByUserId(userId);
//        ctx.json(tickets);
//    }

}
