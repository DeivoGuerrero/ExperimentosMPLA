package com.app.umariana.testespaciodatosestructurados;

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

    private File directorio;

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

        directorio = new File("/data/data/com.app.umariana.testespaciodatosestructurados/databases");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test0:
                ejecutarTest(TEST_0);
                Toast.makeText(getApplicationContext(), "Test 0 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test1:
                ejecutarTest(TEST_1);
                Toast.makeText(getApplicationContext(), "Test 1 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test2:
                ejecutarTest(TEST_2);
                Toast.makeText(getApplicationContext(), "Test 2 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test3:
                ejecutarTest(TEST_3);
                Toast.makeText(getApplicationContext(), "Test 3 finalizado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test4:
                ejecutarTest(TEST_4);
                Toast.makeText(getApplicationContext(), "Test 4 finalizado", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public long tamanioDirectorio() {
        long total = 0;
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            for (int i = 0; i < archivos.length; i++) {
                total += archivos[i].length();
            }
        }
        return total;
    }

    public void escribirLinea(String linea) {
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "test_espacio_de.csv");

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
        for (int i = 0; i < cantidad; i++) {
            manager.agregarEmpleado("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                    "M",
                    11111111l,
                    22222222l,
                    999999,
                    true);
        }
        return tamanioDirectorio();
    }

    public long modificarNombre() {
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.NOMBRE_EMPLEADO, "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        return tamanioDirectorio();
    }

    public long modificarNombre2() {
        ContentValues cv = new ContentValues();
        cv.put(manager.NOMBRE_EMPLEADO, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        manager.actualizarEmpleados(cv);
        return tamanioDirectorio();
    }

    public long modificarFechaIngreso() {
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.FECHA_INGRESO_EMPLEADO, 33333333l);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        return tamanioDirectorio();
    }

    public long modificarFechaIngreso2() {
        ContentValues cv = new ContentValues();
        cv.put(manager.FECHA_INGRESO_EMPLEADO, 55555555l);
        manager.actualizarEmpleados(cv);
        return tamanioDirectorio();
    }

    public long modificarSalario() {
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.SALARIO_EMPLEADO, 888888);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        return tamanioDirectorio();
    }

    public long modificarSalario2() {
        ContentValues cv = new ContentValues();
        cv.put(manager.SALARIO_EMPLEADO, 333333);
        manager.actualizarEmpleados(cv);
        return tamanioDirectorio();
    }

    public long modificarEstado() {
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                ContentValues cv = new ContentValues();
                cv.put(manager.ESTADO_EMPLEADO, false);
                manager.actualizarEmpleado(cv, c.getInt(0));
            } while (c.moveToNext());
        }
        return tamanioDirectorio();
    }

    public long modificarEstado2() {
        ContentValues cv = new ContentValues();
        cv.put(manager.ESTADO_EMPLEADO, true);
        manager.actualizarEmpleados(cv);
        return tamanioDirectorio();
    }

    public long modificarEmpleado() {
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
        return tamanioDirectorio();
    }

    public long modificarEmpleado2() {
        ContentValues cv = new ContentValues();
        cv.put(manager.NOMBRE_EMPLEADO, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        cv.put(manager.APELLIDO_EMPLEADO, "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        cv.put(manager.SEXO_EMPLEADO, "M");
        cv.put(manager.FECHA_NACIMIENTO_EMPLEADO, 66666666l);
        cv.put(manager.FECHA_INGRESO_EMPLEADO, 77777777l);
        cv.put(manager.SALARIO_EMPLEADO, 777777);
        cv.put(manager.ESTADO_EMPLEADO, true);
        manager.actualizarEmpleados(cv);
        return tamanioDirectorio();
    }

    public long eliminarEmpleado() {
        Cursor c = manager.listarEmpleados();
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                manager.eliminarEmpleado(c.getInt(0));
            } while (c.moveToNext());
        }
        return tamanioDirectorio();
    }

    public long eliminarEmpleado2(int cantidad) {
        agregarEmpleado(cantidad);
        manager.eliminarEmpleados();
        return tamanioDirectorio();
    }

    public void ejecutarTest(int test) {
        String linea = tamanioDirectorio() + ";" +
                agregarEmpleado(test) + ";" +
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
