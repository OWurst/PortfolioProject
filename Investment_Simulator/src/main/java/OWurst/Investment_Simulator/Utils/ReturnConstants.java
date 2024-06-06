package OWurst.Investment_Simulator.Utils;

import OWurst.Investment_Simulator.DTO.ReturnBuilder;
import OWurst.Investment_Simulator.DTO.ReturnDTO;

public class ReturnConstants {
    public static ReturnDTO badSession() {
        return new ReturnBuilder().withErrMsg("Error: Session could not be found.").withRespCode(400).build();
    }

    public static ReturnDTO handled400Error(String msg) {
        return new ReturnBuilder().withErrMsg(msg).withRespCode(400).build();
    }

    public static ReturnDTO unverifiedSession() {
        return new ReturnBuilder().withErrMsg("Error: Session could not be verified.").withRespCode(400).build();
    }

    public static ReturnDTO unknownError(String msg) {
        return new ReturnBuilder().withErrMsg(msg).withRespCode(500).build();
    }

    public static ReturnDTO simpleSuccess(String msg, int uid) {
        return new ReturnBuilder().withMsg(msg).withRespCode(200).withUid(uid).build();
    }
}
