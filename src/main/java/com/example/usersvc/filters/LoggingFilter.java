package com.example.usersvc.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    private static final String CORRELATION_ID_LOG_VAR = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Check if the client/upstream service sent a correlation ID
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);

        // 2. If not, generate a new one
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        // 3. Put it in the MDC (Mapped Diagnostic Context)
        MDC.put(CORRELATION_ID_LOG_VAR, correlationId);

        // 4. Return it in the response so the client has it for support tickets
        response.addHeader(CORRELATION_ID_HEADER, correlationId);

        try {
            // Continue processing the request
            filterChain.doFilter(request, response);
        } finally {
            // 5. CRITICAL: Clear the MDC after the request completes
            MDC.remove(CORRELATION_ID_LOG_VAR);
        }
    }
}
