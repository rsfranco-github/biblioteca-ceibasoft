package dominio;

import java.util.Date;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import utilities.Util;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String EL_LIBRO_PALINDROMO_NO_SE_PRESTA = "los libros palíndromos solo se pueden utilizar en la biblioteca";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public void prestar(String isbn, String nombreUsuario) {

		// que exista el libro
		Libro libroPrestar = repositorioLibro.obtenerPorIsbn(isbn);

		if (libroPrestar != null) {

			// SBN palíndromo no se presta
			if (Util.esPalindroma(isbn)) {
				throw new PrestamoException(EL_LIBRO_PALINDROMO_NO_SE_PRESTA);
			} else {

				// verificar que el libro se encuentre disponible

				Libro libro = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);

				if (libro != null) {
					throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
				} else {

					// calcular fechaEntregaMaxima para el prestamo
					Date fechaEntregaMaxima = Util.getFechaEntregaMaxima(isbn);

					Prestamo prestamo = new Prestamo(new Date(), libroPrestar, fechaEntregaMaxima, nombreUsuario);
					repositorioPrestamo.agregar(prestamo);

				}

			}

		}

	}

	public boolean esPrestado(String isbn) {
		
		Libro libro = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		
		return libro!=null;
	}

}
