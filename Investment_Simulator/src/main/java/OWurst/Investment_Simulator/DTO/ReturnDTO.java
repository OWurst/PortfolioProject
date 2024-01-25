package OWurst.Investment_Simulator.DTO;

public class ReturnDTO {
    int uid;
    String errMsg;
    int respCode;

    public ReturnDTO(int uid, int respCode) {
        this(uid, null, respCode);
    }

    public ReturnDTO(int uid, String errMsg, int respCode) {
        this.errMsg = errMsg;
        this.respCode = respCode;
        this.uid = uid;
    }
}
