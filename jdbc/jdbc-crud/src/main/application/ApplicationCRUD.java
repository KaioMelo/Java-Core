package main.application;

import java.util.Scanner;

public class ApplicationCRUD {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int op;
        while (true){
           menu();
           op = Integer.parseInt(sc.nextLine());
           if(op == 0){
               System.out.println("Encerrando Aplicação!");
               break;
           } else if (op == 1) {
               menuComprador();
               op = Integer.parseInt(sc.nextLine());
               CompradorCRUD.executar(op);
           } else if (op == 2) {
               menuCarro();
               op = Integer.parseInt(sc.nextLine());
               CarroCRUD.executar(op);
           }
        }
    }

    public static void menu(){
        System.out.println("Selecione uma opção!");
        System.out.println("1. Comprador");
        System.out.println("2. Carro");
        System.out.println("0. sair");
    }

    public static void menuComprador(){
        System.out.println("Escolha a opção para iniciar!");
        System.out.println("1. Inserir comprador");
        System.out.println("2. Atualizar comprador");
        System.out.println("3. Listar compradores");
        System.out.println("4. Listar comprador por nome");
        System.out.println("5. Deletar");
        System.out.println("0. voltar");
    }

    public static void menuCarro(){
        System.out.println("Escolha a opção para iniciar!");
        System.out.println("1. Inserir carro");
        System.out.println("2. Atualizar carro");
        System.out.println("3. Listar carros");
        System.out.println("4. Listar carros por nome");
        System.out.println("5. Deletar");
        System.out.println("0. voltar");
    }

}
