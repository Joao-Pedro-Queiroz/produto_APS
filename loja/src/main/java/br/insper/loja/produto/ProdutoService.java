package br.insper.loja.produto;

import br.insper.loja.usuario.Usuario;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

    public Produto getProduto(String id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate
                    .getForEntity("http://localhost:8082/api/produto/" + id,
                            Produto.class)
                    .getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public RetornarProdutoDTO diminuirEstoque(String id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<RetornarProdutoDTO> response = restTemplate.exchange(
                    "http://localhost:8082/api/produto/" + id,
                    HttpMethod.PUT,
                    null,  // Corpo da requisição (se necessário, pode ser um HttpEntity)
                    RetornarProdutoDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

}

