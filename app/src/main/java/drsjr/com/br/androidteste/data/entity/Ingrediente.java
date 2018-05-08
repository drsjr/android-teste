package drsjr.com.br.androidteste.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junior on 5/5/18.
 */

public class Ingrediente implements Parcelable {

    private Long id;
    private String name;
    private Double price;
    private String image;

    public Ingrediente(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.price = in.readDouble();
        this.image = in.readString();
    }

    public Ingrediente(Long id, Double price)
    {
        this.id = id;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(image);

    }

    public static final Creator<Ingrediente> CREATOR = new ClassLoaderCreator<Ingrediente>() {
        @Override
        public Ingrediente createFromParcel(Parcel source, ClassLoader loader) {
            return new Ingrediente(source);
        }

        @Override
        public Ingrediente createFromParcel(Parcel source) {
            return new Ingrediente(source);
        }

        @Override
        public Ingrediente[] newArray(int size) {
            return new Ingrediente[size];
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
