package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;

import java.time.LocalDate;

public class Player {

    private String id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private LinkedList<SportEvent> sportEvents;

    private boolean substitute;

    public Player(String id, String name, String surname, LocalDate dateOfBirth) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.sportEvents = new LinkedList<SportEvent>();
        this.substitute = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public boolean isSubstitute() {
        return this.substitute;
    }

    public void addSportEvent(SportEvent sportEvent) {
        this.sportEvents.insertEnd(sportEvent);
    }

    public int numEvents() {
        return this.sportEvents.size();
    }

    public Iterator<SportEvent> getSportEvents() {
        return this.sportEvents.values();
    }
}