package gui;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import test.ExecutedTest;
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
	public static final String HOST_IP = "";
	public static final int HOST_PORT = 5555;
	
    public static final StudentController Controller[]=new StudentController[2];//Student controller
    public static final SampleController TController[]=new SampleController[2];//teacher controller
    public static final ManagerController Manager=new ManagerController();//manager contrller
    public static int check=0;
   
    private int loop;
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));//main window
			 GridPane grid = new GridPane();//load window
		        grid.setHgap(10);
		        grid.setVgap(10);
		        grid.setPadding(new Insets(25, 25, 25, 25));
		        Label L1=new Label("HOST_IP");
		        grid.add(L1,0,0);
		        TextArea txt3=new TextArea();//host ip input field
		        txt3.setPrefColumnCount(15);
		        txt3.setPrefRowCount(1);
		        txt3.setPromptText("enter Ip here");
		        grid.add(txt3,1,0);
		        L1=new Label("ID");
		        grid.add(L1,0,1);
		        TextArea txt1=new TextArea();// id input field
		        txt1.setPrefColumnCount(15);
		        txt1.setPrefRowCount(1);
		        txt1.setPromptText("enter Id here");
		        grid.add(txt1,1,1);
		        L1=new Label("Password");
		        grid.add(L1,0,2);
		        TextArea txt2=new TextArea();//password input field
		        txt2.setPrefColumnCount(15);
		        txt2.setPrefRowCount(1);
		        txt2.setPromptText("enter pasword here");
		        grid.add(txt2,1,2);
		        Button B1=new Button("Login");//login button
		   
		        B1.setOnAction(new EventHandler<ActionEvent>() // handale on action
			    {
				       @Override
				       public void handle(ActionEvent e)//on action 
					  { 
				    	if(!txt3.getText().isEmpty()) {
				    	//Main.HOST_IP=txt3.getText();
				    	Client client = new Client(Main.HOST_IP,Main.HOST_PORT);//getting the client 
				    	ArrayList<String> typeName = User.login(client, txt1.getText(), txt2.getText());//login input
				    	if(typeName.size()==2) //if we found the id and password pair in database
				    	{
					    	switch(typeName.get(0))
					    	{
					    		case "Student": //initializing student
					    			Controller[0]=new StudentController();
					    			Controller[0].set(txt1.getText(), txt2.getText());
					    			Controller[0].Owner_name[0]=typeName.get(1);
					    			Controller[0].time1[0]=new Timeline();
					    			break;
					    		case "Teacher"://initializing teacher
					    			TController[0]=new SampleController();
					    			TController[0].Owner_name[0]=txt1.getText();
					    			TController[0].Owner_name[0]=typeName.get(1);
					    			break;
					    	}
				    	}
					    else 
					    	typeName.add("");
				    	
				    	
				    	if(txt1.getText().isEmpty())// if id is empty
				    	{
				    		if(txt2.getText().isEmpty())//if passwword is empty
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
				    		if(txt2.getText().isEmpty())//if password is empty
				    		{
				    			txt2.setBlendMode(BlendMode.RED);
				    			txt1.setBlendMode(BlendMode.SRC_OVER);
				    		}
				    		else//valid input
				    		{
				    			txt1.setBlendMode(BlendMode.SRC_OVER);
				    			txt2.setBlendMode(BlendMode.SRC_OVER);
				    			//change here to input size for jumps
				    					if(typeName.get(0).equals("Student"))//if student user we open student window
				    					{
				    						BorderPane Main_window=new BorderPane();
				    						Button B1=new Button("Signup for test");
				    						B1.setOnAction(new EventHandler<ActionEvent>()//signup for test function
					    						{
					    							 @Override
					    							  public void handle(ActionEvent e)
					    								  {
					    								    Controller[0].signUp(Controller[0].Owner_name[0],0,client);
					    								    
					    								    
					    								  }
					    						});
				    						Button B2=new Button("solve test");
				    						B2.setOnAction(new EventHandler<ActionEvent>()//solving a test function
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								  Controller[0].loadTestChoiceWindow(0,client);
			    								     
				    								  }
				    						});
				    						Button B3=new Button("check grades");
				    						B3.setOnAction(new EventHandler<ActionEvent>()//checking grades function
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								     Controller[0].resultWindow(Controller[0].Owner_name[0],0,client);
			    								        
				    								      
				    								  }
				    						});
				    						Button B4=new Button("Check Test Signup list");
				    						B4.setOnAction(new EventHandler<ActionEvent>()// checking signuplist function
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								 Controller[0].viewSignUpList(0,client);
		    								         
				    								     
				    								  }
				    						});
				    						 Button back=new Button("back");
					    					    back.setOnAction(new EventHandler<ActionEvent>()//logout function
					    						{
					    							 @Override
					    							  public void handle(ActionEvent e)
					    								  {
					    							    	primaryStage.close();
															User.logout(client, txt1.getText());
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
				    					else if(typeName.get(0).equals("Teacher"))//if user is teacher we open teacher window
				    					{
				    						BorderPane root = new BorderPane();
				    						VBox box1=new VBox();
				    						Button B2=new Button("Manage Question Database");//question data base managing function
				    					    B2.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	
				    							    	TController[0].Q_print(TController[0].Owner_name[0],0,client);
				    							    	
				    							      }
				    							       
				    						 });
				    					    Button B1=new Button("Manage Test Database");//test data base managing function
				    					    B1.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    TController[0].T_print(TController[0].Owner_name[0],0,client);
				    							    	
				    								    
				    							      }
				    							       
				    						 });
				    					    Button B3=new Button("Execute Test");//executing tests function
				    					    B3.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								     TController[0].Run_test(TController[0].Owner_name[0],0,client);
				    							    	
				    								     
				    							      }
				    							       
				    						 });
				    					    Button B4=new Button("Check Tests");//test checking function
				    					    B4.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								   TController[0].CheckTests(0,client);
				    							    	
				    								 
				    							      }
				    							       
				    						 });
				    					    Button B5=new Button("Check Test reports");//checking system reports
				    					    B5.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								TController[0].Check_test_statistics(0,client);
				    							    	
				    								     
				    							      }
				    							       
				    						 });
				    					    Button back=new Button("back");//logout function
				    					    back.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	primaryStage.close();
														User.logout(client, txt1.getText());
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
				    					else if(typeName.get(0).equals("Manager"))//if user is manager we open manager window
				    					{
				    						Timeline tm=new Timeline();//loop to catch massages from teacher
				    						tm.setCycleCount(Timeline.INDEFINITE);
							    			   tm.getKeyFrames().add(
							   		                new KeyFrame(Duration.seconds(1),
							   		                  new EventHandler() {
							   							@Override
							   							public void handle(Event event) {
							   								for(int i=0;i<client.GetAllExecutreTest().size();i++) {//finding time change request on running test
							   									loop=i;
							   								if(client.executedTestsCheckLock(client.GetAllExecutreTest().get(i))==3)//we open time change window
							   								{
							   									tm.stop();
							   								    Stage ask=new Stage();
							   								    GridPane p=new GridPane();
							   								    p.add(new Label("reaseon for change: "+client.GetAllExecutreTest().get(i).returnR()), 0, 0);//getting teacher reason
							   								    p.add(new Label("time added: "+client.GetAllExecutreTest().get(i).returnT()), 0, 1);//getting time extand
							   								    Button B1=new Button("accept");
							   								    B1.setOnAction(new EventHandler<ActionEvent>()//if manager press accepting change
									    						{
									    							 @Override
									    							  public void handle(ActionEvent e)
									    								  {
									    								      client.GetAllExecutreTest().get(loop).setSign(4);
									    								      client.GetAllExecutreTest().get(loop).setCurrentTime((Integer.parseInt(//new test duration
									    								    		  client.GetAllExecutreTest().get(loop).getTest().getTime())
									    								    		  +client.GetAllExecutreTest().get(loop).returnT())+"");
									    								      
									    								      client.UpdateExecutreTest(client.GetAllExecutreTest().get(loop));//updating test running
									    								      tm.play();//continuing run
									    								      ask.close();
									    								      
									    								  }
									    						});
							   								    Button B2=new Button("reject");//if we reject the change
							   								 B2.setOnAction(new EventHandler<ActionEvent>()
									    						{
									    							 @Override
									    							  public void handle(ActionEvent e)
									    								  {
									    								      client.GetAllExecutreTest().get(loop).setSign(1);
									    								      client.UpdateExecutreTest(client.GetAllExecutreTest().get(loop));//updating running test
									    								      tm.play();//continuing run
									    								      ask.close();
									    								      
									    								  }
									    						});
							   								    p.add(B2, 0, 2);
							   								 
							   								    p.add(B1, 1, 2);
							   								    Scene scene = new Scene(p,400,400);
																scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
																ask.setScene(scene);
																ask.setTitle("time change window");
																ask.show();
							   								}
							   								else
							   								{
							   									
							   									tm.play();
							   								}
							   								}
							   							}
							   		                }));
							    			   tm.play();
				    						BorderPane root = new BorderPane();
				    						VBox box1=new VBox();
				    						Button B2=new Button("read System input reports");//system report function
				    					    B2.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	
				    							    	Manager.SystemReport(client);
				    							      }
				    							       
				    						 });
				    					    Button B1=new Button("regular reports");//test reports by type function
				    					    B1.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    Manager.Report(client);
				    							      }
				    							       
				    						 });
				    					    Button B3=new Button("Custom reports");//custom reports function
				    					    B3.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    								    Manager.Custom_report(client);
				    							      }
				    							       
				    						 });
				    					    Button back=new Button("back");//logout function
				    					    back.setOnAction(new EventHandler<ActionEvent>()
				    						{
				    							 @Override
				    							  public void handle(ActionEvent e)
				    								  {
				    							    	primaryStage.close();
														User.logout(client, txt1.getText());
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
				    		
				    				else {//wrong input window
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
					  }  
			    });
		        grid.add(B1, 0,3);
		        Button B2=new Button("Clear");// clearing input spaces
		        B2.setOnAction(new EventHandler<ActionEvent>()
			    {
				       @Override
				       public void handle(ActionEvent e)
					  {
				    	txt1.clear();
				    	txt2.clear();
				    	txt3.clear();
				    	txt1.setBlendMode(BlendMode.SRC_OVER);
		    			txt2.setBlendMode(BlendMode.SRC_OVER);
		    			txt3.setBlendMode(BlendMode.SRC_OVER);
				      }
				       
			    });
		        grid.add(B2, 1,3);
		        root.setCenter(grid);
		        Button B_exit=new Button("Exit");//exiting system function
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
	
	
}
