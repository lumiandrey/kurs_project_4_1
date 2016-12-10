package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.io.Serializable;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TypeTaskEntity implements Entity, Serializable {
    private int idTypeTask;
    private String nameType;

    public TypeTaskEntity() {
    }

    public TypeTaskEntity(int idTypeTask, String nameType) {
        this.idTypeTask = idTypeTask;
        this.nameType = nameType;
    }

    public int getIdTypeTask() {
        return idTypeTask;
    }

    public void setIdTypeTask(int idTypeTask) {
        this.idTypeTask = idTypeTask;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeTaskEntity that = (TypeTaskEntity) o;

        if (idTypeTask != that.idTypeTask) return false;
        if (nameType != null ? !nameType.equals(that.nameType) : that.nameType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeTask;
        result = 31 * result + (nameType != null ? nameType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypeTaskEntity{" +
                "idTypeTask=" + idTypeTask +
                ", nameType='" + nameType + '\'' +
                '}';
    }
}
