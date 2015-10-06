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
    private boolean estaGuardo;

    //-> Atributos (Componentes)
    private EditText etNumeroCedula,
                     etContrasena;
    private Button btnIngresar;
    private TextView tvRegistrarse;

    //-> Atributos (Especiales)
    private Intent in;
    private UsuariosDataBaseManager usuarios;
    private Cursor cBusqueda;
    private SharedPreferences spSesion;
    private SharedPreferences.Editor spEditorSession;

    //-> Atributos (Constantes)
    private final static String SP_ID = "id_usuario",
                                SP_NOMBRE = "nombre_usuario",
                                SP_ESTADO_USUARIO = "estado_usuario",
                                SP_ROL = "rol_usuario",
                                SP_ESTADO_ROL = "estado_rol",
                                SP_GUARDADO = "guardado";

    public void MainActivity() {
        this .estaGuardo = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();     //: Inicializamos todos los componentes del "Activity" para hacerlos accesibles
        validarSiGuardoPreferencias();
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

            //-> Verifica si el estado del Usuario en el Sistema
            if( cBusqueda .getString( 3 ) .equals( "activo" ) ) {

                //-> Verifica si el estado del Rol actual del  Usuario en el Sistema
                if( cBusqueda .getString( 6 ) .equals( "activo" ) ) {

                    //-> Asignamos el nombre al archivo XML que alojará las preferencias y el nivel de seguridad del mismo
                    spSesion = getSharedPreferences("Sesion", MODE_PRIVATE);
                    spEditorSession = spSesion .edit();     //: Se cambia la preferencia para que sea editable y se le asigna el editor.

                    //-> Guarda los datos para usarlos de forma global.
                    guardarPreferencias(
                            cBusqueda.getInt(0),         //: CU_ID
                            cBusqueda.getString(1),      //: CU_NOMBRE
                            cBusqueda.getString(3),      //: CU_ESTADO
                            cBusqueda.getString(4),      //: CR_NOMBRE
                            cBusqueda.getString(6),      //: CR_ESTADO
                            true                         //: Cambio de la variable estaGuardo de false a true
                    );

                    //-> Limpiamos los campos.
                    limpiarCampos();

                    //-> Accedemos al "PanelActivity"
                    in = new Intent( this, PanelActivity.class );
                    startActivity( in );
                }

            }
            else {
                Toast .makeText( this, "Su cuenta se encuentra desactivada", Toast .LENGTH_SHORT ) .show();
            }

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

    private void guardarPreferencias( int numeroCedula, String nombre, String estadoUsuario, String rol, String estadoRol, boolean guardo ) {
        spEditorSession .putInt(SP_ID, numeroCedula);
        spEditorSession .putString(SP_NOMBRE, nombre);
        spEditorSession .putString( SP_ESTADO_USUARIO, estadoUsuario );
        spEditorSession .putString( SP_ROL, rol );
        spEditorSession .putString( SP_ESTADO_ROL, estadoRol );
        //-> Cambiamos el estado
        this .estaGuardo = guardo;
        spEditorSession .putBoolean( SP_GUARDADO, this .estaGuardo );
        spEditorSession .commit();
    }

    private void validarSiGuardoPreferencias() {

        //-> Asignamos el valor del estado de GUARDADO de preferencias compartidas.
        if( spSesion != null ) {
            if( !spSesion .getBoolean( SP_GUARDADO, false ) ) {
                // limpiarCampos();
                Toast .makeText( MainActivity .this, "Existe spSesion, pero esta vacio", Toast .LENGTH_SHORT ) .show();
            }
            else {
                Toast .makeText( MainActivity .this, "Existe spSesion y guarda datos", Toast .LENGTH_SHORT ) .show();
            }
        }
        else {
            Toast .makeText( MainActivity .this, "No existe spSesion", Toast .LENGTH_SHORT) .show();
        }

    }

    private void limpiarCampos() {
        etNumeroCedula .setText( "" );
        etContrasena .setText( "" );
    }

}
