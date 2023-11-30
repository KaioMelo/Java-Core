package main.application;

import main.dao.CompradorDAO;
import main.dao.impl.CompradorDAOImpl;
import main.entities.Comprador;

import java.util.List;
import java.util.Scanner;

public class CompradorCRUD {
    private static Scanner sc = new Scanner(System.in);
    private static CompradorDAO dao = new CompradorDAOImpl();

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
        Comprador comprador = new Comprador();
        System.out.println("CPF: ");
        comprador.setCpf(sc.nextLine());
        System.out.println("Nome: ");
        comprador.setNome(sc.nextLine());
        dao.save(comprador);
    }

    public static void atualizar(){
        System.out.println("Selecione um dos compradores abaixo: ");
        List<Comprador> compradorList = listar();
        Comprador c = compradorList.get(Integer.parseInt(sc.nextLine()));
        System.out.println("Novo nome ou ENTER para manter nome atual: ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()){
            c.setNome(nome);
        }
        System.out.println("Novo CPF ou ENTER para manter nome atual: ");
        String cpf = sc.nextLine();
        if (!cpf.isEmpty()){
            c.setCpf(cpf);
        }
        dao.update(c);
    }

    public static List<Comprador> listar(){
        List<Comprador> compradorList = dao.selectAll();
        for (int i=0; i < compradorList.size(); i++){
            Comprador c = compradorList.get(i);
            System.out.println("["+ i + "]"+ c.getNome() +" "+ c.getCpf());
        }
        System.out.println("------------------------");
        return compradorList;
    }

    public static void listaPorNome(String nome){
        List<Comprador> compradorList = dao.searchByName(nome);
        for (int i=0; i < compradorList.size(); i++){
            Comprador c = compradorList.get(i);
            System.out.println("["+ i + "]"+ c.getNome() +" "+ c.getCpf());
        }
        System.out.println("------------------------");
    }

    public static void deletar(){
        System.out.println("Selecione um dos compradores listados abaixo: ");
        List<Comprador> compradorList = listar();
        int index = Integer.parseInt(sc.nextLine());
        System.out.println("Tem certeza? S/N");
        String op = sc.nextLine();
        if(op == "s"){
            dao.delete(compradorList.get(index));
        }
    }
}
