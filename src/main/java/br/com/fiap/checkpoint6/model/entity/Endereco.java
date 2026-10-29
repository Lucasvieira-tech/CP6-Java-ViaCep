package br.com.fiap.checkpoint6.model.entity;

import br.com.fiap.checkpoint6.enums.RegiaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Classe de modelagem: representa um endereço retornado pela ViaCEP. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private RegiaoEnum regiao;
}
