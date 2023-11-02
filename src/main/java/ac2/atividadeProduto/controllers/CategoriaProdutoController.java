package ac2.atividadeProduto.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ac2.atividadeProduto.dtos.CategoriaProdutoDTO;
import ac2.atividadeProduto.models.CategoriaProduto;
import ac2.atividadeProduto.services.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaProdutoController {
    private CategoriaService categoriaService;

    public CategoriaProdutoController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaProduto inserir(@RequestBody CategoriaProdutoDTO categoriaDTO) {
        return categoriaService.salvar(categoriaDTO);
    }

    @GetMapping
    public List<CategoriaProdutoDTO> listarTodos() {
        return categoriaService.listarTodos();
    }

    @GetMapping("{id}")
    public CategoriaProdutoDTO obterPorId(@PathVariable Long id) {
        return categoriaService.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoriaService.excluir(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody CategoriaProdutoDTO dto) {
        categoriaService.editar(id, dto);
    }
}
