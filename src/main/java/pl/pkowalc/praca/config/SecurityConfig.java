package pl.pkowalc.praca.config;

import java.util.Arrays;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        final var username = "krystiano";
        UserDetails user = users.username(username).password("ronaldo").roles("USER").build();
        final var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!userDetailsManager.userExists(username)) {
            userDetailsManager.createUser(user);
        }
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/api/register", "/api/logout").permitAll()
                        .antMatchers("/**").authenticated())
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().ignoringAntMatchers("/api/register").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .httpBasic(withDefaults())
                .logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID").logoutSuccessHandler(getLogoutSuccessHandler());
        return http.build();
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(200);
        };
    }

    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getCorsConfiguration());
        return source;
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("content-length", "Set-Cookie", "X-XSRF-TOKEN"));
        config.setMaxAge(3600L);
        return config;
    }

}
