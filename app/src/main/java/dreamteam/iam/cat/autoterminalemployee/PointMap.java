package dreamteam.iam.cat.autoterminalemployee;

/**
 *
 * Classe que reprenta un punt amb coordenada X i Y
 */
public class PointMap {

    double x;
    double y;
    int numColumnes = 7;
    int numFiles = 6;
    int columna;
    int fila;

    /**
     * Constructor punt amb X, Y, columna, fila
     * @param x
     * @param y
     * @param columna
     * @param fila
     */
    public PointMap(double x, double y, int columna, int fila){
        this.columna = columna;
        this.fila = fila;
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return double coordenada X
     */
    public double getX() {
        return x;
    }

    /**
     * Actualitza X
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @return double coordenada Y
     */
    public double getY() {
        return y;
    }

    /**
     * Actualitza Y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @return numero de columnes
     */
    public int getNumColumnes() {
        return numColumnes;
    }

    /**
     * Actualitza numero de columnes
     * @param numColumnes
     */
    public void setNumColumnes(int numColumnes) {
        this.numColumnes = numColumnes;
    }

    /**
     *
     * @return int numero de files
     */
    public int getNumFiles() {
        return numFiles;
    }

    /**
     * Actualitza numero de files
     * @param numFiles
     */
    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    /**
     *
     * @return int numero de columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Actualitza numero de columna
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     *
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Actualitza numero de fila
     * @param fila
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Constructor amb coord X i coord Y
     * @param x
     * @param y
     */
    public PointMap(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Calcula distancia a altre punt
     * @param otherPointMap
     * @return
     */
    public double calculaDistanciaAltrePunt(PointMap otherPointMap) {
        return (Math.sqrt(Math.pow(x - otherPointMap.x, 2) + Math.pow(y - otherPointMap.y, 2)));
    }

    /**
     * Comunica la cel·la on es troba el punt
     * @return
     */
    public String comunicaCela() {
        for (int columna = 0; columna < numColumnes; columna++) {
            for (int fila = 0; fila < numFiles; fila++) {
                if (x == (columna + 0.5) && y == (fila + 0.5)) {
                    return "F" + (fila + 1) + "C" + (columna + 1);
                }
            }
        }
        return "null";
    }

}


