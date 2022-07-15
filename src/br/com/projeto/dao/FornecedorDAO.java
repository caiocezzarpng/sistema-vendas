
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author shiro
 */
public class FornecedorDAO {
    private Connection conexao;

    public FornecedorDAO() {
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    // cadastrar clientes
    public void cadastrarFornecedor(Fornecedor obj){
        try {
            
            // 1 - Criando instrução SQL
            
            String sql = "INSERT INTO tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            // 2 - Preparando o SQL
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            
            // 3 - 
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro!" +erro);
        }
    }
    
    
    // Editando Clientes:
    public void alterarFornecedor(Fornecedor obj){
        try{
            // 1 - Instruções SQL
            String sql = "UPDATE tb_fornecedores SET nome = ?, cnpj = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?,"
                    + " cidade = ?, estado = ? WHERE id = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());
            
            // 2 - Executando 
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedor Alterado com sucesso!");
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    
    // Excluindo Clientes:
    public void excluirFornecedor(Fornecedor obj){
        try{
            String sql = "DELETE FROM tb_fornecedores WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null,"Fornecedor Excluido com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    
    
    
    
    
    
    // Listando Clientes:
    public List<Fornecedor> listarFornecedores(){
        try{
            
            List<Fornecedor> lista = new ArrayList<>();
            
            // 1- Instrução SQL
            String sql = "SELECT * FROM tb_fornecedores";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            return lista;
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
        return null;
    }
    
    public Fornecedor buscarFornecedor(String nome){
        try{
            String sql = "SELECT * FROM tb_fornecedores WHERE nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
            }
            return obj;
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
        return null;
    }
    
    
    public List<Fornecedor> pesquisarNome(String nome){
        try {
            List<Fornecedor> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO: " +e);
        }
        return null;
    }
}
