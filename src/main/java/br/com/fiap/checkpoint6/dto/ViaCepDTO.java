package br.com.fiap.checkpoint6.dto;

/**
 * Espelha o JSON retornado pela ViaCEP.
 * Campos que não estão declarados aqui (complemento, ibge, ddd...) são ignorados.
 * Quando o CEP não existe, a API devolve {"erro": "true"}.
 */
public record ViaCepDTO(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        String erro) {
}
