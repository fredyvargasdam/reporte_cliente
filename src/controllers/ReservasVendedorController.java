/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import modelo.EstadoReserva;
import modelo.FechaEntregaCell;
import modelo.Producto;
import modelo.Reserva;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Fredy
 */
public class ReservasVendedorController implements Initializable {

    @FXML
    private Button btnVolver;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private AnchorPane apReservasVendedor;
    @FXML
    private TableView<Reserva> tvReservas;
    @FXML
    private TableColumn<Reserva, Long> tcIdReserva;
    @FXML
    private TableColumn<Reserva, Long> tcUsuario;
    @FXML
    private TableColumn<Reserva, Long> tcProducto;
    @FXML
    private TableColumn<Reserva, Integer> tcCantidad;
    @FXML
    private TableColumn<Reserva, String> tcDescripcion;
    @FXML
    private TableColumn<Reserva, Date> tcFecha;
    @FXML
    private TableColumn<Reserva, Date> tcRealizada;
    @FXML
    private TableColumn<Reserva, Date> tcEntrega;
    //  private TableColumn<BirthdayEvent, Timestamp> tcEntrega;
    @FXML
    private TableColumn<EstadoReserva, EstadoReserva> tcEstado;
    private List<Reserva> reservas;
    
     private Usuario usuario;
    
    private Stage stage = new Stage();
    private static final Logger LOG = Logger.getLogger("controllers.ReservasVendedorController");

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

    private List<Reserva> getReservas() throws IOException {
        List<Reserva> reservas = new ArrayList<>();
        Reserva reserva;
        Producto producto;
        for (int i = 0; i < 20; i++) {
            producto = new Producto();
            producto.setId(Long.valueOf(1));
            producto.setPrecio(179.99f);
            producto.setModelo("Nike Air Max 97");
            producto.setImagen(extractBytes("E:\\reto2\\PruebaVentana\\src\\pruebaventana\\"));

            // reserva = new ReservaT(Long.valueOf(i),producto.getId(),"Buena reserva BRO","CONFIRMADA",i,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),"Pimp Flakito");
            reserva = new Reserva();
            reserva.setId(Long.valueOf(i));
            reserva.setCantidad(i);
          //  reserva.setUser("Pimp flakito");
            reserva.setDescripcion("Buena reserva BRO");
            reserva.setEstado(EstadoReserva.EXPIRADA);
            reserva.setProducto(producto);
            reserva.setFechaReserva(new Timestamp(System.currentTimeMillis()));
            reserva.setFechaEntrega(new Timestamp(System.currentTimeMillis()));

            reservas.add(reserva);
        }
        return reservas;
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
            //     reservas.addAll(getReservas());
            tvReservas.setEditable(true);
            reservas = FXCollections.observableArrayList(getReservas());
            tcIdReserva.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcUsuario.setCellValueFactory(new PropertyValueFactory<>("user"));
            tcProducto.setCellValueFactory((TableColumn.CellDataFeatures<Reserva, Long> param) -> new SimpleObjectProperty<>(param.getValue().getProducto().getId()));
            tcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tcDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
            tcDescripcion.setOnEditCommit(valor -> {
                System.out.println("Nuevo : " + valor.getNewValue());
                System.out.println("Anterior : " + valor.getOldValue());
                Reserva reserva = valor.getRowValue();
                System.out.println("id de reserva " + reserva.getId());
                reserva.setDescripcion(valor.getNewValue());
            });
            tcRealizada.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
            tcEntrega.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));
            tcEntrega.setCellFactory(new Callback<TableColumn<Reserva, Date>, TableCell<Reserva, Date>>() {
                @Override
                public TableCell<Reserva, Date> call(TableColumn<Reserva, Date> arg0) {      
                    return new FechaEntregaCell();
                }
            });
            tcEntrega.setOnEditCommit(value->{
                 System.out.println("Nuevo : " + value.getNewValue());
                System.out.println("Anterior : " + value.getOldValue());
                Reserva reserva = value.getRowValue();
                System.out.println("id de reserva " + reserva.getId());
                reserva.setFechaEntrega(value.getNewValue());
            });

            tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            tcEstado.setCellFactory(ChoiceBoxTableCell.
                    forTableColumn(EstadoReserva.CANCELADA, EstadoReserva.CONFIRMADA, EstadoReserva.EXPIRADA, EstadoReserva.REALIZADA));
            tcEstado.addEventHandler(TableColumn.<Reserva, EstadoReserva>editCommitEvent(),
                    event -> actualizarReservaEstado(event));
           // tvReservas.getSelectionModel().selectedItemProperty().addListener(this::handleReservaTableSelectionChanged);
            tvReservas.setItems((ObservableList<Reserva>) reservas);
            /*  reservas.forEach((r) -> {
            tvReservas.getItems().add(r);
            });*/
        } catch (IOException ex) {
            Logger.getLogger(ReservasVendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    private void actualizarReservaEstado(TableColumn.CellEditEvent<Reserva, EstadoReserva> event) {
        System.out.println("Estoy aca la reserva es " + event);
        System.out.println((EstadoReserva) event.getNewValue());
        System.out.println((EstadoReserva) event.getOldValue());
        Reserva reserva = event.getRowValue();
        EstadoReserva estado = event.getNewValue();
        System.out.println("Estado: " + estado.toString() + reserva.getId() + reserva.getDescripcion() + "Esto es de reserva: " + reserva.getEstado().toString());
        reserva.setEstado(event.getNewValue());
        System.out.println(reserva.getId() + reserva.getDescripcion() + "Esto es de reserva: " + reserva.getEstado().toString()+" fecha de entrega es "+reserva.getFechaEntrega());
        
    }

}
