package br.insper.loja.compra;

import br.insper.loja.produto.Produto;
import br.insper.loja.produto.ProdutoService;
import br.insper.loja.usuario.Usuario;
import br.insper.loja.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;


    public Compra salvarCompra(Compra compra) {
        Usuario usuario = usuarioService.getUsuario(compra.getUsuario());

        for (String idProduto : compra.getProdutos()) {
            produtoService.getProduto(idProduto)
        }

        for (String idProduto : compra.getProdutos()) {
            produtoService.diminuirEstoque(idProduto)
        }


        compra.setNome(usuario.getNome());
        compra.setDataCompra(LocalDateTime.now());
        return compraRepository.save(compra);
    }

    public List<Compra> getCompras() {
        return compraRepository.findAll();
    }
}
