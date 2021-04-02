package edu.ufp.inf.aed2.main;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.Arrays;
import edu.ufp.inf.aed2.main.*;

public class Main {

    /**
     * Main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Exemplo fornecido com AED2");
        //exemplo1AED2();
        // exemplo1_BinSearchAED2();
        // exemplo2AED2_Ficheiros();
        //exercice1_1();
        //exercice1_2();
        //exercice1_3();
        //exercice1_4_normal();
        //exercice1_4_gaussiano();
        //exercice1_4_bernoulli();
        //exercice3_1();
        Ficha1.exercicio1();

    }

    public static void exemplo1AED2() {

        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        st.put("Ana", 1);
        st.put("Bruno", 2);
        st.put("Carla", 3);
        st.put("Daniel", 4);
        st.put("Luis", 25);
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

    public static void exemplo1_BinSearchAED2() {

        BinarySearchST<String, Integer> st = new BinarySearchST();
        st.put("A", 1);
        st.put("Z", 2);
        st.put("C", 3);
        st.put("X", 4);
        st.put("L", 25);
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }

        StdOut.println("Teste da API ordenada da Tabela de Simbolos.");
        StdOut.println("Rank de C = " + st.rank("C"));
        StdOut.println("Chave menor = " + st.min());

        StdOut.println("Listar todas as chaves entre B e P.");
        for (String s : st.keys("B", "P")) {
            StdOut.println(s + " " + st.get(s));
        }

    }

    public static void exemplo2AED2_Ficheiros() {

        In in = new In(".//data//4Kints_1.txt"); // abertura do ficheiro/stream de entrada
        String linha;
        int contar = 0, soma = 0, media;
        while (in.hasNextLine()) {
            linha = in.readLine();
            System.out.println(linha);
            contar++;
            soma = soma + Integer.valueOf(linha);
        }
        media = soma / contar;
        System.out.println("\nMedia=" + media);
    }

    public static void exercice1_1() {
        In in = new In("/Users/franciscocunha/IdeaProjects/AED2_PL/data/8ints.txt");

        if(!in.exists()){
            System.out.println("failed to read file");
            return;
        }

        int[] allInts = in.readAllInts();
        System.out.println("size of the list: " + allInts.length);
        System.out.println("Content: " + Arrays.toString(allInts));
    }

    public static void exercice1_2() {
        In in = new In("/Users/franciscocunha/IdeaProjects/AED2_PL/data/30ints.txt");

        if(!in.exists()){
            System.out.println("failed to read file");
            return;
        }
        int lineNumber = 0, totalItems= 0;
        String line="";

        while (in.hasNextLine()){
            line = in.readLine();

            if(lineNumber > 0){
                System.out.println(Integer.parseInt(line));
                totalItems++;
            }
            lineNumber++;
        }
        System.out.println("List size: " + totalItems);
    }

    public static void exercice1_3(){

        In in = new In("/Users/franciscocunha/IdeaProjects/AED2_PL/data/8ints.txt");

        if(!in.exists()){
            System.out.println("failed to read file");
            return;
        }

        int k = 6;

        int[] allInts = in.readAllInts();

        Out out = new Out("/Users/franciscocunha/IdeaProjects/AED2_PL/data/8ints.txt");

        for(int i = 0 ; i < allInts.length; i++){
            allInts[i] += k;
            System.out.println("Novos elementos; " + allInts[i]);
            out.println(allInts[i]);
        }
    }

    public static void exercice1_4_normal(){
        int size = 10;
        int[] output = new int[size];

        for(int i = 0; i < size; i++){
            output[i] = StdRandom.uniform(Integer.MAX_VALUE);
            System.out.println(output[i]);
        }
    }

    public static void exercice1_4_gaussiano(){
        int size = 10;
        double[] output = new double[size];

        for(int i = 0; i < size; i++){
            output[i] = StdRandom.gaussian();
            System.out.println(output[i]);
        }
    }

    public static void exercice1_4_bernoulli(){
        int size = 10;
        int[] output = new int[size];

        for(int i = 0; i < size; i++){
            if(StdRandom.bernoulli(0.5))
                output[i] = 1;
            else
                output[i] = 0;
            System.out.println(output[i]);
        }
    }

    public static void exercice3_1(){
        Aed2Plot.init_plot(10.0,15.0,8.0,10.0);
        double[] xvlas = new double[3];
        double[] yvals=new double[3];
        xvlas[0] = 13.2;
        xvlas[1] = 14.2;
        xvlas[2] = 11.2;
        yvals[0] = 8.2;
        yvals[1] = 9.2;
        yvals[2] = 9.7;
        Aed2Plot.plot(xvlas,yvals,Color.BLUE);
        Aed2Plot.line(xvlas,yvals,Color.BLACK);
    }
}
