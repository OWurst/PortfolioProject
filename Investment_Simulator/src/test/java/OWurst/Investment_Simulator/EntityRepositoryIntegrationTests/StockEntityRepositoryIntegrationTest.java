package OWurst.Investment_Simulator.EntityRepositoryIntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import OWurst.Investment_Simulator.Repository.ListedStockRepository;
import OWurst.Investment_Simulator.Entity.Stock;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class StockEntityRepositoryIntegrationTest {
    @Autowired
    ListedStockRepository stockRepository;

    List<Stock> stocks;

    @Before
    public void setUp() {
        stocks = new ArrayList<Stock>();

        Stock stock = new Stock("AAPL", "Apple Inc.", "Technology", "Consumer Electronics", 100.0);
        stocks.add(stock);
        stockRepository.save(stock);

        stock = new Stock("GOOGL", "Alphabet Inc.", "Technology", "Internet Content & Information", 200.0);
        stocks.add(stock);
        stockRepository.save(stock);

        stock = new Stock("MSFT", "Microsoft Corporation", "Technology", "Software - Infrastructure", 300.0);
        stocks.add(stock);
        stockRepository.save(stock);

        stock = new Stock("AMZN", "Amazon.com Inc.", "Technology", "Internet Retail", 400.0);
        stocks.add(stock);
        stockRepository.save(stock);

        stock = new Stock("MTB", "M&T Bank Corporation", "Financial Services", "Banks - Regional", 500.0);
        stocks.add(stock);
        stockRepository.save(stock);

        stock = new Stock("JPM", "JPMorgan Chase & Co.", "Financial Services", "Banks - Global", 600.0);
        stocks.add(stock);
        stockRepository.save(stock);
    }

    @Test
    public void testAllStocksAdded() {
        List<Stock> allStocks = stockRepository.findAll();
        assertEquals(6, allStocks.size());
    }

    @Test
    public void testFindByTickerReturnsCorrectStock() {
        Stock stock = stockRepository.findOneByTicker("AAPL");
        assertEquals(stocks.get(0), stock);
    }

    @Test
    public void testFindOneByStockId() {
        for (Stock stock : stocks) {
            Stock foundStock = stockRepository.findOneByStockId(stock.getStockId());
            assertEquals(stock, foundStock);
        }
    }

    @Test
    public void testFindByCompanyContainingReturnsCorrectStocks() {
        List<Stock> foundStocks = stockRepository.findByCompanyContaining("Inc.");
        assertEquals(3, foundStocks.size());

        ArrayList<String> expectedTickers = new ArrayList<String>();
        expectedTickers.add("AAPL");
        expectedTickers.add("GOOGL");
        expectedTickers.add("AMZN");
        for (Stock s : foundStocks) {
            assertTrue(expectedTickers.contains(s.getTicker()));
        }
    }

    @Test
    public void testFindByTickerContainingReturnsCorrectStocks() {
        List<Stock> foundStocks = stockRepository.findByTickerContaining("M");
        assertEquals(4, foundStocks.size());

        ArrayList<String> expectedTickers = new ArrayList<String>();
        expectedTickers.add("MSFT");
        expectedTickers.add("MTB");
        expectedTickers.add("JPM");
        expectedTickers.add("AMZN");
        for (Stock s : foundStocks) {
            assertTrue(expectedTickers.contains(s.getTicker()));
        }
    }

    @Test
    public void testFindBySectorContainingReturnsCorrectStocks() {
        List<Stock> foundStocks = stockRepository.findBySectorContaining("Technology");
        assertEquals(4, foundStocks.size());

        ArrayList<String> expectedTickers = new ArrayList<String>();
        expectedTickers.add("AAPL");
        expectedTickers.add("GOOGL");
        expectedTickers.add("MSFT");
        expectedTickers.add("AMZN");
        for (Stock s : foundStocks) {
            assertTrue(expectedTickers.contains(s.getTicker()));
        }
    }

    @Test
    public void testFindOneByTicker() {
        Stock expectedStock = stocks.get(0);
        Stock stock = stockRepository.findOneByTicker(expectedStock.getTicker());
        assertEquals(expectedStock, stock);
    }

    @Test
    public void testAddDuplicatTickerFails() {
        Stock stock2 = new Stock();
        stock2.setTicker("AAPL");

        assertThrows(DataIntegrityViolationException.class, () -> {
            stockRepository.saveAndFlush(stock2);
        });
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock("TSLA", "Tesla Inc.", "Technology", "Auto Manufacturers", 700.0);
        stockRepository.save(stock);

        Stock foundStock = stockRepository.findOneByTicker("TSLA");
        assertEquals(stock, foundStock);
    }

    @Test
    public void testDeleteStock() {
        Stock stock = stockRepository.findOneByTicker("AAPL");
        stockRepository.delete(stock);

        Stock foundStock = stockRepository.findOneByTicker("AAPL");
        assertNull(foundStock);
    }

    @Test
    public void testUpdateStock() {
        Stock stock = stockRepository.findOneByTicker("AAPL");
        stock.setPrice(150.0);
        stockRepository.save(stock);

        stock = stockRepository.findOneByTicker("AAPL");
        int id = stock.getStockId();

        Stock foundStock = stockRepository.findOneByStockId(id);
        assertEquals(150.0, foundStock.getPrice(), 0.0);
    }

    @Test
    public void testAddNullTickerFails() {
        Stock stock = new Stock();
        assertThrows(DataIntegrityViolationException.class, () -> {
            stockRepository.saveAndFlush(stock);
        });
    }
}
