package com.openapi.cloud.core.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {


        System.out.println("if ----start------------");
        // For API requests, return JSON
        if (isApiRequest(request)) {
            System.out.println("if ----------------");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"access_denied\",\"message\":\"You don't have permission to access this resource\"}");
        } else {
            System.out.println("Else ----------------");

            System.out.println("if ----------------");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"access_denied\",\"message\":\"You don't have permission to access this resource\"}");

            // For browser requests, redirect to custom error page
            response.sendRedirect("/access-denied");
        }
    }

    private boolean isApiRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/") ||
                request.getHeader("Accept").contains("application/json");
    }
}
