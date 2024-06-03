package OWurst.Investment_Simulator.EntityUnitTests;

import OWurst.Investment_Simulator.Entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

// start by testing as POJO
public class UserUnitTest {
    User bareUser;
    User fullConstructorUser;
    User noEmailUser;

    @Before
    public void setUp() {
        bareUser = new User();
        fullConstructorUser = new User("John", "Doe", "johndoe", "password", "johndoe@gmail.com");
        noEmailUser = new User("Jane", "Doe", "janedoe", "password");
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareUser instanceof User);
    }

    public void testFullConstructorReturnsCorrectType() {
        assertTrue(fullConstructorUser instanceof User);
    }

    public void testNoEmailConstructorReturnsCorrectType() {
        assertTrue(noEmailUser instanceof User);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", fullConstructorUser.getFirstName());
        assertEquals("Jane", noEmailUser.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", fullConstructorUser.getLastName());
        assertEquals("Doe", noEmailUser.getLastName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("johndoe", fullConstructorUser.getUsername());
        assertEquals("janedoe", noEmailUser.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", fullConstructorUser.getPassword());
        assertEquals("password", noEmailUser.getPassword());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johndoe@gmail.com", fullConstructorUser.getEmail());
        assertNull(noEmailUser.getEmail());
    }

    @Test
    public void testGetCash() {
        assertEquals(100000.0, fullConstructorUser.getCash(), 0.0);
        assertEquals(100000.0, noEmailUser.getCash(), 0.0);
    }

    @Test
    public void testSetCash() {
        bareUser.setCash(5000.0);
        assertEquals(5000.0, bareUser.getCash(), 0.0);
    }

    @Test
    public void testWithdrawCash() {
        assertTrue(fullConstructorUser.withdrawCash(5000.0));
        assertEquals(95000.0, fullConstructorUser.getCash(), 0.0);
    }

    @Test
    public void testDepositCash() {
        assertTrue(fullConstructorUser.depositCash(5000.0));
        assertEquals(105000.0, fullConstructorUser.getCash(), 0.0);
    }

    @Test
    public void testWithdrawCashReturnsFalseIfNotEnoughCash() {
        assertFalse(fullConstructorUser.withdrawCash(150000.0));
    }

    @Test
    public void testDepositCashReturnsFalseIfNegativeAmount() {
        assertFalse(fullConstructorUser.depositCash(-5000.0));
    }

    @Test
    public void testGetUserId() {
        assertEquals(0, fullConstructorUser.getUserId());
    }

    @Test
    public void testSetEmail() {
        assertNull(bareUser.getEmail());
        bareUser.setEmail("empty@gmail.com");
        assertEquals("empty@gmail.com", bareUser.getEmail());
    }

    @Test
    public void testSetPassword() {
        assertNull(bareUser.getPassword());
        bareUser.setPassword("newpassword");
        assertEquals("newpassword", bareUser.getPassword());
    }
}
