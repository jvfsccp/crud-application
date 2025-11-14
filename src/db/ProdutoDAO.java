package db;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public Produto create(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, quantidade, valor) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setBigDecimal(3, produto.getValor());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Criar produto falhou, nenhuma linha afetada.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao criar o produto, nenhum ID obtido.");
                }
            }
        }
        return produto;
    }

}
