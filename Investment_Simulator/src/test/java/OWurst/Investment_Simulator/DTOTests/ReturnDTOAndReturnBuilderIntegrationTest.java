package OWurst.Investment_Simulator.DTOTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.ReturnBuilder;
import OWurst.Investment_Simulator.DTO.ReturnDTO;
import OWurst.Investment_Simulator.DTO.StockDTO;

public class ReturnDTOAndReturnBuilderIntegrationTest {
    ReturnBuilder builder;
    ReturnDTO returnDTO;
    ReturnDTO fullReturnDTO;
    ArrayList<StockDTO> stocks;

    @Before
    public void setUp() {
        builder = new ReturnBuilder();

        stocks = new ArrayList<StockDTO>();
        fullReturnDTO = new ReturnDTO(1, "errMsg", 1, stocks, "username", "firstname", "lastname", 1.0, "msg", "email");
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(builder instanceof ReturnBuilder);
    }

    @Test
    public void testBuildReturnsCorrectType() {
        returnDTO = builder.build();
        assertTrue(returnDTO instanceof ReturnDTO);
    }

    @Test
    public void testGetUid() {
        assertEquals(1, fullReturnDTO.getUid());
    }

    @Test
    public void testGetErrMsg() {
        assertEquals("errMsg", fullReturnDTO.getErrMsg());
    }

    @Test
    public void testGetRespCode() {
        assertEquals(1, fullReturnDTO.getRespCode());
    }

    @Test
    public void testGetStocks() {
        assertEquals(stocks, fullReturnDTO.getStocks());
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", fullReturnDTO.getUsername());
    }

    @Test
    public void testGetFirstname() {
        assertEquals("firstname", fullReturnDTO.getFirstname());
    }

    @Test
    public void testGetLastname() {
        assertEquals("lastname", fullReturnDTO.getLastname());
    }

    @Test
    public void testGetCash() {
        assertEquals(1.0, fullReturnDTO.getCash(), 0.0);
    }

    @Test
    public void testGetMsg() {
        assertEquals("msg", fullReturnDTO.getMsg());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email", fullReturnDTO.getEmail());
    }

    @Test
    public void testWithUid() {
        returnDTO = builder.withUid(1).build();
        assertEquals(1, returnDTO.getUid());
    }

    @Test
    public void testWithErrMsg() {
        returnDTO = builder.withErrMsg("errMsg").build();
        assertEquals("errMsg", returnDTO.getErrMsg());
    }

    @Test
    public void testWithRespCode() {
        returnDTO = builder.withRespCode(1).build();
        assertEquals(1, returnDTO.getRespCode());
    }

    @Test
    public void testWithStocks() {
        returnDTO = builder.withStocks(stocks).build();
        assertEquals(stocks, returnDTO.getStocks());
    }

    @Test
    public void testWithUsername() {
        returnDTO = builder.withUsername("username").build();
        assertEquals("username", returnDTO.getUsername());
    }

    @Test
    public void testWithFirstname() {
        returnDTO = builder.withFirstname("firstname").build();
        assertEquals("firstname", returnDTO.getFirstname());
    }

    @Test
    public void testWithLastname() {
        returnDTO = builder.withLastname("lastname").build();
        assertEquals("lastname", returnDTO.getLastname());
    }

    @Test
    public void testWithCash() {
        returnDTO = builder.withCash(1.0).build();
        assertEquals(1.0, returnDTO.getCash(), 0.0);
    }

    @Test
    public void testWithMsg() {
        returnDTO = builder.withMsg("msg").build();
        assertEquals("msg", returnDTO.getMsg());
    }

    @Test
    public void testWithEmail() {
        returnDTO = builder.withEmail("email").build();
        assertEquals("email", returnDTO.getEmail());
    }

    @Test
    public void testWithAllFields() {
        returnDTO = builder
                .withUid(1)
                .withErrMsg("errMsg")
                .withRespCode(1)
                .withStocks(stocks)
                .withUsername("username")
                .withFirstname("firstname")
                .withLastname("lastname")
                .withCash(1.0).withMsg("msg")
                .withEmail("email")
                .build();

        assertEquals(fullReturnDTO.getUid(), returnDTO.getUid());
        assertEquals(fullReturnDTO.getErrMsg(), returnDTO.getErrMsg());
        assertEquals(fullReturnDTO.getRespCode(), returnDTO.getRespCode());
        assertEquals(fullReturnDTO.getStocks(), returnDTO.getStocks());
        assertEquals(fullReturnDTO.getUsername(), returnDTO.getUsername());
        assertEquals(fullReturnDTO.getFirstname(), returnDTO.getFirstname());
        assertEquals(fullReturnDTO.getLastname(), returnDTO.getLastname());
        assertEquals(fullReturnDTO.getCash(), returnDTO.getCash(), 0.0);
        assertEquals(fullReturnDTO.getMsg(), returnDTO.getMsg());
        assertEquals(fullReturnDTO.getEmail(), returnDTO.getEmail());
    }
}
