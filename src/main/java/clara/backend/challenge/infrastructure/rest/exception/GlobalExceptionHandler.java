package clara.backend.challenge.infrastructure.rest.exception;

import clara.backend.challenge.domain.exception.ArtistNotFoundException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handle(HandlerMethodValidationException e) {
        var errorDetail = e.getParameterValidationResults().stream()
                .flatMap(parameterValidationResult -> parameterValidationResult.getResolvableErrors().stream())
                .map(MessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(e.getMessage());
        log.info("Validation error: {}", errorDetail);
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), errorDetail);
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ProblemDetail handle(ArtistNotFoundException e) {
        log.info("Artist not found: {}", e.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ProblemDetail handle(FeignException e) {
        log.info("Feign exception caught: {}", e.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(e.status()), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handle(Exception e) {
        log.error("Internal server error: ", e);
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
    }
}
