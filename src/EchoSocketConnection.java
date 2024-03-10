
import java.net.InetAddress;
import java.net.Socket;
// importamos el método estatico getByAddress
import static java.net.InetAddress.getByAddress;

/**
 *
 * Esta clase la cree ya que pienso que, divide mejor la responsabilidad de cada
 * clase, además esta clase nos abstrae de la conexión al socket servidor
 * "echo", teniendo aquí la información necesaria para la conexión al mismo y
 * podemos obtener la conexión unicamente llamando al método socketConnection.
 *
 * @author BETO
 */
public final class EchoSocketConnection {

    //constantes con información de conección al servidor echo de internet
    private static final int PUERTO = 9001;
    private static final byte[] BYTE_IP = {52, 43, 121, 77};

    /**
     * Mtodo que retorna una conexión con un socket
     *
     * @return Socket cliente
     */
    public static Socket socketConnection() {
        Socket socket = null;

        try {
            //representacion de la ip cliente en un objeto InetAddress
            InetAddress ip = getByAddress(BYTE_IP);

            //retorno de objeto Socket
            socket = new Socket(ip, PUERTO);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error durante la conexion con el servidor echo...");
        }

        //retorno null en caso de que no se establezca la conexión con el servidor arriba
        return socket;
    }

    //metodos de recuperacion de valores
    public static int getPUERTO() {
        return PUERTO;
    }

    public static byte[] getBYTE_IP() {
        return BYTE_IP;
    }
}
