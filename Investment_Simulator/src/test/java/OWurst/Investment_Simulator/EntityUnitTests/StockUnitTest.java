package OWurst.Investment_Simulator.EntityUnitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import OWurst.Investment_Simulator.DTO.StockDTO;
import OWurst.Investment_Simulator.Entity.Stock;

public class StockUnitTest {
    Stock baseStock;
    Stock unpricedStock;
    Stock fullStock;
    Stock dtoConstructorStock;
    StockDTO stockDTO;

    @Before
    public void setUp() {
        baseStock = new Stock();
        unpricedStock = new Stock("TSLA", "Tesla", "Consumer Cyclical", "Auto Manufacturers", 0.0);
        fullStock = new Stock("TSLA", "Tesla", "Consumer Cyclical", "Auto Manufacturers", 700.0);
        stockDTO = new StockDTO("TSLA", "Tesla", "Consumer Cyclical", "Auto Manufacturers", 700.0);
        dtoConstructorStock = new Stock(stockDTO);
    }

    @Test
    public void testBaseConstructorReturnsCorrectType() {
        assertTrue(baseStock instanceof Stock);
    }

    @Test
    public void testUnpricedStockConstructorReturnsCorrectType() {
        assertTrue(unpricedStock instanceof Stock);
    }

    @Test
    public void testFullStockConstructorReturnsCorrectType() {
        assertTrue(fullStock instanceof Stock);
    }

    @Test
    public void testDTOConstructorReturnsCorrectType() {
        assertTrue(dtoConstructorStock instanceof Stock);
    }

    @Test
    public void testGetTicker() {
        assertEquals("TSLA", fullStock.getTicker());
    }

    @Test
    public void testGetCompany() {
        assertEquals("Tesla", fullStock.getCompany());
    }

    @Test
    public void testGetSector() {
        assertEquals("Consumer Cyclical", fullStock.getSector());
    }

    @Test
    public void testGetIndustry() {
        assertEquals("Auto Manufacturers", fullStock.getIndustry());
    }

    @Test
    public void testGetPrice() {
        assertEquals(700.0, fullStock.getPrice(), 0.0);
    }

    @Test
    public void testGetStockId() {
        assertEquals(0, fullStock.getStockId());
    }

    @Test
    public void testSetTicker() {
        assertNull(baseStock.getTicker());
        baseStock.setTicker("AAPL");
        assertEquals("AAPL", baseStock.getTicker());
    }

    @Test
    public void testSetCompany() {
        assertNull(baseStock.getCompany());
        baseStock.setCompany("Apple");
        assertEquals("Apple", baseStock.getCompany());
    }

    @Test
    public void testSetSector() {
        assertNull(baseStock.getSector());
        baseStock.setSector("Technology");
        assertEquals("Technology", baseStock.getSector());
    }

    @Test
    public void testSetIndustry() {
        assertNull(baseStock.getIndustry());
        baseStock.setIndustry("Consumer Electronics");
        assertEquals("Consumer Electronics", baseStock.getIndustry());
    }

    @Test
    public void testSetPrice() {
        assertEquals(0.0, baseStock.getPrice(), 0.0);
        baseStock.setPrice(100.0);
        assertEquals(100.0, baseStock.getPrice(), 0.0);
    }

    @Test
    public void testToString() {
        assertEquals(
                "Stock{stockId=0, ticker='TSLA', company='Tesla', sector='Consumer Cyclical', industry='Auto Manufacturers', price=700.0}",
                fullStock.toString());
    }
}
