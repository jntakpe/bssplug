package fr.sopra.mobile.bssplug;

/**
 * @author ntakpe_j
 */
public class UserDTO {

    private final String identifiant;

    private final String ticket;

    private final String userRole = "CLIENT";

    public UserDTO(String identifiant, String ticket) {
        this.identifiant = identifiant;
        this.ticket = ticket;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getTicket() {
        return ticket;
    }

    public String getUserRole() {
        return userRole;
    }
}
