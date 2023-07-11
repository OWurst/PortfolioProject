package OWurst.Investment_Simulator.Entity;

public class Stock {
    private String ticker;
    private String company;
    private double price;
    private long count;

    public Stock(String ticker, String company) {
        this(ticker, company, -1.0, 0);
    }

    public Stock(String ticker, double price) {
        this(ticker, "", price, 0);
    }

    public Stock(String ticker, double price, long count) {
        this(ticker, "", price, count);
    }

    public Stock(String ticker, String company, double price, long count) {
        this.ticker = ticker;
        this.company = company;
        this.price = price;
    }

    public void setStockPrice(double price) {
        this.price = price;
    }

    public void setStockCount(long count) {
        this.count = count;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompany() {
        return company;
    }

    public double getPrice() {
        return price;
    }

    public long getCount() {
        return count;
    }

}
