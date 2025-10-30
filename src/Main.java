import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    final static Scanner sc =  new Scanner(System.in);

    public static LocalDate fechaNacimiento;
    public static Period edad;

    public static int num1, num2;

    public static StringBuilder frase = new StringBuilder();

    public static void main(String[] args) {

        try {
            opciones();
        }
        catch (Exception e){
            System.out.println("Errores: " + e.getClass());
        }

    }

    //Panel de opciones
    public static void opciones () throws Exception{

        System.out.print("""
                -- Menú de opciones --
                a) Calcular la edad de una persona
                b) Números pares y primos
                c) Palíndromo
                d) Salir
                """);
        System.out.print("Que quieres hacer: ");

        String opcion = sc.nextLine();
        switch (opcion){
            case "a":
                pedirEdad();
                break;
            case "b":
                numeroParPrimo();
                break;
            case "c":
                pedirPalindromo();
                break;
            case "d":
                finPrograma();
                break;
            default:
                System.out.println("** La opción no es valida **");
                opciones();
        }
    }

    //Apartado de edades A
    public static void pedirEdad() throws Exception{
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("-----------------------------------");
            System.out.print("Cuando naciste (DD/MM/YYYY): ");
            fechaNacimiento = LocalDate.parse(sc.next(), formato);
            if (fechaNacimiento.isAfter(LocalDate.now())){
                throw new FechaMayorHoy();
            }
            edadPersona();
            proximoCumpleanos();
            sc.nextLine();
            preguntarFin();
        }
        catch (DateTimeParseException ex){
            System.out.println("** Los valores introducidos no son validos **");
            pedirEdad();
        }
        catch (FechaMayorHoy ex){
            System.out.println("** La fecha introducida no puede ser posterior a la fecha actual **");
            pedirEdad();
        }
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

    //Apartado de numeros B
    public static void numeroParPrimo() throws Exception{

        try {
            System.out.println("-----------------------------------");
            System.out.print("Escribe el primer numero: ");
            num1 = sc.nextInt();
            System.out.print("Escribe el segundo numero: ");
            num2 = sc.nextInt();

            if (num1 > num2){
                throw new NumeroMayorAnterior();
            }

            numerosEnMedio();
            parPrimo();
            sc.nextLine();
            preguntarFin();
        }
        catch (NumeroMayorAnterior ex){
            System.out.println("** El primer numero no puede ser mayor que el segundo **");
            numeroParPrimo();
        }
        catch (InputMismatchException ex){
            System.out.println("** Valores no validos **");
            numeroParPrimo();
        }
    }
    public static void numerosEnMedio(){

        StringBuilder numerosImprimir = new StringBuilder();

        for(int i = num1+1; i < num2; i++){
            numerosImprimir.append(i).append(" ");
        }

        System.out.println("-- Números entre " + num1 + " Y " +  num2 + " --");
        System.out.println(numerosImprimir);
    }
    public static void parPrimo() {

        int numeroComprobar = num1;
        int vueltas = 0;

        System.out.println("-- Comparación de par/impar y primo --");

        do {

            if (numeroComprobar % 2 == 0) {

                System.out.println("Numero " + numeroComprobar);
                System.out.println("- Es par");

            } else {
                int contador = 0;
                for (int i = 2; i <= num1; i++) {

                    if (num1 % i == 0) {
                        contador++;
                    }
                }
                System.out.println("Numero " + numeroComprobar);
                if (contador == 1) {
                    System.out.println("- Es impar y es primo");
                } else {
                    System.out.println("- Es impar y no es primo");
                }
            }

            numeroComprobar = num2;
            vueltas++;

        }while (vueltas < 2);
    }

    //Apartado de palíndromo C
    public static void pedirPalindromo() throws Exception{
        Pattern patron = Pattern.compile("^.[A-Za-z ]+");
        System.out.println("-----------------------------------");
        System.out.print("Escribe una frase para comprobar si es un palíndromo: ");
        frase = frase.append(sc.nextLine().toLowerCase());
        Matcher matcher = patron.matcher(frase);
        if (!matcher.matches()){
            System.out.println("** La frase solo puede tener letras en mayúsculas y minuscules **");
            frase.delete(0, frase.length());
            pedirPalindromo();
        }
        comprobarPalindromo();
        preguntarFin();
    }
    public static void comprobarPalindromo(){
        String fraseComprobar = frase.toString().replaceAll(" ", "");

        String alReves = frase.reverse().toString().replaceAll(" ","");


        if (fraseComprobar.equals(alReves)){
            System.out.println("La frase SI es un palíndromo");
        } else {
            System.out.println("La frase NO es un palíndromo");
        }

        frase.delete(0, frase.length());
    }

    public static void preguntarFin () throws Exception{

        System.out.println("-----------------------------------");
        System.out.print("Quires volver a las opciones (si/no): ");
        String respuesta = sc.nextLine().toLowerCase();

        if (respuesta.equals("si")){
            System.out.println("-----------------------------------");
            opciones();
        } else if (respuesta.equals("no")){
            finPrograma();
        } else {
            System.out.println("** Respuesta no valida **");
            preguntarFin();
        }
    }

    public static void finPrograma(){
        System.out.println("-----------------------------------");
        System.out.println("Fin del programa");

    }
}