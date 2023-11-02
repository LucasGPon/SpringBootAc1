package ac2.atividadeProduto.services;

import java.util.List;

import ac2.atividadeProduto.dtos.CategoriaProdutoDTO;
import ac2.atividadeProduto.models.CategoriaProduto;

public interface CategoriaService {
    CategoriaProduto salvar(CategoriaProdutoDTO categoriaProdutoDTO);

    void excluir(Long id);

    void editar(Long id, CategoriaProdutoDTO dto);

    List<CategoriaProdutoDTO> listarTodos();

    CategoriaProdutoDTO obterPorId(Long id);
}
