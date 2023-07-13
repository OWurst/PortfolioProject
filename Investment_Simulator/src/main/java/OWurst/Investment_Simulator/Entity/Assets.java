package OWurst.Investment_Simulator.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Stock.class)
    @JoinColumn(name = "fk_assetId", referencedColumnName = "id")
    private List<Stock> stockSet;

    public Assets() {
        this.cash = 100000.00;
        stockSet = new ArrayList<Stock>();
    }

    public double getCash() {
        return cash;
    }
}
