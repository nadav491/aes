package client;

import java.io.Serializable;

/* An enum class to use database accions*/
public class ActionsType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3007036497465788417L;

	public enum ActionNumber
	{
		QUESIOTN_GET_ALL,
		QUESTION_GET_BY_ID,
		QUESTION_UPDATE;
	
	}
	
	public static int getValue(ActionNumber action)
	{
		switch(action)
		{
		case QUESIOTN_GET_ALL: return 1;
		case QUESTION_GET_BY_ID: return 2;
		case QUESTION_UPDATE: return 3;
		}
		return 0;
	}
	
	public static ActionNumber getAction(int number)
	{
		switch(number)
		{
		case 1: return ActionNumber.QUESIOTN_GET_ALL;
		case 2: return ActionNumber.QUESTION_GET_BY_ID;
		case 3: return ActionNumber.QUESTION_UPDATE;
		}
		return null;
	}

}
