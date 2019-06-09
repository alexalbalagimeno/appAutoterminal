package dreamteam.iam.cat.autoterminalemployee;

/**
 *
 * Classe vehicle
 */
public class Vehicle {

    String Bastidor;
    String Color;
    String Model;
    String Marca;
    String Caracteristiques;
    String Fila;
    String Columna;
    String Planta;
    String Edifici;
    String DataIngres;
    String DataSortida;

    /**
     * Constructor buit de vehicle
     */
    public Vehicle() {
    }

    /**
     *
     * @param bastidor String numero bastidor
     * @param color String color vehicle
     * @param model String model vehicle
     * @param marca String marca vehicle
     * @param caracteristiques String caracteristiques vehicle
     * @param fila String fila vehicle
     * @param columna String columna vehicle
     * @param planta String planta vehicle
     * @param edifici String edifici vehicle
     * @param dataIngres String data ingres vehicle
     * @param dataSortida String data sortida vehicle
     */
    public Vehicle(String bastidor, String color, String model, String marca, String caracteristiques, String fila, String columna, String planta, String edifici, String dataIngres, String dataSortida) {
        this.Bastidor = bastidor;
        this.Color = color;
        this.Model = model;
        this.Marca = marca;
        this.Caracteristiques = caracteristiques;
        this.Fila = fila;
        this.Columna = columna;
        this.Planta = planta;
        this.Edifici = edifici;
        this.DataIngres = dataIngres;
        this.DataSortida = dataSortida;
    }

    /**
     *
     * @return String numero de bastidor
     */
    public String getBastidor() {
        return Bastidor;
    }

    /**
     *
     * @param bastidor actualitza numero de bastidor
     */
    public void setBastidor(String bastidor) {
        Bastidor = bastidor;
    }

    /**
     *
     * @return String color vehicle
     */
    public String getColor() {
        return Color;
    }

    /**
     *
     * @param color actualitza color vehicle
     */
    public void setColor(String color) {
        Color = color;
    }

    /**
     *
     * @return String model del vehicle
     */
    public String getModel() {
        return Model;
    }

    /**
     *
     * @param model actualitza model del vehicle
     */
    public void setModel(String model) {
        Model = model;
    }

    /**
     *
     * @return String marca del vehicle
     */
    public String getMarca() {
        return Marca;
    }

    /**
     *
     * @param marca actualitza marca del vehicle
     */
    public void setMarca(String marca) {
        Marca = marca;
    }

    /**
     *
     * @return String caracteristiques del vehicle
     */
    public String getCaracteristiques() {
        return Caracteristiques;
    }

    /**
     *
     * @param caracteristiques actualitza caracteristiques vehicle
     */
    public void setCaracteristiques(String caracteristiques) {
        Caracteristiques = caracteristiques;
    }

    /**
     *
     * @return String fila
     */
    public String getFila() {
        return Fila;
    }

    /**
     *
     * @param fila actualitza fila vehicle
     */
    public void setFila(String fila) {
        Fila = fila;
    }

    /**
     *
     * @return String columna vehicle
     */
    public String getColumna() {
        return Columna;
    }

    /**
     *
     * @param columna actualitza columna vehicle
     */
    public void setColumna(String columna) {
        Columna = columna;
    }

    /**
     *
     * @return String planta vehicle
     */
    public String getPlanta() {
        return Planta;
    }

    /**
     *
     * @param planta actualitza planta vehicle
     */
    public void setPlanta(String planta) {
        Planta = planta;
    }

    /**
     *
     * @return String edifici vehicle
     */
    public String getEdifici() {
        return Edifici;
    }

    /**
     *
     * @param edifici actualitza edifici vehicle
     */
    public void setEdifici(String edifici) {
        Edifici = edifici;
    }

    /**
     *
     * @return String data ingres vehicle
     */
    public String getDataIngres() {
        return DataIngres;
    }

    /**
     *
     * @param dataIngres actualitza data ingres vehicle
     */
    public void setDataIngres(String dataIngres) {
        DataIngres = dataIngres;
    }

    /**
     *
     * @return String data sortida vehicle
     */
    public String getDataSortida() {
        return DataSortida;
    }

    /**
     *
     * @param dataSortida actualitza data sortida vehicle
     */
    public void setDataSortida(String dataSortida) {
        DataSortida = dataSortida;
    }
}
