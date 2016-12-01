package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class CountryEntity implements Entity{
    private int idCountry;
    private String nameCountry;
    private String keyContry;
    private String keyPhone;

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getKeyContry() {
        return keyContry;
    }

    public void setKeyContry(String keyContry) {
        this.keyContry = keyContry;
    }

    public String getKeyPhone() {
        return keyPhone;
    }

    public void setKeyPhone(String keyPhone) {
        this.keyPhone = keyPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (idCountry != that.idCountry) return false;
        if (nameCountry != null ? !nameCountry.equals(that.nameCountry) : that.nameCountry != null) return false;
        if (keyContry != null ? !keyContry.equals(that.keyContry) : that.keyContry != null) return false;
        if (keyPhone != null ? !keyPhone.equals(that.keyPhone) : that.keyPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + (nameCountry != null ? nameCountry.hashCode() : 0);
        result = 31 * result + (keyContry != null ? keyContry.hashCode() : 0);
        result = 31 * result + (keyPhone != null ? keyPhone.hashCode() : 0);
        return result;
    }
}
