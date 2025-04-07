import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in);
        final int cantVideosMin = 0;
        final int cantVideosMax = 100;
        final int datosMostrados = 8;
        String[][] mainArray = new String[cantVideosMax][datosMostrados]; //el string principal, hasta 100 videos, y son 8 cosas para mostrar en pantalla
        int opcion;
        
        do {
            opcion = mostrarMenu(s);
            opcionesProgramas(opcion);
        }while(opcion != 11);
    }
    
  
    
    //Funcion del menu
    public static int mostrarMenu(Scanner s) {
     int opcion;
     //   boolean chequeoOpcion;
       // boolean exitMenu;
        
        //do {
          //  chequeoOpcion = false;
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
        return comprobarEntero(s,1,11);
    }
    
   
    
    //Funcion para verificar la opcion que elija el usuario en el menu
    public static int comprobarEntero(Scanner s, final int opcionMaxima, final int opcionMinima) {
    	boolean error = false;
		int numero = 0;
    	do {
    		try {
        		numero = s.nextInt();
    			if( numero < opcionMaxima || numero > opcionMaxima) {
    				error = true;
    				System.out.println("El numero debe estar entre el " + opcionMaxima + " y " + opcionMaxima);
    				System.out.println("Ingrese el numero nuevamente");
    			}
    		} catch(InputMismatchException e) {
    			error = true;
    			System.out.println("El tipo de dato ingresado es incorrecto");
    			}catch(Exception e) {
    				System.out.println("Error desconocido");
    				System.out.println("Vuelva a intentar");
    			}finally {
    				s.nextLine();
    			}
    	}while(error);
    	 return numero;
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
