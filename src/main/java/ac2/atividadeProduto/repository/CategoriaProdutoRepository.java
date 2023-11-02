package ac2.atividadeProduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ac2.atividadeProduto.models.CategoriaProduto;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
    List<CategoriaProduto> findByNome(String nome);

    @Query("select cc from CategoriaProduto cc left join fetch cc.produtos c where cc.id = :id ")
    CategoriaProduto findCategoriaProdutoFetchProduto(@Param("id") Long id);
}
