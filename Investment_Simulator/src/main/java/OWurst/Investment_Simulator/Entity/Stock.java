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
    int stockId;

    @Column(length = 10, unique = false, nullable = false)
    private String ticker;

    @Column(length = 40, unique = false, nullable = false)
    private String company;

    @Column(length = 40, unique = false, nullable = true)
    private String sector;

    @Column(length = 40, unique = false, nullable = true)
    private String industry;

    Stock() {
    }

    public Stock(String ticker, String company) {
        this.ticker = ticker;
        this.company = company;
    }

    public Stock(String ticker, String company, String sector, String industry) {
        this.ticker = ticker;
        this.company = company;
        this.sector = sector;
        this.industry = industry;
    }

    public int getStockId() {
        return stockId;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompany() {
        return company;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setCompany(String company) {
        this.company = company;
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

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", ticker='" + ticker + '\'' +
                ", company='" + company + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
