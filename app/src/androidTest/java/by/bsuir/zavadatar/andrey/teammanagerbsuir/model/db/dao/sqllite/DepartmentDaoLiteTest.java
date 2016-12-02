package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.ApplicationBaseHelperTest;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Andrey on 02.12.2016.
 */
@RunWith(AndroidJUnit4.class)
public class DepartmentDaoLiteTest {

    private SQLiteDatabase mDataSource;
    private DepartmentDaoLite mDepartmentDaoLite;

    @Before
    public void setUp(){
        mDataSource = ApplicationBaseHelperTest.getInstance(InstrumentationRegistry.getTargetContext()).getWritableDatabase();
        mDepartmentDaoLite = new DepartmentDaoLite(mDataSource);

    }

    @After
    public void tearDown() throws Exception {
        mDataSource.close();
    }

    @Test
    public void reads() throws Exception {

        List<DepartmentEntity> entities = mDepartmentDaoLite.reads();

        for(DepartmentEntity o: entities){
            System.out.println(o);
        }

        assertTrue(entities.size() != 0);
    }

}