package TrabajoPracticoJasinski;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int CANT_VIDEOS_MIN = 0;
        final int CANT_VIDEOS_MAX = 100;
        final int TAMANO = 8;
        int videos = 0;

        Scanner s = new Scanner(System.in);
        String[][] mainArray = new String[CANT_VIDEOS_MAX][TAMANO];
        int opcion;

        do {
            opcion = mostrarMenu(s);
            videos = generarAccion(s, opcion, mainArray, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX, TAMANO, videos);
        } while(opcion != 11);
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

        return ingresarEntero(s, MAX_OPCION, MIN_OPCION);
    }


    public static String ingresarString(Scanner s, final int MAX, final int MIN) {
    	boolean error;
    	String texto;
    	do {
    		error = false;
    		texto = s.nextLine().trim();
    		if(texto.isEmpty()) {
    			error = true;
    			System.out.println("El texto no puede estar vacio");
    		}else if(texto.length() < MIN || texto.length( ) > MAX ){
    			error = true;
    			System.out.println("El texto debe tener entre " + MIN +" y " + MAX +" caracteres");
    		}
    	}while(error);
    	return texto;
    }
    
    public static int ingresarEntero(Scanner s, final int MAX, final int MIN) {
        boolean error; 
        int numero = 0;
        do {
            error = false;
            try {
                numero = s.nextInt();
                if (numero > MAX || numero < MIN) {
                    error = true;
                    System.out.println("El numero debe estar entre el " + MIN + " y " + MAX);
                }
            } catch (InputMismatchException e) {
                error = true;
                System.out.println("El tipo de dato ingresado es incorrecto");
            } catch (Exception e) {
                System.out.println("Error desconocido");
                error = true;
            } finally {
                s.nextLine();
            }
        } while (error);
        return numero;
    }

    public static int generarAccion(Scanner s, final int opcion, final String[][] mainArray,final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX,final int TAMANO, int videos) {
        switch (opcion) {
            case 1:
                videos = ingresarVideo(s, mainArray, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX, TAMANO, videos);
                break;
            case 2:	
            	if(mainArray[0][0] == null) {
            		System.err.println("Error. No se subio ningun video.");
            		return 0;
            	} else {
                consultarVideo(s, mainArray, TAMANO, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
                }
                break;
                
            case 3:
            	modificarVideo(s, mainArray, TAMANO, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
            	break;
            case 4:
            	if (videos >= 1) {
            		videos = eliminarVideo(s, mainArray, TAMANO, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
            	} else {
            		System.out.println("No hay ningun video subido, porfavor ingrese uno antes de borrar");
            	}
            	break;
            case 5:
            	listarVideos(mainArray, videos);
            	break;
	    case 7:
		buscarVideosPorCanal(s,mainArray,videos,TAMANO);
            	break;
                
        }
        return videos;
    }

	private static void listarVideos(final String[][] ARRAY, int videos) {
		if (videos > 0) {
	        for (int i = 0; i < videos; i++) {
	        	System.out.println("--------------------------------");
	            System.out.println((i + 1) + ") ");
	            System.out.println("ID: " + ARRAY[i][0]);
	            System.out.println("Titulo: " + ARRAY[i][1]);
	            System.out.println("Canal: " + ARRAY[i][2]);
	        }
	    } else {
	        System.out.println("No hay videos existentes como para mostrar");
	    }
	}
    
    
    // Elimino las acciones mediante el id
    private static int eliminarVideo(Scanner s, final String[][] ARRAY, final int TAMANO, int videos, final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) {
    	boolean error = false;
    	System.out.println("Ingrese el Id del video que desea eliminar: ");
    	final int indiceId = buscarVideoPorID(s, ARRAY, error, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
    	
    	if (videos > 0) {
    		for(int i = indiceId; i < (videos-1); i++) {
        		ARRAY[i] = ARRAY[i+1];
        	}
    	} 
    	
    	for(int i = 0; i < ARRAY[videos-1].length; i++) {
    		ARRAY[videos-1][i] = null;
    	}
    	videos--;
    	
		return videos;
	}

	private static int ingresarVideo(Scanner s, final String[][] ARRAY,final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX,final int TAMANO, int videos) {
        String titulo, canal, pattern = "dd/MM/yyyy";
        int id, categoria, duracion, visualizaciones, valoracion, indice_id;
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        formato.setLenient(false);

        String[] categorias = {
            "Gaming", "Musica", "Educacion", "Comedia",
            "Deportes", "Tecnologia", "Vlogs", "Otros"
        };

        boolean error = false;
        System.out.println("Ingrese el ID del video");
        do {
            id = ingresarEntero(s, CANT_VIDEOS_MAX, CANT_VIDEOS_MIN);
            indice_id = buscarEntero(ARRAY, 0, id, videos);
            if (indice_id != -1) {
                System.out.println("Este ID ya se encuentra en uso\nIntentelo de nuevo");
                error = true;
            } else {
                error = false;
            }
        } while (error);

        System.out.println("Por favor ingrese el nombre del video: ");
        titulo = s.nextLine();

        System.out.println("Ingrese el nombre del canal: ");
        canal = s.nextLine();

        System.out.println("Ingrese la categoria del video: ");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i+1) + "- " + categorias[i]);
        }
        categoria = ingresarEntero(s, 8, 1);

        System.out.println("Ingrese la duracion del video en segundos");
        duracion = ingresarEntero(s, Integer.MAX_VALUE, 60);

        System.out.println("Ingrese la fecha de publicacion (DD/MM/YYYY)");
        Date fecha = validarFecha(s, formato);

        System.out.println("Ingrese la cantidad de visualizaciones: ");
        visualizaciones = ingresarEntero(s, Integer.MAX_VALUE, 0);

        System.out.println("Ingrese la valoracion promedio del video:");
        valoracion = ingresarEntero(s, 5, 1);

        ARRAY[videos][0] = String.valueOf(id);
        ARRAY[videos][1] = titulo;
        ARRAY[videos][2] = canal;
        ARRAY[videos][3] = String.valueOf(categoria);
        ARRAY[videos][4] = String.valueOf(duracion);
        ARRAY[videos][5] = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        ARRAY[videos][6] = String.valueOf(visualizaciones);
        ARRAY[videos][7] = String.valueOf(valoracion);

        return videos + 1;
    }

    private static Date validarFecha(Scanner s, SimpleDateFormat formato) {
        Date fecha = null;
        boolean error = false;
        do {
            try {
                fecha = formato.parse(s.nextLine());
                error = false;
            } catch (Exception e) {
                System.out.println("Fecha ingresada no valida. Use el formato DD/MM/YYYY.");
                error = true;
            }
        } while (error);
        return fecha;
    }

    private static int buscarEntero(final String[][] ARRAY, final int POSICION, final int NUM, final int videos) {
        for (int i = 0; i < videos; i++) {
            if (Integer.parseInt(ARRAY[i][POSICION]) == NUM) {
                return i;
            }
        }
        return -1;
    }

    private static void consultarVideo(Scanner s, final String[][] ARRAY,final int TAMANO, final int videos,final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) {
        boolean error = false;
        
        
        System.out.println("Ingrese id del video que quiere consultar");
        
        int indiceId = buscarVideoPorID(s, ARRAY, error, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
        mostrarVideo(ARRAY, TAMANO, indiceId); 
    }
	
    private static void buscarVideosPorCanal(Scanner s,final String[][] MATRIZ, final int videos, final int TAMAÑO) {
    	System.out.println("Escribi el nombre del canal asi buscamos todos sus videos");
    	String canalBuscado = ingresarString(s , 50 ,1);
    	boolean verificar = true;
        for(int i = 0; i < videos; i++) {
			if(MATRIZ[i][2].toLowerCase().equals(canalBuscado.toLowerCase())) {
				System.out.println("VIDEO NUMERO " + i + 1 + ":");
				mostrarVideo(MATRIZ, TAMAÑO ,i);
				verificar = false;
			}	
        }
        if(verificar) {
        	System.out.println("Escribiste el nombre de un canal que no existe");
        }
    }
    


	private static void mostrarVideo(final String[][] ARRAY, final int TAMANO,final int INDICE_ID) {
		for (int i = 0; i < TAMANO; i++) {
            System.out.println(ARRAY[INDICE_ID][i]);
        }
	}

    private static int buscarVideoPorID(Scanner s, final String[][] ARRAY, boolean error,final int VIDEOS, final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) {
        
    	int id, indiceId;
        do {
            
            id = ingresarEntero(s, CANT_VIDEOS_MAX, CANT_VIDEOS_MIN);
            indiceId = buscarEntero(ARRAY, 0, id, VIDEOS);
            if (indiceId == -1) {
                System.out.println("Ingrese un id existente por favor");
                error = true;
            } else {
                error = false;
            }
        } while (error);
        
        return indiceId;
    }

    private static int modificarVideo (Scanner s, final String[][] ARRAY,final int TAMANO, final int videos,final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) { // Esto si funciona
    	 boolean error = false;
    	 System.out.println("Ingrese id del video que quiere editar");
    	 
    	 int indiceId = buscarVideoPorID(s, ARRAY, error, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
    	 
    	 System.out.println("Ahora ingrese el dato que quiera editar: ");
    	
         System.out.println("1) Titulo");
         System.out.println("2) Canal");
         System.out.println("3) Categoria");
         System.out.println("4) Duracion");
         System.out.println("5) Fecha");
         System.out.println("6) Visualizaciones");
         System.out.println("7) Valoracion");
         System.out.println("8) Cancelar");
         
         int eleccion = ingresarEntero(s,8,1);
         
         if (eleccion == 8) {
        	 return -1;
         } else {
        	 ARRAY[indiceId][eleccion] = modificarDatos(s,ARRAY[indiceId][eleccion],eleccion);
         }
         
        return -1;
	}

    private static String modificarDatos(Scanner s, String string, final int OPC) {
        if (OPC == 1 || OPC == 2) {
        	System.out.println("Ingrese el nuevo " + (OPC == 1? "titulo" : "nombre del canal"));
            string = s.nextLine();
        } else {
            switch (OPC) {
                case 3: // Categoría
                    System.out.println("Ingrese la nueva categoría:");
                    String[] categorias = {
                        "Gaming", "Musica", "Educacion", "Comedia",
                        "Deportes", "Tecnologia", "Vlogs", "Otros"
                    };
                    for (int i = 0; i < categorias.length; i++) {
                        System.out.println((i+1) + "- " + categorias[i]);
                    }
                    int nuevaCategoria = ingresarEntero(s, 8, 1);
                    string = String.valueOf(nuevaCategoria);
                    break;
                    
                case 4: // Duración
                    System.out.println("Ingrese la nueva duración en segundos (mínimo 60)");
                    int nuevaDuracion = ingresarEntero(s, Integer.MAX_VALUE, 60);
                    string = String.valueOf(nuevaDuracion);
                    break;

                case 5: // Fecha
                    System.out.println("Ingrese la nueva fecha (DD/MM/YYYY)");
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    formato.setLenient(false);
                    Date nuevaFecha = validarFecha(s, formato);
                    string = new SimpleDateFormat("dd/MM/yyyy").format(nuevaFecha);
                    break;

                case 6: // Visualizaciones
                    System.out.println("Ingrese la nueva cantidad de visualizaciones:");
                    int nuevasVisualizacion = ingresarEntero(s, Integer.MAX_VALUE, 0);
                    string = String.valueOf(nuevasVisualizacion);
                    break;

                case 7: // Valoración
                    System.out.println("Ingrese la nueva valoración (1 a 5):");
                    int nuevaValoracion = ingresarEntero(s, 5, 1);
                    string = String.valueOf(nuevaValoracion);
                    break;
            }
        }
        return string;
    }
    
    
}
	
