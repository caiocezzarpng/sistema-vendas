/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Funcionario;
import br.com.projeto.view.FormMenu;
import br.com.projeto.view.Login;
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
public class FuncionarioDAO {
    private Connection conexao;
    
    public FuncionarioDAO(){
        this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    
    // cadastrar Funcionarios
    public void cadastrarFuncionario(Funcionario obj){
        try {
            
            // 1 - Criando instrução SQL
            
            String sql = "INSERT INTO tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            // 2 - Preparando o SQL
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            
            // 3 - 
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro!" +erro);
        }
    }
    
    
    public void alterarFuncionario(Funcionario obj){
        try {
            // 1- Criando instrução SQL
            
            String sql = "UPDATE tb_funcionarios SET nome = ?, rg = ?, cpf = ?, email = ?, senha = ?, cargo = ?, nivel_acesso = ?, telefone = ?, celular = ?, cep = ?,"
                    + "endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            stmt.setInt(17, obj.getId());
            
            // 2 - Executando 
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionario Alterado com sucesso!");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    
    public void excluirFuncionario(Funcionario obj){
        try{
            String sql = "DELETE FROM tb_funcionarios WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
                stmt.execute();
                stmt.close();
                JOptionPane.showMessageDialog(null,"Funcionario Excluido com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
    }
    
    public Funcionario buscarFuncionario(String nome){
        try{
            String sql = "SELECT * FROM tb_funcionarios WHERE nome = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionario obj = new Funcionario();
            while(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    public List<Funcionario> pesquisarNome(String nome){
        try {
            List<Funcionario> lista = new ArrayList<>();
            String sql = "SELECT * FROM tb_funcionarios WHERE nome LIKE ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    public List<Funcionario> listarFuncionarios(){
        try {
            
            List<Funcionario> lista = new ArrayList<>();
            
            // 1- Criando instrução SQL
            
            String sql = "SELECT * FROM tb_funcionarios";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            JOptionPane.showMessageDialog(null,"ERRO: "+e);
        }
        return null;
    }
    
    
    public void efetuarLogin(String email, String senha){
        try {
            String sql = "SELECT * FROM tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getString("nivel_acesso").equals("Administrador")){
                    FormMenu menu = new FormMenu();
                    menu.usuarioLogado = rs.getString("nome");
                    JOptionPane.showMessageDialog(null,"Seja bem-vindo\n"+menu.usuarioLogado);
                    menu.setVisible(true);
                }else if(rs.getString("nivel_acesso").equals("Usuario")){
                    FormMenu menu = new FormMenu();
                    menu.usuarioLogado = rs.getString("nome");
                    JOptionPane.showMessageDialog(null,"Seja bem-vindo \n"+menu.usuarioLogado);
                    menu.setVisible(true);
                } 
            }
            
            else {
                    Login login = new Login();
                    JOptionPane.showMessageDialog(null, "Dados inválidos!");
                    login.setVisible(true);
                }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
