package OWurst.Investment_Simulator.DTO;

import java.util.List;

// using build pattern to create return objects, as they will have a variable amount of return fields
public class ReturnBuilder {
    private int uid;
    private String errMsg;
    private int respCode;
    private List<SingleStockReturnDTO> stocks;
    private String username;
    private String firstname;
    private String lastname;
    private double cash = 0.0;

    public ReturnBuilder withUid(int uid) {
        this.uid = uid;
        return this;
    }

    public ReturnBuilder withErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public ReturnBuilder withRespCode(int respCode) {
        this.respCode = respCode;
        return this;
    }

    public ReturnBuilder withStocks(List<SingleStockReturnDTO> stocks) {
        this.stocks = stocks;
        return this;
    }

    public ReturnBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public ReturnBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ReturnBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ReturnBuilder withCash(double cash) {
        this.cash = cash;
        return this;
    }

    public ReturnDTO build() {
        return new ReturnDTO(uid, errMsg, respCode, stocks, username, firstname, lastname, cash);
    }
}
