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
                .withUser("employee").password("{noop}employee").authorities("ROLE_USER").and()
                .withUser("javainuse").password("{noop}javainuse").authorities("ROLE_USER", "ROLE_ADMIN").and()
                .withUser("kioskk").password("{noop}kioskk").authorities("ROLE_KIOSK");
    }
}




//dytrych
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("tom").password("{noop}123456").roles("USER");
//        auth.inMemoryAuthentication().withUser("bill").password("{noop}123456").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("james").password("{noop}123456").roles("SUPERADMIN");
//    }
//
////    @Autowired
////    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
////                .usersByUsernameQuery("select username,password, enabled from users where username=?")
////                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.authorizeRequests()
////                .antMatchers("/home/**").access("hasRole('ROLE_USER')")
////                .and()
////                .formLogin().loginPage("/login").failureUrl("/login?error")
////                .usernameParameter("username").passwordParameter("password")
////                .and()
////                .logout().logoutSuccessUrl("/login?logout")
////                .and()
////                .exceptionHandling().accessDeniedPage("/403")
////                .and()
////                .csrf();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/home/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//                .antMatchers("/home/admin/**").access("hasRole('ROLE_ADMIN')")
//                .and().formLogin().and().logout();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // This PasswordEncoder is provided for legacy and testing purposes only
//        // and is not considered secure. A password encoder that does nothing.
//        @SuppressWarnings("deprecation")
//        PasswordEncoder pe = NoOpPasswordEncoder.getInstance();
//
//        return pe;
//    }
//}





//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class WebSecurityConfig {
//
//    private final CustomUserDetails userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http
//////                .authorizeHttpRequests((authz) -> authz
//////                        .anyRequest().authenticated()
//////                )
//////                .httpBasic(withDefaults());
//////        return http.build();
////
//////        http
//////                .formLogin(withDefaults());
////        http
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .permitAll()
////                );
////        // ...
////        return http.build();
////    }
//
//    @Bean
//    SecurityFilterChain web(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .requestMatchers("/", "/login", "/registration", "/process_register").permitAll()
////                        .requestMatchers("/home").authenticated()
////                        .requestMatchers("/home/admin").hasRole("ADMIN")
////                        .anyRequest().denyAll()
////        );
//        http
//                .securityMatcher("/home/**")
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin().loginPage("/login").permitAll();
//
//        return http.build();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userService);
//        return provider;
//    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
////        return manager;
////    }
//
////    @Bean
////    public WebSecurityCustomizer webSecurityCustomizer() {
////        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
////    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
////        return http.build();
////    }
//}
//
//
//
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Autowired
////    private DataSource dataSource;
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new CustomUserDetails();
////    }
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(userDetailsService());
////        authProvider.setPasswordEncoder(passwordEncoder());
////
////        return authProvider;
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/home").authenticated()
////                .anyRequest().permitAll()
////                .and()
////                .formLogin()
////                .usernameParameter("email")
////                .defaultSuccessUrl("/users")
////                .permitAll()
////                .and()
////                .logout().logoutSuccessUrl("/").permitAll();
////    }
////}
