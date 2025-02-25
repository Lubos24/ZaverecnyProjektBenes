package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Rozhraní pro business logiku a práci s databází uživatele
 */
public interface UserService extends UserDetailsService {

    void create(UserDTO user, boolean isAdmin);

}
