package OWurst.Investment_Simulator.DTOTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.LoginDTO;

public class LoginDTOUnitTest {
    LoginDTO bareDTO;
    LoginDTO fullDTO;

    @Before
    public void setUp() {
        bareDTO = new LoginDTO();
        fullDTO = new LoginDTO("username", "password");
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareDTO instanceof LoginDTO);
    }

    @Test
    public void testFullConstructorReturnsCorrectType() {
        assertTrue(fullDTO instanceof LoginDTO);
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", fullDTO.getUsername());
    }

    @Test
    public void testSetUsername() {
        bareDTO.setUsername("username");
        assertEquals("username", bareDTO.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", fullDTO.getPassword());
    }

    @Test
    public void testSetPassword() {
        bareDTO.setPassword("password");
        assertEquals("password", bareDTO.getPassword());
    }
}
