package OWurst.Investment_Simulator.DTO;

import java.util.List;

// using build pattern to create return objects, as they will have a variable amount of return fields
public class ReturnBuilder {
    private int uid;
    private String errMsg;
    private String msg;
    private int respCode;
    private List<StockDTO> stocks;
    private String username;
    private String firstname;
    private String lastname;
    private double cash;
    private String email;

    public ReturnBuilder() {
    }

    public ReturnBuilder withUid(int uid) {
        this.uid = uid;
        return this;
    }

    public ReturnBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ReturnBuilder withMsg(String msg) {
        this.msg = msg;
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

    public ReturnBuilder withStocks(List<StockDTO> stocks) {
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
        return new ReturnDTO(uid, errMsg, respCode, stocks, username, firstname, lastname, cash, msg, email);
    }
}
