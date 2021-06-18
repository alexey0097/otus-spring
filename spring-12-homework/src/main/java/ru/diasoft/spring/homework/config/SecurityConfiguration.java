package ru.diasoft.spring.homework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.diasoft.spring.homework.filter.JwtRequestFilter;
import ru.diasoft.spring.homework.util.JwtTokenUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Autowired @Lazy
    private UserDetailsService userDetailsService;

    @Value("${security.enable}")
    private Boolean securityEnable;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        if (securityEnable) {
            http
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/v1/authors", "/api/v1/author/*").hasRole(USER)
                .antMatchers("/api/v1/genres", "/api/v1/genre/*").hasRole(ADMIN)
                .antMatchers("/api/v1/books", "/api/v1/book/*").hasAnyRole(ADMIN, USER)
                .antMatchers("/api/v1/book/*/comments", "/api/v1/book/*/comment").hasRole(ADMIN)

                .anyRequest().authenticated()

                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean @Lazy
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(userDetailsService, jwtTokenUtil());
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

}
