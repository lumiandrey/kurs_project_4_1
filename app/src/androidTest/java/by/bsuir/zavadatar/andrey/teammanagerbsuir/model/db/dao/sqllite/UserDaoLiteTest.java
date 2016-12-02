package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.ApplicationBaseHelperTest;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.CryptoUtils;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 03.12.2016.
 */
public class UserDaoLiteTest {
    private SQLiteDatabase mDataSource;
    private UserDaoLite mDepartmentDaoLite;

    @Before
    public void setUp(){
        mDataSource = ApplicationBaseHelperTest.getInstance(InstrumentationRegistry.getTargetContext()).getWritableDatabase();
        mDepartmentDaoLite = new UserDaoLite(mDataSource);

    }

    @After
    public void tearDown() throws Exception {
        mDataSource.close();
    }

    @Test
    public void reads() throws Exception {

        List<UserEntity> entities = mDepartmentDaoLite.reads();

        for(UserEntity o: entities){
            System.out.println(o);
            switch (o.getLogin()){
                case "admin":{

                    assertTrue(o.getPassword().equals(CryptoUtils.getHasSHA("admin".getBytes())));
                }break;
                case "user":{
                    assertTrue(o.getPassword().equals(CryptoUtils.getHasSHA("user".getBytes())));
                }break;
            }
        }

        assertTrue(entities.size() != 0);
    }

}