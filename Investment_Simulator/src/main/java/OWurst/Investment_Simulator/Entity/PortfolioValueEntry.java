package OWurst.Investment_Simulator.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class PortfolioValueEntry {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    int entryId;

    @Column
    double value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date entryDate;

    PortfolioValueEntry() {
    }

    public PortfolioValueEntry(double value, Date entryDate) {
        this.value = value;
        this.entryDate = entryDate;
    }

    public int getEntryId() {
        return entryId;
    }

    public double getValue() {
        return value;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
