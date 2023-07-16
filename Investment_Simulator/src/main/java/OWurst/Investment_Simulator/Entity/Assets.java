package OWurst.Investment_Simulator.Entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "Assets")
public class Assets {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private double cash;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ticker_stock_mapping", joinColumns = {
            @JoinColumn(name = "map_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "stock_id", referencedColumnName = "id") })
    @MapKey(name = "ticker")
    private Map<String, Stock> stockSet;

    public Assets() {
        this.cash = 100000.00;
        stockSet = new HashMap<String, Stock>();
    }

    public Map<String, Stock> getStockSet() {
        return stockSet;
    }

    public double getCash() {
        return cash;
    }

    public void decCash(double toDec) {
        this.cash -= toDec;
    }

    public void incCash(double toInc) {
        this.cash += toInc;
    }

    public void addStock(String ticker, Stock stock) {
        stockSet.put(ticker, stock);
    }

    public Stock getStock(String ticker) {
        return stockSet.get(ticker);
    }
}
