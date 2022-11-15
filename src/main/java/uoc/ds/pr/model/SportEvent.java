package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.SportEvents4Club;

import java.time.LocalDate;
import java.util.Comparator;

public class SportEvent {

    private String eventId;

    private int orgId;

    private String description;

    private double max;

    private LocalDate startDate;

    private LocalDate endDate;

    private QueueArrayImpl<Player> enrollments;

    private LinkedList<Rating> ratings;

    public SportEvent(String eventId, int orgId, String description, double max, LocalDate startDate, LocalDate endDate) {
        this.setEventId(eventId);
        this.setOrgId(orgId);
        this.setDescription(description);
        this.setMax(max);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.ratings = new LinkedList<Rating>();
        this.enrollments = new QueueArrayImpl<Player>();
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Iterator<uoc.ds.pr.model.Rating> getRatings() {
        return this.ratings.values();
    }

    public int getTotalRatings() {
        return this.ratings.size();
    }

    public void addEnrollment(Player player) {
        this.enrollments.add(player);
    }

    public int getTotalEnrollments() {
        return this.enrollments.size();
    }

    public void addRating(Rating rating) {
        this.ratings.insertEnd(rating);
    }

    /* This method returns total of substitutes. 
        To calculate the substitutes we have to know the maximum number of enrollments to this sport event and the current number of enrollments.
    */
    public int getTotalSubstitutes() {
        int substitutes = 0;
        if (this.getTotalEnrollments() > this.getMax()) {
            substitutes = (int) (this.getTotalEnrollments() - this.getMax());
        }
        return substitutes;
    }

    public boolean isFull() {
        return this.getTotalEnrollments() > this.getMax();
    }

    public static Comparator<String> COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String id1, String id2) {
            return id1.compareTo(id2);
        }
    };

    public static Comparator<SportEvent> COMPARATOR_BEST_SPORTEVENT = new Comparator<SportEvent>() {
        @Override
        public int compare(SportEvent se1, SportEvent se2) {
            return se1.rating().compareTo(se2.rating());
        }
    };

    // This method returns the average rating for this event.
    public Double rating() {
        int numberOfRatings = this.getTotalRatings();
        if (numberOfRatings == 0) {
            return Double.valueOf(0);
        }
        int rating = 0;
        for (Iterator<Rating> it = this.getRatings(); it.hasNext();) {
            rating += it.next().rating().getValue();
        }
        return (double) rating / (double) numberOfRatings;
    }

}
