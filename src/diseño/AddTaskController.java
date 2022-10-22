package diseño;

import comunicacion.AppLegada;
import comunicacion.wc3270;
import comunicacion.ComunicacionMusicSP;

import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddTaskController {
    wc3270 comunicacionWS = wc3270.getInstancia();
    ComunicacionMusicSP comunicacionSP = ComunicacionMusicSP.getInstancia(comunicacionWS);
    AppLegada appLegada = AppLegada.getInstancia(comunicacionWS);

    @FXML
    private Button botonEnter;

    @FXML
    TextField campoNumero;

    @FXML
    TextField campoNombre;

    @FXML
    TextField campoDescripcion;

    @FXML
    DatePicker campoFecha;

    @FXML
    private void botonEnter() {
        IntroducirTarea();
        Stage ventana = MainApp.primaryStage;
        Scene escena = ventana.getScene();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("Menu.fxml"));

        try {
            escena.setRoot(loader.load());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void IntroducirTarea() {
        System.out.println("Exito introducir");
        final String numero = campoNumero.getText();
        final String nombre = campoNombre.getText();
        final String descripcion = campoDescripcion.getText();
        final LocalDate fecha = campoFecha.getValue();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");
        String fechaAux = df.format(fecha);
        String [] ddmmyy = fechaAux.split(" ");

        try {
            appLegada.añadirTarea(numero, nombre, descripcion, ddmmyy [0], ddmmyy[1], ddmmyy[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
