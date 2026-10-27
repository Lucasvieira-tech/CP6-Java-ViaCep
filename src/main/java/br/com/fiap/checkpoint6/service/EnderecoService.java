package br.com.fiap.checkpoint6.service;

import br.com.fiap.checkpoint6.dto.ViaCepDTO;
import br.com.fiap.checkpoint6.exceptions.CepNaoEncontradoException;
import br.com.fiap.checkpoint6.mapper.EnderecoMapper;
import br.com.fiap.checkpoint6.model.entity.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class EnderecoService {

    private EnderecoMapper mapper = new EnderecoMapper();
    private RestClient restClient = RestClient.create();

    /** Consulta o CEP na ViaCEP e converte o retorno em Endereco.
     * @throws IllegalArgumentException se o CEP não tiver 8 dígitos
     * @throws CepNaoEncontradoException se a ViaCEP não conhecer o CEP
     */
    public Endereco consultar(String cep) {
        String cepLimpo = cep == null ? "" : cep.replaceAll("\\D", "");
        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("O CEP deve ter 8 dígitos.");
        }
        return mapper.toEntity(consultarViaCep(cepLimpo));
    }

    /*
     * A API ViaCEP (https://viacep.com.br/ws/{cep}/json/) recebe um CEP de 8 dígitos
     * e retorna em JSON os dados do endereço correspondente.
     * Dados utilizados: cep, logradouro, bairro, localidade (cidade) e uf.
     * Se o CEP não existir, a API responde {"erro": "true"}.
     */
    private ViaCepDTO consultarViaCep(String cep) {
        ViaCepDTO dto;
        try {
            dto = restClient.get()
                    .uri("https://viacep.com.br/ws/{cep}/json/", cep)
                    .retrieve()
                    .body(ViaCepDTO.class);
        } catch (HttpClientErrorException e) {
            throw new CepNaoEncontradoException("CEP não encontrado na ViaCEP.");
        }

        if (dto == null || "true".equals(dto.erro())) {
            throw new CepNaoEncontradoException("CEP não encontrado na ViaCEP.");
        }
        return dto;
    }
}
