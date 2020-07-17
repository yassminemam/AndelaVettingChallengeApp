
package com.assessment.challengeapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotosSearchResponse implements Parcelable
{

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;
    public final static Creator<PhotosSearchResponse> CREATOR = new Creator<PhotosSearchResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PhotosSearchResponse createFromParcel(Parcel in) {
            return new PhotosSearchResponse(in);
        }

        public PhotosSearchResponse[] newArray(int size) {
            return (new PhotosSearchResponse[size]);
        }

    }
    ;

    protected PhotosSearchResponse(Parcel in) {
        this.photos = ((Photos) in.readValue((Photos.class.getClassLoader())));
        this.stat = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PhotosSearchResponse() {
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(photos);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return  0;
    }

}
