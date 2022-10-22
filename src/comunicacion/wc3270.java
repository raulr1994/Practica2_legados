package comunicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class wc3270 implements wc3270Class {
	public String ws3270exe = "C:\\Program Files\\wc3270\\wc3270.exe";
	
	protected Process procesoWc3270;
	protected InputStream lectura; //Enviar comandos
    protected PrintWriter teclado; //Recibir respuestas de la terminal
    
    protected final String ENTER = "ENTER"; // tecla enter
    private final String FUNCTION_KEY = "PF(%d)"; // tecla F3
    protected static final String OK = "OK";
    protected static final String ASCII = "ascii";
    protected static final String MORE = "More...";
    protected static final String CADENA_CONEXION = "connect %s:%s";
    
    public long millis= 100;

    private static wc3270 instancia = null;
    
    protected wc3270() {
        try {
            this.procesoWc3270 = Runtime.getRuntime().exec(ws3270exe);
            lectura = this.procesoWc3270.getInputStream();
            teclado = new PrintWriter(new OutputStreamWriter(this.procesoWc3270.getOutputStream()));
        } catch (FileNotFoundException ef) {
            System.err.println("Error, ejecutable ws3270.exe no encontrado");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Error, no se pudo conectar con ws3270.exe");
            System.exit(1);
        }
    }

    public static wc3270 getInstancia() {
        if (instancia == null) {
            instancia = new wc3270();
        }
        return instancia;
    }    
    
    @Override
    public StringBuilder leerPantalla() {
        StringBuilder cadena = new StringBuilder();
        try {
            while (lectura.available() == 0) { //Espera a que se llene el buffer
            	//System.out.println("lectura.available() == 0");
            	
            }; 
            while (lectura.available() > 0) {
            	//System.out.println("lectura.available() > 0");
                cadena.append((char) lectura.read());
            }
        } catch (IOException ex) {
            cadena = null;
        } finally {
            return cadena;
        }
        
    }

    @Override
    public void enter() {
        escribirLinea(ENTER);
    }
    
    @Override
    public void ascii() {
        escribirLineaNoEsperaOK(ASCII);
    }
    
    @Override
    public boolean hayMasTexto() {
        ascii();
        String cadenaAux = leerPantalla().toString();
        return cadenaAux.contains(MORE);
    }

    @Override
    public void teclaFuncion(int teclaF) {
        String funcion = String.format(FUNCTION_KEY, teclaF);
        escribirLineaNoEsperaOK(funcion);
    }

    @Override
    public void escribirCadena(String cadena) {
        escribirLinea("string " + cadena);
    }

    @Override
    public void escribirLinea(String cadena) {
        //do {
            //cadena += "\n";
            System.out.println("Intentando escribir " + cadena);
            this.teclado.write(cadena);
            //this.teclado.println(cadena);
            this.teclado.flush();
        //} while (leerPantalla().toString().contains(OK));
        System.out.println("Exito de escritura");
        //Espera una señal de OK o de MORE...
    }
    
    @Override
    public void escribirLineaNoEsperaOK(String cadena) {
        cadena += "\n";
        this.teclado.write(cadena);
        //this.teclado.println(cadena);
        this.teclado.flush();
    }

    @Override
    public boolean buscarCadena(String cadena) {
        ascii();
        System.out.println("Intentando buscar " + cadena);
        if(leerPantalla().toString().contains(cadena)) {
        	 System.out.println("Cadena encontrada");
        	 return true;
        }
        else {
        	return false;
        }
        
        //return leerPantalla().toString().contains(cadena);
    }
	
    @Override
    public void conectar(String ip, String puerto) throws RuntimeException {
    	System.out.println("Intento de conectar");
        String cadenaConexion = String.format(CADENA_CONEXION, ip, puerto);
        escribirLinea(cadenaConexion);
        enter();
        try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Exito al conectar");
    }
}
