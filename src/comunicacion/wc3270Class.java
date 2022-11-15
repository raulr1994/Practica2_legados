package comunicacion;

import java.util.List;

public interface wc3270Class {
	
    StringBuilder leerPantalla();
    
    List<String> leerPantallaS();
    
    void enter();
    
    void ascii();
    
    boolean hayMasTexto();
    
    void teclaFuncion(int teclaF);
    
    void escribirCadena(String cadena);
    
    void escribirLinea(String cadena);
    
    void escribirLineaNoEsperaOK(String cadena);
    
    boolean buscarCadena(String cadena);	
	
	void conectar(String ip, String puerto) throws RuntimeException;
	
	void limpiarEntrada();
}
