package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import question.ExecutedTest;
import question.Test;

public class StudentController {
	public final String Owner_name[]=new String[2];
	private Stage primaryStage;
	public static final ArrayList<Stage> closestage=new  ArrayList<Stage>();
	public static final ArrayList<Stage> Messagestage=new  ArrayList<Stage>();
	public static Map <Integer,ArrayList<Test>> signUp_test_list=new HashMap();
	private ArrayList<Test> test_list;
	private String Question_select="0";
	private Test save=new Test();
	private int start=0;
	private int idx=0;
	private String ID1;
	private String pass1;
	private static int val=0;
	public static  Map <Integer,ArrayList<Test>> checkedTests=new HashMap();
	public static final Timeline time1[]=new Timeline[2];

	public StudentController()
	{
		signUp_test_list.put(val, new ArrayList<Test>());
		checkedTests.put(val, new ArrayList<Test>());
		val++;
		Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
		primaryStage=new Stage();
		test_list=new ArrayList<Test>();
		Test T1=new Test("1","204301","","120");
		T1.getQuestionlist().addAll(client.getAllQuestion());
		ArrayList<String> pointList=new ArrayList<String>();
		pointList.add("50");
		pointList.add("10");
		pointList.add("10");
		pointList.add("30");
		T1.setPointsList(pointList);
		T1.setExe_code("1234");
		Test T2=new Test("1","204401","","160");
		T2.getQuestionlist().addAll(client.getAllQuestion());
		T2.getQuestionlist().remove(1);
		pointList.add("50");
		pointList.add("10");
		pointList.add("40");
		T2.setPointsList(pointList);
		T2.setExe_code("1234");
		test_list.add(T1);
		test_list.add(T2);
	}
	public void signUp(String Owner ,int c)
	{
		
		Owner_name[c]=Owner;
		Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
		
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

		    Label candidatesLbl = new Label("Tests");
		    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
		    gridpane.add(candidatesLbl, 0, 0);

		    Label selectedLbl = new Label("selected");
		    gridpane.add(selectedLbl, 2, 0);
		    GridPane.setHalignment(selectedLbl, HPos.CENTER);

		    // Candidates
		    final ObservableList<String> candidates = FXCollections
		        .observableArrayList();
		    ArrayList<Test> rem=new ArrayList<Test>();
		    int sign=0;
		    for(int i=0;i<Main.ExecuteList.size();i++)
		    {
		    	for(int j=0;j<signUp_test_list.get(c).size();j++)
		    	{
		    		if(Main.ExecuteList.get(i).getTest().getcode().equals(this.signUp_test_list.get(c).get(j).getcode()) )
		    			{
		    			    sign++;
		    			}
		    		
		    	}
		    	if(sign==0) {
		    		if(Main.ExecuteList.get(i).getSign()==0)rem.add(Main.ExecuteList.get(i).getTest());
		    	}
		    	sign=0;
		    }
		    for(int i=0;i<rem.size();i++)
		    {
		    	 candidates.add(rem.get(i).getcode());
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
		    Button B1=new Button("SignUp");
		    B1.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {    
					       if(selected.size()==0)
					       {
					    	   Stage SecondStage=new Stage();
					    	   GridPane grid = new GridPane();
						       grid.setHgap(10);
						       grid.setVgap(10);
						       grid.setPadding(new Insets(25, 25, 25, 25));
						       Label Message=new Label("you added no Tests to signup for , do you wish to continue?");
						       grid.add(Message,1, 0);
						       Button B1=new Button("Yes");
						       B1.setOnAction(new EventHandler<ActionEvent>()
							    {
								       @Override
								       public void handle(ActionEvent e)
									  {
								    	   SecondStage.close();
								    	   primaryStage.close();
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
					       else
					       {
					    	   int index;
					    	   for(int i=0;i<selected.size();i++)
					    	   {
					    		   for(int j=0;j<Main.ExecuteList.size();j++)
					    		   {
					    			   if(!Main.ExecuteList.get(j).getSignUpList().contains(Owner_name[c])){
					    			   if(selected.get(i).equals(Main.ExecuteList.get(j).getTest().getcode())) {
					    				   Main.ExecuteList.get(j).getSignUpList().add(Owner_name[c]);
					    				   for(int d=0;d< Main.ExecuteList.get(j).getSignUpList().size();d++)
					    				   {
					    					   System.out.println( Main.ExecuteList.get(j).getSignUpList());
					    				   }
					    				   Main.ExecuteList.get(j).setStudentNumber(1);
					    				   Main.ExecuteList.get(j).getTest().setExe_code(Main.ExecuteList.get(j).getExe_code());
					    				   signUp_test_list.get(c).add(Main.ExecuteList.get(j).getTest());
					    				   break;
					    			   }
					    			   }
					    		   }
					    	   }
					    	  primaryStage.close();
					       }
					  }
			});
		    Button B2=new Button("Cancel");
		    gridpane.add(B2 ,0, 2);
		    gridpane.add(B1 ,2, 2);
		    root.setCenter(gridpane);

		    GridPane.setVgrow(root, Priority.ALWAYS);
		    primaryStage.setScene(scene);
		    primaryStage.show();
    	
	}
	public void viewSignUpList(int c)
	{
		primaryStage=new Stage();
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<signUp_test_list.get(c).size();i++)data.add(signUp_test_list.get(c).get(i).getcode());

	    listView.setItems(data);
	    listView.getSelectionModel().selectedItemProperty().addListener(
	        (ObservableValue<? extends String> ov, String old_val, 
	            String new_val) -> {
	                Question_select=new_val;
	                
	    });
	    pane.setContent(listView);
	    Scene scene = new Scene(pane,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void loadTestChoiceWindow(int c)
	{
		
		primaryStage=new Stage();
		BorderPane Test_form=new BorderPane();
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<signUp_test_list.get(c).size();i++)data.add(signUp_test_list.get(c).get(i).getcode());

	    listView.setItems(data);
	    listView.getSelectionModel().selectedItemProperty().addListener(
	        (ObservableValue<? extends String> ov, String old_val, 
	            String new_val) -> {
	                Question_select=new_val;
	                
	    });
	    pane.setContent(listView);
	    Button B1=new Button("Start Test");
	    B1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				 GridPane grid = new GridPane();
			       grid.setHgap(10);
			       grid.setVgap(10);
			       grid.setPadding(new Insets(25, 25, 25, 25));
			       Label Message=new Label("take a written test or online?");
			       grid.add(Message,1, 0);
			       Button B1=new Button("Written");
			       B1.setOnAction(new EventHandler<ActionEvent>()
				    {
					       @Override
					       public void handle(ActionEvent e)
						  {
					    	   GridPane enter_pass=new GridPane();
					    	   TextArea F1=new TextArea();
					    	   F1.setPromptText("enter exam execution code here");
					    	   F1.setPrefColumnCount(30);
					    	   F1.setPrefRowCount(1);
					    	   enter_pass.add(F1, 0, 0);
					    	   for(int i=0;i<signUp_test_list.get(c).size();i++)
						    	  {
						    		  if(signUp_test_list.get(c).get(i).getcode().equals(Question_select))
						    			  {
						    			      idx=i;
						    			  }
						    	  }
					    	   Button B1=new Button("Start");
					    	   B1.setOnAction(new EventHandler<ActionEvent>()
							    {
								       @Override
								       public void handle(ActionEvent e)
									  {
								    	   if(F1.getText().equals(signUp_test_list.get(c).get(idx).getExe_code()))
								    	   {
								    		  File file = new File("C:\\Users\\tomer_000\\Desktop\\test.txt");
								    		   BufferedWriter writer;
											try {
												writer = new BufferedWriter(new FileWriter(file, true));
												writer.append("Test: "+signUp_test_list.get(c).get(idx).getcode());
												writer.newLine();
												writer.append("Written by: "+signUp_test_list.get(c).get(idx).getOwner());
												writer.newLine();
												writer.append("test duration: "+signUp_test_list.get(c).get(idx).getLength());
												writer.newLine();
												writer.newLine();
												for(int i=0;i<signUp_test_list.get(c).get(idx).getQuestionlist().size();i++)
												{
													writer.append("Question "+(i+1)+":");
													writer.newLine();
													writer.append("Question points: "+signUp_test_list.get(c).get(idx).getPointsList().get(i));
													writer.newLine();
													writer.newLine();
													writer.append("question:");
													writer.newLine();
													writer.append(signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getBody());
													writer.newLine();
													writer.newLine();
													writer.append("instructions: "+signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getSInstruction());
													writer.newLine();
													writer.newLine();
													writer.append("Answers:");
													writer.newLine();
													writer.append("_ "+signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getAnswer1());
													writer.newLine();
													writer.append("_ "+signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getAnswer2());
													writer.newLine();
													writer.append("_ "+signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getAnswer3());
													writer.newLine();
													writer.append("_ "+signUp_test_list.get(c).get(idx).getQuestionlist().get(i).getAnswer4());
													writer.newLine();
													writer.newLine();
													writer.newLine();
												}
												writer.append("Good Luck!");
								    		    writer.close();
											} catch (IOException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
								    		 primaryStage.close();   
											
								    		  
								    		    
								    	   }
									  }
							    });
					    	     enter_pass.add(B1, 0, 1);
						         Scene scene = new Scene(enter_pass,400,200);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								primaryStage.setScene(scene);
								primaryStage.show();
						  }
				    });
			       Button B2=new Button("Online");
			       B2.setOnAction(new EventHandler<ActionEvent>()
				    {
					       @Override
					       public void handle(ActionEvent e)
						  {
					    	   GridPane enter_pass=new GridPane();
					    	   TextArea F1=new TextArea();
					    	   F1.setPromptText("enter exam execution code here");
					    	   F1.setPrefColumnCount(30);
					    	   F1.setPrefRowCount(1);
					    	   enter_pass.add(F1, 0, 0);
					    	   for(int i=0;i<signUp_test_list.get(c).size();i++)
						    	  {
						    		  if(signUp_test_list.get(c).get(i).getcode().equals(Question_select))
						    			  {
						    			      idx=i;
						    			  }
						    	  }
					    	   Button B1=new Button("Start");
					    	   B1.setOnAction(new EventHandler<ActionEvent>()
							    {
								       @Override
								       public void handle(ActionEvent e)
									  {
								    	   if(F1.getText().equals(signUp_test_list.get(c).get(idx).getExe_code()))
								    	   {
								    		        Main.ExecuteList.get(idx).setrSign(Main.ExecuteList.get(idx).getrSign()+1);
								    		        BorderPane window=new BorderPane();
											        final Test sav1=new Test(signUp_test_list.get(c).get(idx));
  										    	    ScrollPane pane=new ScrollPane();
											      	GridPane Text_edit=new GridPane();
											      	Text_edit.setHgap(10);
											      	Text_edit.setVgap(10);
											      	Text_edit.setPadding(new Insets(25, 25, 25, 25));
											      	TextArea ID=new TextArea();
											      	TextArea pass=new TextArea();
											      	HBox b=new HBox();
											      	CheckBox select[][]=new CheckBox[sav1.getQuestionlist().size()][4];
											      	Button do1=new Button("start");
											      	do1.setOnAction(new EventHandler<ActionEvent>()
												    {
													       @Override
													       public void handle(ActionEvent e)
														  {
													    	   if(!ID.getText().equals(StudentController.this.get1()))
													    	   {
													    		   if(!pass.getText().equals(StudentController.this.get2()))
													    		   {
													    			   pane.setVisible(false);
													    		   }
													    		   else
													    		   {
													    			   pane.setVisible(false);
													    		   }
													    	   }
													    	   else
													    	   {
													    		   if(!pass.getText().equals(StudentController.this.get2()))
													    		   {
													    			   pane.setVisible(false);
													    		   }
													    		   else
													    		   {
													    			   pane.setVisible(true);
													    			   b.setVisible(false);
													    			     time1[c]=new Timeline(new KeyFrame(
													    					   Duration.minutes(Integer.parseInt(sav1.getLength())),
													    					   ae->{
													    						   int j=0;
																		    	   for(int i=0;i<sav1.getQuestionlist().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0);
																		    		   }
																		    		   j=0;
																		    	   }
																		    	   signUp_test_list.get(c).remove(idx);
																		    	   sav1.setName(Owner_name[c]);
																		    	   sav1.setC(c);
																		    	   Main.ExecuteList.get(idx).setFInishedNum(Main.ExecuteList.get(idx).getFInishedNum()+1);
																		    	   Main.ExecuteList.get(idx).setrSign(-1);
																		    	   Main.ExecuteList.get(idx).setSign(1);
																		    	   int k=0;
																		    	   for(int i=0;i<Main.TController.length;i++)
																		    	   {
																		    		   
																		    		   if(Main.TController[i].Owner_name[i].equals(Main.ExecuteList.get(idx).getexecuter()))
																		    	       {
																		    			   k=i;
																		    			  
																		    	       }
																		    	   }
																		    	   SampleController.solved_Tests.get(k).add(sav1);
													    						   closestage.get(c).close();
													    					   }));
													    			   time1[c].play();
													    			  
													    			   
													    		   }
													    	   }
														  }
												    });
											      	ID.setPromptText("enter ID here");
											      	ID.setPrefColumnCount(20);
											      	ID.setPrefRowCount(1);
											      	pass.setPromptText("enter password here");
											      	pass.setPrefColumnCount(20);
											      	pass.setPrefRowCount(1);
											    	b.getChildren().addAll(ID,pass,do1);
											      	Label L1=new Label("Test: "+sav1.getcode());
											      	Text_edit.add(L1, 0, 0);
											      	L1=new Label("Written by: "+sav1.getOwner());
											      	Text_edit.add(L1, 0, 1);
											      	L1=new Label("Test duration: "+sav1.getLength()+" minuts");
											      	Text_edit.add(L1, 0, 2);
											      	int j=3;
											      	int k ,l;
											      	
											      	for(int i=0;i<sav1.getQuestionlist().size();i++)
											      	{
											      		select[i][0]=new CheckBox();
											      		select[i][1]=new CheckBox();
											      		select[i][2]=new CheckBox();
											      		select[i][3]=new CheckBox();
											      	}
											      	
											      	for(int i=0;i<sav1.getQuestionlist().size();i++)
											      	{
											      		final int val=i;
											      		select[i][0].setOnAction(new EventHandler<ActionEvent>()
											    		{
											    			 @Override
											    			  public void handle(ActionEvent e)
											    				  {
											    				     if(select[val][0].selectedProperty().get()==true)
											    				     {
											    				    	 if(select[val][1].selectedProperty().get()==true)select[val][1].selectedProperty().set(false);
											    				    	 if(select[val][2].selectedProperty().get()==true)select[val][2].selectedProperty().set(false);
											    				    	 if(select[val][3].selectedProperty().get()==true)select[val][3].selectedProperty().set(false);
											    				     }
											    				  }
											    		});
											      		select[i][1].setOnAction(new EventHandler<ActionEvent>()
											    		{
											    			 @Override
											    			  public void handle(ActionEvent e)
											    				  {
											    				     if(select[val][1].selectedProperty().get()==true)
											    				     {
											    				    	 if(select[val][0].selectedProperty().get()==true)select[val][0].selectedProperty().set(false);
											    				    	 if(select[val][2].selectedProperty().get()==true)select[val][2].selectedProperty().set(false);
											    				    	 if(select[val][3].selectedProperty().get()==true)select[val][3].selectedProperty().set(false);
											    				     }
											    				  }
											    		});
											      		select[i][2].setOnAction(new EventHandler<ActionEvent>()
											    		{
											    			 @Override
											    			  public void handle(ActionEvent e)
											    				  {
											    				     if(select[val][2].selectedProperty().get()==true)
											    				     {
											    				    	 if(select[val][1].selectedProperty().get()==true)select[val][1].selectedProperty().set(false);
											    				    	 if(select[val][0].selectedProperty().get()==true)select[val][0].selectedProperty().set(false);
											    				    	 if(select[val][3].selectedProperty().get()==true)select[val][3].selectedProperty().set(false);
											    				     }
											    				  }
											    		});
											      		select[i][3].setOnAction(new EventHandler<ActionEvent>()
											    		{
											    			 @Override
											    			  public void handle(ActionEvent e)
											    				  {
											    				     if(select[val][3].selectedProperty().get()==true)
											    				     {
											    				    	 if(select[val][1].selectedProperty().get()==true)select[val][1].selectedProperty().set(false);
											    				    	 if(select[val][2].selectedProperty().get()==true)select[val][2].selectedProperty().set(false);
											    				    	 if(select[val][0].selectedProperty().get()==true)select[val][0].selectedProperty().set(false);
											    				     }
											    				  }
											    		});
											      	}
											      	for(int i=0;i<sav1.getQuestionlist().size();i++)
											      	{
											      		L1=new Label("Question"+(i+1)+":");
											      		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
											          	Text_edit.add(L1, 0, j);
											          	j++;
											          	L1=new Label("Question points:"+sav1.getPointsList().get(i));
											          	Text_edit.add(L1, 0, j);
											          	j=j+2;
											          	L1=new Label("Instructions: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	L1=new Label(sav1.getQuestionlist().get(i).getSInstruction());
											          	Text_edit.add(L1, 1, j);
											          	j++;
											          	L1=new Label("Question: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	j++;
											          	L1=new Label(sav1.getQuestionlist().get(i).getBody());
											          	Text_edit.add(L1, 0, j);
											          	j=j+2;
											          	L1=new Label("Answers: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	j++;
														 
											          	L1=new Label(sav1.getQuestionlist().get(i).getAnswer1());
											          	HBox box1=new HBox();
											          	box1.getChildren().addAll(select[i][0],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getQuestionlist().get(i).getAnswer2());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][1],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getQuestionlist().get(i).getAnswer3());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][2],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getQuestionlist().get(i).getAnswer4());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][3],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											         
											      	}
											        sav1.setAnswers();
											     	Button Submit=new Button("Submit");
										          	Submit.setOnAction(new EventHandler<ActionEvent>()
										     		{
										     			 @Override
										     			  public void handle(ActionEvent e)
										     				  {
										     				      int check=0;
										     				      for(int i=0;i<sav1.getQuestionlist().size();i++)
										     				      {
										     				    	  if(select[i][0].selectedProperty().get()==true) check++;
										     				    	  if(select[i][1].selectedProperty().get()==true) check++;
										     				    	  if(select[i][2].selectedProperty().get()==true) check++;
										     				    	  if(select[i][3].selectedProperty().get()==true) check++;
										     				      }
										     				      if(check<sav1.getQuestionlist().size())
										     				      {
										     				    	  Stage SecondStage=new Stage();
															    	   GridPane grid = new GridPane();
																       grid.setHgap(10);
																       grid.setVgap(10);
																       grid.setPadding(new Insets(25, 25, 25, 25));
																       Label Message=new Label("you didnt anwer all the questions , do you wish to submit your test?");
																       grid.add(Message,1, 0);
																       Button B1=new Button("Yes");
																       B1.setOnAction(new EventHandler<ActionEvent>()
																	    {
																		       @Override
																		       public void handle(ActionEvent e)
																			  {
																		    	   SecondStage.close();
																		    	   closestage.get(c).close();
																		    	   int j=0;
																		    	   for(int i=0;i<sav1.getQuestionlist().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0);
																		    		   }
																		    		   j=0;
																		    	   }
																		    	   
																		    	   sav1.setName(Owner_name[c]);
																		    	   sav1.setC(c);
																		    	   Main.ExecuteList.get(idx).setFInishedNum(Main.ExecuteList.get(idx).getFInishedNum()+1);
																		    	   Main.ExecuteList.get(idx).setrSign(Main.ExecuteList.get(idx).getrSign()-1);
																		    	   if(Main.ExecuteList.get(idx).getrSign()==0)
																		    	   {
																		    		   Main.ExecuteList.get(idx).setSign(1);
																		    		   Main.ExecuteList.get(idx).setrSign(-1);
																		    		   for(int i=0;i<Main.ExecuteList.get(idx).getSignUpList().size();i++)
																		    		   {
																		    			   for(int p=0;p<signUp_test_list.size();p++)
																		    			   {
																		    				   for(int u=0;u<signUp_test_list.get(p).size();u++)
																		    				   {
																		    					   if(signUp_test_list.get(p).get(u).getcode().
																		    							   equals(Main.ExecuteList.get(idx).getTest().getcode()))
																		    					   {
																		    						   signUp_test_list.get(p).remove(u);
																		    						   break;
																		    					   }
																		    				   }
																		    			   }
																		    		   }
																		    	   }
																		    	   int k=0;
																		    	   for(int i=0;i<Main.TController.length;i++)
																		    	   {
																		    		   if(Main.TController[i].Owner_name[i].equals(Main.ExecuteList.get(idx).getexecuter()))
																		    	       {
																		    			   k=i;
																		    			  
																		    	       }
																		    	   }
																		    	   int sign1=0;
																		    	   int q=Main.ExecuteList.get(idx).gradelog.size();
																		    	   Main.ExecuteList.get(idx).gradelog.put(Main.ExecuteList.get(idx).gradelog.size(), sav1.getAnswers());
																		    	   System.out.println(Main.ExecuteList.get(idx).gradelog);
																		    	   for(int f=0;f<Main.ExecuteList.get(idx).gradelog.size();f++)
																		    	   {
																		    		   
																		    		   for(int s=0;s<Main.ExecuteList.get(idx).getTest().getQuestionlist().size();s++)
																		    		   {
																		    			   if(sav1.getAnswers().get(s)==Main.ExecuteList.get(idx).gradelog.get(f).get(s))
																		    			   {
																		    				   sign1++;
																		    			   }
																		    		   }																		    		   
																		    	   }
																		    	   if(sign1>0)sav1.setcop(1);
																		    	   SampleController.solved_Tests.get(k).add(sav1);
																		    	   System.out.println(SampleController.solved_Tests.get(k));
																		    	  
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
										     				      else
										     				      {
										     				    	 Stage SecondStage=new Stage();
															    	   GridPane grid = new GridPane();
																       grid.setHgap(10);
																       grid.setVgap(10);
																       grid.setPadding(new Insets(25, 25, 25, 25));
																       Label Message=new Label("are you sure you want to submit your test?");
																       grid.add(Message,1, 0);
																       Button B1=new Button("Yes");
																       B1.setOnAction(new EventHandler<ActionEvent>()
																	    {
																		       @Override
																		       public void handle(ActionEvent e)
																			  {
																		    	   SecondStage.close();
																		    	   closestage.get(c).close();
																		    	   int j=0;
																		    	   for(int i=0;i<sav1.getQuestionlist().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getQuestionlist().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(Integer.parseInt(sav1.getPointsList().get(i)));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0);
																		    		   }
																		    		   j=0;
																		    	   }
																		    	   sav1.setName(Owner_name[c]);
																		    	   sav1.setC(c);
																		 
																		    	   Main.ExecuteList.get(idx).setFInishedNum(Main.ExecuteList.get(idx).getFInishedNum()+1);
																		    	   Main.ExecuteList.get(idx).setrSign(Main.ExecuteList.get(idx).getrSign()-1);
																		    	   if(Main.ExecuteList.get(idx).getrSign()==0)
																		    	   {
																		    		   Main.ExecuteList.get(idx).setSign(1);
																		    		   Main.ExecuteList.get(idx).setrSign(-1);
																		    		   for(int i=0;i<Main.ExecuteList.get(idx).getSignUpList().size();i++)
																		    		   {
																		    			   for(int p=0;p<signUp_test_list.size();p++)
																		    			   {
																		    				   for(int u=0;u<signUp_test_list.get(p).size();u++)
																		    				   {
																		    					   if(signUp_test_list.get(p).get(u).getcode().
																		    							   equals(Main.ExecuteList.get(idx).getTest().getcode()))
																		    					   {
																		    						   signUp_test_list.get(p).remove(u);
																		    						   break;
																		    					   }
																		    				   }
																		    			   }
																		    		   }
																		    	   }
																		    	   int k=0;
																		    	   for(int i=0;i<Main.TController.length;i++)
																		    	   {
																		    		   if(Main.TController[i].Owner_name[i].equals(Main.ExecuteList.get(idx).getexecuter()))
																		    	       {
																		    			   k=i;
																		    			  
																		    	       }
																		    	   }
																		    	   int sign1=0;
																		    	   int q=Main.ExecuteList.get(idx).gradelog.size();
																		    	   Main.ExecuteList.get(idx).gradelog.put(Main.ExecuteList.get(idx).gradelog.size(), sav1.getAnswers());
																		    	   System.out.println(Main.ExecuteList.get(idx).gradelog);
																		    	   for(int f=0;f<Main.ExecuteList.get(idx).gradelog.size();f++)
																		    	   {
					
																		    		   for(int s=0;s<Main.ExecuteList.get(idx).getTest().getQuestionlist().size();s++)
																		    		   {
																		    			   if(sav1.getAnswers().get(s)==Main.ExecuteList.get(idx).gradelog.get(f).get(s))
																		    			   {
																		    				   sign1++;
																		    			   }
																		    		   
																		    		   }
																		    	   }
																		    	   if(sign1>0)sav1.setcop(1);
																		    	   SampleController.solved_Tests.get(k).add(sav1);
																		    	   System.out.println(SampleController.solved_Tests.get(k));
																		    	   
																		    	   
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
										     				  }
										     		});
										          	Text_edit.add(Submit, 0, j);
											      	pane.setContent(Text_edit);
											      	pane.setVisible(false);
											      	window.setTop(b);
											      	window.setCenter(pane);
											      	Scene scene = new Scene(window,600,449);
											  		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
											  		closestage.get(c).setScene(scene);
											  		closestage.get(c).show();
											  		
											  		
											    	  
											      }
								    	   else
								    	   {
								    		   F1.setBlendMode(BlendMode.RED);
								    	   }
								    	   
									  }
							    });
						       enter_pass.add(B1, 0, 1);
						       Scene scene = new Scene(enter_pass,400,200);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								primaryStage.setScene(scene);
								primaryStage.show();
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
	    Button B2=new Button("back");
	    B2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				     primaryStage.close();
				  }
		});
	    Test_form.setRight(B1);
	    Test_form.setBottom(B2);
	    Test_form.setLeft(pane);
	    Scene scene = new Scene(Test_form,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
	public void resultWindow(String str4,int c) {
		
		Stage Second=new Stage();
		BorderPane BP=new BorderPane();
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<checkedTests.get(c).size();i++)data.add(checkedTests.get(c).get(i).getcode());

	    listView.setItems(data);
	    listView.getSelectionModel().selectedItemProperty().addListener(
	        (ObservableValue<? extends String> ov, String old_val, 
	            String new_val) -> {
	                Question_select=new_val;
	                
	    });
	    Button view1=new Button("view grade");
	    view1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				    if(Question_select!="0") {
				     Stage second=new Stage();
				     GridPane pane=new GridPane();
				     int idx=0;
				     for(int i=0;i<checkedTests.get(c).size();i++)
				     {
				    	 if(checkedTests.get(c).get(i).getcode().equals(Question_select))idx=i;
				     }
				     Label L1=new Label("Grade :"+checkedTests.get(c).get(idx).getGrade());
				     pane.add(L1, 1, 0);
				     Scene scene = new Scene(pane,300,200);
				     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					 Second.setScene(scene);
					 Second.show();
				    }
				  }
		});
	    Button view2=new Button("view Test");
	    view2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				 if(Question_select!="0") {
				     int idx=0,idx2=0;
			         for(int i=0;i<checkedTests.get(c).size();i++)
			         {
			    	 if(checkedTests.get(c).get(i).getcode().equals(Question_select))idx=i;
			         }
			         for(int i=0;i<Main.ExecuteList.size();i++)
			         {
			        	 if(Main.ExecuteList.get(i).getTest().getcode().equals(Question_select))idx2=i;
			         }
			         Test Chosen=new Test();
			         Chosen=checkedTests.get(c).get(idx);
				    ScrollPane pane=new ScrollPane();
			     	GridPane Text_edit=new GridPane();
			     	Text_edit.setHgap(10);
			     	Text_edit.setVgap(10);
			     	Text_edit.setPadding(new Insets(25, 25, 25, 25));
			     	Label L1=new Label("Test: "+Chosen.getcode());
			     	Text_edit.add(L1, 0, 0);
			     	L1=new Label("Taken by: "+Chosen.getName());
			     	Text_edit.add(L1, 0, 1);
			     	L1=new Label("On date: "+Main.ExecuteList.get(idx2).getDate());
			     	Text_edit.add(L1, 0, 2);
			     	L1=new Label("Test duration: "+Chosen.getLength()+" minuts");
			     	Text_edit.add(L1, 0, 3);
			     	L1=new Label("Test grade: "+Chosen.getGrade());
			     	Text_edit.add(L1, 0, 4);
			     	if(!Chosen.getReason().isEmpty())
			     	{
			     		L1=new Label("Grade change reason: "+Chosen.getReason());
				     	Text_edit.add(L1, 0, 5);
			     	}
			     	int j=6;
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
			     		}
			     		if(!Chosen.getremarks().get(i).isEmpty())
			     		{
			     			L1=new Label("Teacher remarks:"+Chosen.getremarks().get(i));
			     			Text_edit.add(L1, 0, j);
			     			j++;
			     		}
			     	}
			     	pane.setContent(Text_edit);
			     	Scene scene = new Scene(pane,600,449);
			 		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 		primaryStage.setScene(scene);
			 		primaryStage.show();
				 }
				  }
		});
	    VBox Views=new VBox();
	    Views.getChildren().addAll(view1,view2);
	    pane.setContent(listView);
	    BP.setLeft(pane);
	    BP.setRight(Views);
	    Scene scene = new Scene(BP,600,449);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Second.setScene(scene);
		Second.show();
	}
    public void set(String str1 , String str2)
    {
    	this.ID1=str1;
    	this.pass1=str2;
    }
    public String get1()
    {
    	return this.ID1;
    }
    public String get2()
    {
    	return this.pass1;
    }
    public void end(Stage Cancel) {
		   Cancel.close();
	}
	
	   
}