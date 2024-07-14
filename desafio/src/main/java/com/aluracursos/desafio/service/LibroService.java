package com.aluracursos.desafio.service;

import com.aluracursos.desafio.dto.AutoresDTO;
import com.aluracursos.desafio.dto.LibroDTO;
import com.aluracursos.desafio.dto.LibrosAutorDTO;
import com.aluracursos.desafio.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void librosRegistrados() {
        List<LibroDTO> libros = libroRepository.librosRegistrados();
        libros.stream()
                .sorted(Comparator.comparing(LibroDTO::getNumeroDeDescargas).reversed())
                .forEach(System.out::println);
    }
    public void autoresRegistrados() {
        List<AutoresDTO> autores = libroRepository.findAutoresAndTitulos();

        for (AutoresDTO autor : autores) {
            List<LibrosAutorDTO> libros = libroRepository.findLibrosByAutor(autor.getNombre());
            autor.setLibros(libros);
        }

        autores.forEach(System.out::println);
    }

    public List<LibrosAutorDTO> findLibrosByAutor(String nombreAutor) {
        return libroRepository.findLibrosByAutor(nombreAutor);
    }

}
