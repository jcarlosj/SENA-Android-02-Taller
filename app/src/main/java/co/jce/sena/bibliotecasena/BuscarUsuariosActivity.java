package co.jce.sena.bibliotecasena;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import co.jce.sena.basededatos.UsuariosDataBaseManager;

/**
 * Created by jce on 27/10/15.
 */
public class BuscarUsuariosActivity extends AppCompatActivity {

    //-> Atributos (Componentes)
    private ListView lvUsuarios;
    private EditText etBuscar;
    private ImageButton ibBuscar;

    //-> Atributos (Especiales)
    private UsuariosDataBaseManager dbmUsuarios;
    private Cursor cUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_buscar_usuarios );
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
        lvUsuarios = ( ListView ) findViewById( R .id .lvUsuarios );
        etBuscar = ( EditText ) findViewById( R .id .etBuscar );
        ibBuscar = ( ImageButton ) findViewById( R .id .ibBuscar );

        //-> Accedemos a la clase que construye la BD.
        //   (Instanciamos)
        dbmUsuarios = new UsuariosDataBaseManager( this );

        //-> Cargamos la lista de contactos en el cursor
        cUsuarios = dbmUsuarios .listarUsuarios();
    }

}
