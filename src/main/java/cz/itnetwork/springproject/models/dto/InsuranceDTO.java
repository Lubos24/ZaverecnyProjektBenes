package cz.itnetwork.springproject.models.dto;

/**
 * Importy
 */
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

/**
 * Tato třída slouží jako přepravka pro data pojištění
 */
public class InsuranceDTO {

    private Long InsuranceId;

    @NotBlank(message = "Vyplňte název pojištění.")
    private String name;

    @NotBlank(message = "Vyplňte stručné inforamce k použití pojištění.")
    @Size(max = 200, message = "Text je příliš dlouhý.")
    private String information;

    @NotNull(message = "Vyplňte cenu pojištění")
    @Min(value = 1, message = "Cena nesmí být záporná.")
    private Integer price;

    @NotBlank(message = "Vyplňte předmět pojištění.")
    private String subject;

    @NotNull(message = "Vyplňte začátek pojištění.")
    @FutureOrPresent(message = "Datum musí být současné nebo budoucí.")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate valid;

    @NotNull(message = "Vyplňte konec pojištění.")
    @FutureOrPresent(message = "Datum musí být současné nebo budoucí.")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate expiration;

    /**
     * Getter/Setter
     */
    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getInsuranceId() {
        return InsuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        InsuranceId = insuranceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getValid() {
        return valid;
    }

    public void setValid(LocalDate valid) {
        this.valid = valid;
    }
    /**
     * Konec Getter/Setter
     */
}









