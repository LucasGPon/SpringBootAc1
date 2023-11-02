package ac2.atividadeProduto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private CategoriaProdutoDTO categoria;
}
