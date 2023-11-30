package database;

import connection.ConnectionFactory;
import entities.Comprador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompradorDB {
    public void save(Comprador comprador) {
        String sql = "";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            System.out.println(stmt.executeUpdate(sql));
            ConnectionFactory.close(conn, stmt);
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Comprador comprador) {
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Não foi possivel excluir o registro");
            return;
        }
        String sql = "DELETE FROM 'agencia'.'comprador' WHERE 'id' = "+comprador.getId()+"";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            System.out.println(stmt.executeUpdate(sql));
            ConnectionFactory.close(conn, stmt);
            System.out.println("Registro excluido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Comprador comprador){
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Não foi possivel atualizar o registro");
            return;
        }
        String sql = "UPDATE 'agencia'.'comprador' SET " +
                "'cpf'="+comprador.getCpf()+", " +
                "'nome'="+comprador.getNome()+"" +
                "WHERE 'id'="+comprador.getId()+"";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            System.out.println(stmt.executeUpdate(sql));
            ConnectionFactory.close(conn, stmt);
            System.out.println("Registro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Comprador> selectAll(){
        String sql = "SELECT id, nome, cpf FROM 'agencia'.'comprador'";
        Connection conn = ConnectionFactory.getConexao();
        List<Comprador> compradorList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                compradorList.add(new Comprador(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome")));
            }
            ConnectionFactory.close(conn, stmt, rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Comprador> searchByName(String nome){
        String sql = "SELECT id, nome, cpf FROM 'agencia'.'comprador' WHERE nome LIKE '%"+nome+"%";
        Connection conn = ConnectionFactory.getConexao();
        List<Comprador> compradorList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                compradorList.add(new Comprador(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome")));
            }
            ConnectionFactory.close(conn, stmt, rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void selectMetaData(){
        String sql = "";
        Connection conn = ConnectionFactory.getConexao();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmt = rs.getMetaData();
            rs.next();
            int qtdColunas = rsmt.getColumnCount();
            System.out.println("Quantidades de colunas: " + qtdColunas);
            for (int i = 1; i <= qtdColunas; i++){
                System.out.println("Tabela: " + rsmt.getTableName(i));
                System.out.println("Nome coluna: " + rsmt.getColumnName(i));
                System.out.println("Tamanho coluna: " + rsmt.getColumnDisplaySize(i));
            }
            ConnectionFactory.close(conn, stmt, rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
