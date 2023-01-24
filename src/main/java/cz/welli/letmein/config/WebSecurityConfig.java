package cz.welli.letmein.config;

import cz.welli.letmein.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;


//import jakarta.activation.DataSource


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


@Autowired
private DataSource dataSource;

@Bean
public UserDetailsService userDetailsService() {
return new CustomUserDetails();
}



@Bean
public DaoAuthenticationProvider authenticationProvider() {
DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
authProvider.setUserDetailsService(userDetailsService());
authProvider.setPasswordEncoder(passwordEncoder());

return authProvider;
}

@Override
protected void configure(AuthenticationManagerBuilder auth) {
auth.authenticationProvider(authenticationProvider());
}
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
                .antMatchers("/", "/login", "/register", "/process_register", "/register_success", "/error").permitAll()
                .antMatchers("/home").hasAnyRole("USER", "ADMIN")
                .antMatchers("/home/admin/**").hasAnyRole( "ADMIN")
                .antMatchers("/kiosk").hasRole("KIOSK")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).permitAll()
                .and().logout().permitAll()
                .and().csrf();
        //        http.csrf().disable();
    }



//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.inMemoryAuthentication()
//                .withUser("userrr").password("{noop}userrr").authorities("ROLE_USER").and()
//                .withUser("adminn").password("{noop}adminn").authorities("ROLE_ADMIN").and()
//                .withUser("kioskk").password("{noop}kioskk").authorities("ROLE_KIOSK");
//    }



//todo replace with bcrypt which is commented lower
    @Bean
    public PasswordEncoder passwordEncoder() {
        // This PasswordEncoder is provided for legacy and testing purposes only
        // and is not considered secure. A password encoder that does nothing.
        @SuppressWarnings("deprecation")
        PasswordEncoder pe = NoOpPasswordEncoder.getInstance();

        return pe;
    }

    //@Bean
//public BCryptPasswordEncoder passwordEncoder() {
//return new BCryptPasswordEncoder();
//}

}