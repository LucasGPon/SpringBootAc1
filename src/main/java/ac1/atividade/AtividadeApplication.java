package ac1.atividade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ac1.atividade.models.CategoriaProduto;
import ac1.atividade.models.Produto;
import ac1.atividade.repository.CategoriaProdutoRepository;
import ac1.atividade.repository.ProdutoRepository;

@SpringBootApplication
public class AtividadeApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository produtoRepository,
			@Autowired CategoriaProdutoRepository categoriaProdutoRepository) {
		return args -> {
			produtoRepository.inserir(
					new Produto(1, "macarrão", 3500));
			produtoRepository.inserir(
					new Produto(2, "café", 1500));
			List<Produto> listaProdutos = produtoRepository.obterTodos();
			listaProdutos.forEach(System.out::println);

			System.out.println("** Excluindo por ID **");
			produtoRepository.excluir(1);
			listaProdutos.forEach(System.out::println);

			System.out.println("** Exemplo obter por ID **");
			Produto produto = produtoRepository.obterPorId("caf").get(0);
			System.out.println(produto);

			System.out.println("** Exemplo inserir categoria **");
			CategoriaProduto c1 = new CategoriaProduto(1, "Mercearia","Produtos Industrializados");
			categoriaProdutoRepository.inserir(c1);

			System.out.println("** Exemplo atualiza categ. curso **");
			listaProdutos.get(1).setCategoriaProduto(c1);
			produtoRepository.inserir(listaProdutos.get(1));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AtividadeApplication.class, args);
	}

}
