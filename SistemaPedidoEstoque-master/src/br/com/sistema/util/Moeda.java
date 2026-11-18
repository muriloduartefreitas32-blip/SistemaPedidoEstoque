package br.com.sistema.util;

import java.util.Locale;

public final class Moeda {
    private Moeda(){

    }
    public static String formatar(double valor){
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", valor);
    }

}
