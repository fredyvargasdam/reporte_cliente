/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import applicationClient.ApplicationClient;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modelo.Producto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Fredy Vargas Flores
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InicioVendedorProductoTest extends ApplicationTest {

    private TableView table;
    private Button btnNuevo;
    private Button btnBorrar;
    private Node node;

    @Override
    public void start(Stage stage) throws Exception {
        //start JavaFX application to be tested    
        new ApplicationClient().start(stage);
        table = lookup("#tvProductos").queryTableView();
        btnNuevo = lookup("#btnNuevo").query();
        btnBorrar = lookup("#btnBorrar").query();

    }

    /**
     * Inicia Sesión
     */
    //  @Test
    public void testA_IniciarIsEnabled() {
        clickOn("#txtUsuario");
        write("pepe");
        clickOn("#txtContrasena");
        write("abcd");
        clickOn("#btnIniciar");
    }

    /**
     * Verificar que la ventana de productos este con los valores establecidos
     */
    //  @Test
    public void testB_IniciarVentana() {
        // verifyThat("#tfBuscar", hasText(""));
        verifyThat(btnBorrar, isDisabled());
        verifyThat(btnNuevo, isEnabled());

    }

    /**
     * Comprobar que el boton nuevo crea una nueva celda y el boton borrar este
     * habilitado
     */
    // @Test
    public void testD_NuevaCeldaTableView() {
        //verificar que la tabla tiene filas
        assertNotEquals("Table has no data: Cannot test.",
                table.getItems().size(), 0);
        clickOn(btnNuevo);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat(btnBorrar, isEnabled());
    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Fácil). Aqui borraremos
     * el producto será facíl porque el producto no tiene ni una sola reserva
     */
    //@Test
    public void testE_BorrarCelda() {
        int rowCount = table.getItems().size();
        Node row = lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        assertEquals("La fila ha sido borrada con exito", rowCount, table.getItems().size() + 1);
    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Medio). En este modo al
     * borrar un producto solamente pondremos el stock a 0
     */
    //@Test
    public void testF_BorrarCelda() {
        int rowCount = table.getItems().size();
        Node row = lookup(".table-row-cell").nth(1).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        //Comprueba que el producto no ha sido borrado
        assertEquals("No se ha borrado la fila", rowCount, table.getItems().size());
        //Aqui es cuando el producto esta reservado y pones el stock a 0    
        Producto producto = (Producto) table.getSelectionModel()
                .getSelectedItem();
        assertEquals("La fila se ha modificado a un stock 0", producto.getStock().toString(), 0);

    }

    /**
     * Comprueba que se borra la celda de la tabla (Modo Forzado)
     */
    // @Test
    public void testG_BorrarCelda() {
        int rowCount = table.getItems().size();
        Node row = lookup(".table-row-cell").nth(1).query();
        clickOn(row);
        clickOn("#btnBorrar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        //Aqui el stock se pone a 0
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        Node row2 = lookup(".table-row-cell").nth(1).query();
        clickOn(row2);
        clickOn("#btnBorrar");
        //Borrado definitivo
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        assertEquals("Borrado forzado", rowCount, table.getItems().size() + 1);
    }

    /**
     * Comprobar que un producto se crea corretamente
     */
    // @Test
    public void testH_CrearProducto() {
        int rowCount = table.getItems().size();
        clickOn(btnNuevo);
        node = lookup("#tcProducto").nth(1).query();
        doubleClickOn(node);
        write("Marquitos69");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        node = lookup("#tcStock").nth(1).query();
        doubleClickOn(node);
        write("100");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        node = lookup("#tcPrecio").nth(1).query();
        doubleClickOn(node);
        write("40.85");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        node = lookup("#tcTipo").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //  write("Pedrito");
        node = lookup("#tcTalla").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.UP).release(KeyCode.UP);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        //
        node = lookup("#tcDescripcion").nth(1).query();
        doubleClickOn(node);
        write("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        node = lookup("#tcProveedor").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        node = lookup("#tcDisponibilidad").nth(1).query();
        doubleClickOn(node);
        clickOn(node);
        write("27/01/2021");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        assertEquals("Producto creado exitosamente", rowCount + 1, table.getItems().size());

    }

    /**
     * Verificar que se pueda modificaar en los campos de la tabla
     */
    // @Test
    public void testI_ModificarProducto() {
        //Almacenamos el tamaño de la tabla
        int rowCount = table.getItems().size();
        assertNotEquals("Tabla productos : No se ha podido realizar el test.",
                rowCount, 0);
        //Selecionamos la primera fila
        Node row = lookup(".table-row-cell").nth(0).query();
        //Si la fila en null la tabla no tiene ningun registro
        assertNotNull("Row is null: table has not that row. ", row);
        //Seleccionamos la primera fila(0)
        clickOn(row);
        //Selecionamos el item producto
        Producto producto = (Producto) table.getSelectionModel()
                .getSelectedItem();

        //Seleccionamos el producto
        node = lookup("#tcProducto").nth(1).query();
        doubleClickOn(node);
        write("Usuario");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos el stock
        node = lookup("#tcStock").nth(1).query();
        doubleClickOn(node);
        write("30");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos el precio
        node = lookup("#tcPrecio").nth(1).query();
        doubleClickOn(node);
        write("7.85");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos el tipo
        node = lookup("#tcTipo").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.UP).release(KeyCode.UP);
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos la talla
        node = lookup("#tcTalla").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        //
        //Seleccionamos la descripción
        node = lookup("#tcDescripcion").nth(1).query();
        doubleClickOn(node);
        write("kddddddd");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos el proveedor
        node = lookup("#tcProveedor").nth(1).query();
        doubleClickOn(node);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos la disponibilidad
        node = lookup("#tcDisponibilidad").nth(1).query();
        doubleClickOn(node);
        clickOn(node);
        write("27/01/2022");
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        //Seleccionamos el producto
        Producto productoModificado = (Producto) table.getSelectionModel()
                .getSelectedItem();

        //Verificar si todos los campos son modificables
        assertNotEquals("Tipo del producto modificado ", producto.getModelo(), productoModificado.getModelo());
        assertNotEquals("Stock modificado ", producto.getStock(), productoModificado.getStock());
        assertNotEquals("Precio modificado ", producto.getPrecio(), productoModificado.getPrecio());
        assertNotEquals("Tipo modificado ", producto.getTipo(), productoModificado.getTipo());
        assertNotEquals("Talla modificada", producto.getTalla(), productoModificado.getTalla());
        assertNotEquals("Descripcion modificada ", producto.getDescripcion(), productoModificado.getDescripcion());
        assertNotEquals("Proveedor modifcado ", producto.getProveedor(), productoModificado.getProveedor());
        assertNotEquals("Disponibilidad modificada", producto.getDisponibilidad(), productoModificado.getDisponibilidad());

    }

    /**
     * Verificamos que la fecha sea la correcta
     */
    // @Test
    public void testJ_VerificarDisponibilidad() {

        //Seleccionaremos y actualizaremos la disponibilidad para poder verificar este método
        Node row = lookup(".table-row-cell").nth(0).query();
        clickOn(row);
        node = lookup("#tcDisponibilidad").nth(1).query();
        doubleClickOn(node);
        clickOn(node);
        write("27/01/2021");
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        Producto producto = (Producto) table.getSelectionModel()
                .getSelectedItem();

        //Selecionamos la primera fila
        node = lookup("#tcDisponibilidad").nth(1).query();
        //Si la fila en null la tabla no tiene ningun registro
        assertNotNull("Row is null: table has not that row. ", node);
        //Seleccionamos la primera fila(0)
        doubleClickOn(node);
        clickOn(1060, 160);
        clickOn(1060, 300);
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        Producto productoModificado = (Producto) table.getSelectionModel()
                .getSelectedItem();

        //Aqui verificamos si la fecha es incorrecta se mantendrá con la fecha inicial
        assertEquals("Disponibilidad modificada", producto.getDisponibilidad(), productoModificado.getDisponibilidad());

    }

    /**
     * Verificamos el comportamiento de la barra de menu(menúItems) RESERVA
     */
    // @Test
    public void testK_menuItemReservas() {

        clickOn("#mReservas");
        clickOn("#miGestionarReservas");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        //Salimos a la ventana de Nadir
        verifyThat("#pnReserva", isVisible());

    }

    /**
     * Verificamos el comportamiento de la barra de menu(menúItems) RESERVA
     */
    //@Test
    public void testl_menuItemSalir() {

        clickOn("#mProductos");
        clickOn("#miSalir");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        //Salimos a la ventana de Nadir
        verifyThat("#pnPrincipal", isVisible());
    }

    /**
     * Vericamos que el buscar nos devuelve resultados
     */
    @Test
    public void testM_listaFiltro() {

        clickOn("#tfBuscar");
        write("Usuario");
        //Verificamos que el dato introducido se encuentra
        node = lookup(".table-row-cell").nth(0).query();
        clickOn(node);
        Producto producto = (Producto) table.getSelectionModel()
                .getSelectedItem();
        assertEquals("La búsqueda ha tenido exito (producto)", producto.getModelo(), "Usuario");

        clickOn("#tfBuscar");
        eraseText(40);
        write("1009");
        //Verificamos que el dato introducido se encuentra
        node = lookup(".table-row-cell").nth(1).query();
        clickOn(node);
        Producto producto2 = (Producto) table.getSelectionModel()
                .getSelectedItem();
        assertEquals("La búsqueda ha tenido exito (id) ", producto2.getId().toString(), "1009");

    }
}
