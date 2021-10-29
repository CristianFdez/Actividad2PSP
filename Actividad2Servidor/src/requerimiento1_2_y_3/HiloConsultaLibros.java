package requerimiento1_2_y_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


public class HiloConsultaLibros implements Runnable{

	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;
	private ArrayList<Libro> listaLibros;
	
	public HiloConsultaLibros(Socket socketAlCliente, ArrayList<Libro> listaLibros) {
		numCliente++;
		hilo = new Thread(this, "Cliente_"+numCliente);
		this.socketAlCliente = socketAlCliente;
		this.listaLibros = listaLibros;
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
			
			//Procesaremos entradas hasta que el texto del cliente sea FIN
			while (continuar) {
				BufferedReader bf = new BufferedReader(entrada);			
				String stringRecibido = bf.readLine();
				String[] conjunto = stringRecibido.split("-");
				
				if (conjunto[0].trim().equalsIgnoreCase("F")) {
					//Mandamos la señal de "0" para que el cliente sepa que vamos a cortar
					//la comunicacion
					salida.println("OK");
					System.out.println(hilo.getName() + " ha cerrado la comunicacion");
					continuar = false;
					
				}else {
					switch (conjunto[0]) {
					case "I":
						texto = buscarIsbn(conjunto[1]);
						break;
					case "T":
						texto = buscarTitulo(conjunto[1]);
						break;
					case "A":
						texto = buscarAutor(conjunto[1]);
						break;
					default:
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
	
	public String buscarIsbn(String isbn){
		String resultado = null;
		for (Libro l : listaLibros) {
			if (l.getIsbn().contains(isbn)) {
				resultado = l.getIsbn() + "-" + l.getTitulo() + "-" + l.getAutor() + "-" + l.getPrecio();
			}
		}
		return resultado;
	}
	
	public String buscarTitulo(String titulo){
		String resultado = "";
		for (Libro l : listaLibros) {
			if (l.getTitulo().contains(titulo)) {
				resultado = l.getIsbn() + "-" + l.getTitulo() + "-" + l.getAutor() + "-" + l.getPrecio();
			}
		}
		return resultado;
	}

	public String buscarAutor(String autor){
		String resultado = "";
		String resultadoFinal = "";
		for (Libro l : listaLibros) {
			if (l.getAutor().contains(autor)) {
				if(resultadoFinal.equals("")) {
					resultadoFinal = resultado;
				}else {
					resultadoFinal = resultadoFinal + "_" + resultado;
				}
				
			}
		}
		return resultadoFinal;
	}

}
