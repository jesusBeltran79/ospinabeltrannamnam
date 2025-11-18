package co.edu.unbosque.ospinabeltrannamnam.exception;

public class NotValidStringException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotValidStringException() {
		super("El texto contiene caracteres inválidos o está vacío.");
	}
}