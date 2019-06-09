package dreamteam.iam.cat.autoterminalemployee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 * Classe menu principal on hi ha botons per a redirigir a les diferents activitats
 */
public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    /**
     * Métode onClick per anar a l'activitat IngressarVehicle
     * @param view
     */
    public void onClickBotoIngressarVehicle(View view) {
        Intent intent = new Intent(this,IngressarVehicle.class);
        startActivity(intent);
    }

    /**
     * Métode onClick per anar a l'activitat BaixaVehicle
     * @param view
     */
    public void onClickBotoBaixaVehicle(View view) {
        Intent intent = new Intent(this,BaixaVehicle.class);
        startActivity(intent);
    }

    /**
     * Métode onClick per anar a l'activitat LocalitzarVehicle
     * @param view
     */
    public void onClickBotoLocalitzarVehicle(View view) {
        Intent intent = new Intent(this,LocalitzarVehicle.class);
        startActivity(intent);
    }

    /**
     * Métode onClick per anar a un enllaç per a reomplir un formulari d'incidencies
     * @param view
     */
    public void onClickBotoReportarIncidencia(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/TR1pUERHfUx6gUGGA"));
        startActivity(browserIntent);
    }
}
