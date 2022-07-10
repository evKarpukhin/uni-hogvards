package pro.sky.java.course3.unihogvards.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacultyNotFoundException extends RuntimeException{

    public FacultyNotFoundException(String message) {
        super(message);
    }
}
