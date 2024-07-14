package com.aluracursos.desafio.dto;

public class LibroDTO {
    private String titulo;
    private Double numeroDeDescargas;
    private String idiomas;
    private String autorNombre;

    public LibroDTO(String titulo, Double numeroDeDescargas, String idiomas, String autorNombre) {
        this.titulo = titulo;
        this.numeroDeDescargas = numeroDeDescargas;
        this.idiomas = idiomas;
        this.autorNombre = autorNombre;
    }


    @Override
    public String toString() {
        return "--------- Libro --------- \n"  +
                "Titulo: " + titulo + '\n' +
                "Descargas: " + numeroDeDescargas + '\n' +
                "Idiomas: " + idiomas + '\n' +
                "Nombre del Autor: " + autorNombre + '\n' +
                "------------------------";
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
