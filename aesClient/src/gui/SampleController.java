package gui;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.Client;
import question.Course;
import question.Question;
import test.ExecutedTest;
import test.Test;
import test.studentTest;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SampleController {
	Course L = null;
	private Stage primaryStage;//main stage
	private ArrayList<Question> Q_list;//question list
	private ArrayList<Test> test_list;//test list
	private ObservableList<String> Subject_list=FXCollections.observableArrayList();//get subject list from database//subject list
	private ObservableList<String> Course_list=FXCollections.observableArrayList();//get course list from database//course list
	public ArrayList<studentTest> solved_Tests=new ArrayList<studentTest> ();//list of solved tests
	private ScrollPane Scroll;
	public final String Owner_name[]=new String[2];//owner name
	public String Question_select="0";
	int start=0;
	Question Q_Update;
	int idx_C,idx2;
	static int val=0;
	private Test Chosen;
	int sum;
	private static String follow="100";
	
	public SampleController()
	{
		test_list=new ArrayList<Test>();//initializing
		
	}
	public void Q_print(String Owner,int c,Client client)//question database managment window
	{
		
		Subject_list=FXCollections.observableArrayList();
		    Course_list=FXCollections.observableArrayList();
		    Course_list.addAll(client.getCoursesId());//get subject list from database
		    Subject_list.addAll(client.getSubjectsId());//get course list from database
			Owner_name[c]=Owner;//setting name
			Q_list = client.getAllQuestion();//getting questions
			for(int i=Q_list.size()-1;i>=0;i--) {//getting teacher questions
				if(!Q_list.get(i).getOwner().equals(Owner_name[c]))Q_list.remove(i);
			}
		primaryStage=new Stage();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Q.fxml"));
			Scroll=addScrollPane();
			root.setCenter(Scroll);
			Button B1,B2,B3;
			B1=new Button("view Question");
			B1.setOnAction(new EventHandler<ActionEvent>()
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
			    	   if(Question_select!="0") Question_mode();//view question
			      }
			       
		    });
		

			B2=new Button("modify Question");
			B2.setOnAction(new EventHandler<ActionEvent>()//modify question
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
			    	Q_Update=new Question();
			    	Text t1=null,t2=null,t3=null,t4=null,t5=null,t6=null,t7=null;
			    	for(int i=0;i<Q_list.size();i++)//finding correct question
			   	    {
			   	    	if(Question_select==Q_list.get(i).getCode())
			   	    		{
			   	    		Q_Update=Q_list.get(i);
			   	    		idx_C=i;
			   	    		}
			   	    }
			    	if(Question_select!="0") {//creating input window
			    	GridPane Text_edit=new GridPane();
			    	Text_edit.setHgap(10);
			    	Text_edit.setVgap(10);
			    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
			    	
                    Label L1=new Label("Student instructions");
                    Text_edit.add(L1, 0, 0);
                    TextArea F1=new TextArea();
                    F1.setPrefColumnCount(30);
                    F1.setPrefRowCount(2);
                    F1.setText(Q_list.get(idx_C).getSInstruction());
                    Text_edit.add(F1, 1,0);
                    L1=new Label("Teacher instructions");
                    Text_edit.add(L1, 0, 1);
                    TextArea F0=new TextArea();
                    F0.setPrefColumnCount(30);
                    F0.setPrefRowCount(2);
                    F0.setText(Q_list.get(idx_C).getTInstruction());
                    Text_edit.add(F0, 1,1);
                    Label L2=new Label("Question");
                    Text_edit.add(L2, 0, 2);
                    TextArea F2=new TextArea();
                    F2.setPrefColumnCount(30);
                    F2.setPrefRowCount(5);
                    F2.setText(Q_list.get(idx_C).getBody());
                    Text_edit.add(F2, 1,2);
                    Label L3=new Label("Answer 1");
                    Text_edit.add(L3, 0, 3);
                    TextArea F3=new TextArea();
                    F3.setPrefColumnCount(30);
                    F3.setPrefRowCount(1);
                    F3.setText(Q_list.get(idx_C).getAnswer1());
                    Text_edit.add(F3, 1,3);
                    Label L4=new Label("Answer 2");
                    Text_edit.add(L4, 0, 4);
                    TextArea F4=new TextArea();
                    F4.setPrefColumnCount(30);
                    F4.setPrefRowCount(1);
                    F4.setText(Q_list.get(idx_C).getAnswer2());
                    Text_edit.add(F4, 1,4);
                    Label L5=new Label("Answer 3");
                    Text_edit.add(L5, 0, 5);
                    TextArea F5=new TextArea();
                    F5.setPrefColumnCount(30);
                    F5.setPrefRowCount(1);
                    F5.setText(Q_list.get(idx_C).getAnswer3());
                    Text_edit.add(F5, 1,5);
                    Label L6=new Label("Answer 4");
                    Text_edit.add(L6, 0, 6);
                    TextArea F6=new TextArea();
                    F6.setPrefColumnCount(30);
                    F6.setPrefRowCount(1);
                    F6.setText(Q_list.get(idx_C).getAnswer4());
                    Text_edit.add(F6, 1,6);
                    Label L7=new Label("Correct");
                    Text_edit.add(L7, 0, 7);
                    TextArea F7=new TextArea();
                    F7.setPrefColumnCount(1);
                    F7.setPrefRowCount(1);
                    F7.setText(Q_list.get(idx_C).getCorrect()+"");
                    Text_edit.add(F7, 1,7);
                    Button B3,B4;
                    B3=new Button("Cancel");//cancel question creation
        			B3.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
        			    	   primaryStage.close();
        			    	   Q_print(Owner,c,client);
        			      }
        			       
        		    });
        			B4=new Button("Submit");//submitting question to update
        			B4.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
        			    	   //getting data from input
        			    	   Q_list.get(idx_C).setSInstruction(F1.getText());
        			    	   Q_list.get(idx_C).setTInstruction(F0.getText());
        			    	   if(F2.getText().length()>0) Q_list.get(idx_C).setBody(F2.getText());
        			    	   if(F3.getText().length()>0) Q_list.get(idx_C).setAnswer1(F3.getText());
        			    	   if(F4.getText().length()>0) Q_list.get(idx_C).setAnswer2(F4.getText());
        			    	   if(F5.getText().length()>0) Q_list.get(idx_C).setAnswer3(F5.getText());
        			    	   if(F6.getText().length()>0) Q_list.get(idx_C).setAnswer4(F6.getText());
        			    	   if(F7.getText().length()>0) {
        			    		   if(F7.getText().equals("1"))Q_list.get(idx_C).setCorrect(1);
        			    		   if(F7.getText().equals("2"))Q_list.get(idx_C).setCorrect(2);
        			    		   if(F7.getText().equals("3"))Q_list.get(idx_C).setCorrect(3);
        			    		   if(F7.getText().equals("4"))Q_list.get(idx_C).setCorrect(4);
        			    		   else System.out.println("invalid correct index");
        			    	   }
        			    	   //updating database
        			    	   client.modifyQuestion(Q_list.get(idx_C));
        			    	   primaryStage.close();
        			    	   Q_print(Owner,c,client);
        			    	  
        			    	   
        			      }
        			       
        		    });
        			Text_edit.add(B3, 0, 8);
        			Text_edit.add(B4, 1, 8);
			    	Scene scene2 = new Scene(Text_edit,600,600);
			    	primaryStage.setScene(scene2);
					primaryStage.show();
					}
			      }
			       
		    });
			Button B4=new Button("add Question");//adding a new question
			B4.setOnAction(new EventHandler<ActionEvent>()
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
			    	   //initializing question creation window
			    	    Q_Update=new Question();
				    	Text t1=null,t2=null,t3=null,t4=null,t5=null,t6=null,t7=null;
				    	GridPane Text_edit=new GridPane();
				    	Text_edit.setHgap(10);
				    	Text_edit.setVgap(10);
				    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
				    	Label L1=new Label("Subject");
		                Text_edit.add(L1, 0, 0);
		                ComboBox box1=new ComboBox(Subject_list);
		               
		                Text_edit.add(box1, 1, 0);
	                    L1=new Label("Student instructions");
	                    Text_edit.add(L1, 0, 1);
	                    TextArea F1=new TextArea();
	                    F1.setPrefColumnCount(30);
	                    F1.setPrefRowCount(2);
	                    F1.setPromptText("Optional: enter student instructions here");
	                    Text_edit.add(F1, 1,1);
	                    L1=new Label("Teacher instructions");
	                    Text_edit.add(L1, 0, 2);
	                    TextArea F0=new TextArea();
	                    F0.setPrefColumnCount(30);
	                    F0.setPrefRowCount(2);
	                    F0.setPromptText("Optional: enter teacher instructions here");
	                    Text_edit.add(F0, 1,2);
	                    Label L2=new Label("Question");
	                    Text_edit.add(L2, 0, 3);
	                    TextArea F2=new TextArea();
	                    F2.setPrefColumnCount(30);
	                    F2.setPrefRowCount(5);
	                    F2.setPromptText("enter Question text here");
	                    Text_edit.add(F2, 1,3);
	                    Label L3=new Label("Answer 1");
	                    Text_edit.add(L3, 0, 4);
	                    TextArea F3=new TextArea();
	                    F3.setPrefColumnCount(30);
	                    F3.setPrefRowCount(1);
	                    F3.setPromptText("enter first answer here");
	                    Text_edit.add(F3, 1,4);
	                    Label L4=new Label("Answer 2");
	                    Text_edit.add(L4, 0, 5);
	                    TextArea F4=new TextArea();
	                    F4.setPrefColumnCount(30);
	                    F4.setPrefRowCount(1);
	                    F4.setPromptText("enter second answer here");
	                    Text_edit.add(F4, 1,5);
	                    Label L5=new Label("Answer 3");
	                    Text_edit.add(L5, 0, 6);
	                    TextArea F5=new TextArea();
	                    F5.setPrefColumnCount(30);
	                    F5.setPrefRowCount(1);
	                    F5.setPromptText("enter third answer here");
	                    Text_edit.add(F5, 1,6);
	                    Label L6=new Label("Answer 4");
	                    Text_edit.add(L6, 0, 7);
	                    TextArea F6=new TextArea();
	                    F6.setPrefColumnCount(30);
	                    F6.setPrefRowCount(1);
	                    F6.setPromptText("enter 4th answer here");
	                    Text_edit.add(F6, 1,7);
	                    Label L7=new Label("Correct");
	                    Text_edit.add(L7, 0, 8);
	                    TextArea F7=new TextArea();
	                    F7.setPrefColumnCount(1);
	                    F7.setPrefRowCount(1);
	                    F7.setPromptText("enter correct answer index here");
	                    Text_edit.add(F7, 1,8);
	                    Button B3,B4;
	                    B3=new Button("Cancel");//canceling creation
	        			B3.setOnAction(new EventHandler<ActionEvent>()
	        		    {
	        			       @Override
	        			       public void handle(ActionEvent e)
	        				  {
	        			    	   primaryStage.close();
	        			    	   Q_print(Owner,c,client);
	        			      }
	        			       
	        		    });
	        			B4=new Button("Submit");//submitting question
	        		
	        			B4.setOnAction(new EventHandler<ActionEvent>()
	        		    {
	        			       @Override
	        			       public void handle(ActionEvent e)
	        				  {
	        			    	   //checking for invalid input
	        			    	   int j=0;
	        			    	   Question Question_add=new Question();
	        			    	   if(box1.getValue()==null)box1.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   box1.setBlendMode(BlendMode.SRC_OVER);
	        			    		   int r=Integer.parseInt(follow)+1;
	        			    		   follow=r+"";
	        			    		   Question_add.setCode(box1.getValue()+""+follow);
	        			    		   
	        			    		   j++;
	        			    	   }
	        			    	   if(F2.getText().isEmpty()) F2.setBlendMode(BlendMode.RED);
	        			    	   else {
	        			    		   F2.setBlendMode(BlendMode.SRC_OVER);
	        			    		   Question_add.setBody(F2.getText());
	        			    		   j++;
	        			    	   }
	        			    	   if(F3.getText().isEmpty()) F3.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   F3.setBlendMode(BlendMode.SRC_OVER);
	        			    		   Question_add.setAnswer1(F3.getText());
	        			    		   j++;
	        			    	   }
	        			    	   if(F4.getText().isEmpty()) F4.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   F4.setBlendMode(BlendMode.SRC_OVER);
	        			    		   Question_add.setAnswer2(F4.getText());
	        			    		   j++;
	        			    	   }
	        			    	   if(F5.getText().isEmpty()) F5.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   F5.setBlendMode(BlendMode.SRC_OVER);
	        			    		   Question_add.setAnswer3(F5.getText());
	        			    		   j++;
	        			    	   }
	        			    	   if(F6.getText().isEmpty()) F6.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   F6.setBlendMode(BlendMode.SRC_OVER);
	        			    		   Question_add.setAnswer4(F6.getText());
	        			    		   j++;
	        			    	   }
	        			    	   if(F7.getText().isEmpty()) F7.setBlendMode(BlendMode.RED);
	        			    	   else
	        			    	   {
	        			    		   F7.setBlendMode(BlendMode.SRC_OVER);
	        			    		   if(F7.getText().equals("1"))
	        			    		   {
	        			    			   Question_add.setCorrect(1);
	        			    			   j++;
	        			    		   }
	        			    		   else if(F7.getText().equals("2")) {
	        			    			   Question_add.setCorrect(2);
	        			    			   j++;
	        			    		   }
	        			    		   else if(F7.getText().equals("3")) {
	        			    			   Question_add.setCorrect(3);
	        			    			   j++;
	        			    		   }
	        			    		   else if(F7.getText().equals("4")) {
	        			    			   Question_add.setCorrect(4);
	        			    		       j++;
	        			    		   }
	        			    		   else
	        			    		   {
	        			    			   F7.setBlendMode(BlendMode.RED);
	        			    		   }
	        			    	   }
	        			    	   Question_add.setSInstruction(F1.getText());
	        			    	   Question_add.setTInstruction(F0.getText());
	        			    	   if(j==7) {
	        			    	   //updating database
	        			    	   Question_add.setOwner(Owner_name[c]);
	        			    	   client.addQuestion(Question_add);
	        			    	   primaryStage.close();
	        			    	   Q_print(Owner,c,client);
	        			    	   }
	        			    	  
	        			    	   
	        			      }
	        			       
	        		    });
	        			Text_edit.add(B3, 0, 9);
	        			Text_edit.add(B4, 1, 9);
				    	Scene scene2 = new Scene(Text_edit,600,600);
				    	primaryStage.setScene(scene2);
						primaryStage.show();
						
				  }
		    });
			
			VBox buttons = new VBox();
			// Add the Buttons to the VBox
			buttons.getChildren().addAll(B1,B2,B4);
            root.setRight(buttons);
			Scene scene = new Scene(root,600,449);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ScrollPane addScrollPane()//creatin a question scroll pane
	{
		ScrollPane sp=new ScrollPane();
		 ObservableList<String> data = FXCollections.observableArrayList();

		    ListView<String> listView = new ListView<String>(data);
		    listView.setPrefSize(300, 250);
		    for(int i=0;i<Q_list.size();i++)data.add(Q_list.get(i).getCode());

		    listView.setItems(data);
		    listView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		                Question_select=new_val;
		                
		    });
		    sp.setContent(listView);
        
        // Horizontal scroll bar is only displayed when needed
		return sp;
	}
	public void Question_mode()//viewing question
	{
		//initializing window view
		Question Q_view=new Question();
		Text txt1,txt2 = null,txt3 = null,txt4 = null,
				txt5 = null,txt6 = null,txt7 = null,txt8 = null,txt9 = null,txt10;
		 txt1=new Text("Question "+Question_select+":\n");
		txt1.setFont(new Font(15));
	    for(int i=0;i<Q_list.size();i++)
	    {
	    	if(Question_select==Q_list.get(i).getCode())Q_view=Q_list.get(i);
	    }
		 txt2=new Text("Written by: "+Q_view.getOwner()+"\n");
		txt2.setFont(new Font(15));
		 txt3=new Text("Student Instructions: "+Q_view.getSInstruction()+"\n");
		txt3.setFont(new Font(15));
		 txt10=new Text("Teacher Instructions: "+Q_view.getTInstruction()+"\n");
		txt10.setFont(new Font(15));
		 txt4=new Text("Question: "+Q_view.getBody()+"\n");
		txt4.setFont(new Font(15));
		 txt5=new Text("answers:\n");
		txt5.setFont(new Font(15));
		 txt6=new Text("1:"+Q_view.getAnswer1()+"\n");
		txt6.setFont(new Font(15));
		 txt7=new Text("2:"+Q_view.getAnswer2()+"\n");
		txt7.setFont(new Font(15));
		 txt8=new Text("3:"+Q_view.getAnswer3()+"\n");
		txt8.setFont(new Font(15));
		 txt9=new Text("4:"+Q_view.getAnswer4()+"\n");
		txt9.setFont(new Font(15));
		
		TextFlow Flowtxt=new TextFlow();
		Flowtxt.setTextAlignment(TextAlignment.JUSTIFY);
		Flowtxt.setPrefSize(600,300);
		Flowtxt.setLineSpacing(5.0);
		ObservableList list=Flowtxt.getChildren();
		list.addAll(txt1,txt2,txt3,txt10,txt4,txt5,txt6,txt7,txt8,txt9);
		Scene do1=new Scene(Flowtxt);
		Stage board=new Stage();
		board.setScene(do1);
		board.show();
	}
	public void Back_button_Press(ActionEvent event) {//back button finction
		Stage closer=(Stage) ((Node) event.getSource()).getScene().getWindow();
		closer.close();
	}
    public void T_print(String Owner,int c,Client client)//test database managing function
    {
    	//initializing data
		Subject_list=FXCollections.observableArrayList();//get subject list from database
		    Course_list=FXCollections.observableArrayList();//get course list from database
		    Course_list.addAll(client.getCoursesId());
		    Subject_list.addAll(client.getSubjectsId());
    	Owner_name[c]=Owner;
    	primaryStage=new Stage();
    	test_list=client.getAllTests();
    	for(int i=test_list.size()-1;i>=0;i--)//getting correct tests
    	{
    		if(!test_list.get(i).getOwner().equals(Owner))
    			test_list.remove(i);
    	}
		SplitPane root = new SplitPane();
		Scroll=new ScrollPane();
		Scroll=addTestScrollPane();
		Button B1=new Button("View test");//view test
		B1.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)//finding test in list
		    		   {
		    			   if(test_list.get(i).getCode().equals(Question_select)) idx_C=i;
		    		   }
		    		   view_test(test_list.get(idx_C));//view test
		           }
			  }
	    });
		Button B2=new Button("Add test");//creating a new test
		B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   Add_test(c,client);
			  }
	    });
		Button B3=new Button("modify test");//modifying a test
		B3.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)//finding test in list
		    		   {
		    			   if(test_list.get(i).getCode().equals(Question_select)) idx_C=i;
		    		   }
		    		   modify_test(test_list.get(idx_C),c,client);
		           }
			  }
	    });
		
		VBox box1=new VBox();
		box1.getChildren().addAll(B1,B2,B3);
		root.getItems().addAll(Scroll,box1);
		Scene scene = new Scene(root,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void view_test(Test Chosen)//a function to view test form
    {
    	//initializing test form window
    	ScrollPane pane=new ScrollPane();
    	GridPane Text_edit=new GridPane();
    	Text_edit.setHgap(10);
    	Text_edit.setVgap(10);
    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
    	Label L1=new Label("Test: "+Chosen.getCode());
    	Text_edit.add(L1, 0, 0);
    	L1=new Label("Written by: "+Chosen.getOwner());
    	Text_edit.add(L1, 0, 1);
    	L1=new Label("Test duration: "+Chosen.getTime()+" minuts");
    	Text_edit.add(L1, 0, 2);
    	int j=3;
    	for(int i=0;i<Chosen.getQuestions().size();i++)
    	{
    		L1=new Label("Question"+(i+1)+":");
    		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
        	Text_edit.add(L1, 0, j);
        	j=j+2;
        	L1=new Label("Student instructions: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	L1=new Label(Chosen.getQuestions().get(i).getSInstruction());
        	Text_edit.add(L1, 1, j);
        	j++;
        	L1=new Label("Teacher instructions: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	L1=new Label(Chosen.getQuestions().get(i).getTInstruction());
        	Text_edit.add(L1, 1, j);
        	j++;
        	L1=new Label("Question: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label(Chosen.getQuestions().get(i).getBody());
        	Text_edit.add(L1, 0, j);
        	j=j+2;
        	L1=new Label("Answers: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("1)"+Chosen.getQuestions().get(i).getAnswer1());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("2)"+Chosen.getQuestions().get(i).getAnswer2());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("3)"+Chosen.getQuestions().get(i).getAnswer3());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("4)"+Chosen.getQuestions().get(i).getAnswer4());
        	Text_edit.add(L1, 0, j);
        	j++;
    	}
    	pane.setContent(Text_edit);
    	Scene scene = new Scene(pane,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void Add_test(int c,Client client)// a function to create a new test
    {
    	//initializng data
		Subject_list=FXCollections.observableArrayList();//get subject list from database
		    Course_list=FXCollections.observableArrayList();//get course list from database
		    Course_list.addAll(client.getCoursesId());
		    Subject_list.addAll(client.getSubjectsId());
    	Test test_add=new Test();
    	GridPane Text_edit=new GridPane();
    	Text_edit.setHgap(10);
    	Text_edit.setVgap(10);
    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
    	ComboBox Subjects=new ComboBox(Subject_list);
    	Text_edit.add(Subjects, 0, 0);
    	ComboBox Courses=new ComboBox(Course_list);
    	Text_edit.add(Courses, 1, 0);
    	ComboBox Test_number=new ComboBox();
    	Test_number.getItems().addAll("01","02");
    	Text_edit.add(Test_number, 2, 0);
    	Button B1=new Button("Cancel");//canceling test creation
    	B1.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   GridPane grid = new GridPane();
			       grid.setHgap(10);
			       grid.setVgap(10);
			       grid.setPadding(new Insets(25, 25, 25, 25));
			       Label Message=new Label("Are you sure you want to stop Test creation?");
			       grid.add(Message,1, 0);
			       Button B1=new Button("Yes");
			       B1.setOnAction(new EventHandler<ActionEvent>()
				    {
					       @Override
					       public void handle(ActionEvent e)
						  {
					    	   primaryStage.close();
					    	   T_print(Owner_name[c],c,client);
						  }
				    });
			       Button B2=new Button("No");
			       B2.setOnAction(new EventHandler<ActionEvent>()
				    {
					       @Override
					       public void handle(ActionEvent e)
						  {
					    	   primaryStage.close();
					    	   Add_test(c,client);
						  }
				    });
			       grid.add(B1, 1, 1);
			       grid.add(B2, 2, 1);
			       Scene scene = new Scene(grid,400,200);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
			       
			  }
	    });
    	Text_edit.add(B1, 0, 1);
    	Button B2=new Button("Continue");//continuing on creating
    	B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   int j=0;
		    	   //checking for invalid input
		    	   if(Subjects.getValue()==null)
		    	   {
		    		   Subjects.setBlendMode(BlendMode.RED);
		    	   }
		    	   else
		    	   {
		    		   Subjects.setBlendMode(BlendMode.SRC_OVER);
		    		   j++;
		    	   }
		           if(Courses.getValue()==null)
    		       {
    			   Courses.setBlendMode(BlendMode.RED);
    		       }
    		       else
    		       {
    			   Courses.setBlendMode(BlendMode.SRC_OVER);
    			   j++;
    		       }
		           if(Test_number.getValue()==null)
    			   {
    				   Test_number.setBlendMode(BlendMode.RED);
    			   }
    			   else
    			   {
    				   Test_number.setBlendMode(BlendMode.SRC_OVER);
    				   j++;
    			   }
		           if(j==3)
		           {
		        	   //getting questions to add to test by subject
		        	   Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
		        	   Q_list=client.getAllQuestion();
		        	   ArrayList<Question> add=new ArrayList<Question>();
		        	   for(int i=0;i<Q_list.size();i++)
		        	   {
		        		   if((Q_list.get(i).getCode().charAt(0)+""+Q_list.get(i).getCode().charAt(1)+"").equals(Subjects.getValue()))
		        		   {
		        			   add.add(Q_list.get(i));
		        		   }
		        	   }
		        	   
		        	   test_add.setCode(Subjects.getValue()+""+Courses.getValue()+""+Test_number.getValue()+"");
		        	   test_add.setOwner(Owner_name[c]);
		        	   //initializing question select window
		        	   BorderPane root = new BorderPane();
		        	    Scene scene = new Scene(root, 400, 250, Color.WHITE);

		        	    GridPane gridpane = new GridPane();
		        	    gridpane.setPadding(new Insets(5));
		        	    gridpane.setHgap(10);
		        	    gridpane.setVgap(10);
		        	    ColumnConstraints column1 = new ColumnConstraints(150, 150,
		        	        Double.MAX_VALUE);
		        	    ColumnConstraints column2 = new ColumnConstraints(50);
		        	    ColumnConstraints column3 = new ColumnConstraints(150, 150,
		        	        Double.MAX_VALUE);
		        	    column1.setHgrow(Priority.ALWAYS);
		        	    column3.setHgrow(Priority.ALWAYS);
		        	    gridpane.getColumnConstraints().addAll(column1, column2, column3);

		        	    Label candidatesLbl = new Label("Questions");
		        	    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
		        	    gridpane.add(candidatesLbl, 0, 0);

		        	    Label selectedLbl = new Label("selected");
		        	    gridpane.add(selectedLbl, 2, 0);
		        	    GridPane.setHalignment(selectedLbl, HPos.CENTER);
		        	    // Candidates
		        	    final ObservableList<String> candidates = FXCollections
		        	        .observableArrayList();
		        	    for(int i=0;i<add.size();i++)
		        	    {
		        	    	candidates.add(add.get(i).getCode());
		        	    }
		        	    final ListView<String> candidatesListView = new ListView<>(candidates);
		        	    gridpane.add(candidatesListView, 0, 1);

		        	    final ObservableList<String> selected = FXCollections.observableArrayList();
		        	    final ListView<String> heroListView = new ListView<>(selected);
		        	    gridpane.add(heroListView, 2, 1);

		        	    Button sendRightButton = new Button(" > ");
		        	    sendRightButton.setOnAction((ActionEvent event) -> {
		        	      String potential = candidatesListView.getSelectionModel()
		        	          .getSelectedItem();
		        	      if (potential != null) {
		        	        candidatesListView.getSelectionModel().clearSelection();
		        	        candidates.remove(potential);
		        	        selected.add(potential);
		        	      }
		        	    });

		        	    Button sendLeftButton = new Button(" < ");
		        	    sendLeftButton.setOnAction((ActionEvent event) -> {
		        	      String s = heroListView.getSelectionModel().getSelectedItem();
		        	      if (s != null) {
		        	        heroListView.getSelectionModel().clearSelection();
		        	        selected.remove(s);
		        	        candidates.add(s);
		        	      }
		        	    });
		        	    VBox vbox = new VBox(5);
		        	    vbox.getChildren().addAll(sendRightButton, sendLeftButton);

		        	    gridpane.add(vbox, 1, 1);
		        	    root.setCenter(gridpane);
                        HBox hbox=new HBox();
                        Button B1=new Button("Cancel");//canceling test creation
                    	B1.setOnAction(new EventHandler<ActionEvent>()
                	    {
                		       @Override
                		       public void handle(ActionEvent e)
                			  {
                		    	   Stage SecondStage=new Stage();
                		    	   GridPane grid = new GridPane();
                			       grid.setHgap(10);
                			       grid.setVgap(10);
                			       grid.setPadding(new Insets(25, 25, 25, 25));
                			       Label Message=new Label("Are you sure you want to stop Test creation?");
                			       grid.add(Message,1, 0);
                			       Button B1=new Button("Yes");
                			       B1.setOnAction(new EventHandler<ActionEvent>()
                				    {
                					       @Override
                					       public void handle(ActionEvent e)
                						  {
                					    	   SecondStage.close();
                					    	   primaryStage.close();
                					    	   T_print(Owner_name[c],c,client);
                						  }
                				    });
                			       Button B2=new Button("No");
                			       B2.setOnAction(new EventHandler<ActionEvent>()
                				    {
                					       @Override
                					       public void handle(ActionEvent e)
                						  {
                					    	   SecondStage.close();
                						  }
                				    });
                			       grid.add(B1, 1, 1);
                			       grid.add(B2, 2, 1);
                			       Scene scene = new Scene(grid,400,200);
                					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                					SecondStage.setScene(scene);
                					SecondStage.show();
                			       
                			  }
                	    });
                        Button B2=new Button("Continue");//continue on creating a test
                    	B2.setOnAction(new EventHandler<ActionEvent>()
                	    {
                		       @Override
                		       public void handle(ActionEvent e)
                			  {
                		    	   if(!selected.isEmpty())
                		    	   {
                		    		   for(int i=0;i<selected.size();i++)
                		    		   {
                		    			   for(int j=0;j<Q_list.size();j++)
                		    			   {
                		    				  
                		    				   if(Q_list.get(j).getCode().equals(selected.get(i)))
                		    					   test_add.getQuestions().add(Q_list.get(j));
                		    			   }
                		    			   
                		    		   }
                		    		GridPane gridpane = new GridPane();
               		        	    gridpane.setPadding(new Insets(5));
               		        	    gridpane.setHgap(10);
               		        	    gridpane.setVgap(10);
               		        	    Label L1=new Label("Regular or Advanced Settings?");
               		        	    gridpane.add(L1, 0, 0);
               		        	    Button B1=new Button("Regular Settings");//adding only test time and question grades and some intructions
               		        	    B1.setOnAction(new EventHandler<ActionEvent>()
                         	        {
                         		       @Override
                         		       public void handle(ActionEvent e)
                         			  {
                         		    	    TextArea matrix[]=new TextArea[test_add.getQuestions().size()];
                         		    	    ScrollPane pane=new ScrollPane();
                         		    	    GridPane gridpane = new GridPane();
                     		        	    gridpane.setPadding(new Insets(5));
                     		        	    gridpane.setHgap(10);
                     		        	    gridpane.setVgap(10);
                     		        	    Label L1=new Label("Time");
                     		        	    gridpane.add(L1,0, 0);
                     		        	    TextArea F1=new TextArea();
                     		        	    F1.setPromptText("enter test time here");
                     		        	    F1.setPrefColumnCount(15);
                     		        	    F1.setPrefRowCount(1);
                     		        	    gridpane.add(F1, 1, 0);
                     		        	    int j=1;
                     		        	    for(int i=0;i<test_add.getQuestions().size();i++)
                     		        	    {
                     		        	    	L1=new Label("Question "+(i+1)+":");
                     		        	    	L1.setFont(Font.font( "", FontWeight.BOLD, 17));
                     		        	    	gridpane.add(L1, 0, j);
                     		        	    	j++;
                     		        	    	L1=new Label("Question points");
                     		        	    	gridpane.add(L1, 0, j);
                     		        	    	matrix[i]=new TextArea();
                     		        	    	matrix[i].setPromptText("enter Question points here");
                     		        	    	matrix[i].setPrefColumnCount(15);
                     		        	    	matrix[i].setPrefRowCount(1);
                     		        	    	gridpane.add(matrix[i], 1, j);
                     		        	    	j++;
                     		        	    }
                     		        	   TextArea generalS=new TextArea();
                     		        	    generalS.setPrefSize(30, 1);
                     		        	    generalS.setPromptText("you may eneter general instructions for students here");
                     		        	    gridpane.add(generalS, 0, j);
                     		        	    j++;
                     		        	   TextArea generalT=new TextArea();
                  		        	       generalT.setPrefSize(30, 1);
                  		        	       generalT.setPromptText("you may eneter general instructions for teachers here");
                  		        	       gridpane.add(generalT, 0, j);
                  		        	       j++;
                     		        	    Button B1=new Button("Cancel");//canceling test creating
                     		        	    B1.setOnAction(new EventHandler<ActionEvent>()
                                	        {
                                		       @Override
                                		       public void handle(ActionEvent e)
                                			  {
                                		    	   Stage SecondStage=new Stage();
                                		    	   GridPane grid = new GridPane();
                                			       grid.setHgap(10);
                                			       grid.setVgap(10);
                                			       grid.setPadding(new Insets(25, 25, 25, 25));
                                			       Label Message=new Label("Are you sure you want to stop Test creation?");
                                			       grid.add(Message,1, 0);
                                			       Button B1=new Button("Yes");
                                			       B1.setOnAction(new EventHandler<ActionEvent>()
                                				    {
                                					       @Override
                                					       public void handle(ActionEvent e)
                                						  {
                                					    	   SecondStage.close();
                                					    	   primaryStage.close();
                                					    	   T_print(Owner_name[c],c,client);
                                						  }
                                				    });
                                			       Button B2=new Button("No");
                                			       B2.setOnAction(new EventHandler<ActionEvent>()
                                				    {
                                					       @Override
                                					       public void handle(ActionEvent e)
                                						  {
                                					    	   SecondStage.close();
                                						  }
                                				    });
                                			       grid.add(B1, 1, 1);
                                			       grid.add(B2, 2, 1);
                                			       Scene scene = new Scene(grid,400,200);
                                					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                                					SecondStage.setScene(scene);
                                					SecondStage.show();
                                			       
                                			  }
                                	        });
                     		        	    Button B2=new Button("submit");//saving the test
                     		        	    B2.setOnAction(new EventHandler<ActionEvent>()
                                	        {
                                		       @Override
                                		       public void handle(ActionEvent e)
                                			  {
                                		    	   int j=0;
                                		    	   //checking for invalid input
                                		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
                                		    	   else {
                                		    		   test_add.setTime(F1.getText());
                                		    		   F1.setBlendMode(BlendMode.SRC_OVER);
                                		    		   j++;
                                		    	   }
                                		    	   int sum=0;
                                		    	   for(int i=0;i<test_add.getQuestions().size();i++)
                                		    	   {
                                		    		   if(matrix[i].getText().isEmpty())matrix[i].setBlendMode(BlendMode.RED);
                                		    		   else {
                                		    			   test_add.getQuestionGrade().add(matrix[i].getText());
                                		    			   sum+=Integer.parseInt(matrix[i].getText());
                                		    			   matrix[i].setBlendMode(BlendMode.SRC_OVER);
                                    		    		   j++;
                                		    		   }
                                		    	   }
                                		    	   
                                		    	   if(j==1+test_add.getQuestions().size())
                                		    	   {
                                		    		  if(sum==100) {
                                		    			  //adding test
                                		    		   test_add.setCommentsForStudent(generalS.getText());
                                		    		   test_add.setCommentsForTeacher(generalT.getText());
                                 		    		   client.createTest(test_add);
                                		    		   primaryStage.close();
                                		    		   T_print(Owner_name[c],c,client);
                                		    		   }
                                		    	
                                		    	   }
                                			  }
                                	        });
                     		        	    gridpane.add(B1, 0, j);
                     		        	    gridpane.add(B2,1, j);
                     		        	    pane.setContent(gridpane);
                     		        	    Scene scene = new Scene(pane,500,600);
                    					    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    					    primaryStage.setScene(scene);
                    					    primaryStage.show();
                         			  }
                         	        });
               		        	    Button B2=new Button("Advanced Settings");//additional student/teacher instructions
               		        	  B2.setOnAction(new EventHandler<ActionEvent>()
                       	        {
                       		       @Override
                       		       public void handle(ActionEvent e)
                       			  {
                       		    	   //creating adding window
                       		    	    TextArea matrix[][]=new TextArea[test_add.getQuestions().size()][3];
                       		    	    ScrollPane pane=new ScrollPane();
                       		    	    GridPane gridpane = new GridPane();
                   		        	    gridpane.setPadding(new Insets(5));
                   		        	    gridpane.setHgap(10);
                   		        	    gridpane.setVgap(10);
                   		        	    Label L1=new Label("Time");
                   		        	    gridpane.add(L1,0, 0);
                   		        	    TextArea F1=new TextArea();
                   		        	    F1.setPromptText("enter test time here");
                   		        	    F1.setPrefColumnCount(15);
                   		        	    F1.setPrefRowCount(1);
                   		        	    gridpane.add(F1, 1, 0);
                   		        	    int j=1;
                   		        	    for(int i=0;i<test_add.getQuestions().size();i++)
                   		        	    {
                   		        	    	L1=new Label("Question "+(i+1)+":");
                   		        	    	L1.setFont(Font.font( "", FontWeight.BOLD, 17));
                   		        	    	gridpane.add(L1, 0, j);
                   		        	    	j++;
                   		        	    	L1=new Label("Question points");
                   		        	    	gridpane.add(L1, 0, j);
                   		        	    	matrix[i][0]=new TextArea();
                   		        	    	matrix[i][0].setPromptText("enter Question points here");
                   		        	    	matrix[i][0].setPrefColumnCount(15);
                   		        	    	matrix[i][0].setPrefRowCount(1);
                   		        	    	gridpane.add(matrix[i][0], 1, j);
                   		        	    	j++;
                   		        	    	L1=new Label("Student instructions");
                   		        	    	gridpane.add(L1, 0, j);
                   		        	    	matrix[i][1]=new TextArea();
                   		        	    	matrix[i][1].setPromptText("enter Student instructions here");
                   		        	    	matrix[i][1].setPrefColumnCount(15);
                   		        	    	matrix[i][1].setPrefRowCount(1);
                   		        	    	gridpane.add(matrix[i][1], 1, j);
                   		        	    	j++;
                   		        	    	L1=new Label("Teacher instructions");
                   		        	    	gridpane.add(L1, 0, j);
                   		        	    	matrix[i][2]=new TextArea();
                   		        	    	matrix[i][2].setPromptText("enter Teacher instructions here");
                   		        	    	matrix[i][2].setPrefColumnCount(15);
                   		        	    	matrix[i][2].setPrefRowCount(1);
                   		        	    	gridpane.add(matrix[i][2], 1, j);
                   		        	    	j++;
                   		        	    }
                   		        	    TextArea generalS=new TextArea();
                   		        	    generalS.setPrefSize(30, 1);
                   		        	    generalS.setPromptText("you may eneter general instructions for students here");
                   		        	    gridpane.add(generalS, 0, j);
                   		        	    j++;
                   		        	 TextArea generalT=new TextArea();
                		        	    generalT.setPrefSize(30, 1);
                		        	    generalT.setPromptText("you may eneter general instructions for teachers here");
                		        	    gridpane.add(generalT, 0, j);
                		        	    j++;
                   		        	    Button B1=new Button("Cancel");//cnceling test creation
                   		        	    B1.setOnAction(new EventHandler<ActionEvent>()
                              	        {
                              		       @Override
                              		       public void handle(ActionEvent e)
                              			  {
                              		    	   Stage SecondStage=new Stage();
                              		    	   GridPane grid = new GridPane();
                              			       grid.setHgap(10);
                              			       grid.setVgap(10);
                              			       grid.setPadding(new Insets(25, 25, 25, 25));
                              			       Label Message=new Label("Are you sure you want to stop Test creation?");
                              			       grid.add(Message,1, 0);
                              			       Button B1=new Button("Yes");
                              			       B1.setOnAction(new EventHandler<ActionEvent>()
                              				    {
                              					       @Override
                              					       public void handle(ActionEvent e)
                              						  {
                              					    	   SecondStage.close();
                              					    	   primaryStage.close();
                              					    	   T_print(Owner_name[c],c,client);
                              						  }
                              				    });
                              			       Button B2=new Button("No");
                              			       B2.setOnAction(new EventHandler<ActionEvent>()
                              				    {
                              					       @Override
                              					       public void handle(ActionEvent e)
                              						  {
                              					    	   SecondStage.close();
                              						  }
                              				    });
                              			       grid.add(B1, 1, 1);
                              			       grid.add(B2, 2, 1);
                              			       Scene scene = new Scene(grid,400,200);
                              					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                              					SecondStage.setScene(scene);
                              					SecondStage.show();
                              			       
                              			  }
                              	        });
                   		        	    Button B2=new Button("submit");//saving the test
                   		        	    B2.setOnAction(new EventHandler<ActionEvent>()
                              	        {
                              		       @Override
                              		       public void handle(ActionEvent e)
                              			  {
                              		    	   int j=0;
                              		    	   //checking for invalid input
                              		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
                              		    	   else {
                              		    		   test_add.setTime(F1.getText());
                              		    		   F1.setBlendMode(BlendMode.SRC_OVER);
                              		    		   j++;
                              		    	   }
                              		    	   int sum=0;
                              		    	   for(int i=0;i<test_add.getQuestions().size();i++)
                              		    	   {
                              		    		   if(matrix[i][0].getText().isEmpty())matrix[i][0].setBlendMode(BlendMode.RED);
                              		    		   else {
                              		    			   test_add.getQuestionGrade().add(matrix[i][0].getText());
                              		    			   sum+=Integer.parseInt(matrix[i][0].getText());
                              		    			   matrix[i][0].setBlendMode(BlendMode.SRC_OVER);
                                  		    		   j++;
                              		    		   }
                              		    		 if(matrix[i][1].getText().isEmpty())matrix[i][1].setBlendMode(BlendMode.RED);
                            		    		   else {
                            		    			   test_add.getQuestions().get(i).setSInstruction(matrix[i][1].getText());
                            		    			   matrix[i][1].setBlendMode(BlendMode.SRC_OVER);
                                		    		   j++;
                            		    		   }
                              		    		 if(matrix[i][2].getText().isEmpty())matrix[i][2].setBlendMode(BlendMode.RED);
                          		    		   else {
                          		    			   test_add.getQuestions().get(i).setTInstruction(matrix[i][2].getText());
                          		    			   matrix[i][2].setBlendMode(BlendMode.SRC_OVER);
                              		    		   j++;
                          		    		   }
                              		    	   }
                              		    	   if(j==1+test_add.getQuestions().size()*3)
                              		    	   {
                              		    		   if(sum==100)
                              		    		   {
                              		    			   //saving test
                              		    			 test_add.setCommentsForStudent(generalS.getText());
                              		    		   test_add.setCommentsForTeacher(generalT.getText());
                              		    			   client.createTest(test_add);
                                		    		   primaryStage.close();
                                		    		   T_print(Owner_name[c],c,client);
                              		    		   }
                              		    		  
                              		    	   }
                              			  }
                              	        });
                   		        	    gridpane.add(B1, 0, j);
                   		        	    gridpane.add(B2,1, j);
                   		        	    pane.setContent(gridpane);
                   		        	    Scene scene = new Scene(pane,500,600);
                  					    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                  					    primaryStage.setScene(scene);
                  					    primaryStage.show();
                       			  }
                       	        });
               		        	    gridpane.add(B1,0, 1);
               		        	    gridpane.add(B2,1, 1);
               		        	    Scene scene = new Scene(gridpane,400,200);
             					    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             					    primaryStage.setScene(scene);
             					    primaryStage.show();
                		    	   }
                			  }
                	    });
                        hbox.getChildren().addAll(B1,B2);
                        root.setBottom(hbox);
		        	    GridPane.setVgrow(root, Priority.ALWAYS);
		        	    primaryStage.setScene(scene);
		        	    primaryStage.show();
		           }
			  }
	    });
    	Text_edit.add(B2, 1, 1);
    	Scene scene = new Scene(Text_edit,300,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void modify_test(Test Chosen,int c,Client client)//modifying existing test
    {//initializing
		Subject_list=FXCollections.observableArrayList();//get subject list from database
		    Course_list=FXCollections.observableArrayList();//get course list from database
		    Course_list.addAll(client.getCoursesId());
		    Subject_list.addAll(client.getSubjectsId());
       Test Update=new Test();
       
 	   Q_list=client.getAllQuestion();
 	  

 	  ArrayList<Question> rem1=new ArrayList<Question>();
 	  int sign=0;
 	  for(int h=0;h<Q_list.size();h++) {
 	   for(int i=Chosen.getQuestions().size()-1;i>=0;i--)//finding questions not in test
 	   {
 		   if(Q_list.get(h).getCode().equals(Chosen.getQuestions().get(i).getCode()))
 		   {
 			  sign++;
 		   }
 	   }
 	   if(sign==0)rem1.add(Q_list.get(h));
 	  sign=0;
 	  }
 	 System.out.println(rem1);
 	   ArrayList<Question> rem2=new ArrayList<Question>();
 	   for(int i=0;i<rem1.size();i++)//getting question by course
 	   {
 		   if((rem1.get(i).getCode().charAt(0)+rem1.get(i).getCode().charAt(1)+"").equals
 				   (Chosen.getCode().charAt(0)+Chosen.getCode().charAt(1)+""))rem2.add(rem1.get(i));
 	   }
 	  //initialize choice window
 	   BorderPane root = new BorderPane();
 	    Scene scene = new Scene(root, 400, 250, Color.WHITE);

 	    GridPane gridpane = new GridPane();
 	    gridpane.setPadding(new Insets(5));
 	    gridpane.setHgap(10);
 	    gridpane.setVgap(10);
 	    ColumnConstraints column1 = new ColumnConstraints(150, 150,
 	        Double.MAX_VALUE);
 	    ColumnConstraints column2 = new ColumnConstraints(50);
 	    ColumnConstraints column3 = new ColumnConstraints(150, 150,
 	        Double.MAX_VALUE);
 	    column1.setHgrow(Priority.ALWAYS);
 	    column3.setHgrow(Priority.ALWAYS);
 	    gridpane.getColumnConstraints().addAll(column1, column2, column3);

 	    Label candidatesLbl = new Label("Questions");
 	    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
 	    gridpane.add(candidatesLbl, 0, 0);

 	    Label selectedLbl = new Label("add list");
 	    gridpane.add(selectedLbl, 2, 0);
 	    GridPane.setHalignment(selectedLbl, HPos.CENTER);
 	    // Candidates
 	    final ObservableList<String> candidates = FXCollections
 	        .observableArrayList();
 	    for(int i=0;i<Chosen.getQuestions().size();i++)
 	    {
 	    	candidates.add(Chosen.getQuestions().get(i).getCode());
 	    }
 	    final ListView<String> candidatesListView = new ListView<>(candidates);
 	    gridpane.add(candidatesListView, 0, 1);

 	    final ObservableList<String> selected = FXCollections.observableArrayList();
 	   for(int i=0;i<rem2.size();i++)
	    {
	    	selected.add(rem2.get(i).getCode());
	    }
 	    final ListView<String> heroListView = new ListView<>(selected);
 	    gridpane.add(heroListView, 2, 1);

 	    Button sendRightButton = new Button(" > ");
 	    sendRightButton.setOnAction((ActionEvent event) -> {
 	      String potential = candidatesListView.getSelectionModel()
 	          .getSelectedItem();
 	      if (potential != null) {
 	        candidatesListView.getSelectionModel().clearSelection();
 	        candidates.remove(potential);
 	        selected.add(potential);
 	      }
 	    });

 	    Button sendLeftButton = new Button(" < ");
 	    sendLeftButton.setOnAction((ActionEvent event) -> {
 	      String s = heroListView.getSelectionModel().getSelectedItem();
 	      if (s != null) {
 	        heroListView.getSelectionModel().clearSelection();
 	        selected.remove(s);
 	        candidates.add(s);
 	      }
 	    });
 	    VBox vbox = new VBox(5);
 	    vbox.getChildren().addAll(sendRightButton, sendLeftButton);

 	    gridpane.add(vbox, 1, 1);
 	    root.setCenter(gridpane);
         HBox hbox=new HBox();
         Button B1=new Button("Cancel");//canceling test modifying
     	B1.setOnAction(new EventHandler<ActionEvent>()
 	    {
 		       @Override
 		       public void handle(ActionEvent e)
 			  {
 		    	   Stage SecondStage=new Stage();
 		    	   GridPane grid = new GridPane();
 			       grid.setHgap(10);
 			       grid.setVgap(10);
 			       grid.setPadding(new Insets(25, 25, 25, 25));
 			       Label Message=new Label("Are you sure you want to stop Test creation?");
 			       grid.add(Message,1, 0);
 			       Button B1=new Button("Yes");
 			       B1.setOnAction(new EventHandler<ActionEvent>()
 				    {
 					       @Override
 					       public void handle(ActionEvent e)
 						  {
 					    	   SecondStage.close();
 					    	   primaryStage.close();
 					    	   T_print(Owner_name[c],c,client);
 						  }
 				    });
 			       Button B2=new Button("No");
 			       B2.setOnAction(new EventHandler<ActionEvent>()
 				    {
 					       @Override
 					       public void handle(ActionEvent e)
 						  {
 					    	   SecondStage.close();
 						  }
 				    });
 			       grid.add(B1, 1, 1);
 			       grid.add(B2, 2, 1);
 			       Scene scene = new Scene(grid,400,200);
 					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 					SecondStage.setScene(scene);
 					SecondStage.show();
 			       
 			  }
 	    });
         Button B2=new Button("Continue");//continue on modifying
     	B2.setOnAction(new EventHandler<ActionEvent>()
 	    {
 		       @Override
 		       public void handle(ActionEvent e)
 			  {
 		    	   if(!candidates.isEmpty())
 		    	   {
 		    		   //initializing test modifying window
 		    		   Q_list=client.getAllQuestion();
 		    		   for(int i=0;i<candidates.size();i++)
 		    		   {
 		    			   for(int j=0;j<Q_list.size();j++)
 		    			   {
 		    				   if(Q_list.get(j).getCode().equals(candidates.get(i)))
 		    					   Update.getQuestions().add(Q_list.get(j));
 		    			   }
 		    			   
 		    		   }
 		    		   Update.setTime(Chosen.getTime());
        		    	    TextArea matrix[][]=new TextArea[Update.getQuestions().size()][9];
        		    	    ScrollPane pane=new ScrollPane();
        		    	    GridPane gridpane = new GridPane();
    		        	    gridpane.setPadding(new Insets(5));
    		        	    gridpane.setHgap(10);
    		        	    gridpane.setVgap(10);
    		        	    Label L1=new Label("Time");
    		        	    gridpane.add(L1,0, 0);
    		        	    TextArea F1=new TextArea();
    		        	    F1.setText(Update.getTime());
    		        	    F1.setPrefColumnCount(15);
    		        	    F1.setPrefRowCount(1);
    		        	    gridpane.add(F1, 1, 0);
    		        	    int j=1;
    		        	    for(int i=0;i<Update.getQuestions().size();i++)
    		        	    {
    		        	    	L1=new Label("Question "+(i+1)+":");
    		        	    	L1.setFont(Font.font( "", FontWeight.BOLD, 17));
    		        	    	gridpane.add(L1, 0, j);
    		        	    	j++;
    		        	    	
    		        	    		L1=new Label("Question points");
        		        	    	gridpane.add(L1, 0, j);
        		        	    	matrix[i][0]=new TextArea();
        		        	    	matrix[i][0].setPromptText("enter Points here");
        		        	    	gridpane.add(matrix[i][0], 1, j);
    		        	    	
    		        	    	j++;
    		        	    	L1=new Label("Student instructions");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][1]=new TextArea();
    		        	    	matrix[i][1].setText(Update.getQuestions().get(i).getSInstruction());
    		        	    	matrix[i][1].setPrefColumnCount(15);
    		        	    	matrix[i][1].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][1], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Teacher instructions");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][2]=new TextArea();
    		        	    	matrix[i][2].setText(Update.getQuestions().get(i).getTInstruction());
    		        	    	matrix[i][2].setPrefColumnCount(15);
    		        	    	matrix[i][2].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][2], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Question bBody");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][3]=new TextArea();
    		        	    	matrix[i][3].setText(Update.getQuestions().get(i).getBody());
    		        	    	matrix[i][3].setPrefColumnCount(15);
    		        	    	matrix[i][3].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][3], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 1");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][4]=new TextArea();
    		        	    	matrix[i][4].setText(Update.getQuestions().get(i).getAnswer1());
    		        	    	matrix[i][4].setPrefColumnCount(15);
    		        	    	matrix[i][4].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][4], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 2");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][5]=new TextArea();
    		        	    	matrix[i][5].setText(Update.getQuestions().get(i).getAnswer2());
    		        	    	matrix[i][5].setPrefColumnCount(15);
    		        	    	matrix[i][5].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][5], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 3");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][6]=new TextArea();
    		        	    	matrix[i][6].setText(Update.getQuestions().get(i).getAnswer3());
    		        	    	matrix[i][6].setPrefColumnCount(15);
    		        	    	matrix[i][6].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][6], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 4");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][7]=new TextArea();
    		        	    	matrix[i][7].setText(Update.getQuestions().get(i).getAnswer4());
    		        	    	matrix[i][7].setPrefColumnCount(15);
    		        	    	matrix[i][7].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][7], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Correct Answer");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][8]=new TextArea();
    		        	    	matrix[i][8].setText(Update.getQuestions().get(i).getCorrect()+"");
    		        	    	matrix[i][8].setPrefColumnCount(15);
    		        	    	matrix[i][8].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][8], 1, j);
    		        	    	j++;
    		        	    }
    		        	    Button B1=new Button("Cancel");//canceling modifying
    		        	    B1.setOnAction(new EventHandler<ActionEvent>()
               	        {
               		       @Override
               		       public void handle(ActionEvent e)
               			  {
               		    	   Stage SecondStage=new Stage();
               		    	   GridPane grid = new GridPane();
               			       grid.setHgap(10);
               			       grid.setVgap(10);
               			       grid.setPadding(new Insets(25, 25, 25, 25));
               			       Label Message=new Label("Are you sure you want to stop Test creation?");
               			       grid.add(Message,1, 0);
               			       Button B1=new Button("Yes");
               			       B1.setOnAction(new EventHandler<ActionEvent>()
               				    {
               					       @Override
               					       public void handle(ActionEvent e)
               						  {
               					    	   SecondStage.close();
               					    	   primaryStage.close();
               					    	   T_print(Owner_name[c],c,client);
               						  }
               				    });
               			       Button B2=new Button("No");
               			       B2.setOnAction(new EventHandler<ActionEvent>()
               				    {
               					       @Override
               					       public void handle(ActionEvent e)
               						  {
               					    	   SecondStage.close();
               						  }
               				    });
               			       grid.add(B1, 1, 1);
               			       grid.add(B2, 2, 1);
               			       Scene scene = new Scene(grid,400,200);
               					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               					SecondStage.setScene(scene);
               					SecondStage.show();
               			       
               			  }
               	        });
    		        	    Button B2=new Button("submit");//saving changes
    		        	    B2.setOnAction(new EventHandler<ActionEvent>()
               	        {
               		       @Override
               		       public void handle(ActionEvent e)
               			  {
               		    	   int j=0;
               		    	   //checking for invalid input
               		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
               		    	   else {
               		    		   Update.setTime(F1.getText());
               		    		   F1.setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
               		    	   }
               		    	   int sum=0;
               		    	   for(int i=0;i<Update.getQuestions().size();i++)
               		    	   {
               		    		   if(matrix[i][0].getText().isEmpty())matrix[i][0].setBlendMode(BlendMode.RED);
               		    		   else {
               		    			   Update.getQuestionGrade().add(matrix[i][0].getText());
               		    			   sum+=Integer.parseInt(matrix[i][0].getText());
               		    			   matrix[i][0].setBlendMode(BlendMode.SRC_OVER);
                   		    		   j++;
               		    		   }
             		    		   Update.getQuestions().get(i).setSInstruction(matrix[i][1].getText());
             		    		   matrix[i][1].setBlendMode(BlendMode.SRC_OVER);
                 		    	   j++;
           		    			   Update.getQuestions().get(i).setTInstruction(matrix[i][2].getText());
           		    			   matrix[i][2].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
               		    		 if(matrix[i][3].getText().isEmpty())matrix[i][3].setBlendMode(BlendMode.RED);
             		    		   else {
             		    			   Update.getQuestions().get(i).setBody(matrix[i][3].getText());
             		    			   matrix[i][3].setBlendMode(BlendMode.SRC_OVER);
                 		    		   j++;
             		    		   }
               		    		if(matrix[i][4].getText().isEmpty())matrix[i][4].setBlendMode(BlendMode.RED);
          		    		   else {
          		    			   Update.getQuestions().get(i).setAnswer1(matrix[i][4].getText());
          		    			   matrix[i][4].setBlendMode(BlendMode.SRC_OVER);
              		    		   j++;
          		    		   }
               		    		if(matrix[i][5].getText().isEmpty())matrix[i][5].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestions().get(i).setAnswer2(matrix[i][5].getText());
           		    			   matrix[i][5].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][6].getText().isEmpty())matrix[i][6].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestions().get(i).setAnswer3(matrix[i][6].getText());
           		    			   matrix[i][6].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][7].getText().isEmpty())matrix[i][7].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestions().get(i).setAnswer4(matrix[i][7].getText());
           		    			   matrix[i][7].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][8].getText().isEmpty())matrix[i][8].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestions().get(i).setCorrect(Integer.parseInt(matrix[i][8].getText()));
           		    			   matrix[i][8].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    	   }
               		    	   if(j==1+Update.getQuestions().size()*9)
               		    	   {
               		    		   if(sum==100)
               		    		   {
               		    			   //updating the test in database
               		    			   Update.setCode(Chosen.getCode());
               		    			   
               		    			   Update.setOwner(Owner_name[c]);
               		    			   client.updateTest(Update);
                 		    		   primaryStage.close();
                 		    		   T_print(Owner_name[c],c,client);
               		    		   }
               		    		  
               		    	   }
               			  }
               	        });
    		        	    gridpane.add(B1, 0, j);
    		        	    gridpane.add(B2,1, j);
    		        	    pane.setContent(gridpane);
    		        	    Scene scene = new Scene(pane,500,600);
   					    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   					    primaryStage.setScene(scene);
   					    primaryStage.show();
        	 
		        	 
 		    	   }
 			  }
 	    });
         hbox.getChildren().addAll(B1,B2);
         root.setBottom(hbox);
 	    GridPane.setVgrow(root, Priority.ALWAYS);
 	    primaryStage.setScene(scene);
 	    primaryStage.show();
    }
    public ScrollPane addTestScrollPane()//creating a test scrollpane
	{
		ScrollPane sp=new ScrollPane();
		 ObservableList<String> data = FXCollections.observableArrayList();

		    ListView<String> listView = new ListView<String>(data);
		    listView.setPrefSize(300, 250);
		    for(int i=0;i<test_list.size();i++)data.add(test_list.get(i).getCode());

		    listView.setItems(data);
		    listView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		                Question_select=new_val;
		                
		    });
		    sp.setContent(listView);
        
        // Horizontal scroll bar is only displayed when needed
		return sp;
	}
    public void Run_test(String Owner,int c, Client client)//run tests/lock tests/extand test duration
    {
	   //initializing window
    	Question_select="0";
    	primaryStage=new Stage();
    	BorderPane pane=new BorderPane();
    	
    		test_list=client.getAllTests();
    		
    	Scroll=new ScrollPane();
    	Scroll=addTestScrollPane();
    	pane.setLeft(Scroll);
    	Button B1=new Button("run test");//we choose to run tests
		B1.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {//we find the tests that canbe runned by the teacher
		    		   for(int i=0;i<test_list.size();i++)
		    		   {
		    			   if(test_list.get(i).getCode().equals(Question_select)) idx_C=i;
		    		   }
		    		   //initializing the input window
		    			    Stage SecondStage=new Stage();
		    			    GridPane Text_edit=new GridPane();
		    		    	Text_edit.setHgap(10);
		    		    	Text_edit.setVgap(10);
		    		    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
		    		    	Label L1=new Label("Execution code");
		    		    	Text_edit.add(L1,0, 0);
		    		    	TextArea F1=new TextArea();
		    		    	F1.setPromptText("enter 4 digit execution code here");
		    		    	F1.setPrefColumnCount(20);
		    		    	F1.setPrefRowCount(1);
		    		    	Text_edit.add(F1,1, 0);
		    		    	Button B1=new Button("Execute");//executing test
		    		    	B1.setOnAction(new EventHandler<ActionEvent>()
		    			    {
		    				       @Override
		    				       public void handle(ActionEvent e)
		    					  {
		    				    	   //checking for invalid code
		    				    	   if(F1.getText().length()!=4)
		    				    	   {
		    				    		   F1.setBlendMode(BlendMode.RED);
		    				    	   }
		    				    	   else {
		    				    		   F1.setBlendMode(BlendMode.SRC_OVER);
		    				    		   Date date=new Date();
		    				    		   final ExecutedTest add=new ExecutedTest(test_list.get(idx_C),date, test_list.get(idx_C).getTime());
		    				    		   add.setExe_code(F1.getText());
		    				    		   add.getTest().setQuestionGrade(test_list.get(idx_C).getQuestionGrade());
		    				    		   add.setexecuter(Owner_name[c]);
		    				    		   add.setrSign(0);
		    				    		   int check=0;
		    				    		   for(int h=0;h< client.GetAllExecutreTest().size();h++)
		    				    		   {
		    				    			   if(client.GetAllExecutreTest().get(h).getTest().getCode().equals(test_list.get(idx_C).getCode()))
		    				    			   {
		    				    				   if(client.GetAllExecutreTest().get(h).getSign()==0)
		    				    				   {
		    				    					   if(client.GetAllExecutreTest().get(h).getexecuter().equals(Owner_name[c]))check++;
		    				    					   
		    				    				   }
		    				    			   }
		    				    		   }
		    				    		   if(check==0) {
		    				    			   //executing tests
		    				    			   client.AddToExecutreTest(add);
		    				    		   }

		    				    		   SecondStage.close();
		    				    	   }
		    				    	   
		    					  }
		    			    });
		    		    	Text_edit.add(B1,0, 1);
		    		    	Scene scene = new Scene(Text_edit,600,449);
		    				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    				SecondStage.setScene(scene);
		    				SecondStage.show();
		    		  
		    	   }
			  }
	    });
		Button B2=new Button("lock test");//stoping running tests
		B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   //initializing choice window
		    	 Stage second=new Stage();
		    	 ArrayList<ExecutedTest> running=new ArrayList<ExecutedTest>();
		    	 //finding lockable tests
		    	 for(int u=0;u<client.GetAllExecutreTest().size();u++)
		    	 {
		    		 if(client.GetAllExecutreTest().get(u).getSign()==0)
		    			 running.add(client.GetAllExecutreTest().get(u));
		    	 }
		    	 BorderPane p1=new BorderPane();
		    	 ScrollPane sp=new ScrollPane();
		  		 ObservableList<String> data = FXCollections.observableArrayList();

		  		    ListView<String> listView = new ListView<String>(data);
		  		    listView.setPrefSize(300, 250);
		  		  for(int i=0;i<running.size();i++) {
		  		    	if(running.get(i).getexecuter().equals(Owner_name[c]))
		  		    			data.add(running.get(i).getTest().getCode());
		  		    }
		  		    listView.setItems(data);
		  		    listView.getSelectionModel().selectedItemProperty().addListener(
		  		        (ObservableValue<? extends String> ov, String old_val, 
		  		            String new_val) -> {
		  		                Question_select=new_val;
		  		                
		  		    });
		  		    sp.setContent(listView);
		  		    p1.setRight(sp);
		  		    Button B1=new Button("lock Test");//locking the test
		  		    B1.setOnAction(new EventHandler<ActionEvent>()
		  	        {
		  		       @Override
		  		       public void handle(ActionEvent e)
		  			  {
		  		    	   if(Question_select!="0") {
		  		    		   //we find the correct test
		  		    	   for(int i=0;i<running.size();i++)
		  		    	   {
		  		    		   if(running.get(i).getTest().getCode().equals(Question_select))idx_C=i;
		  		    	   }
		  		    	   //initializing choice window
		  		    	   Stage second1=new Stage();
		  		    	   GridPane grid = new GridPane();
					       grid.setHgap(10);
					       grid.setVgap(10);
					       grid.setPadding(new Insets(25, 25, 25, 25));
					       Label Message=new Label("Are you sure you want to lock this Test?");
					       grid.add(Message,1, 0);
					       Button B1=new Button("Yes");
					       //locking the test
					       B1.setOnAction(new EventHandler<ActionEvent>()
						    {
							       @Override
							       public void handle(ActionEvent e)
								  {
							    	   second1.close();
							    	   second.close();
							    	   primaryStage.close();
							    	   int g=listView.getSelectionModel().getSelectedIndex();
							    	   int f=0;
							    	   ArrayList<ExecutedTest> get=new ArrayList<ExecutedTest>();
							           for(int j=0;j<client.GetAllExecutreTest().size();j++)
							           {
							        	   if(client.GetAllExecutreTest().get(j).equals(running.get(g)))
							        	   {
							        		   f=j;
							        		   break;
							        	   }
							           }
							           //updating tes status in server
							           client.GetAllExecutreTest().get(f).setSign(2);
							           client.UpdateExecutreTest(client.GetAllExecutreTest().get(f));
							    	   Run_test(Owner,c,client);
								  }
						    });
					       Button B2=new Button("No");
					       B2.setOnAction(new EventHandler<ActionEvent>()
						    {
							       @Override
							       public void handle(ActionEvent e)
								  {
							    	  second1.close();
								  }
						    });
					       grid.add(B1, 1, 1);
					       grid.add(B2, 2, 1);
					       Scene scene = new Scene(grid,400,200);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							second1.setScene(scene);
							second1.show();
		  		    	   }
		  			  }
		  	        });
		  		    p1.setLeft(B1);
		  		  Scene scene = new Scene(p1,600,449);
		  		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  		second.setScene(scene);
		  		second.show();
			  }
	    });
		Button B3=new Button("change test time");//changing time of a running test
		B3.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {//initializing choice window
		    	   Stage second=new Stage();
		    	   BorderPane p1=new BorderPane();
			    	 ScrollPane sp=new ScrollPane();
			  		 ObservableList<String> data = FXCollections.observableArrayList();

			  		    ListView<String> listView = new ListView<String>(data);
			  		    listView.setPrefSize(300, 250);
			  		    for(int i=0;i<client.GetAllExecutreTest().size();i++) {//finding running tests
			  		    	if(client.GetAllExecutreTest().get(i).getexecuter().equals(Owner_name[c]))
			  		    		if(client.GetAllExecutreTest().get(i).getSign()==0) {
			  		    			data.add(client.GetAllExecutreTest().get(i).getTest().getCode());
			  		    		}
			  		    }

			  		    listView.setItems(data);
			  		    listView.getSelectionModel().selectedItemProperty().addListener(
			  		        (ObservableValue<? extends String> ov, String old_val, 
			  		            String new_val) -> {
			  		                Question_select=new_val;
			  		                
			  		    });
			  		  Button B1=new Button("change time");//changing the time
			  		    B1.setOnAction(new EventHandler<ActionEvent>()
			  	        {
			  		       @Override
			  		       public void handle(ActionEvent e)
			  			  {
			  		    	   if(Question_select!="0")
			  		    	   {//finding the correct test
			  		    		   for(int i=0;i<client.GetAllExecutreTest().size();i++)
			  		    		   {
			  		    			   if(client.GetAllExecutreTest().get(i).getTest().getCode().equals(Question_select));
			  		    			   {
			  		    				   idx_C=i;
			  		    			   }
			  		    		   }
			  		    		   //initializing prompt window
			  		    		   GridPane p=new GridPane();
			  		    		   Label L1=new Label("Explanation for request");
			  		    		   p.add(L1, 0, 0);
			  		    		   TextArea F1=new TextArea();
			  		    		   F1.setPrefColumnCount(30);
			  		    		   F1.setPrefRowCount(2);
			  		    		   F1.setPromptText("enter explenation here");
			  		    		   p.add(F1, 1, 0);
			  		    		   L1=new Label("time to add");
			  		    		   p.add(L1, 0, 1);
			  		    		   TextArea F2=new TextArea();
			  		    		   F2.setPrefColumnCount(5);
			  		    		   F2.setPrefRowCount(1);
			  		    		   F2.setPromptText("enter time here");
			  		    		   p.add(F2, 1, 1);
			  		    		   Button B1=new Button("submit request");//sending requst to manager
			  		    		   B1.setOnAction(new EventHandler<ActionEvent>()
								    {
									       @Override
									       public void handle(ActionEvent e)
										  {
									    	   int selected=0;
									    	   selected=listView.getSelectionModel().getSelectedIndex();
									    	   int j=0;
									    	   //checking invalid input
									    	   if(F1.getText().isEmpty())
									    	   {
									    		   F1.setBlendMode(BlendMode.RED);
									    	   }
									    	   else
									    	   {
									    		   F1.setBlendMode(BlendMode.SRC_OVER);
									    		   j++;
									    	   }
									    	   if(F2.getText().isEmpty())
									    	   {
									    		   F2.setBlendMode(BlendMode.RED);
									    	   }
									    	   else
									    	   {
									    		   F2.setBlendMode(BlendMode.SRC_OVER);
									    		   j++;
									    	   }
									    	   if(j==2)
									    	   {
									    		   //updating running test in server
									    		   client.GetAllExecutreTest().get(idx_C).setR(F1.getText());
									    		   client.GetAllExecutreTest().get(idx_C).setT((int)Integer.parseInt(F2.getText()));
									    		   client.GetAllExecutreTest().get(idx_C).setSign(3);
									    		   System.out.println(client.GetAllExecutreTest().get(idx_C).returnR()+" "+client.GetAllExecutreTest().get(idx_C).returnT());
									    		   client.UpdateExecutreTest(client.GetAllExecutreTest().get(idx_C));
									    		   System.out.println(client.GetAllExecutreTest().get(idx_C).returnR()+" "+client.GetAllExecutreTest().get(idx_C).returnT());

									    		   second.close();
									    	   }
									    	   
										  }
								    });
			  		    		   Button B2=new Button("cancel request");//canceling the requesst
			  		    		   B2.setOnAction(new EventHandler<ActionEvent>()
								    {
									       @Override
									       public void handle(ActionEvent e)
										  {
			  		    		   Stage second1=new Stage();
				  		    	   GridPane grid = new GridPane();
							       grid.setHgap(10);
							       grid.setVgap(10);
							       grid.setPadding(new Insets(25, 25, 25, 25));
							       Label Message=new Label("Are you sure you want to lock this Test?");
							       grid.add(Message,1, 0);
							       Button B1=new Button("Yes");
							       B1.setOnAction(new EventHandler<ActionEvent>()
								    {
									       @Override
									       public void handle(ActionEvent e)
										  {
									    	   second1.close();
									    	   second.close();
										  }
								    });
							       Button B2=new Button("No");
							       B2.setOnAction(new EventHandler<ActionEvent>()
								    {
									       @Override
									       public void handle(ActionEvent e)
										  {
									    	  second1.close();
										  }
								    });
							       grid.add(B1, 1, 1);
							       grid.add(B2, 2, 1);
							       Scene scene = new Scene(grid,400,200);
									scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
									second1.setScene(scene);
									second1.show();
										  }
									       
								    });
			  		    		   p.add(B1, 0, 2);
			  		    		   p.add(B2, 1, 2);
			  		    		    Scene scene = new Scene(p,600,449);
							  		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							  		second.setScene(scene);
							  		second.show();
			  		    	   }
			  			  }
			  	        });
			  		    p1.setLeft(B1);
			  		    sp.setContent(listView);
			  		    p1.setRight(sp);
			  		    Scene scene = new Scene(p1,600,449);
				  		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				  		second.setScene(scene);
				  		second.show();
			  }
	    });
		 Button B4=new Button("back");//back to main window function
		 B4.setOnAction(new EventHandler<ActionEvent>()
	     {
				 @Override
				  public void handle(ActionEvent e)
					  {
					     primaryStage.close();
					  }
		});
		pane.setBottom(B4);
		VBox box=new VBox();
		box.getChildren().addAll(B1,B2,B3);
		pane.setRight(box);
		Scene scene = new Scene(pane,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    	
    	
    }
    public void CheckTests(int c, Client client)//checking solved tests
    {
    	//initializing checking window
        primaryStage=new Stage();
        solved_Tests=new ArrayList<studentTest>();
        //getting solved tests
        studentTest list[]=client.getAllTestsByTeacherId(Owner_name[0]);
        for(int u=0;u<list.length;u++)
        {
        	if(!list[u].isCheck()) {
        		list[u].setremarks();
        		solved_Tests.add(list[u]);
        	}
        }
        BorderPane pane=new BorderPane();
		ScrollPane sp=new ScrollPane();
		 ObservableList<String> data = FXCollections.observableArrayList();

		    ListView<String> listView = new ListView<String>(data);
		    listView.setPrefSize(300, 250);
		   
		    for(int i=0;i<solved_Tests.size();i++)data.add(solved_Tests.get(i).getTest().getCode());

		    listView.setItems(data);
		    listView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		                Question_select=new_val;
		                
		    });
		    sp.setContent(listView);
		    pane.setLeft(sp);
		    Button B1=new Button("Check Test");//checking test
		    B1.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {
					    
					     if(Question_select!="0")
					     {//initializing test view window
					    	 int k=0;
					    	 sum=0;
					    	 int a=listView.getSelectionModel().getSelectedIndex();
					    	 for(int i=0;i<solved_Tests.get(a).getAnswers().size();i++)
					    	 {
					    		 if(solved_Tests.get(a).getAnswers().get(i).endsWith(""))
					    		 {
					    			 sum+=Integer.parseInt(solved_Tests.get(a).getAnswers().get(i))*10;
					    		 }
					    		 else
					    		 {
					    			 sum+=Integer.parseInt(solved_Tests.get(a).getAnswers().get(i));
					    		 }
					    		 
					    	 }
					    	 Chosen=new Test();
					    	 Chosen=solved_Tests.get(a).getTest();
					    	 
					    	ScrollPane pane=new ScrollPane();
					     	GridPane Text_edit=new GridPane();
					     	Text_edit.setHgap(10);
					     	Text_edit.setVgap(10);
					     	Text_edit.setPadding(new Insets(25, 25, 25, 25));
					     	Label L1=new Label("Test: "+Chosen.getCode());
					     	Text_edit.add(L1, 0, 0);
					     	L1=new Label("Taken by: "+solved_Tests.get(a).getStudent());
					     	Text_edit.add(L1, 0, 1);
					     	L1=new Label("Test duration: "+Chosen.getTime()+" minuts");
					     	Text_edit.add(L1, 0, 2);
					     	L1=new Label("Test grade: "+sum);
					     	Text_edit.add(L1, 0, 3);
					     	CheckBox gradeChange=new CheckBox();
					     	TextArea T1=new TextArea();
					     	TextArea T2=new TextArea();
					     	gradeChange.setText("change grade");//changing the grade
					     	gradeChange.setOnAction(new EventHandler<ActionEvent>()
				    		{
				    			 @Override
				    			  public void handle(ActionEvent e)
				    				  {
				    				    if(gradeChange.selectedProperty().get()==true)
				    				    {
				    				    	T1.setVisible(true);
				    				    	T2.setVisible(true);
				    				    }
				    				    else
				    				    {
				    				    	T1.setVisible(false);
				    				    	T2.setVisible(false);
				    				    }
				    				  }
				    		});
					     	Text_edit.add(gradeChange, 0, 4);
					     	T1.setPrefColumnCount(5);
					     	T1.setPrefRowCount(1);
					     	T1.setPromptText("enter new grade here");
					     	T1.setVisible(false);
					     	Text_edit.add(T1, 0, 5);
					     	T2.setPrefColumnCount(20);
					     	T2.setPrefRowCount(1);
					     	T2.setPromptText("enter reason for change here");
					     	T2.setVisible(false);
					     	Text_edit.add(T2, 0, 6);
					     	Label p=new Label("check for copying!");
					     	p.setVisible(false);
					     	p.setTextFill(Color.AQUA);
					     	Text_edit.add(p, 0, 7);
					     	
					     	if(solved_Tests.get(a).isCheat())//notifying about possible copying
					     	{
					     		p.setVisible(true);
					     	}
					     	
					     	int j=8;
					     	CheckBox decision[]=new CheckBox[Chosen.getQuestions().size()];
					     	TextArea instructions[]=new TextArea[Chosen.getQuestions().size()];
					     	for(int i=0;i<Chosen.getQuestions().size();i++)
					     	{
					     		final int val=i;
					     		if(Integer.parseInt(solved_Tests.get(a).getAnswers().get(i))!=0) {
					     		L1=new Label("Question"+(i+1)+":");
					     		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
					         	Text_edit.add(L1, 0, j);
					         	j=j+2;
					         	L1=new Label("Student instructions: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	L1=new Label(Chosen.getQuestions().get(i).getSInstruction());
					         	Text_edit.add(L1, 1, j);
					         	j++;
					         	L1=new Label("Teacher instructions: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	L1=new Label(Chosen.getQuestions().get(i).getTInstruction());
					         	Text_edit.add(L1, 1, j);
					         	j++;
					         	L1=new Label("Question: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label(Chosen.getQuestions().get(i).getBody());
					         	Text_edit.add(L1, 0, j);
					         	j=j+2;
					         	L1=new Label("Answers: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("1)"+Chosen.getQuestions().get(i).getAnswer1());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("2)"+Chosen.getQuestions().get(i).getAnswer2());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("3)"+Chosen.getQuestions().get(i).getAnswer3());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("4)"+Chosen.getQuestions().get(i).getAnswer4());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					        	instructions[val]=new TextArea();
					        	instructions[val].setPrefColumnCount(20);
					        	instructions[val].setPrefRowCount(1);
					        	instructions[val].setPromptText("enter remarks here");
					        	instructions[val].setVisible(false);
					         	decision[val]=new CheckBox();
					         	decision[val].setText("add remarks");//adding remarks on question
					         	decision[val].setOnAction(new EventHandler<ActionEvent>()
					    		{
					    			 @Override
					    			  public void handle(ActionEvent e)
					    				  {
					    				    if(decision[val].selectedProperty().get()==true)
					    				    {
					    				    	instructions[val].setVisible(true);
					    				    }
					    				    else
					    				    {
					    				    	instructions[val].setVisible(false);
					    				    }
					    				  }
					    		});
					         	Text_edit.add(decision[val], 0, j);
					         	j++;
					         	Text_edit.add(instructions[val], 0, j);
					         	j++;
					     		}
					     		else {//answered wrongly questions init
					     			L1=new Label("Question"+(i+1)+":");
						     		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
						     		L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j=j+2;
						         	L1=new Label("Student instructions: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	L1=new Label(Chosen.getQuestions().get(i).getSInstruction());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 1, j);
						         	j++;
						         	L1=new Label("Teacher instructions: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	L1=new Label(Chosen.getQuestions().get(i).getTInstruction());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 1, j);
						         	j++;
						         	L1=new Label("Question: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label(Chosen.getQuestions().get(i).getBody());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j=j+2;
						         	L1=new Label("Answers: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("1)"+Chosen.getQuestions().get(i).getAnswer1());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("2)"+Chosen.getQuestions().get(i).getAnswer2());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("3)"+Chosen.getQuestions().get(i).getAnswer3());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("4)"+Chosen.getQuestions().get(i).getAnswer4());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	instructions[val]=new TextArea();
						         	instructions[val].setPrefColumnCount(20);
						         	instructions[val].setPrefRowCount(1);
						         	instructions[val].setPromptText("enter remarks here");
						         	instructions[val].setVisible(false);
						         	decision[val]=new CheckBox();
						         	decision[val].setText("add remarks");
						         	decision[val].setOnAction(new EventHandler<ActionEvent>()
						    		{
						    			 @Override
						    			  public void handle(ActionEvent e)
						    				  {
						    				    if(decision[val].selectedProperty().get()==true)
						    				    {
						    				    	instructions[val].setVisible(true);
						    				    }
						    				    else
						    				    {
						    				    	instructions[val].setVisible(false);
						    				    }
						    				  }
						    		});
						         	Text_edit.add(decision[val], 0, j);
						         	j++;
						         	Text_edit.add(instructions[val], 0, j);
						         	j++;
					     		}
					     	}
					     	Button Save_Change=new Button("save checked test");//saving checked test with changes
					     	Save_Change.setOnAction(new EventHandler<ActionEvent>()
				    		{
				    			 @Override
				    			  public void handle(ActionEvent e)
				    				  {
				    				      if(gradeChange.selectedProperty().get()==true)
				    				      {
				    				    	  //checking for invalid input
				    				    	  if(T1.getText().isEmpty())
				    				    	  {
				    				    		  T1.setBlendMode(BlendMode.RED);
				    				    	  }
				    				    	  else
				    				    	  {
				    				    		  if(Integer.parseInt(T1.getText())<0 ||Integer.parseInt(T1.getText())>100)
				    				    		  {
				    				    			  T1.setBlendMode(BlendMode.RED);
				    				    		  }
				    				    		  else
				    				    		  {
				    				    			  T1.setBlendMode(BlendMode.SRC_OVER);
				    				    			  solved_Tests.get(a).setGrade(Integer.parseInt(T1.getText()));
				    				    		  }
				    				    	  }
				    				    	  if(T2.getText().isEmpty())
				    				    	  {
				    				    		  T2.setBlendMode(BlendMode.RED);
				    				    	  }
				    				    	  else
				    				    	  {
				    				    		  T2.setBlendMode(BlendMode.SRC_OVER);
				    				    		  solved_Tests.get(a).setReason(T2.getText());
				    				    	  }
				    				    	 
				    				      }
				    				      else
				    				      {
				    				    	  solved_Tests.get(a).setGrade(sum);
				    				    	  solved_Tests.get(a).setReason("");
				    				      }
				    				      for(int i=0;i<Chosen.getQuestions().size();i++)
			    				    	  {
			    				    		  if(decision[i].selectedProperty().get()==true)
			    				    		  {
			    				    			  if(instructions[i].getText().isEmpty())
			    				    			  {
			    				    				  instructions[i].setBlendMode(BlendMode.RED);
			    				    			  }
			    				    			  else
			    				    			  {
			    				    				  instructions[i].setBlendMode(BlendMode.SRC_OVER);
			    				    				  solved_Tests.get(a).getRemark().add(instructions[i].getText());
			    				    			  }
			    				    		  }
			    				    		  else
			    				    		  {
			    				    			  solved_Tests.get(a).getRemark().add("");
			    				    		  }
			    				    	  }
				    				      //updating database
				    				      solved_Tests.get(a).setCheck(true);
				    				      client.UpdateStudentTest(solved_Tests.get(a));
				    				      primaryStage.close();
				    				      CheckTests(c,client);
				    				  }
				    		});
					     	Text_edit.add(Save_Change, 0, j);
					     	pane.setContent(Text_edit);
					     	Scene scene = new Scene(pane,600,449);
					 		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					 		primaryStage.setScene(scene);
					 		primaryStage.show();
					 		
					     }
					     
					  }
					 
			});
		    Button B2=new Button("back");//going back window
		    B2.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {
					     primaryStage.close();
					  }
			});
		    pane.setBottom(B2);
		    pane.setRight(B1);
		    Scene scene = new Scene(pane,600,449);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
    }
    public void Check_test_statistics(int c,Client client)//checking statistics about executed tests
    {
    	//initializing window
    	Stage second=new Stage();
 	   BorderPane p1=new BorderPane();
	    	 ScrollPane sp=new ScrollPane();
	  		 ObservableList<String> data = FXCollections.observableArrayList();
                ArrayList<ExecutedTest> arr=new ArrayList<ExecutedTest>();
	  		    ListView<String> listView = new ListView<String>(data);
	  		    listView.setPrefSize(300, 250);
	  		    for(int i=0;i<client.GetAllExecutreTest().size();i++) {//getting tests
	  		    	if(client.GetAllExecutreTest().get(i).getTest().getOwner().equals(Owner_name[c])&& client.GetAllExecutreTest().get(i).getSign()==1)
	  		    			{
	  		    		      data.add(client.GetAllExecutreTest().get(i).getTest().getCode());
	  		    		      arr.add(client.GetAllExecutreTest().get(i));
	  		    		      
	  		    			}
	  		    }

	  		    listView.setItems(data);
	  		    listView.getSelectionModel().selectedItemProperty().addListener(
	  		        (ObservableValue<? extends String> ov, String old_val, 
	  		            String new_val) -> {
	  		                Question_select=new_val;
	  		                
	  		    });
	  		    Button B1=new Button("get report");//getting report
	  		    B1.setOnAction(new EventHandler<ActionEvent>()
				{
					 @Override
					  public void handle(ActionEvent e)
						  {
						      if(Question_select!="0")
						      {//initializing test report
						    	  ScrollPane pane=new ScrollPane();
						    	  int idx=0;
						    	  idx=listView.getSelectionModel().getSelectedIndex();
						    	  GridPane p=new GridPane();
						    	  p.add(new Label("Test :"+arr.get(idx).getTest().getCode()), 0, 0);
						    	  p.add(new Label("Taken in: "+arr.get(idx).getDate()), 0, 1);
						    	  p.add(new Label("Started at: "+arr.get(idx).getStartTime()), 0, 2);
						    	  p.add(new Label("Test duration: "+arr.get(idx).getCurrentTime()), 0, 3);
						    	  p.add(new Label("Executed by: "+arr.get(idx).getexecuter()), 0, 4);
						    	  int avarage=0;
						    	  //calculating avarage
				    				 for(int b=0;b<arr.get(idx).getGradeList().size();b++)
				    				 {
				    					 avarage+=Integer.parseInt(arr.get(idx).getGradeList().get(b));
				    				 }
				    				 System.out.println(arr.get(idx).getGradeList());
				    				 avarage=avarage/arr.get(idx).getFInishedNum();
				    				 p.add(new Label("avarage grade: "+avarage), 0, 5);
				    				 int median=0;
				    				 //calculating median
				    				 int middle = arr.get(idx).getFInishedNum();
				    				 if(arr.get(idx).getGradeList().size()!=0) {
				    			        if (arr.get(idx).getGradeList().size() % 2 == 1) {
				    			             median=Integer.parseInt(arr.get(idx).getGradeList().get((middle)/2));
				    			        } else {
				    			           median=Integer.parseInt(arr.get(idx).getGradeList().get((middle-1)/2));
				    			        }
				    				 }
				    			     p.add(new Label("median: "+median),0, 6);
				    			     //calculating grade spread by tens
				    			     int gradeSpace[]=new int[10];
				    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
				    			     for(int b=0;b<arr.get(idx).getGradeList().size();b++)
				    			     {
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=10 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>=0)
				    			    		 gradeSpace[0]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=20 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>10)
				    			    		 gradeSpace[1]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=30 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>20)
				    			    		 gradeSpace[2]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=40 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>30)
				    			    		 gradeSpace[3]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=50 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>40)
				    			    		 gradeSpace[4]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=60 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>50)
				    			    		 gradeSpace[5]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=70 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>60)
				    			    		 gradeSpace[6]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=80 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>70)
				    			    		 gradeSpace[7]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=90 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>80)
				    			    		 gradeSpace[8]++;
				    			    	 if(Integer.parseInt(arr.get(idx).getGradeList().get(b))<=100 && 
				    			    			 Integer.parseInt(arr.get(idx).getGradeList().get(b))>90)
				    			    		 gradeSpace[9]++;
				    			    	 
				    			    	 
				    			     }
				    			     //creating hystogram view
				    			        final NumberAxis xAxis = new NumberAxis();
				    			        final CategoryAxis yAxis = new CategoryAxis();
				    			        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
				    			        bc.setTitle("Grade Hystogram");
				    			        xAxis.setLabel("Span");
				    			        yAxis.setLabel("Number of students");
				    			        XYChart.Series series1 = new XYChart.Series();
				    			        series1.setName("0-10");
				    			        series1.getData().add(new XYChart.Data(gradeSpace[0], "0-10"));
				    			        
				    			        XYChart.Series series2 = new XYChart.Series();
				    			        series2.setName("10-20");
				    			        series2.getData().add(new XYChart.Data(gradeSpace[1], "10-20"));
				    			        
				    			        XYChart.Series series3 = new XYChart.Series();
				    			        series3.setName("20-30");
				    			        series3.getData().add(new XYChart.Data(gradeSpace[2], "20-30"));
				    			        
				    			        XYChart.Series series4 = new XYChart.Series();
				    			        series4.setName("30-40");
				    			        series4.getData().add(new XYChart.Data(gradeSpace[3], "30-40"));
				    			        
				    			        XYChart.Series series5 = new XYChart.Series();
				    			        series5.setName("40-50");
				    			        series5.getData().add(new XYChart.Data(gradeSpace[4], "40-50"));
				    			        
				    			        XYChart.Series series6 = new XYChart.Series();
				    			        series6.setName("50-60");
				    			        series6.getData().add(new XYChart.Data(gradeSpace[5], "50-60"));
				    			        
				    			        XYChart.Series series7 = new XYChart.Series();
				    			        series7.setName("60-70");
				    			        series7.getData().add(new XYChart.Data(gradeSpace[6], "60-70"));
				    			        
				    			        XYChart.Series series8 = new XYChart.Series();
				    			        series8.setName("70-80");
				    			        series8.getData().add(new XYChart.Data(gradeSpace[7], "70-80"));
				    			        
				    			        XYChart.Series series9 = new XYChart.Series();
				    			        series9.setName("80-90");
				    			        series9.getData().add(new XYChart.Data(gradeSpace[8], "80-90"));
				    			        
				    			        XYChart.Series series10 = new XYChart.Series();
				    			        series10.setName("90-100");
				    			        series10.getData().add(new XYChart.Data(gradeSpace[9], "90-100"));
				    			        
				    			        bc.getData().addAll(series1, series2, series3,series4,series5,series6,series7,series8,series9,series10);
				    			        bc.setPrefHeight(700);
				    			        p.add(bc, 0, 7);
						    	  pane.setContent(p);
						    	  Scene scene = new Scene(pane,700,449);
							  	  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							  	  second.setScene(scene);
							  	  second.show();
						      }
						  }
				});
	  		    sp.setContent(listView);
	  		    p1.setRight(B1);
	  		    p1.setLeft(sp);
	  		    Scene scene = new Scene(p1,600,449);
		  		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  		second.setScene(scene);
		  		second.show();
		  		
    }
}
