package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class PostCursorWrapper extends BaseCustomCursorWrapper<PostEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public PostCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public PostEntity getData(){

        PostEntity entity = new PostEntity();

        entity.setIdPost(getInt(getColumnIndex(PostTable.Colums.ID_POST)));
        entity.setNamePost(getString(getColumnIndex(PostTable.Colums.NAME_POST)));
        entity.setRate(getDouble(getColumnIndex(PostTable.Colums.RATE)));

        return entity;
    }
}
