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
 * questa activitat envia el codi de bastidor a la base de dades mySQL
 * La seva funció es emparellar el vehicle que hi hagi a la base de dades
 * a un dispositiu nodeMCU amb el seu codi corresponent
 */
public class IngressarVehicle extends AppCompatActivity {

    public static EditText resultatCodiVehicle;
    public static EditText resultatCodiDispositiu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingressar_vehicle);

        resultatCodiVehicle = (EditText)findViewById(R.id.eTIngressarVehicle);
        resultatCodiDispositiu = (EditText)findViewById(R.id.eTIngressarDispositiu);

        if(resultatCodiVehicle.getText().toString().equals("") || resultatCodiDispositiu.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Métode onClick que agafa les dades del QR (codi bastidor) cridant a l'activitat ScanCode
     * Se li passa una String IngV per a diferenciar que ve des d'aqui
     * @param view
     */
    public void onClickBotoFotoIngressarVehicle(View view) {
        Intent intent = new Intent(this,ScanCode.class);
        intent.putExtra("PreviaActivitat", "IngV");
        startActivity(intent);
    }

    /**
     * Métode onClick que agafa les dades del QR (codi nodeMCU) cridant a l'activitat ScanCode
     * Se li passa una String IngD per a diferenciar que ve des d'aqui
     * @param view
     */
    public void onClickBotoFotoIngressarDispositiu(View view) {
        Intent intent = new Intent(this,ScanCode.class);
        intent.putExtra("PreviaActivitat", "IngD");
        startActivity(intent);
    }

    /**
     * Métode onClick que envia les dades QR (codi bastidor+"#"+codi nodeMCU) a la base dades mySQL
     * si les dades son nules avisa de que introdueixis el numero de bastidor
     * @param view
     */
    public void onClickBotoConfirmarIngressar(View view) {
        if(resultatCodiVehicle.getText().toString().equals("") || resultatCodiDispositiu.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        }
        else{
            ConexioServerURL conexio = new ConexioServerURL(this, resultatCodiVehicle.getText().toString()+"#"+resultatCodiDispositiu.getText());
        }
    }


}
