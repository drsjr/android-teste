package drsjr.com.br.androidteste;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestePromocao {

    private List<Ingrediente> lista;
    private Lanche lanche;


    public TestePromocao() {

        lista = new ArrayList<>();
        lista.add(new Ingrediente(1L, new Double("0.4"))); //Alface
        lista.add(new Ingrediente(2L, new Double("2.0"))); //Bacon
        lista.add(new Ingrediente(3L, new Double("3.0"))); //Hamburger
        lista.add(new Ingrediente(4L, new Double("0.8"))); //Ovo
        lista.add(new Ingrediente(5L, new Double("1.5"))); //Queijo
        lista.add(new Ingrediente(6L, new Double("1.0"))); //Pao

    }


    @Test
    public void retorn_price_with_not_promocao() throws Exception {
        //4.2

        lanche = new Lanche();
        lanche.setIngredients(new Long[]{1l, 2l, 6l, 4l});
        lanche.setExtras(new Long[]{});

        Double price  = lanche.getPrice(lista);
        assertEquals(4.2d, price.doubleValue(), 2);
    }


    @Test
    public void return_price_with_alface_promocao() {
        //5.2

        lanche = new Lanche();
        lanche.setIngredients(new Long[]{1l, 6l, 4l, 3l});
        lanche.setExtras(new Long[]{});

        Double price  = lanche.getPrice(lista);
        Double expected = new Double(5.2*0.9);
        assertEquals(expected, price.doubleValue(), 2);
    }

    @Test
    public void return_price_with_carne_promocao() {
        //9.8

        lanche = new Lanche();
        lanche.setIngredients(new Long[]{2l, 6l, 4l, 3l });
        lanche.setExtras(new Long[]{3l, 3l});

        Double price  = lanche.getPrice(lista);
        assertEquals(9.8, price.doubleValue(), 2);
    }


    @Test
    public void return_price_with_alface_and_carne_promocao() {
        //8.4

        lanche = new Lanche();
        lanche.setIngredients(new Long[]{1l, 6l, 4l, 3l });
        lanche.setExtras(new Long[]{3l, 3l});

        Double price  = lanche.getPrice(lista);
        double value = (8.2*.9);
        assertEquals(value, price.doubleValue(), 2);
    }



}