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
	
	public ArrayList<Tarea> cargarListadoTareasEspecificas() { //Version 2
    	viewTask(); //Acceder al visualizador de tareas
    	specificTask(); //Visualizar el listado
        String numTask;
        String typeTask;
        String name;
        String description;
        String date;
        ListadoTareasEspecificas = new ArrayList<Tarea>(); //Borrar el contenido previo del listado
        if(TotalTareasEspecificas() > 0) { //Si la conexión al mainframe es lenta se puede perder la lectura, así que hay que hacer varios intentos controlando la conexión
        	System.out.println("cargandoListadoTareasEspecificas");
        	do {
	            comunicacionS.enter();
	            comunicacionS.ascii();
	            final List<String> lines = comunicacionS.leerPantallaS();
	            //System.out.println("El listado de tareas especificas es");
	            //System.out.println(lines);
	            if(lines != null) {
		            if((TotalTareasEspecificas() > 0) && (lines.size() > 0)) {
		            	int n = lines.size();
		            	int i = 0;
		            	while(i < n) {
			            		//System.out.println(lines);
			            		String linei = lines.get(i);
				            	if (linei.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
				            		//String[] array2 = array[pos+i].split(" ");
				            		String[] array2 = linei.split(" "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
				            		numTask = array2[2];
				                    typeTask = array2[3];
				            		date = array2[4];
			                        name= array2[5];
			                        description = array2[6];
			                        /*System.out.println("numTask "+numTask);
			                        System.out.println("typeTask "+typeTask);
			                        System.out.println("name "+name);
			                        System.out.println("description "+description);
			                        System.out.println("date "+date);*/
			                        ListadoTareasEspecificas.add(new Tarea(numTask, name,  description, date));
				            	}
				            	i++;
			            	}
		            }
	            }
	        } while (comunicacionS.hayMasTexto());
        }
    	comunicacionS.enter();
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
        if(TotalTareasGenerales() > 0) { //Si la conexión al mainframe es lenta se puede perder la lectura, así que hay que hacer varios intentos controlando la conexión
        	System.out.println("CargandoListadoTareasGenerales");
        	do {
	            comunicacionS.enter();
	            comunicacionS.ascii();
	            final List<String> lines = comunicacionS.leerPantallaS();
	            //System.out.println("El listado de tareas generales es");
	            //System.out.println(lines);
	            
	            if(lines != null) {
		            if((TotalTareasGenerales() > 0) && (lines.size() > 0)) {
		            	
		            	int n = lines.size();
		            	int i = 0;
		            	while(i < n) {
			            		System.out.println(lines);
			            		String linei = lines.get(i);
				            	if (linei.contains("TASK "+ i)) { //Guardar tarea numero i-esima si contiene la cadena TASK
				            		//String[] array2 = array[pos+i].split(" ");
				            		String[] array2 = linei.split(" "); //data: TASK Number: SPECIFIC Date Nombre Descriptioni
				            		numTask = array2[2];
				                    typeTask = array2[3];
				            		date = array2[4];
			                        description = array2[6];
			                        System.out.println("numTask "+numTask);
			                        System.out.println("typeTask "+typeTask);
			                        System.out.println("description "+description);
			                        System.out.println("date "+date);
			                        ListadoTareasGenerales.add(new Tarea(numTask, null,  description, date));
				            	}
				            	i++;
			            	}
		            }
	            }
	        } while (comunicacionS.hayMasTexto());
        }
    	comunicacionS.enter();
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
