package co.jce.sena.bibliotecasena;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.jce.sena.basededatos.UsuariosDataBaseManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //-> Atributos (Comunes)
    private int vNumeroCedula;
    private String vContrasena;

    //-> Atributos (Componentes)
    private EditText etNumeroCedula,
                     etContrasena;
    private Button btnIngresar;
    private TextView tvRegistrarse;

    //-> Atributos (Especiales)
    private Intent in;
    private UsuariosDataBaseManager usuarios;
    private SharedPreferences spSesion;
    private SharedPreferences .Editor spEditorSession;
    private Cursor cBusqueda;

    //-> Atributos (Constantes)
    private final static String SP_ID = "id_usuario",
                                SP_NOMBRE = "nombre_usuario",
                                SP_ROL = "rol_usuario",
                                GUARDADO = "guardado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();     //: Inicializamos todos los componentes del "Activity" para hacerlos accesibles

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
        //-> Accedemos a los componentes del "Activity"
        etNumeroCedula = ( EditText ) findViewById( R .id .etNumeroCedula );
        etContrasena = ( EditText ) findViewById( R .id .etContrasena );
        btnIngresar = ( Button ) findViewById( R .id .btnIngresar );
        tvRegistrarse = ( TextView ) findViewById( R .id .tvRegistrarse );

        //-> Asignación de manejadores de eventos
        btnIngresar .setOnClickListener( this );
        tvRegistrarse .setOnClickListener( this );
    }

    @Override
    public void onClick( View v ) {

        if( v .getId() == R .id .btnIngresar ) {

            verificarUsuario();

        }

        if( v .getId() == R. id. tvRegistrarse ) {
            in = new Intent( this, RegistroActivity.class );
            startActivity(in);
        }

    }

    private void verificarUsuario() {

        capturaValores();
        usuarios = new UsuariosDataBaseManager( this );

        //-> Colocamos la busqueda en un cursor nuevo.
        cBusqueda = usuarios .buscarUsuario( vNumeroCedula, vContrasena );

        //-> Valida si el usuario existe
        if( cBusqueda .moveToFirst() ) {

            //# Antes de lanzar el "PanelActivity" debe cargar los valores importantes del registro encontrado
            //# en una especie de variable de sesión (SharedPreferences) que permita su disponibilidad de estos
            //# datos a lo largo de la aplicación.

            //in = new Intent( this, PanelActivity.class );
            //startActivity( in );

            Toast .makeText( this, "Lo encontre", Toast.LENGTH_SHORT ) .show();
            //Toast .makeText( this, String .valueOf( cBusqueda .getInt( 0 ) ), Toast.LENGTH_SHORT ) .show();
        }
        else {
            Toast .makeText( this, "No encuentro nada", Toast.LENGTH_SHORT ) .show();
        }

    }

    private void capturaValores() {

        //-> Extraer los valores de los componentes del "Activity"
        vNumeroCedula = Integer .parseInt( etNumeroCedula .getText() .toString() );
        vContrasena = etContrasena .getText() .toString();

    }

    private void guardarPreferencias( String numero_cedula, String nombre, String rol ) {
        spEditorSession .putString( SP_ID, numero_cedula );
        spEditorSession .putString( SP_NOMBRE, nombre );
        spEditorSession .putString( SP_ROL, rol );
        //spEditorSession .putBoolean( GUARDADO, estaGuardo );
        spEditorSession .commit();
    }

}
