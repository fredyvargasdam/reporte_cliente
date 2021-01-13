/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author essad
 */
public class AltaProductoController  {

    @FXML
    private ChoiceBox<?> cbTalla;
    @FXML
    private TextArea tfdescripcion;
    @FXML
    private TextField tfprecio;
    @FXML
    private TextField tfstock;
    @FXML
    private TextField tfmodelo;
    @FXML
    private Button btnImg;
    @FXML
    private ChoiceBox<?> cbTipo;
    @FXML
    private ChoiceBox<?> cbProveedor;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnvolver;
    private Usuario usuario;
    private Stage stage = new Stage();
    private static final Logger LOG = Logger.getLogger("controllers.AltaProductoController");

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

    }

}
