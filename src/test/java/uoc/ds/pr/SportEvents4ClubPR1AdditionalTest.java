package uoc.ds.pr;

import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.exceptions.FullContainerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.Player;
import uoc.ds.pr.model.Rating;
import uoc.ds.pr.model.SportEvent;
import uoc.ds.pr.util.StringUtils;

import java.time.LocalDate;

public class SportEvents4ClubPR1AdditionalTest {

    private static final int OE_NOT_FOUND = 10000;
    private SportEvents4Club sportEvents4Club;

    private SportEvents4Club newApp;

    @Before
    public void setUp() throws Exception {
        this.sportEvents4Club = FactorySportEvents4Club.getSportEvents4Club();
        this.newApp = new SportEvents4ClubImpl();
    }

    @After
    public void tearDown() {
        this.sportEvents4Club = null;
    }

    // This method test the exception to exceeded limit players.
    @Test
    public void testAddPlayerLimitExceeded() throws LimitExceededException {
        for (int i = this.sportEvents4Club.numPlayers(); i < SportEvents4Club.MAX_NUM_PLAYER; i++) {
            this.sportEvents4Club.addPlayer("User " + i, StringUtils.getRandomString(25),
                    StringUtils.getRandomString(100), LocalDate.of(1970,1,1));
        }
        Assert.assertEquals(this.sportEvents4Club.numPlayers(), SportEvents4Club.MAX_NUM_PLAYER);
        Assert.assertThrows(LimitExceededException.class, ()->
                this.sportEvents4Club.addPlayer("User " + this.sportEvents4Club.numPlayers() + 1,
                    StringUtils.getRandomString(25), StringUtils.getRandomString(100),
                    LocalDate.of(1970,1,1))
        );
    }

    // This method test the exception to exceeded limit organizing entities.
    @Test
    public void testAddOrganizingEntityLimitExceeded() throws LimitExceededException {
        for (int i = this.sportEvents4Club.numOrganizingEntities(); i < SportEvents4Club.MAX_NUM_ORGANIZING_ENTITIES; i++) {
            this.sportEvents4Club.addOrganizingEntity(i+20, StringUtils.getRandomString(120),
                StringUtils.getRandomString(255));
        }
        Assert.assertEquals(this.sportEvents4Club.numOrganizingEntities(), SportEvents4Club.MAX_NUM_ORGANIZING_ENTITIES);
        Assert.assertThrows(LimitExceededException.class, ()->
            this.sportEvents4Club.addOrganizingEntity(this.sportEvents4Club.numOrganizingEntities() + 1,
                StringUtils.getRandomString(120), StringUtils.getRandomString(255))
        );
    }

    // This method test the exception to exceeded limit organizing entities.
    @Test
    public void testAddFileOrganizingEntityNotFound() {
        Assert.assertTrue(this.sportEvents4Club.numOrganizingEntities() > 0);
        Assert.assertThrows(OrganizingEntityNotFoundException.class, ()->
            this.sportEvents4Club.addFile("100", "TEST-EVENT", OE_NOT_FOUND,
                StringUtils.getRandomString(255), SportEvents4Club.Type.SMALL,
                SportEvents4Club.FLAG_ALL_OPTS, 100, LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 11, 15)
            )
        );
    }

    // This method check that not exists any file in queue array of files.
    @Test
    public void testUpdateFileFilesNotFound() {
        Assert.assertThrows(NoFilesException.class, ()->
            this.newApp.updateFile(SportEvents4Club.Status.ENABLED,
                    LocalDate.of(2022,11, 30), StringUtils.getRandomString(255))
        );
    }

    // This method check the limit of sport events
    @Test
    public void testAddSportEventFullContainer() throws DSException {
        int orgId = 1;
        this.newApp.addOrganizingEntity(orgId, StringUtils.getRandomString(10), StringUtils.getRandomString(255));
        for (int i = 0; i < SportEvents4Club.MAX_NUM_SPORT_EVENTS; i++) {
            // Create new file
            this.newApp.addFile("'" + i + "'", "TEST-EVENT-" + i, orgId,
                StringUtils.getRandomString(255), SportEvents4Club.Type.SMALL,
                SportEvents4Club.FLAG_ALL_OPTS, 100, LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 11, 15)
            );
            // Aprove file and add new sport event for each iterate.
            this.newApp.updateFile(SportEvents4Club.Status.ENABLED,
                LocalDate.of(2022, 1, 1), StringUtils.getRandomString(255));
        }

        // Try to force exception creating new sport event.
        this.newApp.addFile("'" + (this.newApp.numFiles()+1) + "'", "TEST-EVENT-" + (this.newApp.numFiles()+1), orgId,
            StringUtils.getRandomString(255), SportEvents4Club.Type.SMALL,
            SportEvents4Club.FLAG_ALL_OPTS, 100, LocalDate.of(2022, 1, 1),
            LocalDate.of(2022, 11, 15)
        );
        Assert.assertThrows(FullContainerException.class, ()->
            this.newApp.updateFile(SportEvents4Club.Status.ENABLED,
            LocalDate.of(2022, 1, 1), StringUtils.getRandomString(255))
        );
    }

    // This method check if exists sport event for add new rating
    @Test
    public void testAddRatingSportEventNotFound() {
        Assert.assertThrows(SportEventNotFoundException.class, () ->
            this.sportEvents4Club.addRating("playerRandom", "eventRandom",
                SportEvents4Club.Rating.FIVE, StringUtils.getRandomString(40))
        );
    }

    // This method check if exists player for add new rating
    @Test
    public void testAddRatingPlayerNotFound() throws DSException {
        SportEvent sportEvent = this.sportEvents4Club.bestSportEvent();
        Assert.assertThrows(PlayerNotFoundException.class, () ->
                this.sportEvents4Club.addRating("playerRandom", sportEvent.getEventId(),
                        SportEvents4Club.Rating.FIVE, StringUtils.getRandomString(40))
        );
    }

    // This method check if exists player in event for add new rating
    @Test
    public void testAddRatingPlayerNotInSportEventException() {
        // Get event from player 1
        Player player1 = this.sportEvents4Club.getPlayer("idPlayer1");
        Assert.assertEquals("EV-1101", player1.getSportEvents().next().getEventId());
        // Get event from player 2
        Player player2 = this.sportEvents4Club.getPlayer("idPlayer2");
        Assert.assertEquals("EV-1102", player2.getSportEvents().next().getEventId());
        // Cross data to throw PlayerNotInSportEventException
        Assert.assertThrows(PlayerNotInSportEventException.class, () ->
                this.sportEvents4Club.addRating("idPlayer1", "EV-1102",
                        SportEvents4Club.Rating.FIVE, StringUtils.getRandomString(40))
        );
    }

    // This method check if exists event in order to get ratings
    @Test
    public void testGetRatingsSportEventNotFound() {
        Assert.assertThrows(SportEventNotFoundException.class, () ->
            this.sportEvents4Club.getRatingsByEvent("eventNotFound")
        );
    }

    // This method check if exists most active player
    @Test
    public void testMostActivePlayerWithPlayerNotFound() {
        Assert.assertThrows(PlayerNotFoundException.class, () ->
                this.newApp.mostActivePlayer()
        );
    }

    // This method check if exists sport events into best sport events vector
    @Test
    public void testBestSportEventWithSportEventNotFoundException() {
        Assert.assertThrows(SportEventNotFoundException.class, () ->
                this.newApp.bestSportEvent()
        );
    }

    // This method checks if exists player or not in order to sign up event.
    @Test
    public void testSignUpEventPlayerNotFound() {
        Assert.assertThrows(PlayerNotFoundException.class, () ->
                this.sportEvents4Club.signUpEvent("randomPlayerNotFound", "EV-1101")
        );
    }

    // This method checks if exists sport event or not in order to sign up event.
    @Test
    public void testSignUpEventSportEventNotFound() {
        Assert.assertThrows(SportEventNotFoundException.class, () ->
                this.sportEvents4Club.signUpEvent("idPlayer1", "randomEventNotFound")
        );
    }

    // This method checks if exists sport event in player
    @Test
    public void testGetEventsByPlayerNoSportEventsFound() {
        Assert.assertThrows(NoSportEventsException.class, () ->
                this.sportEvents4Club.getEventsByPlayer("idPlayer12")
        );
    }

}
