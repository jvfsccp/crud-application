import db.ProdutoDAO;
import model.Produto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


public class Principal {
    public static void main(String[] args){
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();

            System.out.println("--- 1. Criando um novo produto ---");
            Produto novoProduto = new Produto();
            novoProduto.setNome("Notebook Gamer");
            novoProduto.setQuantidade(10);
            novoProduto.setValor(new BigDecimal("7500.00"));

            produtoDAO.create(novoProduto);
            System.out.println("Produto criado com ID: " + novoProduto.getId());
            System.out.println("----------------------------------\n");

            System.out.println("--- 2. Lendo todos os produtos ---");
            List<Produto> produtos = produtoDAO.readAll();
            for (Produto p : produtos) {
                System.out.println(p);
            }
            System.out.println("----------------------------------\n");

            System.out.println("--- 3. Atualizando o produto (ID: " + novoProduto.getId() + ") ---");
            novoProduto.setValor(new BigDecimal("7250.99"));
            novoProduto.setQuantidade(8);

            boolean atualizou = produtoDAO.update(novoProduto);
            if (atualizou) {
                System.out.println("Produto atualizado com sucesso.");
                System.out.println(novoProduto);
            } else {
                System.out.println("Falha ao atualizar o produto.");
            }

            System.out.println("----------------------------------\n");

            System.out.println("--- 4. Deletando o produto (ID: " + novoProduto.getId() + ") ---");
            boolean deletou = produtoDAO.delete(novoProduto.getId());
            if (deletou) {
                System.out.println("Produto deletado com sucesso.");
            } else {
                System.out.println("Falha ao deletar o produto.");
            }
            System.out.println("----------------------------------\n");


            System.out.println("--- 5. Lendo todos os produtos (após DELETE) ---");
            List<Produto> produtosFinais = produtoDAO.readAll();
            for (Produto p : produtosFinais) {
                System.out.println(p);
            }
            System.out.println("----------------------------------\n");
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro de SQL: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
