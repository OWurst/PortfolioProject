package OWurst.Investment_Simulator.DTO;

import java.sql.Date;

public class StockDTO {
    private String ticker;
    private String company;
    private String industry;
    private String sector;
    private double cost;
    private long count;
    private Date date;

    public StockDTO(String ticker, String company, double cost, long count) {
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.count = count;
        this.date = new Date(System.currentTimeMillis());
    }

    public StockDTO(String ticker, String company, double cost, long count, Date date) {
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.count = count;
        this.date = date;
    }

    public StockDTO(String ticker, String company, double cost, long count, String industry, String sector) {
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.count = count;
        this.industry = industry;
        this.sector = sector;
        this.date = new Date(System.currentTimeMillis());
    }

    public StockDTO(String ticker, String company, String sector, String industry, double cost) {
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.industry = industry;
        this.sector = sector;
    }

    public StockDTO(String ticker, String company, double cost, long count, String industry, String sector, Date date) {
        this.ticker = ticker;
        this.company = company;
        this.cost = cost;
        this.count = count;
        this.industry = industry;
        this.sector = sector;
        this.date = date;
    }

    public StockDTO(String ticker, double cost, long count) {
        this.ticker = ticker;
        this.cost = cost;
        this.count = count;
    }

    public StockDTO(String ticker, long count) {
        this.ticker = ticker;
        this.count = count;
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
