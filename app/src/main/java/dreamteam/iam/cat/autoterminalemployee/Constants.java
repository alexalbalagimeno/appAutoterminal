package dreamteam.iam.cat.autoterminalemployee;

/**
 *
 * Constants de peticions, context i URL a Servlet
 */
public class Constants {

    // Constants peticions vehicles
    public static final String PETICIO_ON_ES_EL_VEHICLE = "401=";
    public static final String PETICIO_EMPARELLAR_VEHICLE = "402=";
    public static final String PETICIO_BAIXA_VEHICLE = "403=";

    //Constants context
    public static final String CONTEXT_INGRESSAR_VEHICLE = "IngressarVehicle";
    public static final String CONTEXT_BAIXA_VEHICLE = "BaixaVehicle";

    //Constants URL
    public static final String URL = "http://192.168.1.105:8080/AutoterminalProject/DeltaTracking";
}
