package com.aluracursos.desafio.model;

public enum Idioma {

    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");

    public String nombreIdioma;

    Idioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public static Idioma fromString(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Nombre de idioma vacío o nulo");
        }
        for (Idioma idioma : Idioma.values()) {
            if (idioma.nombreIdioma.equalsIgnoreCase(text.replace("[","").replace("]",""))) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado para el nombre: " + text);
    }

    @Override
    public String toString() {
        return nombreIdioma;
    }
}
