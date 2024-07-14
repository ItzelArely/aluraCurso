package com.aluracursos.desafio.dto;
import java.time.LocalDate;
import java.util.List;

public class AutoresDTO {
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    private String titulo;
    private List<LibrosAutorDTO> libros;


    public AutoresDTO(String nombre, String fechaDeNacimiento, String fechaDeMuerte, String titulo) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeMuerte = fechaDeMuerte;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<LibrosAutorDTO> getLibros() {
        return libros;
    }

    public void setLibros(List<LibrosAutorDTO> libros) {
        this.libros = libros;
    }

    public String toString() {
        return "--------- Autores --------- \n"  +
                "Nombre del Autor: " + nombre + '\n' +
                "Fecha de Nacimiento: " + fechaDeNacimiento + '\n' +
                "Fecha de Muerte: " + fechaDeMuerte + '\n' +
                "Libros: " + libros + '\n' +
                "------------------------";
    }



}
