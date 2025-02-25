package cz.itnetwork.springproject.data.entities;

/**
 * Importy
 */
import jakarta.persistence.*;
import java.util.List;

/**
 * Tato třída reprezentuje entitu pojištěnce v databázi
 */
@Entity
public class InsuredEntity {

    /**
     * Vazba 1:N pro propojení pojištěnce a pojištění
     */
    @OneToMany(mappedBy = "insured", cascade = CascadeType.ALL)
    private List<InsuranceEntity> insurance;

    /**
     * Udává entitě unikátní ID (číslo)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Každé nové ID se zvýší o jedno
    private long insuredId;

    @Column(nullable = false) // Když je pole prázdné, vyvolá to chybu = nepůjde uložit bez vyplnění
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postcode;

    /**
     * Getter/Setter
     */
    public long getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(long insuredId) {
        this.insuredId = insuredId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    /**
     * Konec Getter/Setter
     */
}
