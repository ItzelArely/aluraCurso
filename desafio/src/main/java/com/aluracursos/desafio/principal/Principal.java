package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.*;
import com.aluracursos.desafio.repository.LibroRepository;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;
import com.aluracursos.desafio.service.LibroService;
import com.aluracursos.desafio.utils.NormalizarTexto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private final LibroRepository repositorio;
    private final LibroService libroService;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;
    private List<Autor> autor;

    @Autowired
    public Principal(LibroService libroService, LibroRepository repositorio) {
        this.libroService = libroService;
        this.repositorio = repositorio;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    MENU DE OPCIONES
                    1 - Buscar Libro 
                    2 - Libros Registrados
                    3 - Autores Registrados
                    4 - Autores Vivos En Un Determinado Año
                    5 - Libros Por Idioma   
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresVivosDeterminadoAno();
                    break;
                case 5:
                    librosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {
        DatosLibros datosLibro = getDatosLibro();
        if (datosLibro != null) {
            Libro libro = new Libro(datosLibro);
            List<Autor> autorList = datosLibro.autor().stream()
                    .map(Autor::new)
                    .collect(Collectors.toList());
            libro.setAutor(autorList);
            repositorio.save(libro);
            System.out.println(libro);
        } else {
            System.out.println("No se pudo encontrar el libro.");
        }
    }

    private DatosLibros getDatosLibro() {
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscados = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscados.isPresent()) {
            System.out.println("Libro Encontrado");
            System.out.println(libroBuscados.get());
            return libroBuscados.get();
        } else {
            return null;
        }
    }

    private void librosRegistrados() {
        libroService.librosRegistrados();
    }

    private void autoresRegistrados() {
        libroService.autoresRegistrados();
    }

    private void autoresVivosDeterminadoAno() {
        System.out.println("Ingrese el año que desea buscar");
        var anioBusqueda = teclado.nextLine();
        var busResultado =repositorio.findAutoresVivos(anioBusqueda);
        if(busResultado.isPresent()){
            autor = busResultado.get();
            autor.forEach(a-> System.out.println(
                    "------- Autores --------" + "\n"+
                    "Autor: "+ a.getNombre() + "\n"+
                    "Fecha de nacimiento: "+ a.getFechaDeNacimiento() + "\n"+
                    "Fecha de muerte: "+ a.getFechaDeMuerte()+"\n"+
                    "-------------------------"));
        }else{
            System.out.println("No hay autores registrados en ese año");
        }
    }
    private void librosPorIdioma() {
        System.out.println("""
            Escriba el idioma que desea ver
            Español
            Inglés
            Frances
            Portugués
        """);
        var idiomas = teclado.nextLine();
        var idiomaNormalizado = NormalizarTexto.normalize(idiomas);
        var libros = repositorio.findByIdioma(idiomaNormalizado);

        if(libros.isEmpty()){
            System.out.println("No se encontraron libros");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getNumeroDeDescargas).reversed())
                    .forEach(l-> System.out.println(
                            "------- Libro --------" + "\n"+
                                    "Titulo: "+ l.getTitulo() + "\n"+
                                    "Idioma: "+ idiomas + "\n"+
                                    "Descargas: "+ l.getNumeroDeDescargas()+"\n"+
                                    "-------------------------"));
        }
    }


}
