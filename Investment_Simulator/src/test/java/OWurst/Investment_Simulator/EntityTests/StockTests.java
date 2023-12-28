package OWurst.Investment_Simulator.EntityTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import OWurst.Investment_Simulator.Entity.Stock;

@SpringBootTest
class StockTests {
    @Test
    void stockConstructorTest() {
        Stock test = new Stock("mtb", "m&t bank", 100.0, 12);
        assertEquals(Stock.class, test.getClass());
    }

    @Test
    void getTicker() {
        Stock test = createSimpleStock();
        assertEquals("tick", test.getTicker());
    }

    @Test
    void getCompany() {
        Stock test = createSimpleStock();
        assertEquals("company", test.getCompany());
    }

    @Test
    void setAndGetPrice() {
        Stock test = createSimpleStock();
        assertEquals(10.0, test.getPrice());
        test.setStockPrice(5.0);
        assertEquals(5.0, test.getPrice());
    }

    @Test
    void setAndGetCount() {
        Stock test = createSimpleStock();
        assertEquals(5, test.getCount());
        test.setStockCount(10);
        assertEquals(10, test.getCount());
    }

    @Test
    void incrementStockCount() {
        Stock test = createSimpleStock();
        assertEquals(5, test.getCount());
        test.incrementStockCount(5);
        assertEquals(10, test.getCount());
    }

    @Test
    void decrementStockCount() {
        Stock test = createSimpleStock();
        assertEquals(5, test.getCount());
        test.decrementStockCount(5);
        assertEquals(0, test.getCount());
    }

    Stock createSimpleStock() {
        return new Stock("tick", "company", 10.0, 5);
    }
}