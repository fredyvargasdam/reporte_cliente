/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author essad
 */
public class AltaProductosController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
