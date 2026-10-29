
package br.com.fiap.checkpoint6.controller;

import br.com.fiap.checkpoint6.dao.EnderecoDAO;
import br.com.fiap.checkpoint6.exceptions.CepNaoEncontradoException;
import br.com.fiap.checkpoint6.model.entity.Endereco;
import br.com.fiap.checkpoint6.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service = new EnderecoService();
    private final EnderecoDAO dao = new EnderecoDAO();

    // Consulta um CEP na API ViaCEP
    @GetMapping("/{cep}")
    public ResponseEntity<?> consultar(@PathVariable String cep) {
        try {
            Endereco endereco = service.consultar(cep);
            return ResponseEntity.ok(endereco);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CepNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Lista os endereços salvos no Oracle
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(dao.listar());
        } catch (SQLException e) {
            return ResponseEntity.status(500)
                    .body("Erro ao listar endereços.");
        }
    }

    // Cadastra um endereço no Oracle
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Endereco endereco) {
        try {
            dao.cadastrar(endereco);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Endereço cadastrado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(500)
                    .body("Erro ao cadastrar endereço.");
        }
    }

    // Busca um endereço pelo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorPK(@PathVariable long id) {
        try {
            Endereco endereco = dao.buscarPorPK(id);

            if (endereco == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(endereco);
        } catch (SQLException e) {
            return ResponseEntity.status(500)
                    .body("Erro ao buscar endereço.");
        }
    }

    // Busca endereços por CEP, UF ou bairro
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorCampo(
            @RequestParam String campo,
            @RequestParam String valor) {
        try {
            List<Endereco> enderecos =
                    dao.buscarPorCampoEscolhido(campo, valor);
            return ResponseEntity.ok(enderecos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (SQLException e) {
            return ResponseEntity.status(500)
                    .body("Erro ao buscar endereços.");
        }
    }
}

