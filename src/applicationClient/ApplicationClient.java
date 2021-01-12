/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationClient;

import controllers.InicioAdministradorVendedorController;
import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Lorena
 */
public class ApplicationClient extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
        //LOG.log(Level.INFO, "Iniciando Ventana Administrador Vendedor");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InicioAdministrador_vendedor.fxml"));
        
        Parent root = (Parent) loader.load();
        
        InicioAdministradorVendedorController controller = ((InicioAdministradorVendedorController) loader.getController());
            controller.setStage(primaryStage);
            controller.initStage(root);
            } catch (IOException e) {
           //LOG.log(Level.SEVERE, "Se ha producido un error de E/S");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
