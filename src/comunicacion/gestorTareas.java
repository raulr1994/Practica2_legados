package comunicacion;

import Excepciones.TaskNuberUsedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author apg29
 */
public class gestorTareas {

    private wc3270 comunicacionS;

    private static gestorTareas instancia = null;
    
    private ArrayList<Tarea> ListadoTareasEspecificas = new ArrayList<>();
    private ArrayList<Tarea> ListadoTareasGenerales = new ArrayList<>();
    
    private int nTareasEspecificasCreadas = 0;
    private int nTareasGeneralesCreadas = 0;

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
    	System.out.println("Creando tarea especifica");
    	assignTasks();
    	specificTask();
    	introducirDato(dd+mm); //Introducir fecha
    	introducirDato(name); //Introducir nombre
    	introducirDato(description); //Introducir descripción
    	comunicacionS.enter();
    	nTareasEspecificasCreadas++;
    }
    
    public void crearTareaGeneral(String dd, String mm, String description) {
    	System.out.println("Creando tarea general");
    	assignTasks();
    	generalTask();
    	introducirDato(dd+mm); //Introducir fecha
    	introducirDato(description); //Introducir descripción
    	comunicacionS.enter();
    	nTareasGeneralesCreadas++;
    }
	
	public ArrayList<Tarea> cargarListadoTareasEspecificas() { //Version 2
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
        String numTask;
        String typeTask;
        String name;
        String description;
        String date;
        ListadoTareasEspecificas = new ArrayList<Tarea>(); //Borrar el contenido previo del listado
        while(ListadoTareasEspecificas.size()< TotalTareasEspecificas()) {
        	if(TotalTareasEspecificas() > 0) { //Si la conexión al mainframe es lenta se puede perder la lectura, así que hay que hacer varios intentos controlando la conexión
	        	System.out.println("cargandoListadoTareasEspecificas");
	        	do {
		            comunicacionS.enter();
		            comunicacionS.ascii();
		            final List<String> lines = comunicacionS.leerPantallaS();
		            if(lines != null) {
			            if((TotalTareasEspecificas() > 0) && (lines.size() > 0)) {
			            	int n = lines.size();
			            	int i = 0;
			            	while(i < n) {
				            		String linei = lines.get(i);
					            	if (linei.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
					            		String[] array2 = linei.split(" "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
					            		numTask = array2[2];
					                    typeTask = array2[3];
					            		date = array2[4];
				                        name= array2[5];
				                        description = array2[6];
				                        if(typeTask.equals("SPECIFIC")) {
				                        	ListadoTareasEspecificas.add(new Tarea(numTask, name,  description, date));
				                        }
					            	}
					            	i++;
				            	}
			            }
		            }
		        } while (comunicacionS.hayMasTexto());
	        }
	    	comunicacionS.enter();
	    	Sincronizador.waitSyncro(100);
        }
    	if(!ListadoTareasEspecificas.isEmpty()) {
    		int numTareas = ListadoTareasEspecificas.size();
    		System.out.println("El número de tareas especificas es " + numTareas);
    		return ListadoTareasEspecificas;
    	}
    	else {
    		System.out.println("No hay tareas Especificas guardadas");
    		return null;
    	}
    }
	
public ArrayList<Tarea> cargarListadoTareasGenerales() { //Version 2
    	viewTask(); //Acceder al visualizador de tareas
    	generalTask(); //Visualizar el listado
        String numTask;
        String typeTask;
        String description;
        String date;
        ListadoTareasGenerales = new ArrayList<Tarea>(); //Borrar el contenido previo del listado
	    while(ListadoTareasGenerales.size()< TotalTareasGenerales()) {
        	if(TotalTareasGenerales() > 0) { //Si la conexión al mainframe es lenta se puede perder la lectura, así que hay que hacer varios intentos controlando la conexión
	        	System.out.println("CargandoListadoTareasGenerales");
	        	do {
		            comunicacionS.enter();
		            comunicacionS.ascii();
		            final List<String> lines = comunicacionS.leerPantallaS();
		            if(lines != null) {
			            if((TotalTareasGenerales() > 0) && (lines.size() > 0)) {
			            	int n = lines.size();
			            	int i = 0;
			            	while(i < n) {
				            		System.out.println(lines);
				            		String linei = lines.get(i);
					            	if (linei.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
					            		String[] array2 = linei.split(" "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
					            		numTask = array2[2];
					                    typeTask = array2[3];
					            		date = array2[4];
				                        description = array2[6];
				                        if(typeTask.equals("GENERAL")) {
				                        	ListadoTareasGenerales.add(new Tarea(numTask, null,  description, date));
				                        }
					            	}
					            	i++;
				            	}
			            }
		            }
		        } while (comunicacionS.hayMasTexto());
	        }
        	comunicacionS.enter();
        	Sincronizador.waitSyncro(100);
	    }
	    if(!ListadoTareasGenerales.isEmpty()){
	    	int numTareas = ListadoTareasGenerales.size();
	    	System.out.println("El número de tareas generales es " + numTareas);
	    	return ListadoTareasGenerales;
	    }
	    else {
	    	System.out.println("No hay tareas generales guardadas");
	    	return null;
	    }
    }
    
    public ArrayList<Tarea> obtenerListadoTareasEspecificas() {
    	return ListadoTareasEspecificas;
    }
    public ArrayList<Tarea> obtenerListadoTareasGenerales() {
    	return ListadoTareasGenerales;
    }
    public int TotalTareasEspecificas() {
    	return nTareasEspecificasCreadas;
    }
    public int TotalTareasGenerales() {
    	return nTareasGeneralesCreadas;
    }
}
