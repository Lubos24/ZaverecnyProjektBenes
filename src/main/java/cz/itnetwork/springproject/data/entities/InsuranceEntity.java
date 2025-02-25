package cz.itnetwork.springproject.data.entities;

/**
 * Importy
 */
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Tato třída reprezentuje pojištění v databázi
 */
@Entity
public class InsuranceEntity {

    /**
     * Vazba 1:N pro propojení pojištěnce a pojištění
     */
    @ManyToOne
    @JoinColumn(name = "insured_id", nullable = false)
    private InsuredEntity insured;

    /**
     * Udává entitě unikátní ID (číslo)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Každé nové ID se zvýší o jedno
    private long insuranceId;

    @Column(nullable = false) // Když je pole prázdné, vyvolá to chybu = nepůjde uložit bez vyplnění
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String information;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private LocalDate valid;

    @Column(nullable = false)
    private LocalDate expiration;


    /**
     * Getter/Setter
     */
    public InsuredEntity getInsured() {
        return insured;
    }

    public void setInsured(InsuredEntity insured) {
        this.insured = insured;
    }

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

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
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


