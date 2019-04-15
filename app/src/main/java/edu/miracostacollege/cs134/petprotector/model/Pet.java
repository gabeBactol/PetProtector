package edu.miracostacollege.cs134.petprotector.model;

import android.net.Uri;

public class Pet
{
    private String mDetails;
    private int mId;
    private Uri mImageURI;
    private String mName;
    private String mPhone;

    public Pet(String details, Uri imageURI, String name, String phone) {
        mDetails = details;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    public Pet(String details, int id, Uri imageURI, String name, String phone) {
        mDetails = details;
        mId = id;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Uri getImageURI() {
        return mImageURI;
    }

    public void setImageURI(Uri imageURI) {
        mImageURI = imageURI;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "mDetails='" + mDetails + '\'' +
                ", mId=" + mId +
                ", mImageURI=" + mImageURI +
                ", mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                '}';
    }
}
