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
import question.ExecutedTest;
import question.Question;
import question.Test;
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
	private Stage primaryStage;
	private ArrayList<Question> Q_list;
	private ArrayList<Test> test_list;
	private ObservableList<String> Subject_list=FXCollections.observableArrayList("02","04","06");//get subject list from database
	private ObservableList<String> Course_list=FXCollections.observableArrayList("20","12","14");//get course list from database
	public static Map<Integer,ArrayList<Test>> solved_Tests=new HashMap();
	private ScrollPane Scroll;
	public final String Owner_name[]=new String[2];//change to size of teachers
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
		solved_Tests.put(val, new ArrayList<Test>());
		val++;
		test_list=new ArrayList<Test>();//get tests from database
		
	}
	public void Q_print(String Owner,int c,Client client)
	{
		
			Owner_name[c]=Owner;
			Q_list = client.getAllQuestion();
			for(int i=Q_list.size()-1;i>=0;i--) {
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
			    	   if(Question_select!="0") Question_mode();
			      }
			       
		    });
		

			B2=new Button("modify Question");
			B2.setOnAction(new EventHandler<ActionEvent>()
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
			    	Q_Update=new Question();
			    	Text t1=null,t2=null,t3=null,t4=null,t5=null,t6=null,t7=null;
			    	for(int i=0;i<Q_list.size();i++)
			   	    {
			   	    	if(Question_select==Q_list.get(i).getCode())
			   	    		{
			   	    		Q_Update=Q_list.get(i);
			   	    		idx_C=i;
			   	    		}
			   	    }
			    	if(Question_select!="0") {
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
                    B3=new Button("Cancel");
        			B3.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
        			    	   primaryStage.close();
        			    	   Q_print(Owner,c,client);
        			      }
        			       
        		    });
        			B4=new Button("Submit");
        			B4.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
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
			Button B4=new Button("add Question");
			B4.setOnAction(new EventHandler<ActionEvent>()
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
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
	                    B3=new Button("Cancel");
	        			B3.setOnAction(new EventHandler<ActionEvent>()
	        		    {
	        			       @Override
	        			       public void handle(ActionEvent e)
	        				  {
	        			    	   primaryStage.close();
	        			    	   Q_print(Owner,c,client);
	        			      }
	        			       
	        		    });
	        			B4=new Button("Submit");
	        		
	        			B4.setOnAction(new EventHandler<ActionEvent>()
	        		    {
	        			       @Override
	        			       public void handle(ActionEvent e)
	        				  {
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
	        			    	   Question_add.setOwner(Owner_name[c]);
	        			    	   System.out.println("123");
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
			B3=new Button("remove Question");
			B3.setOnAction(new EventHandler<ActionEvent>()
		    {
			       @Override
			       public void handle(ActionEvent e)
				  {
			    	   if(Question_select!="0") {
			    	   for(int i=0;i<Q_list.size();i++)
				   	    {
				   	    	if(Question_select==Q_list.get(i).getCode())
				   	    		{
				   	    		idx_C=i;
				   	    		}
				   	    }
			    	   GridPane grid = new GridPane();
				       grid.setHgap(10);
				       grid.setVgap(10);
				       grid.setPadding(new Insets(25, 25, 25, 25));
				       Label Message=new Label("Are you sure you want to delete this question?");
				       grid.add(Message,1, 0);
				       Button B1=new Button("Yes");
				       B1.setOnAction(new EventHandler<ActionEvent>()
					    {
						       @Override
						       public void handle(ActionEvent e)
							  {
						    	   client.removeQuestion(Q_list.get(idx_C).getCode());
						    	   primaryStage.close();
						    	   Q_list.remove(idx_C);
						    	   Q_print(Owner,c,client);
							  }
					    });
				       Button B2=new Button("No");
				       B2.setOnAction(new EventHandler<ActionEvent>()
					    {
						       @Override
						       public void handle(ActionEvent e)
							  {
						    	  primaryStage.close();
						    	  Q_print(Owner,c,client);
							  }
					    });
				       grid.add(B1, 1, 1);
				       grid.add(B2, 2, 1);
				       Scene scene = new Scene(grid,400,200);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
			    	   }
			    	   
				  }
		    });
			VBox buttons = new VBox();
			// Add the Buttons to the VBox
			buttons.getChildren().addAll(B1,B2,B3,B4);
            root.setRight(buttons);
			Scene scene = new Scene(root,600,449);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ScrollPane addScrollPane()
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
	public void Question_mode()
	{
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
	public void Back_button_Press(ActionEvent event) {
		Stage closer=(Stage) ((Node) event.getSource()).getScene().getWindow();
		closer.close();
	}
    public void T_print(String Owner,int c)
    {
    	Owner_name[c]=Owner;
    	primaryStage=new Stage();
    	Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
		SplitPane root = new SplitPane();
		Scroll=new ScrollPane();
		Scroll=addTestScrollPane();
		Button B1=new Button("View test");
		B1.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)
		    		   {
		    			   if(test_list.get(i).getcode().equals(Question_select)) idx_C=i;
		    		   }
		    		   view_test(test_list.get(idx_C));
		           }
			  }
	    });
		Button B2=new Button("Add test");
		B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   Add_test(c);
			  }
	    });
		Button B3=new Button("modify test");
		B3.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)
		    		   {
		    			   if(test_list.get(i).getcode().equals(Question_select)) idx_C=i;
		    		   }
		    		   modify_test(test_list.get(idx_C),c);
		           }
			  }
	    });
		Button B4=new Button("remove test");
		B4.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)
		    		   {
		    			   if(test_list.get(i).getcode().equals(Question_select)) idx_C=i;
		    		   }
		    		   Stage secondStage=new Stage();
		    		   GridPane grid = new GridPane();
				       grid.setHgap(10);
				       grid.setVgap(10);
				       grid.setPadding(new Insets(25, 25, 25, 25));
				       Label Message=new Label("Are you sure you want to delete this Test?");
				       grid.add(Message,1, 0);
				       Button B1=new Button("Yes");
				       B1.setOnAction(new EventHandler<ActionEvent>()
					    {
						       @Override
						       public void handle(ActionEvent e)
							  {
						    	   test_list.remove(idx_C);
						    	   secondStage.close();
						    	   primaryStage.close();
						    	   T_print(Owner_name[c],c);
							  }
					    });
				       Button B2=new Button("No");
				       B2.setOnAction(new EventHandler<ActionEvent>()
					    {
						       @Override
						       public void handle(ActionEvent e)
							  {
						    	   secondStage.close();
							  }
					    });
				       grid.add(B1, 1, 1);
				       grid.add(B2, 2, 1);
				       Scene scene = new Scene(grid,400,200);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						secondStage.setScene(scene);
						secondStage.show();

		           }
			  }
	    });
		VBox box1=new VBox();
		box1.getChildren().addAll(B1,B2,B3,B4);
		root.getItems().addAll(Scroll,box1);
		Scene scene = new Scene(root,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void view_test(Test Chosen)
    {
    	ScrollPane pane=new ScrollPane();
    	GridPane Text_edit=new GridPane();
    	Text_edit.setHgap(10);
    	Text_edit.setVgap(10);
    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
    	Label L1=new Label("Test: "+Chosen.getcode());
    	Text_edit.add(L1, 0, 0);
    	L1=new Label("Written by: "+Chosen.getOwner());
    	Text_edit.add(L1, 0, 1);
    	L1=new Label("Test duration: "+Chosen.getLength()+" minuts");
    	Text_edit.add(L1, 0, 2);
    	int j=3;
    	for(int i=0;i<Chosen.getQuestionlist().size();i++)
    	{
    		L1=new Label("Question"+(i+1)+":");
    		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
        	Text_edit.add(L1, 0, j);
        	j=j+2;
        	L1=new Label("Student instructions: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	L1=new Label(Chosen.getQuestionlist().get(i).getSInstruction());
        	Text_edit.add(L1, 1, j);
        	j++;
        	L1=new Label("Teacher instructions: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	L1=new Label(Chosen.getQuestionlist().get(i).getTInstruction());
        	Text_edit.add(L1, 1, j);
        	j++;
        	L1=new Label("Question: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label(Chosen.getQuestionlist().get(i).getBody());
        	Text_edit.add(L1, 0, j);
        	j=j+2;
        	L1=new Label("Answers: ");
        	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("1)"+Chosen.getQuestionlist().get(i).getAnswer1());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("2)"+Chosen.getQuestionlist().get(i).getAnswer2());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("3)"+Chosen.getQuestionlist().get(i).getAnswer3());
        	Text_edit.add(L1, 0, j);
        	j++;
        	L1=new Label("4)"+Chosen.getQuestionlist().get(i).getAnswer4());
        	Text_edit.add(L1, 0, j);
        	j++;
    	}
    	pane.setContent(Text_edit);
    	Scene scene = new Scene(pane,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    public void Add_test(int c)
    {
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
    	Button B1=new Button("Cancel");
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
					    	   T_print(Owner_name[c],c);
						  }
				    });
			       Button B2=new Button("No");
			       B2.setOnAction(new EventHandler<ActionEvent>()
				    {
					       @Override
					       public void handle(ActionEvent e)
						  {
					    	   primaryStage.close();
					    	   Add_test(c);
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
    	Button B2=new Button("Continue");
    	B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   int j=0;
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
		        	   test_add.setId((test_list.size()+1)+"");
		        	   test_add.setcode(Subjects.getValue()+""+Courses.getValue()+""+Test_number.getValue()+"");
		        	   test_add.setOwner(Owner_name[c]);
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
                        Button B1=new Button("Cancel");
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
                					    	   T_print(Owner_name[c],c);
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
                        Button B2=new Button("Continue");
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
                		    				   if(Q_list.get(j).getCode().equals(selected.get(i)))test_add.getQuestionlist().add(Q_list.get(j));
                		    			   }
                		    			   
                		    		   }
                		    		GridPane gridpane = new GridPane();
               		        	    gridpane.setPadding(new Insets(5));
               		        	    gridpane.setHgap(10);
               		        	    gridpane.setVgap(10);
               		        	    Label L1=new Label("Regular or Advanced Settings?");
               		        	    gridpane.add(L1, 0, 0);
               		        	    Button B1=new Button("Regular Settings");
               		        	    B1.setOnAction(new EventHandler<ActionEvent>()
                         	        {
                         		       @Override
                         		       public void handle(ActionEvent e)
                         			  {
                         		    	    TextArea matrix[]=new TextArea[test_add.getQuestionlist().size()];
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
                     		        	    for(int i=0;i<test_add.getQuestionlist().size();i++)
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
                     		        	    Button B1=new Button("Cancel");
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
                                					    	   T_print(Owner_name[c],c);
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
                     		        	    Button B2=new Button("submit");
                     		        	    B2.setOnAction(new EventHandler<ActionEvent>()
                                	        {
                                		       @Override
                                		       public void handle(ActionEvent e)
                                			  {
                                		    	   int j=0;
                                		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
                                		    	   else {
                                		    		   test_add.setLength(F1.getText());
                                		    		   F1.setBlendMode(BlendMode.SRC_OVER);
                                		    		   j++;
                                		    	   }
                                		    	   int sum=0;
                                		    	   for(int i=0;i<test_add.getQuestionlist().size();i++)
                                		    	   {
                                		    		   if(matrix[i].getText().isEmpty())matrix[i].setBlendMode(BlendMode.RED);
                                		    		   else {
                                		    			   test_add.getPointsList().add(matrix[i].getText());
                                		    			   sum+=Integer.parseInt(matrix[i].getText());
                                		    			   matrix[i].setBlendMode(BlendMode.SRC_OVER);
                                    		    		   j++;
                                		    		   }
                                		    	   }
                                		    	   if(j==1+test_add.getQuestionlist().size())
                                		    	   {
                                		    		  if(sum==100) {
                                 		    		   test_list.add(test_add);
                                		    		   primaryStage.close();
                                		    		   T_print(Owner_name[c],c);
                                		    		   }
                                		    	
                                		    	   }
                                		    	   System.out.println(sum);
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
               		        	    Button B2=new Button("Advanced Settings");
               		        	  B2.setOnAction(new EventHandler<ActionEvent>()
                       	        {
                       		       @Override
                       		       public void handle(ActionEvent e)
                       			  {
                       		    	    TextArea matrix[][]=new TextArea[test_add.getQuestionlist().size()][3];
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
                   		        	    for(int i=0;i<test_add.getQuestionlist().size();i++)
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
                   		        	    Button B1=new Button("Cancel");
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
                              					    	   T_print(Owner_name[c],c);
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
                   		        	    Button B2=new Button("submit");
                   		        	    B2.setOnAction(new EventHandler<ActionEvent>()
                              	        {
                              		       @Override
                              		       public void handle(ActionEvent e)
                              			  {
                              		    	   int j=0;
                              		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
                              		    	   else {
                              		    		   test_add.setLength(F1.getText());
                              		    		   F1.setBlendMode(BlendMode.SRC_OVER);
                              		    		   j++;
                              		    	   }
                              		    	   int sum=0;
                              		    	   for(int i=0;i<test_add.getQuestionlist().size();i++)
                              		    	   {
                              		    		   if(matrix[i][0].getText().isEmpty())matrix[i][0].setBlendMode(BlendMode.RED);
                              		    		   else {
                              		    			   test_add.getPointsList().add(matrix[i][0].getText());
                              		    			   sum+=Integer.parseInt(matrix[i][0].getText());
                              		    			   matrix[i][0].setBlendMode(BlendMode.SRC_OVER);
                                  		    		   j++;
                              		    		   }
                              		    		 if(matrix[i][1].getText().isEmpty())matrix[i][1].setBlendMode(BlendMode.RED);
                            		    		   else {
                            		    			   test_add.getQuestionlist().get(i).setSInstruction(matrix[i][1].getText());
                            		    			   matrix[i][1].setBlendMode(BlendMode.SRC_OVER);
                                		    		   j++;
                            		    		   }
                              		    		 if(matrix[i][2].getText().isEmpty())matrix[i][2].setBlendMode(BlendMode.RED);
                          		    		   else {
                          		    			   test_add.getQuestionlist().get(i).setTInstruction(matrix[i][2].getText());
                          		    			   matrix[i][2].setBlendMode(BlendMode.SRC_OVER);
                              		    		   j++;
                          		    		   }
                              		    	   }
                              		    	   if(j==1+test_add.getQuestionlist().size()*3)
                              		    	   {
                              		    		   if(sum==100)
                              		    		   {
                              		    			 test_list.add(test_add);
                                		    		   primaryStage.close();
                                		    		   T_print(Owner_name[c],c);
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
    public void modify_test(Test Chosen,int c)
    {
       Test Update=new Test();
       Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
 	   Q_list=client.getAllQuestion();
 	   for(int i=0;i<Q_list.size();i++)
 	   {
 		   for(int j=0;j<Q_list.size();j++)
 		   {
 			   if(Integer.parseInt(Q_list.get(i).getCode())<Integer.parseInt(Q_list.get(j).getCode()))
 			   {
 				   Question save=Q_list.get(i);
 				   Q_list.set(i,Q_list.get(j));
 				   Q_list.set(j, save);
 			   }
 		   }
 	   }
 	  for(int i=0;i<Chosen.getQuestionlist().size();i++)
	   {
		   for(int j=0;j<Chosen.getQuestionlist().size();j++)
		   {
			   if(Integer.parseInt(Chosen.getQuestionlist().get(i).getCode())<Integer.parseInt(Chosen.getQuestionlist().get(j).getCode()))
			   {
				   Question save=Chosen.getQuestionlist().get(i);
				   Chosen.getQuestionlist().set(i,Chosen.getQuestionlist().get(j));
				   Chosen.getQuestionlist().set(j, save);
			   }
		   }
	   }
 	  ArrayList<Question> rem1=new ArrayList<Question>();
 	   for(int i=Chosen.getQuestionlist().size()-1;i>=0;i--)
 	   {
 		   if(Q_list.get(i).getCode().equals(Chosen.getQuestionlist().get(i).getCode()))
 			   {
 			   }
 		   else
 		   {
 			   rem1.add(Q_list.get(i));
 		   }
 	   }
 	   ArrayList<Question> rem2=new ArrayList<Question>();
 	   for(int i=0;i<rem1.size();i++)
 	   {
 		   if((rem1.get(i).getCode().charAt(0)+rem1.get(i).getCode().charAt(1)+"").equals
 				   (Chosen.getcode().charAt(0)+Chosen.getcode().charAt(1)+""))rem2.add(rem1.get(i));
 	   }
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
 	    for(int i=0;i<Chosen.getQuestionlist().size();i++)
 	    {
 	    	candidates.add(Chosen.getQuestionlist().get(i).getCode());
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
         Button B1=new Button("Cancel");
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
 					    	   T_print(Owner_name[c],c);
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
         Button B2=new Button("Continue");
     	B2.setOnAction(new EventHandler<ActionEvent>()
 	    {
 		       @Override
 		       public void handle(ActionEvent e)
 			  {
 		    	   if(!candidates.isEmpty())
 		    	   {
 		    		   Q_list=client.getAllQuestion();
 		    		   for(int i=0;i<candidates.size();i++)
 		    		   {
 		    			   for(int j=0;j<Q_list.size();j++)
 		    			   {
 		    				   if(Q_list.get(j).getCode().equals(candidates.get(i)))Update.getQuestionlist().add(Q_list.get(j));
 		    			   }
 		    			   
 		    		   }
 		    		   Update.setLength(Chosen.getLength());
        		    	    TextArea matrix[][]=new TextArea[Update.getQuestionlist().size()][9];
        		    	    ScrollPane pane=new ScrollPane();
        		    	    GridPane gridpane = new GridPane();
    		        	    gridpane.setPadding(new Insets(5));
    		        	    gridpane.setHgap(10);
    		        	    gridpane.setVgap(10);
    		        	    Label L1=new Label("Time");
    		        	    gridpane.add(L1,0, 0);
    		        	    TextArea F1=new TextArea();
    		        	    F1.setText(Update.getLength());
    		        	    F1.setPrefColumnCount(15);
    		        	    F1.setPrefRowCount(1);
    		        	    gridpane.add(F1, 1, 0);
    		        	    int j=1;
    		        	    for(int i=0;i<Update.getQuestionlist().size();i++)
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
    		        	    	matrix[i][1].setText(Update.getQuestionlist().get(i).getSInstruction());
    		        	    	matrix[i][1].setPrefColumnCount(15);
    		        	    	matrix[i][1].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][1], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Teacher instructions");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][2]=new TextArea();
    		        	    	matrix[i][2].setText(Update.getQuestionlist().get(i).getTInstruction());
    		        	    	matrix[i][2].setPrefColumnCount(15);
    		        	    	matrix[i][2].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][2], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Question bBody");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][3]=new TextArea();
    		        	    	matrix[i][3].setText(Update.getQuestionlist().get(i).getBody());
    		        	    	matrix[i][3].setPrefColumnCount(15);
    		        	    	matrix[i][3].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][3], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 1");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][4]=new TextArea();
    		        	    	matrix[i][4].setText(Update.getQuestionlist().get(i).getAnswer1());
    		        	    	matrix[i][4].setPrefColumnCount(15);
    		        	    	matrix[i][4].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][4], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 2");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][5]=new TextArea();
    		        	    	matrix[i][5].setText(Update.getQuestionlist().get(i).getAnswer2());
    		        	    	matrix[i][5].setPrefColumnCount(15);
    		        	    	matrix[i][5].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][5], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 3");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][6]=new TextArea();
    		        	    	matrix[i][6].setText(Update.getQuestionlist().get(i).getAnswer3());
    		        	    	matrix[i][6].setPrefColumnCount(15);
    		        	    	matrix[i][6].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][6], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Answer 4");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][7]=new TextArea();
    		        	    	matrix[i][7].setText(Update.getQuestionlist().get(i).getAnswer4());
    		        	    	matrix[i][7].setPrefColumnCount(15);
    		        	    	matrix[i][7].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][7], 1, j);
    		        	    	j++;
    		        	    	L1=new Label("Correct Answer");
    		        	    	gridpane.add(L1, 0, j);
    		        	    	matrix[i][8]=new TextArea();
    		        	    	matrix[i][8].setText(Update.getQuestionlist().get(i).getCorrect()+"");
    		        	    	matrix[i][8].setPrefColumnCount(15);
    		        	    	matrix[i][8].setPrefRowCount(1);
    		        	    	gridpane.add(matrix[i][8], 1, j);
    		        	    	j++;
    		        	    }
    		        	    Button B1=new Button("Cancel");
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
               					    	   T_print(Owner_name[c],c);
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
    		        	    Button B2=new Button("submit");
    		        	    B2.setOnAction(new EventHandler<ActionEvent>()
               	        {
               		       @Override
               		       public void handle(ActionEvent e)
               			  {
               		    	   int j=0;
               		    	   if(F1.getText().isEmpty())F1.setBlendMode(BlendMode.RED);
               		    	   else {
               		    		   Update.setLength(F1.getText());
               		    		   F1.setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
               		    	   }
               		    	   int sum=0;
               		    	   for(int i=0;i<Update.getQuestionlist().size();i++)
               		    	   {
               		    		   if(matrix[i][0].getText().isEmpty())matrix[i][0].setBlendMode(BlendMode.RED);
               		    		   else {
               		    			   Update.getPointsList().add(matrix[i][0].getText());
               		    			   sum+=Integer.parseInt(matrix[i][0].getText());
               		    			   matrix[i][0].setBlendMode(BlendMode.SRC_OVER);
                   		    		   j++;
               		    		   }
             		    		   Update.getQuestionlist().get(i).setSInstruction(matrix[i][1].getText());
             		    		   matrix[i][1].setBlendMode(BlendMode.SRC_OVER);
                 		    	   j++;
           		    			   Update.getQuestionlist().get(i).setTInstruction(matrix[i][2].getText());
           		    			   matrix[i][2].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
               		    		 if(matrix[i][3].getText().isEmpty())matrix[i][3].setBlendMode(BlendMode.RED);
             		    		   else {
             		    			   Update.getQuestionlist().get(i).setBody(matrix[i][3].getText());
             		    			   matrix[i][3].setBlendMode(BlendMode.SRC_OVER);
                 		    		   j++;
             		    		   }
               		    		if(matrix[i][4].getText().isEmpty())matrix[i][4].setBlendMode(BlendMode.RED);
          		    		   else {
          		    			   Update.getQuestionlist().get(i).setAnswer1(matrix[i][4].getText());
          		    			   matrix[i][4].setBlendMode(BlendMode.SRC_OVER);
              		    		   j++;
          		    		   }
               		    		if(matrix[i][5].getText().isEmpty())matrix[i][5].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestionlist().get(i).setAnswer2(matrix[i][5].getText());
           		    			   matrix[i][5].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][6].getText().isEmpty())matrix[i][6].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestionlist().get(i).setAnswer3(matrix[i][6].getText());
           		    			   matrix[i][6].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][7].getText().isEmpty())matrix[i][7].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestionlist().get(i).setAnswer4(matrix[i][7].getText());
           		    			   matrix[i][7].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    		if(matrix[i][8].getText().isEmpty())matrix[i][8].setBlendMode(BlendMode.RED);
           		    		   else {
           		    			   Update.getQuestionlist().get(i).setCorrect(Integer.parseInt(matrix[i][8].getText()));
           		    			   matrix[i][8].setBlendMode(BlendMode.SRC_OVER);
               		    		   j++;
           		    		   }
               		    	   }
               		    	   if(j==1+Update.getQuestionlist().size()*9)
               		    	   {
               		    		   if(sum==100)
               		    		   {
               		    			   Update.setcode(Chosen.getcode());
               		    			   Update.setId(test_list.size()+"");
               		    			   Update.setOwner(Owner_name[c]);
               		    			 test_list.add(Update);
                 		    		   primaryStage.close();
                 		    		   T_print(Owner_name[c],c);
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
    public ScrollPane addTestScrollPane()
	{
		ScrollPane sp=new ScrollPane();
		 ObservableList<String> data = FXCollections.observableArrayList();

		    ListView<String> listView = new ListView<String>(data);
		    listView.setPrefSize(300, 250);
		    for(int i=0;i<test_list.size();i++)data.add(test_list.get(i).getcode());

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
    public void Run_test(String Owner,int c)
    {
    	Question_select="0";
    	primaryStage=new Stage();
    	BorderPane pane=new BorderPane();
    	Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
    	if(start==0) {
    		test_list=new ArrayList<Test>();
    		Test T1=new Test("1","201201",Owner,"120");
    		T1.getQuestionlist().addAll(client.getAllQuestion());
    		T1.getQuestionlist().remove(3);
    		T1.getQuestionlist().remove(1);
    		test_list.add(T1);
    		Test T2=new Test("1","202001",Owner,"30");
    		T2.getQuestionlist().addAll(client.getAllQuestion());
    		T2.getQuestionlist().remove(2);
    		T2.getQuestionlist().remove(1);
    		test_list.add(T2);
    		start++;
    		}
    	Scroll=new ScrollPane();
    	Scroll=addTestScrollPane();
    	pane.setLeft(Scroll);
    	Button B1=new Button("run test");
		B1.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   if(Question_select!="0")
		    	   {
		    		   for(int i=0;i<test_list.size();i++)
		    		   {
		    			   if(test_list.get(i).getcode().equals(Question_select)) idx_C=i;
		    		   }
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
		    		    	Button B1=new Button("Execute");
		    		    	B1.setOnAction(new EventHandler<ActionEvent>()
		    			    {
		    				       @Override
		    				       public void handle(ActionEvent e)
		    					  {
		    				    	   if(F1.getText().length()!=4)
		    				    	   {
		    				    		   F1.setBlendMode(BlendMode.RED);
		    				    	   }
		    				    	   else {
		    				    		   F1.setBlendMode(BlendMode.SRC_OVER);
		    				    		   Date date=new Date();
		    				    		   final ExecutedTest add=new ExecutedTest(test_list.get(idx_C),date, test_list.get(idx_C).getLength());
		    				    		   add.setExe_code(F1.getText());
		    				    		   add.getTest().getPointsList().add("45");
		    				    		   add.getTest().getPointsList().add("55");
		    				    		   add.setexecuter(Owner_name[c]);
		    				    		   System.out.println(add.getexecuter());
		    				    		   add.setrSign(0);
		    				    		   int check=0;
		    				    		   for(int h=0;h< Main.ExecuteList.size();h++)
		    				    		   {
		    				    			   if(Main.ExecuteList.get(h).getTest().getcode().equals(test_list.get(idx_C).getcode()))
		    				    			   {
		    				    				   if(Main.ExecuteList.get(h).getSign()==0)
		    				    				   {
		    				    					   if(Main.ExecuteList.get(h).getexecuter().equals(Owner_name[c]))check++;
		    				    					   
		    				    				   }
		    				    			   }
		    				    		   }
		    				    		   if(check==0)Main.ExecuteList.add(add);
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
		Button B2=new Button("lock test");
		B2.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	 Stage second=new Stage();
		    	 BorderPane p1=new BorderPane();
		    	 ScrollPane sp=new ScrollPane();
		  		 ObservableList<String> data = FXCollections.observableArrayList();

		  		    ListView<String> listView = new ListView<String>(data);
		  		    listView.setPrefSize(300, 250);
		  		  for(int i=0;i<Main.ExecuteList.size();i++) {
		  		    	if(Main.ExecuteList.get(i).getexecuter().equals(Main.TController[c].Owner_name[c]))
		  		    			data.add(Main.ExecuteList.get(i).getTest().getcode());
		  		    }
		  		    listView.setItems(data);
		  		    listView.getSelectionModel().selectedItemProperty().addListener(
		  		        (ObservableValue<? extends String> ov, String old_val, 
		  		            String new_val) -> {
		  		                Question_select=new_val;
		  		                
		  		    });
		  		    sp.setContent(listView);
		  		    p1.setRight(sp);
		  		    Button B1=new Button("lock Test");
		  		    B1.setOnAction(new EventHandler<ActionEvent>()
		  	        {
		  		       @Override
		  		       public void handle(ActionEvent e)
		  			  {
		  		    	   if(Question_select!="0") {
		  		    	   for(int i=0;i<Main.ExecuteList.size();i++)
		  		    	   {
		  		    		   if(Main.ExecuteList.get(i).getTest().getcode().equals(Question_select))idx_C=i;
		  		    	   }
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
							    	   primaryStage.close();
							    	   for(int i=0;i<Main.ExecuteList.get(idx_C).getSignUpList().size();i++)
							    	   {
							    		  System.out.println(Main.ExecuteList.get(idx_C).getSignUpList().get(i)+" "+Main.Controller[0].Owner_name[0]);
							    		  if(Main.ExecuteList.get(idx_C).getSignUpList().get(i).equals(Main.Controller[0].Owner_name[0]))
							    		  {
							    			  
							    			  Main.Controller[0].closestage.get(0).close();
							    			  for(int j=0;j<StudentController.signUp_test_list.get(0).size();j++)
							    			 {
							    				 if(Main.ExecuteList.get(idx_C).getTest().getcode().equals(StudentController.signUp_test_list.get(0).get(j).getcode()))
							    				 {
							    					 StudentController.signUp_test_list.get(0).remove(j);
							    				 }
							    			 }
							    		 }
							    		  System.out.println(Main.ExecuteList.get(idx_C).getSignUpList().get(i)+" "+Main.Controller[1].Owner_name[1]);
							    		  if(Main.ExecuteList.get(idx_C).getSignUpList().get(i).equals(Main.Controller[1].Owner_name[1]))
							    		  {
							    			  Main.Controller[1].closestage.get(1).close();
							    			  for(int j=0;j<StudentController.signUp_test_list.get(1).size();j++)
								    			 {
								    				 if(Main.ExecuteList.get(idx_C).getTest().getcode().equals(StudentController.signUp_test_list.get(1).get(j).getcode()))
								    				 {
								    					 StudentController.signUp_test_list.get(1).remove(j);
								    				 }
								    			 }
							    		  }
							    	   }
							    	   Main.ExecuteList.remove(idx_C);
							    	   Run_test(Owner,c);
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
		Button B3=new Button("change test time");
		B3.setOnAction(new EventHandler<ActionEvent>()
	    {
		       @Override
		       public void handle(ActionEvent e)
			  {
		    	   Stage second=new Stage();
		    	   BorderPane p1=new BorderPane();
			    	 ScrollPane sp=new ScrollPane();
			  		 ObservableList<String> data = FXCollections.observableArrayList();

			  		    ListView<String> listView = new ListView<String>(data);
			  		    listView.setPrefSize(300, 250);
			  		    for(int i=0;i<Main.ExecuteList.size();i++) {
			  		    	if(Main.ExecuteList.get(i).getexecuter().equals(Main.TController[c].Owner_name[c]))
			  		    			data.add(Main.ExecuteList.get(i).getTest().getcode());
			  		    }

			  		    listView.setItems(data);
			  		    listView.getSelectionModel().selectedItemProperty().addListener(
			  		        (ObservableValue<? extends String> ov, String old_val, 
			  		            String new_val) -> {
			  		                Question_select=new_val;
			  		                
			  		    });
			  		  Button B1=new Button("change time");
			  		    B1.setOnAction(new EventHandler<ActionEvent>()
			  	        {
			  		       @Override
			  		       public void handle(ActionEvent e)
			  			  {
			  		    	   if(Question_select!="0")
			  		    	   {
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
			  		    		   Button B1=new Button("submit request");
			  		    		   B1.setOnAction(new EventHandler<ActionEvent>()
								    {
									       @Override
									       public void handle(ActionEvent e)
										  {
									    	   int selected=0;
									    	   selected=listView.getSelectionModel().getSelectedIndex();
									    	   int j=0;
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
									    		   Main.Manager.handleTimeChangeRequest(selected, F1.getText(),F2.getText());
									    		   second.close();
									    	   }
									    	   
										  }
								    });
			  		    		   Button B2=new Button("cancel request");
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
		 Button B4=new Button("back");
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
    public void CheckTests(int c)
    {
        primaryStage=new Stage();
        BorderPane pane=new BorderPane();
		ScrollPane sp=new ScrollPane();
		 ObservableList<String> data = FXCollections.observableArrayList();

		    ListView<String> listView = new ListView<String>(data);
		    listView.setPrefSize(300, 250);
		   
		    for(int i=0;i<solved_Tests.get(c).size();i++)data.add(solved_Tests.get(c).get(i).getcode());

		    listView.setItems(data);
		    listView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		                Question_select=new_val;
		                
		    });
		    sp.setContent(listView);
		    pane.setLeft(sp);
		    Button B1=new Button("Check Test");
		    B1.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {
					    
					     if(Question_select!="0")
					     {
					    	 int k=0;
					    	 sum=0;
					    	 int a=listView.getSelectionModel().getSelectedIndex();
					    	 int sign=0;
					    	 for(int i=0;i<solved_Tests.size();i++)
					    	 {
					    		 for(int d=0;d<solved_Tests.get(i).size();d++)
					    		 {
					    			 
					    			 if(sign==a)
					    			 {
					    				 idx2=d;
					    				 idx_C=i;
					    			 }
					    			 sign++;
					    		 }
					    	 }
					    	 for(int i=0;i<solved_Tests.get(idx_C).get(idx2).getAnswers().size();i++)
					    	 {
					    		 sum+=solved_Tests.get(idx_C).get(idx2).getAnswers().get(i);
					    	 }
					    	 Chosen=new Test();
					    	 Chosen=solved_Tests.get(idx_C).get(idx2);
					    	 
					    	ScrollPane pane=new ScrollPane();
					     	GridPane Text_edit=new GridPane();
					     	Text_edit.setHgap(10);
					     	Text_edit.setVgap(10);
					     	Text_edit.setPadding(new Insets(25, 25, 25, 25));
					     	Label L1=new Label("Test: "+Chosen.getcode());
					     	Text_edit.add(L1, 0, 0);
					     	L1=new Label("Taken by: "+Chosen.getName());
					     	Text_edit.add(L1, 0, 1);
					     	L1=new Label("Test duration: "+Chosen.getLength()+" minuts");
					     	Text_edit.add(L1, 0, 2);
					     	L1=new Label("Test grade: "+sum);
					     	Text_edit.add(L1, 0, 3);
					     	CheckBox gradeChange=new CheckBox();
					     	TextArea T1=new TextArea();
					     	TextArea T2=new TextArea();
					     	gradeChange.setText("change grade");
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
					     	if(Chosen.getcop()==1)
					     	{
					     		p.setVisible(true);
					     	}
					     	int j=8;
					     	CheckBox decision[]=new CheckBox[Chosen.getQuestionlist().size()];
					     	TextArea instructions[]=new TextArea[Chosen.getQuestionlist().size()];
					     	for(int i=0;i<Chosen.getQuestionlist().size();i++)
					     	{
					     		final int val=i;
					     		if(Chosen.getAnswers().get(i)!=0) {
					     		L1=new Label("Question"+(i+1)+":");
					     		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
					         	Text_edit.add(L1, 0, j);
					         	j=j+2;
					         	L1=new Label("Student instructions: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	L1=new Label(Chosen.getQuestionlist().get(i).getSInstruction());
					         	Text_edit.add(L1, 1, j);
					         	j++;
					         	L1=new Label("Teacher instructions: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	L1=new Label(Chosen.getQuestionlist().get(i).getTInstruction());
					         	Text_edit.add(L1, 1, j);
					         	j++;
					         	L1=new Label("Question: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label(Chosen.getQuestionlist().get(i).getBody());
					         	Text_edit.add(L1, 0, j);
					         	j=j+2;
					         	L1=new Label("Answers: ");
					         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("1)"+Chosen.getQuestionlist().get(i).getAnswer1());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("2)"+Chosen.getQuestionlist().get(i).getAnswer2());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("3)"+Chosen.getQuestionlist().get(i).getAnswer3());
					         	Text_edit.add(L1, 0, j);
					         	j++;
					         	L1=new Label("4)"+Chosen.getQuestionlist().get(i).getAnswer4());
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
					     		else {
					     			L1=new Label("Question"+(i+1)+":");
						     		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
						     		L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j=j+2;
						         	L1=new Label("Student instructions: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	L1=new Label(Chosen.getQuestionlist().get(i).getSInstruction());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 1, j);
						         	j++;
						         	L1=new Label("Teacher instructions: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	L1=new Label(Chosen.getQuestionlist().get(i).getTInstruction());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 1, j);
						         	j++;
						         	L1=new Label("Question: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label(Chosen.getQuestionlist().get(i).getBody());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j=j+2;
						         	L1=new Label("Answers: ");
						         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("1)"+Chosen.getQuestionlist().get(i).getAnswer1());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("2)"+Chosen.getQuestionlist().get(i).getAnswer2());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("3)"+Chosen.getQuestionlist().get(i).getAnswer3());
						         	L1.setTextFill(Color.RED);
						         	Text_edit.add(L1, 0, j);
						         	j++;
						         	L1=new Label("4)"+Chosen.getQuestionlist().get(i).getAnswer4());
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
					     	Button Save_Change=new Button("save checked test");
					     	Save_Change.setOnAction(new EventHandler<ActionEvent>()
				    		{
				    			 @Override
				    			  public void handle(ActionEvent e)
				    				  {
				    				      if(gradeChange.selectedProperty().get()==true)
				    				      {
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
				    				    			  Chosen.setGrade(Integer.parseInt(T1.getText()));
				    				    		  }
				    				    	  }
				    				    	  if(T2.getText().isEmpty())
				    				    	  {
				    				    		  T2.setBlendMode(BlendMode.RED);
				    				    	  }
				    				    	  else
				    				    	  {
				    				    		  T2.setBlendMode(BlendMode.SRC_OVER);
			    				    			  Chosen.setReason(T2.getText());
				    				    	  }
				    				    	 
				    				      }
				    				      else
				    				      {
				    				    	  Chosen.setGrade(sum);
				    				    	  Chosen.setReason("");
				    				      }
				    				      for(int i=0;i<Chosen.getQuestionlist().size();i++)
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
			    				    				  Chosen.getremarks().add(instructions[i].getText());
			    				    			  }
			    				    		  }
			    				    		  else
			    				    		  {
			    				    			  Chosen.getremarks().add("");
			    				    		  }
			    				    	  }
				    				      solved_Tests.get(idx_C).remove(idx2);
				    				      int k=0;
				    				      for(int i=0;i<Main.Controller.length;i++)
				    				      {
				    				    	  if(Main.Controller[i].Owner_name[i].equals(Chosen.getName()))k=i;
				    				      }
				    				      StudentController.checkedTests.get(k).add(Chosen);
				    				      for(int i=0;i<Main.ExecuteList.size();i++)
				    				      {
				    				    	  if((Main.ExecuteList.get(i).getTest().getcode().equals(Chosen.getcode()) &&(
				    				    			  Main.ExecuteList.get(i).getTest().getOwner().equals(Chosen.getOwner()))))
				    				    	  {
				    				    		  Main.ExecuteList.get(i).getGradeList().add(Chosen.getGrade()+"");
				    				    		  System.out.println(Main.ExecuteList.get(i).getGradeList());
				    				    	  }
				    				      }
				    				      primaryStage.close();
				    				      CheckTests(c);
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
		    Button B2=new Button("back");
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
    public void Check_test_statistics(int c)
    {
    	Stage second=new Stage();
 	   BorderPane p1=new BorderPane();
	    	 ScrollPane sp=new ScrollPane();
	  		 ObservableList<String> data = FXCollections.observableArrayList();

	  		    ListView<String> listView = new ListView<String>(data);
	  		    listView.setPrefSize(300, 250);
	  		    for(int i=0;i<Main.ExecuteList.size();i++) {
	  		    	if(Main.ExecuteList.get(i).getTest().getOwner().equals(Main.TController[c].Owner_name[c])&& Main.ExecuteList.get(i).getSign()==1)
	  		    			data.add(Main.ExecuteList.get(i).getTest().getcode());
	  		    }

	  		    listView.setItems(data);
	  		    listView.getSelectionModel().selectedItemProperty().addListener(
	  		        (ObservableValue<? extends String> ov, String old_val, 
	  		            String new_val) -> {
	  		                Question_select=new_val;
	  		                
	  		    });
	  		    Button B1=new Button("get report");
	  		    B1.setOnAction(new EventHandler<ActionEvent>()
				{
					 @Override
					  public void handle(ActionEvent e)
						  {
						      if(Question_select!="0")
						      {
						    	  ScrollPane pane=new ScrollPane();
						    	  int idx=0;
						    	  idx=listView.getSelectionModel().getSelectedIndex();
						    	  GridPane p=new GridPane();
						    	  p.add(new Label("Test :"+Main.ExecuteList.get(idx).getTest().getcode()), 0, 0);
						    	  p.add(new Label("Taken in: "+Main.ExecuteList.get(idx).getDate()), 0, 1);
						    	  p.add(new Label("Started at: "+Main.ExecuteList.get(idx).getStartTime()), 0, 2);
						    	  p.add(new Label("Test duration: "+Main.ExecuteList.get(idx).getCurrentTime()), 0, 3);
						    	  p.add(new Label("Student number: "+Main.ExecuteList.get(idx).getStudentNumber()), 0, 4);
						    	  p.add(new Label("Finished student number: "+Main.ExecuteList.get(idx).getFInishedNum()), 0, 5);
						    	  p.add(new Label("Force Finished student number: "+Main.ExecuteList.get(idx).getFFInishedNum()), 0, 6);
						    	  p.add(new Label("Signup list: "+Main.ExecuteList.get(idx).getSignUpList()), 0, 7);
						    	  p.add(new Label("Executed by: "+Main.ExecuteList.get(idx).getexecuter()), 0, 8);
						    	  int avarage=0;
				    				 for(int b=0;b<Main.ExecuteList.get(idx).getGradeList().size();b++)
				    				 {
				    					 avarage+=Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b));
				    				 }
				    				 if(Main.ExecuteList.get(idx).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(idx).getGradeList().size();
				    				 p.add(new Label("avarage grade: "+avarage), 0, 9);
				    				 int median=0;
				    				 int middle = Main.ExecuteList.get(idx).getGradeList().size();
				    				 if(Main.ExecuteList.get(idx).getGradeList().size()!=0) {
				    			        if (Main.ExecuteList.get(idx).getGradeList().size() % 2 == 1) {
				    			             median=Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get((middle)/2));
				    			        } else {
				    			           median=Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get((middle-1)/2));
				    			        }
				    				 }
				    			     p.add(new Label("median: "+median),0, 10);
				    			     int gradeSpace[]=new int[10];
				    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
				    			     for(int b=0;b<Main.ExecuteList.get(idx).getGradeList().size();b++)
				    			     {
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=10 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>=0)
				    			    		 gradeSpace[0]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=20 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>10)
				    			    		 gradeSpace[1]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=30 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>20)
				    			    		 gradeSpace[2]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=40 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>30)
				    			    		 gradeSpace[3]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=50 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>40)
				    			    		 gradeSpace[4]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=60 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>50)
				    			    		 gradeSpace[5]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=70 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>60)
				    			    		 gradeSpace[6]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=80 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>70)
				    			    		 gradeSpace[7]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=90 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>80)
				    			    		 gradeSpace[8]++;
				    			    	 if(Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))<=100 && 
				    			    			 Integer.parseInt(Main.ExecuteList.get(idx).getGradeList().get(b))>90)
				    			    		 gradeSpace[9]++;
				    			    	 
				    			    	 
				    			     }
				    			     
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
				    			        p.add(bc, 0, 11);
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
