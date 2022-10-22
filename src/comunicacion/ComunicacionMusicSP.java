package comunicacion;

import java.util.concurrent.TimeUnit;

import Excepciones.UserIdInUseException;

public class ComunicacionMusicSP {

    private wc3270 comunicacion;
    private static String ip = "155.210.152.51";
    private static String puerto = "3270";
    
    

    private static ComunicacionMusicSP instancia = null;

    protected ComunicacionMusicSP(wc3270 comunicacion) {
        this.comunicacion = comunicacion;
    }

    public static ComunicacionMusicSP getInstancia(wc3270 comunicacion) {
        if (instancia == null) {
            instancia = new ComunicacionMusicSP(comunicacion);
        }
        return instancia;
    }

    public void conectar() {
        comunicacion.conectar(ip, puerto);
    }

    public boolean login(String usuario, String contraseña) throws Exception {
        //Escribe el nombre de usuario
        //Si no es válido pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
    	System.out.println("Comprobando nombre usuario");
        comunicacion.escribirCadena(usuario);
        comunicacion.enter();
        Thread.sleep(comunicacion.millis);
        System.out.println("Enter terminado");
        if (comunicacion.buscarCadena("Userid is not authorized")) {
        	System.out.println("Userid is not authorized");
            comunicacion.teclaFuncion(3);//Prueba
            comunicacion.enter();
            Thread.sleep(comunicacion.millis);
            return false;
        }
        //Escribe la contraseña
        //Si no es válida pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
        System.out.println("Comprobando contraseña");
        comunicacion.escribirCadena(contraseña);
        comunicacion.enter();
        Thread.sleep(comunicacion.millis);
        if (comunicacion.buscarCadena("Password incorrect")) {
        	System.out.println("Password incorrect");
            comunicacion.teclaFuncion(3);//Prueba
            comunicacion.enter();
            Thread.sleep(comunicacion.millis);
            return false;
        }
        System.out.println("Contraseña comprobada");
        //Si ya hay un usuario con el mismo ID connectado
        //Lanza una excepción
        if (comunicacion.buscarCadena("Userid is in use.")) {
            throw new UserIdInUseException();
        }
        System.out.println("Userid is not in use.");
        //Si la contraseña es válida devolverá TRUE
        //y ejecutará el programa
        if (comunicacion.buscarCadena("Press ENTER to continue...")) {
            comunicacion.enter();
            comenzarPrograma();
            return true;
        }
        return false;
    }

    private void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

	public void comenzarPrograma() throws Exception {
        //comunicacion.escribirCadena("tasks2.job");
        //comunicacion.enter();
		System.out.println("Programa comenzado");
    }

    public void logout() {
        comunicacion.teclaFuncion(3);
        comunicacion.escribirCadena("off");
        comunicacion.enter();
    }

    public String verPantalla() {
        comunicacion.ascii();
        return comunicacion.leerPantalla().toString();
    }
}
