package test;

import database.CompradorDB;
import entities.Comprador;

import java.util.List;

public class TesteConexao {
    public static void main(String[] args) {
        List<Comprador> compradorList = buscarPorNome("ria");
        System.out.println(compradorList);
    }

    public static void inserir(){
        Comprador comprador = new Comprador();
        CompradorDB compradorDB = new CompradorDB();
        compradorDB.save(comprador);
    }

    public static void deletar(){
        Comprador comprador = new Comprador();
        CompradorDB compradorDB = new CompradorDB();
        compradorDB.delete(comprador);
    }

    public static void atualizar(){
        Comprador comprador = new Comprador();
        CompradorDB compradorDB = new CompradorDB();
        compradorDB.update(comprador);
    }

    public static List<Comprador> selecionarTudo(){
        return CompradorDB.selectAll();
    }

    public static List<Comprador> buscarPorNome(String nome){
        return CompradorDB.searchByName(nome);
    }
}
