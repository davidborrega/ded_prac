package uoc.ds.pr;

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

    public SportEvents4ClubImpl() {
        // Initialize array of players
        this.players = new Player[MAX_NUM_PLAYER];
        this.numberOfPlayers = 0;
        // Initialize array of organizingEntities;
        this.organizingEntities = new OrganizingEntity[MAX_NUM_ORGANIZING_ENTITIES];
        this.numberOfOrganizingEntities = 0;
    }

    @Override
    public void addPlayer(String id, String name, String surname, LocalDate dateOfBirth) throws LimitExceededException {
        Player player =  this.getPlayer(id);
        if (player == null) {
            if (this.numberOfPlayers >= MAX_NUM_PLAYER) {
                throw new LimitExceededException();
            }
        } else {
            player.setName(name);
            player.setSurname(surname);
            player.setDateOfBirth(dateOfBirth);
        }
    }

    @Override
    public void addOrganizingEntity(int id, String name, String description) {
        OrganizingEntity organizingEntity = new OrganizingEntity(id);

    }

    @Override
    public void addFile(String id, String eventId, int orgId, String description, Type type, byte resources, int max, LocalDate startDate, LocalDate endDate) throws OrganizingEntityNotFoundException {

    }

    @Override
    public File updateFile(Status status, LocalDate date, String description) throws NoFilesException {
        return null;
    }

    @Override
    public void signUpEvent(String playerId, String eventId) throws PlayerNotFoundException, SportEventNotFoundException, LimitExceededException {

    }

    @Override
    public double getRejectedFiles() {
        return 0;
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
        return 0;
    }

    @Override
    public int numOrganizingEntities() {
        return 0;
    }

    @Override
    public int numFiles() {
        return 0;
    }

    @Override
    public int numRejectedFiles() {
        return 0;
    }

    @Override
    public int numPendingFiles() {
        return 0;
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
        return null;
    }

    @Override
    public SportEvent getSportEvent(String eventId) {
        return null;
    }

    @Override
    public OrganizingEntity getOrganizingEntity(int id) {
        return null;
    }

    @Override
    public File currentFile() {
        return null;
    }
}
