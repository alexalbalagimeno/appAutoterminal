package dreamteam.iam.cat.autoterminalemployee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * Aquesta activitat envia el codi de bastidor a la base de dades mySQL
 * La seva funció es borrar el vehicle que hi hagi a la base de dades
 */
public class BaixaVehicle extends AppCompatActivity {

    public static EditText resultatCodiVehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baixa_vehicle);

        resultatCodiVehicle = (EditText)findViewById(R.id.eTIngressarVehicle);

        if(resultatCodiVehicle.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Métode onClick que agafa les dades del QR cridant a l'activitat ScanCode
     * Se li passa una String BaixaV per a diferenciar que ve des d'aqui
     * @param view
     */
    public void onClickBotoFotoBaixaVehicle(View view) {
        Intent intent = new Intent(this,ScanCode.class);
        intent.putExtra("PreviaActivitat", "BaixaV");
        startActivity(intent);
    }

    /**
     * Métode onClick que envia les dades QR a la base dades mySQL si les dades son nules
     * avisa de que posis el numero de bastidor
     * @param view
     */
    public void onClickBotoConfirmarBaixa(View view) {

        if(resultatCodiVehicle.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        }
        else {
            ConexioServerURL conexio = new ConexioServerURL(this, resultatCodiVehicle.getText().toString());
        }
    }

}
