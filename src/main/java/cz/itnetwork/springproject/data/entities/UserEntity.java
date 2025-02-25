package cz.itnetwork.springproject.data.entities;

/**
 * Importy
 */
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * Tato třída reprezentuje uživatele v databázi
 */
@Entity
public class UserEntity implements UserDetails {

    /**
     * Udává entitě unikátní ID (číslo)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Každé nové ID se zvýší o jedno
    private long userId;

    @Column(nullable = false, unique = true) // Když je pole prázdné, vyvolá to chybu = nepůjde uložit bez vyplnění. Email musí být unikátní.
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

    /**
     * Využití metody UserDetails, která reprezentuje uživatele a následně je jako přepravka
     * @return udělené přístupy
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (admin? "ADMIN" : "USER"));
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Getter/Setter
     */
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    /**
     * Konec Getter/Setter
     */
}
