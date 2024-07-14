package com.aluracursos.desafio.dto;

public class LibrosAutorDTO {
    private String titulo;
    private Double numeroDeDescargas;
    private String idiomas;
    private String autorNombre;

    public LibrosAutorDTO(String titulo, Double numeroDeDescargas, String idiomas, String autorNombre) {
        this.titulo = titulo;
        this.numeroDeDescargas = numeroDeDescargas;
        this.idiomas = idiomas;
        this.autorNombre = autorNombre;
    }

    @Override
    public String toString() {
        return "\n" + titulo  ;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }
}
