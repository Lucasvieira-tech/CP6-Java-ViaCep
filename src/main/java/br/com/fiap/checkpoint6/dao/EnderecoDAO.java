
package br.com.fiap.checkpoint6.dao;

import br.com.fiap.checkpoint6.model.entity.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    public void cadastrar(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (cep, logradouro, bairro, localidade, uf, regiao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, endereco.getCep());
            comando.setString(2, endereco.getLogradouro());
            comando.setString(3, endereco.getBairro());
            comando.setString(4, endereco.getCidade());
            comando.setString(5, endereco.getUf());
            comando.setString(6, endereco.getRegiao() == null ? null : endereco.getRegiao().name());

            comando.executeUpdate();
        }
    }

    public List<Endereco> listar() throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(resultado.getLong("id"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCidade(resultado.getString("localidade"));
                endereco.setUf(resultado.getString("uf"));

                String regiao = resultado.getString("regiao");
                if (regiao != null) {
                    endereco.setRegiao(
                            br.com.fiap.checkpoint6.enums.RegiaoEnum.valueOf(regiao)
                    );
                }

                enderecos.add(endereco);
            }
        }

        return enderecos;
    }

    public Endereco buscarPorPK(long id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setLong(1, id);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(resultado.getLong("id"));
                    endereco.setCep(resultado.getString("cep"));
                    endereco.setLogradouro(resultado.getString("logradouro"));
                    endereco.setBairro(resultado.getString("bairro"));
                    endereco.setCidade(resultado.getString("localidade"));
                    endereco.setUf(resultado.getString("uf"));

                    String regiao = resultado.getString("regiao");
                    if (regiao != null) {
                        endereco.setRegiao(
                                br.com.fiap.checkpoint6.enums.RegiaoEnum.valueOf(regiao)
                        );
                    }

                    return endereco;
                }
            }
        }

        return null;
    }

    public List<Endereco> buscarPorCampoEscolhido(String campo, String valor) throws SQLException {
        if (!campo.equals("cep") && !campo.equals("uf") && !campo.equals("bairro")) {
            throw new IllegalArgumentException("Campo de busca inválido");
        }

        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco WHERE " + campo + " = ?";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, valor);

            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(resultado.getLong("id"));
                    endereco.setCep(resultado.getString("cep"));
                    endereco.setLogradouro(resultado.getString("logradouro"));
                    endereco.setBairro(resultado.getString("bairro"));
                    endereco.setCidade(resultado.getString("localidade"));
                    endereco.setUf(resultado.getString("uf"));

                    String regiao = resultado.getString("regiao");
                    if (regiao != null) {
                        endereco.setRegiao(
                                br.com.fiap.checkpoint6.enums.RegiaoEnum.valueOf(regiao)
                        );
                    }

                    enderecos.add(endereco);
                }
            }
        }

        return enderecos;
    }
}
