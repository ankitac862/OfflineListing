package com.anki.offlinelisting.local.entity;

import com.anki.offlinelisting.BR;
import com.anki.offlinelisting.local.convertor.DateConverter;
import com.anki.offlinelisting.remote.pojo.Dob;
import com.anki.offlinelisting.remote.pojo.Id;
import com.anki.offlinelisting.remote.pojo.Location;
import com.anki.offlinelisting.remote.pojo.Login;
import com.anki.offlinelisting.remote.pojo.Name;
import com.anki.offlinelisting.remote.pojo.Picture;
import com.anki.offlinelisting.remote.pojo.Registered;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Table pojo
 */

@Entity(tableName = "member")
public class Member extends BaseObservable {
    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Bindable
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    @SerializedName("memberId")
    private long memberId;
    private String gender;
    @Embedded(prefix = "name_")
    private Name name;
    @Embedded(prefix = "location_")
    private Location location;
    private String email;
    @Embedded(prefix = "login_")
    private Login login;
    @Embedded(prefix = "dob_")
    private Dob dob;
    @Embedded(prefix = "registered_")
    private Registered registered;
    private String phone;
    private String cell;
    @Embedded(prefix = "id_")
    private Id id;
    @Embedded(prefix = "picture_")
    private Picture picture;
    private String nat;
    private int status;
    @TypeConverters(DateConverter.class)
    private Date date;


}
