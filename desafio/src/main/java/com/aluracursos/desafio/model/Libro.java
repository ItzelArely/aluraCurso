package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @Enumerated(EnumType.STRING)
    private Idioma idiomas;
    private Double numeroDeDescargas;
    public Libro(){}
    public Libro(DatosLibros datosLibros) {
        if (datosLibros == null) {
            throw new IllegalArgumentException("DatosLibros no puede ser null");
        }
        this.titulo = datosLibros.titulo();


        if (datosLibros.idiomas() != null && !datosLibros.idiomas().isEmpty()) {
            this.idiomas = Idioma.fromString(datosLibros.idiomas().get(0));
        } else {
            throw new IllegalArgumentException("Lista de idiomas vacía o nula");
        }

        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(a->a.setLibro(this));
        this.autor = autor;
    }

    public Idioma getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas ;
    }


}
