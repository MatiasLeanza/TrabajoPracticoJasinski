package TrabajoPracticoJasinski;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in);
        String[][] mainArray = new String[100][8]; //el string principal, hasta 100 videos, y son 8 cosas para mostrar en pantalla
        int opcion;
        
        opcion = mostrarMenu(s);
        opcionesProgramas(opcion);
    }
    
  
    
    //Funcion del menu
    public static int mostrarMenu(Scanner s) {
        int opcion;
        boolean chequeoOpcion;
        boolean exitMenu;
        
        do {
            chequeoOpcion = false;
            System.out.println("----------------------------------------------MENU-----------------------------------------");
            System.out.println("\nElija una Opcion");
            System.out.println("1) Subir video");
            System.out.println("2) Consultar video");
            System.out.println("3) Modificacion informacion de video");
            System.out.println("4) Eliminar video");
            System.out.println("5) Listar todos los videos");
            System.out.println("6) Buscar videos por categoria");
            System.out.println("7) Buscar videos por canal");
            System.out.println("8) Buscar videos mas/menos vistos");
            System.out.println("9) Buscar videos mejor/peor valorados");
            System.out.println("10) Calcular estadisticas de la plataforma");
            System.out.println("11) Salir");
            
            opcion = s.nextInt();
            
        } while (chequeoMenu(opcion));
        
        return opcion;
    }
    
   
    
    //Funcion para verificar la opcion que elija el usuario en el menu
    public static boolean chequeoMenu(int opcion) {
        if (opcion > 11 || opcion <= 0) {
            System.out.println("Opcion no valida, Intente de nuevo");
            return true;
        }
        return false;
    }
    
   
   //Aca tendrian  que ir los programas, en cada switch habria que poner una funcion que esa funcion sea la opcion elegida, osea un "programa", 
    public static void opcionesProgramas(int opcion) {
        switch (opcion) {
            case 1:
                //EjemploDePrograma(Etc);
            	System.out.println("1"); // los numeros es para verificar que ande, despues saquenlos al pingo
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                System.out.println("5");
                break;
            case 6:
                System.out.println("6");
                break;
            case 7:
                System.out.println("7");
                break;
            case 8:
                System.out.println("8");
                break;
            case 9:
                System.out.println("9");
                break;
            case 10:
                System.out.println("10");
                break;
            case 11:
                System.out.println("Programa cerrado con exito");
                break;
        }
    }
}

