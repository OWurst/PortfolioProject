package OWurst.Investment_Simulator.Utils;

import jakarta.servlet.http.HttpServletRequest;

public class SessionData {
    public static boolean validateSession(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        if (request.getSession().getAttribute("USER_ID") == null) {
            return false;
        }
        return true;
    }

    public static int getUserId(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("USER_ID");
    }
}
