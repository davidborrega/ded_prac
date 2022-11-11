package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;



public class OrganizingEntity {

    private int id;

    private String name;

    private String description;

    private LinkedList<SportEvent> sportEvents;

    public OrganizingEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sportEvents = new LinkedList<SportEvent>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addSportEvent(SportEvent sportEvent) {
        this.sportEvents.insertEnd(sportEvent);
    }

    public Iterator<SportEvent> getSportEvents() {
        return this.sportEvents.values();
    }

    public int numEvents() {
        return this.sportEvents.size();
    }

}
