package cz.itnetwork.springproject.models.dto;

/**
 * Importy
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Tato třída slouží jako přepravka pro data uživatele
 */
public class UserDTO {

    @Email(message = "Vyplňte validní email.")
    @NotBlank(message = "Vyplňte uživatelský email.")
    private String email;

    @NotBlank(message = "Vyplňte uživatelské heslo.")
    @Size(min = 6, message = "Heslo musí obsahovat alespoň 6 zanků.")
    private String password;

    @NotBlank(message = "Vyplňte uživatelské heslo.")
    @Size(min = 6, message = "Heslo musí obsahovat alespoň 6 znaků.")
    private String confirmPassword;

    /**
     * Getter/Setter
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    /**
     * Konec Getter/Setter
     */
}
