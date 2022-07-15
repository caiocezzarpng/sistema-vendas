
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Cliente;
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
public class ClienteDAO {
    private Connection conexao;
    
    public ClienteDAO(){
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    // cadastrar clientes
    public void cadastrarClientes(Cliente obj){
        try {
            
            // 1 - Criando instrução SQL
            
            String sql = "INSERT INTO tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            // 2 - Preparando o SQL
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setString(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            
            // 3 - 
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro!" +erro);
        }
    }
    
    
    // Editando Clientes:
    public void alterarCliente(Cliente obj){
        try{
            // 1 - Instruções SQL
            String sql = "UPDATE tb_clientes SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?,"
                    + " cidade = ?, estado = ? WHERE id = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setString(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            stmt.setInt(14, obj.getId());
            
            // 2 - Executando 
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente Alterado com sucesso!");
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    
    // Excluindo Clientes:
    public void excluirCliente(Cliente obj){
        try{
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null,"Cliente Excluido com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    
    
    
    
    
    
    // Listando Clientes:
    public List<Cliente> listarClientes(){
        try{
            
            List<Cliente> lista = new ArrayList<>();
            
            // 1- Instrução SQL
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    public Cliente buscarCliente(String nome){
        try{
            String sql = "SELECT * FROM tb_clientes WHERE nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    
    public Cliente buscarClienteCPF(String cpf){
        try{
            String sql = "SELECT * FROM tb_clientes WHERE cpf = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    
    public List<Cliente> pesquisarNome(String nome){
        try {
            List<Cliente> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
