/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author Fredy
 */
public class ProductoItemController implements Initializable {

    @FXML
    private ImageView ivImagen;
    @FXML
    private Label lblProducto;
    @FXML
    private Label lblPrecio;

    private Producto producto;
    private static final String MONEDA = "€";
    @FXML
    private AnchorPane apProductoItem;

    public void setData(Producto producto) throws IOException {
        this.producto = producto;
        lblProducto.setText("Producto"); 
        lblPrecio.setText("Precio " );

        //  byte[] bytearray = Base64.decode(base64String);
        byte[] bytearray = producto.getImagen();
        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));

        Image i = SwingFXUtils.toFXImage(imag, null);
        ivImagen.setImage(i);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
