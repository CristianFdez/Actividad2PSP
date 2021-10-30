package requerimiento1_2_y_3;

//En la clase Libro hemos creado el constructor con todo e implementado los getters por que son los únicos que necesitamos.
public class Libro{
	private String isbn, titulo, autor, precio;

	public Libro(String isbn, String titulo, String autor, String precio) {
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

	public String getPrecio() {
		return precio;
	}
	
}
