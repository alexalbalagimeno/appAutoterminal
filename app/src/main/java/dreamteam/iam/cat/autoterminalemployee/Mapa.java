package dreamteam.iam.cat.autoterminalemployee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 *
 * Activitat que crida a MapaVista
 */
public class Mapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MapaVista(this));
    }
}
