package requerimiento1_2_y_3;

import java.util.ArrayList;

//En la clase Biblioteca generamos un constructor que metemos directamente los datos al ArrayList y los métodos de consulta y añadir libros.
public class Biblioteca {
	
	private ArrayList<Libro> listaLibros = new ArrayList<Libro>();

	Libro libro1 = new Libro("1111", "El Quijote", "Miguel de Cervantes", "20");
	Libro libro2 = new Libro("2222", "Harry Potter y la Piedra Filosofal", "J.K. Rowling", "15.5");
	Libro libro3 = new Libro("3333", "Harry Potter y la Camara Secreta", "J.K. Rowling", "13");
	Libro libro4 = new Libro("4444", "Memorias de Idhun", "Laura Gallego", "17.99");
	Libro libro5 = new Libro("5555", "Juego de Tronos", "George R.R. Martin", "19.99");

	public Biblioteca() {
		super();
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);	
	}

	//Este método busca el libro pedido por ISBN y devuelve todos los datos de dicho libro.
	public String buscarIsbn(String isbn){
		String resultado = "";
		for (Libro l : listaLibros) {
			if (l.getIsbn().contains(isbn)) {
				resultado = l.getIsbn() + "-" + l.getTitulo() + "-" + l.getAutor() + "-" + l.getPrecio();
			}
		}
		return resultado;
	}
	
	//Este método busca el libro pedido por titulo y devuelve todos los datos de dicho libro.
	public String buscarTitulo(String titulo){
		String resultado = "";
		for (Libro l : listaLibros) {
			if (l.getTitulo().contains(titulo)) {
				resultado = l.getIsbn() + "-" + l.getTitulo() + "-" + l.getAutor() + "-" + l.getPrecio();
			}
		}
		return resultado;
	}

	//Este método busca el libro pedido por autor y devuelve todos los datos de dicho libro.
	public String buscarAutor(String autor){
		String resultado = "";
		String resultadoFinal = "";
		for (Libro l : listaLibros) {
			if (l.getAutor().contains(autor)) {
				resultado = l.getIsbn() + "-" + l.getTitulo() + "-" + l.getAutor() + "-" + l.getPrecio();
				if(resultadoFinal.equals("")) {
					resultadoFinal = resultado;
				}else {
					resultadoFinal = resultadoFinal + "_" + resultado;
				}
			}
		}
		return resultadoFinal;
	}
	
	//Este método recibe los datos de libro, crea un nuevo libro con estos datos y añade el libro a la biblioteca virtual.
	public void añadirLibro(String isbn, String titulo, String autor, String precio){
		listaLibros.add(new Libro(isbn, titulo, autor, precio));
	}
}