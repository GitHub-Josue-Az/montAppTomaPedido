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
import com.gob.proyectomontpedidosinicial.data.db.dao.TipoDeClienteDAO;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;

@Database(entities = {EntityCliente.class, EntityCondicionDePago.class, EntityDireccionCliente.class, EntityTipoDeCliente.class}, version = 2)
public abstract class AppDb extends RoomDatabase {

    private static AppDb INSTANCE;
    public abstract ClienteDAO clienteDAO();
    /*public abstract CondicionDePagoDAO condicionDePagoDAO();*/
    public abstract CondicionDePagoDAO condicionDePagoDA();
    public abstract DireccionClienteDAO direccionClienteDAO();
    public abstract TipoDeClienteDAO tipoDeClienteDAO();

    public static AppDb getAppDb(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constans.NAME_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

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








}
