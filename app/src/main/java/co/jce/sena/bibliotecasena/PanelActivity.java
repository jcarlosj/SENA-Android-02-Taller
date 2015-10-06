package co.jce.sena.bibliotecasena;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by jce on 4/09/15.
 */
public class PanelActivity extends AppCompatActivity {

    //-> Atributos (Comunes)
    private boolean estaGuardo;

    //-> Atributos (Especiales)
    private SharedPreferences spSesion;

    //-> Atributos (Constantes)
    private final static String SP_ID = "id_usuario",
            SP_NOMBRE = "nombre_usuario",
            SP_ESTADO_USUARIO = "estado_usuario",
            SP_ROL = "rol_usuario",
            SP_ESTADO_ROL = "estado_rol",
            SP_GUARDADO = "guardado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        init();
        validarSiGuardoPreferencias();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if( this .estaGuardo ) {
            if( spSesion .getString( SP_ROL, "" ) .equals( "Admin" ) ) {
                getMenuInflater().inflate(R.menu.menu_main_admin, menu);
            }
            if( spSesion .getString( SP_ROL, "" ) .equals( "Usuario" ) ) {
                getMenuInflater().inflate(R.menu.menu_main_usuario, menu);
            }
        }
        else {
            //getMenuInflater().inflate(R.menu.menu_main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        //-> Asignamos el nombre al archivo XML que aloja las preferencias y el nivel de seguridad del mismo
        spSesion = getSharedPreferences( "Sesion", MODE_PRIVATE );
    }

    private void validarSiGuardoPreferencias() {

        //-> Asignamos el valor del estado de GUARDADO de preferencias compartidas.
        if( spSesion != null ) {
            if( !spSesion .getBoolean( SP_GUARDADO, false ) ) {
                // limpiarCampos();
                Toast.makeText( this, "Existe spSesion, pero esta vacio", Toast.LENGTH_SHORT) .show();
            }
            else {
                this .estaGuardo = true;
                Toast .makeText( this, "Existe spSesion y posee datos", Toast.LENGTH_SHORT ) .show();
            }
        }
        else {
            Toast .makeText( this, "No existe spSesion", Toast.LENGTH_SHORT) .show();
        }

    }

}
