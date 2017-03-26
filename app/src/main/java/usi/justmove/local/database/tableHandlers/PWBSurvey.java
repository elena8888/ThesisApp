package usi.justmove.local.database.tableHandlers;

import android.content.ContentValues;
import android.database.Cursor;

import usi.justmove.local.database.LocalDbUtility;
import usi.justmove.local.database.LocalTables;
import usi.justmove.local.database.tables.PWBTable;

/**
 * Created by usi on 10/03/17.
 */

public class PWBSurvey extends TableHandler {
    private static LocalTables table = LocalTables.TABLE_PWB;
    public long parentId;
    public boolean completed;
    public int q1;
    public int q2;
    public int q3;
    public int q4;
    public int q5;
    public int q6;
    public int q7;
    public int q8;

    public PWBSurvey(boolean isNewRecord) {
        super(isNewRecord);
        columns = LocalDbUtility.getTableColumns(table);
        id = -1;
    }

    public static TableHandler findByPk(long pk) {
        String idColumn = LocalDbUtility.getTableColumns(table)[0];
        Cursor surveyRecord = localController.rawQuery("SELECT * FROM " + table + " WHERE " + idColumn + " = " + pk + " LIMIT " + 1, null);

        if(surveyRecord.getCount() == 0) {
            return null;
        }

        PWBSurvey survey = new PWBSurvey(false);
        surveyRecord.moveToFirst();

        survey.setAttributes(survey.getAttributesFromCursor(surveyRecord));

        surveyRecord.close();
        return survey;
    }

    @Override
    public void setAttributes(ContentValues attributes) {
        if(attributes.containsKey(columns[0])) {
            id = attributes.getAsLong(columns[0]);
        }

        if(attributes.containsKey(columns[1])) {
            parentId = attributes.getAsLong(columns[1]);
        }

        if(attributes.containsKey(columns[2])) {
            completed = attributes.getAsBoolean(columns[2]);
        }

        if(attributes.containsKey(columns[3])) {
            q1 = attributes.getAsInteger(columns[3]);
        }

        if(attributes.containsKey(columns[4])) {
            q2 = attributes.getAsInteger(columns[4]);
        }

        if(attributes.containsKey(columns[5])) {
            q3 = attributes.getAsInteger(columns[5]);
        }

        if(attributes.containsKey(columns[6])) {
            q4 = attributes.getAsInteger(columns[6]);
        }

        if(attributes.containsKey(columns[7])) {
            q5 = attributes.getAsInteger(columns[7]);
        }

        if(attributes.containsKey(columns[8])) {
            q6 = attributes.getAsInteger(columns[8]);
        }

        if(attributes.containsKey(columns[9])) {
            q7 = attributes.getAsInteger(columns[9]);
        }

        if(attributes.containsKey(columns[10])) {
            q8 = attributes.getAsInteger(columns[10]);
        }

    }

    @Override
    public ContentValues getAttributes() {
        ContentValues attributes = new ContentValues();
        if(id >= 0) {
            attributes.put(columns[0], id);
        }

        attributes.put(columns[1], parentId);
        attributes.put(columns[2], completed);
        attributes.put(columns[3], q1);
        attributes.put(columns[4], q2);
        attributes.put(columns[5], q3);
        attributes.put(columns[6], q4);
        attributes.put(columns[7], q5);
        attributes.put(columns[8], q6);
        attributes.put(columns[9], q7);
        attributes.put(columns[10], q8);

        return attributes;
    }

    public static TableHandler[] findAll(String select, String clause) {
        Cursor surveyRecords = localController.rawQuery("SELECT " + select + " FROM " + LocalDbUtility.getTableName(table) + " WHERE " + clause, null);
        TableHandler[] surveys =  new TableHandler[surveyRecords.getCount()];

        if(surveyRecords.getCount() == 0) {
            return surveys;
        }

        int i = 0;
        PWBSurvey survey;
        while(surveyRecords.moveToNext()) {
            survey = new PWBSurvey(false);

            survey.setAttributes(survey.getAttributesFromCursor(surveyRecords));
            surveys[i] = survey;
            i++;
        }
        surveyRecords.close();
        return surveys;
    }

    public static TableHandler find(String select, String clause) {
        Cursor surveyRecord = localController.rawQuery("SELECT " + select + " FROM " + LocalDbUtility.getTableName(table) + " WHERE " + clause + " LIMIT " + 1, null);

        if(surveyRecord.getCount() == 0) {
            return null;
        }

        PWBSurvey survey = new PWBSurvey(false);
        surveyRecord.moveToFirst();

        survey.setAttributes(survey.getAttributesFromCursor(surveyRecord));

        surveyRecord.close();
        return survey;
    }

    @Override
    public ContentValues getAttributesFromCursor(Cursor cursor) {
        ContentValues attributes = new ContentValues();
        attributes.put(columns[0], cursor.getLong(0));
        attributes.put(columns[1], cursor.getLong(1));
        attributes.put(columns[2], cursor.getInt(2));
        attributes.put(columns[3], cursor.getInt(3));
        attributes.put(columns[4], cursor.getInt(4));
        attributes.put(columns[5], cursor.getInt(5));
        attributes.put(columns[6], cursor.getInt(6));
        attributes.put(columns[7], cursor.getInt(7));
        attributes.put(columns[8], cursor.getInt(8));
        attributes.put(columns[9], cursor.getInt(9));
        attributes.put(columns[10], cursor.getInt(10));

        return attributes;
    }

    @Override
    public void save() {
        String tableName = LocalDbUtility.getTableName(table);

        if(isNewRecord) {
            id = localController.insertRecord(tableName, getAttributes());
        } else {
            String columnId = columns[0];
            localController.update(tableName, getAttributes(), columnId + " = " + id);
        }
    }

    @Override
    public void setAttribute(String attributeName, ContentValues attribute) {
        super.setAttribute(attributeName, attribute);

        switch(attributeName) {
            case PWBTable.KEY_PWB_ID:
                id = attribute.getAsLong(PWBTable.KEY_PWB_ID);
                break;
            case PWBTable.KEY_PWB_PARENT_SURVEY_ID:
                parentId = attribute.getAsLong(PWBTable.KEY_PWB_PARENT_SURVEY_ID);
                break;
            case PWBTable.KEY_PWB_COMPLETED:
                completed = attribute.getAsBoolean(PWBTable.KEY_PWB_COMPLETED);
                break;
            case PWBTable.KEY_PWB_Q1:
                q1 = attribute.getAsInteger(PWBTable.KEY_PWB_Q1);
                break;
            case PWBTable.KEY_PWB_Q2:
                q2 = attribute.getAsInteger(PWBTable.KEY_PWB_Q2);
                break;
            case PWBTable.KEY_PWB_Q3:
                q3 = attribute.getAsInteger(PWBTable.KEY_PWB_Q3);
                break;
            case PWBTable.KEY_PWB_Q4:
                q4 = attribute.getAsInteger(PWBTable.KEY_PWB_Q4);
                break;
            case PWBTable.KEY_PWB_Q5:
                q5 = attribute.getAsInteger(PWBTable.KEY_PWB_Q5);
                break;
            case PWBTable.KEY_PWB_Q6:
                q6 = attribute.getAsInteger(PWBTable.KEY_PWB_Q6);
                break;
            case PWBTable.KEY_PWB_Q7:
                q7 = attribute.getAsInteger(PWBTable.KEY_PWB_Q7);
                break;
            case PWBTable.KEY_PWB_Q8:
                q8 = attribute.getAsInteger(PWBTable.KEY_PWB_Q8);
                break;
        }
    }

    @Override
    public String[] getColumns() {
        return columns;
    }

    @Override
    public String getTableName() {
        return table.getTableName();
    }

    @Override
    public void delete() {
        localController.delete(table.getTableName(), columns[0] + " = " + id);
    }
}
