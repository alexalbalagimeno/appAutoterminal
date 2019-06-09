package dreamteam.iam.cat.autoterminalemployee;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 * Activitat de 'Splash Screen' de 3 segons de logo
 */
public class Logo extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        /**
         * Carrega logo 3 segons
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent logoIntent = new Intent(Logo.this,Principal.class);
                startActivity(logoIntent);
                finish();
            }
        },SPLASH_SCREEN_TIME_OUT);
    }
}
