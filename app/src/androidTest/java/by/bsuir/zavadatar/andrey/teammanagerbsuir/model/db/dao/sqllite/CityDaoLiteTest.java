package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.ApplicationBaseHelperTest;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 01.12.2016.
 */
public class CityDaoLiteTest {

    private SQLiteDatabase mDataSource;
    private CountryDaoLite mCountryDaoLite;
    private CityDaoLite mCityDaoLite;

    @Before
    public void setUp(){
        //RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mDataSource = ApplicationBaseHelperTest.getInstance(InstrumentationRegistry.getTargetContext()).getWritableDatabase();

        mCountryDaoLite = new CountryDaoLite(mDataSource);

        mCityDaoLite = new CityDaoLite(mDataSource);

        createCountriesAndCities();
    }

    public void createCountriesAndCities(){
        CountryEntity entity = new CountryEntity(1, "Belarus", "BY", "375");

        CityEntity cityEntity = new CityEntity(1, "Minsk", "17", (int) mCountryDaoLite.create(entity));

        mCityDaoLite.create(cityEntity);
    }

    @After
    public void finish() {

        mCityDaoLite.deleteAll();
        mCountryDaoLite.deleteAll();

        mDataSource.close();
    }

    @Test
    public void create() throws Exception {

        List<CountryEntity> listCountry = mCountryDaoLite.reads();

        CityEntity cityEntity = new CityEntity(1, "Minsk", "17", listCountry.get(0).getIdCountry());

        List<CityEntity> list = mCityDaoLite.reads();

        int before = list.size();

        mCityDaoLite.create(cityEntity);

        list = mCityDaoLite.reads();

        int after = list.size();

        assertTrue(before < after);

    }

    @Test
    public void read() throws Exception {

        List<CityEntity> list = mCityDaoLite.reads();

        CityEntity entity = mCityDaoLite.read(list.get(0).getIdCity());

        assertTrue(entity.equals(list.get(0)));

    }

    @Test
    public void update() throws Exception {

        List<CityEntity> list = mCityDaoLite.reads();

        CityEntity entity = list.get(0);

        CityEntity updateEntity = mCityDaoLite.read(list.get(0).getIdCity());

        assertTrue(entity.equals(updateEntity));

        updateEntity.setName("New Name" + Math.random()*100);
        mCityDaoLite.update(updateEntity);

        updateEntity = mCityDaoLite.read(updateEntity.getIdCountry());

        assertTrue(!entity.equals(updateEntity));
    }

    @Test
    public void createList() throws Exception {

        List<CountryEntity> listCountry = mCountryDaoLite.reads();

        final int idCountry = listCountry.get(0).getIdCountry();

        //читаем все записи из базы
        List<CityEntity> list = mCityDaoLite.reads();
        //Сохраняем результат
        int before = list.size();

        list = new ArrayList<CityEntity>(){{
            add(new CityEntity(1, "Belarus", "BY", idCountry));
            add(new CityEntity(2, "Russia", "RU", idCountry));
            add(new CityEntity(3, "Ukraine", "UA", idCountry));
        }};

        //Создаем новые записи в базе
        mCityDaoLite.create(list);

        //читаем все страны из базы
        list = mCityDaoLite.reads();
        //Сохраняем результат
        int after = list.size();

        //Сравниваем, после добавления записей должно было стать больше
        assertTrue(before < after);

    }

    @Test
    public void delete() throws Exception {

        List<CountryEntity> listCountry = mCountryDaoLite.reads();

        final int idCountry = listCountry.get(0).getIdCountry();

        CityEntity cityEntity = new CityEntity(1, "Belarus", "BY", idCountry);
        //Вставляем в базу
        mCityDaoLite.create(cityEntity);
        //читаем все записи из базы
        List<CityEntity> list = mCityDaoLite.reads();
        //Сохраняем результат
        int before = list.size();

        mCityDaoLite.delete(list.get(0));
        //читаем все записи из базы
        list = mCityDaoLite.reads();
        //Сохраняем результат
        int after = list.size();

        assertTrue(before > after);
    }

    @Test
    public void reads() throws Exception {

        List<CountryEntity> listCountry = mCountryDaoLite.reads();

        final int idCountry = listCountry.get(0).getIdCountry();


        List<CityEntity> list;

        list = new ArrayList<CityEntity>(){{
            add(new CityEntity(1, "Belarus", "BY", idCountry));
            add(new CityEntity(2, "Russia", "RU", idCountry));
            add(new CityEntity(3, "Ukraine", "UA", idCountry));
        }};

        //Создаем новые записи в базе
        mCityDaoLite.create(list);

        //читаем все из базы
        list = mCityDaoLite.reads();
        //Сохраняем результат
        assertTrue(list.size() != 0);
    }

    @Test
    public void deleteAll() throws Exception {
        mCityDaoLite.deleteAll();
        List<CityEntity> rate = mCityDaoLite.reads();

        assertThat(rate.size(), is(0));
    }

}