package com.example.antoniocabezas.examenandroidamcm;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    private String name;
    private Integer number;
    private String email;

    public Contact(String name, Integer number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Contact(Parcel parcel) {
        name = parcel.readString();
        number = parcel.readInt();
        email = parcel.readString();
    }

    public Contact(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public Contact(Integer number) {
        this.number = number;
    }

    public Contact(String email) {
        this.email = email;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (number != null ? !number.equals(contact.number) : contact.number != null) return false;
        return email != null ? email.equals(contact.email) : contact.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(number);
        parcel.writeString(email);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }
        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }

    };

    @Override
    public String toString() {
        return name+", "+number+", "+email;
    }
}
