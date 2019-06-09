package dreamteam.iam.cat.autoterminalemployee;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *
 * Classe per autenticar
 * Dades de edittext de usuari i contrasenya
 * Base de dades FirebaseDatabase
 * ValueEventListener
 * ArrayList de usuaris
 * Booleana per a saber si autentica o no
 */
public class Principal extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    private EditText user;
    private EditText pass;
    private FirebaseDatabase database;
    private ValueEventListener listenerValue;
    private DatabaseReference reference ;
    private ArrayList<Usuari> usuaris= new ArrayList<Usuari>();
    boolean autentica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        user = (EditText)findViewById(R.id.eTUsuari);
        pass = (EditText)findViewById(R.id.eTPass);

        usuaris= new ArrayList<Usuari>();
        reference= FirebaseDatabase.getInstance().getReference();

        listenerValue= new ValueEventListener() {
            /**
             *
             * @param dataSnapshot per cada fill es crea un usuari i s'afegeix a la llista usuaris
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot usuari : dataSnapshot.getChildren()) {
                    usuaris.add(new Usuari(""+usuari.getKey(),""+usuari.getValue()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
    }

    /**
     * S'afegeix listener a la base de dades FireBase Database RealTime
     */
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, getResources().getString(R.string.PrepareLogin), Toast.LENGTH_LONG).show();
        reference.addValueEventListener(listenerValue);
        autentica = false;
    }

    /**
     * Es treu listener a la base de dades FireBase Database RealTime
     */
    protected void onStop(){
        super.onStop();
        reference.removeEventListener(listenerValue);
        autentica = false;
    }

    /**
     * Métode onClick per a autenticar usuari amb String nom i String contrasenya
     * Es recorre la llista usuaris creada al ValueEventListener
     * @param view
     */
    public void onClickBotoEntra(View view) {
        for(Usuari usu : usuaris){
            if(user.getText().toString().equals(usu.getNom()))
                if(pass.getText().toString().equals(usu.getContra())) {
                    autentica = true;
                    Intent intent = new Intent(this, MenuPrincipal.class);
                    startActivity(intent);
                }
        }
        if(!autentica){
            Toast.makeText(this, getResources().getString(R.string.ErrorAutentication), Toast.LENGTH_LONG).show();
        }
    }
}
