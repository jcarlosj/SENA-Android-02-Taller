package co.jce.sena.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.jce.sena.basededatos.Usuario;
import co.jce.sena.bibliotecasena.R;

/**
 * Created by jce on 27/10/15.
 */
public class UsuariosAdapter extends ArrayAdapter<Usuario> {

    //-> Atributos (Colecciones)
    private ArrayList alUsuarios;

    //-> Atributos (Especiales)
    private Context cContexto;

    //-> Atributos (Personalizados)
    private Usuario usuarioPosicion;

    //-> Atributos (Componentes)
    private ImageView ivUsuario;
    private TextView tvNombresApellidos,
                     tvNumeroDocumento,
                     tvCorreoElectronico,
                     tvEstado,
                     tvPrestamos;

    //-> Constructor
    public UsuariosAdapter( Context contexto, ArrayList<Usuario> usuarios ) {
        super( contexto, 0, usuarios );        //: super( contexto, recurso, coleccion de objetos );
        this .cContexto = contexto;
        this .alUsuarios = usuarios;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        //-> Accedemos al objeto "Animal" contenido en el indice indicado del ArrayList
        usuarioPosicion = ( Usuario ) this .alUsuarios .get( position );

        //-> Inflamos la vista del "layout" creado para el despliegue de cada "item" del "ListView"
        convertView = LayoutInflater .from( this .cContexto ) .inflate( R.layout .list_item_usuario, null );

        //-> Accedemos a los componentes de "list_item_animals.xml"
        ivUsuario = ( ImageView ) convertView .findViewById( R .id .ivUsuario );
        tvNombresApellidos = ( TextView ) convertView .findViewById( R .id .tvNombresApellidos );
        tvNumeroDocumento = ( TextView ) convertView .findViewById( R .id .tvNumeroDocumento );
        tvCorreoElectronico = ( TextView ) convertView .findViewById( R .id .tvCorreoElectronico );
        tvEstado = ( TextView ) convertView .findViewById( R .id .tvEstado );
        tvPrestamos = ( TextView ) convertView .findViewById( R .id .tvPrestamos );

        //-> Pasamos los valores a los componentes de "list_item_animals.xml"
        ivUsuario .setImageResource( usuarioPosicion.getIdRecursoImagen() );
        tvNombresApellidos .setText(usuarioPosicion.getNombres() + " " + usuarioPosicion.getApellidos());
        tvNumeroDocumento .setText(String.valueOf(usuarioPosicion.getId()));
        tvCorreoElectronico .setText(usuarioPosicion.getCorreo());
        tvEstado .setText( usuarioPosicion .getEstado() );
        tvPrestamos .setText( "Pendiente" );

        return convertView;
    }

}
