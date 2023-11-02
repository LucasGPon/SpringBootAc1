package ac2.atividadeProduto.services;

import java.util.List;

import ac2.atividadeProduto.dtos.DadosProdutoDTO;
import ac2.atividadeProduto.dtos.ProdutoDTO;
import ac2.atividadeProduto.models.Produto;

public interface ProdutoService {
    Produto salvar(ProdutoDTO dto);

    DadosProdutoDTO ObterPorId(Long id);

    void excluir(Long id);

    void editar(Long id, ProdutoDTO dto);

    List<ProdutoDTO> ObterTodos();
}
