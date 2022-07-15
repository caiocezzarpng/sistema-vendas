
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author shiro
 */
public class VendaDAO {
    private Connection conexao;

    public VendaDAO() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    
    public void cadastrarVenda(Vendas obj){
            try {
                String sql = "INSERT INTO tb_vendas (cliente_id, data_venda, total_venda, observacoes) values (?,?,?,?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, obj.getCliente().getId());
                stmt.setString(2, obj.getData_venda());
                stmt.setDouble(3, obj.getTotal_venda());
                stmt.setString(4, obj.getObs());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }
    }
    
    
    public int retornaUltimaVenda(){
        try {
            int id_ultima_venda = 0;
            String sql = "SELECT MAX(id) id FROM tb_vendas";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas v = new Vendas();
                v.setId(rs.getInt("id"));
                id_ultima_venda = v.getId();
            
            }
            return id_ultima_venda;
                
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
