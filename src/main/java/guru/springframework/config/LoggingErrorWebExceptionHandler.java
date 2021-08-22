package guru.springframework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
@Order(-2)
public class LoggingErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggingErrorWebExceptionHandler.class);

    public LoggingErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                           ServerProperties serverProperties, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resourceProperties, serverProperties.getError(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected void logError(ServerRequest request, HttpStatus errorStatus) {
        Throwable ex = getError(request);
        logger.error("Error Occurred:", ex);
        super.logError(request, errorStatus);
    }

}