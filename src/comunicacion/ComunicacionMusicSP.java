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
    	usuario = "grupo_09";
    	contraseña = "secreto6";
        //Escribe el nombre de usuario
        //Si no es válido pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
    	System.out.println("Comprobando nombre usuario");
        comunicacion.escribirCadena(usuario);
        comunicacion.enter();
        Thread.sleep(comunicacion.millis);
        System.out.println("Enter terminado");
        if (comunicacion.buscarCadena("Userid is not authorized")) {
        	//System.out.println("Userid is not authorized");
            comunicacion.teclaFuncion(3);//Prueba
            comunicacion.enter();
            Thread.sleep(comunicacion.millis);
            return false;
        }
        System.out.println("Usuario comprobado");
        //Escribe la contraseña
        //Si no es válida pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
        System.out.println("Comprobando contraseña");
        comunicacion.escribirCadena(contraseña);
        comunicacion.enter();
        Thread.sleep(comunicacion.millis);
        if (comunicacion.buscarCadena("Password incorrect")) {
        	//System.out.println("Password incorrect");
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
        //System.out.println(verPantalla());
        
        if (comunicacion.buscarCadena("Press ENTER to continue...")) {
            comunicacion.enter();
            Thread.sleep(500);
            //comunicacion.verTodo();
            comenzarPrograma();
            return true;
        }
        return false;
    }

	public void comenzarPrograma() throws Exception {
		comunicacion.escribirCadena("tareas.c");
        comunicacion.enter();
        Thread.sleep(500);
        //comunicacion.verTodo();
        //System.out.println(verPantalla());
		//System.out.println("Programa comenzado");
		//finalizarPrograma();
    }

    public void finalizarPrograma() {
    	comunicacion.escribirCadena("3");
    	comunicacion.enter();
    	Sincronizador.waitSyncro(1);
        comunicacion.escribirCadena("off");
        //comunicacion.enter();
        comunicacion.teclaFuncion(3);
        try {
			Thread.sleep(comunicacion.millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(verPantalla());
        System.out.println("Programa finalizado");
        comunicacion.cerrarProceso();
    }

    public String verPantalla() {
        comunicacion.ascii();
        return comunicacion.leerPantalla().toString();
    }
}
