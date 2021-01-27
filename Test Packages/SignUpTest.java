
import applicationClient.ApplicationClient;
import javafx.stage.Stage;
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
public class SignUpTest extends ApplicationTest {

    public String num = "123456789123456789123456789123456789123456789123456789123456789";
    public String maxCaracteres = "Quiere la boca exhausta vid, kiwi, piña y fugaz jamón. "
            + "Fabio me exige, sin tapujos, que añada cerveza al whisky."
            + " Jovencillo emponzoñado de whisky, ¡qué figurota exhibes! "
            + "La cigüeña tocaba cada vez mejor el saxofón y"
            + " el búho pedía kiwi y queso. El jef";

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
     *
     */
    @Test
    public void preTest() {
        sleep(1000);
        clickOn("#hlRegistrarse");
    }

    /**
     * Test que permite ver el estado inicial de la ventana
     */
    @Test
    public void testA_InitialState() {
        verifyThat("#tfUsuario", hasText(""));
        verifyThat("#tfCorreoElectronico", hasText(""));
        verifyThat("#tfNombre", hasText(""));
        verifyThat("#tfDireccion", hasText(""));
        verifyThat("#tfTelefono", hasText(""));
        verifyThat("#pfContrasenia", hasText(""));
        verifyThat("#pfConfirmarContrasenia", hasText(""));
        verifyThat("#btnRegistrarse", isDisabled());
        verifyThat("#btnCancelar", isEnabled());

    }

    /**
     * Test que prueba que el boton Registrar esta habilitado cuando los campos
     * estan rellenos
     */
    @Test
    public void testB_RegistrarIsEnabled() {
        clickOn("#tfUsuario");
        write("username");
        clickOn("#tfCorreoElectronico");
        write("email@gmail.com");
        clickOn("#tfNombre");
        write("user");
        clickOn("#tfDireccion");
        write("Calle Tartanga");
        clickOn("#tfTelefono");
        write("698741203");
        clickOn("#pfContrasenia");
        write("password");
        clickOn("#pfConfirmarContrasenia");
        write("password");
        verifyThat("#btnRegistrarse", isEnabled());
        clickOn("#btnRegistrarse");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");

    }

    /**
     * Test que prueba que el boton Registrar esta deshabilitado cuando uno de
     * los campos estan vacios
     */
    @Test
    public void testC_RegistrarIsDisabled() {
        preTest();
        clickOn("#tfUsuario");
        write("username");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(8);
        clickOn("#tfCorreoElectronico");
        write("email@gmail.com");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(15);
        clickOn("#tfNombre");
        write("user");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(15);
        clickOn("#tfDireccion");
        write("direccion");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(9);
        clickOn("#tfTelefono");
        write("666125368");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(9);
        clickOn("#pfContrasenia");
        write("password");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(8);
        clickOn("#pfConfirmarContrasenia");
        write("password");
        verifyThat("#btnRegistrarse", isDisabled());
        eraseText(8);
        clickOn("#btnCancelar");

    }

    /**
     * Test que prueba la longitud maxima de los campos
     */
    @Test
    public void testD_maxLenght() {
        preTest();
        clickOn("#tfUsuario");
        write(num);
        eraseText(30);
        clickOn("#tfCorreoElectronico");
        write(num);
        eraseText(50);
        clickOn("#tfNombre");
        write(num);
        eraseText(50);
        clickOn("#tfDireccion");
        write(maxCaracteres);
        eraseText(250);
        clickOn("#tfTelefono");
        write(num);
        eraseText(11);
        clickOn("#pfContrasenia");
        write(num);
        eraseText(30);
        clickOn("#pfConfirmarContrasenia");
        write(num);
        eraseText(30);
        verifyThat("#btnRegistrarse", isDisabled());
    }

    @Test
    public void testE_probandoTildes() {
        clickOn("#tfUsuario");
        write("Aaron");
        clickOn("#tfCorreoElectronico");
        write("aaron@gmail.com");
        clickOn("#tfNombre");
        write("Aarón Aparicio");
        clickOn("#tfDireccion");
        write("Avenida Tartanga");
        clickOn("#tfTelefono");
        write("647852014");
        clickOn("#pfContrasenia");
        write("123456");
        clickOn("#pfConfirmarContrasenia");
        write("123456");
        clickOn("#btnRegistrarse");
        verifyThat("Aceptar", NodeMatchers.isVisible());
        clickOn("Aceptar");
    }

    /**
     * Test que comprueba que la vista del LogIn es visible cuando se hace click
     * en el boton Cancelar
     */
    @Test
    public void testF_SignUpToLogIn() {
        preTest();
        clickOn("#btnCancelar");
        verifyThat("#pnPrincipal", isVisible());
    }

}
