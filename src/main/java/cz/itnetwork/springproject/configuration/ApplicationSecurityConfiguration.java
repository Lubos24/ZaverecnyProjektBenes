package cz.itnetwork.springproject.configuration;

/**
 * Importy
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Tato třída nastavuje přístupy do webové aplikace
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurityConfiguration {

    /**
     * Přepsání výchozích pravidel nastavených importovanou knihovnou do pom.xml
     * @param http
     * @return povolení přístupů
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Označení obsahu pro přihlášené uživatele
                .authorizeHttpRequests()
                        .anyRequest()
                            .permitAll()
                        .and()
                .formLogin() // Ošetření přihlášení a následného přesměrování
                        .loginPage("/account/login")
                        .loginProcessingUrl("/account/login")
                        .defaultSuccessUrl("/insured", true)
                        .usernameParameter("email")
                        .permitAll() // Povolí vstup na logování i nepřihlášeným uživatelům
                        .and()
                .logout() // Ošetření přesměrování po odhlášení
                        .logoutUrl("/account/logout")
                        .and()
                .build();
    }

    /**
     * Implementace rozhraní pro hashování hesla
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
