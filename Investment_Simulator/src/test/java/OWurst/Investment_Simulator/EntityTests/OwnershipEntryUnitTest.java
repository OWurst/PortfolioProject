package OWurst.Investment_Simulator.EntityTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.Entity.OwnershipEntry;

public class OwnershipEntryUnitTest {
    OwnershipEntry bareEntry;
    OwnershipEntry constructorEntry;

    @Before
    public void setUp() {
        bareEntry = new OwnershipEntry();
        constructorEntry = new OwnershipEntry(1, 1, 10);
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareEntry instanceof OwnershipEntry);
    }

    @Test
    public void testConstructorReturnsCorrectType() {
        assertTrue(constructorEntry instanceof OwnershipEntry);
    }

    @Test
    public void testGetStockId() {
        assertEquals(constructorEntry.getStockId(), 1);
    }

    @Test
    public void testGetUserId() {
        assertEquals(constructorEntry.getUserId(), 1);
    }

    @Test
    public void testGetNumShares() {
        assertEquals(constructorEntry.getNumShares(), 10);
    }

    @Test
    public void testSetStockId() {
        assertEquals(bareEntry.getStockId(), 0);
        bareEntry.setStockId(2);
        assertEquals(bareEntry.getStockId(), 2);
    }

    @Test
    public void testSetUserId() {
        assertEquals(bareEntry.getUserId(), 0);
        bareEntry.setUserId(2);
        assertEquals(bareEntry.getUserId(), 2);
    }

    @Test
    public void testSetNumShares() {
        assertEquals(bareEntry.getNumShares(), 0);
        bareEntry.setNumShares(20);
        assertEquals(bareEntry.getNumShares(), 20);
    }

    @Test
    public void testIncrementSharesFailsOnNegative() {
        assertFalse(bareEntry.incrementShares(-1));
        assertEquals(0, bareEntry.getNumShares());
    }

    @Test
    public void testDecrementSharesFailsOnNegative() {
        assertFalse(bareEntry.decrementShares(-1));
        assertEquals(0, bareEntry.getNumShares());
    }

    @Test
    public void testDecrementSharesFailsOnTooManyShares() {
        assertFalse(bareEntry.decrementShares(1));
        assertEquals(0, bareEntry.getNumShares());
    }

    @Test
    public void testIncrementShares() {
        assertTrue(bareEntry.incrementShares(1));
        assertEquals(1, bareEntry.getNumShares());
    }

    @Test
    public void testDecrementShares() {
        assertTrue(constructorEntry.decrementShares(10));
        assertEquals(0, constructorEntry.getNumShares());
    }
}
