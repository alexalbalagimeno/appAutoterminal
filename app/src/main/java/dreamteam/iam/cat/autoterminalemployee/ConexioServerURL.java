package dreamteam.iam.cat.autoterminalemployee;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * Classe que en el seu constructor rep el context de l'activitat que la crida i String
 * amb l'informacio corresponent
 * El constructor crida al métode Asyntask per a fer conexió al servLet
 */
public class ConexioServerURL {

    URLConnection connection;
    Context context;
    String data;
    OutputStreamWriter out;
    BufferedReader in;

    public ConexioServerURL(Context context, String data) {
        this.context = context;
        this.data = data;
        new AsynTask().execute();
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
         * @return String amb el que respon el servidor servlet
         */
        @Override
        protected String doInBackground(String... strings) {

            try {
                connection = new URL(Constants.URL).openConnection();
                connection.setDoOutput(true);
                switch (context.getClass().getSimpleName().toString()) {
                    case Constants.CONTEXT_INGRESSAR_VEHICLE:
                        out = new OutputStreamWriter(connection.getOutputStream());
                        out.write(Constants.PETICIO_EMPARELLAR_VEHICLE + data);
                        out.close();
                        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        resultat = in.readLine();
                        in.close();
                        return resultat;
                    case Constants.CONTEXT_BAIXA_VEHICLE:
                        out = new OutputStreamWriter(connection.getOutputStream());
                        out.write(Constants.PETICIO_BAIXA_VEHICLE + data);
                        out.close();
                        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        resultat = in.readLine();
                        in.close();
                        return resultat;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         *
         * @param resultat si es true es mostra vehicle afegit en el cas de que el context sigui de
         *                 l activitat IngressarVehicle i si el context es de l activitat Baixa
         *                 Vehicle es mostra vehicle borrat. En el cas que sigui false en els dos
         *                 casos canvi no efectuat
         *
         */
        @Override
        protected void onPostExecute(String resultat) {
            super.onPostExecute(resultat);
            switch (context.getClass().getSimpleName().toString()) {
                case Constants.CONTEXT_INGRESSAR_VEHICLE:
                    if (resultat.equals("true"))
                        Toast.makeText(context, context.getResources().getString(R.string.AddedCar), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, context.getResources().getString(R.string.UpdateFailed), Toast.LENGTH_LONG).show();
                    break;
                case Constants.CONTEXT_BAIXA_VEHICLE:
                    if (resultat.equals("true"))
                        Toast.makeText(context, context.getResources().getString(R.string.DeletedCar), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, context.getResources().getString(R.string.UpdateFailed), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}


