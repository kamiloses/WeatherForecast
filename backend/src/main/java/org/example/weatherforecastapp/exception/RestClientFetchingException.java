package src.main.java.org.example.weatherforecastapp.exception;

public class RestClientFetchingException extends RuntimeException {
    public RestClientFetchingException(String message) {
        super(message);
    }
}
