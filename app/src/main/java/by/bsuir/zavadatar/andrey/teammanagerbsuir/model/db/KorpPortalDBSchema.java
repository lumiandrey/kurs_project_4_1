package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

/**
 * Created by Andrey on 27.11.2016.
 */

public class KorpPortalDBSchema {

    public static final class CountryTable {

        public static final String NAME = "country";

        public static final class Colums{
            public static final String ID_COUNTRY = "_id_country";
            public static final String NAME_COUNTRY = "name_country";
            public static final String KEY_CONTRY = "key_contry";
            public static final String KEY_PHONE = "key_phone";
        }

    }

    public static final class CityTable {

        public static final String NAME = "city";

        public static final class Colums {
            public static final String ID_CITY = "_id_city";
            public static final String NAME = "name";
            public static final String CODE_PHONE = "code_phone";
            public static final String ID_COUNTRY = "id_country";
        }

        public static final String REF_COUNTRY =  "FOREIGN KEY (" + Colums.ID_COUNTRY +
                ") REFERENCES " + CountryTable.NAME + '(' + CountryTable.Colums.ID_COUNTRY +')';

    }

    public static final class DepartmentTable {

        public static final String NAME = "department";

        public static final class Colums {
            public static final String ID_DEPARTMENT = "_id_department";
            public static final String NAME_DEPARTMENT = "name_department";
        }


    }

    public static final class PostTable {

        public static final String NAME = "post";

        public static final class Colums {
            public static final String ID_POST = "_id_post";
            public static final String NAME_POST = "name_post";
            public static final String RATE = "rate";
        }


    }

    public static final class TypeUserTable {

        public static final String NAME = "type_user";

        public static final class Colums {
            public static final String ID_TYPE = "_id_type";
            public static final String NAME = "name";
            public static final String ACCESS_LEVEL = "access_level";
        }


    }

    public static final class UserTable {

        public static final String NAME = "user";

        public static final class Colums {
            public static final String ID_USER = "_id_user";
            public static final String LOGIN = "login";
            public static final String PASSWORD = "password";
            public static final String ID_TYPE_USER = "id_type_user";
        }

        public static final String REF_TYPE_USER = "FOREIGN KEY (" + Colums.ID_TYPE_USER +
                ") REFERENCES " + TypeUserTable.NAME + '(' + TypeUserTable.Colums.ID_TYPE +')';

    }

    public static final class PersonTable {

        public static final String NAME = "person";

        public static final class Colums {
            public static final String ID_PERSON = "_id_person";
            public static final String SURNAME = "surname";
            public static final String NAME = "name";
            public static final String PATRONYMIC = "patronymic";
            public static final String DATE_OF_BIRTH = "date_of_birth";
            public static final String SEX = "sex";
            public static final String E_MAIL = "e_mail";
            public static final String ID_USER = "id_user";
            public static final String ID_POST = "id_post";
            public static final String ID_DEPARTMENT = "id_department";
            public static final String ID_CITY = "id_city";
            public static final String MOBILE_PHONE = "mobile_phone";
            public static final String HOME_PHONE = "home_phone";
        }

        public static final String REF_USER = "FOREIGN KEY (" + Colums.ID_USER +
                ") REFERENCES " + UserTable.NAME + '(' + UserTable.Colums.ID_USER +')';
        public static final String REF_POST = "FOREIGN KEY (" + Colums.ID_POST +
                ") REFERENCES " + PostTable.NAME + '(' + PostTable.Colums.ID_POST +')';
        public static final String REF_DEPARTMENT = "FOREIGN KEY (" + Colums.ID_DEPARTMENT +
                ") REFERENCES " + DepartmentTable.NAME + '(' + DepartmentTable.Colums.ID_DEPARTMENT +')';
        public static final String REF_CITY = "FOREIGN KEY (" + Colums.ID_CITY +
                ") REFERENCES " + CityTable.NAME + '(' + CityTable.Colums.ID_CITY +')';

    }

    public static final class TypeTaskTable {

        public static final String NAME = "type_task";

        public static final class Colums {
            public static final String ID_TYPE_TASK = "_id_type_task";
            public static final String NAME_TYPE = "name_type";
        }


    }

    public static final class TaskTable {

        public static final String NAME = "task";

        public static final class Colums {
            public static final String ID_TASK = "_id_task";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String DATE_BEGIN = "date_begin";
            public static final String DATE_END = "date_end";
            public static final String DONE = "done";
            public static final String PROGRESS = "progress";
            public static final String ID_TYPE_TASK = "id_type_task";
            public static final String ID_PERSON_ADD = "id_person_add";
        }

        public static final String REF_TYPE_TASK = "FOREIGN KEY (" + Colums.ID_TYPE_TASK +
                ") REFERENCES " + TypeTaskTable.NAME + '(' + TypeTaskTable.Colums.ID_TYPE_TASK +')';
        public static final String REF_PERSON_ADD = "FOREIGN KEY (" + Colums.ID_PERSON_ADD +
                ") REFERENCES " + PersonTable.NAME + '(' + PersonTable.Colums.ID_PERSON +')';

    }

    public static final class HasTaskPersonTable {

        public static final String NAME = "has_task_person";

        public static final class Colums {
            public static final String ID_HAS_TASK_PERSON = "_id_has_task_person";
            public static final String ID_TASK = "_id_task";
            public static final String ID_PERSON = "id_person";
        }

        public static final String REF_PERSON = "FOREIGN KEY (" + Colums.ID_PERSON +
                ") REFERENCES " + PersonTable.NAME + '(' + PersonTable.Colums.ID_PERSON +')';
        public static final String REF_TASK = "FOREIGN KEY (" + Colums.ID_TASK +
                ") REFERENCES " + TaskTable.NAME + '(' + TaskTable.Colums.ID_TASK +')';


    }

    public static final class TypeActivityTable {

        public static final String NAME = "type_activity";

        public static final class Colums {
            public static final String ID_TYPE_ACTIVITY = "_id_type_activity";
            public static final String NAME_ACTIVITY = "name_activity";
        }


    }

    public static final class LogTimeTaskTable {

        public static final String NAME = "log_time_task";

        public static final class Colums {
            public static final String ID_LOG = "_id_log";
            public static final String DATE_LOG = "date_log";
            public static final String DESCRIPTION = "";
            public static final String LINKS_EXT_STOR = "links_ext_stor";
            public static final String ID_TYPE_ACTIVITY = "id_type_activity";
            public static final String ID_HAS_TASK_PERSON = "id_has_task_person";
        }

        public static final String REF_TYPE_ACTIVITY = "FOREIGN KEY (" + Colums.ID_TYPE_ACTIVITY +
                ") REFERENCES " + TypeActivityTable.NAME + '(' + TypeActivityTable.Colums.ID_TYPE_ACTIVITY +')';
        public static final String REF_HAS_TASK_PERSON = "FOREIGN KEY (" + Colums.ID_HAS_TASK_PERSON +
                ") REFERENCES " + HasTaskPersonTable.NAME + '(' + HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON +')';


    }

}
