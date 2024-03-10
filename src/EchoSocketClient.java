//importaciones necesarias

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Esta clase se encarga de crear y leer los flujos de entrada y salida para
 * leer a través del socket, mismo que es creado a partir del método
 * socketConnection.
 *
 * @author BETO
 */
public class EchoSocketClient {

    //socket va a permitir la conexión de este cliente al servidor
    private Socket socket = null;
    //permitirá leer la respuesta del servidor
    private BufferedReader flujoEntrada = null;
    //permitirá el envío de información al servidor
    private PrintWriter flujoSalida = null;

    //constructor vacío
    public EchoSocketClient() {
        //inicializa el socket
        this.socket = EchoSocketConnection.socketConnection();

        //manejar las posibles excepciones que arrojan los metodos Input y Output Stream
        try {
            // objeto que permite leer la respuesta del servidor
            this.flujoEntrada = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream())
            );

            //objeto que permite enviar información al servidor
            this.flujoSalida = new PrintWriter(this.socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Método que permite enviar un mensaje al servidor echo a través del flujo
     * de salida, además retorna la respuesta del servidor
     *
     * @param mensaje a enviar al servidor
     * @return respuesta del servidor
     */
    public String enviarMensaje(String mensaje) {
        //envio de mensaje al servidor a traves del flujo de salida
        this.flujoSalida.println(mensaje);

        //retorna la respuesta del servidor
        return recibirMensaje();
    }

    /**
     * Método que permite leer el mensaje recibido a través del flujo de entrada
     * (respuesta del servidor)
     *
     * @return mensaje recibido en el flujo de entrada
     */
    public String recibirMensaje() {
        String mensaje = null;

        try {
            //lee el flujo de entrada
            mensaje = this.flujoEntrada.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //retorna el mensaje
        return mensaje;
    }

    //metodos de acceso
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getSockInput() {
        return flujoEntrada;
    }

    public void setSockInput(BufferedReader sockInput) {
        this.flujoEntrada = sockInput;
    }

    public PrintWriter getSocketOuput() {
        return flujoSalida;
    }

    public void setSocketOuput(PrintWriter socketOuput) {
        this.flujoSalida = socketOuput;
    }
}
