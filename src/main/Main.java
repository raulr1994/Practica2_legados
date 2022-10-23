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

import comunicacion.AppLegada;
import comunicacion.ComunicacionMusicSP;
import comunicacion.wc3270;

public class Main {
    static wc3270 comunicacionWS = wc3270.getInstancia();
    static ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

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
        Boolean exito = comunicacionSP.login(usuario, contraseña);
        System.out.println("login realizado");
        System.out.println(usuario);
        System.out.println(contraseña);
        if (exito) {
            System.out.println("Exito");
            /*Stage ventana = MainApp.primaryStage;
            Scene escena = ventana.getScene();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Menu.fxml"));*/

            /*try {
                escena.setRoot(loader.load());
            } catch (
                    IOException e) {
                e.printStackTrace();
            }*/
        } else {
            System.out.println("Fracaso");
        }
    }

}
