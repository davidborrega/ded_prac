package uoc.ds.pr.util;

import edu.uoc.ds.exceptions.FullContainerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uoc.ds.pr.model.SportEvent;

public class OrderedVectorTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testAddandIsFull() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        Assert.assertEquals(testVector.isFull(), false);
        for (int i = 0; i < 5; i++) {
            testVector.add(StringUtils.getRandomString(1));
        }
        Assert.assertEquals(testVector.size(), 5);
        Assert.assertEquals(testVector.isFull(), true);
        Assert.assertThrows(FullContainerException.class, ()->
            testVector.add(StringUtils.getRandomString(1))
        );
    }

    @Test
    public void testIsEmpty() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        Assert.assertEquals(testVector.isEmpty(), true);
        testVector.add(StringUtils.getRandomString(1));
        Assert.assertEquals(testVector.isEmpty(), false);
    }

    @Test
    public void testGetElementSizeAndValues() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        testVector.add("A");
        testVector.add("B");
        Assert.assertEquals(testVector.get(0), "A");
        Assert.assertEquals(testVector.get(1), "B");
        Assert.assertEquals(testVector.get(2), null);
        Assert.assertEquals(testVector.size(), 2);
        testVector.add("C");
        Assert.assertEquals(testVector.get(2), "C");
        Assert.assertEquals(testVector.size(), 3);
    }

    @Test
    public void testCheckInAlphabeticOrder() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        // Add elements in alphabetic disorder
        testVector.add("E");
        testVector.add("A");
        testVector.add("B");
        testVector.add("C");
        // Check in alphabetic order
        Assert.assertEquals(testVector.size(), 4);
        Assert.assertEquals(testVector.get(0), "A");
        Assert.assertEquals(testVector.get(1), "B");
        Assert.assertEquals(testVector.get(2), "C");
        Assert.assertEquals(testVector.get(3), "E");
    }

    @Test
    public void testUpdateAndCheckInAlphabeticOrder() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        // Add elements in alphabetic disorder
        testVector.add("E");
        testVector.add("A");
        testVector.add("B");
        testVector.add("C");
        // Check in alphabetic order
        Assert.assertEquals(testVector.size(), 4);
        Assert.assertEquals(testVector.get(0), "A");
        Assert.assertEquals(testVector.get(1), "B");
        Assert.assertEquals(testVector.get(2), "C");
        Assert.assertEquals(testVector.get(3), "E");
        testVector.update("A");
        Assert.assertEquals(testVector.get(0), "A");
    }

    @Test
    public void testDeleteAndCheckOrder() {
        OrderedVector<String> testVector = new OrderedVector<String>(5, SportEvent.COMPARATOR);
        // Add elements in alphabetic disorder
        testVector.add("E");
        testVector.add("A");
        testVector.add("B");
        testVector.add("C");
        // Check in alphabetic order
        Assert.assertEquals(testVector.size(), 4);
        Assert.assertEquals(testVector.get(0), "A");
        Assert.assertEquals(testVector.get(1), "B");
        Assert.assertEquals(testVector.get(2), "C");
        Assert.assertEquals(testVector.get(3), "E");
        testVector.delete(0);
        // Position 0 belonged to element "A". At the time of removal, element "B" takes over that position.
        Assert.assertEquals(testVector.get(0), "B");
        Assert.assertEquals(testVector.get(1), "C");
        Assert.assertEquals(testVector.get(2), "E");
    }

}
