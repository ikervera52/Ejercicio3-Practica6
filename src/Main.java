import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Matcher;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    final static Scanner sc =  new Scanner(System.in);
    public static LocalDate fechaNacimiento;
    public static Period edad;

    public static int num1, num2;
    public static void main(String[] args) {


        opciones();

    }

    public static void opciones(){

        System.out.print("""
                -- Funciones del programa --
                a) Calcular la edad de una persona
                b) Números pares y primos
                c) Palíndromo
                d) Salir
                Que quieres hacer:
                """);

        String opcion = sc.nextLine();
        switch (opcion){
            case "a":
                pedirEdad();
                break;
            case "b":
                numeroParPrimo();
                break;
            case "c":
              //  comprobarPalindromo();
                break;
            case "d":
               // finPrograma();
                break;
            default:
                System.out.println("La opción no es valida");
        }
    }

    public static void pedirEdad(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print("Cuando naciste (DD/MM/YYYY): ");
        fechaNacimiento = LocalDate.parse(sc.next(), formato);
        edadPersona();
        proximoCumpleanos();
    }
    public static void edadPersona(){
        edad = Period.between(fechaNacimiento, LocalDate.now());
        System.out.println("Tienes " + edad.getYears() + " años");
    }
    public static void proximoCumpleanos(){

        LocalDate fecha1 = fechaNacimiento.plusYears(edad.getYears()+1);
        long proximo = ChronoUnit.DAYS.between(LocalDate.now(), fecha1);
        System.out.println("Tu siguiente cumpleaños es en " + proximo + " dias");
    }

    public static void numeroParPrimo(){

        System.out.println("-----------------------------------");
        System.out.print("Escribe el primer numero: ");
        num1 = sc.nextInt();
        System.out.print("Escribe el segundo numero: ");
        num2 = sc.nextInt();

        numerosEnMedio();
        parPrimo();
    }

    public static void numerosEnMedio(){

        StringBuilder numerosImprimir = new StringBuilder();

        for(int i = num1+1; i < num2; i++){
            numerosImprimir.append(i).append(" ");
        }

        System.out.printf("""
                -----------------------------------
                Los números que están entre %s y %s son:
                %s""", num1, num2, numerosImprimir);
    }

    public static void parPrimo(){

        if(num1 % 2 == 0){
            System.out.print(num1 + " Es PAR");
        }
        else {
            int contador = 0;
            for(int i = 0; i < num1; i++){
                if (i % num1 == 0){
                    contador++;
                }
            }
            if(contador == 2){
                System.out.printf("""
                        El numero %s
                            - Es IMPAR
                            - Es PRIMO
                        """);
            }
        }
    }
}