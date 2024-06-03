package OWurst.Investment_Simulator.DTOTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.ChangePWDTO;

public class ChangePWDTOUnitTest {
    ChangePWDTO bareDTO;
    ChangePWDTO fullDTO;

    @Before
    public void setUp() {
        bareDTO = new ChangePWDTO();
        fullDTO = new ChangePWDTO("oldpassword", "newpassword");
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareDTO instanceof ChangePWDTO);
    }

    @Test
    public void testFullConstructorReturnsCorrectType() {
        assertTrue(fullDTO instanceof ChangePWDTO);
    }

    @Test
    public void testGetOldpassword() {
        assertEquals("oldpassword", fullDTO.getOldpassword());
    }

    @Test
    public void testSetOldpassword() {
        bareDTO.setOldpassword("oldpassword");
        assertEquals("oldpassword", bareDTO.getOldpassword());
    }

    @Test
    public void testGetNewpassword() {
        assertEquals("newpassword", fullDTO.getNewpassword());
    }

    @Test
    public void testSetNewpassword() {
        bareDTO.setNewpassword("newpassword");
        assertEquals("newpassword", bareDTO.getNewpassword());
    }
}
