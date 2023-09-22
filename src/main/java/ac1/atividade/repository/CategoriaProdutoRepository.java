package ac1.atividade.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ac1.atividade.models.CategoriaProduto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public CategoriaProduto inserir(CategoriaProduto categoriaProduto) {
        entityManager.merge(categoriaProduto);
        return categoriaProduto;
    }

    public List<CategoriaProduto> obterTodos() {
        return entityManager
                .createQuery("from CategoriaProduto",
                        CategoriaProduto.class)
                .getResultList();
    }
}
