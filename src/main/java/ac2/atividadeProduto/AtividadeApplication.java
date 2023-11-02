package ac2.atividadeProduto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ac2.atividadeProduto.models.CategoriaProduto;
import ac2.atividadeProduto.models.Produto;
import ac2.atividadeProduto.repository.CategoriaProdutoRepository;
import ac2.atividadeProduto.repository.ProdutoRepository;

@SpringBootApplication
public class AtividadeApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository produtoRepository, CategoriaProdutoRepository categoriaProdutoRepository) {
		return args -> {

			System.out.println("*** INSERINDO PRODUTO ***");
			Produto c1 = new Produto((long) 0, "Notebook", 2000.0 );
			Produto c2 = new Produto((long) 0, "Sabão", 21.0);
			Produto c3 = new Produto((long) 0, "Televisão", 710.0);
			c1 = produtoRepository.save(c1);
			c2 = produtoRepository.save(c2);
			c3 = produtoRepository.save(c3);

			System.out.println("*** INSERINDO CATEGORIA ***");
			CategoriaProduto eletro = new CategoriaProduto((long) 0, "Eletro");
			CategoriaProduto basar = new CategoriaProduto((long) 0, "Basar");
			eletro = categoriaProdutoRepository.save(eletro);
			basar = categoriaProdutoRepository.save(basar);


			System.out.println("*** EDITANDO PRODUTO ***");
			c1.setNome("Notebook Acer i5 ² Geração");
			c1.setCategoriaProduto(eletro);
			c2.setCategoriaProduto(basar);
			c1 = produtoRepository.save(c1);
			c2 = produtoRepository.save(c2);

			System.out.println("*** LISTAR TODOS PRODUTOS ***");
			List<Produto> produtos = produtoRepository.findAll();
			for (Produto p : produtos) {
				System.out.println(p.getNome());
			}

			// System.out.println("*** EXCLUIR PRODUTO ***");
			// produtoRepository.delete(c2);
			// produtoRepository.deleteById((long) 1);

			System.out.println("*** LISTAR POR INICIAL NOME ***");
			List<Produto> produtos1 = produtoRepository.findByNomeStartingWith("N");
			for (Produto p : produtos1) {
				System.out.println(p.getNome());
			}

			System.out.println("*** LISTAR PREÇO MAIOR ***");
			List<Produto> produtos2 = produtoRepository.findByPrecoGreaterThan(1.0);
			for (Produto p : produtos2) {
				System.out.println(p.getNome());
			}

			System.out.println("*** LISTAR PREÇO MENOR IGUAL ***");
			List<Produto> produtos3 = produtoRepository.findByPrecoLessThanEqual(30.0);
			for (Produto p : produtos3) {
				System.out.println(p.getNome());
			}

			System.out.println("*** LISTAR CATEGORIA POR NOME ***");
			List<CategoriaProduto> categoria = categoriaProdutoRepository.findByNome("Eletro");
			for (CategoriaProduto c : categoria) {
				System.out.println(c.getNome());
			}

			// System.out.println("*** LISTAR PRODUTOS POR ID CATEGORIA ***");
			// List<CategoriaProduto> categs = categoriaProdutoRepository.findAll();
			// for (CategoriaProduto ca : categs) {
			// System.out.println(ca.getId() + " - " + ca.getCategoria() + "qtde produtos: " +
			// ca.getProdutos().size());
			// }
			// CategoriaProduto cc = categoriaProdutoRepository.findCategoriaProdutoFetchProduto((long) 1);
			// System.out.println(cc.getProdutos().size());

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AtividadeApplication.class, args);
	}

}
