package com.samir.taskmanager.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Log a message to see if the filter is being executed
        System.out.println("CustomFilter is being executed.");

        checkForCookie((HttpServletRequest) request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Authenticated user: " + authentication.getName());
            System.out.println("User authorities: " + authentication.getAuthorities());
        } else {

            System.out.println("Authentication is null.");
        }

        chain.doFilter(request, response);
    }

    private void checkForCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check for the specific cookie you are interested in
                if ("JSESSIONID".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    System.out.println(cookieValue);
                    // Your logic with the cookie value
                    // For example, you might want to validate or use its value
                }
            }
        }
    }
    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }
}
