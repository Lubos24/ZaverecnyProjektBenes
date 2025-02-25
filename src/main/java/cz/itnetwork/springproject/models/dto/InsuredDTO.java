package cz.itnetwork.springproject.models.dto;

/**
 * Importy
 */
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * Tato třída slouží jako přepravka pro data k pojištěnci
*/
public class InsuredDTO {

    private long insuredId;

    @NotBlank(message = "Vyplňte jméno")
    private String name;

    @NotBlank(message = "Vyplňte příjmení")
    private String surname;

    @NotBlank(message = "Vyplňte email")
    private String email;

    @NotBlank(message = "Vyplňte telefonní číslo")
    private String phone;

    @NotBlank(message = "Vyplňte ulici a číslo popisné")
    private String address;

    @NotBlank(message = "Vyplňte město")
    private String city;

    @NotBlank(message = "Vyplňte PSČ")
    private String postcode;

    private ArrayList<Object> insurance; //smazat

    /**
     * Getter/Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() { return surname; }

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

    public String getAddress() { return address; }

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

    public long getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(long insuredId) {
        this.insuredId = insuredId;
    }
    /**
     * Konec Getter/Setter
     */

    /**
     * Přidá pojištění a když není, tak vytvoří nový list, aby nebylo null
     * @param insurance pojištění
     */
    public void addInsurance(InsuranceDTO insurance) {

        if (this.insurance == null) {
            this.insurance = new ArrayList<>();
        }
        this.insurance.add(insurance);
    }
}


