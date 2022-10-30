package main;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Scanner;

import comunicacion.gestorTareas;
import comunicacion.ComunicacionMusicSP;
import comunicacion.wc3270;

public class Main {
    static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    static gestorTareas appLegada = gestorTareas.getInstancia(comunicacionWS);

	public static void main(String[] args) throws IOException {
		try {
			comunicacionWS.assertConnected();
            logearse();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    private static void logearse() throws Exception {
    	comunicacionSP.conectar();
    	Thread.sleep(100);
        final String usuario = "grupo_09";
        final String contraseña = "secreto6";
        System.out.println("intentando login");
        //comunicacionWS.verTodo();
        Boolean exito = comunicacionSP.login(usuario, contraseña);
        System.out.println("login realizado");
        /*System.out.println(usuario);
        System.out.println(contraseña);*/
        if (exito) {
            System.out.println("Exito");
            //comunicacionWS.verTodo();
            //appLegada.crearTareaEspecifica("11","12","Tarea1","Prueba de tarea 1");
            //appLegada.crearTareaEspecifica("12","13","Tarea2","Prueba de tarea 2");
            //appLegada.crearTareaEspecifica("13","14","Tarea3","Prueba de tarea 3");
            //appLegada.cargarListadoTareasEspecificas();
            //appLegada.cargarListadoTareasEspecificas();
            //comunicacionWS.verTodo();
            //Acciones en interfaz grafica
            //System.out.println(comunicacionSP.verPantalla());
        } else {
            System.out.println("Fracaso");
        }
        comunicacionSP.finalizarPrograma();
    }

}
