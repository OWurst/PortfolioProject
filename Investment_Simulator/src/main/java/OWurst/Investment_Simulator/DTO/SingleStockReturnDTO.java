package OWurst.Investment_Simulator.DTO;

import OWurst.Investment_Simulator.Entity.Stock;

// I believe I still need this DTO regardless of Builder pattern, as I need to return a list of stocks
public class SingleStockReturnDTO {
    String ticker;
    String name;
    String industry;
    double price;
    long count;

    public SingleStockReturnDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.name = stock.getCompany();
        this.price = stock.getPrice();
        this.count = stock.getCount();
        this.industry = stock.getIndustry();
    }
}
