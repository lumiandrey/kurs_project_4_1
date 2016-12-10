package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.io.Serializable;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TypeActivityEntity implements Entity, Serializable {
    private int idTypeActivity;
    private String nameActivity;

    public TypeActivityEntity() {
    }

    public TypeActivityEntity(int idTypeActivity, String nameActivity) {
        this.idTypeActivity = idTypeActivity;
        this.nameActivity = nameActivity;
    }

    public int getIdTypeActivity() {
        return idTypeActivity;
    }

    public void setIdTypeActivity(int idTypeActivity) {
        this.idTypeActivity = idTypeActivity;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeActivityEntity that = (TypeActivityEntity) o;

        if (idTypeActivity != that.idTypeActivity) return false;
        if (nameActivity != null ? !nameActivity.equals(that.nameActivity) : that.nameActivity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeActivity;
        result = 31 * result + (nameActivity != null ? nameActivity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypeActivityEntity{" +
                "idTypeActivity=" + idTypeActivity +
                ", nameActivity='" + nameActivity + '\'' +
                '}';
    }
}
