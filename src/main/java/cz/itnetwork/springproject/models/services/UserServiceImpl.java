package cz.itnetwork.springproject.models.services;

/**
 * Importy
 */
import cz.itnetwork.springproject.data.entities.UserEntity;
import cz.itnetwork.springproject.data.repositories.UserRepository;
import cz.itnetwork.springproject.models.dto.UserDTO;
import cz.itnetwork.springproject.models.exceptions.DuplicateEmailException;
import cz.itnetwork.springproject.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Tato třída implementuje rozhraní UserService a následně pracuje s logikou
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Získání instance služby UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Získání instance služby PasswordEncoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Vytvoří uživatele
     * @param user data uživatele
     * @param isAdmin nastavení admina
     */
    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new PasswordsDoNotEqualException();

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * Načte uloženého uživatele
     * @param username email
     * @return repositář s funkcí hledání podle emailu
     * @throws UsernameNotFoundException výjimka pro nenalezeného uživatele
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
}
