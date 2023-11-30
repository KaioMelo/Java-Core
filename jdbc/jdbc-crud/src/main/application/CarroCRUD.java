package main.application;

import main.dao.CarroDAO;
import main.dao.impl.CarroDAOImpl;
import main.entities.Carro;
import main.entities.Comprador;

import java.util.List;
import java.util.Scanner;

public class CarroCRUD {
    private static Scanner sc = new Scanner(System.in);

    private static CarroDAO dao = new CarroDAOImpl();

    public static void executar(Integer op){
        switch (op){
            case 1:
                inserir();
                break;
            case 2:
                atualizar();
                break;
            case 3:
                listar();
                break;
            case 4:
                System.out.println("Digite um nome: ");
                listaPorNome(sc.nextLine());
                break;
            case 5:
                deletar();
                break;
        }
    }

    public static void inserir(){
        Carro carro = new Carro();
        System.out.println("Nome: ");
        carro.setNome(sc.nextLine());
        System.out.println("Placa: ");
        carro.setPlaca(sc.nextLine());
        System.out.println("Selecione um dos compradores abaixo: ");
        List<Comprador> compradorList = CompradorCRUD.listar();
        carro.setComprador(compradorList.get(Integer.parseInt(sc.nextLine())));
        dao.save(carro);
    }

    public static void atualizar(){
        System.out.println("Selecione um dos carros abaixo: ");
        List<Carro> carroList = listar();
        Carro c = carroList.get(Integer.parseInt(sc.nextLine()));
        System.out.println("Novo nome ou ENTER para manter nome atual: ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()){
            c.setNome(nome);
        }
        System.out.println("Nova Placa ou ENTER para manter nome atual: ");
        String placa = sc.nextLine();
        if (!placa.isEmpty()){
            c.setPlaca(placa);
        }
        dao.update(c);
    }

    public static List<Carro> listar(){
        List<Carro> carroList = dao.selectAll();
        for (int i=0; i < carroList.size(); i++){
            Carro c = carroList.get(i);
            System.out.println("["+ i + "]"+ c.getNome() +" "+ c.getPlaca()+" "+ c.getComprador().getNome());
        }
        System.out.println("------------------------");
        return carroList;
    }

    public static void listaPorNome(String nome){
        List<Carro> carroList = dao.searchByName(nome);
        for (int i=0; i < carroList.size(); i++){
            Carro c = carroList.get(i);
            System.out.println("["+ i + "]"+ c.getNome() +" "+ c.getPlaca()+" "+ c.getComprador().getNome());
        }
        System.out.println("------------------------");
    }

    public static void deletar(){
        System.out.println("Selecione um dos carros listados abaixo: ");
        List<Carro> carroList = listar();
        int index = Integer.parseInt(sc.nextLine());
        System.out.println("Tem certeza? S/N");
        String op = sc.nextLine();
        if(op == "s"){
            dao.delete(carroList.get(index));
        }
    }
}
