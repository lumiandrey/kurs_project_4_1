package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class DepartmentEntity {
    private int idDepartment;
    private String nameDepartment;

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (idDepartment != that.idDepartment) return false;
        if (nameDepartment != null ? !nameDepartment.equals(that.nameDepartment) : that.nameDepartment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDepartment;
        result = 31 * result + (nameDepartment != null ? nameDepartment.hashCode() : 0);
        return result;
    }
}
