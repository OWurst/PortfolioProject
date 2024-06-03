package OWurst.Investment_Simulator.DTOTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.UserDTO;

public class UserDTOUnitTest {
    UserDTO bareDTO;
    UserDTO fullDTO;

    @Before
    public void setUp() {
        bareDTO = new UserDTO();
        fullDTO = new UserDTO("username", "password", "first", "last", "email");
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareDTO instanceof UserDTO);
    }

    @Test
    public void testFullConstructorReturnsCorrectType() {
        assertTrue(fullDTO instanceof UserDTO);
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

    @Test
    public void testGetFirstname() {
        assertEquals("first", fullDTO.getFirstname());
    }

    @Test
    public void testSetFirstname() {
        bareDTO.setFirstname("first");
        assertEquals("first", bareDTO.getFirstname());
    }

    @Test
    public void testGetLastname() {
        assertEquals("last", fullDTO.getLastname());
    }

    @Test
    public void testSetLastname() {
        bareDTO.setLastname("last");
        assertEquals("last", bareDTO.getLastname());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email", fullDTO.getEmail());
    }

    @Test
    public void testSetEmail() {
        bareDTO.setEmail("email");
        assertEquals("email", bareDTO.getEmail());
    }

    @Test
    public void testGetId() {
        assertEquals(0, fullDTO.getId());
    }

    @Test
    public void testSetId() {
        bareDTO.setId(1);
        assertEquals(1, bareDTO.getId());
    }
}
