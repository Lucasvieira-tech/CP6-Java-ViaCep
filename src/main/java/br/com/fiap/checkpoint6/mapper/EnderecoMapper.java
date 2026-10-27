package br.com.fiap.checkpoint6.mapper;

import br.com.fiap.checkpoint6.dto.ViaCepDTO;
import br.com.fiap.checkpoint6.enums.RegiaoEnum;
import br.com.fiap.checkpoint6.model.entity.Endereco;

public class EnderecoMapper {

    /** Converte o DTO da ViaCEP em Entity e descobre a região pela UF. */
    public Endereco toEntity(ViaCepDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep() == null ? null : dto.cep().replaceAll("\\D", ""));
        endereco.setLogradouro(dto.logradouro());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.localidade());
        endereco.setUf(dto.uf());
        endereco.setRegiao(RegiaoEnum.fromUf(dto.uf()));
        return endereco;
    }
}
