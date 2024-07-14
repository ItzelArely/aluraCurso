package com.aluracursos.desafio.utils;

import java.text.Normalizer;
import java.util.Locale;

public class NormalizarTexto {
    public static String normalize(String input) {
        if (input == null) return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);
    }
}
