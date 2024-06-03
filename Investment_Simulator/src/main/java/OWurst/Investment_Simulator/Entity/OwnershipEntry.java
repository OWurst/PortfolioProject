package OWurst.Investment_Simulator.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// will have a table of stock ids, user ids, and the number of stocks owned
// primary key will be a composite key of stock id and user id
@Entity
public class OwnershipEntry {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ownershipId;

    @Column
    private int stockId;

    @Column
    private int userId;

    @Column
    private int numShares;

    public OwnershipEntry() {
    }

    public OwnershipEntry(int stockId, int userId, int numShares) {
        this.stockId = stockId;
        this.userId = userId;
        this.numShares = numShares;
    }

    public int getOwnershipId() {
        return ownershipId;
    }

    public int getStockId() {
        return stockId;
    }

    public int getUserId() {
        return userId;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean incrementShares(int numShares) {
        if (numShares < 0) {
            return false;
        }
        this.numShares += numShares;
        return true;
    }

    public boolean decrementShares(int numShares) {
        if (numShares < 0) {
            return false;
        }
        if (numShares > this.numShares) {
            return false;
        }
        this.numShares -= numShares;
        return true;
    }
}
