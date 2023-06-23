package OWurst.Investment_Simulator.General_Objects.Entity;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

@Entity
public class User {
    public static final double STARTING_CASH = 1000000.0;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 40, unique = false, nullable = false)
    private String name;

    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 40, unique = false, nullable = false)
    private String email;

    @Column
    private double totalCash;

    @Column
    private double totalWorth;

    public User(int id, String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.totalCash = STARTING_CASH;
        this.totalWorth = STARTING_CASH;
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.totalCash = STARTING_CASH;
        this.totalWorth = STARTING_CASH;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public void setTotalWorth(double totalWorth) {
        this.totalWorth = totalWorth;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public double getTotalWorth() {
        return totalWorth;
    }

    public int getId() {
        return id;
    }
}
