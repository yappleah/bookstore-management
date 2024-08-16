package com.jac.bookstoreProject.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // Custom logic to determine the target URL
        String targetUrl = determineTargetUrl(authentication);

        // Redirect the user to the appropriate page
        response.sendRedirect(targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        // Your logic to determine the target URL based on the user's roles or other criteria
        // For example:
        // if (userHasRole(authentication, "ADMIN")) {
        //     return "/admin/dashboard";
        // } else {
        //     return "/user/profile";
        // }
        return "/";
    }

}
