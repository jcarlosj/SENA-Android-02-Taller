package co.jce.sena.bibliotecasena;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //-> Atributos (Componentes)
    private EditText etNumeroCedula,
                     etContrasena;
    private Button btnIngresar;
    private TextView tvRegistrarse;

    //-> Atributos (Especiales)
    private Intent in;

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
            in = new Intent( this, PanelActivity.class );
            startActivity( in );
        }

        if( v .getId() == R. id. tvRegistrarse ) {
            in = new Intent( this, RegistroActivity.class );
            startActivity( in );
        }

    }

}
