package requerimiento1_2_y_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

//Este hilo recibe la información necesaria para devolver información del libro buscado o añadir un nuevo libro a la biblioteca.
//La conexion se mantendra abierta hasta que el cliente indique que quiere salir con la letra "F".
public class HiloConsultaLibros implements Runnable{
	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;
	private Biblioteca biblioteca;
	
	public HiloConsultaLibros(Socket socketAlCliente, Biblioteca biblioteca) {
		numCliente++;
		hilo = new Thread(this, "Cliente_"+numCliente);
		this.socketAlCliente = socketAlCliente;
		this.biblioteca = biblioteca;
		hilo.start();
	}
	
	@Override
	public void run() {		
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		
		try {
			//Salida del servidor al cliente
			salida = new PrintStream(socketAlCliente.getOutputStream());
			
			//Entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());

			String texto = "";
			boolean continuar = true;
			
			while (continuar) {
				
				BufferedReader bf = new BufferedReader(entrada);			
				String stringRecibido = bf.readLine();
				String[] conjunto = stringRecibido.split("-");
				
				if (conjunto[0].trim().equalsIgnoreCase("F")) {				
					salida.println("KO");
					System.out.println(hilo.getName() + " ha cerrado la comunicacion");
					continuar = false;
					
				}else {
					switch (conjunto[0]) {
					case "I":
						texto = biblioteca.buscarIsbn(conjunto[1]);
						break;
					case "T":
						texto = biblioteca.buscarTitulo(conjunto[1]);
						break;
					case "A":
						texto = biblioteca.buscarAutor(conjunto[1]);
						break;
					case "L":
						biblioteca.añadirLibro(conjunto[1], conjunto[2], conjunto[3], conjunto[4]);
						texto = "OK";
						break;
					case "N":
						texto = "N";
						break;
					}
					
					salida.println(texto);
				}
			}

			socketAlCliente.close();

		} catch (IOException e) {
			System.err.println("HiloContadorLetras: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloContadorLetras: Error");
			e.printStackTrace();
		}
	}
}