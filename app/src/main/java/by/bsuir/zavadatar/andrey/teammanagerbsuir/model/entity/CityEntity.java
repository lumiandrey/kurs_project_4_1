package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class CityEntity implements Entity {
    private int idCity;
    private String name;
    private String codePhone;
    private int idCountry;

    public CityEntity() {
    }

    public CityEntity(int idCity, String name, String codePhone, int idCountry) {
        this.idCity = idCity;
        this.name = name;
        this.codePhone = codePhone;
        this.idCountry = idCountry;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodePhone() {
        return codePhone;
    }

    public void setCodePhone(String codePhone) {
        this.codePhone = codePhone;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (idCity != that.idCity) return false;
        if (idCountry != that.idCountry) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (codePhone != null ? !codePhone.equals(that.codePhone) : that.codePhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codePhone != null ? codePhone.hashCode() : 0);
        result = 31 * result + idCountry;
        return result;
    }
}
