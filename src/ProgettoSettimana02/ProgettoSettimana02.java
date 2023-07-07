package ProgettoSettimana02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

public class ProgettoSettimana02 {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProgettoSettimana02.class);

	public static void main(String[] args) {

		Faker f = new Faker();
		System.out.println("Progetto Settimana 02 - CATALOGO BIBLIOTECARIO");
		Scanner input = new Scanner(System.in);
		int scelta = 0;

		// Dichiaro una Map catalogo dove utilizzo il codice isbn come chiave
		Map<String, Cartaceo> catalogo = new HashMap<String, Cartaceo>();

		// Definisco i Supplier per gestire RIvista e libro da cui poi fare get() e
		// .put() per metterli in catalogo





		do {

			System.out.println("Benvenuto nel nostro Catalogo Bibliotecario!\nScegli un'opzione:");
			System.out.println("1. Aggiungi un nuovo prodotto al Catalogo Bibliotecario");
			System.out.println("2. Rimuovi un prodotto dal Catalogo Bibliotecario");
			System.out.println("3. Fai una ricerca per ISBN");
			System.out.println("4. Fai una ricerca per anno di pubblicazione");
			System.out.println("5. Fai una ricerca per autore");
			System.out.println("0. Esci");

			scelta = Integer.parseInt(input.nextLine());


			switch (scelta) {
			case 1:
				System.out.println("Cosa vuoi aggiungere?");
				System.out.println("1. Rivista");
				System.out.println("2. Libro");
				int sceltaTipo = Integer.parseInt(input.nextLine());
				switch(sceltaTipo) {
				case 1:
					System.out.println("Stai inserendo una Rivista nel Catalogo");
					System.out.println("Inserisci il codice ISBN");
					String isbnRSup = input.nextLine();
					System.out.println("Inserire il Titolo della rivista");
					String titoloRSup = input.nextLine();
					System.out.println("Inserire anno di pubblicazione");
					String annoPubRSup = input.nextLine();
					System.out.println("Inserire la periodicità della Rivita " + "'" + titoloRSup + "'");
					String periodicitaRString = input.nextLine();
					Periodicita periodicitaRSup = Periodicita.valueOf(periodicitaRString);
					System.out.println("Inserire il numero di pagine della Rivista " + "'" + titoloRSup + "'");
					int numPagSup = Integer.parseInt(input.nextLine());
					Supplier<Rivista> rivistaSupplier = () -> {

						return new Rivista(isbnRSup, titoloRSup, annoPubRSup, numPagSup, periodicitaRSup);

					};
					catalogo.put(isbnRSup, rivistaSupplier.get());
					System.out.println("Hai inserito correttamente la Rivista " + "'" + titoloRSup + "' nel catalogo");
					System.out.println("**********");
					break;
				case 2:
					System.out.println("Stai inserendo un Libro nel Catalogo");
					System.out.println("Inserisci il codice ISBN");
					String isbnLSup = input.nextLine();
					System.out.println("Inserire il Titolo del Libro");
					String titoloLSup = input.nextLine();
					System.out.println("Inserire anno di pubblicazione");
					String annoPubLSup = input.nextLine();
					System.out.println("Inserire il numero di pagine del Libro " + "'" + titoloLSup + "'");
					int numPagLSup = Integer.parseInt(input.nextLine());
					System.out.println("Inserire il nome completo dell'autore del Libro " + "'" + titoloLSup + "'");
					String autoreLSup = input.nextLine();
					System.out.println("Inserire il Genere Letterario del Libro " + "'" + titoloLSup + "'");
					String genereLSup = input.nextLine();
					Supplier<Libro> libroSupplier = () -> {

						return new Libro(isbnLSup, titoloLSup, annoPubLSup, numPagLSup, autoreLSup, genereLSup);

					};
					catalogo.put(isbnLSup, libroSupplier.get());
					System.out.println("Hai inserito correttamente il Libro " + "'" + titoloLSup + "'" + " di "
							+ autoreLSup + " nel catalogo");
					System.out.println("**********");
					break;
				}
				break;
			case 2:
				System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere");
				String isbnDaRimuovere = input.nextLine();

				Predicate<String> presente = (isbn) -> catalogo.containsKey(isbn);

				if (presente.test(isbnDaRimuovere)) {
					Cartaceo elementoRimosso = catalogo.remove(isbnDaRimuovere);
					String tipoElemento = elementoRimosso instanceof Rivista ? "Rivista" : "Libro";
					String nomeElemento = elementoRimosso.getTitolo();
					System.out.println("Prodotto rimosso: Tipo = " + tipoElemento + ". Nome: '" + nomeElemento + "'");
				} else {
					System.out.println("L'elemento con ISBN " + isbnDaRimuovere + " non è presente nel catalogo");
				}
				break;


			case 3:
				System.out.println("Inserisci il codice ISBN dell'elemento da ricercare");
				String isbnDaRicercare = input.nextLine();

				catalogo.entrySet().stream().filter(chiaveValore -> chiaveValore.getKey().equals(isbnDaRicercare))
						.map(chiaveValore -> chiaveValore.getValue()).findFirst().ifPresent(elementoTrovato -> {
							String tipoElemento = elementoTrovato instanceof Rivista ? "La Rivista" : "Il Libro";

							System.out.println(
									tipoElemento + " '" + elementoTrovato.getTitolo() + "', con codice ISBN "
											+ isbnDaRicercare
											+ " è presente nel Catalogo Bibliotecario:\n");
							System.out.println("Numero di pagine = " + elementoTrovato.getNumeroPagine());
							System.out.println(
									"Anno di pubblicazione = " + elementoTrovato.getAnnoPubblicazione());
							System.out.println(elementoTrovato);
							System.out.println("**********");
						});

				if (!catalogo.containsKey(isbnDaRicercare)) {
					System.out.println("L'elemento con ISBN " + isbnDaRicercare + " non è presente nel catalogo");
					System.out.println("**********");

				}
				break;

			case 4:
				System.out.println("Inserisci l'anno di pubblicazione da cercare");
				String annoDaCercare = input.nextLine();

				Predicate<Cartaceo> annoPresente = cartaceo -> cartaceo.getAnnoPubblicazione().equals(annoDaCercare);

				boolean presente1 = catalogo.values().stream().anyMatch(annoPresente);

				if (presente1) {
					System.out.println(
							"La lista dei prodotti che hanno un anno di pubblicazione " + annoDaCercare + " è:");
					catalogo.values().stream().filter(annoPresente).forEach(elemento -> {
						String tipoElemento = elemento instanceof Rivista ? "La Rivista" : "Il Libro";
						String nomeElemento = elemento.getTitolo();
						System.out.println(tipoElemento + " '" + nomeElemento + "'");
						System.out.println("Numero di pagine = " + elemento.getNumeroPagine());
						System.out.println("Anno di pubblicazione = " + elemento.getAnnoPubblicazione());
						System.out.println(elemento);
						System.out.println("**********");
					});
				} else {
					System.out.println("Nessun prodotto trovato con l'anno di pubblicazione " + annoDaCercare);
				}
				break;


			case 5:
				// Logica per la ricerca per autore
				break;
			case 0:
				System.out.println("Arrivederci.");
				break;
			default:
				System.out.println("Scelta non valida. Riprova.");
				break;
			}
		} while (scelta != 0);

		input.close();
	}
}
