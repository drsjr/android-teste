package drsjr.com.br.androidteste.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junior on 5/5/18.
 */

public class Promocao implements Parcelable {

    private Long id;
    private String name;
    private String description;

    public Promocao(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    public static final Creator<Promocao> CREATOR = new ClassLoaderCreator<Promocao>() {
        @Override
        public Promocao createFromParcel(Parcel source, ClassLoader loader) {
            return new Promocao(source);
        }

        @Override
        public Promocao createFromParcel(Parcel source) {
            return new Promocao(source);
        }

        @Override
        public Promocao[] newArray(int size) {
            return new Promocao[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
