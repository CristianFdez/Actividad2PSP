package requerimiento1_2_y_3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	public static final int PUERTO = 2042;	
	private ArrayList<Libro> listaLibros;


	public static void main(String[] args) {
		System.out.println("      APLICACIÓN DE SERVIDOR CON HILOS     ");
		System.out.println("-------------------------------------------");	
		
//		InputStreamReader entrada = null;
//		Socket socketDelCliente = null;
		
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		Libro libro1 = new Libro("1111", "El Quijote", "Miguel de Cervantes", 20);
		Libro libro2 = new Libro("2222", "Harry Potter y la Piedra Filosofal", "J.K. Rowling", 15.5);
		Libro libro3 = new Libro("3333", "Harry Potter y la Camara Secreta", "J.K. Rowling", 13);
		Libro libro4 = new Libro("4444", "Memorias de Idhun", "Laura Gallego", 17.99);
		Libro libro5 = new Libro("5555", "Juego de Tronos", "George R.R. Martin", 19.99);
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		
		int peticion = 0;
		
		try (ServerSocket servidor = new ServerSocket()){			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);

			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			while (true) {
//				Socket socketAlCliente = servidor.accept();
//				entrada = new InputStreamReader(socketDelCliente.getInputStream());
//				BufferedReader bf = new BufferedReader(entrada);			
//				String stringRecibido = bf.readLine();
//				String[] conjunto = stringRecibido.split("-");
//				
//			if(conjunto[0].equals("L")) {
//					listaLibros.add(new Libro(conjunto[1], conjunto[2], conjunto[3], conjunto[4]));
//					System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
//					
//				}else {
					Socket socketAlCliente = servidor.accept();
					System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");

					new HiloConsultaLibros(socketAlCliente, listaLibros);
				}
	//		}	
			
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}
			
	}
	


}
