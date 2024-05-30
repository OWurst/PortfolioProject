package OWurst.Investment_Simulator.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    public static final double STARTING_CASH = 100000.0;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(length = 40, unique = false, nullable = false)
    private String firstName;

    @Column(length = 40, unique = false, nullable = false)
    private String lastName;

    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 40, unique = false, nullable = false)
    private String email;

    @Column
    private double cash;

    public User() {
    }

    public User(String firstname, String lastname, String username, String password, String email) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cash = STARTING_CASH;
    }

    public User(String firstname, String lastname, String username, String password) {
        this(firstname, lastname, username, password, null);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public boolean withdrawCash(double amount) {
        if (amount > cash) {
            return false;
        }
        cash -= amount;
        return true;
    }

    public boolean depositCash(double amount) {
        if (amount < 0) {
            return false;
        }
        cash += amount;
        return true;
    }
}
