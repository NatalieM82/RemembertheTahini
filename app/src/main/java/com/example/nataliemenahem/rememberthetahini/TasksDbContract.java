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

        public static final String COLUMN_TASK_DATE_YEAR = "year";
        public static final String COLUMN_TASK_DATE_MONTH = "month";
        public static final String COLUMN_TASK_DATE_DAY = "day";
        public static final String COLUMN_TASK_TIME_HOUR = "hour";
        public static final String COLUMN_TASK_TIME_MINUTES = "minutes";

        public static final String COLUMN_TASK_ALARM = "alarm";

        public static final String KEY_LOCATION_LONGITUDE = "longitude";
        public static final String KEY_LOCATION_LATITUDE = "latitude";

    }
}
