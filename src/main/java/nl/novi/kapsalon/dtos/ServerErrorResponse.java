package nl.novi.kapsalon.dtos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@RequiredArgsConstructor
public class ServerErrorResponse implements ErrorResponse {

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(this.httpStatus.value());
    }

    @Override
    public ProblemDetail getBody() {
        return ProblemDetail.forStatusAndDetail(getStatusCode(), this.message);
    }
}
