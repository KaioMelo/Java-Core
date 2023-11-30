package main.dao.impl;

import main.dao.CompradorDAO;
import main.entities.Comprador;
import main.configuration.ConfigDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompradorDAOImpl implements CompradorDAO {
    @Override
    public void save(Comprador comprador) {
        String sql = "INSERT INTO agencia.comprador (cpf, nome) VALUES (?, ?)";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, comprador.getCpf());
            ps.setString(2, comprador.getNome());
            ps.executeUpdate();
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Comprador comprador) {
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Não foi possivel excluir o registro");
            return;
        }
        String sql = "DELETE FROM agencia.comprador WHERE 'id' = ?";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, comprador.getId());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Comprador comprador) {
        if (comprador == null || comprador.getId() == null) {
            System.out.println("Não foi possivel atualizar o registro");
            return;
        }
        String sql = "UPDATE agencia.comprador SET cpf= ?, nome= ? WHERE id= ?";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, comprador.getCpf());
            ps.setString(2, comprador.getNome());
            ps.setInt(3, comprador.getId());
            ps.executeUpdate();
            System.out.println("Registro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comprador> selectAll() {
        String sql = "SELECT id, nome, cpf FROM agencia.comprador";
        List<Comprador> compradorList = new ArrayList<>();
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                compradorList.add(new Comprador(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome")));
            }
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comprador> searchByName(String nome) {
        String sql = "SELECT id, nome, cpf FROM agencia.comprador WHERE nome LIKE ?";
        List<Comprador> compradorList = new ArrayList<>();
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                compradorList.add(new Comprador(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome")));
            }
            ConfigDB.close(conn, ps, rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  Comprador searchById(Integer id) {
        String sql = "SELECT id, nome, cpf FROM agencia.comprador WHERE id = ?";
        Comprador comprador = null;
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                comprador = new Comprador(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome"));
            }
            ConfigDB.close(conn, ps, rs);
            return comprador;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
