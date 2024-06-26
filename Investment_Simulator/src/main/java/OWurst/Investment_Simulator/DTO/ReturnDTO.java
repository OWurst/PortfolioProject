package OWurst.Investment_Simulator.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnDTO {
    private int uid;
    private String errorMsg;
    private String msg;
    private int respCode;
    private List<StockDTO> stocks;
    private String username;
    private String firstname;
    private String lastname;
    private double cash;
    private String email;

    public ReturnDTO(int uid, String errorMsg, int respCode, List<StockDTO> stocks, String username,
            String firstname, String lastname, double cash, String msg, String email) {
        this.uid = uid;
        this.errorMsg = errorMsg;
        this.respCode = respCode;
        this.stocks = stocks;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cash = cash;
        this.msg = msg;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getUid() {
        return uid;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public int getRespCode() {
        return respCode;
    }

    public List<StockDTO> getStocks() {
        return stocks;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public double getCash() {
        return cash;
    }

    @Override
    public String toString() {
        return this.toString(false);
    }

    // for test purposes
    public String toString(boolean test) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            if (test) {
                throw new Exception();
            }
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            String resp = "{"
                    + "\"errorMsg\": \"Error converting ReturnDTO to JSON\","
                    + "\"respCode\": 500,"
                    + "\"uid\": " + this.uid
                    + "}";
            return resp;
        }
    }
}
