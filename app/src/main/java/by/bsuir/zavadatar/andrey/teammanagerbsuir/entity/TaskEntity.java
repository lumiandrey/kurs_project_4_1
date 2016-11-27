package by.bsuir.zavadatar.andrey.teammanagerbsuir.entity;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TaskEntity {
    private Integer idTask;
    private String name;
    private String description;
    private String dateBegin;
    private String dateEnd;
    private Integer done;
    private Integer progress;
    private int idTypeTask;
    private int idPersonAdd;

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public int getIdTypeTask() {
        return idTypeTask;
    }

    public void setIdTypeTask(int idTypeTask) {
        this.idTypeTask = idTypeTask;
    }

    public int getIdPersonAdd() {
        return idPersonAdd;
    }

    public void setIdPersonAdd(int idPersonAdd) {
        this.idPersonAdd = idPersonAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (idTypeTask != that.idTypeTask) return false;
        if (idPersonAdd != that.idPersonAdd) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (done != null ? !done.equals(that.done) : that.done != null) return false;
        if (progress != null ? !progress.equals(that.progress) : that.progress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTask != null ? idTask.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (done != null ? done.hashCode() : 0);
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + idTypeTask;
        result = 31 * result + idPersonAdd;
        return result;
    }
}
