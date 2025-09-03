package ba.sum.fpmoz.minibiblioteka.config;

import ba.sum.fpmoz.minibiblioteka.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/register", "/actuator/health").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> {})
            .formLogin(login -> login
                    .defaultSuccessUrl("/api/auth/me", true)  // <-- DODANO: gdje te vodi nakon uspješnog login-a
                    .permitAll())
            .logout(logout -> {});
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository users) {
        return username -> users.findByUsername(username)
            .map(u -> {
                var authorities = u.getRoles().stream()
                    .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getName()))
                    .collect(Collectors.toList());
                return new User(u.getUsername(), u.getPassword(), u.isEnabled(), true, true, true, authorities);
            })
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsService uds, PasswordEncoder pe) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(pe);
        return p;
    }
}
