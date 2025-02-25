package cz.itnetwork.springproject.controllers;


/**
 * Importy
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Tato třída vypisuje hlavní stráhky aplikace
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String renderIndex() {
        return "pages/home/index"; //Odkaz na html soubor/šablonu, která se vykreslí v prohlížeči.
    }

}
