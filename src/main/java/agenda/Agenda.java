package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    private ArrayList<Event> events = new ArrayList<Event>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        if (e == null) {
            throw new IllegalArgumentException("L'évenement ne peut pas être nul.");
        }
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> eventsInDay = new ArrayList<>();
        for (Event event : events) {
            if (event.isInDay(day)) {
                eventsInDay.add(event);
            }
        }
        return eventsInDay;
    }

    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     *
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        List<Event> eventsInTitle = new ArrayList<>();
        for (Event e : events) {
            if (e.getTitle() == title) {
                eventsInTitle.add(e);
            }

        }
        return eventsInTitle;
    }

    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     *
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        LocalDate dateEvent = e.getStart().toLocalDate();
        List<Event> eventDay = this.eventsInDay(dateEvent);
        LocalDateTime eventEnd = e.getStart().plus(e.getDuration());
        boolean isFreeFor;
        for (Event e2 : eventDay){
            LocalDateTime thisEventEnd = e2.getStart().plus(e2.getDuration());
            if(e2.getStart().isBefore(eventEnd) && e.getStart().isBefore(eventEnd)){
                isFreeFor = false;
            }
        }
        isFreeFor = true;
        return isFreeFor;
    }
}
