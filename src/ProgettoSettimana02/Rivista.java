package ProgettoSettimana02;

public class Rivista extends Cartaceo {
    private Periodicita periodicita;

	public Rivista(String isbn, String titolo, String annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "Periodicita = " + periodicita + "\n";
	}

}