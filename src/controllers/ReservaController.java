/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factory.ReservaFactory;
import modelo.Cliente;
import modelo.EstadoReserva;
import modelo.Producto;
import modelo.Reserva;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import manager.ReservaManager;


/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class ReservaController implements Initializable {
    
    private static final Logger LOG = Logger.getLogger("controllers.ReservaController");
    
    @FXML
    private Pane pnReserva;
    @FXML
    private Label lblReservas;
    @FXML
    private TableView<Reserva> tbReservas;
    @FXML
    private TableColumn<Reserva, Cliente> tcIdCliente;
    @FXML
    private TableColumn<Reserva, Producto> tcIdProducto;
    @FXML
    private TableColumn<Reserva,Integer> tcCantidad;
    @FXML
    private TableColumn<Reserva, String> tcDescripcion;
    @FXML
    private TableColumn<Reserva, EstadoReserva> tcEstado;
    @FXML
    private TableColumn<Reserva, Date> tcFecha;
    @FXML
    private TableColumn<Reserva, Timestamp> tcReserva;
    @FXML
    private TableColumn<Reserva, Timestamp> tcEntrega;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField tfBuscar;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuCliente;
    @FXML
    private MenuItem menuPerfil;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private Menu menuAyuda;
    @FXML
    private MenuItem menuAbout;

    private Stage stage = new Stage();
    private List<Reserva> reservas;
    private Reserva reserva;
    private ReservaManager reservaManager;
    
    public ReservaController() {

    }

    
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Establece el escenario
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setReservaRESTClient(ReservaManager reserva){
        this.reservaManager=reserva;
    }
    
    public ReservaManager getReservaRESTClient(){
        return this.reservaManager;
    }
    
    /**
     * Inicia el escenario
     *
     * @param root, clase parent
     */
    public void initStage(Parent root) {
        LOG.log(Level.INFO, "Ventana Reserva");
        Scene scene = new Scene(root);
        iniciarColumnasTabla();
        
        stage.setScene(scene);
        stage.setTitle("Reserva");
        stage.setResizable(false);
        stage.setOnCloseRequest(this::handleWindowClose);
        stage.setOnShowing(this::handleWindowShowing);
        tbReservas.setEditable(true);
        
        

        stage.show();
    }
    

    /**
     * Al cerrar la ventana, saldrá un mensaje de confirmacion
     *
     * @param event, WindowEvent
     */
    private void handleWindowClose(WindowEvent event) {
        LOG.log(Level.INFO, "Beginning InicioAdministradorProveedorController::handleWindowClose");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Administrador");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get() == ButtonType.OK) {
            LOG.log(Level.INFO, "Has pulsado el boton Aceptar");
            stage.hide();
        } else {
            LOG.log(Level.INFO, "Has pulsado el boton Cancelar");
            event.consume();
        }
    }
    
    /**
     * Configura los eventos al iniciar la ventana
     *
     * @param event WindowEvent
     */
    private void handleWindowShowing(WindowEvent event) {
        LOG.log(Level.INFO, "Beginning InicioAdministradorProveedorController::handleWindowShowing");
        btnInsertar.setDisable(true);
        btnBorrar.setDisable(true);
        btnBuscar.setDisable(true);
    }
    
    
    /**
     * Inicializa la tabla de proveedores
     */
    private void iniciarColumnasTabla() {
        seleccionarReserva();
        //convertimos la tabla editable.
        tbReservas.setEditable(true);

        //Id del cliente
        tcIdCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        //id del producto
        tcIdProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        //cantidad de la reserva
        tcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        //Descripcion de la reserva, esta es editable.
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));    
        tcDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        tcDescripcion.setOnEditCommit((TableColumn.CellEditEvent<Reserva, String> des) -> {
            LOG.log(Level.INFO, "Nuevo Email: {0}", des.getNewValue());
            LOG.log(Level.INFO, "Antiguo Email: {0}", des.getOldValue());
            //Devuelve el dato de la celda
            Reserva res = des.getRowValue();
            //Añadimos el nuevo valor a la celda
            res.setDescripcion(des.getNewValue());
        });
        //Estado de la reserva, esta es editable.
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            tcEstado.setCellFactory(ChoiceBoxTableCell.
                    forTableColumn(EstadoReserva.CANCELADA, EstadoReserva.CONFIRMADA, EstadoReserva.EXPIRADA, EstadoReserva.REALIZADA));
            tcEstado.addEventHandler(TableColumn.<Reserva, EstadoReserva>editCommitEvent(),
                    event -> actualizarReservaEstado(event));
        //Fecha de reserva
        tcReserva.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));    
        //Fecha de entrega de la reserva.
        tcEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));  
        
    }
    
    /*
    *Usamos este metodo para actualizar el Estado de la reserva.
    */
    private void actualizarReservaEstado(TableColumn.CellEditEvent<Reserva, EstadoReserva> event) {
        System.out.println("Estoy aca la reserva es " + event);
        System.out.println((EstadoReserva) event.getNewValue());
        System.out.println((EstadoReserva) event.getOldValue());
        reserva = event.getRowValue();
        EstadoReserva estado = event.getNewValue();
        System.out.println("Estado: " + estado.toString() + reserva.getId() + reserva.getDescripcion() + "Esto es de reserva: " + reserva.getEstado().toString());
        reserva.setEstado(event.getNewValue());
        System.out.println(reserva.getId() + reserva.getDescripcion() + "Esto es de reserva: " + reserva.getEstado().toString()+" fecha de entrega es "+reserva.getFechaEntrega());
        
    }
    
    /**
     * Nos permite seleccionar a un proveedor de la tabla y este controla que el
     * botón BorrarProveedor y ActualizarProveedor esté habilitado o
     * deshabilitado
     */
    private void seleccionarReserva() {
        tbReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (reserva != null) {
                        btnInsertar.setDisable(true);
                        btnBorrar.setDisable(true);
                    } else {
                        btnInsertar.setDisable(false);
                        btnBorrar.setDisable(false);
                    }
                });
    }
     
    /**
     * MenuItem que muestra un alerta informandonos de la conexión actual del
     * usuario
     *
     * @param event
     */
    @FXML
    private void configMenuCliente(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información del Cliente");
        alert.setHeaderText("Usuario: Cliente");

        SimpleDateFormat sdf = new SimpleDateFormat("hh: mm dd-MMM-aaaa");
        String fechaComoCadena = sdf.format(new Date());
        alert.setContentText(fechaComoCadena);
        alert.showAndWait();
    }
    
    /**
     * MenuItem que nos redirige hacia la ventana de LogIn y cierra la ventana
     * actual
     *
     * @param event
     */
    @FXML
    private void configMenuSalir(ActionEvent event) {
        LOG.log(Level.INFO, "Ventana Login");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LogIn.fxml"));

            Parent root = (Parent) loader.load();

            LogInController controller = ((LogInController) loader.getController());
            controller.initStage(root);
            stage.hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Se ha producido un error de E/S");
        }
    }

 
    
}
