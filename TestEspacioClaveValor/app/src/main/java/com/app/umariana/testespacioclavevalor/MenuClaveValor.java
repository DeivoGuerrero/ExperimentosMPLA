package com.app.umariana.testespacioclavevalor;

import android.content.Context;
import android.content.SharedPreferences;
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

public class MenuClaveValor extends AppCompatActivity implements View.OnClickListener{

    public static int TEST_0 = 1,
            TEST_1 = 10,
            TEST_2 = 100,
            TEST_3 = 1000,
            TEST_4 = 10000;

    private Button test0, test1, test2, test3, test4;

    private File directorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_clave_valor);

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

        directorio = new File("/data/data/com.app.umariana.testespacioclavevalor/shared_prefs");

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
        long sum = 0;
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            for (int i = 0; i < archivos.length; i++) {
                sum += archivos[i].length();
            }
        }
        return sum;
    }

    public int numeroArchivo() {
        int num = 0;
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            num += archivos.length;
        }
        return num;
    }

    public void escribirLinea(String linea) {
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "test_espacio_cv.csv");

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
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            editor.putString("apellido", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            editor.putString("sexo", "M");
            editor.putLong("fecha_nacimiento", 11111111l);
            editor.putLong("fecha_ingreso", 22222222l);
            editor.putFloat("salario", 999999);
            editor.putBoolean("estado", true);
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarNombre() {
        for (int i = 0; i < numeroArchivo(); i++) {
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarNombre2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarFechaIngreso() {
        for (int i = 0; i < numeroArchivo(); i++) {
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("fecha_nacimiento", 33333333l);
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarFechaIngreso2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarSalario() {
        for (int i = 0; i < numeroArchivo(); i++) {
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("salario", 888888);
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarSalario2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarEstado() {
        for (int i = 0; i < numeroArchivo(); i++) {
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("estado", false);
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarEstado2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarEmpleado() {
        for (int i = 0; i < numeroArchivo(); i++) {
            SharedPreferences prefs = getSharedPreferences("Empleado" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
            editor.putString("apellido", "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
            editor.putString("sexo", "F");
            editor.putLong("fecha_nacimiento", 22222222l);
            editor.putLong("fecha_ingreso", 44444444l);
            editor.putFloat("salario", 555555);
            editor.putBoolean("estado", false);
            editor.commit();
        }
        return tamanioDirectorio();
    }

    public long modificarEmpleado2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long eliminarEmpleado() {
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            for (int i = 0; i < archivos.length; i++) {
                File f = (File) archivos[i];
                f.delete();
            }
        }
        return tamanioDirectorio();
    }

    public long eliminarEmpleado2(int cantidad) {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
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
