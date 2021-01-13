/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import modelo.Producto;
import modelo.Producto;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Fredy
 */
public class InicioVendedorController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Menu mProductos;
    @FXML
    private MenuItem miSalir;
    @FXML
    private Menu mReservas;
    @FXML
    private MenuItem miVerReservas;
    @FXML
    private Menu mCliente;
    @FXML
    private MenuItem miAltaCliente;
    @FXML
    private ScrollPane spProductos;
    @FXML
    private GridPane gpProductos;
    @FXML
    private MenuItem miRegistrarProducto;

    private List<Producto> productos = new ArrayList<>();
    @FXML
    private AnchorPane apInicioVendedor;
    @FXML
    private BorderPane bpInicioVendedor;
    @FXML
    private MenuBar mbMenu;
    @FXML
    private Label lblVendedor;

    private Usuario usuario;

    private Stage stage = new Stage();
    private static final Logger LOG = Logger.getLogger("controllers.InicioVendedorController");

    /**
     * Recibe el escenario
     *
     * @return stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Establece el escenario.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Establece un Usuario
     *
     * @param usuario Usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Inicia el escenario
     *
     * @param root Clase Parent
     */
    public void initStage(Parent root) {
        stage.show();
    }

    private List<Producto> getProductos() throws IOException {
        List<Producto> productos = new ArrayList<>();
        Producto producto;
        String dirName = "E:\\reto2\\PruebaVentana\\src\\pruebaventana\\";
        for (int i = 0; i < 20; i++) {

            producto = new Producto();
            producto.setPrecio(179.99f);
            producto.setModelo("Nike Air Max 97");
            producto.setImagen(extractBytes("E:\\reto2\\PruebaVentana\\src\\pruebaventana\\"));
            productos.add(producto);
        }
        return productos;
    }

    public byte[] extractBytes(String path) throws IOException {
        // abrimos la imagen
        File imgPath = new File(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        BufferedImage img = ImageIO.read(new File(path, "nik.jpg"));
        ImageIO.write(img, "jpg", baos);
        baos.flush();
        //   String base64String = Base64.encode(baos.toByteArray());
        baos.close();

        return (baos.toByteArray());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int columna = 0;
            int fila = 1;
            productos.addAll(getProductos());
            for (int i = 0; i < productos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProductoItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProductoItemController productoItemController = fxmlLoader.getController();
                productoItemController.setData(productos.get(i));
                if (columna == 3) {
                    columna = 0;
                    fila++;
                }
                gpProductos.add(anchorPane, columna++, fila);

                gpProductos.setMinWidth(Region.USE_COMPUTED_SIZE);
                gpProductos.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gpProductos.setMaxWidth(Region.USE_PREF_SIZE);

                gpProductos.setMinHeight(Region.USE_COMPUTED_SIZE);
                gpProductos.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gpProductos.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(InicioVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
