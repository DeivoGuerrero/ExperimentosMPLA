package com.app.umariana.testvelocidaddatosestructurados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guerr on 06/02/2017.
 */

public class DbManager {

    public static final String TABLE_NAME = "empleados",
            ID_EMPLEADO = "_id",
            NOMBRE_EMPLEADO = "nombre",
            APELLIDO_EMPLEADO = "apellido",
            SEXO_EMPLEADO = "sexo",
            FECHA_NACIMIENTO_EMPLEADO = "fecha_nacimiento",
            FECHA_INGRESO_EMPLEADO = "fecha_ingreso",
            SALARIO_EMPLEADO = "salario",
            ESTADO_EMPLEADO = "estado";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID_EMPLEADO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE_EMPLEADO + " TEXT NOT NULL, " +
            APELLIDO_EMPLEADO + " TEXT NOT NULL, " +
            SEXO_EMPLEADO + " VARCHAR(1) NOT NULL, " +
            FECHA_NACIMIENTO_EMPLEADO + " DATETIME NOT NULL, " +
            FECHA_INGRESO_EMPLEADO + " DATETIME NOT NULL, " +
            SALARIO_EMPLEADO + " FLOAT NOT NULL, " +
            ESTADO_EMPLEADO + " BOOLEAN NOT NULL);";

    private DbHelper bd_helper;
    private SQLiteDatabase db;

    public DbManager(Context miContext) {
        bd_helper = new DbHelper(miContext);
        db = bd_helper.getWritableDatabase();
    }

    private ContentValues contentValues (String nombre, String apellido, String sexo,
                                         long fechaNacimiento, long fehcaIngreso,
                                         float salario, boolean estado) {
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE_EMPLEADO, nombre);
        cv.put(APELLIDO_EMPLEADO, apellido);
        cv.put(SEXO_EMPLEADO, sexo);
        cv.put(FECHA_NACIMIENTO_EMPLEADO, fechaNacimiento);
        cv.put(FECHA_INGRESO_EMPLEADO, fehcaIngreso);
        cv.put(SALARIO_EMPLEADO, salario);
        cv.put(ESTADO_EMPLEADO, estado);
        return cv;
    }

    public void agregarEmpleado (String nombre, String apellido, String sexo, long fechaNacimiento,
                                 long fehcaIngreso, float salario, boolean estado) {
        db.insert(TABLE_NAME, null, contentValues(nombre,apellido,sexo,fechaNacimiento,fehcaIngreso,salario,estado));
    }

    public Cursor listarEmpleados () {
        String[] columnas = new String[] {
                ID_EMPLEADO,
                NOMBRE_EMPLEADO,
                APELLIDO_EMPLEADO,
                SEXO_EMPLEADO,
                FECHA_NACIMIENTO_EMPLEADO,
                FECHA_INGRESO_EMPLEADO,
                SALARIO_EMPLEADO,
                ESTADO_EMPLEADO };
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public void eliminarEmpleado (int id) {
        db.delete(TABLE_NAME, ID_EMPLEADO + "=?", new String[]{String.valueOf(id)});
    }

    public void eliminarEmpleados (){
        db.delete(TABLE_NAME, null, null);
    }

    public void actualizarEmpleado (ContentValues cv, int id) {
        db.update(TABLE_NAME,cv,ID_EMPLEADO + "=?",new String[]{String.valueOf(id)});
    }
    public void actualizarEmpleados (ContentValues cv){
        db.update(TABLE_NAME,cv,null,null);
    }
}
