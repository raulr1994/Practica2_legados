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
    	//Sincronizador.waitSyncro(1);
    }
    
    public void viewTask() {
    	comunicacionS.escribirCadena("2");
    	comunicacionS.enter();
    	//Sincronizador.waitSyncro(1);
    }
    
    public void introducirDato(String cadena) {
    	comunicacionS.escribirCadena(cadena);
    	comunicacionS.enter();
    	//Sincronizador.waitSyncro(1);
    }
    
    public void generalTask() {
    	comunicacionS.escribirCadena("1");
    	comunicacionS.enter();
    	//Sincronizador.waitSyncro(1);
    }        
    
    public void specificTask() {
    	comunicacionS.escribirCadena("2");
    	comunicacionS.enter();
    	//Sincronizador.waitSyncro(1);
    }
    
    public void mainMenu() {
    	comunicacionS.escribirCadena("3");
    	comunicacionS.enter();
    	//Sincronizador.waitSyncro(1);
    }
    
    public void exit() {
    	comunicacionS.escribirCadena("3");
    	comunicacionS.enter();
    }
    
    public void crearTareaEspecifica(String dd, String mm, String name, String description) {
    	//Sincronizador.waitSyncro(2);
    	System.out.println("Creando tarea ");
    	assignTasks();
    	specificTask();
    	introducirDato(dd+mm); //Introducir fecha
    	introducirDato(name); //Introducir nombre
    	introducirDato(description); //Introducir descripción
    	comunicacionS.enter();
    }
    
    public void crearTareaGeneral() {
    	
    }
    
	public void mostrarTarea(String array[]) {
		for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
	}
	
	public int buscarIndicePrimeraTarea(String array[]) {
		int indice=0;
		if(array.length>0) {
			while(!array[indice].contains("TASK 0") && indice<array.length) {
            indice++;
			}
		}
		return indice;
	}
    
    /*public void cargarListadoTareasEspecificas() { //Version 1 estable
    	System.out.println("cargandoListadoTareasEspecificas");
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
        String numTask;
        String typeTask;
        String name;
        String description;
        String date;
        int pos;
        ListadoTareasEspecificas = new ArrayList<>(); //Borrar el contenido previo del listado
        do {
            comunicacionS.enter();
            comunicacionS.ascii();
            String cadenaAux = comunicacionS.leerPantalla().toString();
            //System.out.println("Salida de lectura de tareas");
            //System.out.println(cadenaAux);
            
            String[] array = cadenaAux.split("data: "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
            mostrarTarea(array);
            pos = buscarIndicePrimeraTarea(array);
            int n = array.length;
            n--;
            int i = 0;
            while (i <= n) {
                if (cadenaAux.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
                	System.out.println("Tarea encontrada");
                	System.out.println("Tarea nueva guardada " + array[pos+i]);
                	String[] array2 = array[pos+i].split(" ");
                    numTask = array2[1];
                    typeTask = array2[2];
                    date = array2[3];
                    name= array2[4];
                    description = array2[5];
                    System.out.println("numTask "+numTask);
                    System.out.println("typeTask "+typeTask);
                    System.out.println("name "+name);
                    System.out.println("description "+description);
                    System.out.println("date "+date);
                    
                    ListadoTareasEspecificas.add(new Tarea(numTask, name,  description, date));
                }
                i++;
            }
            Sincronizador.waitSyncro(1);
        } while (comunicacionS.hayMasTexto());
    	comunicacionS.enter();
    	if(ListadoTareasEspecificas.isEmpty()) {
    		System.out.println("No hay tareas guardadas");
    	}
    	else {
    		int numTareas = ListadoTareasEspecificas.size();
    		System.out.println("El número de tareas es " + numTareas);
    	}
    	Sincronizador.waitSyncro(1);
    }*/
	
	public void cargarListadoTareasEspecificas() { //Version 2
    	System.out.println("cargandoListadoTareasEspecificas");
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
        String numTask;
        String typeTask;
        String name;
        String description;
        String date;
        int pos;
        ListadoTareasEspecificas = new ArrayList<>(); //Borrar el contenido previo del listado
        do {
            comunicacionS.enter();
            comunicacionS.ascii();
            String cadenaAux = comunicacionS.leerPantallaS();
            //System.out.println("Salida de lectura de tareas");
            //System.out.println(cadenaAux);
            
            /*String[] array = cadenaAux.split("data: "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
            mostrarTarea(array);
            pos = buscarIndicePrimeraTarea(array);
            int n = array.length;
            n--;
            int i = 0;
            while (i <= n) {
                if (cadenaAux.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
                	System.out.println("Tarea encontrada");
                	System.out.println("Tarea nueva guardada " + array[pos+i]);
                	String[] array2 = array[pos+i].split(" ");
                    numTask = array2[1];
                    typeTask = array2[2];
                    date = array2[3];
                    name= array2[4];
                    description = array2[5];
                    System.out.println("numTask "+numTask);
                    System.out.println("typeTask "+typeTask);
                    System.out.println("name "+name);
                    System.out.println("description "+description);
                    System.out.println("date "+date);
                    
                    ListadoTareasEspecificas.add(new Tarea(numTask, name,  description, date));
                }
                i++;
            }*/
            Sincronizador.waitSyncro(1);
        } while (comunicacionS.hayMasTexto());
    	comunicacionS.enter();
    	if(ListadoTareasEspecificas.isEmpty()) {
    		System.out.println("No hay tareas guardadas");
    	}
    	else {
    		int numTareas = ListadoTareasEspecificas.size();
    		System.out.println("El número de tareas es " + numTareas);
    	}
    	//Sincronizador.waitSyncro(100);
    }
    
    /*public void cargarListadoTareasEspecificas() {
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
    	//comunicacionS.verTodo();
    	//ListadoTareasEspecificas = new ArrayList<>(); //Borrar el contenido previo del listado
    	//String pantalla=null;
        do {
            comunicacionS.enter();
            comunicacionS.ascii();
            System.out.println(comunicacionS.leerPantalla().toString());
        } while (comunicacionS.hayMasTexto());
        //System.out.println(pantalla);
    }*/
    
    public ArrayList<Tarea> obtenerListadoTareasEspecificas() {
    	return ListadoTareasEspecificas;
    }
    public ArrayList<Tarea> obtenerListadoTareasGenerales() {
    	return ListadoTareasGenerales;
    }
}
