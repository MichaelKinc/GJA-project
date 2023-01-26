package cz.welli.letmein.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Method of this class is responsible for redirect after login
 */
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * User is redirected to /kiosk when has role ROLE_KIOSK and to /home otherwise
     *
     * @param httpServletRequest httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param authentication authentication
     * @throws IOException If an input or output exception occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_KIOSK")) {
            httpServletResponse.sendRedirect("/kiosk");
        } else {
            httpServletResponse.sendRedirect("/home");
        }
    }
}
