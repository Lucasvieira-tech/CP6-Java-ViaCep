package br.com.fiap.checkpoint6.enums;

public enum RegiaoEnum {
    NORTE,
    NORDESTE,
    CENTRO_OESTE,
    SUDESTE,
    SUL;

    /** Descobre a região do Brasil a partir da sigla do estado (UF). */
    public static RegiaoEnum fromUf(String uf) {
        if (uf == null) {
            return null;
        }
        return switch (uf.toUpperCase()) {
            case "AC", "AP", "AM", "PA", "RO", "RR", "TO" -> NORTE;
            case "AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE" -> NORDESTE;
            case "DF", "GO", "MT", "MS" -> CENTRO_OESTE;
            case "ES", "MG", "RJ", "SP" -> SUDESTE;
            case "PR", "RS", "SC" -> SUL;
            default -> null;
        };
    }
}
