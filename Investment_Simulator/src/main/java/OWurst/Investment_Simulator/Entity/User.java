package OWurst.Investment_Simulator.Entity;

//import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class User {
    public static final double STARTING_CASH = 100000.0;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    private Assets assets;

    public User() {
    }

    public User(String firstname, String lastname, String username, String password, String email) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.assets = new Assets();
    }

    public User(String firstname, String lastname, String username, String password) {
        this(firstname, lastname, username, password, null);
    }

    public double getCash() {
        return this.assets.getCash();
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
