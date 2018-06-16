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
		USER_LOGIN;
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
		}
		return null;
	}

}
