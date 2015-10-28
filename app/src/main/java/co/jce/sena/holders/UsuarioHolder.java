package co.jce.sena.holders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jce on 28/10/15.
 */
//-> Creamos la clase "Holder" para mantener los datos.
//   Este será el contenedor de las referencias a los contenedores
public class UsuarioHolder {

    //-> Atributos (Comunes)
    private ImageView ivUsuario;
    private TextView tvNumeroDocumento,
                     tvNombresApellidos,
                     tvCorreoElectronico,
                     tvEstado,
                     tvPrestamos;

    //-> Getters & Setters
    public ImageView getIvUsuario() {
        return ivUsuario;
    }

    public void setIvUsuario( ImageView ivUsuario ) {
        this .ivUsuario = ivUsuario;
    }

    public TextView getTvNumeroDocumento() {
        return tvNumeroDocumento;
    }

    public void setTvNumeroDocumento( TextView tvNumeroDocumento ) {
        this .tvNumeroDocumento = tvNumeroDocumento;
    }

    public TextView getTvNombresApellidos() {
        return tvNombresApellidos;
    }

    public void setTvNombresApellidos( TextView tvNombresApellidos ) {
        this.tvNombresApellidos = tvNombresApellidos;
    }

    public TextView getTvCorreoElectronico() {
        return tvCorreoElectronico;
    }

    public void setTvCorreoElectronico( TextView tvCorreoElectronico ) {
        this .tvCorreoElectronico = tvCorreoElectronico;
    }

    public TextView getTvEstado() {
        return tvEstado;
    }

    public void setTvEstado( TextView tvEstado ) {
        this .tvEstado = tvEstado;
    }

    public TextView getTvPrestamos() {
        return tvPrestamos;
    }

    public void setTvPrestamos( TextView tvPrestamos ) {
        this .tvPrestamos = tvPrestamos;
    }

}
