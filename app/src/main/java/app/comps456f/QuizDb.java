package app.comps456f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.comps456f.QuizContract.*;
/**
 * Created by hoyin on 31/3/2018.
 */

public class QuizDb extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "Quiz.db";
    private static final  int DATABASE_VERSION = 7;

    private SQLiteDatabase db;

    public QuizDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NO + " INTEGER," +
                QuestionTable.COLUMN_DIFFICULTY + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL( "DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionTable(){
        //Questions for Quiz 0 Basic
        Questions q1 = new Questions("What does SQL stand for?", "Structured Query Language", "Structured Queue Language"
                , "Short Query Language","None of above",1 , Questions.DIFFICULTY_Q0);
        addQuestion(q1);
        Questions q2 = new Questions("How to select columns Name and ID from table “Student” in SQL", "SELECT ALL FROM Student", "SELECT Name, ID IN Student",
                "SELECT Name, ID FROM Student","FROM Student SELECT Name, ID ",3, Questions.DIFFICULTY_Q0);
        addQuestion(q2);
        Questions q3 = new Questions("DML does NOT include which statement below?", "INSERT", "UPDATE",
                "DELETE","CREATE",4, Questions.DIFFICULTY_Q0);
        addQuestion(q3);
        Questions q4 = new Questions("Which statement is used to insert record?", "CREATE", "INSERT INTO",
                "DROP","SELECT",2, Questions.DIFFICULTY_Q0);
        addQuestion(q4);
        Questions q5 = new Questions("Which operator is used to search for a specified pattern?", "IN", "LIKE",
                "BETWEEN","None of above",1, Questions.DIFFICULTY_Q0);
        addQuestion(q5);
        Questions q6 = new Questions("How to drop a table named “Course”?", "DROP Course", "DELETE Table Course",
                "DROP TABLE Course","DELETE Course",3, Questions.DIFFICULTY_Q0);
        addQuestion(q6);
        Questions q7 = new Questions("Which statement is used to modify the existing records?", "UPDATE", "CREATE TABLE",
                "DROP TABLE","INSERT INTO",1, Questions.DIFFICULTY_Q0);
        addQuestion(q7);
        Questions q8 = new Questions("Which statement is used to add, delete, or modify columns in an existing table?", "CREATE TABLE",
                "DROP TABLE", "INSERT INTO","ALTER TABLE",4, Questions.DIFFICULTY_Q0);
        addQuestion(q8);
        Questions q9 = new Questions("Which statement about Data definition language (DDL) is not correct?", "All definition are stored in the database.",
                "Allow definition of data types and structures.", "Allow definition of any data constraints.","Gives basic data manipulation operators on data stored in database.",
                4, Questions.DIFFICULTY_Q0);
        addQuestion(q9);
        Questions q10 = new Questions("Which statement about the ORDER BY clause is not correct?", "To sort the result-set in ascending or descending order.",
                "By default, it sorts the records in ascending order. ", "To filter records and obtain only those records that suit a specified condition."
                ,"To sort the records in descending order, use the DESC keyword.",3, Questions.DIFFICULTY_Q0);
        addQuestion(q10);

        //Questions for Quiz 1 Join
        Questions q11 = new Questions("With SQL, list all the employee name with their department name.", "SELECT e.Employee_name, d.Department_name \n" +
                "\tFROM employee e INNER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;",
                "SELECT e.Employee_name, d.Department_name \n" +
                        "\tFROM employee e INNER JOIN department d; ", "SELECT e.Employee_name, d.Department_name \n" +
                "\tON employee e INNER JOIN department d;"
                ,"SELECT e.Employee_name, d.Department_name\n" +
                " \tON e.Employee_ID = d.Employee_ID\n" +
                "\tFROM employee e INNER JOIN department d;",1, Questions.DIFFICULTY_Q1);
        addQuestion(q11);
        Questions q12 = new Questions("With SQL, how to show Mary’s department name and salary?", "SELECT e.Salary, d.Department_name FROM employee e \n" +
                "\tINNER JOIN department d\n" +
                "\tWHERE e.Employee_name= 'Mary';",
                "SELECT e.Salary, d.Department_name FROM employee e \n" +
                        "\tINNER JOIN department d ON e.Department_ID = d.Department_ID\n" +
                        "\tWHERE e.Employee_name= 'Mary';", "SELECT Salary, Department_name FROM employee  \n" +
                "\tINNER JOIN department ON Department_ID = Department_ID\n" +
                "\tWHERE Employee_name= 'Mary';"
                ,"SELECT Salary, Department_name FROM employee e \n" +
                "\tWHERE Employee_name= 'Mary';",2, Questions.DIFFICULTY_Q1);
        addQuestion(q12);
        Questions q13 = new Questions("With SQL, list all the employee name and employee ID which work at the Marketing department .", "SELECT e.Employee_ID, e.Employee_name\n" +
                "\tFROM employee e INNER JOIN department d \n" +
                "  \tWHERE d.Department_name = 'Marketing';",
                "SELECT e.Employee_ID, e.Employee_name\n" +
                        "\tFROM employee e INNER JOIN department d \n" +
                        "\tON e.Department_ID = d.Department_ID\n" +
                        "    \tWHERE d.Department_name = 'Marketing';", "SELECT e.Employee_ID, e.Employee_name\n" +
                "\tFROM employee e AND department d \n" +
                "\tON e.Department_ID = d.Department_ID\n" +
                "  \tWHERE d.Department_name = 'Marketing';"
                ,"SELECT e.Employee_ID, e.Employee_name\n" +
                "\tFROM employee e INNER JOIN department d \n" +
                "  \tWHERE d.Department_name = 'Marketing'\n" +
                "\tON e.Department_ID = d.Department_ID;",2, Questions.DIFFICULTY_Q1);
        addQuestion(q13);
        Questions q14 = new Questions("With SQL, list all employees(include the employee that do not allocate department) with their employee ID, employee name, department ID and department name without salary.",
                "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                        "\td.Department_ID, d.Department_name\n" +
                        "\tFROM employee e LEFT OUTER JOIN department d \n" +
                        "\tON e.Department_ID = d.Department_ID;",
                "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                        "\td.Department_ID, d.Department_name\n" +
                        "\tFROM employee e LEFT OUTER JOIN department d \n" +
                        "\tIN e.Department_ID = d.Department_ID;", "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e INNER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;"
                ,"SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e INNER JOIN department d \n" +
                "\tIN e.Department_ID = d.Department_ID;",1, Questions.DIFFICULTY_Q1);
        addQuestion(q14);
        Questions q15 = new Questions("Which type of JOIN clause is not correct?", "LEFT JOIN",
                "FULL JOIN", "ALL JOIN"
                ,"RIGHT JOIN",3, Questions.DIFFICULTY_Q1);
        addQuestion(q15);
        Questions q16 = new Questions("With SQL, list all the department name(include the department do not have employee) and the corresponding employee records.", "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e LEFT OUTER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;",
                "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                        "\td.Department_ID, d.Department_name\n" +
                        "\tFROM employee e LEFT OUTER JOIN department d \n" +
                        "\tON e.Employee_ID = d.Employee_ID;", "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e RIGHT OUTER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;"
                ,"SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e RIGHT OUTER JOIN department d \n" +
                "\tON e.Employee_ID = d.Employee_ID;",3, Questions.DIFFICULTY_Q1);
        addQuestion(q16);
        Questions q17 = new Questions("What is the use of JOIN clause?", "To return the number of rows that matches a specified criteria.",
                "To sort the result-set in ascending or descending order.", "To return only distinct values."
                ,"To mix up the rows from two or more tables and set the connection by the related column between them.",4, Questions.DIFFICULTY_Q1);
        addQuestion(q17);
        Questions q18 = new Questions(" What is the use of INNER JOIN clause?", "Return all records from the right table, and the matched records from the left table.",
                "Return all records from the left table, and the matched records from the right table.", "Returns records that have matching values in both tables."
                ,"Return all records when there is a match in either left or right table",3, Questions.DIFFICULTY_Q1);
        addQuestion(q18);
        Questions q19 = new Questions(" With SQL, list all the department records(include the department do not have employee) and the corresponding employee records(include the employee that do not allocate department).",
                "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                        "\td.Department_ID, d.Department_name\n" +
                        "\tFROM employee e INNER JOIN department d \n" +
                        "\tON e.Department_ID = d.Department_ID;",
                "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                        "\td.Department_ID, d.Department_name\n" +
                        "\tFROM employee e RIGHTOUTER JOIN department d \n" +
                        "\tON e.Department_ID = d.Department_ID;", "SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e FULL OUTER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;"
                ,"SELECT e.Employee_ID, e.Employee_name, e.Salary,\n" +
                "\td.Department_ID, d.Department_name\n" +
                "\tFROM employee e LEFT OUTER JOIN department d \n" +
                "\tON e.Department_ID = d.Department_ID;",3, Questions.DIFFICULTY_Q1);
        addQuestion(q19);
        Questions q20 = new Questions("With SQL, list the department name, employee name, salary of department id is 40.", "SELECT d.Department_name, e.Employee_name, e.Salary\n" +
                "\tFROM employee e INNER JOIN department d  \t\n" +
                "\tON e.Department_ID = d.Department_ID\n" +
                "\tWHERE d.Department_ID = 40;",
                "SELECT d.Department_name, e.Employee_name, e.Salary\n" +
                        "\tFROM employee e INNER JOIN department d  \t\n" +
                        "\tWHERE d.Department_ID = 40\n" +
                        "\tON e.Department_ID = d.Department_ID;", "SELECT d.Department_name, e.Employee_name, e.Salary\n" +
                "\tFROM employee e LEFT JOIN department d  \t\n" +
                "\tON e.Department_ID = d.Department_ID\n" +
                "\tWHERE d.Department_ID = 40;"
                ,"SELECT d.Department_name, e.Employee_name, e.Salary\n" +
                "\tFROM employee e LEFT JOIN department d  \n" +
                "\tWHERE d.Department_ID = 40\t\n" +
                "\tON e.Department_ID = d.Department_ID;",1, Questions.DIFFICULTY_Q1);
        addQuestion(q20);

        //Questions for Quiz 2 Aggregate
        Questions q21 = new Questions("Count the total number of employee of department ID is 10.", "SELECT SUM(Employee_ID) FROM employee\n" +
                "\tWHERE Department_ID = 10;",
                "SELECT TOTAL(Employee_ID) FROM employee\n" +
                        "\tWHERE Department_ID = 10;", "SELECT Employee_ID FROM employee\n" +
                "\tWHERE Department_ID = 10;"
                ,"SELECT COUNT(Employee_ID) FROM employee\n" +
                "\tWHERE Department_ID = 10;",4, Questions.DIFFICULTY_Q2);
        addQuestion(q21);
        Questions q22 = new Questions("How to list the department ID that the average salary of the department is higher than $18000?", "SELECT Department_ID, AVG(Salary) FROM employee\n" +
                "GROUP BY Department_ID\n" +
                "HAVING AVG(Salary) > 18000;",
                "SELECT Department_ID, AVG(Salary) FROM employee\n" +
                        "GROUP BY Department_ID\n" +
                        "WHERE AVG(Salary) > 18000;", "SELECT Department_ID, AVG(Salary) FROM employee\n" +
                "HAVING AVG(Salary) > 18000;"
                ,"SELECT Department_ID, AVG(Salary) FROM employee\n" +
                "WHERE AVG(Salary) > 18000;",1, Questions.DIFFICULTY_Q2);
        addQuestion(q22);
        Questions q23 = new Questions("List the employee name and salary with the employee that have the highest salary. ", "SELECT Employee_name, MAX(Salary) FROM employee;",
                "SELECT Employee_name, MAXIMUM(Salary) FROM employee;", "SELECT Employee_name, Salary FROM employee\n" +
                "\tWHERE MAX(Salary);","SELECT Employee_name, Salary FROM employee\n" +
                "\tHAVING MAX(Salary);",1, Questions.DIFFICULTY_Q2);
        addQuestion(q23);
        Questions q24 = new Questions("List the department ID and the lowest salary in each department.", "SELECT Department_ID, MIN(Salary) FROM employee;",
                "SELECT Department_ID, Salary FROM employee\n" +
                        "\tHAVING MIN(Salary);", "SELECT Department_ID, MIN(Salary) FROM employee\n" +
                "\tGROUP BY Department_ID;","SELECT Department_ID, Salary FROM employee\n" +
                "\tWHERE MIN(Salary);",3, Questions.DIFFICULTY_Q2);
        addQuestion(q24);
        Questions q25 = new Questions("List the Department ID and count the number of employee in the department which obtain less than 3 employee.",
                "SELECT Department_ID, COUNT(Employee_ID) FROM employee\n" +
                        "\tGROUP BY Department_ID\n" +
                        "\tHAVING COUNT(Employee_ID) < 3;",
                "SELECT Department_ID, COUNT(Employee_ID)<3 FROM employee\n" +
                        "\tGROUP BY Department_ID;", "SELECT Department_ID, COUNT(Employee_ID) FROM employee\n" +
                "\tHAVING COUNT(Employee_ID) < 3;","SELECT Department_ID, Employee_ID FROM employee\n" +
                "\tGROUP BY Department_ID\n" +
                "\tHAVING COUNT(Employee_ID) < 3;",1, Questions.DIFFICULTY_Q2);
        addQuestion(q25);
        Questions q26 = new Questions("Which is not a aggregate function?",
                "COUNT()", "AVG()", "MEAN()","SUM()",3, Questions.DIFFICULTY_Q2);
        addQuestion(q26);
        Questions q27 = new Questions("What is the difference between HAVING clause and WHERE clause?",
                "They are no difference.", "WHERE clause can not be used with aggregate functions, but HAVING can.", "WHERE clause can filter records, but HAVING clause cannot.",
                "WHERE clause can be used with aggregate functions, but HAVING cannot.",2, Questions.DIFFICULTY_Q2);
        addQuestion(q27);
        Questions q28 = new Questions(" What is the use of the GROUP BY statement?",
                "It is used with aggregate functions to group the result-set by one or more columns.", "It is used to filter records.", "It is used to mix up the rows from two or more tables and set the connection by the related column between them.",
                "It is used to sort the result-set in ascending or descending order.",1, Questions.DIFFICULTY_Q2);
        addQuestion(q28);
        Questions q29 = new Questions("With SQL, how to calculate the average salary of all employee?",
                "SELECT Salary FROM employee WHERE AVG.Salary;", "SELECT Salary FROM employee WHERE AVG(salary);", "SELECT AVG(Salary) FROM employee;",
                "SELECT AVG.Salary FROM employee;",3, Questions.DIFFICULTY_Q2);
        addQuestion(q29);
        Questions q30 = new Questions("With SQL, how to list each department have how many employee?",
                "SELECT Department_ID, COUNT(Employee_ID) FROM employee;", "SELECT Department_ID, COUNT(Employee_ID) FROM employee\n" +
                "\tGROUP BY Department_ID;", "SELECT Department_ID, COUNT(Employee_ID) FROM employee\n" +
                "\tHAVING Department_ID;","SELECT Department_ID, Employee_ID FROM employee\n" +
                "\tGROUP BY Department_ID;",2, Questions.DIFFICULTY_Q2);
        addQuestion(q30);
        //Questions for Quiz 3 Subquery
        Questions q31 = new Questions("With SQL, how to list all employee names and their employee ID? ", "SELECT Employee_name, Employee_ID AT employee;",
                "SELECT * FROM employee;", "SELECT Employee_name, Employee_ID FROM employee;","SELECT Employee_name, Employee_ID IN employee;",3,
                Questions.DIFFICULTY_Q3);
        addQuestion(q31);
        Questions q32 = new Questions("With SQL, how to list all records in the employee table. ", "SELECT ALL FROM employee;",
                "SELECT * FROM employee;", "FROM employee SELECT ALL;","FROM employee SELECT *;",2, Questions.DIFFICULTY_Q3);
        addQuestion(q32);
        Questions q33 = new Questions("A subselect can be used in which SQL clause(s)? ", "WHERE clause only",
                "WHERE and HAVING clauses", "HAVING clause only","Cannot be used in any SQL clause",2, Questions.DIFFICULTY_Q3);
        addQuestion(q33);
        Questions q34 = new Questions("With SQL, list how many employee(s) have higher salary than Mandy.", "SELECT COUNT(Employee_name) FROM employee\n" +
                "\t\tWHERE Salary > (SELECT Salary \n" +
                "\t\tFROM employee WHERE Employee_name = 'Mandy');",
                "SELECT COUNT(Employee_name) FROM employee\n" +
                        "\tHAVING Salary > (SELECT Salary FROM employee \n" +
                        "\tWHERE Employee_name = 'Mandy');", "SELECT COUNT(Employee_name) FROM employee\n" +
                "\tWHERE Salary > Mandy.Salary;","SELECT COUNT(Employee_name) FROM employee\n" +
                "\t\tHAVING Salary > (SELECT Salary FROM employee \n" +
                "\t\tWHERE Employee_name = 'Mandy');",1, Questions.DIFFICULTY_Q3);
        addQuestion(q34);
        Questions q35 = new Questions(" With SQL, how to list the first 5 records from employee table. ", "SELECT * FROM employee LIMIT 0, 5;",
                "SELECT * FROM employee IN 0, 5;", "SELECT * FROM employee LIMIT 5, 0;","SELECT * FROM employee IN 5, 0;",1, Questions.DIFFICULTY_Q3);
        addQuestion(q35);
        Questions q36 = new Questions("How to list the name of employee(s) that salary is higher than the average salary?", "SELECT Employee_name FROM employee\n" +
                "\tWHERE Salary > (SELECT AVG(Salary) FROM employee);",
                "SELECT Employee_name FROM employee\n" + "\tWHERE Salary > (SELECT Salary FROM employee WHERE AVG(salary));", "SELECT Employee_name FROM employee\n" +
                "\tWHERE Salary > AVG(salary);", "SELECT Employee_name FROM employee\n" +
                "\tWHERE Employee_name.Salary > AVG(salary);",1, Questions.DIFFICULTY_Q3);
        addQuestion(q36);
        Questions q37 = new Questions("How to list the name of employee(s) that salary is lower than Andy’s salary? ", "SELECT Employee_name FROM employee \n" +
                "\tWHERE Salary < (SELECT Salary FROM employee \n" +
                "\t\t\t\tWHERE Employee_name='Andy');",
                "SELECT Employee_name FROM employee \n" +
                        "\tWHERE Salary < (SELECT Andy.Salary FROM employee);\n", "SELECT Employee_name FROM employee \n" +
                "\tWHERE Salary > (SELECT Salary FROM employee \n" +
                "\t\t\t\tWHERE Employee_name='Andy');","SELECT Employee_name FROM employee \n" +
                "\tWHERE Employee_name = 'Andy' AND Salary < Andy.Salary;",1, Questions.DIFFICULTY_Q3);
        addQuestion(q37);

        Questions q38 = new Questions(" With SQL, list the name of department ID(s) that the average salary of department is higher than the average salary of all employees? ",
                "SELECT Department_ID FROM employee \n" +
                        "\tGROUP BY Department_ID\n" +
                        "\tWHERE AVG(Salary) > (SELECT AVG(Salary) FROM employee);",
                "SELECT Department_ID FROM employee \n" +
                        "\tWHERE AVG(Salary) > (SELECT AVG(Salary) FROM employee)\n" +
                        "\tGROUP BY Department_ID;", "SELECT Department_ID FROM employee \n" +
                "\tGROUP BY Department_ID\n" +
                "\tHAVING AVG(Salary) > (SELECT AVG(Salary) FROM employee);","SELECT Department_ID FROM employee \n" +
                "\tHAVING AVG(Salary) > (SELECT AVG(Salary) FROM employee);",3, Questions.DIFFICULTY_Q3);
        addQuestion(q38);
        Questions q39 = new Questions("With SQL, list the department id which the employee(s) in that department is more than the employees in department id '10'?",
                "SELECT Department_ID FROM employee \n" +
                        "\t\tHAVING COUNT(Employee_ID) > (SELECT COUNT(Employee_ID) \n" +
                        "\tFROM employee WHERE Department_ID = 10);",
                "SELECT Department_ID FROM employee \n" +
                        "\tWHERE COUNT(Employee_ID) > (SELECT COUNT(Employee_ID) \n" +
                        "\tFROM employee WHERE Department_ID = 10);", "SELECT Department_ID FROM employee \n" +
                "\tWHERE Employee_ID > (SELECT COUNT(Employee_ID) FROM employee \n" +
                "\tWHERE Department_ID = 10) GROUP BY Department_ID;",
                "SELECT Department_ID FROM employee GROUP BY Department_ID\n" +
                        "\t\tHAVING COUNT(Employee_ID) > (SELECT COUNT(Employee_ID) \n" +
                        "\tFROM employee WHERE Department_ID = 10);",4,
                Questions.DIFFICULTY_Q3);
        addQuestion(q39);
        Questions q40 = new Questions("With SQL, list the employees name and their salary which higher than Michael.", "SELECT Employee_name, Salary FROM employee \n" +
                "\t\tWHERE Salary < (SELECT Salary FROM employee \n" +
                "\t\tWHERE Employee_name = 'Michael');",
                "SELECT Employee_name, Salary FROM employee \n" +
                        "\t\tHAVING Salary < (SELECT Salary FROM employee \n" +
                        "\t\tHAVING Employee_name = 'Michael');", "SELECT Employee_name, Salary FROM employee \n" +
                "\t\tWHERE Salary < (SELECT Salary FROM employee \n" +
                "\t\tHAVING Employee_name = 'Michael');","SELECT Employee_name, Salary FROM employee \n" +
                "\t\tHAVING Salary < (SELECT Salary FROM employee \n" +
                "\t\tWHERE Employee_name = 'Michael');",1,
                Questions.DIFFICULTY_Q3);
        addQuestion(q40);

    }
    private void addQuestion(Questions question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NO, question.getAnsno());
        cv.put(QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty() );
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Questions> getAllQuestions() {
        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnsno(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Questions> getQuestions(String difficulty) {
        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.COLUMN_DIFFICULTY + " =  ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnsno(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NO)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFFICULTY)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
