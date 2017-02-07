package com.app.umariana.testvelocidaddatosestructurados;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MenuDatosEstructurados extends AppCompatActivity implements View.OnClickListener{

    public static int TEST_0 = 1,
            TEST_1 = 10,
            TEST_2 = 100,
            TEST_3 = 1000,
            TEST_4 = 10000;

    private Button test0, test1, test2, test3, test4;

    private DbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_datos_estructurados);

        test0 = (Button) findViewById(R.id.btn_test0);
        test0.setOnClickListener(this);

        test1 = (Button) findViewById(R.id.btn_test1);
        test1.setOnClickListener(this);

        test2 = (Button) findViewById(R.id.btn_test2);
        test2.setOnClickListener(this);

        test3 = (Button) findViewById(R.id.btn_test3);
        test3.setOnClickListener(this);

        test4 = (Button) findViewById(R.id.btn_test4);
        test4.setOnClickListener(this);

        manager = new DbManager(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test0:
                for (int i = 0; i < 100; i++) {
                    ejecutarTest(TEST_0);
                }
                Toast.makeText(getApplicationContext(), "Test 0 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test1:
                for (int i = 0; i < 100; i++) {
                    ejecutarTest(TEST_1);
                }
                Toast.makeText(getApplicationContext(), "Test 1 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test2:
                for (int i = 0; i < 100; i++) {
                    ejecutarTest(TEST_2);
                }
                Toast.makeText(getApplicationContext(), "Test 2 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test3:
                for (int i = 0; i < 100; i++) {
                    ejecutarTest(TEST_3);
                }
                Toast.makeText(getApplicationContext(), "Test 3 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test4:
                for (int i = 0; i < 100; i++) {
                    ejecutarTest(TEST_4);
                }
                Toast.makeText(getApplicationContext(), "Test 4 finalizado", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void escribirLinea(String linea) {
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "test_velocidad_de.csv");

            if (!f.exists()) {
                OutputStreamWriter fout =
                        new OutputStreamWriter(
                                new FileOutputStream(f));

                fout.write(linea);
                fout.close();

            } else {

                File temp = new File(f.getAbsolutePath() + ".tmp");

                BufferedReader br = new BufferedReader(new FileReader(f));
                PrintWriter pw = new PrintWriter(new FileWriter(temp));

                String row = null;

                while ((row = br.readLine()) != null) {
                    pw.println(row);
                    pw.flush();
                }
                pw.println(linea);
                pw.close();
                br.close();

                f.delete();
                temp.renameTo(f);
            }

        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
    }

    public long agregarEmpleado(int cantidad) {
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < cantidad; i++) {
            manager.agregarEmpleado("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                                    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                                    "M",
                                    11111111l,
                                    22222222l,
                                    999999,
                                    true);
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarNombre() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.NOMBRE_EMPLEADO, "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarNombre2() {
        long inicio = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        cv.put(manager.NOMBRE_EMPLEADO, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        manager.actualizarEmpleados(cv);
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarFechaIngreso() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.FECHA_INGRESO_EMPLEADO, 33333333l);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarFechaIngreso2() {
        long inicio = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        cv.put(manager.FECHA_INGRESO_EMPLEADO, 55555555l);
        manager.actualizarEmpleados(cv);
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarSalario() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.SALARIO_EMPLEADO, 888888);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarSalario2() {
        long inicio = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        cv.put(manager.SALARIO_EMPLEADO, 333333);
        manager.actualizarEmpleados(cv);
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEstado() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.ESTADO_EMPLEADO, false);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEstado2() {
        long inicio = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        cv.put(manager.ESTADO_EMPLEADO, true);
        manager.actualizarEmpleados(cv);
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEmpleado() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.NOMBRE_EMPLEADO, "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
                cv.put(manager.APELLIDO_EMPLEADO, "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                cv.put(manager.SEXO_EMPLEADO, "F");
                cv.put(manager.FECHA_NACIMIENTO_EMPLEADO, 22222222l);
                cv.put(manager.FECHA_INGRESO_EMPLEADO, 44444444l);
                cv.put(manager.SALARIO_EMPLEADO, 555555);
                cv.put(manager.ESTADO_EMPLEADO, false);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEmpleado2() {
        long inicio = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        cv.put(manager.NOMBRE_EMPLEADO, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        cv.put(manager.APELLIDO_EMPLEADO, "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        cv.put(manager.SEXO_EMPLEADO, "M");
        cv.put(manager.FECHA_NACIMIENTO_EMPLEADO, 66666666l);
        cv.put(manager.FECHA_INGRESO_EMPLEADO, 77777777l);
        cv.put(manager.SALARIO_EMPLEADO, 777777);
        cv.put(manager.ESTADO_EMPLEADO, true);
        manager.actualizarEmpleados(cv);
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long eliminarEmpleado() {
        long inicio = System.currentTimeMillis();
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                manager.eliminarEmpleado(c.getInt(0));
            } while (c.moveToNext());
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long eliminarEmpleado2(int cantidad) {
        agregarEmpleado(cantidad);
        long inicio = System.currentTimeMillis();
        manager.eliminarEmpleados();
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public void ejecutarTest(int test) {
        String linea = agregarEmpleado(test) + ";" +
                modificarNombre() + ";" +
                modificarNombre2() + ";" +
                modificarFechaIngreso() + ";" +
                modificarFechaIngreso2() + ";" +
                modificarSalario() + ";" +
                modificarSalario2() + ";" +
                modificarEstado() + ";" +
                modificarEstado2() + ";" +
                modificarEmpleado() + ";" +
                modificarEmpleado2() + ";" +
                eliminarEmpleado() + ";" +
                eliminarEmpleado2(test);
        escribirLinea(linea);
    }

}
