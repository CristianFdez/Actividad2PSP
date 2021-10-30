package requerimiento1_2_y_3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Abriremos un hilo por cada petición del servidor y para que así pueda recibir varias peticiones de diferentes clientes.
public class Servidor {

	public static final int PUERTO = 2042;	
	
	public static void main(String[] args) {
		System.out.println("      APLICACIÓN SERVIDOR     ");
		System.out.println("------------------------------");	
		Biblioteca biblioteca = new Biblioteca();
		
		int peticion = 0;
		
		try (ServerSocket servidor = new ServerSocket()){			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);

			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			while (true) {
					Socket socketAlCliente = servidor.accept();
					System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");

					//Abrimos un hilo nuevo y liberamos el hilo principal para que pueda recibir peticiones de otros clientes
					new HiloConsultaLibros(socketAlCliente, biblioteca);
				}

		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}			
	}
}
