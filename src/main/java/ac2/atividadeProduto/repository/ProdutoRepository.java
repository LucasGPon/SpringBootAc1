package ac2.atividadeProduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ac2.atividadeProduto.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeStartingWith(String nome);
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThanEqual(Double preco);

}
