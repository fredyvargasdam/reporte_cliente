
import applicationClient.ApplicationClient;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.util.NodeQueryUtils.hasText;

/**
 *
 * @author Lorena Cáceres Manuel
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InicioAdministradorProveedorTest extends ApplicationTest {

    /**
     * Inicia la aplicacion para testearla
     *
     * @throws Exception, si ocurre algun error
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationClient.class);

    }

    /**
     * Comienza la ventana LogIn, introducimos las credenciales del
     * administrador para poder acceder a la ventana
     * InicioAdministradorProveedor
     */
    @Test
    public void preTest() {
        //Esperamos 3 segundos para que se pueda prepara el robot 
        sleep(3000);
        //Hacemos click en el TextField de usuario
        clickOn("#txtUsuario");
        //Escribimos el usuario: admin
        write("Administrador");
        //Hacemos click en el TextField de contraseña
        clickOn("#txtContrasena");
        //Escribimos la contraseña: admin
        write("admin");
        //Hacemos click en el botón de iniciar
        clickOn("#btnIniciar");
        //Comprobamos que la ventana de InicioAdministradorVendedor sea visible
        verifyThat("#pnInicioAdminVend", isVisible());
        //Hacemos click en la opción de menu de menuProveedor
        clickOn("#menuProveedor");
        //Hacemos click en el menuItem de menuProveedores
        clickOn("#menuProveedores");
        //Comprobamos que la ventana de InicioAdministradorProveedor sea visible
        verifyThat("Aceptar", NodeMatchers.isVisible());
        verifyThat("Cancelar", NodeMatchers.isVisible());
        clickOn("Aceptar");
        verifyThat("#pnInicioAdminProv", isVisible());

    }

    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_showStage() {
        //Comprobamos que el TextField de buscar esta vacío
        verifyThat("#tfBuscar", hasText(""));
        //Comprobamos que el botón de borrado está deshabilitado
        verifyThat("#btnBorrarProveedor", isDisabled());
        //Comprobamos que el botón de alta está habilitado
        verifyThat("#btnAltaProveedor", isEnabled());

    }

    /**
     * Test que permite comprobar el funcionamiento del DatePicker, permite
     * cambiar de fecha
     */
    @Test
    public void testB_comprobacionDataPicker() {
        //Inidicamos que clicke en la fila 1 y en la columna Fecha Alta
        Node rowFecha = lookup("#tcFechaAlta").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowFecha);
        //Clickamos en las coordenadas indicadas, hacen referencia al DatePicker
        clickOn(1315, 285);
        //Clickamos en el día 16 para poder cambiarlo
        clickOn("29");
    }

    /**
     * Test que permite añadir un nuevo proveedor respetando las validaciones de
     * las celdas
     */
    @Test
    public void testC_NuevoProveedorConValidaciones() {
        //Hacemos click en el botón de Alta Proveedor
        clickOn("#btnAltaProveedor");
        //Nombre
        //Inidicamos que clicke en la fila 0 y en la columna Nombre
        Node rowOrdenNombre = lookup("#tcNombre").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowOrdenNombre);
        //Inidicamos que clicke en la fila 1 y en la columna Nombre
        Node rowNombre = lookup("#tcNombre").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowNombre);
        //Escribimos un nombre que contenga espacios y tildes
        write("Antón García");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        ///Tipo producto
        //Inidicamos que clicke en la fila 1 y en la columna TipoProducto
        Node rowTipo = lookup("#tcTipo").nth(1).query();
        //Indicamos que el nodo anterior tiene que estar enfocado
        rowTipo.isFocused();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowTipo);
        //Hacemos otro click en el nodo anterior para que muestre los datos de la ComboBox
        clickOn(rowTipo);
        //Empresa
        //Inidicamos que clicke en la fila 0 y en la columna Empresa
        Node rowOrdenEmpresa = lookup("#tcEmpresa").nth(0).query();
        //Hacemos click en el nodo anterior para ordenar los datos de la tabla
        clickOn(rowOrdenEmpresa);
        //Inidicamos que clicke en la fila 1 y en la columna Empresa
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowEmpresa);
        //Escribimos el nombre de la empresa
        write("Quechua");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Email
        //Inidicamos que clicke en la fila 0 y en la columna Email
        Node rowOrdenEmail = lookup("#tcEmail").nth(0).query();
        //Hacemos Click en el nodo anterior
        clickOn(rowOrdenEmail);
        //Inidicamos que clicke en la fila 1 y en la columna Email
        Node rowEmail = lookup("#tcEmail").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowEmail);
        //Escribimos un email correcto
        write("quechua@gmail.com");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Teléfono
        //Inidicamos que clicke en la fila 0 y en la columna Teléfono
        Node rowOrdenTelefono = lookup("#tcTelefono").nth(0).query();
        //Hacemos Click en el nodo anterior
        clickOn(rowOrdenTelefono);
        //Inidicamos que clicke en la fila 1 y en la columna Teléfono
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowTelefono);
        //Escribimos un número de teléfono correcto
        write("615478520");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Descripción
        //Inidicamos que clicke en la fila 0 y en la columna Descripción
        Node rowOrdenDescripcion = lookup("#tcDescripcion").nth(0).query();
        //Hacemos Click en el nodo anterior
        clickOn(rowOrdenDescripcion);
        //Inidicamos que clicke en la fila 1 y en la columna Descripción
        Node rowDescripcion = lookup("#tcDescripcion").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowDescripcion);
        //Escribimos una descripción
        write("Producto de buena calidad y rápida entrega");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);

    }

    /**
     * Test que comprueba que al clickar en el botón de borrar proveedor, y al
     * aceptar la confirmación, se borrará el proveedor seleccionado
     */
    @Test
    public void testD_botonBorrarAceptar() {
        //Inidicamos que clicke en la fila 1 y en la columna Nombre
        Node row = lookup("#tcNombre").nth(1).query();
        //Hacemos click en el nodo anterior
        clickOn(row);
        //Comprobamos que el botón de borrado está habilitado
        verifyThat("#btnBorrarProveedor", isEnabled());
        //Hacemos click en el botón de borrado de proveedor
        clickOn("#btnBorrarProveedor");
        //Comprobamos que sale una alerta de confirmación y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Comprobamos que sale una alerta de confirmación y sale la opción Cancelar
        verifyThat("Cancelar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al clickar en el botón de borrar proveedor, sale
     * una alerta de confirmación del borrado de ese proveedor
     */
    @Test
    public void testE_botonBorrarCancelar() {
        //Inidicamos que clicke en la fila 1 y en la columna Nombre
        Node row = lookup("#tcNombre").nth(1).query();
        //Hacemos click en el nodo anterior
        clickOn(row);
        //Hacemos click en el botón de borrado de proveedor
        clickOn("#btnBorrarProveedor");
        //Comprobamos que el botón borrado de proveedor está habilitado
        verifyThat("#btnBorrarProveedor", isEnabled());
        //Comprobamos que sale una alerta de confirmación y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Comprobamos que sale una alerta de confirmación y sale la opción Cancelar
        verifyThat("Cancelar", NodeMatchers.isVisible());
        //Hacemos click en Cancelar
        clickOn("Cancelar");
    }

    /**
     * Test que comprueba que al introducir un caracter válido no dejará guardar
     * los cambios dentro de la celda del Nombre
     */
    @Test
    public void testF_AlertaNombre() {
        //Hacemos click en el botón de Alta de proveedor
        clickOn("#btnAltaProveedor");
        //Inidicamos que clicke en la fila 0 y en la columna Nombre
        Node rowOrdenarNombre = lookup("#tcNombre").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarNombre);
        //Inidicamos que clicke en la fila 1 y en la columna Nombre
        Node rowNombre = lookup("#tcNombre").nth(1).query();
        //Hacemos dobleClick en el nodo anterior
        doubleClickOn(rowNombre);
        //Escribimos un caracter inválido
        write(".");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir un caracter válido no dejará guardar
     * los cambios dentro de la celda de la Empresa
     */
    @Test
    public void testG_AlertaEmpresa() {
        //Inidicamos que clicke en la fila 0 y en la columna Empresa
        Node rowOrdenarEmpresa = lookup("#tcEmpresa").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarEmpresa);
        //Inidicamos que clicke en la fila 1 y en la columna Empresa
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowEmpresa);
        //Escribimos un caracter inválido 
        write(".");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir un caracter válido no dejará guardar
     * los cambios dentro de la celda del Email
     */
    @Test
    public void testH_AlertaEmail() {
        //Inidicamos que clicke en la fila 0 y en la columna Email
        Node rowOrdenarEmail = lookup("#tcEmail").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarEmail);
        //Inidicamos que clicke en la fila 1 y en la columna Email
        Node rowEmail = lookup("#tcEmail").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowEmail);
        //Escribimos un caracter inválido 
        write(".");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir una serie de números y sobrepase el
     * límite no dejará guardar los cambios dentro de la celda del Teléfono
     */
    @Test
    public void testI_AlertaTelefonoLargo() {
        //Inidicamos que clicke en la fila 0 y en la columna Teléfono
        Node rowOrdenarTelefono = lookup("#tcTelefono").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarTelefono);
        //Inidicamos que clicke en la fila 1 y en la columna Teléfono
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowTelefono);
        //Escribimos un caracter inválido 
        write("615478520888");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir un número o una serie de números
     * inferior a seis dígitos no dejará guardar los cambios dentro de la celda
     * del Teléfono
     */
    @Test
    public void testJ_AlertaTelefonoCorto() {
        //Inidicamos que clicke en la fila 0 y en la columna Teléfono
        Node rowOrdenarTelefono = lookup("#tcTelefono").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarTelefono);
        //Inidicamos que clicke en la fila 1 y en la columna Teléfono
        Node rowTelefono = lookup("#tcTelefono").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowTelefono);
        //Escribimos un caracter inválido 
        write("6");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir un caracter válido no dejará guardar
     * los cambios dentro de la celda de la Descripción
     */
    @Test
    public void testK_AlertaDescripcion() {
        //Inidicamos que clicke en la fila 0 y en la columna Descripción
        Node rowOrdenarDescripcion = lookup("#tcDescripcion").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarDescripcion);
        //Inidicamos que clicke en la fila 1 y en la columna Descripción
        Node rowDescripcion = lookup("#tcDescripcion").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowDescripcion);
        //Escribimos un caracter inválido 
        write(".");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al introducir una empresa ya introducida dentro de
     * la base de datos, saldrá una alerta informándonos que el proveedor ya
     * está dado de alta
     */
    @Test
    public void testL_ProveedorYaExiste() {
        //Hacemos click en el botón de Alta proveedor
        clickOn("#btnAltaProveedor");
        //Inidicamos que clicke en la fila 0 y en la columna Empresa
        Node rowOrdenarEmpresa = lookup("#tcEmpresa").nth(0).query();
        //Hacemos click en el nodo anterior 
        clickOn(rowOrdenarEmpresa);
        //Inidicamos que clicke en la fila 1 y en la columna Empresa
        Node rowEmpresa = lookup("#tcEmpresa").nth(1).query();
        //Hacemos dobleClick en el nodo anterior 
        doubleClickOn(rowEmpresa);
        //Escribimos una empresa que ya esté registrada  
        write("Quechua");
        //Hacemos que pulse el Enter para poder guardar los cambios de la celda
        this.push(KeyCode.ENTER);
        //Comprobamos que sale una alerta y sale la opción Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que al pulsar el botón de borrado de proveedor y no
     * hay seleccionado ningún proveedor, saldrá una alerta informando al
     * usuario que debería de seleccionar un proveedor antes de presionar el
     * botón
     */
    @Test
    public void testM_botonBorrarError() {
        //Hacemos click en el botón de borrar un proveedor
        clickOn("#btnBorrarProveedor");
        //Comprobamos que sale una alerta de confirmación y muestra la opción de Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Clickamos en Aceptar
        clickOn("Aceptar");
        //Comprobamos que sale una alerta de aviso y muestra la opción de Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Clickamos en Aceptar
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba la opción de menú de Lista de Vendedores, esta nos
     * permite acceder dentro de la ventana InicioAdministradorVendedor
     */
    @Test
    public void testN_menuListaVendedores() {
        //Hacemos click dentro de la opción de menu Vendedor
        clickOn("#menuVendedor");
        //Hacemos click en el menuItem de vendedores
        clickOn("#menuVendedores");
        //Comprobamos que se muestra la ventana InicioAdministradorVendedor
        verifyThat("#pnInicioAdminVend", isVisible());
    }

    /**
     * Test que comprueba la opción de menú de Salir, esta nos permite salir de
     * la ventana actual y nos redirije al Login
     */
    @Test
    public void testO_menuSalir() {
        //Hacemos click dentro de la opción de menu Perfil
        clickOn("#menuPerfil");
        //Hacemos click en el menuItem de Salir
        clickOn("#menuSalir");
        //Comprobamos que sale una alerta y muestra la opción de Aceptar
        verifyThat("Aceptar", NodeMatchers.isVisible());
        //Comprobamos que sale una alerta y muestra la opción de Cancelar
        verifyThat("Cancelar", NodeMatchers.isVisible());
        //Hacemos click en Aceptar
        clickOn("Aceptar");
        //Comprobamos que nos redirije hacia la Ventana del LogIn
        verifyThat("#pnPrincipal", isVisible());
    }

}
