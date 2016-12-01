package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.Entity;

/**
 * Created by Andrey on 01.12.2016.
 */

public abstract class BaseCustomCursorWrapper<T extends Entity> extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public BaseCustomCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public abstract T getData();
}
