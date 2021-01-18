package controllers;

import exceptions.DeleteException;
import exceptions.ErrorBDException;
import exceptions.ErrorServerException;
import exceptions.ProveedorNotFoundException;
import exceptions.UpdateException;
import implementation.AdministradorManagerImplementation;
import implementation.ProveedorManagerImplementation;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.ws.rs.ClientErrorException;
import manager.AdministradorManager;
import manager.ProveedorManager;
import modelo.FechaAltaCell;
import modelo.Proveedor;
import modelo.TipoProducto;
import validar.Validar;

/**
 * FXML Controller class
 *
 * @author Lorena Cáceres Manuel
 */
public class InicioAdministradorProveedorController {

    private static final Logger LOG = Logger.getLogger(InicioAdministradorProveedorController.class.getName());

    private Stage stage;
    @FXML
    private Pane pnInicioAdminProv;
    @FXML
    private Label lblProveedor;
    @FXML
    private VBox Vbox;
    @FXML
    private HBox Hbox;
    @FXML
    private Button btnAltaProveedor;
    @FXML
    private Button btnBorrarProveedor;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuPerfil;
    @FXML
    private MenuItem menuAdministrador;
    @FXML
    private Menu menuVendedor;
    @FXML
    private MenuItem menuSalir;
    @FXML
    private MenuItem menuVendedores;
    @FXML
    private TableView<Proveedor> tbProveedor;
    @FXML
    private TableColumn<Proveedor, String> tcNombre;
    @FXML
    private TableColumn<TipoProducto, TipoProducto> tcTipo;
    @FXML
    private TableColumn<Proveedor, String> tcEmpresa;
    @FXML
    private TableColumn<Proveedor, String> tcEmail;
    @FXML
    private TableColumn<Proveedor, String> tcTelefono;
    @FXML
    private TableColumn<Proveedor, String> tcDescripcion;
    @FXML
    private TableColumn<Proveedor, Date> tcFechaAlta;

    private ProveedorManager proveedorManager;
    private AdministradorManager administradorManager;

    private ObservableList<Proveedor> listProveedores = FXCollections.observableArrayList();
    private Proveedor proveedor;
    @FXML
    private TextField tfBuscar;

    /**
     * Recibe el escenario
     *
     * @return stage
     */
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

    /**
     * Inicia el escenario
     *
     * @param root, clase parent
     */
    public void initStage(Parent root) {
        //Indicamos las columnas que debe tener la tabla y como deben ser
        iniciarColumnasTabla();
        //Configuración de la ventana
        LOG.log(Level.INFO, "Ventana Inicio de Administrador (Proveedor)");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Administrador");
        stage.setResizable(false);
        //Indicamos las imagenes de los botones
        imagenBotones();
        //Indicamos el metodo que se encargara de la apariencia del stage cuando se cierre la ventana
        stage.setOnCloseRequest(this::handleWindowClose);
        //Indicamos el metodo que se encargara de la apariencia del stage cuando se inicie la ventana
        stage.setOnShowing(this::handleWindowShowing);
        //Indicamos las acciones de cada boton 
        btnAltaProveedor.setOnAction(this::btnAltaProveedorClick);
        btnBorrarProveedor.setOnAction(this::btnBorrarProveedorClick);
        //Indicamos que el textField va a tener un metodo asociado
        tfBuscar.textProperty().addListener(this::txtChanged);
        //Mostramos el stage
        stage.show();

    }

    /**
     * Valida los textos introducidos
     *
     * @param observable Observa los cambios
     * @param oldValue Valor antiguo
     * @param newValue Valor nuevo
     */
    private void txtChanged(ObservableValue observable, String oldValue, String newValue) {

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
        //Indicamos que el boton borrarProveedor tiene que estar desactivado
        btnBorrarProveedor.setDisable(true);
    }

    /**
     * Inicializa la tabla de proveedores
     */
    private void iniciarColumnasTabla() {
        seleccionarProveedor();
        //Hacemos que la tabla sea editable
        tbProveedor.setEditable(true);
        //Rellenamos la tabla con los proveedores
        datosTabla();
        //Definimos las celdas de la tabla, incluyendo que algunas pueden ser editables
        //Nombre del proveedor
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        tcNombre.setOnEditCommit((TableColumn.CellEditEvent<Proveedor, String> data) -> {
            /* boolean isValidNombre = Validar.isValidColumnString(tcNombre);
            if (isValidNombre) {
                LOG.log(Level.INFO, "Nuevo Nombre: {0}", data.getNewValue());
                LOG.log(Level.INFO, "Antiguo Nombre: {0}", data.getOldValue());
                //Devuelve el dato de la celda
                Proveedor p = data.getRowValue();
                //Añadimos el nuevo valor a la celda
                p.setNombre(data.getNewValue());
            } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al introducir valor");
                alerta.setContentText("Por favor, introduzca un valor permitido");
                alerta.showAndWait();
            }*/

        });

        //Tipo de producto 
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tcTipo.setCellFactory(ChoiceBoxTableCell.
                forTableColumn(TipoProducto.ROPA, TipoProducto.ZAPATILLAS, TipoProducto.AMBAS));
        tcTipo.addEventHandler(TableColumn.<Proveedor, TipoProducto>editCommitEvent(),
                event -> actualizarTipoProducto(event));

        //Empresa del proveedor
        tcEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        //Indicamos que la celda puede cambiar a un TextField
        tcEmpresa.setCellFactory(TextFieldTableCell.forTableColumn());
        //Aceptamos la edición de la celda de la columna empresa 
        tcEmpresa.setOnEditCommit((TableColumn.CellEditEvent<Proveedor, String> data) -> {
            /* boolean isValidEmpresa = Validar.isValidColumnString(tcEmpresa);
            if (isValidEmpresa) {
                LOG.log(Level.INFO, "Nuevo Empresa: {0}", data.getNewValue());
                LOG.log(Level.INFO, "Antigua Empresa: {0}", data.getOldValue());
                //Devuelve el dato de la celda
                Proveedor p = data.getRowValue();
                //Añadimos el nuevo valor a la celda
                p.setEmpresa(data.getNewValue());
            } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al introducir valor");
                alerta.setContentText("Por favor, introduzca un valor permitido");
                alerta.showAndWait();
            }*/

        });

        //Email del proveedor
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //Indicamos que la celda puede cambiar a un TextField
        tcEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        //Aceptamos la edición de la celda de la columna email 
        tcEmail.setOnEditCommit((TableColumn.CellEditEvent<Proveedor, String> data) -> {
            /* boolean isValidEmail = Validar.isValidColumnEmail(tcEmail);
            if (isValidEmail) {
                LOG.log(Level.INFO, "Nuevo Email: {0}", data.getNewValue());
                LOG.log(Level.INFO, "Antiguo Email: {0}", data.getOldValue());
                //Devuelve el dato de la celda
                Proveedor p = data.getRowValue();
                //Añadimos el nuevo valor a la celda
                p.setEmail(data.getNewValue());
            } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al introducir valor");
                alerta.setContentText("Por favor, introduzca un valor permitido");
                alerta.showAndWait();
            }
             */
        });

        //Teléfono del proveedor
        tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        //Indicamos que la celda puede cambiar a un TextField
        tcTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
        //Aceptamos la edición de la celda de la columna teléfono 
        tcTelefono.setOnEditCommit((TableColumn.CellEditEvent<Proveedor, String> data) -> {
            try {
                proveedorManager = (ProveedorManagerImplementation) new factory.ProveedorFactory().getProveedorManagerImplementation();
                proveedorManager.edit(data.getRowValue());
                datosTabla();
                /*  boolean isValidTelefono = Validar.isValidColumnTelefono(tcTelefono);
                if (isValidTelefono) {
                LOG.log(Level.INFO, "Nuevo Telefono: {0}", data.getNewValue());
                LOG.log(Level.INFO, "Antiguo Telefono: {0}", data.getOldValue());
                //Devuelve el dato de la celda
                Proveedor p = data.getRowValue();
                //Añadimos el nuevo valor a la celda
                p.setTelefono(data.getNewValue());
                
                } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al introducir valor");
                alerta.setContentText("Por favor, introduzca un valor permitido");
                alerta.showAndWait();
                }
            **/  } catch (ClientErrorException ex) {
                Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UpdateException ex) {
                Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErrorBDException ex) {
                Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErrorServerException ex) {
                Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProveedorNotFoundException ex) {
                Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Descripción del proveedor
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        //Indicamos que la celda puede cambiar a un TextField
        tcDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        //Aceptamos la edición de la celda de la columna descripción 
        tcDescripcion.setOnEditCommit((TableColumn.CellEditEvent<Proveedor, String> data) -> {
            /*   boolean isValidDescripcion = Validar.isValidColumnString(tcDescripcion);
            if (isValidDescripcion) {
                LOG.log(Level.INFO, "Nueva Descripción: {0}", data.getNewValue());
                LOG.log(Level.INFO, "Antiguo Descripción: {0}", data.getOldValue());
                //Devuelve el dato de la celda
                Proveedor p = data.getRowValue();
                //Añadimos el nuevo valor a la celda
                p.setDescripcion(data.getNewValue());
            } else {
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al introducir valor");
                alerta.setContentText("Por favor, introduzca un valor permitido");
                alerta.showAndWait();
            }
             */
        });
        //Fecha de alta del proveedor
        tcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        //Indicamos que la celda puede cambiar a una TableCell
        tcFechaAlta.setCellFactory(new Callback<TableColumn<Proveedor, Date>, TableCell<Proveedor, Date>>() {
            //Este método indica que la TableCell incluye un DatePicker
            @Override
            public TableCell<Proveedor, Date> call(TableColumn<Proveedor, Date> arg0) {
                return new FechaAltaCell();
            }
        });
        //Aceptamos la edición de la celda de la columna fechaAlta 
        tcFechaAlta.setOnEditCommit(value -> {
            LOG.log(Level.INFO, "Nueva Fecha : {0}", value.getNewValue());
            LOG.log(Level.INFO, "Anterior Fecha : {0}", value.getOldValue());
            Proveedor p = value.getRowValue();

        });
    }

    /**
     * Nos permite actualizar/cambiar el tipo de producto dentro de la tabla
     * editable
     *
     * @param event
     */
    private void actualizarTipoProducto(TableColumn.CellEditEvent<Proveedor, TipoProducto> event) {

    }

    /**
     * Nos permite seleccionar a un proveedor de la tabla y este controla que el
     * botón BorrarProveedor esté habilitado o deshabilitado
     */
    private void seleccionarProveedor() {
        tbProveedor.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (proveedor != null) {
                        btnBorrarProveedor.setDisable(true);
                    } else {
                        btnBorrarProveedor.setDisable(false);
                    }
                });
    }

    //CONFIGURACIÓN DE LOS DATOS 
    /**
     * Muestra los datos de la base de datos en la TableView
     */
    private void datosTabla() {
        try {
            administradorManager = (AdministradorManagerImplementation) new factory.AdministradorFactory().getAdministradorManagerImplementation();
            listProveedores = FXCollections.observableArrayList(administradorManager.getProveedores());
            tbProveedor.setItems(listProveedores);

            for (Proveedor p : listProveedores) {
                LOG.log(Level.INFO, "Lista de Proveedores: {0}", listProveedores);
            }
            tbProveedor.setItems(listProveedores);
        } catch (ClientErrorException ex) {
            LOG.log(Level.INFO, "ClientErrorException");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Administrador");
            alert.setHeaderText("Imposible conectar. Inténtelo más tarde");
            alert.showAndWait();
        } catch (ErrorBDException ex) {
            LOG.log(Level.INFO, "ErrorBDException");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Administrador");
            alert.setHeaderText("Imposible conectar. Inténtelo más tarde");
            alert.showAndWait();
        } catch (ErrorServerException ex) {
            LOG.log(Level.INFO, "ErrorServerException");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Administrador");
            alert.setHeaderText("Imposible conectar. Inténtelo más tarde");
            alert.showAndWait();
        }
    }

    private void buscarProveedor(ActionEvent event) {

    }

    //CONFIGURACIÓN DE BOTONES
    /**
     *
     *
     * @param event ActionEvent
     */
    private void btnAltaProveedorClick(ActionEvent event) {
        //Posicion actual
        TablePosition pos = tbProveedor.getFocusModel().getFocusedCell();
        //
        tbProveedor.getSelectionModel().clearSelection();

        Proveedor nuevoProveedor = new Proveedor();
        tbProveedor.getItems().add(nuevoProveedor);

        int row = tbProveedor.getItems().size() - 1;
        tbProveedor.getSelectionModel().select(row, pos.getTableColumn());
        tbProveedor.scrollTo(nuevoProveedor);
    }

    /**
     * Borra el proveedor seleccionado de la tabla de proveedores
     *
     * @param event
     */
    private void btnBorrarProveedorClick(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Borrado de Proveedor");
        alert.setContentText("¿Estas seguro de borrar este proveedor?");
        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get() == ButtonType.OK) {
            LOG.log(Level.INFO, "Has pulsado el boton Aceptar");
            //Capturamos el indice del proveedor seleccionado y borro su item asociado de la tabla
            int proveedorIndex = tbProveedor.getSelectionModel().getSelectedIndex();
            if (proveedorIndex >= 0) {
                try {
                    proveedorManager = (ProveedorManagerImplementation) new factory.ProveedorFactory().getProveedorManagerImplementation();
                    proveedorManager.remove(tbProveedor.getSelectionModel().getSelectedItem().getIdProveedor().toString());
                    datosTabla();
                    LOG.log(Level.INFO, "Se ha borrado un proveedor");
                } catch (ClientErrorException ex) {
                    Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ProveedorNotFoundException ex) {
                    Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DeleteException ex) {
                    Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ErrorBDException ex) {
                    Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ErrorServerException ex) {
                    Logger.getLogger(InicioAdministradorProveedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //En el caso de no seleccionar un proveedor. Saldrá un alerta
                Alert alerta = new Alert(AlertType.WARNING);
                alerta.setTitle("Atención");
                alerta.setHeaderText("Proveedor no seleccionado");
                alerta.setContentText("Por favor, selecciona un proveedor de la tabla");
                alerta.showAndWait();
            }
        } else {
            LOG.log(Level.INFO, "Has pulsado el boton Cancelar");
            event.consume();
        }
    }

    //CONFIGURACIÓN DEL MENÚ
    /**
     * MenuItem que muestra un alerta informandonos de la última conexión del
     * usuario
     *
     * @param event
     */
    @FXML
    private void configMenuAdministrador(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información del Administrador");
        alert.setHeaderText("Usuario: Administrador");

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MMM-aaaa");
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
        LOG.log(Level.INFO, "Beginning InicioAdministradorProveedorController::handleWindowClose");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Administrador");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get() == ButtonType.OK) {
            LOG.log(Level.INFO, "Has pulsado el boton Aceptar");
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
        } else {
            LOG.log(Level.INFO, "Has pulsado el boton Cancelar");
            event.consume();
        }

    }

    /**
     * MenuItem que nos redirige hacia la ventana de InicioAdministrador del
     * lado vendedor y cierra la ventana actual
     *
     * @param event
     */
    @FXML
    private void configMenuVendedores(ActionEvent event) {
        LOG.log(Level.INFO, "Ventana Inicio de Administrador (Vendedor)");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InicioAdministrador_vendedor.fxml"));

            Parent root = (Parent) loader.load();

            /*InicioAdministradorVendedorController controller = ((InicioAdministradorVendedorController) loader.getController());
            controller.initStage(root);*/
            stage.hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Se ha producido un error de E/S");
        }
    }

    //CONFIGURACIÓN DE IMAGENES 
    /**
     * Añade las imagenes de los botones
     */
    private void imagenBotones() {
        //Creamos un objeto y en él guardaremos la ruta donde se encuentra las imagenes para los botones
        URL linkAlta = getClass().getResource("/img/usuario.png");
        URL linkBorrar = getClass().getResource("/img/eliminar.png");

        //Instanciamos una imagen pasándole la ruta de las imagenes y las medidas del boton 
        Image imageAlta = new Image(linkAlta.toString(), 32, 32, false, true);
        Image imageBorrar = new Image(linkBorrar.toString(), 32, 32, false, true);

        //Añadimos la imagen a los botones que deban llevar icono
        btnAltaProveedor.setGraphic(new ImageView(imageAlta));
        btnBorrarProveedor.setGraphic(new ImageView(imageBorrar));

    }

    public void setAdministradorRESTClient(AdministradorManager administrador) {
        this.administradorManager = administrador;
    }

    public AdministradorManager getAdministradorRESTClient() {
        return this.administradorManager;
    }

}
