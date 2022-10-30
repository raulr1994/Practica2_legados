package comunicacion;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class Sincronizador {
		synchronized public static void waitSyncro(int n){//method synchronized
		   int temp = 1;
		   for(int i=1;i<=5;i++){
		     temp = n*temp;
		     try{  
		      Thread.sleep(500);  
		     }catch(Exception e){System.out.println(e);}  
		   }  
		 }    
}
