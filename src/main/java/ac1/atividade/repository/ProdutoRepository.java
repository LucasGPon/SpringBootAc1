package ac1.atividade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ac1.atividade.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeStartingWith(String nome);
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThanEqual(Double preco);

}
