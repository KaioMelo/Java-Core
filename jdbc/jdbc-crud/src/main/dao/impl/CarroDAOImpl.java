package main.dao.impl;

import main.configuration.ConfigDB;
import main.dao.CarroDAO;
import main.dao.CompradorDAO;
import main.entities.Carro;
import main.entities.Comprador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAOImpl implements CarroDAO {
    private static CompradorDAO compradorDAO = new CompradorDAOImpl();
    @Override
    public void save(Carro carro) {
        String sql = "INSERT INTO agencia.carro (nome, placa, compradorid) VALUES (?, ?, ?)";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, carro.getNome());
            ps.setString(2, carro.getPlaca());
            ps.setInt(3, carro.getComprador().getId());
            ps.executeUpdate();
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Carro carro) {
        if (carro == null || carro.getId() == null) {
            System.out.println("Não foi possivel excluir o registro");
            return;
        }
        String sql = "DELETE FROM agencia.Carro WHERE 'id' = ?";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, carro.getId());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void update(Carro carro) {
        if (carro == null || carro.getId() == null) {
            System.out.println("Não foi possivel atualizar o registro");
            return;
        }
        String sql = "UPDATE agencia.carro SET nome= ?, placa= ? WHERE id= ?";
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, carro.getNome());
            ps.setString(2, carro.getPlaca());
            ps.setInt(3, carro.getId());
            ps.executeUpdate();
            System.out.println("Registro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  List<Carro> selectAll() {
        String sql = "SELECT id, nome, placa, compradorid FROM agencia.carro";
        List<Carro> carroList = new ArrayList<>();
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Comprador c = compradorDAO.searchById(rs.getInt("compradorid"));
                carroList.add(new Carro(
                        rs.getInt("id"), rs.getString("nome"), rs.getString("placa"), c));
            }
            return carroList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  List<Carro> searchByName(String nome) {
        String sql = "SELECT id, nome, placa, compradorid FROM agencia.carro WHERE nome LIKE ?";
        List<Carro> CarroList = new ArrayList<>();
        try (Connection conn = ConfigDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comprador c = compradorDAO.searchById(rs.getInt("compradorid"));
                CarroList.add(new Carro(
                        rs.getInt("id"), rs.getString("nome"), rs.getString("placa"), c));
            }
            ConfigDB.close(conn, ps, rs);
            return CarroList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
