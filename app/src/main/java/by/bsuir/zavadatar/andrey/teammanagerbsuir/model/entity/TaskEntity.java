package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.DateConvert;

/**
 * Created by Andrey on 27.11.2016.
 */
public class TaskEntity implements Entity, Serializable {

    private static final String TAG = TaskEntity.class.getName();

    private Integer idTask;
    private String name;
    private String description;
    private Date dateBegin;
    private Date dateEnd;
    private Integer done;
    private Integer progress;
    private int idTypeTask;
    private int idPersonAdd;

    public TaskEntity(){
    }

    public TaskEntity(Integer idTask, String name, String description, Date dateBegin, Date dateEnd, Integer done, Integer progress, int idTypeTask, int idPersonAdd) {
        this.idTask = idTask;
        this.name = name;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.done = done;
        this.progress = progress;
        this.idTypeTask = idTypeTask;
        this.idPersonAdd = idPersonAdd;
    }

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

    public Date getDateBegin() {
        return dateBegin;
    }

    public String getDateBeginString(){
        String date;
        if(dateBegin != null)
            date = DateConvert.getDateAndTimeToString(dateBegin);
        else date = "Date begin";
        return date;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        try {
            this.dateBegin = DateConvert.getDateAndTime(dateBegin);
        } catch (ParseException e) {
            Log.e(TAG, "Error parse date");
            this.dateBegin = null;
        }
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getDateEndString(){
        String date;
        if(dateBegin != null)
            date = DateConvert.getDateAndTimeToString(dateEnd);
        else date = "Date end";
        return date;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateEnd(String dateEnd) {

        try {
            this.dateEnd = DateConvert.getDateAndTime(dateEnd);
        } catch (ParseException e) {
            Log.e(TAG, "Error parse date");
            this.dateEnd = null;
        }
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

    public boolean isDone(){
        return done != 0;
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

    @Override
    public String toString() {
        return "TaskEntity{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", done=" + done +
                ", progress=" + progress +
                ", idTypeTask=" + idTypeTask +
                ", idPersonAdd=" + idPersonAdd +
                '}';
    }
}
