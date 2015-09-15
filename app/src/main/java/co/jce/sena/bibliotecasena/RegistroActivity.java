package co.jce.sena.bibliotecasena;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.jce.sena.basededatos.UsuariosDataBaseManager;

/**
 * Created by jce on 14/09/15.
 */
public class RegistroActivity extends AppCompatActivity implements View .OnClickListener {

    //-> Atributos (Componentes)
    private EditText etNumeroCedula,
                     etNombres,
                     etApellidos,
                     etCorreo,
                     etContrasena,
                     etConfirmarContrasena;
    private Button btnRegistrar;

    //-> Atributos (Comunes)
    private int vNumeroCedula;
    private String vNombres,
                   vApellidos,
                   vCorreo,
                   vContrasena,
                   vConfirmarContrasena;

    //Atributos (Especiales)
    private UsuariosDataBaseManager usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();

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
        etNombres = ( EditText ) findViewById( R .id .etNombres );
        etApellidos = ( EditText ) findViewById( R .id .etApellidos );
        etCorreo = ( EditText ) findViewById( R .id .etCorreo );
        etContrasena = ( EditText ) findViewById( R .id .etContrasena );
        etConfirmarContrasena = ( EditText ) findViewById( R .id .etConfirmarContrasena );
        btnRegistrar = ( Button ) findViewById( R .id .btnRegistrar );

        //-> Agregamos manejadores de eventos
        btnRegistrar .setOnClickListener( this );

    }


    @Override
    public void onClick(View v) {

        if( v .getId() == R .id .btnRegistrar ) {

            long numeroRegistros;

            //-> Validamos que los dos campos de contraseña y su confirmación sean iguales.
            if( etContrasena .getText() .toString() .equals( etConfirmarContrasena .getText() .toString() ) ) {

                capturaValores();
                usuarios = new UsuariosDataBaseManager( this );
                numeroRegistros = usuarios .insertar( vNumeroCedula, vNombres, vApellidos, vCorreo, vContrasena );

                if( numeroRegistros != -1 ) {
                    Toast .makeText( RegistroActivity .this, "Registro realizado con éxito", Toast .LENGTH_SHORT ) .show();
                }
                else {
                    Toast .makeText( RegistroActivity .this , "Ocurrio un error en el registro", Toast .LENGTH_SHORT ) .show();
                }

            }
            else {
                Toast .makeText( this, "La contraseña no ha sido confirmada", Toast .LENGTH_SHORT ) .show();
            }
        }

    }

    private void capturaValores() {

        vNumeroCedula = Integer .parseInt( etNumeroCedula .getText() .toString() );
        vNombres = etNombres .getText().toString();
        vApellidos = etApellidos .getText() .toString();
        vCorreo = etCorreo .getText() .toString();
        vContrasena = etContrasena .getText() .toString();
        vConfirmarContrasena = etConfirmarContrasena .getText() .toString();

    }
}
