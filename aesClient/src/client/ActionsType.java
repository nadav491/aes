package client;

import java.io.Serializable;

/**
*An enum class to use database actions
*/
public class ActionsType implements Serializable
{
	private static final long serialVersionUID = 3007036497465788417L;

	public enum ActionNumber
	{
		QUESIOTN_GET_ALL,
		QUESTION_GET_BY_CODE,
		QUESTION_UPDATE,
		QUESTION_GET_BY_OWNER,
		QUESTION_ADD,
		QUESTION_REMOVE,
		
		USER_LOGIN,
		USER_LOGOUT,

		TEST_CREATE,
		TEST_SET_QUESTIONS,
		TEST_UPDATE,
		TEST_UPDATE_COMMENTS_TEACHER,
		TEST_UPDATE_COMMENTS_STUDENT,
		TEST_DELETE,
		TEST_GET_ALL,
		TEST_GET_BY_ID,
		TEST_GET_ALL_TESTS_BY_STUDENT,
		
		START_TEST,
		STUDENT_TEST_CREATE,
		STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID,
		STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID,
		STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID,
		STUDENT_TEST_UPDATE,
		STUDENT_GET_ALL_NAME,
		
		EXECUTED_TEST_ADD,
		EXECUTED_TEST_GET_ALL,
		EXECUTED_TEST_UPDATE,
		EXECUTED_TEST_CHECK_LOCK_TEST,
		
		UPLOAD_FILE,
		DOWNLOAD_FILE,
		COURSE_GET_ID_LIST,
		Subject_GET_ID_LIST,
		TEACHER_GET_ALL_NAME;
	}

	public static int getValue(ActionNumber action)
	{
		switch (action)
		{
		case QUESIOTN_GET_ALL: return 1;
		case QUESTION_GET_BY_CODE: return 2;
		case QUESTION_UPDATE: return 3;
		case QUESTION_GET_BY_OWNER: return 4;
		case QUESTION_ADD: return 5;
		case QUESTION_REMOVE: return 6;
		case USER_LOGIN: return 7;
		case USER_LOGOUT: return 8;

		case TEST_CREATE: return 100;
		case TEST_SET_QUESTIONS: return 103;
		case TEST_UPDATE: return 105;
		case TEST_UPDATE_COMMENTS_TEACHER: return 106;
		case TEST_UPDATE_COMMENTS_STUDENT: return 108;
		case TEST_DELETE: return 110;
		case TEST_GET_ALL: return 112;
		case TEST_GET_BY_ID: return 113;

		case STUDENT_TEST_CREATE: return 120;
		case STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID: return 121;
		case STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID: return 122;
		case STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID: return 123;
		case STUDENT_TEST_UPDATE: return 124;
		case STUDENT_GET_ALL_NAME: return 125;

		case EXECUTED_TEST_ADD: return 1000;
		case EXECUTED_TEST_GET_ALL: return 1001;
		case EXECUTED_TEST_UPDATE: return 1002;
		case EXECUTED_TEST_CHECK_LOCK_TEST: return 1003;

		case UPLOAD_FILE: return 2000;
		case DOWNLOAD_FILE: return 2001;
		case COURSE_GET_ID_LIST: return 2002;
		case TEACHER_GET_ALL_NAME: return 2003;
		case Subject_GET_ID_LIST: return 2004;
		default: return 0;
		}
	}

	public static ActionNumber getAction(int number)
	{
		switch (number)
		{
		case 1: return ActionNumber.QUESIOTN_GET_ALL;
		case 2: return ActionNumber.QUESTION_GET_BY_CODE;
		case 3: return ActionNumber.QUESTION_UPDATE;
		case 4: return ActionNumber.QUESTION_GET_BY_OWNER;
		case 5: return ActionNumber.QUESTION_ADD;
		case 6: return ActionNumber.QUESTION_REMOVE;
		case 7: return ActionNumber.USER_LOGIN;
		case 8: return ActionNumber.USER_LOGOUT;

		case 100: return ActionNumber.TEST_CREATE;
		case 103: return ActionNumber.TEST_SET_QUESTIONS;
		case 105: return ActionNumber.TEST_UPDATE;
		case 106: return ActionNumber.TEST_UPDATE_COMMENTS_TEACHER;
		case 108: return ActionNumber.TEST_UPDATE_COMMENTS_STUDENT;
		case 110: return ActionNumber.TEST_DELETE;
		case 112: return ActionNumber.TEST_GET_ALL;
		case 113: return ActionNumber.TEST_GET_BY_ID;

		case 120: return ActionNumber.STUDENT_TEST_CREATE;
		case 121: return ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_STUDENT_ID;
		case 122: return ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_TEACHER_ID;
		case 123: return ActionNumber.STUDENT_TEST_GET_ALL_TESTS_BY_COURSE_ID;
		case 124: return ActionNumber.STUDENT_TEST_UPDATE;
		case 125: return ActionNumber.STUDENT_GET_ALL_NAME;

		case 1000: return ActionNumber.EXECUTED_TEST_ADD;
		case 1001: return ActionNumber.EXECUTED_TEST_GET_ALL;
		case 1002: return ActionNumber.EXECUTED_TEST_UPDATE;
		case 1003: return ActionNumber.EXECUTED_TEST_CHECK_LOCK_TEST;

		case 2000: return ActionNumber.UPLOAD_FILE;
		case 2001: return ActionNumber.DOWNLOAD_FILE;
		case 2002: return ActionNumber.COURSE_GET_ID_LIST;
		case 2003: return ActionNumber.TEACHER_GET_ALL_NAME;
		case 2004: return ActionNumber.Subject_GET_ID_LIST;
		}
		return null;
	}

}
