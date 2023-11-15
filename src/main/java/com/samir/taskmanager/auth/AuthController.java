package com.samir.taskmanager.auth;


import com.samir.taskmanager.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository httpSessionSecurityContextRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        System.out.println(loginDTO);
        try {
            UsernamePasswordAuthenticationToken authenticationRequest =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(), loginDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationRequest);

            // Create a SecurityContext and set the Authentication in it
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            // Save the SecurityContext in the HttpSession
            httpSessionSecurityContextRepository.saveContext(context, request, response);

            return ResponseEntity.ok("login success");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Invalidate the HttpSession
            request.getSession().invalidate();

            // Clear the SecurityContext
            SecurityContextHolder.clearContext();

            return ResponseEntity.ok("logout success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("User is logged in");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
        }
    }
}
