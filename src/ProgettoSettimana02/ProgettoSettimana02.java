package ProgettoSettimana02;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.io.FileUtils;

public class ProgettoSettimana02 {


	public static void main(String[] args) {


		System.out.println("Progetto Settimana 02 - CATALOGO BIBLIOTECARIO");
		Scanner input = new Scanner(System.in);
		int scelta = 0;


		Map<String, Cartaceo> catalogo = new HashMap<String, Cartaceo>();
		File file = new File("Catalogo.txt");






		do {
			try {

				System.out.println("Benvenuto nel nostro Catalogo Bibliotecario!\nScegli un'opzione:");
			System.out.println("1. Aggiungi un nuovo prodotto al Catalogo Bibliotecario");
			System.out.println("2. Rimuovi un prodotto dal Catalogo Bibliotecario");
			System.out.println("3. Fai una ricerca per ISBN");
			System.out.println("4. Fai una ricerca per anno di pubblicazione");
			System.out.println("5. Fai una ricerca per autore");
			System.err.println("6. Elimina il catalogo");
			System.out.println("7. Stampa catalogo");
			System.out.println("0. Esci");

			scelta = Integer.parseInt(input.nextLine());
			switch (scelta) {
			case 1:
				System.out.println("Cosa vuoi aggiungere?");
				System.out.println("1. Rivista");
				System.out.println("2. Libro");
				System.out.println("0. Torna al Menù iniziale");
				try {
					int sceltaTipo = Integer.parseInt(input.nextLine());
					switch (sceltaTipo) {
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
						String periodicitaRSTRING = periodicitaRString.toUpperCase();
						Periodicita periodicitaRSup = Periodicita.valueOf(periodicitaRSTRING);
						System.out.println("Inserire il numero di pagine della Rivista " + "'" + titoloRSup + "'");
						int numPagSup = Integer.parseInt(input.nextLine());
						Supplier<Rivista> rivistaSupplier = () -> {

							return new Rivista(isbnRSup, titoloRSup, annoPubRSup, numPagSup, periodicitaRSup);

						};
						riscriviCatalogo(catalogo, file);
						catalogo.put(isbnRSup, rivistaSupplier.get());

						System.out.println(
								"Hai inserito correttamente la Rivista " + "'" + titoloRSup + "' nel catalogo");
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
						riscriviCatalogo(catalogo, file);
						catalogo.put(isbnLSup, libroSupplier.get());


						System.out.println("Hai inserito correttamente il Libro " + "'" + titoloLSup + "'" + " di "
								+ autoreLSup + " nel catalogo");
						System.out.println("**********");
						break;

					case 0:
						System.out.println("**********");

						break;
					default:
						System.out.println("Scelta non valida. Riprova");
						System.out.println("**********");

						break;
				}
				} catch (NumberFormatException e) {
					System.out.println("Inserisci un numero.");
				}

				break;
			case 2:
				System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere");

				try {
					String isbnDaRimuovere = input.nextLine();

					Predicate<String> presente = (isbn) -> catalogo.containsKey(isbn);

					if (presente.test(isbnDaRimuovere)) {
						Cartaceo elementoRimosso = catalogo.remove(isbnDaRimuovere);
						String tipoElemento = elementoRimosso instanceof Rivista ? "La Rivista" : "Il Libro";
						String nomeElemento = elementoRimosso.getTitolo();
						System.out.println(tipoElemento + "'" + nomeElemento + "' è "
								+ (elementoRimosso instanceof Rivista ? "stata rimossa" : "stato rimosso")
								+ " dal Catalogo Bibliotecario");
						riscriviCatalogo(catalogo, file);
						System.out.println("**********");

					} else {
						System.out.println("L'elemento con ISBN " + isbnDaRimuovere
								+ " non è presente nel Catalogo Bibliotecario");
					}
				} catch (Exception e) {
					System.out.println("Input non valido. Assicurati di inserire un valore.");
				}

				break;


			case 3:
				System.out.println("Inserisci il codice ISBN dell'elemento da ricercare");
				String isbnDaRicercare = input.nextLine();

				catalogo.entrySet().stream().filter(chiaveValore -> chiaveValore.getKey().equals(isbnDaRicercare))
						.forEach(elemento -> {
							Cartaceo elementoTrovato = elemento.getValue();
							String tipoElemento = elementoTrovato instanceof Rivista ? "La Rivista" : "Il Libro";

							System.out.println(tipoElemento + " '" + elementoTrovato.getTitolo() + "', con codice ISBN "
									+ isbnDaRicercare + " è presente nel Catalogo Bibliotecario:\n");
							System.out.println("Numero di pagine = " + elementoTrovato.getNumeroPagine());
							System.out.println("Anno di pubblicazione = " + elementoTrovato.getAnnoPubblicazione());
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

				Predicate<Cartaceo> annoPresente = (cartaceo) -> cartaceo.getAnnoPubblicazione().equals(annoDaCercare);

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
				System.out.println("Inserisci il nome dell'autore da cercare");
				String autoreDaCercare = input.nextLine();

				boolean presente2 = false;

				System.out.println("La lista dei prodotti dell'autore " + autoreDaCercare + " è:");
				for (Cartaceo elemento : catalogo.values()) {
					if (elemento instanceof Libro) {
						Libro libroTrovato = (Libro) elemento;
						if (libroTrovato.getAutore().equalsIgnoreCase(autoreDaCercare)) {
							presente2 = true;

							System.out.println("Titolo = '" + libroTrovato.getTitolo() + "'");
							System.out.println("Numero di pagine = " + libroTrovato.getNumeroPagine());
							System.out.println("Anno di pubblicazione = " + libroTrovato.getAnnoPubblicazione());
							System.out.println("ISBN = " + libroTrovato.getIsbn());
							System.out.println(libroTrovato);
							System.out.println("**********");
						}
					}
				}

				if (!presente2) {
					System.out.println("Nessun prodotto trovato dell'autore " + autoreDaCercare);
				}
				break;

			case 6:

				try {

					FileUtils.writeStringToFile(file, "", "UTF-8");
					catalogo.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Catalogo completo:");
				catalogo.values().forEach(elemento -> {

					String nomeElemento = elemento.getTitolo();
					System.out.println(" '" + nomeElemento + "'");
					System.out.println("Numero di pagine = " + elemento.getNumeroPagine());
					System.out.println("Anno di pubblicazione = " + elemento.getAnnoPubblicazione());
					if (elemento instanceof Libro) {
						Libro libro = (Libro) elemento;
						System.out.println("Genere = " + libro.getGenere());
						System.out.println("Autore = " + libro.getAutore());
					} else if (elemento instanceof Rivista) {
						Rivista rivista = (Rivista) elemento;
						System.out.println("Periodicità = " + rivista.getPeriodicita());
					}
					System.out.println("**********");
				});
				break;

			case 0:
				System.out.println("Arrivederci.");
				break;
			default:
				System.out.println("Scelta non valida. Riprova.");
				break;
			}

		}

		catch (NumberFormatException e) {
			System.out.println("Inserisci un numero.");
		}

		} while (scelta != 0);

		input.close();
	}

	public static void riscriviCatalogo(Map<String, Cartaceo> catalogo, File percorsoFile) {
		try {
			for (Map.Entry<String, Cartaceo> chiaveValore : catalogo.entrySet()) {
				String isbn = chiaveValore.getKey();
				String titolo = chiaveValore.getValue().getTitolo();
				String anno = chiaveValore.getValue().getAnnoPubblicazione();
				int numPagine = chiaveValore.getValue().getNumeroPagine();
				String elementoCatalogo = isbn + " - " + titolo + " - " + anno + " - " + numPagine + " pagine";
				FileUtils.writeStringToFile(percorsoFile, elementoCatalogo + System.lineSeparator(), "UTF-8");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
