package cz.welli.letmein.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


//import jakarta.activation.DataSource


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/app.css").permitAll()
                .antMatchers("/", "/login", "/register", "/error").permitAll()
                .antMatchers("/home").hasAnyRole("USER", "ADMIN")
                .antMatchers("/home/admin").hasAnyRole( "ADMIN")
                .antMatchers("/addNewEmployee").hasAnyRole("ADMIN")
                .antMatchers("/kiosk").hasRole("KIOSK")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).permitAll()
                .and().logout().permitAll();

        http.csrf().disable();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
                .withUser("userrr").password("{noop}userrr").authorities("ROLE_USER").and()
                .withUser("adminn").password("{noop}adminn").authorities("ROLE_ADMIN").and()
                .withUser("kioskk").password("{noop}kioskk").authorities("ROLE_KIOSK");
    }
}