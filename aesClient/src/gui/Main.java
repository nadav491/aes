package gui;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import client.Client;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import question.ExecutedTest;
import user.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;


public class Main extends Application {
	public static String HOST_IP = "192.168.1.12";
	public static final int HOST_PORT = 5555;
	public static final ArrayList<ExecutedTest> ExecuteList=new ArrayList<ExecutedTest>();
    public static final StudentController Controller[]=new StudentController[2];//change to size of students
    public static final SampleController TController[]=new SampleController[2];//change to size of teachers
    public static final ManagerController Manager=new ManagerController();
    public static int check=0;
    private int loop;
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			 GridPane grid = new GridPane();
		        grid.setHgap(10);
		        grid.setVgap(10);
		        grid.setPadding(new Insets(25, 25, 25, 25));
		        Label L1=new Label("ID");
		        grid.add(L1,0,0);
		        TextArea txt1=new TextArea();
		        txt1.setPrefColumnCount(15);
		        txt1.setPrefRowCount(1);
		        txt1.setPromptText("enter Id here");
		        grid.add(txt1,1,0);
		        L1=new Label("Password");
		        grid.add(L1,0,1);
		        TextArea txt2=new TextArea();
		        txt2.setPrefColumnCount(15);
		        txt2.setPrefRowCount(1);
		        txt2.setPromptText("enter pasword here");
		        grid.add(txt2,1,1);
		        Button B1=new Button("Login");
		        //get login data from database here
		   
		        B1.setOnAction(new EventHandler<ActionEvent>()
			    {
				       @Override
				       public void handle(ActionEvent e)
					  { 
				    	Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
				    	ArrayList<String> typeName = User.login(client, txt1.getText(), txt2.getText());
				    	if(typeName.size()==2) 
				    	{
					    	switch(typeName.get(0))
					    	{
					    		case "Student": 
					    			Controller[0]=new StudentController();
					    			Controller[0].set(txt1.getText(), txt2.getText());
					    			Controller[0].Owner_name[0]=typeName.get(1);
					    			Controller[0].time1[0]=new Timeline();
					    			break;
					    		case "Teacher":
					    			TController[0]=new SampleController();
					    			TController[0].Owner_name[0]=txt1.getText();
					    			TController[0].Owner_name[0]=typeName.get(1);
					    			break;
					    	}
				    	}
					    else 
					    	typeName.add("");
				    	
				    	
				    	if(txt1.getText().isEmpty())
				    	{
				    		if(txt2.getText().isEmpty())
				    		{
				    			txt1.setBlendMode(BlendMode.RED);
				    			txt2.setBlendMode(BlendMode.RED);
				    		}
				    		else
				    		{
				    			txt1.setBlendMode(BlendMode.RED);
				    			txt2.setBlendMode(BlendMode.SRC_OVER);
				    		}
				    	}
				    	else
				    	{
				    		if(txt2.getText().isEmpty())
				    		{
				    			txt2.setBlendMode(BlendMode.RED);
				    			txt1.setBlendMode(BlendMode.SRC_OVER);
				    		}
				    		else
				    		{
				    			txt1.setBlendMode(BlendMode.SRC_OVER);
				    			txt2.setBlendMode(BlendMode.SRC_OVER);
				    					
						    	System.out.println(txt1.getText() + txt2.getText()+" "+typeName+" "+typeName.get(0));
				    					//change here to input size for jumps
				    					if(typeName.get(0).equals("Student"))
				    					{
				    						BorderPane Main_window=new BorderPane();
				    						Button B1=new Button("Signup for test");
				    						B1.setOnAction(new EventHandler<ActionEvent>()
					    						{
					    							 @Override
					    							  public void handle(ActionEvent e)
					    								  {
					    								    Controller[0].signUp(Controller[0].Owner_name[0],0,client);
					    								    
					    								    
					    								  }
					    						});
				    						Button B2=new Button("solve test");
				    						B2.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								  Controller[0].loadTestChoiceWindow(0,client);
			    								     
				    								  }
				    						});
				    						Button B3=new Button("check grades");
				    						B3.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								     Controller[0].resultWindow(Controller[0].Owner_name[0],0,client);
			    								        
				    								      
				    								  }
				    						});
				    						Button B4=new Button("Check Test Signup list");
				    						B4.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								 Controller[0].viewSignUpList(0,client);
		    								         
				    								     
				    								  }
				    						});
				    						 Button back=new Button("back");
					    					    back.setOnAction(new EventHandler<ActionEvent>()
					    						{
					    							 @Override
					    							  public void handle(ActionEvent e)
					    								  {
					    							    	primaryStage.close();
					    							    	start(new Stage());
					    							      }
					    							       
					    						 });
				    						VBox box=new VBox();
				    						box.getChildren().addAll(B1,B4,B2,B3);
				    						Main_window.setLeft(box);
				    						Main_window.setBottom(back);
				    						Scene scene = new Scene(Main_window,400,400);
				    						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				    						primaryStage.setScene(scene);
				    						primaryStage.setTitle("Student window");
				    						primaryStage.show();
				    					}
				    					else if(typeName.get(0).equals("Teacher"))
				    					{
				    						BorderPane root = new BorderPane();
				    						VBox box1=new VBox();
				    						Button B2=new Button("Manage Question Database");
				    					    B2.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	
				    							    	TController[0].Q_print(TController[0].Owner_name[0],0,client);
				    							    	
				    							      }
				    							       
				    						 });
				    					    Button B1=new Button("Manage Test Database");
				    					    B1.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    TController[0].T_print(TController[0].Owner_name[0],0,client);
				    							    	
				    								    
				    							      }
				    							       
				    						 });
				    					    Button B3=new Button("Execute Test");
				    					    B3.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								     TController[0].Run_test(TController[0].Owner_name[0],0,client);
				    							    	
				    								     
				    							      }
				    							       
				    						 });
				    					    Button B4=new Button("Check Tests");
				    					    B4.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								   TController[0].CheckTests(0,client);
				    							    	
				    								 
				    							      }
				    							       
				    						 });
				    					    Button B5=new Button("Check Test reports");
				    					    B5.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								TController[0].Check_test_statistics(0);
				    							    	
				    								     
				    							      }
				    							       
				    						 });
				    					    Button back=new Button("back");
				    					    back.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	primaryStage.close();
				    							    	start(new Stage());
				    							      }
				    							       
				    						 });
				    					    box1.getChildren().addAll(B2,B1,B3,B4,B5);
				    					    root.setLeft(box1);
				    					    root.setBottom(back);
											Scene scene = new Scene(root,400,400);
											scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
											primaryStage.setScene(scene);
											primaryStage.setTitle("Teacher window");
											primaryStage.show();
				    					}
				    					else if(typeName.get(0).equals("Manager"))
				    					{
				    						BorderPane root = new BorderPane();
				    						VBox box1=new VBox();
				    						Button B2=new Button("read System input reports");
				    					    B2.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	
				    							    	Manager.SystemReport();
				    							      }
				    							       
				    						 });
				    					    Button B1=new Button("regular reports");
				    					    B1.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    Manager.Report();
				    							      }
				    							       
				    						 });
				    					    Button B3=new Button("Custom reports");
				    					    B3.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    Manager.Custom_report();
				    							      }
				    							       
				    						 });
				    					    Button back=new Button("back");
				    					    back.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	primaryStage.close();
				    							    	start(new Stage());
				    							      }
				    							       
				    						 });
				    					    box1.getChildren().addAll(B2,B1,B3);
				    					    root.setLeft(box1);
				    					    root.setBottom(back);
											Scene scene = new Scene(root,400,400);
											scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
											primaryStage.setScene(scene);
											primaryStage.setTitle("Teacher window");
											primaryStage.show();
											
				    					
				    				}
				    		
				    				else {
				    					GridPane grid = new GridPane();
				    			        grid.setHgap(10);
				    			        grid.setVgap(10);
				    			        grid.setPadding(new Insets(25, 25, 25, 25));
				    			        Label L1=new Label("wrong ID or password");
				    			        grid.add(L1, 0, 0);
				    			        Button Back=new Button("Back");
				    			        Back.setOnAction(new EventHandler<ActionEvent>()
				    				    {
				    					       @Override
				    					       public void handle(ActionEvent e)
				    						  {
				    					    	   primaryStage.close();
				    					    	   start(new Stage());
				    						  }
				    				    });
				    			        grid.add(Back, 0, 1);
									    Scene scene = new Scene(grid,400,200);
									    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
									    primaryStage.setScene(scene);
									    primaryStage.show();
				    				}
				    			}
				    	}
				    	
				      }
				       
			    });
		        grid.add(B1, 0,2);
		        Button B2=new Button("Clear");
		        B2.setOnAction(new EventHandler<ActionEvent>()
			    {
				       @Override
				       public void handle(ActionEvent e)
					  {
				    	txt1.clear();
				    	txt2.clear();
				    	txt1.setBlendMode(BlendMode.SRC_OVER);
		    			txt2.setBlendMode(BlendMode.SRC_OVER);
				      }
				       
			    });
		        grid.add(B2, 1,2);
		        root.setCenter(grid);
		        Button B_exit=new Button("Exit");
		        B_exit.setOnAction(new EventHandler<ActionEvent>()
			    {
				       @Override
				       public void handle(ActionEvent e)
					  {
				    	   GridPane grid = new GridPane();
					       grid.setHgap(10);
					       grid.setVgap(10);
					       grid.setPadding(new Insets(25, 25, 25, 25));
					       Label Message=new Label("Are you sure you want to exit the system?");
					       grid.add(Message,1, 0);
					       Button B1=new Button("Yes");
					       B1.setOnAction(new EventHandler<ActionEvent>()
						    {
							       @Override
							       public void handle(ActionEvent e)
								  {
							    	   primaryStage.close();
								  }
						    });
					       Button B2=new Button("No");
					       B2.setOnAction(new EventHandler<ActionEvent>()
						    {
							       @Override
							       public void handle(ActionEvent e)
								  {
							    	  primaryStage.close();
							    	  start(new Stage());
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
		        root.setBottom(B_exit);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("AES Login window");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
	public ArrayList<ExecutedTest> getList()
	{
		return this.ExecuteList;
	}
	
}
