package OWurst.Investment_Simulator.EntityUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.Entity.PortfolioValueEntry;

public class PortfolioValueEntryUnitTest {
    PortfolioValueEntry bareEntry;
    PortfolioValueEntry constructorEntry;
    Date currDate;

    @Before
    public void setUp() {
        bareEntry = new PortfolioValueEntry();
        currDate = new Date();
        constructorEntry = new PortfolioValueEntry(1, 100.0, currDate);
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareEntry instanceof PortfolioValueEntry);
    }

    @Test
    public void testConstructorReturnsCorrectType() {
        assertTrue(constructorEntry instanceof PortfolioValueEntry);
    }

    @Test
    public void testGetValue() {
        assertEquals(100.0, constructorEntry.getValue(), 0.0);
    }

    @Test
    public void testGetEntryDate() {
        assertEquals(currDate, constructorEntry.getEntryDate());
    }

    @Test
    public void testSetValue() {
        assertEquals(0.0, bareEntry.getValue(), 0);
        bareEntry.setValue(200.0);
        assertEquals(200.0, bareEntry.getValue(), 0.0);
    }

    @Test
    public void testSetEntryDate() {
        assertNull(bareEntry.getEntryDate());
        Date newDate = new Date();
        bareEntry.setEntryDate(newDate);
        assertEquals(newDate, bareEntry.getEntryDate());
    }

    @Test
    public void testGetUserId() {
        assertEquals(1, constructorEntry.getUserId());
    }

    @Test
    public void testSetUserId() {
        assertEquals(0, bareEntry.getUserId());
        bareEntry.setUserId(2);
        assertEquals(2, bareEntry.getUserId());
    }
}
