package database;

import java.io.Serializable;

/* An enum class to use database actions*/
public class ActionsType implements Serializable
{
	/**
	 * 
	 */
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
		
		/* TEST ACTION NUMBERS */
		TEST_CREATE,//done
		TEST_SET_QUESTIONS, //done
		TEST_UPDATE, // done
		TEST_UPDATE_COMMENTS_TEACHER,//done
		TEST_UPDATE_COMMENTS_STUDENT,//done
		TEST_DELETE,//done
		TEST_GET_ALL,//done
		TEST_GET_BY_ID,//done
		
		/* TEST DATA ACTION NUMBERS */
		TEST_GET_ALL_TEST_BY_OWNER,
		TEST_GET_ALL_TESTS_BY_STUDENT,
		
		
		/* STUDENT TEST ACTION NUMBERS */
		START_TEST,
		TEST_APPROVE_GRADE,
		TEST_ASK_FOR_TIME_EXCHANGE,
		TEST_LOCK_TEST;
	}
	
	public static int getValue(ActionNumber action)
	{
		switch(action)
		{
		case QUESIOTN_GET_ALL: return 1;
		case QUESTION_GET_BY_CODE: return 2;
		case QUESTION_UPDATE: return 3;
		case QUESTION_GET_BY_OWNER: return 4;
		case QUESTION_ADD: return 5;
		case QUESTION_REMOVE: return 6;
		case USER_LOGIN: return 7;
		case USER_LOGOUT: return 8;
		
		
		case TEST_CREATE : return 100;
		case TEST_SET_QUESTIONS: return 103;
		case TEST_UPDATE: return 105;
		case TEST_UPDATE_COMMENTS_TEACHER: return 106;
		case TEST_UPDATE_COMMENTS_STUDENT: return 108;
		case TEST_DELETE: return 110;
		case TEST_GET_ALL: return 112;
		case TEST_GET_BY_ID: return 113;	
		
		case START_TEST: return 120;
		}
		return 0;
	}
	
	public static ActionNumber getAction(int number)
	{
		switch(number)
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
		
		case 120: return ActionNumber.START_TEST;
		}
		return null;
	}

}
