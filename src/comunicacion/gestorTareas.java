package comunicacion;

import Excepciones.TaskNuberUsedException;
import java.util.ArrayList;

/**
 * @author apg29
 */
public class gestorTareas {

    private wc3270 comunicacionS;

    private static gestorTareas instancia = null;
    
    private ArrayList<Tarea> ListadoTareasEspecificas = new ArrayList<>();
    private ArrayList<Tarea> ListadoTareasGenerales = new ArrayList<>();

    protected gestorTareas(wc3270 comunicacion) {
        this.comunicacionS = comunicacion;
    }

    public static gestorTareas getInstancia(wc3270 comunicacion) {
        if (instancia == null) {
            instancia = new gestorTareas(comunicacion);
        }
        return instancia;
    }
    
    public void assignTasks() {
    	comunicacionS.escribirCadena("1");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void viewTask() {
    	comunicacionS.escribirCadena("2");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void introducirDato(String cadena) {
    	comunicacionS.escribirCadena(cadena);
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void generalTask() {
    	comunicacionS.escribirCadena("1");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }        
    
    public void specificTask() {
    	comunicacionS.escribirCadena("2");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void mainMenu() {
    	comunicacionS.escribirCadena("3");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void exit() {
    	comunicacionS.escribirCadena("3");
    	comunicacionS.enter();
    	Sincronizador.waitSyncro(1);
    }
    
    public void crearTareaEspecifica(String dd, String mm, String name, String description) {
    	Sincronizador.waitSyncro(2);
    	assignTasks();
    	specificTask();
    	introducirDato(dd+mm); //Introducir fecha
    	introducirDato(name); //Introducir nombre
    	introducirDato(description); //Introducir descripción
    	comunicacionS.enter();
    	mainMenu();
    }
    
    public void crearTareaGeneral() {
    	
    }
    
    public void cargarListadoTareasEspecificas() {
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
    	Sincronizador.waitSyncro(5);
        String numTask;
        String name;
        String description;
        String date;
        do {
            comunicacionS.enter();
            comunicacionS.ascii();
            String cadenaAux = comunicacionS.leerPantalla().toString();
            String[] array = cadenaAux.split("data:");
            int n = array.length;
            n--;
            int i = 0;
            while (i <= n) {
                if (array[i].contains("TASK NUMBER")) {
                    numTask = array[i];
                    name = array[i + 1];
                    description = array[i + 2];
                    date = array[i + 3];

                    numTask = numTask.split(": ")[1];
                    name = name.split(": ")[1];
                    description = description.split(": ")[1];
                    date = date.split(": ")[1];

                    ListadoTareasEspecificas.add(new Tarea(numTask, name, description, date));
                }
                i++;
            }
        } while (comunicacionS.hayMasTexto());
    	comunicacionS.enter();
    	if(ListadoTareasEspecificas.isEmpty()) {
    		int numTareas = ListadoTareasEspecificas.size();
    		System.out.println("El número de tareas es " + numTareas);
    	}
    	else {
    		System.out.println("No hay tareas guardadas");
    	}
    }
    public void cargarListadoTareasGenerales() {
    	viewTask();
    	generalTask();
    	mainMenu();
    }
    public ArrayList<Tarea> obtenerListadoTareasEspecificas() {
    	return ListadoTareasEspecificas;
    }
    public ArrayList<Tarea> obtenerListadoTareasGenerales() {
    	return ListadoTareasGenerales;
    }
}
