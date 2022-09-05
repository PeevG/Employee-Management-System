package com.example.employemanagementsystem.config;

import com.example.employemanagementsystem.model.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                  .authorizeRequests()
                  .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                  .antMatchers("/", "/users/login", "/users/registration").permitAll()
                  .antMatchers("/projects")
                  .hasAnyRole(UserRoleEnum.USER.name(), UserRoleEnum.IT_LEAD.name(), UserRoleEnum.ADMIN.name())
                  .antMatchers("/employees/all", "/departments/all")
                  .hasAnyRole(UserRoleEnum.HR_MANAGER.name(), UserRoleEnum.ADMIN.name())
                  .antMatchers("/**").authenticated()
                .and()
                  .formLogin()
                  .loginPage("/users/login")
                  .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                  .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                  .defaultSuccessUrl("/")
                  .failureForwardUrl("/users/login-error")
                .and()
                  .logout()
                  // This is the URL which spring will implement for me and will log the user out.
                  .logoutUrl("/users/logout")
                  // Where to go after logout.
                  .logoutSuccessUrl("/users/login")
                  // Remove the session from server
                  .invalidateHttpSession(true)
                  // Delete the cookies that references to my session.
                  .deleteCookies("JSESSIONID");
    }
}
