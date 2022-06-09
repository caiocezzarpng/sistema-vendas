/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author shiro
 */
public class TestarConexao {
    public static void main(String [] args){
        try{
            new ConexaoBanco().pegarConexao();
            JOptionPane.showMessageDialog(null,"Conectado!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"erro: "+e);
        }
    }
}
