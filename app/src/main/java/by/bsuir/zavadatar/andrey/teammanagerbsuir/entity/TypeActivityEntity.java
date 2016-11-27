package by.bsuir.zavadatar.andrey.teammanagerbsuir.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TypeActivityEntity {
    private int idTypeActivity;
    private String nameActivity;

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
}
