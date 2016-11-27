package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class DepartmentCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DepartmentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DepartmentEntity getDepartment(){

        DepartmentEntity entity = new DepartmentEntity();

        entity.setIdDepartment(getInt(getColumnIndex(DepartmentTable.Colums.ID_DEPARTMENT)));
        entity.setNameDepartment(getString(getColumnIndex(DepartmentTable.Colums.NAME_DEPARTMENT)));

        return entity;

    }

}
