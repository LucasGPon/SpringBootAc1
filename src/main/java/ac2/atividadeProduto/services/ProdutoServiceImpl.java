package ac2.atividadeProduto.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ac2.atividadeProduto.dtos.CategoriaProdutoDTO;
import ac2.atividadeProduto.dtos.DadosProdutoDTO;
import ac2.atividadeProduto.dtos.ProdutoDTO;
import ac2.atividadeProduto.exceptions.RegraNegocioException;
import ac2.atividadeProduto.models.CategoriaProduto;
import ac2.atividadeProduto.models.Produto;
import ac2.atividadeProduto.repository.CategoriaProdutoRepository;
import ac2.atividadeProduto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    @Override
    @Transactional
    public Produto salvar(ProdutoDTO dto) {
        CategoriaProduto categ = categoriaProdutoRepository.findById(
                dto.getCategoriaProdutoId())
                .orElseThrow(() -> new RegraNegocioException("Código não encontrado."));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoriaProduto(categ);

        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        List<ProdutoDTO> produtos = produtoRepository.findAll().stream().map((Produto p) -> {
            return ProdutoDTO.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .preco(p.getPreco())
                    .categoriaProdutoId(p.getCategoriaProduto() != null ? p.getCategoriaProduto().getId() : null)
                    .build();

        }).collect(Collectors.toList());
        return produtos;
    }

    @Override
    public DadosProdutoDTO ObterPorId(Long id) {
        return produtoRepository.findById(id).map((Produto p) -> {
            return DadosProdutoDTO
                    .builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .preco(p.getPreco())
                    .categoria(CategoriaProdutoDTO.builder()
                            .id(p.getCategoriaProduto().getId())
                            .nome(p.getCategoriaProduto().getNome()).build())
                    .build();
        }).orElseThrow(() -> new RegraNegocioException("Produto não encontrado com o ID fornecido"));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public void editar(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código usuário não encontrado."));

        CategoriaProduto categoriaProduto = categoriaProdutoRepository.findById(dto.getCategoriaProdutoId())
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoriaProduto(categoriaProduto);
        produtoRepository.save(produto);
    }

    public List<ProdutoDTO> ObterTodos() {
        List<ProdutoDTO> produtos = produtoRepository.findAll().stream().map((Produto p) ->{
            return ProdutoDTO.builder()
            .id(p.getId())
            .nome(p.getNome())
            .preco(p.getPreco())
            .categoriaProdutoId(p.getCategoriaProduto() == null ? 0 : p.getCategoriaProduto().getId())
            .build();
        }).collect(Collectors.toList());
        return produtos;
    }
}