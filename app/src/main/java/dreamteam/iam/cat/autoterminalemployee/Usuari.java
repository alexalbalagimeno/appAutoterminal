package dreamteam.iam.cat.autoterminalemployee;

/**
 *
 * Usuari amb atributs String nom i String contrasenya
 */
public class Usuari {

    String nom;
    String contra;

    public Usuari(String nom, String contra) {
        this.nom = nom;
        this.contra = contra;
    }

    /**
     * Métode retorna nom
     * @return String nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Métode retorna contrasenya
     * @return String contra
     */
    public String getContra() {
        return contra;
    }
}
