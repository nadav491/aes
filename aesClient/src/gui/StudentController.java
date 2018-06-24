package gui;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import test.ExecutedTest;
import test.MyFile;
import test.Test;
import test.studentTest;


public class StudentController {
	public final String Owner_name[]=new String[2];
	private Stage primaryStage;
	
	public static final ArrayList<Stage> closestage=new  ArrayList<Stage>();
	public static ArrayList<Test> signUp_test_list=new ArrayList<Test>();//signup test list
	public ArrayList<studentTest> checked_list=new ArrayList<studentTest>();//checked test list
	private ArrayList<Test> test_list;//test list
	private String Question_select="0";
	private Test save=new Test();
	private int start=0;
	private int idx=0;
	private String ID1;
	private String pass1;
	private static int val=0;
	static int timeC=0;
	int dur;
	public static final Timeline time1[]=new Timeline[2];

	public StudentController()
	{
		//init
		closestage.add(new Stage());
		val++;
		primaryStage=new Stage();
		test_list=new ArrayList<Test>();
	}
	public void signUp(String Owner ,int c,Client client)//signup to running tests
	{
		
		Owner_name[c]=Owner;
		signUp_test_list=new ArrayList<Test>();
		for(int i=0;i<client.GetAllExecutreTest().size();i++)//getting running tests without being already signed up
		{
			if(client.GetAllExecutreTest().get(i).getSign()==0)
			{
				if(client.GetAllExecutreTest().get(i).getSignUpList().contains(Owner_name[c]))
				{
					signUp_test_list.add(client.GetAllExecutreTest().get(i).getTest());
				}
			}
		}
		//initializing choice window
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
		    for(int i=0;i<client.GetAllExecutreTest().size();i++)
		    {
		    	for(int j=0;j<signUp_test_list.size();j++)
		    	{
		    		if(client.GetAllExecutreTest().get(i).getTest().getCode().equals(this.signUp_test_list.get(j).getCode()) )
		    			{
		    			    sign++;
		    			}
		    		
		    	}
		    	if(sign==0) {
		    		if(client.GetAllExecutreTest().get(i).getSign()==0)rem.add(client.GetAllExecutreTest().get(i).getTest());
		    	}
		    	sign=0;
		    }
		    for(int i=0;i<rem.size();i++)
		    {
		    	 candidates.add(rem.get(i).getCode());
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
		    Button B1=new Button("SignUp");//signing up
		    B1.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {    
					       if(selected.size()==0)//not added to list choice
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
					    	   for(int i=0;i<selected.size();i++)//updating tests
					    	   {
					    		   for(int j=0;j<client.GetAllExecutreTest().size();j++)
					    		   {
					    			   if(!client.GetAllExecutreTest().get(j).getSignUpList().contains(Owner_name[c])){
					    			   if(selected.get(i).equals(client.GetAllExecutreTest().get(j).getTest().getCode())) {
					    				   client.GetAllExecutreTest().get(j).getSignUpList().add(Owner_name[c]);
					    				   client.GetAllExecutreTest().get(j).setStudentNumber(1);
					    				   client.GetAllExecutreTest().get(j).getTest().setCode(client.GetAllExecutreTest().get(j).getTest().getCode());
					    				   client.GetAllExecutreTest().get(j).setExe_code(client.GetAllExecutreTest().get(j).getExe_code());
					    				   client.GetAllExecutreTest().get(j).getF().add("0");					    				  
					    				   signUp_test_list.add(client.GetAllExecutreTest().get(j).getTest());
					    				   client.UpdateExecutreTest(client.GetAllExecutreTest().get(j));
					    				   System.out.println(client.GetAllExecutreTest().get(j).getSignUpList());
					    				   break;
					    			   }
					    			   }
					    		   }
					    	   }
					    	  primaryStage.close();
					       }
					  }
			});
		    gridpane.add(B1 ,2, 2);
		    root.setCenter(gridpane);

		    GridPane.setVgrow(root, Priority.ALWAYS);
		    primaryStage.setScene(scene);
		    primaryStage.show();
    	
	}
	public void viewSignUpList(int c , Client client)//view signup list
	{
		primaryStage=new Stage();
		
		signUp_test_list=new ArrayList<Test>();
		for(int i=0;i<client.GetAllExecutreTest().size();i++)//getting tests
		{
			if(client.GetAllExecutreTest().get(i).getSign()==0)
			{
				if(client.GetAllExecutreTest().get(i).getSignUpList().contains(Owner_name[c]))
				{
					signUp_test_list.add(client.GetAllExecutreTest().get(i).getTest());
				}
			}
		}
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<signUp_test_list.size();i++)data.add(signUp_test_list.get(i).getCode());

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
	public void loadTestChoiceWindow(int c, Client client)//solving tests
	{
		ArrayList<String> exeCodes=new ArrayList<String>();
		primaryStage=new Stage();
		signUp_test_list=new ArrayList<Test>();
		for(int i=0;i<client.GetAllExecutreTest().size();i++)//finding running tests and exe codes
		{
			if(client.GetAllExecutreTest().get(i).getSign()==0)
			{
				if(client.GetAllExecutreTest().get(i).getSignUpList().contains(Owner_name[c]))
				{
					
					for(int p=0;p<client.GetAllExecutreTest().get(i).getSignUpList().size();p++) {
				    if(client.GetAllExecutreTest().get(i).getSignUpList().get(p).equals(Owner_name[c])) {
				    	if(client.GetAllExecutreTest().get(i).getF().get(p).equals("0")) {
					      signUp_test_list.add(client.GetAllExecutreTest().get(i).getTest());
					      exeCodes.add(client.GetAllExecutreTest().get(i).getExe_code());
				    	}
				    }
					}
				}
			}
		}
		//initializing choice window
		BorderPane Test_form=new BorderPane();
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<signUp_test_list.size();i++)data.add(signUp_test_list.get(i).getCode());

	    listView.setItems(data);
	    listView.getSelectionModel().selectedItemProperty().addListener(
	        (ObservableValue<? extends String> ov, String old_val, 
	            String new_val) -> {
	                Question_select=new_val;
	                
	    });
	    pane.setContent(listView);
	    Button B1=new Button("Start Test");//starting test
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
			       Button B1=new Button("Written");//written test choice
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
					    	   for(int i=0;i<signUp_test_list.size();i++)//finding test
						    	  {
						    		  if(signUp_test_list.get(i).getCode().equals(Question_select))
						    			  {
						    			      idx=i;
						    			  }
						    	  }
					    	   
					    	   Button B1=new Button("Start");//starting test
					    	   B1.setOnAction(new EventHandler<ActionEvent>()
							    {
								       @Override
								       public void handle(ActionEvent e)
									  {
								    	   //check for invalid input
								    	   if(F1.getText().equals(exeCodes.get(idx)))
								    	   {
								    		 GridPane p=new GridPane();
								    		 Stage j=new Stage();
								    		 Button B1=new Button("DownLoad");//download test file
									    	   B1.setOnAction(new EventHandler<ActionEvent>()
											    {
												       @Override
												       public void handle(ActionEvent e)
													  {
												    	   client.downloadFile(client.GetAllExecutreTest().get(idx));
													  }
											    });
									    	   Button B2=new Button("UpLoad");//uploading test file
									    	   B2.setOnAction(new EventHandler<ActionEvent>()
											    {
												       @Override
												       public void handle(ActionEvent e)
													  {
												    	   client.uploadFile("saveTestFiles\\"+client.GetAllExecutreTest().get(idx).getTest().getCode()+".txt");
													  }
											    });
									    	   p.add(B1, 0, 0);
									    	   p.add(B2, 1, 0);
									    	   Scene scene = new Scene(p,400,200);
												scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
												j.setScene(scene);
												j.show();
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
			       Button B2=new Button("Online");//choose online test
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
					    	   for(int i=0;i<signUp_test_list.size();i++)
						    	  {
						    		  if(signUp_test_list.get(i).getCode().equals(Question_select))
						    			  {
						    			      idx=i;
						    			  }
						    	  }
					    	   Button B1=new Button("Start");//start test
					    	   B1.setOnAction(new EventHandler<ActionEvent>()
							    {
								       @Override
								       public void handle(ActionEvent e)
									  {
								    	   //check for invalid input
								    	   if(F1.getText().equals(exeCodes.get(idx)))
								    	   {//finding test
								    		        for(int q=0;q<client.GetAllExecutreTest().size();q++)
								    		        {
								    		        	if(client.GetAllExecutreTest().get(q).getTest().equals(signUp_test_list.get(idx)))
								    		        	{
								    		        		idx=q;
								    		        		break;
								    		        	}
								    		        }
								    		        //initializing test window
								    		        client.GetAllExecutreTest().get(idx).setrSign(client.GetAllExecutreTest().get(idx).getrSign()+1);
								    		        BorderPane window=new BorderPane();
											        final studentTest sav1=new studentTest(Owner_name[c],client.GetAllExecutreTest().get(idx).getTest(),0,new ArrayList<String>(),client.GetAllExecutreTest().get(idx).getTest().getTime(),
											        		client.GetAllExecutreTest().get(idx).getTest().getOwner());
											        
  										    	    ScrollPane pane=new ScrollPane();
											      	GridPane Text_edit=new GridPane();
											      	Text_edit.setHgap(10);
											      	Text_edit.setVgap(10);
											      	Text_edit.setPadding(new Insets(25, 25, 25, 25));
											      	TextArea ID=new TextArea();
											      	TextArea pass=new TextArea();
											      	HBox b=new HBox();
											      	CheckBox select[][]=new CheckBox[sav1.getTest().getQuestions().size()][4];
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
													    			   //starting test timer
													    			   pane.setVisible(true);
													    			   b.setVisible(false);
													    			   time1[c].setCycleCount(Timeline.INDEFINITE);
													    			   time1[c].getKeyFrames().add(
													   		                new KeyFrame(Duration.seconds(1),
													   		                  new EventHandler() {
													   							@Override
													   							public void handle(Event event) {
													   								if(client.executedTestsCheckLock(client.GetAllExecutreTest().get(idx))==2)//if test was locked stop this test
													   								{
													   									time1[c].stop();
													   								    closestage.get(c).close();
													   								}
													   								if(client.executedTestsCheckLock(client.GetAllExecutreTest().get(idx))==4)// if time was changed change time
													   								{
													   									dur=Integer.parseInt(client.GetAllExecutreTest().get(idx).getCurrentTime());
													   									client.GetAllExecutreTest().get(idx).setSign(1);
												    								    client.UpdateExecutreTest(client.GetAllExecutreTest().get(idx));
												    			
												    								      
													   								}
													   								timeC++;
													   								
													   								if(timeC==dur*60) {//after time ended save the test as it is
													   							   int j=0;
													   							  //check for answers
																		    	   for(int i=0;i<sav1.getTest().getQuestions().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0+"");
																		    		   }
																		    		   j=0;
																		    	   }
																		    	   //update database and server
																		    	   signUp_test_list.remove(idx);
																		    	   client.GetAllExecutreTest().get(idx).setStudentNumber(client.GetAllExecutreTest().get(idx).getSignUpList().size());
																		    	   client.GetAllExecutreTest().get(idx).setFFInishedNum(client.GetAllExecutreTest().get(idx).getFFInishedNum()+1);
																		    	   client.GetAllExecutreTest().get(idx).setrSign(-1);
																		    	   client.GetAllExecutreTest().get(idx).setSign(2);
																		    	   client.GetAllExecutreTest().get(idx).getF().add("1");
																		    	   client.UpdateExecutreTest(client.GetAllExecutreTest().get(idx));
													    						   closestage.get(c).close();
													   							   time1[c].stop();
													   							}
													   								else
													   								{
													   									
													   									time1[c].play();
													   								}
													   							}
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
											      	Label L1=new Label("Test: "+sav1.getTest().getCode());
											      	Text_edit.add(L1, 0, 0);
											      	L1=new Label("Written by: "+sav1.getTest().getOwner());
											      	Text_edit.add(L1, 0, 1);
											      	L1=new Label("Test duration: "+sav1.getTest().getTime()+" minuts");
											      	Text_edit.add(L1, 0, 2);
											      	int j=3;
											      	int k ,l;
											      	//initializing test choices
											      	for(int i=0;i<sav1.getTest().getQuestions().size();i++)
											      	{
											      		select[i][0]=new CheckBox();
											      		select[i][1]=new CheckBox();
											      		select[i][2]=new CheckBox();
											      		select[i][3]=new CheckBox();
											      	}
											      	
											      	for(int i=0;i<sav1.getTest().getQuestions().size();i++)
											      	{
											      		final int val=i;
											      		//create handlers
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
											      	for(int i=0;i<sav1.getTest().getQuestions().size();i++)
											      	{
											      		L1=new Label("Question"+(i+1)+":");
											      		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
											          	Text_edit.add(L1, 0, j);
											          	j++;
											          	L1=new Label("Question points:"+sav1.getTest().getQuestionGrade().get(i));
											          	Text_edit.add(L1, 0, j);
											          	j=j+2;
											          	L1=new Label("Instructions: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getSInstruction());
											          	Text_edit.add(L1, 1, j);
											          	j++;
											          	L1=new Label("Question: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	j++;
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getBody());
											          	Text_edit.add(L1, 0, j);
											          	j=j+2;
											          	L1=new Label("Answers: ");
											          	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
											          	Text_edit.add(L1, 0, j);
											          	j++;
														 
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getAnswer1());
											          	HBox box1=new HBox();
											          	box1.getChildren().addAll(select[i][0],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getAnswer2());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][1],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getAnswer3());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][2],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											          	
											          	L1=new Label(sav1.getTest().getQuestions().get(i).getAnswer4());
											          	box1=new HBox();
											          	box1.getChildren().addAll(select[i][3],L1);
											          	Text_edit.add(box1, 0, j);
											          	j++;
											         
											      	}
											        sav1.setAnswers(new ArrayList<String>());
											     	Button Submit=new Button("Submit");//saving test
										          	Submit.setOnAction(new EventHandler<ActionEvent>()
										     		{
										     			 @Override
										     			  public void handle(ActionEvent e)
										     				  {
										     				      int check=0;
										     				      for(int i=0;i<sav1.getTest().getQuestions().size();i++)
										     				      {
										     				    	  if(select[i][0].selectedProperty().get()==true) check++;
										     				    	  if(select[i][1].selectedProperty().get()==true) check++;
										     				    	  if(select[i][2].selectedProperty().get()==true) check++;
										     				    	  if(select[i][3].selectedProperty().get()==true) check++;
										     				      }
										     				      if(check<sav1.getTest().getQuestions().size())
										     				      {
										     				    	  //load choice window
										     				    	  Stage SecondStage=new Stage();
															    	   GridPane grid = new GridPane();
																       grid.setHgap(10);
																       grid.setVgap(10);
																       grid.setPadding(new Insets(25, 25, 25, 25));
																       Label Message=new Label("you didnt anwer all the questions , do you wish to submit your test?");
																       grid.add(Message,1, 0);
																       Button B1=new Button("Yes");
																       B1.setOnAction(new EventHandler<ActionEvent>()//saving test data
																	    {
																		       @Override
																		       public void handle(ActionEvent e)
																			  {
																		    	   SecondStage.close();
																		    	   closestage.get(c).close();
																		    	   int j=0;
																		    	   for(int i=0;i<sav1.getTest().getQuestions().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0+"");
																		    		   }
																		    		   j=0;
																		    	   }
                                                                                   int sum=0;
																		    	   
																		    	   for (int y=0;y<sav1.getAnswers().size();y++)
																		    	   {
																		    		   sum+=Integer.parseInt(sav1.getAnswers().get(y));
																		    	   }
																		    	   //updating database
																		    	   client.GetAllExecutreTest().get(idx).setFInishedNum(client.GetAllExecutreTest().get(idx).getFInishedNum()+1);
																		    	   client.GetAllExecutreTest().get(idx).setrSign(client.GetAllExecutreTest().get(idx).getrSign()-1);
																		    	   client.GetAllExecutreTest().get(idx).setStudentNumber(client.GetAllExecutreTest().get(idx).getSignUpList().size());
																		    	   
																		    	   if(client.GetAllExecutreTest().get(idx).getrSign()==0)
																		    	   {
																		    		   client.GetAllExecutreTest().get(idx).setSign(1);
																		    		   client.GetAllExecutreTest().get(idx).setrSign(-1);
																		    		   client.GetAllExecutreTest().get(idx).setFFInishedNum(client.GetAllExecutreTest().get(idx).getStudentNumber()-client.GetAllExecutreTest().get(idx).getFInishedNum()+1);
																		    		  
																		    	   }
																		    	   
																		    	   int sign1=0;
																		    	   int q=client.GetAllExecutreTest().get(idx).gradelog.size();
																		    	   client.GetAllExecutreTest().get(idx).gradelog.put(client.GetAllExecutreTest().get(idx).gradelog.size(), sav1.getAnswers());
																		    	   System.out.println(client.GetAllExecutreTest().get(idx).gradelog);
																		    	   for(int f=0;f<client.GetAllExecutreTest().get(idx).getGradeList().size();f++)
																		    	   {
																		    		   		if(client.GetAllExecutreTest().get(idx).getGradeList().get(f).equals(sum+""))	
																		    		   		{
																		    		   			sign1++;
																		    		   		}
																		    	   }
																		    	   client.GetAllExecutreTest().get(idx).getGradeList().add(sum+"");
																		    	   for(int i=0;i< client.GetAllExecutreTest().get(idx).getSignUpList().size();i++)
																		    	   {
																		    		   if(client.GetAllExecutreTest().get(idx).getSignUpList().get(i).equals(Owner_name[c]))
																		    		   {
																		    			   client.GetAllExecutreTest().get(idx).getF().set(i, "1");
																		    		   }
																		    	   }
																		    	   
																		    	  
																		    	   System.out.println(client.GetAllExecutreTest().get(idx).getTest().getCode());
																		    	   studentTest st1 = new studentTest(Owner_name[c], client.GetAllExecutreTest().get(idx).getTest(), sum, sav1.getAnswers(), sav1.getTime(), sav1.getTeacher());
																		    	   if(sign1>1) st1.setCheat(true);
																		    	   client.submitStudentTest(st1);

																		    	   client.UpdateExecutreTest(client.GetAllExecutreTest().get(idx));
																		    	   time1[c].stop();
																		    	  
																		    	  
																			  }
																	    });
																       Button B2=new Button("No");//continue solving test
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
																		    	   //submiting test
																		    	   SecondStage.close();
																		    	   closestage.get(c).close();
																		    	   int j=0;
																		    	   for(int i=0;i<sav1.getTest().getQuestions().size();i++)
																		    	   {
																		    		   if(select[i][0].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==1) {
																		    				  sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				  j++;
																		    			   }
																		    		   }
																		    		    if(select[i][1].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==2) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		    if(select[i][2].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==3) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    		   }
																		    		   if(select[i][3].selectedProperty().get()==true)
																		    		   {
																		    			   if(sav1.getTest().getQuestions().get(i).getCorrect()==4) {
																		    				   sav1.getAnswers().add(sav1.getTest().getQuestionGrade().get(i));
																		    				   j++;
																		    			   }
																		    				   
																		    		   }
																		    		   if(j==0)
																		    		   {
																		    			   sav1.getAnswers().add(0+"");
																		    		   }
																		    		   j=0;
																		    	   }
																		    	   int sum=0;
																		    	   
																		    	   for (int y=0;y<sav1.getAnswers().size();y++)
																		    	   {
																		    		   sum+=Integer.parseInt(sav1.getAnswers().get(y));
																		    	   }
																		    	   //update database
																		    	   client.GetAllExecutreTest().get(idx).setFInishedNum(client.GetAllExecutreTest().get(idx).getFInishedNum()+1);
																		    	   client.GetAllExecutreTest().get(idx).setrSign(client.GetAllExecutreTest().get(idx).getrSign()-1);
																		    	   client.GetAllExecutreTest().get(idx).setStudentNumber(client.GetAllExecutreTest().get(idx).getSignUpList().size());
																		    	   
																		    	   if(client.GetAllExecutreTest().get(idx).getrSign()==0)
																		    	   {
																		    		   client.GetAllExecutreTest().get(idx).setSign(1);
																		    		   client.GetAllExecutreTest().get(idx).setrSign(-1);
																		    		   client.GetAllExecutreTest().get(idx).setFFInishedNum(client.GetAllExecutreTest().get(idx).getStudentNumber()-client.GetAllExecutreTest().get(idx).getFInishedNum()+1);
																		    		  
																		    	   }
																		    	   int sign1=0;
																		    	   int q=client.GetAllExecutreTest().get(idx).gradelog.size();
																		    	   for(int i=0;i< client.GetAllExecutreTest().get(idx).getSignUpList().size();i++)
																		    	   {
																		    		   if(client.GetAllExecutreTest().get(idx).getSignUpList().get(i).equals(Owner_name[c]))
																		    		   {
																		    			   client.GetAllExecutreTest().get(idx).getF().set(i, "1");
																		    		   }
																		    	   }
																		    	   client.GetAllExecutreTest().get(idx).gradelog.put(client.GetAllExecutreTest().get(idx).gradelog.size(), sav1.getAnswers());
																		    	   for(int f=0;f<client.GetAllExecutreTest().get(idx).getGradeList().size();f++)
																		    	   {
																		    		   		if(client.GetAllExecutreTest().get(idx).getGradeList().get(f).equals(sum+""))	
																		    		   		{
																		    		   			sign1++;
																		    		   		}
																		    	   }
																		    	   client.GetAllExecutreTest().get(idx).getGradeList().add(sum+"");
																		    	   System.out.println(client.GetAllExecutreTest().get(idx).getTest().getCode());
																		    	  
																		    	   studentTest st1 = new studentTest(Owner_name[c], client.GetAllExecutreTest().get(idx).getTest(), sum, sav1.getAnswers(), sav1.getTime(), sav1.getTeacher());
																		    	   if(sign1>1) st1.setCheat(true);
																		    	   client.submitStudentTest(st1);
																		    	   client.UpdateExecutreTest(client.GetAllExecutreTest().get(idx));
																		    	   time1[c].stop();
																		    	  
																		    	   
																		    	   
																			  }
																	    });
																       Button B2=new Button("No");//continue solving
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
	    Button B2=new Button("back");//going to main window
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
	public void resultWindow(String str4,int c,Client client) {//checking grades
		
		Stage Second=new Stage();
		//loading choice window
		checked_list=new ArrayList<studentTest>();
		studentTest list[]=client.getAllTestsByStudentId(str4).getTests();
		if(list != null) {//getting tests
			for(int i=0;i<list.length;i++)
			{
				if(list[i].isCheck())
				{
					checked_list.add(list[i]);
				}
			}
		}
		BorderPane BP=new BorderPane();
		ScrollPane pane=new ScrollPane();
		ObservableList<String> data = FXCollections.observableArrayList();

	    ListView<String> listView = new ListView<String>(data);
	    listView.setPrefSize(300, 250);
	    for(int i=0;i<checked_list.size();i++)data.add(checked_list.get(i).getTest().getCode());

	    listView.setItems(data);
	    listView.getSelectionModel().selectedItemProperty().addListener(
	        (ObservableValue<? extends String> ov, String old_val, 
	            String new_val) -> {
	                Question_select=new_val;
	                
	    });
	    Button view1=new Button("view grade");//view only the grade
	    view1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				    if(Question_select!="0") {
				    	//loading the window
				     Stage second=new Stage();
				     GridPane pane=new GridPane();
				     int idx=0;
				     for(int i=0;i<checked_list.size();i++)//finding the test
				     {
				    	 if(checked_list.get(i).getTest().getCode().equals(Question_select))idx=i;
				     }
				     Label L1=new Label("Grade :"+checked_list.get(idx).getGrade());//change after madafaker
				     pane.add(L1, 1, 0);
				     Scene scene = new Scene(pane,300,200);
				     scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					 Second.setScene(scene);
					 Second.show();
				    }
				  }
		});
	    Button view2=new Button("view Test");//loading test
	    view2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				 if(Question_select!="0") {
				     int idx=0,idx2=0;
				     Date d=new Date();
			         for(int i=0;i<checked_list.size();i++)//finding test
			         {
			    	 if(checked_list.get(i).getTest().getCode().equals(Question_select))idx=i;
			         }
			         for(int i=0;i<client.GetAllExecutreTest().size();i++)
			         {
			        	 if(client.GetAllExecutreTest().get(i).getTest().getCode()
			        			 .equals(checked_list.get(idx).getTest().getCode()))
			        	 {
			        		 d=client.GetAllExecutreTest().get(i).getDate();
			        	 }
			         }
			         //loading test window
			         studentTest Chosen=new studentTest();
			         Chosen.setTest(checked_list.get(idx).getTest());
				    ScrollPane pane=new ScrollPane();
			     	GridPane Text_edit=new GridPane();
			     	Text_edit.setHgap(10);
			     	Text_edit.setVgap(10);
			     	Text_edit.setPadding(new Insets(25, 25, 25, 25));
			     	Label L1=new Label("Test: "+Chosen.getTest().getCode());
			     	Text_edit.add(L1, 0, 0);
			     	L1=new Label("Taken by: "+Owner_name[c]);
			     	Text_edit.add(L1, 0, 1);
			     	L1=new Label("On date: "+d);
			     	Text_edit.add(L1, 0, 2);
			     	L1=new Label("Test duration: "+Chosen.getTime()+" minuts");
			     	Text_edit.add(L1, 0, 3);
			     	L1=new Label("Test grade: "+Chosen.getGrade());
			     	Text_edit.add(L1, 0, 4);
			       	if(Chosen.getReason()!=null && !Chosen.getReason().isEmpty())
			     	{
			     		L1=new Label("Grade change reason: "+Chosen.getReason());
				     	Text_edit.add(L1, 0, 5);
			     	}
			     	int j=6;
			     	for(int i=0;i<Chosen.getTest().getQuestions().size();i++)
			     	{
			     		final int val=i;
			     		if(Chosen.getAnswers() != null && Chosen.getAnswers().get(i)!="0") {
			     		L1=new Label("Question"+(i+1)+":");
			     		L1.setFont(Font.font( "", FontWeight.BOLD, 17));
			         	Text_edit.add(L1, 0, j);
			         	j=j+2;
			         	L1=new Label("Student instructions: ");
			         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
			         	Text_edit.add(L1, 0, j);
			         	L1=new Label(Chosen.getTest().getQuestions().get(i).getSInstruction());
			         	Text_edit.add(L1, 1, j);
			         	j++;
			         	L1=new Label("Teacher instructions: ");
			         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
			         	Text_edit.add(L1, 0, j);
			         	L1=new Label(Chosen.getTest().getQuestions().get(i).getTInstruction());
			         	Text_edit.add(L1, 1, j);
			         	j++;
			         	L1=new Label("Question: ");
			         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
			         	Text_edit.add(L1, 0, j);
			         	j++;
			         	L1=new Label(Chosen.getTest().getQuestions().get(i).getBody());
			         	Text_edit.add(L1, 0, j);
			         	j=j+2;
			         	L1=new Label("Answers: ");
			         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
			         	Text_edit.add(L1, 0, j);
			         	j++;
			         	L1=new Label("1)"+Chosen.getTest().getQuestions().get(i).getAnswer1());
			         	Text_edit.add(L1, 0, j);
			         	j++;
			         	L1=new Label("2)"+Chosen.getTest().getQuestions().get(i).getAnswer2());
			         	Text_edit.add(L1, 0, j);
			         	j++;
			         	L1=new Label("3)"+Chosen.getTest().getQuestions().get(i).getAnswer3());
			         	Text_edit.add(L1, 0, j);
			         	j++;
			         	L1=new Label("4)"+Chosen.getTest().getQuestions().get(i).getAnswer4());
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
				         	L1=new Label(Chosen.getTest().getQuestions().get(i).getSInstruction());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 1, j);
				         	j++;
				         	L1=new Label("Teacher instructions: ");
				         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	L1=new Label(Chosen.getTest().getQuestions().get(i).getTInstruction());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 1, j);
				         	j++;
				         	L1=new Label("Question: ");
				         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
				         	L1=new Label(Chosen.getTest().getQuestions().get(i).getBody());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j=j+2;
				         	L1=new Label("Answers: ");
				         	L1.setFont(Font.font( "", FontWeight.BOLD, 15));
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
				         	L1=new Label("1)"+Chosen.getTest().getQuestions().get(i).getAnswer1());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
				         	L1=new Label("2)"+Chosen.getTest().getQuestions().get(i).getAnswer2());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
				         	L1=new Label("3)"+Chosen.getTest().getQuestions().get(i).getAnswer3());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
				         	L1=new Label("4)"+Chosen.getTest().getQuestions().get(i).getAnswer4());
				         	L1.setTextFill(Color.RED);
				         	Text_edit.add(L1, 0, j);
				         	j++;
			     		}
			     		if(Chosen.getRemark() != null && i<Chosen.getRemark().size() && !Chosen.getRemark().get(i).isEmpty())
			     		{
			     			L1=new Label("Teacher remarks:"+Chosen.getRemark().get(i));
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
