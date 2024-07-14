package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.dto.AutoresDTO;
import com.aluracursos.desafio.dto.LibrosAutorDTO;
import com.aluracursos.desafio.model.Autor;
import com.aluracursos.desafio.model.Idioma;
import com.aluracursos.desafio.model.Libro;
import com.aluracursos.desafio.dto.LibroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String nombre);

    @Query("SELECT new com.aluracursos.desafio.dto.LibroDTO(l.titulo, l.numeroDeDescargas, " +
            "CASE WHEN l.idiomas = 'ESPANOL' THEN 'Español' " +
            "WHEN l.idiomas = 'INGLES' THEN 'Inglés' " +
            "WHEN l.idiomas = 'FRANCES' THEN 'Francés' " +
            "WHEN l.idiomas = 'PORTUGUES' THEN 'Portugués' " +
            "ELSE 'Desconocido' END, a.nombre) " +
            "FROM Libro l JOIN l.autor a")
    List<LibroDTO> librosRegistrados();

    @Query("SELECT new com.aluracursos.desafio.dto.AutoresDTO(a.nombre, a.fechaDeNacimiento, a.fechaDeMuerte, " +
            "l.titulo) FROM Libro l JOIN l.autor a")
    List<AutoresDTO> findAutoresAndTitulos();

    @Query("SELECT new com.aluracursos.desafio.dto.LibrosAutorDTO(l.titulo, l.numeroDeDescargas, " +
                  "CASE WHEN l.idiomas = 'ESPANOL' THEN 'Español' " +
                  "WHEN l.idiomas = 'INGLES' THEN 'Inglés' " +
                  "WHEN l.idiomas = 'FRANCES' THEN 'Francés' " +
                  "WHEN l.idiomas = 'PORTUGUES' THEN 'Portugués' " +
                  "ELSE 'Desconocido' END, a.nombre) " +
                  "FROM Libro l JOIN l.autor a WHERE a.nombre = :nombreAutor")
    List<LibrosAutorDTO> findLibrosByAutor(@Param("nombreAutor") String nombreAutor);

    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.fechaDeNacimiento <= :anioBusqueda AND a.fechaDeMuerte >= :anioBusqueda")
    Optional<List<Autor>> findAutoresVivos(String anioBusqueda);

    @Query("SELECT l FROM Libro l WHERE LOWER(l.idiomas) = LOWER(:idioma)")
    List<Libro> findByIdioma(@Param("idioma") String idioma);




}
