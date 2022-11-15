package uoc.ds.pr;

import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.OrderedVector;
import uoc.ds.pr.util.OrderedVectorDictionary;

import java.time.LocalDate;

public class SportEvents4ClubImpl implements SportEvents4Club {

    // Set players
    private Player players[];
    private int numberOfPlayers;
    private Player mostActivePlayer;

    // Set array of organizing entities
    private OrganizingEntity organizingEntities[];
    private int numberOfOrganizingEntities;
    // Set files
    private QueueArrayImpl<File> files;
    private int numberOfFiles;
    private int numberOfRejectedFiles;
    // Set sport events
    private OrderedVectorDictionary<String, SportEvent> sportEvents;
    private OrderedVector<SportEvent> bestSportEvents;

    public SportEvents4ClubImpl() {
        // Initialize array of players
        this.players = new Player[MAX_NUM_PLAYER];
        this.numberOfPlayers = 0;
        // Initialize most active player pointer.
        this.mostActivePlayer = null;
        // Initialize array of organizingEntities;
        this.organizingEntities = new OrganizingEntity[MAX_NUM_ORGANIZING_ENTITIES];
        this.numberOfOrganizingEntities = 0;
        // Initialize sport events.
        this.sportEvents = new OrderedVectorDictionary<String, SportEvent>(MAX_NUM_SPORT_EVENTS, SportEvent.COMPARATOR);
        this.bestSportEvents = new OrderedVector<SportEvent>(MAX_NUM_SPORT_EVENTS, SportEvent.COMPARATOR_BEST_SPORTEVENT);
        // Initialize queue of files.
        this.files = new QueueArrayImpl<File>();
        this.numberOfFiles = 0;
        this.numberOfRejectedFiles = 0;
    }

    @Override
    public void addPlayer(String id, String name, String surname, LocalDate dateOfBirth) throws LimitExceededException {
        // Get the player from current array of players.
        Player player = this.getPlayer(id);
        if (player == null) {
            // If current array of players have the maximum number, throw custom exception.
            if (this.numberOfPlayers >= MAX_NUM_PLAYER) {
                throw new LimitExceededException();
            }
            // Save into array of players the new player element and increase player counter.
            this.players[this.numberOfPlayers] = new Player(id, name, surname, dateOfBirth);
            this.numberOfPlayers++;
        } else {
            // Update data.
            player.setName(name);
            player.setSurname(surname);
            player.setDateOfBirth(dateOfBirth);
        }
    }

    @Override
    public void addOrganizingEntity(int id, String name, String description) throws LimitExceededException {
        // Get the organizing entity from current array of organizing entities.
        OrganizingEntity organizingEntity = this.getOrganizingEntity(id);
        if (organizingEntity == null) {
            // If current array of organizing entities have the maximum number, throw custom exception.
            if (this.numberOfOrganizingEntities >= MAX_NUM_ORGANIZING_ENTITIES) {
                throw new LimitExceededException();
            }
            // Save into array of organizing entities the new player element and increase the counter.
            this.organizingEntities[this.numberOfOrganizingEntities] = new OrganizingEntity(id, name, description);
            this.numberOfOrganizingEntities++;
        } else {
            // Update data
            organizingEntity.setName(name);
            organizingEntity.setDescription(description);
        }
    }

    @Override
    public void addFile(String id, String eventId, int orgId, String description, Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) throws OrganizingEntityNotFoundException {
        // Check if the organization exists, if not exists, throw custom error.
        OrganizingEntity organizingEntity = this.getOrganizingEntity(orgId);
        if (organizingEntity == null) {
            throw new OrganizingEntityNotFoundException();
        }
        // Prepare and add file object into queue of files.
        File file = new File(id, eventId, orgId, description, type, resources, max, startDate, endDate);
        this.files.add(file);
        this.numberOfFiles++;
    }

    @Override
    public File updateFile(Status status, LocalDate date, String description) throws NoFilesException {
        if (this.files.isEmpty()) {
            throw new NoFilesException();
        }
        // Get first file of queue of files (using FIFO strategy) and remove it.
        File file = this.files.poll();
        if (status == Status.ENABLED) {
            // Add new event into sport events.
            SportEvent sportEvent = new SportEvent(file.getEventId(), file.getOrgId(), file.getDescription(),
                    file.getMax(), file.getStartDate(), file.getEndDate());
            this.sportEvents.put(file.getEventId(), sportEvent);
            // Add new event into best sport events vector.
            this.bestSportEvents.add(sportEvent);
            // Add new event into linked list of organizing entity.
            this.getOrganizingEntity(file.getOrgId()).addSportEvent(sportEvent);

        } else if (status == Status.DISABLED) {
            // Increase the number of rejected files and no save data.
            this.numberOfRejectedFiles++;
        }
        return file;
    }

    @Override
    public void signUpEvent(String playerId, String eventId) throws PlayerNotFoundException, SportEventNotFoundException, LimitExceededException {
        // Get sport event by id.
        SportEvent sportEvent = this.getSportEvent(eventId);
        if (sportEvent == null) {
            throw new SportEventNotFoundException();
        }
        // Get player by id.
        Player player = this.getPlayer(playerId);
        if (player == null) {
            throw new PlayerNotFoundException();
        }

        // New enrollment into sport event.
        sportEvent.addEnrollment(player);
        // add event into linked list of sport events player
        player.addSportEvent(sportEvent);

        if (sportEvent.isFull()) {
            throw new LimitExceededException();
        }

        // update most active player
        if (this.mostActivePlayer == null || (this.mostActivePlayer.numEvents() < player.numEvents())) {
            this.mostActivePlayer = player;
        }
    }

    @Override
    public double getRejectedFiles() {
        // Get the percentage between number of rejected files with total of files.
        return (double) this.numRejectedFiles()/ (double) this.numFiles();
    }

    @Override
    public Iterator<SportEvent> getSportEventsByOrganizingEntity(int organizationId) throws NoSportEventsException {
        OrganizingEntity organizingEntity = this.getOrganizingEntity(organizationId);
        if (organizingEntity == null) {
            throw new NoSportEventsException();
        }
        if (organizingEntity.numEvents() == 0) {
            throw new NoSportEventsException();
        }
        return organizingEntity.getSportEvents();
    }

    @Override
    public Iterator<SportEvent> getAllEvents() throws NoSportEventsException {
        if (this.sportEvents.size() == 0) {
            // If sport event not found, throw custom exception.
            throw new NoSportEventsException();
        }
        return this.sportEvents.values();
    }

    @Override
    public Iterator<SportEvent> getEventsByPlayer(String playerId) throws NoSportEventsException {
        Player player = this.getPlayer(playerId);
        if ((player == null) || (player.numEvents() == 0)) {
            // If player not found, throw custom exception.
            throw new NoSportEventsException();
        }
        return player.getSportEvents();
    }

    @Override
    public void addRating(String playerId, String eventId, Rating rating, String message) throws SportEventNotFoundException, PlayerNotFoundException, PlayerNotInSportEventException {
        // Get sport event from the ordered vector
        SportEvent sportEvent = this.getSportEvent(eventId);
        if (sportEvent == null) {
            // If sport event not found, throw custom exception.
            throw new SportEventNotFoundException();
        }
        // Get player by id
        Player player = this.getPlayer(playerId);
        if (player == null) {
            // If player not found, throw custom exception.
            throw new PlayerNotFoundException();
        }
        // Check if player has participated in sport event or not
        if (!player.hasParticipatedInEvent(sportEvent)) {
            throw new PlayerNotInSportEventException();
        }
        // Create new rating and add into sport event.
        uoc.ds.pr.model.Rating newRating = new uoc.ds.pr.model.Rating(player, eventId, rating, message);
        sportEvent.addRating(newRating);
        // Reorder best sport events vector.
        this.bestSportEvents.update(sportEvent);
    }

    @Override
    public Iterator<uoc.ds.pr.model.Rating> getRatingsByEvent(String eventId) throws SportEventNotFoundException, NoRatingsException {
        SportEvent sportEvent = this.getSportEvent(eventId);
        if (sportEvent == null) {
            // If sport event not found, throw custom exception.
            throw new SportEventNotFoundException();
        }
        if (sportEvent.getTotalRatings() == 0) {
            // If not ratings found, throw custom exception.
            throw  new NoRatingsException();
        }
        return sportEvent.getRatings();
    }

    @Override
    public Player mostActivePlayer() throws PlayerNotFoundException {
        if (this.mostActivePlayer == null) {
            // If player not found, throw custom exception.
            throw new PlayerNotFoundException();
        }
        return this.mostActivePlayer;
    }

    @Override
    public SportEvent bestSportEvent() throws SportEventNotFoundException {
        if (this.bestSportEvents.size() == 0) {
            // If sport event not found, throw custom exception.
            throw new SportEventNotFoundException();
        }
        return this.bestSportEvents.get(this.bestSportEvents.size()-1);
    }

    @Override
    public int numPlayers() {
        return this.numberOfPlayers;
    }

    @Override
    public int numOrganizingEntities() {
        return this.numberOfOrganizingEntities;
    }

    @Override
    public int numFiles() {
        return this.numberOfFiles;
    }

    @Override
    public int numRejectedFiles() {
        return this.numberOfRejectedFiles;
    }

    @Override
    public int numPendingFiles() {
        return this.files.size();
    }

    @Override
    public int numSportEvents() {
        return this.sportEvents.size();
    }

    @Override
    public int numSportEventsByPlayer(String playerId) {
        Player player = this.getPlayer(playerId);
        if (player == null) {
            // TODO: Should be throw new PlayerNotFoundException
            return 0;
        }
        return player.numEvents();
    }

    @Override
    public int numPlayersBySportEvent(String sportEventId) {
        SportEvent sportEvent = this.getSportEvent(sportEventId);
        if (sportEvent == null) {
            // TODO: Should be throw new SportEventNotFound
            return 0;
        }
        return sportEvent.getTotalEnrollments();
    }

    @Override
    public int numSportEventsByOrganizingEntity(int orgId) {
        OrganizingEntity organizingEntity = this.getOrganizingEntity(orgId);
        if (organizingEntity == null) {
            // TODO: Should be throw new OrganizingEntityNotFound
            return 0;
        }
        return organizingEntity.numEvents();
    }

    @Override
    public int numSubstitutesBySportEvent(String sportEventId) {
        SportEvent sportEvent = this.getSportEvent(sportEventId);
        if (sportEvent == null) {
            // TODO: Should be throw new SportEventNotFound
            return 0;
        }
        return sportEvent.getTotalSubstitutes();
    }

    @Override
    public Player getPlayer(String playerId) {
        for (int i = 0; i < this.numberOfPlayers; i++) {
            // If next position of players vector is null, break process and return null
            if (this.players[i].getId() == playerId) {
                return this.players[i];
            }
        }
        return null;
    }

    @Override
    public SportEvent getSportEvent(String eventId) {
        return this.sportEvents.get(eventId);
    }

    @Override
    public OrganizingEntity getOrganizingEntity(int id) {
        for (int i = 0; i < this.numberOfOrganizingEntities; i++) {
            if (this.organizingEntities[i].getId() == id) {
                return this.organizingEntities[i];
            }
        }
        return null;
    }

    @Override
    public File currentFile() {
        // Get element of queue using FIFO algorythm.
        return this.files.peek();
    }
}
