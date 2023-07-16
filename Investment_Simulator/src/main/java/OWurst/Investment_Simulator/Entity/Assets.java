package OWurst.Investment_Simulator.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.BatchSize;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKey;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;

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
