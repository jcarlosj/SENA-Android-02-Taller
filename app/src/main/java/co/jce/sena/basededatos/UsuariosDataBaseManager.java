package co.jce.sena.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jce on 14/09/15.
 */
public class UsuariosDataBaseManager  {

    //-> ATRIBUTOS (Constantes)
    private static final String T_NOMBRE = "usuarios";        //: Nombre de la tabla.
    //   Nombre de cada uno de los campos de la tabla "contactos"
    public static final String  C_ID = "_id",                 //: Identificador de la tabla.
                                C_NOMBRES = "nombres",        //: Nombres del usuario.
                                C_APELLIDOS = "apellidos",    //: Apellidos del usuario.
                                C_CORREO = "correo",          //: Correo del usuario.
                                C_CONTRASENA = "contrasena",  //: Contraseña del acceso.
                                C_ROL_ID = "rol_id";           //: Identidicador del rol

    //   Sentencia SQL para la creación de la tabla
    public static final String CREATE_TABLE = "create table if not exists " + T_NOMBRE + " ("
                                                    + C_ID + " integer primary key, "
                                                    + C_NOMBRES + " text not null,"
                                                    + C_APELLIDOS + " text not null,"
                                                    + C_CORREO + " text not null,"
                                                    + C_CONTRASENA + " text not null,"
                                                    + C_ROL_ID + " text not null"
                                                + " ); ";

    //   Sentencia SQL para eliminar la tabla
    public static final String DROP_TABLE = "drop table if exists " + T_NOMBRE;

    //-> Atributos (Comunes)
    private String columnas[];

    //-> Atributos (Especiales)
    private DataBaseHelper helper;
    private SQLiteDatabase db;
    private ContentValues cvCamposTabla;
    private Cursor cRegistro;
    private Context context;
    private RolesDataBaseManager roles;

    //-> Constructor
    public UsuariosDataBaseManager( Context context ) {

        this .context = context;

        //-> Inicializamos el "Array" columnas con los nombres de las columnas de la tabla
        columnas = new String[] {
            C_ID,
            C_NOMBRES,
            C_APELLIDOS,
            C_CORREO,
            C_CONTRASENA,
            C_ROL_ID
        };

        //-> Instanciamos la clase "DataBaseHelper"
        helper = new DataBaseHelper( context );
        db = helper .getWritableDatabase();     //: Esta línea permite la creación de la BD. el método ".getWritableDatabase()" es un método de
                                                //: la clase "SQLiteOpenHelper" que se encarga de crear la BD si no existe y la establece en modo
                                                //: de escritura, si ya existe solo la devuelve.

    }

    public static String crearTabla() {
        return CREATE_TABLE;
    }

    public static String eliminarTabla() {
        return DROP_TABLE;
    }

    private ContentValues contenedorValores( int numero_cedula, String nombres, String apellidos, String correo, String contrasena, int rol_id ) {
        cvCamposTabla = new ContentValues();
        cvCamposTabla .put( C_ID, numero_cedula );
        cvCamposTabla .put( C_NOMBRES, nombres );
        cvCamposTabla .put( C_APELLIDOS, apellidos );
        cvCamposTabla .put( C_CORREO, correo );
        cvCamposTabla .put( C_CONTRASENA, contrasena );
        cvCamposTabla .put( C_ROL_ID, rol_id );

        return cvCamposTabla;
    }

    //-> Esta forma de insertar datos se realiza usando un método proporcionado por Android para
    //   la inserción de registros en la tabla correspondiente de una BD.
    public long insertar( int numero_cedula, String nombres, String apellidos, String correo, String contrasena, int rol_id ) {
        //-> db .insert( TABLE, NullColumnHack, ContentValues ); donde "NullColumnHack" puede ser "null" o un campo de la tabla que desde su creación
        //   permita valores núlos, por ejemplo "CN_PHONE" sea "null", si no existe un campo con esa característica basta con poner "null"
        //   insert into table; / SQL-> insert into table (telefono) values(null)
        return db .insert( T_NOMBRE, null, contenedorValores( numero_cedula, nombres, apellidos, correo, contrasena, rol_id ) );     //: Si devuelve -1 es por que ha ocurrido un error y no se ha realizado.

    }

    public boolean consultarId( int numero_cedula ) {
        cRegistro = db. rawQuery( "select " + C_ID + " from " + T_NOMBRE
                                            + " where " + C_ID + " = " + numero_cedula , null  );

        //-> Validamos si el registro con "_id" numero_cedula fue encontrado o no.
        if( cRegistro .moveToFirst() ) {
            return true;    //: Existe
        }

        return false;       //: No existe
    }

}
