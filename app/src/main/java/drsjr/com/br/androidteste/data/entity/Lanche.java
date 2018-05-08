package drsjr.com.br.androidteste.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by junior on 5/5/18.
 */

public class Lanche implements Parcelable {

    private Long id;
    private String name;
    private Long[] ingredients;
    private String image;
    private Long[] extras;

    public Lanche(){

    }

    public Lanche(Parcel in) {
        id = in.readLong();
        name = in.readString();
        ingredients = (Long[]) in.readArray(Ingrediente.class.getClassLoader());
        image = in.readString();
        extras = (Long[]) in.readArray(Ingrediente.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeArray(ingredients);
        dest.writeString(image);
        dest.writeArray(extras);
    }

    public static final Creator<Lanche> CREATOR = new ClassLoaderCreator<Lanche>() {
        @Override
        public Lanche createFromParcel(Parcel source, ClassLoader loader) {
            return new Lanche(source);
        }

        @Override
        public Lanche createFromParcel(Parcel source) {
            return new Lanche(source);
        }

        @Override
        public Lanche[] newArray(int size) {
            return new Lanche[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Long[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long[] getExtras() {
        return extras;
    }

    public void setExtras(Long[] extras) {
        this.extras = extras;
    }


    public Double getPrice(List<Ingrediente> lista) {
        Double price = new Double("0.0");
        for(Ingrediente ing : lista)
            price += setIngrediente(ing);
        price = tenPercent(price);
        return price;
    }


    private Double setIngrediente(Ingrediente ingr) {
        int count = 0;

        for(Long i : returnAll()){
            if(ingr.getId() == i){
                count++;
            }
        }

        if((ingr.getId() == 5|| ingr.getId() == 3) && count > 3)
            return moreThenThree(ingr.getPrice(), count);

        return (count * ingr.getPrice());

    }

    private Double tenPercent(Double price) {
        boolean t = false, n = false;
        for(Long i : returnAll() ) {
            if (i == 3) t = !t;
            if (i == 1) n = !n;
            if (t && n) return price *(0.9);
        }
        return price;
    }

    private static double moreThenThree(Double preco, int quantidade) {
        return ((quantidade/3) * (2*preco.doubleValue())
                + (quantidade%3)*preco.doubleValue());
    }

    private Long[] returnAll() {
        Long[] all =  new Long[ingredients.length + extras.length];
        int count = 0;

        for(Long i : ingredients){
            all[count] = i;
            count++;
        }

        if(extras.length > 0){
            for(Long i : extras){
                all[count] = i;
                count++;
            }
        }

        return all;
    }
}
