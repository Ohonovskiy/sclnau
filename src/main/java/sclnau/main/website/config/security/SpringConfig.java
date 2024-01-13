package sclnau.main.website.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->
                        auth
                                .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(ses ->
                        ses.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .sessionFixation().migrateSession()
                                .invalidSessionUrl("/auth/login")
                                .maximumSessions(1)
                                .expiredUrl("/auth/login?expired=true"))
                .formLogin(form -> form.loginPage("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login?error=true"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/"))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/denied")
                ) // added
                //.accessDeniedHandler(new CustomAccessDeniedHandler()))
                //.authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
}