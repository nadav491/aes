package database;


/* An enum class to use database accions*/
public class ActionsType
{
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
		return-1;
	}

}
