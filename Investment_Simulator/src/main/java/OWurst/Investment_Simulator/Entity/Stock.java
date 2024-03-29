package OWurst.Investment_Simulator.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(length = 5, unique = false, nullable = false)
    private String ticker;

    @Column(length = 40, unique = false, nullable = false)
    private String company;

    @Column(length = 40, unique = false, nullable = false)
    private String industry;

    @Column
    private double price;

    @Column
    private long count;

    public Stock() {
    }

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
        this.count = count;
    }

    public void setStockPrice(double price) {
        this.price = price;
    }

    public void setStockCount(long count) {
        this.count = count;
    }

    public boolean incrementStockCount(long toAdd) {
        this.count += toAdd;
        return true;
    }

    public boolean decrementStockCount(long toSubtract) {
        if (this.count < toSubtract) {
            return false;
        }

        this.count -= toSubtract;
        return true;
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

    public String getIndustry() {
        return industry;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
