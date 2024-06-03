package OWurst.Investment_Simulator.DTO;

import java.util.Date;

public class StockDTO {
    private String ticker;
    private String company;
    private String industry;
    private String sector;
    private double cost;
    private long count;
    private Date date;

    public StockDTO() {
    }

    public StockDTO(String ticker, double cost, long count) {
        // constructor for returning simple changes (change in ownership, change in
        // price)
        this(ticker, null, cost, count, null, null, null);
    }

    public StockDTO(String ticker, double cost, Date date) {
        // constructor for returning historical data
        this(ticker, null, cost, 0, null, null, date);
    }

    public StockDTO(String ticker, String company, String sector, String industry, double cost) {
        // constructor for returning full current stock information
        // (searching for a stock, building stock table)
        this(ticker, company, cost, 0, industry, sector, null);
    }

    public StockDTO(String ticker, String company, double cost, long count, String industry, String sector) {
        // constructor for returning full current stock information for a given user
        this(ticker, company, cost, count, industry, sector, null);
    }

    public StockDTO(String ticker, String company, double cost, long count, String industry, String sector, Date date) {
        // overloaded constructor for returning full stock information
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.count = count;
        this.industry = industry;
        this.sector = sector;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
