package com.gob.proyectomontpedidosinicial.data.db.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gob.proyectomontpedidosinicial.data.constans.Constans;
import com.gob.proyectomontpedidosinicial.data.db.dao.ClienteDAO;
import com.gob.proyectomontpedidosinicial.data.db.dao.CondicionDePagoDAO;
import com.gob.proyectomontpedidosinicial.data.db.dao.DireccionClienteDAO;
import com.gob.proyectomontpedidosinicial.data.db.dao.LoginUsuarioPostDAO;
import com.gob.proyectomontpedidosinicial.data.db.dao.ProductoDAO;
import com.gob.proyectomontpedidosinicial.data.db.dao.TipoDeClienteDAO;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityLoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;

@Database(entities = {EntityCliente.class, EntityCondicionDePago.class, EntityDireccionCliente.class,
        EntityTipoDeCliente.class, EntityProductoPorUsuario.class, EntityLoginUsuarioPost.class}, version = 4)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;
    public abstract ClienteDAO clienteDAO();
    /*public abstract CondicionDePagoDAO condicionDePagoDAO();*/
    public abstract CondicionDePagoDAO condicionDePagoDA();
    public abstract DireccionClienteDAO direccionClienteDAO();
    public abstract TipoDeClienteDAO tipoDeClienteDAO();
    public abstract ProductoDAO productoDAO();
    public abstract LoginUsuarioPostDAO loginUsuarioPostDAO();

    public static AppDb getAppDb(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constans.NAME_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .build();
        }
        return INSTANCE;
    }

    /* .addMigrations(MIGRATION_2_3) */

    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("DROP TABLE entitycliente");
database.execSQL("CREATE TABLE entitycliente(uid INTEGER PRIMARY KEY NOT NULL,direccion TEXT,fecha_update TEXT,fecha_creacion TEXT,estado_sincronizacion TEXT,gps_longitud TEXT,gps_latitud TEXT,zona_venta TEXT,codigo_vendedor TEXT,correo_cliente TEXT,telefono_cliente TEXT,coa_cliente TEXT,razon_social TEXT,id_cliente TEXT)");

       database.execSQL("DROP TABLE entitycondicionpago");
       database.execSQL("CREATE TABLE entitycondicionpago(uid INTEGER PRIMARY KEY NOT NULL,nombre_condicion TEXT,id_condicion TEXT)");

       database.execSQL("DROP TABLE entitydireccioncliente");
       database.execSQL("CREATE TABLE entitydireccioncliente(uid INTEGER PRIMARY KEY NOT NULL,descripcion_direccion TEXT,estado_direccion TEXT,tipo_direccion TEXT,departamento_direccion TEXT,provincia_direccion TEXT,distrito_direccion TEXT,clientes_id_cliente TEXT,id_direccion TEXT)");
          }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
     database.execSQL("CREATE TABLE entityproducto(productouid INTEGER PRIMARY KEY NOT NULL,estado TEXT,fecha_creacion TEXT,fecha_update TEXT,codigo TEXT,nombre_corto TEXT,nombre_completo TEXT,id_producto TEXT,subtotal TEXT,promocion TEXT,costo TEXT,cantidad TEXT)");
     database.execSQL("CREATE TABLE entityusuariopost(loginusuariouid INTEGER PRIMARY KEY NOT NULL,almacen TEXT,tipo_usuario_id_tipo_usuario TEXT,unique_id TEXT,correo_usuario TEXT,codigo_vendedor TEXT,fecha_clave TEXT,estado_usuario TEXT,clave_usuario TEXT,usuario_usuario TEXT,nombre_usuario TEXT,id_usuario TEXT)");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE entityproducto");
            database.execSQL("DROP TABLE entityusuariopost");
            database.execSQL("CREATE TABLE entityproductoporusuario(productouid INTEGER PRIMARY KEY NOT NULL,estado TEXT,fecha_creacion TEXT,fecha_update TEXT,codigo TEXT,nombre_corto TEXT,nombre_completo TEXT,id_producto TEXT,subtotal TEXT,promocion TEXT,costo REAL NOT NULL,cantidad INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE entityusuariopost(loginusuariouid INTEGER PRIMARY KEY NOT NULL,almacen TEXT,tipo_usuario_id_tipo_usuario TEXT,unique_id TEXT,correo_usuario TEXT,codigo_vendedor TEXT,fecha_clave TEXT,estado_usuario TEXT,clave_usuario TEXT,usuario_usuario TEXT,nombre_usuario TEXT,id_usuario TEXT)");
        }
    };



    /*
    * CADA QUE SE HAGA UNA MIGRACIÓN
    * PRIMERO DEBES CAMBIAR LA VERSION
    * LUEGO AGREGAR LA MIGRACION AL BUILD
    * LUEGO LA FUNCION DE MIGRACIÓN CON LOS NUMEROS(VERIFICAR ANTES DE EJECUTAR QUE LOS NOMBRES Y VALORES DE LAS VARIABLES SEAN CORRECTAS)
    * */


}
