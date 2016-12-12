package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.PostCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.PostDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.PostTable;

/**
 * Created by Andrey on 02.12.2016.
 */

public class PostDaoLite extends AbstractDaoBase<PostEntity> implements PostDao {

    public static final String TAG = PostDaoLite.class.getName();
    public static final String NAME_TABLE = PostTable.NAME;
    public static final String ID_WHERE = PostTable.Colums.ID_POST;

    public PostDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public PostDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    public PostDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(PostEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public PostEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public PostEntity update(PostEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdPost())});
    }

    @Override
    public int delete(PostEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdPost())});
    }

    @Override
    protected ContentValues getContentValuesNotId(PostEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(PostTable.Colums.NAME_POST, userEntity.getNamePost());
        values.put(PostTable.Colums.RATE, userEntity.getRate());


        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(PostEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdPost());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<PostEntity> getCursorWrapper(Cursor cursor) {
        return new PostCursorWrapper(cursor);
    }

    @Override
    public List<PostEntity> create(List<PostEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<PostEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
