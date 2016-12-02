package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class LogTimeTaskEntity implements Entity{
    private int idLog;
    private String dateLog;
    private String discription;
    private String linksExtStor;
    private int idTypeActivity;
    private int idHasTaskPerson;

    public LogTimeTaskEntity() {
    }

    public LogTimeTaskEntity(int idLog,
                             String dateLog,
                             String discription,
                             String linksExtStor,
                             int idTypeActivity,
                             int idHasTaskPerson) {
        this.idLog = idLog;
        this.dateLog = dateLog;
        this.discription = discription;
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

    public String getDateLog() {
        return dateLog;
    }

    public void setDateLog(String dateLog) {
        this.dateLog = dateLog;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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
        if (discription != null ? !discription.equals(that.discription) : that.discription != null) return false;
        if (linksExtStor != null ? !linksExtStor.equals(that.linksExtStor) : that.linksExtStor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLog;
        result = 31 * result + (dateLog != null ? dateLog.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
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
                ", discription='" + discription + '\'' +
                ", linksExtStor='" + linksExtStor + '\'' +
                ", idTypeActivity=" + idTypeActivity +
                ", idHasTaskPerson=" + idHasTaskPerson +
                '}';
    }
}
