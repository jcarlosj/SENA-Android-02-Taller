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
    private static final String T_USUARIOS = "usuarios",      //: Nombre de la tabla USUARIOS.
                                T_ROLES = "roles";            //: Nombre de la tabla ROLES.

    //   Nombre de cada uno de los campos de la tabla USUARIOS
    public static final String  CU_ID = "_id",                 //: Identificador de la tabla.
                                CU_NOMBRES = "nombres",        //: Nombres del usuario.
                                CU_APELLIDOS = "apellidos",    //: Apellidos del usuario.
                                CU_CORREO = "correo",          //: Correo del usuario.
                                CU_CONTRASENA = "contrasena",  //: Contraseña del acceso.
                                CU_ESTADO = "estado",          //: Estado del usuario
                                CU_ROL_ID = "rol_id",          //: Identidicador del rol

    //   Nombre de cada uno de los campos de la tabla ROLES
    //   que interactuarán con la tabla USUARIOS
                                CR_ID = "_id",                 //: Identificador de la tabla.
                                CR_NOMBRE = "nombre",          //: Nombres del usuario.
                                CR_ESTADO = "estado";          //: Correo del usuario.

    //   Sentencia SQL para la creación de la tabla USUARIOS
    public static final String CREATE_TABLE = "create table if not exists " + T_USUARIOS + " ("
                                                    + CU_ID + " integer primary key, "
                                                    + CU_NOMBRES + " text not null,"
                                                    + CU_APELLIDOS + " text not null,"
                                                    + CU_CORREO + " text not null,"
                                                    + CU_CONTRASENA + " text not null,"
                                                    + CU_ESTADO + " text not null,"
                                                    + CU_ROL_ID + " integer not null"
                                                + " ); ";

    //   Sentencia SQL para eliminar la tabla
    public static final String DROP_TABLE = "drop table if exists " + T_USUARIOS;

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
            CU_ID,
            CU_NOMBRES,
            CU_APELLIDOS,
            CU_CORREO,
            CU_CONTRASENA,
            CU_ESTADO,
            CU_ROL_ID
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
        return "insert into " + T_USUARIOS + " (" + CU_ID + ", " + CU_NOMBRES + ", " + CU_APELLIDOS + ", " + CU_CORREO + " ," + CU_CONTRASENA + " ," + CU_ESTADO + " , " + CU_ROL_ID + " )"
                + " values ( 79878292, \"Super\", \"Administrador\", \"admin@biblioteca.sena.edu.co\", \"29287897\", \"activo\", 1 ),"
                + " ( \"79878293\", \"Usuario\", \"Demo\", \"demo@biblioteca.sena.edu.co\", \"39287897\", \"activo\", 2 );";
    }

    public static String eliminarTabla() {
        return DROP_TABLE;
    }

    private ContentValues contenedorValores( int numero_cedula, String nombres, String apellidos, String correo, String contrasena, String estado, int rol_id ) {
        cvCamposTabla = new ContentValues();
        cvCamposTabla .put( CU_ID, numero_cedula );
        cvCamposTabla .put( CU_NOMBRES, nombres );
        cvCamposTabla .put( CU_APELLIDOS, apellidos );
        cvCamposTabla .put( CU_CORREO, correo );
        cvCamposTabla .put( CU_CONTRASENA, contrasena );
        cvCamposTabla .put( CU_ESTADO, estado );
        cvCamposTabla .put( CU_ROL_ID, rol_id );

        return cvCamposTabla;
    }

    //-> Esta forma de insertar datos se realiza usando un método proporcionado por Android para
    //   la inserción de registros en la tabla correspondiente de una BD.
    public long insertar( int numero_cedula, String nombres, String apellidos, String correo, String contrasena, String estado, int rol_id ) {
        //-> db .insert( TABLE, NullColumnHack, ContentValues ); donde "NullColumnHack" puede ser "null" o un campo de la tabla que desde su creación
        //   permita valores núlos, por ejemplo "CN_PHONE" sea "null", si no existe un campo con esa característica basta con poner "null"
        //   insert into table; / SQL-> insert into table (telefono) values(null)
        return db .insert( T_USUARIOS, null, contenedorValores( numero_cedula, nombres, apellidos, correo, contrasena, estado, rol_id ) );     //: Si devuelve -1 es por que ha ocurrido un error y no se ha realizado.

    }

    public boolean consultarId( int numero_cedula ) {
        cRegistro = db. rawQuery( "select " + CU_ID + " from " + T_USUARIOS
                                            + " where " + CU_ID + " = " + numero_cedula , null  );

        //-> Validamos si el registro con "_id" numero_cedula fue encontrado o no.
        if( cRegistro .moveToFirst() ) {
            return true;    //: Existe
        }

        return false;       //: No existe
    }

    //-> Buscar si el usuario esta registrado y la contraseña coincide.
    public Cursor buscarUsuario( int numero_cedula, String contrasena ) {

        return db. rawQuery(                                    //-> Indices de recuperación
            "select " + T_USUARIOS + "." + CU_ID + ", "             //: 0 Para GUARDAR como Shared Preferences en spSesion
                      + T_USUARIOS + "." + CU_NOMBRES + ", "        //: 1 Para GUARDAR como Shared Preferences en spSesion
                      + T_USUARIOS + "." + CU_APELLIDOS + ", "      //: 2
                      + T_USUARIOS + "." + CU_ESTADO + ", "         //: 3 Para GUARDAR como Shared Preferences en spSesion
                      + T_ROLES + "." + CR_NOMBRE + ", "            //: 4 Para GUARDAR como Shared Preferences en spSesion
                      + T_ROLES + "." + CR_ID + ", "                //: 5
                      + T_ROLES + "." + CR_ESTADO                   //: 6 Para GUARDAR como Shared Preferences en spSesion
                    + " from " + T_USUARIOS
                    + " inner join " + T_ROLES
                        + " on " + T_USUARIOS + "." + CU_ROL_ID + " = " + T_ROLES + "." + CR_ID
                        + " where " + T_USUARIOS + "." + CU_ID + " = " + numero_cedula
                            + " and " + T_USUARIOS + "." + CU_CONTRASENA + " like \"" + contrasena + "\"",    //: Primer parametro de rawQuery: La consulta
            null                                                                                              //: Segundo parámetro de rawQuery: null
        );

        // select _id, nombres, apellidos, correo, estado from usuarios
        //        inner join roles on usuarios.rol_id = roles._id;

    }


}
