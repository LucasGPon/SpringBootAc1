package ac2.atividadeProduto.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ac2.atividadeProduto.dtos.DadosProdutoDTO;
import ac2.atividadeProduto.dtos.ProdutoDTO;
import ac2.atividadeProduto.models.Produto;
import ac2.atividadeProduto.services.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody ProdutoDTO dto) {
        Produto produto = produtoService.salvar(dto);
        return produto;
    }

    @GetMapping("{id}")
    public DadosProdutoDTO getById(@PathVariable Long id) {
        return produtoService.ObterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        produtoService.excluir(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        produtoService.editar(id, dto);
    }

    @GetMapping
    public List<ProdutoDTO> obterTodos() {
        return produtoService.ObterTodos();
    }
}
