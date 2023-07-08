package OWurst.Investment_Simulator.Service.ThirdParty;

import java.net.MalformedURLException;
import java.net.URL;

public class APIWrapper {
    URL darqubeBaseURL = new URL("https://api.darqube.com/data-api");
    private final String apiAccessToken = "e53669f2537b412eae4c21d7cc8c3157";

    public APIWrapper() throws MalformedURLException {
    }
}