/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import client.UsuarioRESTClient;
import factory.UsuarioFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import manager.UsuarioManager;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author 2dam
 */
public class RecuperarContraseniaController {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnIniciar;
    @FXML
    private Label lblCorreoElectronico;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnVerificar;
    @FXML
    private PasswordField pfNuevaContrasenia;
    @FXML
    private PasswordField pfRepetirContrasenia;

    private Usuario usuario;
    private Stage stage = new Stage();
    private static final Logger LOG = Logger.getLogger("controllers.RecuperarContraseniaController");

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

        LOG.log(Level.INFO, "Ventana Recuperar Contraseña");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("¡Recuperar Contraseña!");
        stage.setResizable(false);
        stage.setOnCloseRequest(this::handleWindowClose);
        stage.setOnShowing(this::handleWindowShowing);
        btnAceptar.setOnAction(this::btnAceptarClick);
        btnAceptar.setTooltip(new Tooltip("Pulse para aceptar "));
        btnCancelar.setOnAction(this::btnCancelarClick);
        btnCancelar.setTooltip(new Tooltip("Pulse para cancelar "));
        btnVerificar.setOnAction(this::btnVerificarClick);
        btnVerificar.setTooltip(new Tooltip("Pulse para verificar "));

      //  txtUsuario.textProperty().addListener(this::txtChanged);
      //  txtContrasena.textProperty().addListener(this::txtChanged);
      //  hlRegistrarse.setOnAction(this::hlRegistrarseClick);
      //  hlContraseniaOlvidada.setOnAction(this::hlContraseniaOlvidadClick);
        stage.show();
    }

    /**
     * Al cerrar la ventana, saldrá un mensaje de confirmacion
     *
     * @param event, WindowEvent
     */
    private void handleWindowClose(WindowEvent event) {
        /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("LogIn");
        alert.setContentText("¿Estas seguro que quieres salir de la aplicación?");
        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get() == ButtonType.OK) {
            LOG.log(Level.INFO, "Has pulsado el boton Aceptar");
            stage.hide();
        } else {
            LOG.log(Level.INFO, "Has pulsado el boton Cancelar");
            event.consume();
        }
    }*/
    }

    /**
     * Configura los eventos al iniciar la ventana)
     *
     * @param event WindowEvent
     */
    private void handleWindowShowing(WindowEvent event) {
        /*
        btnIniciar.setDisable(true);
        LOG.log(Level.INFO, "Beginning LoginController::handleWindowShowing");
         */

    }

    private void btnIniciarClick(ActionEvent event) {
    }

    private void btnAceptarClick(ActionEvent event) {
        UsuarioManager usuarioM = new UsuarioFactory().getUsuarioRESTClient();
        Alert alert;
        try {
        lblCorreoElectronico.setText(usuario.getEmail());
        UsuarioRESTClient usuarioR = (UsuarioRESTClient) usuarioM;
            usuario = usuarioR.usuarioLogin(Usuario.class, usuario.getLogin());
            System.out.println(usuario.getLastAccess());
           
        }catch(Exception e){
            
        }
    }

    private void btnCancelarClick(ActionEvent event) {
    }
    private void btnVerificarClick(ActionEvent event) {
    }


}
