
/**
 * Write a description of class Prueba2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sudok
{
    static int [][] tabla = {
        {0,0,5,3,0,0,0,0,0},
        {8,0,0,0,0,0,0,2,0},
        {0,7,0,0,1,0,5,0,0},
        {4,0,0,0,0,5,3,0,0},
        {0,1,0,0,7,0,0,0,6},
        {0,0,3,2,0,0,0,8,0},
        {0,6,0,5,0,0,0,0,9},
        {0,0,4,0,0,0,0,3,0},
        {0,0,0,0,0,9,7,0,0}

        };
     /**
     * verifica que el valor no esta en la columna
     */ 
    public static boolean columna (int valor,int column) {
        for (int line=0; line<9; line++) {
            if (tabla[line][column]==valor){
                return true;
            }
        }
        return false;
    }

    /**
     * verifica que no esa en la fila 
     */
    public static boolean linea(int valor,int line) {
        for (int column=0; column<9; column++) {
            if (tabla[line][column]==valor){
                return true;
            }
        }
        return false;
    }

    /**
     * verifica que no esta en el cuadrante
     */
    public static boolean cuadrado(int valor,int line,int column) {
        int derecha = 3*(column/3);
        int alto = 3*(line/3);
        for (int c=derecha; c<derecha+3; c++) {
            for (int l=alto; l<alto+3; l++) {
                if (tabla[l][c]==valor){
                    return true;
                }
            }
        }
        return false;
    }
    

    /**
     * llama a los metodos verificar linea, columna, cuadrante y devuelve un boolean
     */
    public static boolean valorPosible(int valor,int line,int column) {
        return !columna(valor,column)
        && !linea(valor,line)
        && !cuadrado(valor,line,column);
    }

    /**
     * devuelve en pantalla el resultado
     */
    public static void pantalla() {
        for (int l=0; l<9; l++) {
            for (int c=0; c<9; c++) {
                if (tabla[l][c]!=0){
                    System.out.print(tabla[l][c]+" ");
                }
                else{ 
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * metodo que soluciona el cuadrante
     */
    public static boolean solucion(int line,int column) {
        
        // calcula la posicion siguiente
        int lineaSiguiente;
        int columnaSiguiente;
        if (column==8) { 
            lineaSiguiente = line+1; 
            columnaSiguiente=0;
        }
        else {
            lineaSiguiente = line;
            columnaSiguiente = column+1;
        }

        // ha terminado de recorrer todo el sudoku?
        if (line==9) { 
            pantalla(); 
            return true;
        }

        if (tabla[line][column]!=0) { // la celda esta vacia?
            return solucion(lineaSiguiente,columnaSiguiente);
        } else { // no esta vacia 
            for (int valor=1; valor<10; valor++) {
                
                if (!valorPosible(valor,line,column)){ // verifica que el valor no se encuentra repetido 
                    continue;
                }
                tabla[line][column] = valor; // inserta el valor en el sudoku
                boolean correcto = solucion(lineaSiguiente,columnaSiguiente);
                if (correcto){
                    return true;
                }// el valor es correcto
            }
            tabla[line][column] = 0; // el valor no es correcto se pone la celda a cero
            return false; // no se ha encontrado el valor, volvemos a empezar
        }
    }

    
}
