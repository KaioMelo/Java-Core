package main.dao;

import main.entities.Comprador;

import java.util.List;

public interface CompradorDAO {
    void save(Comprador comprador);

    void delete(Comprador comprador);

    void update(Comprador comprador);

    List<Comprador> selectAll();

    List<Comprador> searchByName(String nome);

    Comprador searchById(Integer id);
}
