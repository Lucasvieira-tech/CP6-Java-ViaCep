package br.com.fiap.checkpoint6.controller;

import br.com.fiap.checkpoint6.exceptions.CepNaoEncontradoException;
import br.com.fiap.checkpoint6.model.entity.Endereco;
import br.com.fiap.checkpoint6.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private EnderecoService service = new EnderecoService();

    // GET /enderecos/01001000 -> 200 com o endereço, 400 se o CEP for inválido, 404 se não existir
    @GetMapping("/{cep}")
    public ResponseEntity<?> consultar(@PathVariable String cep) {
        try {
            Endereco endereco = service.consultar(cep);
            return ResponseEntity.ok(endereco);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CepNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
