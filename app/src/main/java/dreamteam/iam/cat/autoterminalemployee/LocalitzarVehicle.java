package dreamteam.iam.cat.autoterminalemployee;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * Aquesta activitat envia el codi de bastidor a la base de dades mySQL
 * La seva funció es obtenir les dades del vehicle que ja està emparellat
 * i que existeix a la base de dades
 */
public class LocalitzarVehicle extends AppCompatActivity {

    URLConnection connection;
    String data;
    OutputStreamWriter out;
    BufferedReader in;
    public static EditText resultatCodi;
    public static String xml;
    public static double coordX;
    public static double coordY;
    public static int fila;
    public static int columna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localitzar_vehicle);

        resultatCodi = (EditText) findViewById(R.id.eTBastidor);

        if (resultatCodi.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Métode onClick que accedeix a la activitat Mapa
     * si les dades son nules avisa de que introdueixis el numero de bastidor
     * @param view
     */
    public void onClickMapa(View view) {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }

    /**
     * Métode onClick que agafa les dades del QR (codi nodeMCU) cridant a l'activitat ScanCode
     * Se li passa una String LocV per a diferenciar que ve des d'aqui
     * @param view
     */
    public void onClickFoto(View view) {
        Intent intent = new Intent(this, ScanCode.class);
        intent.putExtra("PreviaActivitat", "LocV");
        startActivity(intent);
    }

    /**
     * Métode onClick que agafa les dades del QR del editText
     * Si esta buit avisa de que posis el numero de bastidor sino crea una AsynTask
     * @param view
     */
    public void onClickBuscar(View view) {
        data = resultatCodi.getText().toString();
        if (resultatCodi.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.PutNumber), Toast.LENGTH_LONG).show();
        } else {
            new AsynTask().execute();
        }
    }

    /**
     * Métode que gestiona la conexió al servLet
     * Atributs URLConnection connection,  Context context, String data, OutputStreamWriter out, BufferedReader in
     */
    protected class AsynTask extends AsyncTask<String, Void, String> {
        String resultat = "";
        /**
         *
         * @param strings
         * @return String en format xml amb el que respon el servidor servlet
         */
        @Override
        protected String doInBackground(String... strings) {
            try {
                connection = new URL(Constants.URL).openConnection();
                connection.setDoOutput(true);
                out = new OutputStreamWriter(connection.getOutputStream());
                out.write(Constants.PETICIO_ON_ES_EL_VEHICLE + data);
                out.close();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                resultat = "";
                String strAux;
                while((strAux = in.readLine()) != null){
                    resultat += strAux;
                }
                in.close();
                return resultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                return "";
            }
            return null;
        }

        /**
         *
         * @param resultat es String en format XML i es parseja amb creació vehicle
         *                 es posa les dades obtingudes al seu editText corresponent
         */
        @Override
        protected void onPostExecute(String resultat) {
            super.onPostExecute(resultat);
            if(resultat.equals("")){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.IncorrectNumber), Toast.LENGTH_LONG).show();
            }
            else {
                EditText etBastidor = (EditText) findViewById(R.id.eTBastidor2);
                EditText etColor = (EditText) findViewById(R.id.eTColor);
                EditText etModel = (EditText) findViewById(R.id.eTModel);
                EditText etMarca = (EditText) findViewById(R.id.eTMarca);
                EditText etCaract = (EditText) findViewById(R.id.eTCaract);
                EditText etPlaca = (EditText) findViewById(R.id.eTPlaca);
                EditText etPlanta = (EditText) findViewById(R.id.eTPlanta);
                EditText etEdifici = (EditText) findViewById(R.id.eTEdifici);
                EditText etDataEntrada = (EditText) findViewById(R.id.eTDataEntrada);
                EditText etDataSortida = (EditText) findViewById(R.id.eTDataSortida);

                Vehicle vehicleTrobat = new Vehicle();

                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(new StringReader(resultat));
                    while (xpp.next() != XmlPullParser.END_DOCUMENT) {
                        int event = xpp.getEventType();
                        switch (event) {
                            case XmlPullParser.START_TAG:
                                String nomEti = xpp.getName();
                                if (nomEti.equals("bastidor")) {
                                    xpp.next();
                                    vehicleTrobat.setBastidor(xpp.getText());
                                } else if (nomEti.equals("color")) {
                                    xpp.next();
                                    vehicleTrobat.setColor(xpp.getText());
                                } else if (nomEti.equals("model")) {
                                    xpp.next();
                                    vehicleTrobat.setModel(xpp.getText());
                                } else if (nomEti.equals("marca")) {
                                    xpp.next();
                                    vehicleTrobat.setMarca(xpp.getText());
                                } else if (nomEti.equals("caract")) {
                                    xpp.next();
                                    vehicleTrobat.setCaracteristiques(xpp.getText());
                                } else if (nomEti.equals("fila")) {
                                    xpp.next();
                                    vehicleTrobat.setFila(xpp.getText());
                                    fila = Integer.parseInt(xpp.getText());
                                } else if (nomEti.equals("columna")) {
                                    xpp.next();
                                    vehicleTrobat.setColumna(xpp.getText());
                                    columna = Integer.parseInt(xpp.getText());
                                } else if (nomEti.equals("posX")) {
                                    xpp.next();
                                    coordX = Double.parseDouble(xpp.getText());
                                } else if (nomEti.equals("posY")) {
                                    xpp.next();
                                    coordY = Double.parseDouble(xpp.getText());
                                } else if (nomEti.equals("planta")) {
                                    xpp.next();
                                    vehicleTrobat.setPlanta(xpp.getText());
                                } else if (nomEti.equals("edifici")) {
                                    xpp.next();
                                    vehicleTrobat.setEdifici(xpp.getText());
                                } else if (nomEti.equals("dataentrada")) {
                                    xpp.next();
                                    vehicleTrobat.setDataIngres(xpp.getText());
                                } else if (nomEti.equals("datasortida")) {
                                    xpp.next();
                                    vehicleTrobat.setDataSortida(xpp.getText());
                                }
                                break;
                        }

                    }

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                etBastidor.setText(vehicleTrobat.getBastidor());
                etColor.setText(vehicleTrobat.getColor());
                etModel.setText(vehicleTrobat.getModel());
                etMarca.setText(vehicleTrobat.getMarca());
                etCaract.setText(vehicleTrobat.getCaracteristiques());
                etPlaca.setText(getResources().getString(R.string.Row) + ": " + vehicleTrobat.getFila() + " " + getResources().getString(R.string.Column) + ": " + vehicleTrobat.getColumna());
                etPlanta.setText(vehicleTrobat.getPlanta());
                etEdifici.setText(vehicleTrobat.getEdifici());
                etDataEntrada.setText(vehicleTrobat.getDataIngres());
                etDataSortida.setText(vehicleTrobat.getDataSortida());
            }
        }
    }
}
