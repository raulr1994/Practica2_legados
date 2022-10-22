package Excepciones;

public class TaskNuberUsedException extends Exception{
    public TaskNuberUsedException() {
        super("Task number repeated");
    }
}
