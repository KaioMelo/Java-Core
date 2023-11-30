package main.dao;

import main.entities.Carro;
import main.entities.Comprador;

import java.util.List;

public interface CarroDAO {

    void save(Carro carro);

    void delete(Carro carro);

    void update(Carro carro);

    List<Carro> selectAll();

    List<Carro> searchByName(String nome);
}
