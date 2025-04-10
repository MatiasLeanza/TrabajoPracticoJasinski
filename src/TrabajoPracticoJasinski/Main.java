package TrabajoPracticoJasinski;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final int CANT_VIDEOS_MIN = 0;
    private static final int CANT_VIDEOS_MAX = 100;
    private static final int TAMANO = 8;
    private static int videos = 0;

    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in);
        
        String[][] mainArray = new String[CANT_VIDEOS_MAX][TAMANO];
        int opcion;
        
        do {
            opcion = mostrarMenu(s);
            generarAccion(s,opcion, mainArray);
        }while(opcion != 11);
    }
    
  
    
    public static int mostrarMenu(Scanner s) {
        final int MAX_OPCION = 11;
        final int MIN_OPCION = 1;

        System.out.println("---------------------MENU---------------------");
        System.out.println("Elija una Opcion");
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
        System.out.println("-----------------------------------------------");

        return comprobarEntero(s,MAX_OPCION,MIN_OPCION);
    }
    
   
    
    //Funcion para verificar la opcion que elija el usuario en el menu
    public static int comprobarEntero(Scanner s, final int MAX, final int MIN) {
    	boolean error = false;
		int numero = 0;
    	do {
    		try {
        		numero = s.nextInt();
    			if( numero > MAX || numero < MIN) {
    				error = true;
    				System.out.println("El numero debe estar entre el " + MIN + " y " + MAX);
    				System.out.println("Ingrese el numero nuevamente");
    			} else {
                    error = false;
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
    	} while(error);
        
        return numero;
    }
    
   
    public static void generarAccion(Scanner s,final int opcion, final String[][] mainArray) {
        switch (opcion) {
            case 1:
                ingresarVideo(s,mainArray);
                break;
            case 2:
            	consultarDatos(s, mainArray);
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 11:
            	
                break;
        }
    }



    private static void ingresarVideo(Scanner s,final String[][] ARRAY) {
    	String titulo,
    	canal,
    	pattern = "dd/MM/yyyy";
    	
        int id, categoria, duracion, visualizaciones, valoracion, indice_id;
        
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        formato.setLenient(false);

        String [] categorias = {
            "Gaming",
            "Musica",
            "Educacion",
            "Comedia",
            "Deportes",
            "Tecnologia",
            "Vlogs", 
            "Otros"
        };

        boolean error = false;

        System.out.println("Ingrese el ID del video");
        do {
            id = comprobarEntero(s, CANT_VIDEOS_MAX, CANT_VIDEOS_MIN);
            indice_id = buscarEntero(ARRAY, 0, id);
            if(indice_id != -1){
                System.out.println("Este ID ya se encuentra en uso\nIntentelo de nuevo");
                error = true;
            }
            
        } while (error);
        
        error = false;

        System.out.println("Porfavor ingrese el nombre del video que desea ingresar: ");
        titulo = s.nextLine();

        System.out.println("Ingrese el nombre del canal: ");
        canal = s.nextLine();

        System.out.println("Ingrese la categoria del video: ");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i+1) + "- " + categorias[i]);
        }
        categoria = comprobarEntero(s, 8, 1);

        System.out.println("Ingrese la duracion del viedo en segundos");
        duracion = comprobarEntero(s, Integer.MAX_VALUE, 60);

        System.out.println("Ingrese la fecha de publicacion (con el formato DD/MM/YYYY)");
        Date fecha = null;
        
        do {
            try {
                fecha = formato.parse(s.nextLine());
                error = true;
            } catch (Exception e) {
                System.out.println("Fecha ingresada no valida. Asegurese de usar el formato DD/MM/YYYY.");
            }
        } while (!error);

        error = false;

        System.out.println("Ingrese la cantidad de visualizaciones: ");
        visualizaciones = comprobarEntero(s, Integer.MAX_VALUE, 0);

        System.out.println("Ingrese la valoraxion promedio del video:");
        valoracion = comprobarEntero(s, 5, 1);

        ARRAY[videos][0] = String.valueOf(id);
        ARRAY[videos][1] = titulo;
        ARRAY[videos][2] = canal;
        ARRAY[videos][3] = String.valueOf(categoria);
        ARRAY[videos][4] = String.valueOf(duracion);
        ARRAY[videos][5] = String.valueOf(fecha);
        ARRAY[videos][6] = String.valueOf(visualizaciones);
        ARRAY[videos][7] = String.valueOf(valoracion);

        videos++;
    }


    private static int buscarEntero(final String[][] ARRAY, final int POSICION,final int NUM) {
        int i = 0;
        while (i < videos) {
            if(Integer.parseInt(ARRAY[i][POSICION]) == NUM) {
                return i;
            }
            i++;
        }

        return -1;
    }

    
    
    private static void consultarDatos(Scanner s, final String[][]ARRAY) {
    	System.out.println("Ingrese id del video que quiere consultar");
    	boolean error = false;
    	int id = -1;
    do {
    		error = true;
    		id = comprobarEntero(s, CANT_VIDEOS_MAX,CANT_VIDEOS_MIN);
    		if (id != -1) {
    			System.out.println("Ingrese un id existente por favor");
    			
    		} else {
    			error = false;
    		}
    }while (error);
    	
    
    
    
  }
    
    


    /*private static String validarCanal(Scanner s, final String[][] ARRAY, final int POSICION) {
        boolean check = false;
        String str;
        
        do {
            str = s.nextLine();
            for (int i = 0; i < videos; i++) {
                if (ARRAY[i][POSICION].equals(str)) {
                    System.err.println("Este canal ya existe");
                    System.out.println("Ingrese un nombre no existente");
                } else {
                    check = true;
                }
            }
        } while (!check);

        return str;
    } */
}
