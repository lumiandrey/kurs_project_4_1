package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.ApplicationBaseHelperTest;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 01.12.2016.
 */
@RunWith(AndroidJUnit4.class)
public class CountryDaoLiteTest {

    private SQLiteDatabase mDataSource;
    private CountryDaoLite mCountryDaoLite;

    @Before
    public void setUp(){
        //RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mDataSource = ApplicationBaseHelperTest.getInstance(InstrumentationRegistry.getTargetContext()).getWritableDatabase();
        mCountryDaoLite = new CountryDaoLite(mDataSource);

    }

    @After
    public void finish() {
        mCountryDaoLite.deleteAll();

        mDataSource.close();
    }

    @Test
    public void create() throws Exception {

        CountryEntity entity = new CountryEntity(1, "Belarus", "BY", "375");

        List<CountryEntity> list = mCountryDaoLite.reads();

        int before = list.size();

        mCountryDaoLite.create(entity);

        list = mCountryDaoLite.reads();

        int after = list.size();

        assertTrue(before < after);

    }

    @Test
    public void read() throws Exception {

        create();

        List<CountryEntity> list = mCountryDaoLite.reads();

        CountryEntity entity = mCountryDaoLite.read(list.get(0).getIdCountry());

        assertTrue(entity.equals(list.get(0)));

    }

    @Test
    public void update() throws Exception {

        create();

        List<CountryEntity> list = mCountryDaoLite.reads();

        CountryEntity entity = list.get(0);

        CountryEntity updateEntity = mCountryDaoLite.read(list.get(0).getIdCountry());

        assertTrue(entity.equals(updateEntity));

        updateEntity.setNameCountry("New Name" + Math.random()*100);
        mCountryDaoLite.update(updateEntity);

        updateEntity = mCountryDaoLite.read(updateEntity.getIdCountry());

        assertTrue(!entity.equals(updateEntity));
    }

    @Test
    public void createList() throws Exception {

        //читаем все записи из базы
        List<CountryEntity> list = mCountryDaoLite.reads();
        //Сохраняем результат
        int before = list.size();

        list = new ArrayList<CountryEntity>(){{
            add(new CountryEntity(1, "Belarus", "BY", "375"));
            add(new CountryEntity(2, "Russia", "RU", "9"));
            add(new CountryEntity(3, "Ukraine", "UA", "65"));
        }};

        //Создаем новые записи в базе
        mCountryDaoLite.create(list);

        //читаем все страны из базы
        list = mCountryDaoLite.reads();
        //Сохраняем результат
        int after = list.size();

        //Сравниваем, после добавления записей должно было стать больше
        assertTrue(before < after);

    }

    @Test
    public void delete() throws Exception {

        CountryEntity countryEntity = new CountryEntity(1, "Belarus", "BY", "375");
        //Вставляем в базу
        mCountryDaoLite.create(countryEntity);
        //читаем все записи из базы
        List<CountryEntity> list = mCountryDaoLite.reads();
        //Сохраняем результат
        int before = list.size();

        mCountryDaoLite.delete(list.get(0));
        //читаем все записи из базы
        list = mCountryDaoLite.reads();
        //Сохраняем результат
        int after = list.size();

        assertTrue(before > after);
    }

    @Test
    public void reads() throws Exception {

        createList();

        //читаем все страны из базы
        List<CountryEntity> list = mCountryDaoLite.reads();
        //Сохраняем результат
        assertTrue(list.size() != 0);
    }

    @Test
    public void deleteAll() throws Exception {
        mCountryDaoLite.deleteAll();
        List<CountryEntity> rate = mCountryDaoLite.reads();

        assertThat(rate.size(), is(0));
    }

}