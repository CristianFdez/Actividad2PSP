package requerimiento1_2_y_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static final int PUERTO = 2042;
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in)){
						
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
					" por el puerto " + PUERTO);

			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			String texto = "";			
			String opcion;
			String conjunto = "";
			
			boolean continuar = true;
			do {
				escribirMenu();
				opcion = sc.nextLine().toUpperCase();
				
				switch (opcion) {
					case "I":
						System.out.println("Escribe el ISBN del libro: ");
						texto = sc.nextLine();
						break;
					case "T":
						System.out.println("Escribe el titulo del libro: ");
						texto = sc.nextLine();
						break;
					case "A":
						System.out.println("Escribe el autor del libro: ");
						texto = sc.nextLine();
						break;
					case "L":
						System.out.println("Escribe los datos del libro que quieres añadir separados con guiones: ");
						texto = sc.nextLine();
						break;
					case "F":
						break;
					default:
						System.out.println("Opción incorrecta");
				}
				
				conjunto  = opcion + "-" + texto;
				
				salida.println(conjunto);
				System.out.println("CLIENTE: Esperando respuesta ...... ");				
				String respuesta = entradaBuffer.readLine();
				
				
				if("OK".equalsIgnoreCase(respuesta)) {
					continuar = false;
				}else {
					String[] biblioteca = respuesta.split("_");
					
						for(int i = 0; i < biblioteca.length; i++) {
							String[] libro = biblioteca[i].split("-");
							System.out.println("CLIENTE: Servidor responde, el ISBN es: " + libro[0] + 
									", el titulo es: " + libro[1] + ", el autor es: " + libro[2] + ", y el precio es: " + libro[3]);
						}
				}		
				
			}while(continuar);
			
			//Cerramos la conexion
			socketAlServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("CLIENTE: Fin del programa");
		
	}
	
	private static void escribirMenu() {
		System.out.println();
		System.out.println("Búsqueda de libros");
		System.out.println("--------------------------");
		System.out.println("I = ISBN");
		System.out.println("T = Título");
		System.out.println("A = Autor");
		System.out.println("L = Añadir libro nuevo");
		System.out.println("F = Terminar programa");
		System.out.println("--------------------------");
		System.out.println("¿Qué opción eliges?");
	}
}
