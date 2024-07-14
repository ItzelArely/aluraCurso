package com.aluracursos.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    @Column(name = "fecha_de_nacimiento")
    private String fechaDeNacimiento;
    @Column(name = "fecha_de_muerte")
    private String fechaDeMuerte;
    @ManyToOne
    private Libro libro;
    public Autor(){}
    public Autor(DatosAutor a){
        this.nombre = a.nombre();
        this.fechaDeNacimiento = a.fechaDeNacimiento();
        this.fechaDeMuerte = a.fechaDeMuerte();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
