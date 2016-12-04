package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.text.ParseException;
import java.util.Date;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.DateConvert;

/**
 * Created by Andrey on 27.11.2016.
 */
public class PersonEntity implements Entity{
    private int idPerson;
    private String surname;
    private String name;
    private String patronymic;
    private Date dateOfBirth;
    private Sex sex;
    private String eMail;
    private int idUser;
    private int idPost;
    private int idDepartment;
    private int idCity;
    private String mobilePhone;
    private String homePhone;

    public PersonEntity() {
    }

    public PersonEntity(int idPerson,
                        String surname,
                        String name,
                        String patronymic,
                        String dateOfBirth,
                        Sex sex,
                        String eMail,
                        int idUser,
                        int idPost,
                        int idDepartment,
                        int idCity,
                        String mobilePhone,
                        String homePhone) throws ParseException {
        this.idPerson = idPerson;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = DateConvert.getDate(dateOfBirth);
        this.sex = sex;
        this.eMail = eMail;
        this.idUser = idUser;
        this.idPost = idPost;
        this.idDepartment = idDepartment;
        this.idCity = idCity;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirthToString() {
        return DateConvert.getDateToString(dateOfBirth);
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex.name();
    }

    public void setSex(String sex) {
        this.sex = Sex.valueOf(sex);
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (idPerson != that.idPerson) return false;
        if (idUser != that.idUser) return false;
        if (idPost != that.idPost) return false;
        if (idDepartment != that.idDepartment) return false;
        if (idCity != that.idCity) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPerson;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + idUser;
        result = 31 * result + idPost;
        result = 31 * result + idDepartment;
        result = 31 * result + idCity;
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "idPerson=" + idPerson +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", eMail='" + eMail + '\'' +
                ", idUser=" + idUser +
                ", idPost=" + idPost +
                ", idDepartment=" + idDepartment +
                ", idCity=" + idCity +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                '}';
    }
}
