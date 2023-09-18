package ac1.atividade.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ac1.atividade.models.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto inserir(Produto produto) {
        entityManager.merge(produto);
        return produto;
    }

    @Transactional
    public void excluir(Produto produto) {
        entityManager.remove(produto);
    }

    @Transactional
    public void excluir(int id) {
        excluir(entityManager.find(Produto.class, id));
    }

    public List<Produto> obterTodos() {
        return entityManager.createQuery("from Produto",
                Produto.class).getResultList();
    }

    public List<Produto> obterPorId(String prod_nome) {
        String jpql = "select * from tbl_produtos p where p.prod_nome like :prod_nome ";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("prod_nome", "%" + prod_nome + "%");
        return query.getResultList();
    }

}
