package uoc.ds.pr;

import edu.uoc.ds.adt.sequential.QueueArrayImpl;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.File;
import uoc.ds.pr.model.OrganizingEntity;
import uoc.ds.pr.model.Player;
import uoc.ds.pr.model.SportEvent;

import java.time.LocalDate;

public class SportEvents4ClubImpl implements SportEvents4Club {

    // Set array of players
    private Player players[];
    private int numberOfPlayers;
    // Set array of organizing entities
    private OrganizingEntity organizingEntities[];
    private int numberOfOrganizingEntities;
    // Set most active player
    private Player mostActivePlayer;
    // Set files
    private QueueArrayImpl<File> files;
    private int numberOfFiles;
    private int numberOfRejectedFiles;
    // Set sport events


    public SportEvents4ClubImpl() {
        // Initialize array of players
        this.players = new Player[MAX_NUM_PLAYER];
        this.numberOfPlayers = 0;
        // Initialize array of organizingEntities;
        this.organizingEntities = new OrganizingEntity[MAX_NUM_ORGANIZING_ENTITIES];
        this.numberOfOrganizingEntities = 0;
        // Initialize most active player pointer.
        this.mostActivePlayer = null;
        // Initialize queue of files.
        this.files = new QueueArrayImpl<File>();
        this.numberOfFiles = 0;
        this.numberOfRejectedFiles = 0;
    }

    @Override
    public void addPlayer(String id, String name, String surname, LocalDate dateOfBirth) throws LimitExceededException {
        Player player =  this.getPlayer(id);
        if (player == null) {
            if (this.numberOfPlayers >= MAX_NUM_PLAYER) {
                throw new LimitExceededException();
            }
            this.players[numberOfPlayers] = new Player(id, name, surname, dateOfBirth);
            this.numberOfPlayers++;
        } else {
            player.setName(name);
            player.setSurname(surname);
            player.setDateOfBirth(dateOfBirth);
        }
    }

    @Override
    public void addOrganizingEntity(int id, String name, String description) throws LimitExceededException {
        OrganizingEntity organizingEntity = new OrganizingEntity(id, name, description);
        if (organizingEntity == null) {
            if (this.numberOfOrganizingEntities >= MAX_NUM_ORGANIZING_ENTITIES) {
                throw new LimitExceededException();
            }
            this.organizingEntities[numberOfOrganizingEntities] = new OrganizingEntity(id, name, description);
            this.numberOfOrganizingEntities++;
        } else {
            organizingEntity.setName(name);
            organizingEntity.setDescription(description);
        }
    }

    @Override
    public void addFile(String id, String eventId, int orgId, String description, Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) throws OrganizingEntityNotFoundException {
        // Check if the organization exists, if not exists, throw custom error.
        OrganizingEntity organizingEntity = getOrganizingEntity(orgId);
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
        } else if (status == Status.DISABLED) {
            this.numberOfRejectedFiles++;
        }
        return null;
    }

    @Override
    public void signUpEvent(String playerId, String eventId) throws PlayerNotFoundException, SportEventNotFoundException, LimitExceededException {

    }

    @Override
    public double getRejectedFiles() {
        return this.numberOfRejectedFiles;
    }

    @Override
    public Iterator<SportEvent> getSportEventsByOrganizingEntity(int organizationId) throws NoSportEventsException {
        return null;
    }

    @Override
    public Iterator<SportEvent> getAllEvents() throws NoSportEventsException {
        return null;
    }

    @Override
    public Iterator<SportEvent> getEventsByPlayer(String playerId) throws NoSportEventsException {
        return null;
    }

    @Override
    public void addRating(String playerId, String eventId, Rating rating, String message) throws SportEventNotFoundException, PlayerNotFoundException, PlayerNotInSportEventException {

    }

    @Override
    public Iterator<uoc.ds.pr.model.Rating> getRatingsByEvent(String eventId) throws SportEventNotFoundException, NoRatingsException {
        return null;
    }

    @Override
    public Player mostActivePlayer() throws PlayerNotFoundException {
        if (this.mostActivePlayer == null) {
            throw new PlayerNotFoundException();
        }
        return this.mostActivePlayer;
    }

    @Override
    public SportEvent bestSportEvent() throws SportEventNotFoundException {

        return null;
    }

    @Override
    public int numPlayers() {
        return this.players.length;
    }

    @Override
    public int numOrganizingEntities() {
        return this.organizingEntities.length;
    }

    @Override
    public int numFiles() {
        return this.files.size();
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
        return 0;
    }

    @Override
    public int numSportEventsByPlayer(String playerId) {
        return 0;
    }

    @Override
    public int numPlayersBySportEvent(String sportEventId) {
        return 0;
    }

    @Override
    public int numSportEventsByOrganizingEntity(int orgId) {
        return 0;
    }

    @Override
    public int numSubstitutesBySportEvent(String sportEventId) {
        return 0;
    }

    @Override
    public Player getPlayer(String playerId) {
        if (this.players.length == 0) {
            return null;
        }
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i].getId() == playerId) {
                return this.players[i];
            }
        }
        return null;
    }

    @Override
    public SportEvent getSportEvent(String eventId) {
        return null;
    }

    @Override
    public OrganizingEntity getOrganizingEntity(int id) {
        if (this.organizingEntities.length == 0) {
            return null;
        }
        return this.organizingEntities[id];
    }

    @Override
    public File currentFile() {
        return this.files.peek();
    }
}
