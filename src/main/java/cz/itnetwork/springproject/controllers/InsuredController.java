package cz.itnetwork.springproject.controllers;

/**
 * Importy
 */
import cz.itnetwork.springproject.models.dto.InsuranceDTO;
import cz.itnetwork.springproject.models.dto.InsuredDTO;
import cz.itnetwork.springproject.models.dto.mappers.InsuredMapper;
import cz.itnetwork.springproject.models.exceptions.InsuredNotFoundException;
import cz.itnetwork.springproject.models.services.InsuranceService;
import cz.itnetwork.springproject.models.services.InsuredService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Tato třída vypisuje pojištění
 */
@Controller
@RequestMapping ("/insured")
public class InsuredController {

    /**
     * Získání instance služby InsuredService
     */
    @Autowired
    private InsuredService insuredService;

    /**
     * Vykreslí stránku s přehledem pojištěnců
     * @param model s daty pojištěnců
     * @return stránka s pojištěnci
     */
    @GetMapping
    public String renderIndex(Model model) {
        List<InsuredDTO> insureds = insuredService.getAll();
        model.addAttribute("insured", insureds);

        return "pages/insured/index";
    }

    /**
     * Vykreslení prázdného formuláře
     * @param insured základní paramtery pro vyplnění formuláře
     * @return prázdný formuláře
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute InsuredDTO insured) {

        return "pages/insured/create";
    }

    /**
     * Odeslání vyplněného formuláře
     * @param insured data s pojištěncem
     * @param result zachycení chyb a validace
     * @param redirectAttributes Flash messages
     * @return pojištěný
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("create")
    public String createInsured(
            @Valid @ModelAttribute InsuredDTO insured,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderCreateForm(insured);

        insuredService.create(insured);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec úspěšně vytvořen.");

        return "redirect:/insured";
    }

    /**
     * Vykreslí detail pojištěnce
     * @param insuredId unikátní klíč pojištěnce
     * @param model data s pojištěncem
     * @return detail pojištěnce
     */
    @GetMapping("{insuredId}")
    public String renderDetail(
            @PathVariable long insuredId,
            Model model
    ) {
        InsuredDTO insured = insuredService.getById(insuredId);
        model.addAttribute("insured", insured);

        return "pages/insured/detail";
    }

    /**
     * Získání instance služby InsuredMapper
     */
    @Autowired
    private InsuredMapper insuredMapper;

    /**
     * Vykreslí editační formulář
     * @param insuredId unikátní klíč pojištěnce
     * @param insured   data pojištěnce
     * @return editační formulář
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("edit/{insuredId}")
    public String renderEditForm(
            @PathVariable Long insuredId,
            InsuredDTO insured
    ) {
        InsuredDTO fetchedInsured = insuredService.getById(insuredId);
        insuredMapper.updateInsuredDTO(fetchedInsured, insured);

        return "pages/insured/edit";
    }

    /**
     * Odešle editační formulář
     * @param insuredId unikátní klíč pojištěnce
     * @param insured data pojištěnce
     * @param result validace a chyby
     * @param redirectAttributes Flash messages
     * @return pojištěný
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("edit/{insuredId}")
    public String editInsured(
            @PathVariable long insuredId,
            @Valid InsuredDTO insured,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderEditForm(insuredId, insured);

        insured.setInsuredId(insuredId);
        insuredService.edit(insured);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec úspěšně upraven.");

        return "redirect:/insured";
    }

    /**
     * Smaže pojištěnce
     * @param insuredId data pojištěnce
     * @param redirectAttributes Flash messages
     * @return přesměruje na pojištěnce
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuredId}")
    public String deleteInsured(
            @PathVariable long insuredId,
            RedirectAttributes redirectAttributes
    ) {
        insuredService.remove(insuredId);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec úspěšně smazán.");

        return "redirect:/insured";
    }

    /**
     * Zpracuje chyby - chybějící pojištěnec
     * @param redirectAttributes validace a chyby
     * @return pojištěnec
     */
    @ExceptionHandler({InsuredNotFoundException.class})
    public String handleInsuredNotFoundException(
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");
        return "redirect:/insured";
    }

    /**
     * Vykreslí formulář pro výběr a následné přiřazení pojištění pojištěnci
     */
    @Autowired
    private InsuranceService insuranceService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/assign/{id}")
    public String renderAssignInsurance(@PathVariable Long id,
                                        Model model
    ) {
        InsuredDTO insured = insuredService.getById(id);
        List<InsuranceDTO> insurance = insuranceService.getAll();

        model.addAttribute("insured", insured);
        model.addAttribute("insurance", insurance);
        model.addAttribute("insuranceDTO", new InsuranceDTO());
        model.addAttribute("insuredId", id);

        return "pages/insured/assign";
    }

    /**
     * Uloží přiřazené pojištění k pojištěnci
     * @param id unikátní klíč
     * @param insurance pojištění
     * @return přesměruje na detail pojištěnce
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/assign/{id}")
    public String assignInsurance(
            @PathVariable Long id,
            @ModelAttribute InsuranceDTO insurance
    ) {

        InsuredDTO insured = insuredService.getById(id);
        insured.addInsurance(insurance);
        insuredService.edit(insured);

        return "redirect:/insured/" + id;
    }
}