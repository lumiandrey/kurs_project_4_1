package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import java.io.Serializable;
import java.util.Date;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.DateConvert;

/**
 * Created by Andrey on 27.11.2016.
 */
public class LogTimeTaskEntity implements Entity, Serializable {
    private int idLog;
    private Date dateLog;
    private String description;
    private float hours;
    private String linksExtStor;
    private int idTypeActivity;
    private int idHasTaskPerson;

    public LogTimeTaskEntity() {
        dateLog = new Date();
        linksExtStor = "http://";
    }

    public LogTimeTaskEntity(int idLog,
                             Date dateLog,
                             String description,
                             float hours,
                             String linksExtStor,
                             int idTypeActivity,
                             int idHasTaskPerson) {
        this.idLog = idLog;
        this.dateLog = dateLog;
        this.description = description;
        this.hours = hours;
        this.linksExtStor = linksExtStor;
        this.idTypeActivity = idTypeActivity;
        this.idHasTaskPerson = idHasTaskPerson;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getDateLogString() {
        return DateConvert.getDateToString(dateLog);
    }

    public Date getDateLog() {
        return dateLog;
    }

    public void setDateLog(Date dateLog) {
        this.dateLog = dateLog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinksExtStor() {
        return linksExtStor;
    }

    public void setLinksExtStor(String linksExtStor) {
        this.linksExtStor = linksExtStor;
    }

    public int getIdTypeActivity() {
        return idTypeActivity;
    }

    public void setIdTypeActivity(int idTypeActivity) {
        this.idTypeActivity = idTypeActivity;
    }

    public int getIdHasTaskPerson() {
        return idHasTaskPerson;
    }

    public void setIdHasTaskPerson(int idHasTaskPerson) {
        this.idHasTaskPerson = idHasTaskPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogTimeTaskEntity that = (LogTimeTaskEntity) o;

        if (idLog != that.idLog) return false;
        if (idTypeActivity != that.idTypeActivity) return false;
        if (idHasTaskPerson != that.idHasTaskPerson) return false;
        if (dateLog != null ? !dateLog.equals(that.dateLog) : that.dateLog != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (linksExtStor != null ? !linksExtStor.equals(that.linksExtStor) : that.linksExtStor != null) return false;

        return true;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    @Override
    public int hashCode() {
        int result = idLog;
        result = 31 * result + (dateLog != null ? dateLog.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (linksExtStor != null ? linksExtStor.hashCode() : 0);
        result = 31 * result + idTypeActivity;
        result = 31 * result + idHasTaskPerson;
        return result;
    }

    @Override
    public String toString() {
        return "LogTimeTaskEntity{" +
                "idLog=" + idLog +
                ", dateLog='" + dateLog + '\'' +
                ", description='" + description + '\'' +
                ", linksExtStor='" + linksExtStor + '\'' +
                ", idTypeActivity=" + idTypeActivity +
                ", idHasTaskPerson=" + idHasTaskPerson +
                '}';
    }
}
