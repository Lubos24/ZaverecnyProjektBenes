package cz.itnetwork.springproject.controllers;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.UserDTO;
import cz.itnetwork.springproject.models.exceptions.DuplicateEmailException;
import cz.itnetwork.springproject.models.exceptions.PasswordsDoNotEqualException;
import cz.itnetwork.springproject.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Tato třída vypisuje přihlašovací html
 */
@Controller
@RequestMapping("/account")
public class AccountController {


    /**
     * Atribut UserService
     */
    @Autowired
    private UserService userService;

    /**
     * Vykreslení přihlašovacího formuláře
     * @return přihlašovací html
     */
    @GetMapping("login")
    public String renderLogin() {
        return "/pages/account/login.html";
    }

    /**
     * Vykreslení registračního formuláře
     * @param userDTO - přepravka uživatele
     * @return registrační formuláře
     */
    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {
        return "/pages/account/register";
    }

    /**
     * Pošle vyplněný registrační formulář včetně zachycení a vykreslení chyb uživateli
     * @param userDTO - přepravka uživatele
     * @param result - zachycení a zpracování chyb validace
     * @param redirectAttributes - využití k Flash messages
     * @return přihlašovací formulář
     */
    @PostMapping("register")
    public String register(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderRegister(userDTO);

        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email se shoduje s již registrovaným uživatelem.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "Zadejte stejná hesla.");
            result.rejectValue("confirmPassword", "error", "Zadejte stejná hesla.");
            return "/pages/account/register";
        }

        redirectAttributes.addFlashAttribute("success", "Uživatel úspěšně zaregistrován");
        return "redirect:/account/login";
    }
}
