package com.EstudosLivroPaulDeitel.Conceitos_Java.EstudoDeCasos_SecureRandom;

import java.util.Scanner;

public class GeracaoNumerosAleatoriosSeguros {

  public static void main(String[] args) {
    //System.out.println("Teste");

    System.out.println(calcRadius(22.21, 10.1));
  }

  static double calcRadius(double valor1, double valor2) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite um nnumero para o Raio >>> ");
        double radius = input.nextDouble();
        double volume = (valor1 / valor2) * Math.PI * Math.pow(radius, 3);
        return volume;
  }

  int product() {
    int a = 12;
    int b = 22;
    int result = a + b;
    return result;
  }
}
