package dreamteam.iam.cat.autoterminalemployee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 *
 * Classe que crea el ZXingScannerView a la vista
 */
public class ScanCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScanVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScanVista = new ZXingScannerView(this);
        setContentView(ScanVista);
    }

    /**
     *
     * @param result gestiona el que escaneja segons de la activitat que ve i pasa valor a la
     *               variable de la activitat
     */
    @Override
    public void handleResult(Result result) {

        if (getIntent().getStringExtra("PreviaActivitat").equals("LocV")) {
            LocalitzarVehicle.resultatCodi.setText(result.getText());
        } else if (getIntent().getStringExtra("PreviaActivitat").equals("IngV")) {
            IngressarVehicle.resultatCodiVehicle.setText(result.getText());
        } else if (getIntent().getStringExtra("PreviaActivitat").equals("IngD")){
            IngressarVehicle.resultatCodiDispositiu.setText(result.getText());
        } else
            BaixaVehicle.resultatCodiVehicle.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScanVista.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScanVista.setResultHandler(this);
        ScanVista.startCamera();
    }


}
