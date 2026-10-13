package pit2027;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


/**
 * Pruebas de los constructores de Matriz.
 */
public class MatrizTest {

    @Test
    public void sumaEstaticaCalculaTodosLosElementos() {
        Matriz primera = new Matriz(new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 } });
        Matriz segunda = new Matriz(new double[][] { { 0.5, -2.0 }, { 7.0, 6.0 } });
        Matriz suma = Matriz.suma(primera, segunda);
        assertArrayEquals(new int[] { 2, 2 }, suma.getDimension());
        assertEquals(1.5, suma.getElemento(0, 0), 1e-9);
        assertEquals(0.0, suma.getElemento(0, 1), 1e-9);
        assertEquals(10.0, suma.getElemento(1, 0), 1e-9);
        assertEquals(10.0, suma.getElemento(1, 1), 1e-9);
    }

    @Test
    public void sumaEstaticaRechazaArgumentosNull() {
        Matriz matriz = new Matriz(new double[][] { { 1.0 } });
        assertNull(Matriz.suma(null, matriz));
        assertNull(Matriz.suma(matriz, null));
        assertNull(Matriz.suma(null, null));
    }

    @Test
    public void sumaEstaticaRechazaDimensionesIncompatibles() {
        Matriz matriz = new Matriz(new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 } });
        assertNull(Matriz.suma(matriz, new Matriz(new double[][] { { 1.0, 2.0 } })));
        assertNull(Matriz.suma(matriz, new Matriz(new double[][] { { 1.0 }, { 2.0 } })));
    }

    @Test
    public void sumaMatricesRectangulares() {
        Matriz primera = new Matriz(new double[][] { { 1.0, -2.0, 0.5 }, { 3.0, 4.0, 5.0 } });
        Matriz segunda = new Matriz(new double[][] { { 2.0, 2.0, 1.5 }, { -3.0, 1.0, 2.0 } });
        Matriz suma = primera.suma(segunda);
        assertArrayEquals(new int[] { 2, 3 }, suma.getDimension());
        double[][] esperado = { { 3.0, 0.0, 2.0 }, { 0.0, 5.0, 7.0 } };
        for (int fila = 0; fila < 2; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                assertEquals(esperado[fila][columna], suma.getElemento(fila, columna), 1e-9);
            }
        }
    }

    @Test
    public void sumaRechazaArgumentoNull() {
        Matriz matriz = new Matriz(new double[][] { { 1.0 } });
        assertNull(matriz.suma(null));
    }

    @Test
    public void sumaRechazaDimensionesIncompatibles() {
        Matriz matriz = new Matriz(new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 } });
        assertNull(matriz.suma(new Matriz(new double[][] { { 1.0, 2.0 } })));
        assertNull(matriz.suma(new Matriz(new double[][] { { 1.0 }, { 2.0 } })));
    }

    /*
     * Comprobamos el caso más sencillo:
     * el constructor recibe una matriz rectangular.
     */
    @Test
    public void constructorMatrizRectangular() {

        double[][] datos = {
            { 1.0, 2.0, 3.0 },
            { 4.0, 5.0, 6.0 }
        };

        Matriz matriz = new Matriz(datos);

        assertArrayEquals(new int[] { 2, 3 }, matriz.getDimension());
        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(1, 2), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaCelda() {
        Matriz matriz = new Matriz(new double[][] { { 7.5 } });

        assertArrayEquals(new int[] { 1, 1 }, matriz.getDimension());
        assertEquals(7.5, matriz.getElemento(0, 0), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaFila() {
        Matriz matriz = new Matriz(new double[][] { { 2.0, 4.0, 6.0 } });

        assertArrayEquals(new int[] { 1, 3 }, matriz.getDimension());
        assertEquals(2.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(0, 2), 0.0);
    }

    @Test
    public void construyeMatrizDeUnaColumna() {
        Matriz matriz = new Matriz(new double[][] { { 2.0 }, { 4.0 }, { 6.0 } });

        assertArrayEquals(new int[] { 3, 1 }, matriz.getDimension());
        assertEquals(2.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(6.0, matriz.getElemento(2, 0), 0.0);
    }

    /*
     * Si las filas no tienen la misma longitud,
     * la matriz toma como número de columnas la longitud
     * de la fila más larga y completa con ceros.
     */
    @Test
    public void constructorRellenaConCeros() {

        double[][] datos = {
            { 1.0 },
            { 4.0, 5.0, 6.0 },
            { },
            { 7.0, 8.0 }
        };

        Matriz matriz = new Matriz(datos);

        assertArrayEquals(new int[] { 4, 3 }, matriz.getDimension());
        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(0.0, matriz.getElemento(0, 1), 0.0);
        assertEquals(4.0, matriz.getElemento(1, 0), 0.0);
        assertEquals(5.0, matriz.getElemento(1, 1), 0.0);
        assertEquals(6.0, matriz.getElemento(1, 2), 0.0);
        assertEquals(0.0, matriz.getElemento(2, 0), 0.0);
        assertEquals(0.0, matriz.getElemento(2, 2), 0.0);
        assertEquals(7.0, matriz.getElemento(3, 0), 0.0);
        assertEquals(8.0, matriz.getElemento(3, 1), 0.0);
        assertEquals(0.0, matriz.getElemento(3, 2), 0.0);
    }

    /*
     * El constructor debe copiar los datos recibidos.
     * Modificar posteriormente el array original no debe
     * modificar la matriz.
     */
    @Test
    public void constructorHaceUnaCopiaDeLosDatos() {

        double[][] datos = {
            { 1.0, 2.0 },
            { 3.0, 4.0 }
        };

        Matriz matriz = new Matriz(datos);
        datos[0][0] = 99.0;
        datos[1] = new double[] { 88.0 };

        assertEquals(1.0, matriz.getElemento(0, 0), 0.0);
        assertEquals(4.0, matriz.getElemento(1, 1), 0.0);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array null o vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizNull() {
        new Matriz(null);
    }  
    
    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizVacia() {
        new Matriz(new double[0][]);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * todos los arrays con filas vacías.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizConFilasVacias() {
        double[][] datos = {
            { },
            { }
        };

        new Matriz(datos);
    }

    /*
     * Comprobamos que el constructor lanza una excepción si recibe
     * un array con filas null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorMatrizConFilasNull() {
        double[][] datos = {
            { 1.0, 2.0 },
            null
        };

        new Matriz(datos);
    }
}
    
