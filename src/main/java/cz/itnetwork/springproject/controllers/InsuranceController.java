package cz.itnetwork.springproject.controllers;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.mappers.InsuranceMapper;
import org.springframework.ui.Model;
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import cz.itnetwork.springproject.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Tato třída vypisuje pojištění
 */
@Controller
@RequestMapping ("/insurance")
public class InsuranceController {

    /**
     * Získání instance služby InsuranceService
     */
    @Autowired
    private InsuranceService insuranceService;

    /**
     * Získání instance služby InsuranceMapper
     */
    @Autowired
    private InsuranceMapper insuranceMapper;

    /**
     * Vykreslení stránky s přehledem pojištění
     * @param model s daty pojištění
     * @return hlavní strana s pojištěními
     */
    @GetMapping
    public String renderIndex(Model model) {
        List<InsuranceDTO> insurance = insuranceService.getAll();
        model.addAttribute("insurance", insurance);

        return "pages/insurance/index";
    }

    /**
     * Zobrazení prázdného formuláře
     * @param insurance základní parametry pro vyplnění
     * @return formulář pro vytvoření pojištění
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute InsuranceDTO insurance) {

        return "/pages/insurance/create";
    }

    /**
     * Pošle vyplněný formulář, ošetří chyby ve formuláři
     * @param insurance vyplněná data pojištění
     * @param result výsledek ošetření chyb
     * @param redirectAttributes Flash messages
     * @return uložené pojištění
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("create")
    public String createInsurance(
            @Valid @ModelAttribute InsuranceDTO insurance,
            BindingResult result,
            RedirectAttributes redirectAttributes
            ) {
        if (result.hasErrors())
            return renderCreateForm(insurance);

        insuranceService.create(insurance);
        redirectAttributes.addFlashAttribute("success", "Pojištění úspěšně vytvořeno.");

        return "redirect:/insurance";
    }

    /**
     * Vypíše detail konkrétního pojištění
     * @param insuranceId unikátní klíč pojištění
     * @param model data s pojištěním
     * @return detail pojištění
     */
    @GetMapping ("{insuranceId}")
    public String renderDetail(
            @PathVariable long insuranceId,
            Model model
    ) {

        InsuranceDTO insurance = insuranceService.getById(insuranceId);
        model.addAttribute("insurance", insurance);

        return "/pages/insurance/detail";
    }

    /**
     * Vykreslí editační formulář pojištění
     * @param insuranceId unikátní klíč pojištění
     * @param insurance data pojištění
     * @return formulář k úpravám pojištění
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{insuranceId}")
    public String renderEditForm(
            @PathVariable Long insuranceId,
            InsuranceDTO insurance
    ) {
        InsuranceDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceDTO(fetchedInsurance, insurance);

        return "pages/insurance/edit";
    }

    /**
     * Odešle upravené pojištění a zvaliduje opravené údaje
     * @param insuranceId unikátní klíč pojištění
     * @param insurance data pojištění
     * @param result ošetření chyb
     * @param redirectAttributes Flash messages
     * @return seznam pojištění
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{insuranceId}")
    public String editInsurance(
            @PathVariable long insuranceId,
            @Valid InsuranceDTO insurance,
            BindingResult result,
            RedirectAttributes redirectAttributes // Flash message
    ) {
        if (result.hasErrors())
            return renderEditForm(insuranceId, insurance);

        insurance.setInsuranceId(insuranceId);
        insuranceService.edit(insurance);
        redirectAttributes.addFlashAttribute("success", "Pojištění úspěšně upraveno.");

        return "redirect:/insurance";
    }

    /**
     * Smaže pojištění
     * @param insuranceId unikátní klíč pojištění
     * @param redirectAttributes Flash messages
     * @return seznam pojištění
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuranceId}")
    public String deleteInsurance(
            @PathVariable long insuranceId,
            RedirectAttributes redirectAttributes // Flash message
    ) {
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Pojištění úspěšně smazáno.");

        return "redirect:/insurance";
    }
}

