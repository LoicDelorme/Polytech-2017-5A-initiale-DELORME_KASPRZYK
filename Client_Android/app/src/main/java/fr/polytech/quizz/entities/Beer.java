package fr.polytech.quizz.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Beer implements Parcelable, Serializable {

    private String name;

    private String first_brewed;

    private String description;

    private String image_url;

    private double abv;

    private double ibu;

    private String brewers_tips;

    protected Beer(Parcel in) {
        name = in.readString();
        first_brewed = in.readString();
        description = in.readString();
        image_url = in.readString();
        abv = in.readDouble();
        ibu = in.readDouble();
        brewers_tips = in.readString();
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public String getBrewers_tips() {
        return brewers_tips;
    }

    public void setBrewers_tips(String brewers_tips) {
        this.brewers_tips = brewers_tips;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(first_brewed);
        dest.writeString(description);
        dest.writeString(image_url);
        dest.writeDouble(abv);
        dest.writeDouble(ibu);
        dest.writeString(brewers_tips);
    }
}