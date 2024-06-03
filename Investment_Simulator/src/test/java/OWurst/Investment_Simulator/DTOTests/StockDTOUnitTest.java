package OWurst.Investment_Simulator.DTOTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.StockDTO;

public class StockDTOUnitTest {
    StockDTO bareDTO;
    StockDTO simpleChangeDTO;
    StockDTO historicalDataPointDTO;
    StockDTO stockTableInfoDTO;
    StockDTO userStockInfoDTO;
    StockDTO fullStockInfoDTO;
    Date date;

    @Before
    public void setUp() {
        date = new Date();

        bareDTO = new StockDTO();
        simpleChangeDTO = new StockDTO("ticker", 1.0, 1);
        historicalDataPointDTO = new StockDTO("ticker", 1.0, date);
        stockTableInfoDTO = new StockDTO("ticker", "company", "sector", "industry", 1.0);
        userStockInfoDTO = new StockDTO("ticker", "company", 1.0, 1, "industry", "sector");
        fullStockInfoDTO = new StockDTO("ticker", "company", 1.0, 1, "industry", "sector", date);
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(bareDTO instanceof StockDTO);
    }

    @Test
    public void testSimpleChangeConstructorReturnsCorrectType() {
        assertTrue(simpleChangeDTO instanceof StockDTO);
    }

    @Test
    public void testHistoricalDataPointConstructorReturnsCorrectType() {
        assertTrue(historicalDataPointDTO instanceof StockDTO);
    }

    @Test
    public void testStockTableInfoConstructorReturnsCorrectType() {
        assertTrue(stockTableInfoDTO instanceof StockDTO);
    }

    @Test
    public void testUserStockInfoConstructorReturnsCorrectType() {
        assertTrue(userStockInfoDTO instanceof StockDTO);
    }

    @Test
    public void testFullStockInfoConstructorReturnsCorrectType() {
        assertTrue(fullStockInfoDTO instanceof StockDTO);
    }

    @Test
    public void testGetDateReturnsCorrectDate() {
        assertEquals(date, fullStockInfoDTO.getDate());
    }

    @Test
    public void testSetDateSetsCorrectDate() {
        Date newDate = new Date();
        bareDTO.setDate(newDate);
        assertEquals(newDate, bareDTO.getDate());
    }

    @Test
    public void testGetTickerReturnsCorrectTicker() {
        assertEquals("ticker", fullStockInfoDTO.getTicker());
    }

    @Test
    public void testSetTickerSetsCorrectTicker() {
        bareDTO.setTicker("newTicker");
        assertEquals("newTicker", bareDTO.getTicker());
    }

    @Test
    public void testGetCompanyReturnsCorrectCompany() {
        assertEquals("company", fullStockInfoDTO.getCompany());
    }

    @Test
    public void testSetCompanySetsCorrectCompany() {
        bareDTO.setCompany("newCompany");
        assertEquals("newCompany", bareDTO.getCompany());
    }

    @Test
    public void testGetCostReturnsCorrectCost() {
        assertEquals(1.0, fullStockInfoDTO.getCost(), 0.0);
    }

    @Test
    public void testSetCostSetsCorrectCost() {
        bareDTO.setCost(2.0);
        assertEquals(2.0, bareDTO.getCost(), 0.0);
    }

    @Test
    public void testGetCountReturnsCorrectCount() {
        assertEquals(1, fullStockInfoDTO.getCount());
    }

    @Test
    public void testSetCountSetsCorrectCount() {
        bareDTO.setCount(2);
        assertEquals(2, bareDTO.getCount());
    }

    @Test
    public void testGetIndustryReturnsCorrectIndustry() {
        assertEquals("industry", fullStockInfoDTO.getIndustry());
    }

    @Test
    public void testSetIndustrySetsCorrectIndustry() {
        bareDTO.setIndustry("newIndustry");
        assertEquals("newIndustry", bareDTO.getIndustry());
    }

    @Test
    public void testGetSectorReturnsCorrectSector() {
        assertEquals("sector", fullStockInfoDTO.getSector());
    }

    @Test
    public void testSetSectorSetsCorrectSector() {
        bareDTO.setSector("newSector");
        assertEquals("newSector", bareDTO.getSector());
    }

    @Test
    public void testSimpleChangeConstructorSetsCorrectData() {
        assertEquals("ticker", simpleChangeDTO.getTicker());
        assertEquals(1.0, simpleChangeDTO.getCost(), 0.0);
        assertEquals(1, simpleChangeDTO.getCount());
        assertNull(simpleChangeDTO.getCompany());
        assertNull(simpleChangeDTO.getIndustry());
        assertNull(simpleChangeDTO.getSector());
        assertNull(simpleChangeDTO.getDate());
    }

    @Test
    public void testHistoricalDataPointConstructorSetsCorrectData() {
        assertEquals("ticker", historicalDataPointDTO.getTicker());
        assertEquals(1.0, historicalDataPointDTO.getCost(), 0.0);
        assertEquals(date, historicalDataPointDTO.getDate());
        assertNull(historicalDataPointDTO.getCompany());
        assertNull(historicalDataPointDTO.getIndustry());
        assertNull(historicalDataPointDTO.getSector());
        assertEquals(0, historicalDataPointDTO.getCount());
    }

    @Test
    public void testStockTableInfoConstructorSetsCorrectData() {
        assertEquals("ticker", stockTableInfoDTO.getTicker());
        assertEquals("company", stockTableInfoDTO.getCompany());
        assertEquals("sector", stockTableInfoDTO.getSector());
        assertEquals("industry", stockTableInfoDTO.getIndustry());
        assertEquals(1.0, stockTableInfoDTO.getCost(), 0.0);
        assertNull(stockTableInfoDTO.getDate());
        assertEquals(0, stockTableInfoDTO.getCount());
    }

    @Test
    public void testUserStockInfoConstructorSetsCorrectData() {
        assertEquals("ticker", userStockInfoDTO.getTicker());
        assertEquals("company", userStockInfoDTO.getCompany());
        assertEquals("sector", userStockInfoDTO.getSector());
        assertEquals("industry", userStockInfoDTO.getIndustry());
        assertEquals(1.0, userStockInfoDTO.getCost(), 0.0);
        assertNull(userStockInfoDTO.getDate());
        assertEquals(1, userStockInfoDTO.getCount());
    }
}
