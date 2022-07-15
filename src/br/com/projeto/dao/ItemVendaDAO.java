
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.ItensVenda;
import br.com.projeto.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shiro
 */
public class ItemVendaDAO {
    private Connection conexao;
    
    public ItemVendaDAO(){
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    public void cadastrarItensProduto(ItensVenda obj){
        try {
            String sql = "INSERT INTO tb_itensvendas (venda_id, produto_id, qtd, subtotal) values(?,?,?,?)";  
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ItensVenda> listaItensVenda(int venda_id){
        try {
            
            List<ItensVenda> lista = new ArrayList<>();
            
            String sql = "SELECT p.descricao, i.qtd, p.preco, i.subtotal FROM tb_itensvendas AS i INNER JOIN tb_produtos ON(i.produto_id = p.id) WHERE i.venda_id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, venda_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ItensVenda item = new ItensVenda();
                Produto p = new Produto();
                p.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                p.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                item.setProduto(p);
                
                lista.add(item);
                
            }
            return lista;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
