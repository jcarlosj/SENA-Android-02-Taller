package co.jce.sena.basededatos;

/**
 * Created by jce on 27/10/15.
 */
public class Usuario {

    //-> Atributos (Comunes)
    private int idRecursoImagen,
                id,
                rolID;
    private String nombres,
                   apellidos,
                   correo,
                   estado,
                   contrasenia;

    //-> Constructor
    public Usuario( int idRecursoImagen, int id, String nombres, String apellidos, String correo, String estado, String contrasenia, int rolID ) {
        this .idRecursoImagen = idRecursoImagen;
        this .id = id;
        this .nombres = nombres;
        this .apellidos = apellidos;
        this .correo = correo;
        this .estado = estado;
        this .contrasenia = contrasenia;
        this .rolID = rolID;
    }

    //-> Getters and Setters
    public int getIdRecursoImagen() {
        return idRecursoImagen;
    }

    public void setIdRecursoImagen( int idRecursoImagen ) {
        this .idRecursoImagen = idRecursoImagen;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this .id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres( String nombres ) {
        this .nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos( String apellidos ) {
        this .apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo( String correo ) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado( String estado ) {
        this .estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia( String contrasenia ) {
        this .contrasenia = contrasenia;
    }

    public int getRolID() {
        return rolID;
    }

    public void setRolID( int rolID ) {
        this .rolID = rolID;
    }

}
