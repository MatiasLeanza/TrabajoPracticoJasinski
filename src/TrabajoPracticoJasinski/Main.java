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
                consultarDatos(s, mainArray, TAMANO, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
                break;
        }
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
        ARRAY[videos][5] = String.valueOf(fecha);
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

    private static void consultarDatos(Scanner s, final String[][] ARRAY,final int TAMANO, final int videos,final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) {
        boolean error = false;
        int indiceId = buscarVideoPorID(s, ARRAY, error, videos, CANT_VIDEOS_MIN, CANT_VIDEOS_MAX);
        for (int i = 0; i < TAMANO; i++) {
            System.out.println(ARRAY[indiceId][i]);
        }
    }

    private static int buscarVideoPorID(Scanner s, final String[][] ARRAY, boolean error,int videos, final int CANT_VIDEOS_MIN, final int CANT_VIDEOS_MAX) {
        int id, indiceId;
        do {
            System.out.println("Ingrese id del video que quiere consultar");
            id = ingresarEntero(s, CANT_VIDEOS_MAX, CANT_VIDEOS_MIN);
            indiceId = buscarEntero(ARRAY, 0, id, videos);
            if (indiceId == -1) {
                System.out.println("Ingrese un id existente por favor");
                error = true;
            } else {
                error = false;
            }
        } while (error);
        return indiceId;
    }
}
