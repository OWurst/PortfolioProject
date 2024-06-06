package OWurst.Investment_Simulator.Utils;

public class InputExceptions {
    public static class IllegalRegistrationException extends Exception {
        public IllegalRegistrationException(String message) {
            super(message);
        }
    }

    public static class InvalidLoginException extends Exception {
        public InvalidLoginException() {
            super("Invalid login credentials.");
        }
    }
}
