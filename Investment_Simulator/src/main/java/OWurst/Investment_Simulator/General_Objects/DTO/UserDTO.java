package OWurst.Investment_Simulator.General_Objects.DTO;

public class UserDTO {

    private int id;

    private String name;
    private String username;
    private String password;
    private String email;
    private double totalCash;
    private double totalWorth;

    public UserDTO() {
        // Empty constructor
    }

    public UserDTO(int id, String username, String password, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public UserDTO(int id, double totalCash, double totalWorth) {
        this.id = id;
        this.totalCash = totalCash;
        this.totalWorth = totalWorth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public double getTotalWorth() {
        return totalWorth;
    }

    public void setTotalWorth(double totalWorth) {
        this.totalWorth = totalWorth;
    }
}
