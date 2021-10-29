package requerimiento1_2_y_3;

public class Libro{
	private String isbn, titulo, autor;
	private double precio;

	public Libro(String isbn, String titulo, String autor, double precio) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public double getPrecio() {
		return precio;
	}
	
}
