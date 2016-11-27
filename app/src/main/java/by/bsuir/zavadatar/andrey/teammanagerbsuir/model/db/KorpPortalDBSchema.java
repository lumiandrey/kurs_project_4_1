package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

/**
 * Created by Andrey on 27.11.2016.
 */

public class KorpPortalDBSchema {

    public static final class CountryTable {

        public static final String NAME = "";

        public static final class Colums{
            public static final String ID_COUNTRY = "id_country";
            public static final String NAME_COUNTRY = "name_country";
            public static final String KEY_CONTRY = "key_contry";
            public static final String KEY_PHONE = "key_phone";
        }

    }

    public static final class CityTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_CITY = "id_city";
            public static final String NAME = "name";
            public static final String CODE_PHONE = "code_phone";
            public static final String ID_COUNTRY = "id_country";
        }


    }

    public static final class DepartmentTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_DEPARTMENT = "id_department";
            public static final String NAME_DEPARTMENT = "name_department";
        }


    }

    public static final class PostTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_POST = "id_post";
            public static final String NAME_POST = "name_post";
            public static final String RATE = "rate";
        }


    }

    public static final class TypeUserTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_TYPE = "id_type";
            public static final String NAME = "name";
            public static final String ACCESS_LEVEL = "access_level";
        }


    }

    public static final class UserTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_USER = "id_user";
            public static final String LOGIN = "login";
            public static final String PASSWORD = "password";
            public static final String ID_TYPE_USER = "id_type_user";
        }


    }

    public static final class PersonTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_PERSON = "id_person";
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


    }

    public static final class TypeTaskTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_TYPE_TASK = "id_type_task";
            public static final String NAME_TYPE = "name_type";
        }


    }

    public static final class TaskTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_TASK = "id_task";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String DATE_BEGIN = "date_begin";
            public static final String DATE_END = "date_end";
            public static final String DONE = "done";
            public static final String PROGRESS = "progress";
            public static final String ID_TYPE_TASK = "id_type_task";
            public static final String ID_PERSON_ADD = "id_person_add";
        }


    }

    public static final class HasTaskPersonTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_HAS_TASK_PERSON = "id_has_task_person";
            public static final String ID_TASK = "id_task";
            public static final String ID_PERSON = "id_person";
        }


    }

    public static final class TypeActivityTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_TYPE_ACTIVITY = "id_type_activity";
            public static final String NAME_ACTIVITY = "name_activity";
        }


    }

    public static final class LogTimeTaskTable {

        public static final String NAME = "";

        public static final class Colums {
            public static final String ID_LOG = "id_log";
            public static final String DATE_LOG = "date_log";
            public static final String DESCRIPTION = "";
            public static final String LINKS_EXT_STOR = "links_ext_stor";
            public static final String ID_TYPE_ACTIVITY = "id_type_activity";
            public static final String ID_HAS_TASK_PERSON = "id_has_task_person";
        }


    }

}
