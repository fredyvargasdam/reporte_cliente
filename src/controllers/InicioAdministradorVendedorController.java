package controllers;

import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lorena Cáceres Manuel
 */
public class InicioAdministradorVendedorController {

    private static final Logger LOG = Logger.getLogger(InicioAdministradorVendedorController.class.getName());

    private Stage stage = new Stage();
    @FXML
    private Pane pnInicioAdminVend;
    @FXML
    private TableView<?> tbVendedores;
    @FXML
    private Label lblVendedor;
    @FXML
    private VBox Vbox;
    @FXML
    private Button btnAltaVendedor;
    @FXML
    private Button btnBorrarVendedor;
    @FXML
    private Button btnActualizarVendedor;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuPerfil;
    @FXML
    private Menu menuProveedor;
    @FXML
    private MenuItem menuAdministrador;
    @FXML
    private MenuItem menuProveedores;
    @FXML
    private MenuItem menuSalir;

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
        LOG.log(Level.INFO, "Ventana Inicio de Administrador (Vendedor)");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Administrador");
        stage.setResizable(false);
        opcionesMenu();
        
        //Pane pane = new Pane();
        //MenuBar
        /*
         menuBar = new MenuBar();
        //Menus 
         menuPerfil = new Menu("Perfil");
         menuProveedor = new Menu("Proveedor");
        //MenuItem
         menuProveedores = new MenuItem("Lista de proveedores");
         menuSalir = new MenuItem("Salir");
         menuAdministrador = new MenuItem("Administrador");
        //Añadimos las acciones
        menuSalir.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Salir");
                stage.close();
            }
        });
        menuProveedores.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Lista de proveedores");
            }
        });
        menuAdministrador.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Administrador");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Administrador");
                alert.setHeaderText(null);
                alert.setContentText("Informacion del administrador");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        });
        //Añadimos los menus dentro del menuBar 
        menuBar.getMenus().addAll(menuPerfil, menuProveedor);
        //Añadimos el menuItem dentro del menu 
        menuPerfil.getItems().addAll(menuAdministrador, menuSalir);
        menuProveedor.getItems().add(menuProveedores);
        //pane.setTop(menuBar);
        */
        imagenBotones();
        stage.setOnCloseRequest(this::handleWindowClose);
        stage.setOnShowing(this::handleWindowShowing);
        btnAltaVendedor.setOnAction(this::btnAltaVendedorClick);
        stage.show();

    }

    /**
     * Al cerrar la ventana, saldrá un mensaje de confirmacion
     *
     * @param event, WindowEvent
     */
    private void handleWindowClose(WindowEvent event) {
        LOG.log(Level.INFO, "Beginning InicioAdministradorVendedorController::handleWindowClose");
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
        LOG.log(Level.INFO, "Beginning InicioAdministradorVendedorController::handleWindowShowing");
        btnActualizarVendedor.setDisable(true);
        btnBorrarVendedor.setDisable(true);

    }

    private void btnAltaVendedorClick(ActionEvent event) {
        LOG.log(Level.INFO, "Ventana Alta Vendedor (SignUp_Vendedor)");
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignUp.fxml"));

            Parent root = (Parent) loader.load();

            SignUpController controller = ((SignUpController) loader.getController());
            controller.initStage(root);
            stage.hide();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Se ha producido un error de E/S");
        }*/
    }

    private void imagenBotones() {
        //Creamos un objeto y en él guardaremos la ruta donde se encuentra las imagenes para los botones
        URL linkAlta = getClass().getResource("/img/usuario.png");
        URL linkBorrar = getClass().getResource("/img/eliminar.png");
        URL linkActualizar = getClass().getResource("/img/refrescar.png");

        //Instanciamos una imagen pasándole la ruta de las imagenes y las medidas del boton 
        Image imageAlta = new Image(linkAlta.toString(), 32, 32, false, true);
        Image imageBorrar = new Image(linkBorrar.toString(), 32, 32, false, true);
        Image imageActualizar = new Image(linkActualizar.toString(), 32, 32, false, true);

        //Añadimos la imagen a los botones que deban llevar icono
        btnAltaVendedor.setGraphic(new ImageView(imageAlta));
        btnBorrarVendedor.setGraphic(new ImageView(imageBorrar));
        btnActualizarVendedor.setGraphic(new ImageView(imageActualizar));

    }

    private void opcionesMenu() {
        /* //Barra de menú
        MenuBar menuBar = new MenuBar();
        //Opción del menú -- Perfil
        Menu menuPerfil = new Menu();
        //Añadimos el menuItem Administrador a la opción de menú de Perfil
        MenuItem menuAdministrador = new MenuItem("Administrador");
        menuPerfil.getItems().add(menuAdministrador);
        //Opción del menú -- Salir
        Menu menuSalir = new Menu();
        
        //Añadimos a la barra de menú las opciones creadas
        menuBar.getMenus().addAll(menuPerfil, menuSalir);

        return menuBar;*/

       /* //BorderPane root = new BorderPane();
        //MenuBar
        MenuBar menuBar = new MenuBar();
        //Menus 
        Menu menuPerfil = new Menu("Perfil");
        Menu menuProveedor = new Menu("Proveedor");
        //MenuItem
        MenuItem menuProveedores = new MenuItem("Lista de proveedores");
        MenuItem menuSalir = new MenuItem("Salir");
        MenuItem menuAdministrador = new MenuItem("Administrador");
        //Añadimos las acciones
        menuSalir.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Salir");
                stage.close();
            }
        });
        menuProveedores.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Lista de proveedores");
            }
        });
        menuAdministrador.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LOG.log(Level.INFO, "Se ha pulsado el MenuItem -- Administrador");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Administrador");
                alert.setHeaderText(null);
                alert.setContentText("Informacion del administrador");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        });
        //Añadimos los menus dentro del menuBar 
        menuBar.getMenus().addAll(menuPerfil, menuProveedor);
        //Añadimos el menuItem dentro del menu 
        menuPerfil.getItems().addAll(menuAdministrador, menuSalir);
        menuProveedor.getItems().add(menuProveedores);
        root.setTop(menuBar);
        Scene scene = new Scene(root);
        stage.setTitle("Administrador");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
*/
    }

}
