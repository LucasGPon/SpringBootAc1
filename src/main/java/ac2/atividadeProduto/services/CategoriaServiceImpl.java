package ac2.atividadeProduto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ac2.atividadeProduto.dtos.CategoriaProdutoDTO;
import ac2.atividadeProduto.exceptions.RegraNegocioException;
import ac2.atividadeProduto.models.CategoriaProduto;
import ac2.atividadeProduto.repository.CategoriaProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProduto salvar(CategoriaProdutoDTO categoriaProdutoDTO) {
        CategoriaProduto c = new CategoriaProduto();
        c.setNome(categoriaProdutoDTO.getNome());
        return categoriaProdutoRepository.save(c);
    }

    @Override
    public void excluir(Long id) {
        categoriaProdutoRepository.deleteById(id);
    }

    @Override
    public List<CategoriaProdutoDTO> listarTodos() {
        List<CategoriaProdutoDTO> categorias = categoriaProdutoRepository.findAll().stream().map(
                (CategoriaProduto c) -> {
                    return CategoriaProdutoDTO.builder()
                            .id(c.getId())
                            .nome(c.getNome())
                            .build();
                }).collect(Collectors.toList());
        return categorias;
    }

    @Override
    public void editar(Long id, CategoriaProdutoDTO dto) {
        CategoriaProduto categoria = categoriaProdutoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

        categoria.setNome(dto.getNome());
        categoriaProdutoRepository.save(categoria);
    }

    @Override
    public CategoriaProdutoDTO obterPorId(Long id) {
        return categoriaProdutoRepository.findById(id).map((CategoriaProduto c) -> {
            return CategoriaProdutoDTO.builder()
                    .id(c.getId())
                    .nome(c.getNome())
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Id da categoria não encontrado."));
    }

}
