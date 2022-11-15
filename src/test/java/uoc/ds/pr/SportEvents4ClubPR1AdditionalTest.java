package uoc.ds.pr;

import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.exceptions.FullContainerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.exceptions.DSException;
import uoc.ds.pr.exceptions.LimitExceededException;
import uoc.ds.pr.exceptions.NoFilesException;
import uoc.ds.pr.exceptions.OrganizingEntityNotFoundException;

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
            this.sportEvents4Club.addPlayer("User " + i, this.getRandomString(25),
                    this.getRandomString(100), LocalDate.of(1970,1,1));
        }
        Assert.assertEquals(this.sportEvents4Club.numPlayers(), SportEvents4Club.MAX_NUM_PLAYER);
        Assert.assertThrows(LimitExceededException.class, ()->
                this.sportEvents4Club.addPlayer("User " + this.sportEvents4Club.numPlayers() + 1,
                        this.getRandomString(25), this.getRandomString(100),
                        LocalDate.of(1970,1,1))
        );
    }

    // This method test the exception to exceeded limit organizing entities.
    @Test
    public void testAddOrganizingEntityLimitExceeded() throws LimitExceededException {
        for (int i = this.sportEvents4Club.numOrganizingEntities(); i < SportEvents4Club.MAX_NUM_ORGANIZING_ENTITIES; i++) {
            this.sportEvents4Club.addOrganizingEntity(i+20, this.getRandomString(120),
                    this.getRandomString(255));
        }
        Assert.assertEquals(this.sportEvents4Club.numOrganizingEntities(), SportEvents4Club.MAX_NUM_ORGANIZING_ENTITIES);
        Assert.assertThrows(LimitExceededException.class, ()->
                this.sportEvents4Club.addOrganizingEntity(this.sportEvents4Club.numOrganizingEntities() + 1,
                        this.getRandomString(120), this.getRandomString(255))
        );
    }

    // This method test the exception to exceeded limit organizing entities.
    @Test
    public void testAddFileOrganizingEntityNotFound() {
        Assert.assertTrue(this.sportEvents4Club.numOrganizingEntities() > 0);
        Assert.assertThrows(OrganizingEntityNotFoundException.class, ()->
                this.sportEvents4Club.addFile("100", "TEST-EVENT", OE_NOT_FOUND,
                        this.getRandomString(255), SportEvents4Club.Type.SMALL,
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
                    LocalDate.of(2022,11, 30), this.getRandomString(255))
        );
    }

    // This method check the limit of sport events
    @Test
    public void testAddSportEventFullContainer() throws DSException {
        int orgId = 1;
        this.newApp.addOrganizingEntity(orgId, this.getRandomString(10), this.getRandomString(255));
        for (int i = 0; i < SportEvents4Club.MAX_NUM_SPORT_EVENTS; i++) {
            // Create new file
            this.newApp.addFile("'" + i + "'", "TEST-EVENT-" + i, orgId,
                    this.getRandomString(255), SportEvents4Club.Type.SMALL,
                    SportEvents4Club.FLAG_ALL_OPTS, 100, LocalDate.of(2022, 1, 1),
                    LocalDate.of(2022, 11, 15)
            );
            // Aprove file and add new sport event for each iterate.
            this.newApp.updateFile(SportEvents4Club.Status.ENABLED,
                    LocalDate.of(2022, 1, 1), this.getRandomString(255));
        }

        // Try to force exception creating new sport event.
        this.newApp.addFile("'" + (this.newApp.numFiles()+1) + "'", "TEST-EVENT-" + (this.newApp.numFiles()+1), orgId,
                this.getRandomString(255), SportEvents4Club.Type.SMALL,
                SportEvents4Club.FLAG_ALL_OPTS, 100, LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 11, 15)
        );
        Assert.assertThrows(FullContainerException.class, ()->
                this.newApp.updateFile(SportEvents4Club.Status.ENABLED,
                        LocalDate.of(2022, 1, 1), this.getRandomString(255))
        );
    }

    private String getRandomString(int size) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

}
