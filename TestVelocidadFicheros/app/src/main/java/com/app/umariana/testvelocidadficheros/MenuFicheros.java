package com.app.umariana.testvelocidadficheros;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MenuFicheros extends AppCompatActivity implements View.OnClickListener{

    public static int TEST_0 = 1,
            TEST_1 = 10,
            TEST_2 = 100,
            TEST_3 = 1000,
            TEST_4 = 10000;

    private Button test0, test1, test2, test3, test4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ficheros);

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
            File f = new File(ruta_sd.getAbsolutePath(), "test_velocidad_aa.csv");

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
            try
            {
                OutputStreamWriter fout=
                        new OutputStreamWriter(
                                openFileOutput("empleados.txt", Context.MODE_APPEND));

                fout.write( i +
                        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:" +
                        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:" +
                        "M:" +
                        "11111111l:" +
                        "22222222l:" +
                        "999999:" +
                        "true");
                fout.close();
            }
            catch (Exception ex)
            {
                Log.e("Ficheros", "Error al escribir fichero a memoria interna");
            }
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarNombre() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            String texto = null;
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while ((texto = br.readLine() ) != null) {
                String[] linea = texto.split(":");
                linea[1] = "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY";
                pw.write(linea[0] + ":" + //id
                        linea[1] + ":" + //nombre
                        linea[2] + ":" + //apellido
                        linea[3] + ":" + //sexo
                        linea[4] + ":" + //fechaNacimiento
                        linea[5] + ":" + //fechaIngreso
                        linea[6] + ":" + //salario
                        linea[7] + ":" + //estado empleado
                        "\n"); //salto de linea
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarNombre2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarFechaIngreso() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            String texto = null;
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while ((texto = br.readLine() ) != null) {
                String[] linea = texto.split(":");
                linea[5] = "33333333l";
                pw.write(linea[0] + ":" + //id
                        linea[1] + ":" + //nombre
                        linea[2] + ":" + //apellido
                        linea[3] + ":" + //sexo
                        linea[4] + ":" + //fechaNacimiento
                        linea[5] + ":" + //fechaIngreso
                        linea[6] + ":" + //salario
                        linea[7] + ":" + //estado empleado
                        "\n"); //salto de linea
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarFechaIngreso2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarSalario() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            String texto = null;
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while ((texto = br.readLine() ) != null) {
                String[] linea = texto.split(":");
                linea[6] = "888888";
                pw.write(linea[0] + ":" + //id
                        linea[1] + ":" + //nombre
                        linea[2] + ":" + //apellido
                        linea[3] + ":" + //sexo
                        linea[4] + ":" + //fechaNacimiento
                        linea[5] + ":" + //fechaIngreso
                        linea[6] + ":" + //salario
                        linea[7] + ":" + //estado empleado
                        "\n"); //salto de linea
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarSalario2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarEstado() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            String texto = null;
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while ((texto = br.readLine() ) != null) {
                String[] linea = texto.split(":");
                linea[7] = "false";
                pw.write(linea[0] + ":" + //id
                        linea[1] + ":" + //nombre
                        linea[2] + ":" + //apellido
                        linea[3] + ":" + //sexo
                        linea[4] + ":" + //fechaNacimiento
                        linea[5] + ":" + //fechaIngreso
                        linea[6] + ":" + //salario
                        linea[7] + ":" + //estado empleado
                        "\n"); //salto de linea
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEstado2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long modificarEmpleado() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            String texto = null;
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while ((texto = br.readLine() ) != null) {
                String[] linea = texto.split(":");
                linea[1] = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
                linea[2] = "YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY";
                linea[3] = "F";
                linea[4] = "22222222l";
                linea[5] = "44444444l";
                linea[6] = "555555";
                linea[7] = "false";
                pw.write(linea[0] + ":" + //id
                        linea[1] + ":" + //nombre
                        linea[2] + ":" + //apellido
                        linea[3] + ":" + //sexo
                        linea[4] + ":" + //fechaNacimiento
                        linea[5] + ":" + //fechaIngreso
                        linea[6] + ":" + //salario
                        linea[7] + ":" + //estado empleado
                        "\n"); //salto de linea
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long modificarEmpleado2() {
        long inicio = System.currentTimeMillis();
        long fin = System.currentTimeMillis();
        //Método no disponible con este mecanismo.
        return -1;
    }

    public long eliminarEmpleado() {
        long inicio = System.currentTimeMillis();
        try {
            InputStreamReader reader = new InputStreamReader(openFileInput("empleados.txt"));
            BufferedReader br = new BufferedReader(reader);
            OutputStreamWriter pw = new OutputStreamWriter(openFileOutput("empleados.tmp", Context.MODE_PRIVATE));
            while (br.readLine() != null) {
                pw.write("");
                pw.flush();
            }
            pw.close();
            br.close();


            File archivo = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.txt");
            File temp = new File("/data/data/app.umariana.com.testvelocidadficheros/files/empleados.tmp");

            archivo.delete();
            temp.renameTo(archivo);
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
        long fin = System.currentTimeMillis();
        return fin - inicio;
    }

    public long eliminarEmpleado2(int cantidad) {
        agregarEmpleado(cantidad);
        long inicio = System.currentTimeMillis();
        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("empleados.txt", Context.MODE_PRIVATE));

            fout.write( "");
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
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
