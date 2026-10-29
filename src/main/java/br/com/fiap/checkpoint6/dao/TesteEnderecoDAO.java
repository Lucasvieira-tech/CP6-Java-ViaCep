package br.com.fiap.checkpoint6.dao;

import br.com.fiap.checkpoint6.model.entity.Endereco;

public class TesteEnderecoDAO {
    public static void main(String[] args) {
        try {
            EnderecoDAO dao = new EnderecoDAO();

            Endereco endereco = new Endereco();
            endereco.setCep("01001000");
            endereco.setLogradouro("Praça da Sé");
            endereco.setBairro("Sé");
            endereco.setCidade("São Paulo");
            endereco.setUf("SP");

            dao.cadastrar(endereco);

            System.out.println("Endereço cadastrado com sucesso!");

            System.out.println("Total de endereços: " + dao.listar().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}