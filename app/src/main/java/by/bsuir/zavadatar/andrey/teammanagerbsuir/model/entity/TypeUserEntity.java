package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.io.Serializable;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.TypeUserName;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TypeUserEntity implements Entity, Serializable {
    private int idType;
    private TypeUserName name;
    private int accessLevel;

    public TypeUserEntity() {
    }

    public TypeUserEntity(int idType, TypeUserName name, int accessLevel) {
        this.idType = idType;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameToString() {
        return name.name();
    }

    public void setNameToString(String name) {
        this.name = TypeUserName.valueOf(name);
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeUserEntity that = (TypeUserEntity) o;

        if (idType != that.idType) return false;
        if (accessLevel != that.accessLevel) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + accessLevel;
        return result;
    }

    @Override
    public String toString() {
        return "TypeUserEntity{" +
                "idType=" + idType +
                ", name='" + name + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
