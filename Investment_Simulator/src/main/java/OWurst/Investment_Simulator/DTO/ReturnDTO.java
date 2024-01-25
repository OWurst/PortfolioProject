package OWurst.Investment_Simulator.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnDTO {
    private int uid;
    private String errMsg;
    private int respCode;
    private List<SingleStockReturnDTO> stocks;
    private String username;
    private String firstname;
    private String lastname;
    private double cash;

    public ReturnDTO(int uid, String errMsg, int respCode, List<SingleStockReturnDTO> stocks, String username,
            String firstname, String lastname, double cash) {
        this.uid = uid;
        this.errMsg = errMsg;
        this.respCode = respCode;
        this.stocks = stocks;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cash = cash;
    }

    public int getUid() {
        return uid;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public int getRespCode() {
        return respCode;
    }

    public List<SingleStockReturnDTO> getStocks() {
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

    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            String resp = "{"
                    + "\"errMsg\": \"Error converting ReturnDTO to JSON\","
                    + "\"respCode\": 500,"
                    + "\"uid\": " + this.uid
                    + "}";
            return resp;
        }
    }
}
