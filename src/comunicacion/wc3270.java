package comunicacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class wc3270 implements wc3270Class {
	//public String ws3270exe = "C:\\Program Files\\wc3270\\wc3270.exe";
	
	//public String s3270 = "C:\\Program Files\\x3270is\\s3270.exe";
	//public String ws3270exe = "cmd";
	public String s3270 = "C:\\Program Files\\wc3270\\s3270.exe";
	
	protected Process procesoS3270;
	protected static InputStream lectura; //Enviar comandos
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
            this.procesoS3270 = Runtime.getRuntime().exec(s3270); //proceso
            lectura = this.procesoS3270.getInputStream(); //salida
            teclado = new PrintWriter(new OutputStreamWriter(this.procesoS3270.getOutputStream())); //entrada
        } catch (FileNotFoundException ef) {
            System.err.println("Error, ejecutable s3270.exe no encontrado");
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
        do {
            cadena += "\n";
            //System.out.println("Intentando escribir " + cadena);
            this.teclado.write(cadena);
            //this.teclado.println(cadena);
            this.teclado.flush();
        } while (leerPantalla().toString().contains(OK));
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
        //System.out.println("Intentando buscar " + cadena);
        String busqueda = leerPantalla().toString();
    	//System.out.println("Cadena leida " + busqueda);        
        if(busqueda.contains(cadena)){
        	 //System.out.println("Cadena encontrada");
        	 return true;
        }
        else {
        	return false;
        }
        
        //return leerPantalla().toString().contains(cadena);
    }
	
    @Override
    public void conectar(String ip, String puerto) throws RuntimeException {
    	//System.out.println("Intento de conectar");
        String cadenaConexion = String.format(CADENA_CONEXION, ip, puerto);
        escribirLinea(cadenaConexion);
        enter();
        try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("Exito al conectar");
    }
    
    public void assertConnected() {
        if (instancia == null) {
            throw new RuntimeException("not connected");
        }
        else {
        	System.out.println("Objeto creado con exito");
        }
    }
    
    public void verTodo() {
    	ascii();
	    BufferedReader inBufReader= new BufferedReader(new InputStreamReader(lectura));
	    String response="";
	    while (true) {
			try {
				response = inBufReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		if(response.equals("ok")) {
	    			break;
	    		}
	    		System.out.println( response );
	    }
		try {
			inBufReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    
    synchronized public static void Sincronizador(int n){//method synchronized
		   int temp = 1;
		   for(int i=1;i<=5;i++){
				try {
					if(lectura.available()>0) {
						 break;
					 }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		     temp = n*temp;
		     try{  
		      Thread.sleep(500);  
		     }catch(Exception e){System.out.println(e);}  
		   }  
	}
    
    /*public void purificarPantalla() {
    	ascii();
	    BufferedReader inBufReader= new BufferedReader(new InputStreamReader(lectura));
	    String response="";
	    while (true) {
			try {
				response = inBufReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		if(response.equals("ok")) {
	    			try {
						response = inBufReader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			break;
	    		}
	    		System.out.println( response );
	    }
		try {
			inBufReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    
    void cerrarProceso() {
    	try {
			lectura.close();
			teclado.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
