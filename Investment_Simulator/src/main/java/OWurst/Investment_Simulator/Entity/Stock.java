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

    @Column(length = 10, unique = false, nullable = false)
    private String ticker;

    @Column(length = 40, unique = false, nullable = false)
    private String company;

    Stock() {
    }

    public Stock(String ticker, String company) {
        this.ticker = ticker;
        this.company = company;
    }

    public int getId() {
        return id;
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
}
