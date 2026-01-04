package com.aluracursos.librarymatch;

import com.aluracursos.librarymatch.model.DatosLibro;
import com.aluracursos.librarymatch.model.RespuestaGutendex;
import com.aluracursos.librarymatch.service.ConsumoAPI;
import com.aluracursos.librarymatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.Normalizer;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

@SpringBootApplication
public class LibrarymatchApplication implements CommandLineRunner {

	public static void main(String[] args) {

        SpringApplication.run(LibrarymatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        System.out.println("LibraryMatch iniciado correctamente");

        ConsumoAPI consumoAPI = new ConsumoAPI();
        ConvierteDatos conversor = new ConvierteDatos();

        String url = "https://gutendex.com/books/";
        String json = consumoAPI.obtenerDatos(url);

        var respuesta = conversor.obtenerDatos(json, RespuestaGutendex.class);

        System.out.println("Total de libros registrados: " + respuesta.count());

        System.out.println("\n TOP 10 LIBROS MÁS DESCARGADOS\n");

        respuesta.results().stream()
                .sorted((l1, l2) -> l2.descargas().compareTo(l1.descargas()))
                .limit(10)
                .forEach(libro ->
                        System.out.println(
                                libro.title() + " -> " + libro.descargas() + " descargas"
                        )
                );
        System.out.println("\n ESTADÍSTICAS DE DESCARGAS\n");

        IntSummaryStatistics estadisticas = respuesta.results().stream()
                .filter(libro -> libro.descargas() != null)
                .mapToInt(DatosLibro::descargas)
                .summaryStatistics();

        System.out.println("Promedio de descargas: " + (int) estadisticas.getAverage());
        System.out.println("Máximo de descargas: " + estadisticas.getMax());
        System.out.println("Mínimo de descargas: " + estadisticas.getMin());
        System.out.println("Total de libros analizados: " + estadisticas.getCount());

        Scanner teclado = new Scanner(System.in);

        System.out.println("\n Ingresa el nombre del libro que deseas buscar:");
        String busqueda = normalizarTexto(teclado.nextLine());
        String[] palabras = busqueda.split("\\s+");

        respuesta.results().stream()
                .filter(libro -> {
                    String tituloNormalizado = normalizarTexto(libro.title());
                    for (String palabra : palabras) {
                        if (tituloNormalizado.contains(palabra)) {
                            return true;
                        }
                    }
                    return false;
                })
                .findFirst()
                .ifPresentOrElse(
                        libro -> {
                            System.out.println("\n LIBRO ENCONTRADO\n");
                            System.out.println("Título: " + libro.title());
                            libro.authors().forEach(author ->
                                    System.out.println("Autor: " + author.name() +
                                    " (" + author.nacimiento() + " - " + author.fallecimiento() + ")")
                        );
                            System.out.println("Idiomas: " + libro.languages());
                            System.out.println("Descargas:  " + libro.descargas());
                        },
                        () -> System.out.println("Libro no encontrado")
                );

    }

    private String normalizarTexto(String texto) {
        return Normalizer.normalize(texto.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
