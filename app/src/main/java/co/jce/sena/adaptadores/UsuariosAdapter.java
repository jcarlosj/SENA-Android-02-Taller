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
import co.jce.sena.holders.UsuarioHolder;

/**
 * Created by jce on 27/10/15.
 */
public class UsuariosAdapter extends ArrayAdapter<Usuario> {

    //-> Atributos (Colecciones)
    private ArrayList alUsuarios;

    //-> Atributos (Especiales)
    private Context cContexto;
    private UsuarioHolder usuarioHolder;

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

        //-> Valida si el "View" del "item" del "ListView" NO existe.
        //   En caso de NO existir infla la "View" para el "layout" del "item" nuevo para el "ListView"
        if( convertView == null ) {

            //-> Inflamos la vista del "layout" creado para el despliegue de cada "item" del "ListView"
            convertView = LayoutInflater .from( this .cContexto ) .inflate( R.layout .list_item_usuario, null );

            //-> Instanciamos el "holder" para mantener los datos.
            this .usuarioHolder = new UsuarioHolder();

            //-> Accedemos a los componentes de "list_item_animals.xml"
            this .usuarioHolder .setIvUsuario( ( ImageView ) convertView .findViewById( R .id .ivUsuario ) );
            this .usuarioHolder .setTvNombresApellidos( ( TextView ) convertView .findViewById( R .id .tvNombresApellidos ) );
            this .usuarioHolder .setTvNumeroDocumento( ( TextView ) convertView .findViewById( R .id .tvNumeroDocumento) );
            this .usuarioHolder .setTvCorreoElectronico( ( TextView ) convertView .findViewById( R.id.tvCorreoElectronico ) );
            this .usuarioHolder .setTvEstado( ( TextView ) convertView .findViewById( R .id .tvEstado ) );
            this .usuarioHolder .setTvPrestamos( ( TextView ) convertView .findViewById( R .id .tvPrestamos ) );

            convertView .setTag( this .usuarioHolder );    //: Guardamos el "Holder" dentro del "View" para luego poder recuperarlo

        }

        //-> Reasignamos la referencia de los componentes contenidos en el "View" al "Holder"
        usuarioHolder = ( UsuarioHolder ) convertView .getTag();

        //-> Pasamos los valores a los componentes de "list_item_animals.xml" en el Holder
        this .usuarioHolder .getIvUsuario() .setImageResource( usuarioPosicion .getIdRecursoImagen() );
        this .usuarioHolder .getTvNombresApellidos() .setText( usuarioPosicion .getNombres() + " " + usuarioPosicion .getApellidos() );
        this .usuarioHolder .getTvNumeroDocumento() .setText( String.valueOf(usuarioPosicion .getId() ) );
        this .usuarioHolder .getTvCorreoElectronico() .setText( usuarioPosicion .getCorreo() );
        this .usuarioHolder .getTvEstado() .setText( usuarioPosicion .getEstado() );
        this .usuarioHolder .getTvPrestamos() .setText( "Pendiente" );

        return convertView;
    }

}
