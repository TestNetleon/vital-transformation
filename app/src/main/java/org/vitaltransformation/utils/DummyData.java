package org.vitaltransformation.utils;

import org.vitaltransformation.model.Classes;
import org.vitaltransformation.model.Events;
import org.vitaltransformation.model.Ticket;
import org.vitaltransformation.model.UpcomingEvent;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<Events> createEvents() {
        ArrayList<Events> events = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Events onlineClass = new Events();
            onlineClass.setId("" + i);
            onlineClass.setTime("07, Dec 2018 1:30 pm - 6:30 pm");
            onlineClass.setLocation("Los Angeles");
            if (i % 3 == 0) {
                onlineClass.setTitle("Online Classes Title Long Text For Testing 123 " + i);
            } else {
                onlineClass.setTitle("Online Classes " + i);
            }
            onlineClass.setImage("" + i);
            events.add(onlineClass);
        }
        return events;
    }

    public static ArrayList<UpcomingEvent> createLatestEvents() {
        ArrayList<UpcomingEvent> events = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            UpcomingEvent onlineClass = new UpcomingEvent();
            onlineClass.setId("" + i);
            onlineClass.setDate("25");
            onlineClass.setMonth("Dec");
            onlineClass.setTime("10:50 AM");
            onlineClass.setEvent("New Seminar");
            onlineClass.setLocation("Los Angeles");
            events.add(onlineClass);
        }
        return events;
    }

    public static ArrayList<Classes> createClasses() {
        ArrayList<Classes> classes = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            Classes onlineClass = new Classes();
            onlineClass.setId("" + i);
            onlineClass.setClasses("" + i);
            if (i % 3 == 0) {
                onlineClass.setTitle("Online Classes Title Long Text For Testing 123 " + i);
            } else {
                onlineClass.setTitle("Online Classes " + i);
            }
            onlineClass.setImage("" + i);
            classes.add(onlineClass);
        }
        return classes;
    }

    public static ArrayList<Classes> createLatestClasses() {
        ArrayList<Classes> classes = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            Classes onlineClass = new Classes();
            onlineClass.setId("" + i);
            onlineClass.setClasses("" + i);
            onlineClass.setDuration("Duration - 1:10:45 min / Total secession - 10");
            if (i % 3 == 0) {
                onlineClass.setTitle("Online Classes Title Long Text For Testing 123 " + i);
            } else {
                onlineClass.setTitle("Online Classes " + i);
            }
            onlineClass.setImage("" + i);
            classes.add(onlineClass);
        }
        return classes;
    }

    public static ArrayList<Ticket> createTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Ticket ticket = new Ticket();
            ticket.setId("" + i);
            ticket.setCount(5);
            ticket.setPrice(18);
            ticket.setTitle("Friday Night Dinner");
            ticket.setImage("" + i);
            tickets.add(ticket);
        }
        return tickets;
    }

}
