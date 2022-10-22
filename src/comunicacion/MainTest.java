package comunicacion;

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

public class MainTest {
	public static final String ws3270exe = "C:\\Program Files\\wc3270\\wc3270.exe";
	//public static final String wx3270exe = "C:\\Program Files\\wx3270\\wx3270.exe";
    protected static final String OK = "OK";
    protected static final String ASCII = "ascii";
    protected static final String MORE = "More...";
    protected static final String orderConnect = "Connect(155.210.152.51:3270)";
	
	static Process process; 
	//static InputStream lectura;
	//PrintWriter err = new PrintWriter(new OutputStreamWriter(process.getErrorStream()));
	//static PrintWriter ordenes;
	
    
    public static void escribirLineaNoEsperaOK(String cadena, PrintWriter ordenes) {
        cadena += "\n";
        ordenes.write(cadena);
        ordenes.flush();
    }
    
    public static void ascii(PrintWriter ordenes) {
        escribirLineaNoEsperaOK(ASCII,ordenes);
    }
	
    @SuppressWarnings("finally")
	public static StringBuilder leerPantalla(InputStream lectura) {
        StringBuilder cadena = new StringBuilder();
        try {
            while (lectura.available() == 0); //Espera a que se llene el buffer
            while (lectura.available() > 0) {
                cadena.append((char) lectura.read());
            }
        } catch (IOException ex) {
            cadena = null;
        } finally {
            return cadena;
        }
    }
	
    public static void escribirLinea(String cadena, PrintWriter ordenes, InputStream lectura) {
        
        cadena += "\n";
        //ordenes.write(cadena);
        ordenes.println(cadena);
        ordenes.flush();
        while (leerPantalla(lectura).toString().contains(OK) || leerPantalla(lectura).toString().contains(MORE)) {};
        //Espera una señal de OK o de MORE...
    }
	
	@SuppressWarnings("finally")
	public static StringBuilder leerLinea(InputStream lectura) {
		StringBuilder cadena = new StringBuilder();
        try {
            while (lectura.available() == 0); //Espera a que se llene el buffer
            while (lectura.available() > 0) {
                cadena.append((char) lectura.read());
            }
        } catch (IOException ex) {
            cadena = null;
        } finally {
            return cadena;
        }		
	}
	
	static void mostrarSalida(InputStream in) {
		try {
	         String strg = "";
	         String txaMsg = "";
	         //PrintWriter out = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
	         BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
	         while (((strg = bfr.readLine()) != null)) {
	             System.out.println(strg);
	             strg = strg.trim();
	             //Messages.postDebug(strg);
	             strg = strg.toLowerCase();
	             if (txaMsg != null) {
	               txaMsg += strg;
	               txaMsg += "\n";
	             }
	             //System.out.println("lectura nueva");
	           }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
		//System.out.println("terminada lectura");
	}
	
	static void mostrarSalida2(Scanner stdout) {
        while (stdout.hasNextLine()) {
    	    System.out.println("Process output: " + stdout.nextLine());
        }
		
	}
	
	public static boolean checkOK(InputStream lectura) {
	    try {
	    	//lectura.readLine();
	    	leerLinea(lectura);
	        //return lectura.readLine().contains("ok");
	    	return leerLinea(lectura).toString().contains("ok");
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static void execute(String query, PrintWriter ordenes, InputStream lectura) throws IOException {
	    if (query != null) {
	    	//ordenes.write(query+"\n");
	    	ordenes.println(query);
	    	ordenes.flush();
	    }
	    /*if (!checkOK(lectura)) {
	        System.out.println(">>> FAIL QUERY");
	    }*/
	}

	public static void enter(PrintWriter ordenes, InputStream lectura) throws IOException {
		execute("Enter\n",ordenes,lectura);
		//ordenes.flush();
	    /*if (!checkOK(lectura)) {
	        System.out.println(">>> FAIL ENTER");
	    }*/
	}

	public static void main(String[] args) throws IOException {
		Process process = Runtime.getRuntime().exec("C:\\Program Files\\wc3270\\wc3270.exe");
		//Process process = Runtime.getRuntime().exec("C:\\Program Files\\wc3270\\wc3270.exe +S");
		//Process process = Runtime.getRuntime().exec("C:\\Program Files\\wc3270\\wc3270.exe",null);
		//Process process = Runtime.getRuntime().exec("C:\\Program Files\\x3270is\\s3270.exe",null);
		
		/*ProcessBuilder builder = new ProcessBuilder("C:\\Program Files\\wc3270\\wc3270.exe");
		builder.redirectErrorStream(false);
		Process process = builder.start();*/
		
		
		InputStream lectura = process.getInputStream();
		OutputStream escritura = process.getOutputStream();
		InputStream errorStream = process.getErrorStream();
		
		Scanner stdout =  new Scanner( new BufferedReader(new InputStreamReader(lectura)));	
		//PrintWriter ordenes = new PrintWriter(new BufferedWriter(new OutputStreamWriter(escritura)), true);
		PrintWriter ordenes = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
		BufferedWriter outBufWriter = new BufferedWriter( new OutputStreamWriter(process.getOutputStream()));
		//PrintWriter err = new PrintWriter(new outputStreamWriter(errorStream));/*
		
	    //outBufWriter.write(orderConnect);
		/*outBufWriter.write("help"+"\n");
	    outBufWriter.flush();
	    outBufWriter.write("Enter\n");
	    outBufWriter.flush();*/
	    BufferedReader inBufReader= new BufferedReader(new InputStreamReader(lectura));
	     
	    /*String response;
	    while ( (response = inBufReader.readLine()) != null ) {
	    		System.out.println( response );
	    }
		inBufReader.close();*/
		//outBufWriter.close();
	    
		/*ascii(ordenes);
		mostrarSalida2(stdout);*/
		//enter(ordenes,lectura);

		//mostrarSalida(lectura);
		/*ordenes.println("help");
        ordenes.flush();*/
		
		execute(orderConnect,ordenes,lectura);
		enter(ordenes,lectura);
		
		/*escribirLinea("String(help)",ordenes,lectura);
		enter(ordenes,lectura);*/
		
        /*ordenes.println("ENTER");
        ordenes.flush();*/
		
		/*ascii(ordenes);
		System.out.println(leerPantalla(lectura).toString().contains("Enter"));*/
        
		//leerPantalla(lectura);
		
		ascii(ordenes);
		mostrarSalida2(stdout);

		//enter(ordenes,lectura);
		/*ascii(ordenes);
		mostrarSalida(lectura);*/

	    int c = 0;
	    while ((c = errorStream.read()) != -1) {
	      System.out.print((char)c);
	    }

		System.out.println("Fin de la prueba");
		process.destroy();
	}

}
