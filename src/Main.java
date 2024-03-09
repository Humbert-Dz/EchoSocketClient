//Importaciones necesarias

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //declaracion de arreglo con la ip del servidor Echo de internet
        byte[] byteIp = {52, 43, 121, 77};

        // numero de puerto del servidor
        int puerto = 9001;

        //ip va a representar la ip del servidor en un objeto
        InetAddress ip = null;
        //socket va a permitir la conexión de este cliente al servidor
        Socket socket = null;
        //sockInput va a permitir leer la respuesta del servidor
        BufferedReader sockInput = null;
        // socketOutput va a permitir el envío de información al servidor
        PrintWriter socketOuput = null;

        //manejar la posible excepcion que arroja el metodo getByAddress
        try {
            // obteniendo un objeto de tipo InetByAddress
            ip = InetAddress.getByAddress(byteIp);
        } catch (UnknownHostException e) {
            e.printStackTrace(System.out);
        }

        //manejar la posble excepcion que arroja el constructor Socket
        try {
            // creando un socket a partir de la direccion ip y el puerto
            socket = new Socket(ip, puerto);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        //manejar las posibles excepciones que arrojan los metodos Input y Output Stream
        try {
            // objeto que permite leer la respuesta del servidor
            sockInput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            //objeto que permite enviar información al servidor
            socketOuput = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        //objeto Scanner para leer la consola
        Scanner consola = new Scanner(System.in);

        //variable para almacenar la lectura de la consola
        String lecturaDeConsola;

        //mensaje de advertencia sobre lectura: espacios en blanco o cadenas vacias
        System.out.println("No se permiten espacios en blanco o cadenas vacias!!!");

        while (true) {
            try {
                //mostrar la respuesta del servidor
                System.out.println(sockInput.readLine());
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            //leemos la consola
            lecturaDeConsola = consola.nextLine();

            //si se intenta enviar espacios en blanco o cadenas vacias al servidor
            if (lecturaDeConsola.isBlank()) {
                //mensaje de aviso
                System.out.println("No se permiten espacios en blanco, programa terminado..");
                //rompe ciclo
                break;
            } else {
                //le enviamos lo que escribimos en consola al servidor
                socketOuput.println(lecturaDeConsola);
            }
        }
    }
}
