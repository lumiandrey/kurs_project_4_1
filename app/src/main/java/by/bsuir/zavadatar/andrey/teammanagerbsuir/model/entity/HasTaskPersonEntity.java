package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.io.Serializable;

/**
 * Created by Andrey on 27.11.2016.
 */
public class HasTaskPersonEntity implements Entity, Serializable {
    private int idHasTaskPerson;
    private int idTask;
    private int idPerson;

    public HasTaskPersonEntity() {
    }

    public HasTaskPersonEntity(int idHasTaskPerson, int idTask, int idPerson) {
        this.idHasTaskPerson = idHasTaskPerson;
        this.idTask = idTask;
        this.idPerson = idPerson;
    }

    public int getIdHasTaskPerson() {
        return idHasTaskPerson;
    }

    public void setIdHasTaskPerson(int idHasTaskPerson) {
        this.idHasTaskPerson = idHasTaskPerson;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HasTaskPersonEntity that = (HasTaskPersonEntity) o;

        if (idHasTaskPerson != that.idHasTaskPerson) return false;
        if (idTask != that.idTask) return false;
        if (idPerson != that.idPerson) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHasTaskPerson;
        result = 31 * result + idTask;
        result = 31 * result + idPerson;
        return result;
    }

    @Override
    public String toString() {
        return "HasTaskPersonEntity{" +
                "idHasTaskPerson=" + idHasTaskPerson +
                ", idTask=" + idTask +
                ", idPerson=" + idPerson +
                '}';
    }
}
