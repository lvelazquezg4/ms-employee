package mx.com.ms.exception;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericException extends RuntimeException{

	private static final long serialVersionUID = 8091557110509627185L;
	private final String code;

    public GenericException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
