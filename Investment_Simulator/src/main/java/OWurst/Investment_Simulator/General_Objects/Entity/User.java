package OWurst.Investment_Simulator.General_Objects.Entity;

public class User {
    public static final double STARTING_CASH = 1000000.0;

    private int id;

    private String name;
    private String username;
    private String password;
    private String email;
    private double totalCash;
    private double totalWorth;

    public User(String name, String username, String password, String email) {
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
