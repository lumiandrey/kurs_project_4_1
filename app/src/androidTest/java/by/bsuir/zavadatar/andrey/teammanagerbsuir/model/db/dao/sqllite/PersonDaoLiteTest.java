package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.ApplicationBaseHelperTest;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.PersonDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Andrey on 04.12.2016.
 */
public class PersonDaoLiteTest {
    private SQLiteDatabase mDataSource;
    private PersonDao mPersonDao;

    @Before
    public void setUp(){
        mDataSource = ApplicationBaseHelperTest.getInstance(InstrumentationRegistry.getTargetContext()).getWritableDatabase();
        mPersonDao = new PersonDaoLite(mDataSource);

    }

    @After
    public void tearDown() throws Exception {
        mDataSource.close();
    }

    @Test
    public void reads() throws Exception {

        List<PersonEntity> entities = mPersonDao.reads();

        for(PersonEntity o: entities){
            Log.d("TEST_PERSON", o.toString());
        }

        assertTrue(entities.size() != 0);
    }
}