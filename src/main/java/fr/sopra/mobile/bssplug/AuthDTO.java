package fr.sopra.mobile.bssplug;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ntakpe_j
 */
public class AuthDTO {

    @NotEmpty
    private String j_username;

    @NotNull
    private String j_password;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateNaissance;

    @AssertTrue(message = "Le mot de passe doit être 'password'")
    public boolean isPasswordValid() {
        return "password".equals(j_password);
    }

    @AssertTrue(message = "La date de naissance doit être égale à 21/04/1973")
    public boolean isDateNaissanceValid() {
        System.out.println(dateNaissance);
        return LocalDate.of(1973, 4, 21).isEqual(dateNaissance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public String getJ_username() {
        return j_username;
    }

    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    public String getJ_password() {
        return j_password;
    }

    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
