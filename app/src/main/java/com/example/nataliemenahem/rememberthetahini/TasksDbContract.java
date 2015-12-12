package com.example.nataliemenahem.rememberthetahini;

import android.provider.BaseColumns;

/**
 * Created by NatalieMenahem on 12/12/2015.
 */
public class TasksDbContract {
    /* Inner class that defines the table contents of the tasks */
    public static final class TaskEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "tasks";

        public static final String COLUMN_TASK_DESCRIPTION = "task_description";

        public static final String COLUMN_TASK_STATUS = "task_status";


//        public static final String COLUMN_FRIEND_PHONE_NUMBER = "friend_phone_number";

    }
}
