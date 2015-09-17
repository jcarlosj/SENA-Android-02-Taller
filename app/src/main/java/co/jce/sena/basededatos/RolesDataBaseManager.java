package co.jce.sena.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jce on 17/09/15.
 */
public class RolesDataBaseManager {

    //-> ATRIBUTOS (Constantes)
    private static final String T_NOMBRE = "roles";     //: Nombre de la tabla.
    //   Nombre de cada uno de los campos de la tabla "roles"
    public static final String  C_ID = "_id",                       //: Identificador de la tabla.
                                C_ROL = "nombre_rol",               //: Nombres del usuario.
                                C_DESCRIPCION = "descripcion_rol",  //: Apellidos del usuario.
                                C_ESTADO = "estado_rol";            //: Correo del usuario.

    //   Sentencia SQL para la creación de la tabla
    public static final String CREATE_TABLE = "create table if not exists " + T_NOMBRE + " ("
                                                    + C_ID + " integer primary key autoincrement, "
                                                    + C_ROL + " text not null, "
                                                    + C_DESCRIPCION + " text not null, "
                                                    + C_ESTADO + " text not null "
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

    //-> Constructor
    public RolesDataBaseManager( Context context ) {

        //-> Inicializamos el "Array" columnas con los nombres de las columnas de la tabla
        columnas = new String[] {
                C_ROL,
                C_DESCRIPCION,
                C_ESTADO
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

    public static String inicializarTabla() {
        return "insert into " + T_NOMBRE + " (" + C_ROL + ", " + C_DESCRIPCION + " ," + C_ESTADO + " )"
                               + " values ( \"Admin\", \"Rol para el administrador del sistema\", \"activo\" ),"
                               + " ( \"Demo\", \"Rol demo para el usuario estandar\", \"activo\" );";
    }

    public static String eliminarTabla() {
        return DROP_TABLE;
    }

    private ContentValues contenedorValores( String rol, String descripcion, String estado ) {
        cvCamposTabla = new ContentValues();
        cvCamposTabla .put( C_ROL, rol );
        cvCamposTabla .put( C_DESCRIPCION, descripcion );
        cvCamposTabla .put( C_ESTADO, estado );

        return cvCamposTabla;
    }

    //-> Esta forma de insertar datos se realiza usando un método proporcionado por Android para
    //   la inserción de registros en la tabla correspondiente de una BD.
    public long insertar( String rol, String descripcion, String estado ) {
        //-> db .insert( TABLE, NullColumnHack, ContentValues ); donde "NullColumnHack" puede ser "null" o un campo de la tabla que desde su creación
        //   permita valores núlos, por ejemplo "CN_PHONE" sea "null", si no existe un campo con esa característica basta con poner "null"
        //   insert into table; / SQL-> insert into table (telefono) values(null)

        return db .insert( T_NOMBRE, null, contenedorValores( rol, descripcion, estado ) );     //: Si devuelve -1 es por que ha ocurrido un error y no se ha realizado.
    }

}
