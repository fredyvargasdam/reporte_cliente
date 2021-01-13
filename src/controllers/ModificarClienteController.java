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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Fredy
 */
public class ModificarClienteController  {

    @FXML
    private TextField tfCorreoElectronico;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfTelefono;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private PasswordField pfNuevaContrasenia;
    @FXML
    private PasswordField pfRepiteContrasenia;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private AnchorPane apModificarCliente;
    @FXML
    private TextField tfUsuario;
    private Usuario usuario;

    private Stage stage = new Stage();
    private static final Logger LOG = Logger.getLogger("controllers.ModificarClienteController");

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
