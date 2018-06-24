package gui;

import java.util.ArrayList;

import client.Client;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import question.Question;
import test.ExecutedTest;
import test.Test;
import user.User;


public class ManagerController {

	
	private Stage Main_stage;//main stage
	private String Question_select;//selection vessale
	private ArrayList<String> Course_list;//list of cources
	private ArrayList<Question> Q_list;//question list
	private ArrayList<Test> test_list;//test list
	public ManagerController()// initialization
	{
		Q_list=new ArrayList<Question>();
		test_list=new ArrayList<Test>();
		Main_stage=new Stage();
		Question_select="0";
	}
	public void SystemReport(Client client)//view system input reports
	{
		Q_list=client.getAllQuestion();//get all question from database
		test_list=client.getAllTests();//get all tests from database
		//
		//
		//initializing view window
		GridPane grid=new GridPane();
   	    int j=0;
   	    Question Q_view=new Question();
		Text txt1,txt2 = null,txt3 = null,txt4 = null,
				txt5 = null,txt6 = null,txt7 = null,txt8 = null,txt9 = null,txt10;
		TextFlow Flowtxt=new TextFlow();
		Flowtxt.setTextAlignment(TextAlignment.JUSTIFY);
		Flowtxt.setPrefSize(600,300);
		Flowtxt.setLineSpacing(5.0);
		ObservableList list=Flowtxt.getChildren(); 
		for(int i=0;i<Q_list.size();i++) {//adding test view
		Q_view=Q_list.get(i);
		 txt1=new Text("Question "+(i+1)+":\n"); //question num
		txt1.setFont(new Font(15));
		 txt2=new Text("Written by: "+Q_view.getOwner()+"\n");//question creator
		txt2.setFont(new Font(15));
		 txt3=new Text("Student Instructions: "+Q_view.getSInstruction()+"\n");//instructions for student
		txt3.setFont(new Font(15));
		 txt10=new Text("Teacher Instructions: "+Q_view.getTInstruction()+"\n");//instructions for teacher
		txt10.setFont(new Font(15));
		 txt4=new Text("Question: "+Q_view.getBody()+"\n");//question body
		txt4.setFont(new Font(15));
		 txt5=new Text("answers:\n");
		txt5.setFont(new Font(15));
		 txt6=new Text("1:"+Q_view.getAnswer1()+"\n");//answer 1
		txt6.setFont(new Font(15));
		 txt7=new Text("2:"+Q_view.getAnswer2()+"\n");//answer 2
		txt7.setFont(new Font(15));
		 txt8=new Text("3:"+Q_view.getAnswer3()+"\n");//answer 3
		txt8.setFont(new Font(15));
		 txt9=new Text("4:"+Q_view.getAnswer4()+"\n");//answer 4
		txt9.setFont(new Font(15));
		list.addAll(txt1,txt2,txt3,txt10,txt4,txt5,txt6,txt7,txt8,txt9); //adding input
		list.add(new Text("\n")); 
		}
		//setting window
   	    TitledPane tp = new TitledPane();
	    tp.setText("Question Database");
	    tp.setExpanded(false);
		tp.setPrefWidth(1000);
		ScrollPane p=new ScrollPane();
		p.setContent(Flowtxt);
		tp.setContent(p);
		ScrollPane pane=new ScrollPane();
    	GridPane Text_edit=new GridPane();
    	Text_edit.setHgap(10);
    	Text_edit.setVgap(10);
    	Text_edit.setPadding(new Insets(25, 25, 25, 25));
    	
    	for(int k=0;k<test_list.size();k++) {//creating test view window
    	Test Chosen=new Test();
    	Chosen=test_list.get(k);
    	Label L1=new Label("Test: "+Chosen.getCode());//test code
    	Text_edit.add(L1, 0, j);
    	j++;
    	L1=new Label("Written by: "+Chosen.getOwner());//test creator
    	Text_edit.add(L1, 0, j);
    	j++;
    	L1=new Label("Test duration: "+Chosen.getTime()+" minuts");//test length
    	Text_edit.add(L1, 0, j);
    	j++;
    	for(int i=0;i<Chosen.getQuestions().size();i++) // getting question data
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
        	j++;
    	}
    	}
    	//adding to view window
    	pane.setContent(Text_edit);
	    TitledPane tp1 = new TitledPane();
	    tp1.setExpanded(false);
		tp1.setPrefWidth(1000);
	    tp1.setText("Test Database");
	    tp1.setContent(pane);
	    grid.add(tp, 0, 0);
	    grid.add(tp1, 0, 1);
	    Scene scene = new Scene(grid,1000,1000);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  Main_stage.setScene(scene);
		  Main_stage.setTitle("System report view window");
		  Main_stage.show();
	}
 	public void Report(Client client)//function to view test data by teacher/course/student id
	{
		BorderPane root=new BorderPane();
		Button B1=new Button("get report by teacher");//by teacher id
		 B1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {  //initializing choice window
				     ArrayList<String> Teachers=new ArrayList<String>();
				     Teachers=client.getAllTeachersNames();
				     BorderPane pane=new BorderPane();
				     ScrollPane sp=new ScrollPane();
				     ObservableList<String> data = FXCollections.observableArrayList();

				     ListView<String> listView = new ListView<String>(data);
				     listView.setPrefSize(300, 250);
				     for(int i=0;i<Teachers.size();i++)data.add(Teachers.get(i));

				     listView.setItems(data);
				     listView.getSelectionModel().selectedItemProperty().addListener(
				        (ObservableValue<? extends String> ov, String old_val, 
				            String new_val) -> {
				                Question_select=new_val;
				                
				          });
				      sp.setContent(listView);
				      pane.setLeft(sp);
				      Button B1=new Button("get report");//getting the report
				      B1.setOnAction(new EventHandler<ActionEvent>()
						{
							 @Override
							  public void handle(ActionEvent e)
								  {     
								     if(Question_select!="0")
								     {
								    	 ArrayList<ExecutedTest> dick=new ArrayList<ExecutedTest>();
								    	 for(int i=0;i<client.GetAllExecutreTest().size();i++)//finding finished checked tests
								    	 {
								    		 if(Question_select.equals(client.GetAllExecutreTest().get(i).getTest().getOwner()))
								    		 {
								    			 if(client.GetAllExecutreTest().get(i).getSign()==1) {
								    			 dick.add(client.GetAllExecutreTest().get(i));
								    			 }
								    		 }
								    	 }
								    	 //initializing window
								    	 GridPane grid=new GridPane();
								    	 int j=0;
								    	 for(int i=0;i<dick.size();i++)
								    	 {
								    		 if(dick.get(i).getTest().getOwner().equals(Question_select))
								    		 {
								    			 if(dick.get(i).getSign()==0)
								    			 {
								    				 TitledPane tp = new TitledPane();
								    				 tp.setText(dick.get(i).getTest().getCode());
								    				 GridPane p=new GridPane();
								    				 int avarage=0;
								    				 //calculating avarage score
								    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
								    				 {
								    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
								    				 }
								    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
								    				 p.add(new Label("avarage score: "+avarage),0, 0);
								    				 int median=0;
								    				 //calculating median
								    				 int middle = dick.get(i).getGradeList().size();
								    				 if(dick.get(i).getGradeList().size()!=0) {
								    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
								    			             median=Integer.parseInt(dick.get(i).getGradeList().get(middle-1));
								    			        } else {
								    			           median=Integer.parseInt(dick.get(i).getGradeList().get(middle));
								    			        }
								    				 }
								    			     p.add(new Label("median: "+median),0, 1);
								    			     //calculating grade spread to tens
								    			     int gradeSpace[]=new int[10];
								    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
								    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
								    			     {
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
								    			    		 gradeSpace[0]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
								    			    		 gradeSpace[1]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
								    			    		 gradeSpace[2]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
								    			    		 gradeSpace[3]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
								    			    		 gradeSpace[4]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
								    			    		 gradeSpace[5]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
								    			    		 gradeSpace[6]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
								    			    		 gradeSpace[7]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
								    			    		 gradeSpace[8]++;
								    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
								    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
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
								    			        p.add(bc, 0, 3);
								    			        
								    				 tp.setContent(p);
								    				 tp.setExpanded(false);
								    				 tp.setPrefWidth(1000);
								    				 grid.add(tp, 0, j);
								    				 j++;
								    			 }
								    		 }
								    	 }
								    	  Scene scene = new Scene(grid,1000,1000);
										  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
										  Main_stage.setScene(scene);
										  Main_stage.setTitle("report view window");
										  Main_stage.show();
								     }
								  }
						});
				      pane.setRight(B1);
				      Scene scene = new Scene(pane,400,400);
					  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					  Main_stage.setScene(scene);
					  Main_stage.setTitle("teacher report window");
					  Main_stage.show();
				  }
		});
		Button B2=new Button("get report by course");//getting test reports by course id
		B2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  { //initializing choice window
				 BorderPane pane=new BorderPane();
			     ScrollPane sp=new ScrollPane();
			     ObservableList<String> data = FXCollections.observableArrayList();
                 Course_list=client.getCoursesId();
			     ListView<String> listView = new ListView<String>(data);
			     listView.setPrefSize(300, 250);
			     for(int i=0;i<Course_list.size();i++)data.add(Course_list.get(i));

			     listView.setItems(data);
			     listView.getSelectionModel().selectedItemProperty().addListener(
			        (ObservableValue<? extends String> ov, String old_val, 
			            String new_val) -> {
			                Question_select=new_val;
			                
			          });
			      sp.setContent(listView);
			      Button B1=new Button("get report");//getting report
			      B1.setOnAction(new EventHandler<ActionEvent>()
					{
						 @Override
						  public void handle(ActionEvent e)
							  {     
							 if(Question_select!="0")
						     {
								 ArrayList<ExecutedTest> dick=new ArrayList<ExecutedTest>();
								 for(int i=0;i<client.GetAllExecutreTest().size();i++)//finding tests by course id
								 {
									 if(client.GetAllExecutreTest().get(i).getSign()==1)
									 {
										 dick.add(client.GetAllExecutreTest().get(i));
									 }
								 }
						    	 GridPane grid=new GridPane();
						    	 int j=0;
						    	 for(int i=0;i<dick.size();i++)
						    	 {
						    		 if((dick.get(i).getTest().getCode().charAt(2)+""+dick.get(i).getTest().getCode().charAt(3)).equals(Question_select))
						    		 {
						    			 if(dick.get(i).getSign()==0)
						    			 {
						    				 TitledPane tp = new TitledPane();
						    				 tp.setText(dick.get(i).getTest().getCode());
						    				 GridPane p=new GridPane();
						    				 int avarage=0;
						    				 //calculating avarage
						    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
						    				 {
						    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
						    				 }
						    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
						    				 p.add(new Label("avarage score: "+avarage),0, 0);
						    				 int median=0;
						    				 //calculating median
						    				 int middle = dick.get(i).getGradeList().size();
						    				 if(dick.get(i).getGradeList().size()!=0) {
						    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
						    			             median=Integer.parseInt(dick.get(i).getGradeList().get(middle-1));
						    			        } else {
						    			           median=Integer.parseInt(dick.get(i).getGradeList().get(middle));
						    			        }
						    				 }
						    			     p.add(new Label("median: "+median),0, 1);
						    			     //calculating grade by tens
						    			     int gradeSpace[]=new int[10];
						    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
						    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
						    			     {
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
						    			    		 gradeSpace[0]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
						    			    		 gradeSpace[1]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
						    			    		 gradeSpace[2]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
						    			    		 gradeSpace[3]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
						    			    		 gradeSpace[4]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
						    			    		 gradeSpace[5]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
						    			    		 gradeSpace[6]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
						    			    		 gradeSpace[7]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
						    			    		 gradeSpace[8]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
						    			    		 gradeSpace[9]++;
						    			    	 
						    			    	 
						    			     }
						    			        //creting hystogram view
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
						    			        p.add(bc, 0, 3);
						    			        
						    				 tp.setContent(p);
						    				 tp.setExpanded(false);
						    				 tp.setPrefWidth(1000);
						    				 grid.add(tp, 0, j);
						    				 j++;
						    			 }
						    		 }
						    	 }
						    	  Scene scene = new Scene(grid,1000,1000);
								  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								  Main_stage.setScene(scene);
								  Main_stage.setTitle("report view window");
								  Main_stage.show();
						     }
							  }
					});
			      pane.setRight(B1);
			      pane.setLeft(sp);
			      Scene scene = new Scene(pane,400,400);
				  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				  Main_stage.setScene(scene);
				  Main_stage.setTitle("teacher report window");
				  Main_stage.show();
				  }
		});
		Button B3=new Button("get report by student");//getting reports by student id
		B3.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {//initializing chice window  
				 BorderPane pane=new BorderPane();
			     ScrollPane sp=new ScrollPane();
			     ObservableList<String> data = FXCollections.observableArrayList();
                 ArrayList<String> students=new ArrayList<String> ();
                 students=client.getAllStudnetNames();
			     ListView<String> listView = new ListView<String>(data);
			     listView.setPrefSize(300, 250);
			     for(int i=0;i<students.size();i++)data.add(students.get(i));

			     listView.setItems(data);
			     listView.getSelectionModel().selectedItemProperty().addListener(
			        (ObservableValue<? extends String> ov, String old_val, 
			            String new_val) -> {
			                Question_select=new_val;
			                
			          });
			      sp.setContent(listView);
			      Button B1=new Button("get report");//getting report
			      B1.setOnAction(new EventHandler<ActionEvent>()
					{
						 @Override
						  public void handle(ActionEvent e)
							  {     
							 if(Question_select!="0")
						     {
								 ArrayList<ExecutedTest> dick=new ArrayList<ExecutedTest>();
								 dick=client.GetAllExecutreTest();
						    	 GridPane grid=new GridPane();
						    	 int j=0;
						    	 for(int i=0;i<dick.size();i++)//getting correct tests by student id
						    	 {
						    		 for(int k=0;k<dick.get(i).getSignUpList().size();k++){
						    		 if(dick.get(i).getSignUpList().get(k).equals(Question_select))
						    		 {
						    			 if(dick.get(i).getSign()==1)
						    			 {   
						    				 TitledPane tp = new TitledPane();
						    				 tp.setText(dick.get(i).getTest().getCode());
						    				 GridPane p=new GridPane();
						    				 int avarage=0;
						    				 //calculating avarage
						    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
						    				 {
						    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
						    				 }
						    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
						    				 p.add(new Label("avarage score: "+avarage),0, 0);
						    				 int median=0;
						    				 //calculating median
						    				 int middle = dick.get(i).getGradeList().size();
						    				 if(dick.get(i).getGradeList().size()!=0) {
						    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
						    			             median=Integer.parseInt(dick.get(i).getGradeList().get(middle-1));
						    			        } else {
						    			           median=Integer.parseInt(dick.get(i).getGradeList().get(middle));
						    			        }
						    				 }
						    			     p.add(new Label("median: "+median),0, 1);
						    			     //calculating grade by tens
						    			     int gradeSpace[]=new int[10];
						    			     for(int d=0;d<10;d++)gradeSpace[d]=0;
						    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
						    			     {
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
						    			    		 gradeSpace[0]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
						    			    		 gradeSpace[1]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
						    			    		 gradeSpace[2]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
						    			    		 gradeSpace[3]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
						    			    		 gradeSpace[4]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
						    			    		 gradeSpace[5]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
						    			    		 gradeSpace[6]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
						    			    		 gradeSpace[7]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
						    			    		 gradeSpace[8]++;
						    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
						    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
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
						    			        p.add(bc, 0, 3);
						    			        
						    				 tp.setContent(p);
						    				 tp.setExpanded(false);
						    				 tp.setPrefWidth(1000);
						    				 grid.add(tp, 0, j);
						    				 j++;
						    			 }
						    		 }
						    		 }
						    	 }
						    	  Scene scene = new Scene(grid,1000,1000);
								  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								  Main_stage.setScene(scene);
								  Main_stage.setTitle("report view window");
								  Main_stage.show();
						     }
							  }
							  
					});
			      pane.setRight(B1);
			      pane.setLeft(sp);
			      Scene scene = new Scene(pane,400,400);
				  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				  Main_stage.setScene(scene);
				  Main_stage.setTitle("teacher report window");
				  Main_stage.show();
				  }
		});
		  Button back=new Button("back");//back to main window
		    back.setOnAction(new EventHandler<ActionEvent>()
			{
				 @Override
				  public void handle(ActionEvent e)
					  {
					    Main_stage.close();
				      }
				       
			 });
		    VBox box1=new VBox();
		    box1.getChildren().addAll(B1,B2,B3);
		    root.setLeft(box1);
		    root.setBottom(back);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main_stage.setScene(scene);
			Main_stage.setTitle("report window");
			Main_stage.show();
		
			
	}
  
    public void Custom_report(Client client)//creating a report by choice
    {
    	ArrayList<String> Teachers=new ArrayList<String>();
    	ArrayList<String> Cources=new ArrayList<String>();
    	ArrayList<String> Students=new ArrayList<String>();
		Students=client.getAllStudnetNames();
		Cources=client.getCoursesId();
		Teachers=client.getAllTeachersNames();
    	BorderPane pM=new BorderPane();
    	GridPane grid=new GridPane();
    	TitledPane tp = new TitledPane();
	    tp.setText("Select Teacher partion");//creating teacher choice window
	    tp.setExpanded(false);
	    tp.setPrefWidth(600);
	    ScrollPane p=new ScrollPane();
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

	    Label candidatesLbl = new Label("Candidates");
	    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
	    gridpane.add(candidatesLbl, 0, 0);

	    Label selectedLbl = new Label("selected");
	    gridpane.add(selectedLbl, 2, 0);
	    GridPane.setHalignment(selectedLbl, HPos.CENTER);

	    // Candidates
	    final ObservableList<String> candidates = FXCollections
	        .observableArrayList();
	    for(int i=0;i<Teachers.size();i++)
	    {
	    	candidates.add(Teachers.get(i));
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
	    p.setContent(gridpane);
	    tp.setContent(p);
	    grid.add(tp, 0, 0);
	    TitledPane tp1 = new TitledPane();
	    tp1.setText("Select Course partion");//creating course choice window
	    tp1.setExpanded(false);
	    tp1.setPrefWidth(600);
	    ScrollPane p1=new ScrollPane();
	    GridPane gridpane1 = new GridPane();
	    gridpane1.setPadding(new Insets(5));
	    gridpane1.setHgap(10);
	    gridpane1.setVgap(10);
	    ColumnConstraints column11 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    ColumnConstraints column21 = new ColumnConstraints(50);
	    ColumnConstraints column31 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    column11.setHgrow(Priority.ALWAYS);
	    column31.setHgrow(Priority.ALWAYS);
	    gridpane1.getColumnConstraints().addAll(column11, column21, column31);

	    Label candidatesLbl1 = new Label("Candidates");
	    GridPane.setHalignment(candidatesLbl1, HPos.CENTER);
	    gridpane1.add(candidatesLbl1, 0, 0);

	    Label selectedLbl1 = new Label("selected");
	    gridpane1.add(selectedLbl1, 2, 0);
	    GridPane.setHalignment(selectedLbl1, HPos.CENTER);

	    // Candidates
	    final ObservableList<String> candidates1 = FXCollections
	        .observableArrayList();
	    for(int i=0;i<Cources.size();i++)
	    {
	    	candidates1.add(Cources.get(i));
	    }
	    final ListView<String> candidatesListView1 = new ListView<>(candidates1);
	    gridpane1.add(candidatesListView1, 0, 1);

	    final ObservableList<String> selected1 = FXCollections.observableArrayList();
	    final ListView<String> heroListView1 = new ListView<>(selected1);
	    gridpane1.add(heroListView1, 2, 1);

	    Button sendRightButton1 = new Button(" > ");
	    sendRightButton1.setOnAction((ActionEvent event) -> {
	      String potential = candidatesListView1.getSelectionModel()
	          .getSelectedItem();
	      if (potential != null) {
	        candidatesListView1.getSelectionModel().clearSelection();
	        candidates1.remove(potential);
	        selected1.add(potential);
	      }
	    });

	    Button sendLeftButton1 = new Button(" < ");
	    sendLeftButton1.setOnAction((ActionEvent event) -> {
	      String s = heroListView1.getSelectionModel().getSelectedItem();
	      if (s != null) {
	        heroListView1.getSelectionModel().clearSelection();
	        selected1.remove(s);
	        candidates1.add(s);
	      }
	    });
	    VBox vbox1 = new VBox(5);
	    vbox1.getChildren().addAll(sendRightButton1, sendLeftButton1);

	    gridpane1.add(vbox1, 1, 1);
	    p1.setContent(gridpane1);
	    tp1.setContent(p1);
	    grid.add(tp1, 0, 1);
	    TitledPane tp2 = new TitledPane();
	    tp2.setText("Select student partion");//creating student choice window
	    tp2.setExpanded(false);
	    tp2.setPrefWidth(600);
	    ScrollPane p2=new ScrollPane();
	    GridPane gridpane2 = new GridPane();
	    gridpane1.setPadding(new Insets(5));
	    gridpane1.setHgap(10);
	    gridpane1.setVgap(10);
	    ColumnConstraints column12 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    ColumnConstraints column22 = new ColumnConstraints(50);
	    ColumnConstraints column32 = new ColumnConstraints(150, 150,
	        Double.MAX_VALUE);
	    column12.setHgrow(Priority.ALWAYS);
	    column32.setHgrow(Priority.ALWAYS);
	    gridpane2.getColumnConstraints().addAll(column12, column22, column32);

	    Label candidatesLbl2 = new Label("Candidates");
	    GridPane.setHalignment(candidatesLbl2, HPos.CENTER);
	    gridpane2.add(candidatesLbl2, 0, 0);

	    Label selectedLbl2 = new Label("selected");
	    gridpane2.add(selectedLbl2, 2, 0);
	    GridPane.setHalignment(selectedLbl2, HPos.CENTER);

	    // Candidates
	    final ObservableList<String> candidates2 = FXCollections
	        .observableArrayList();
	    for(int i=0;i<Students.size();i++)
	    {
	    	candidates2.add(Students.get(i));
	    }
	    final ListView<String> candidatesListView2 = new ListView<>(candidates2);
	    gridpane2.add(candidatesListView2, 0, 1);

	    final ObservableList<String> selected2 = FXCollections.observableArrayList();
	    final ListView<String> heroListView2 = new ListView<>(selected2);
	    gridpane2.add(heroListView2, 2, 1);

	    Button sendRightButton2 = new Button(" > ");
	    sendRightButton2.setOnAction((ActionEvent event) -> {
	      String potential = candidatesListView2.getSelectionModel()
	          .getSelectedItem();
	      if (potential != null) {
	        candidatesListView2.getSelectionModel().clearSelection();
	        candidates2.remove(potential);
	        selected2.add(potential);
	      }
	    });

	    Button sendLeftButton2 = new Button(" < ");
	    sendLeftButton2.setOnAction((ActionEvent event) -> {
	      String s = heroListView2.getSelectionModel().getSelectedItem();
	      if (s != null) {
	        heroListView2.getSelectionModel().clearSelection();
	        selected2.remove(s);
	        candidates2.add(s);
	      }
	    });
	    VBox vbox2 = new VBox(5);
	    vbox2.getChildren().addAll(sendRightButton2, sendLeftButton2);

	    gridpane2.add(vbox2, 1, 1);
	    p2.setContent(gridpane2);
	    tp2.setContent(p2);
	    grid.add(tp2, 0, 2);
	    pM.setCenter(grid);
	    Button B1=new Button("get report");//getting reports
	    B1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				 
				 GridPane grid=new GridPane();
		    	 int j=0;
		    	 if(selected.size()>0)grid.add(new Label("Teacher reports"), 0, 0);//creating teacher reports
		    	 j++;
		    	 ArrayList<ExecutedTest> dick=new ArrayList<ExecutedTest>();
		    	 for(int i=0;i<client.GetAllExecutreTest().size();i++)
		    	 {
		    		 if(Question_select.equals(client.GetAllExecutreTest().get(i).getTest().getOwner()))
		    		 {
		    			 if(client.GetAllExecutreTest().get(i).getSign()==1) {
		    			 dick.add(client.GetAllExecutreTest().get(i));
		    			 }
		    		 }
		    	 }
		    	 for(int g=0;g<selected.size();g++) {
		    	 for(int i=0;i<dick.size();i++)
		    	 {
		    		 if(dick.get(i).getTest().getOwner().equals(selected.get(g)))
		    		 {
		    			 if(dick.get(i).getSign()==1)
		    			 {
		    				 TitledPane tp = new TitledPane();
		    				 tp.setText(dick.get(i).getexecuter()+" "+dick.get(i).getTest().getCode());
		    				 GridPane p=new GridPane();
		    				 int avarage=0;
		    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
		    				 {
		    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
		    				 }
		    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
		    				 p.add(new Label("avarage score: "+avarage),0, 0);
		    				 int median=0;
		    				 int middle = dick.get(i).getGradeList().size();
		    				 if(dick.get(i).getGradeList().size()!=0) {
		    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
		    			             median=Integer.parseInt(dick.get(i).getGradeList().get((middle)/2));
		    			        } else {
		    			           median=Integer.parseInt(dick.get(i).getGradeList().get((middle-1)/2));
		    			        }
		    				 }
		    			     p.add(new Label("median: "+median),0, 1);
		    			     int gradeSpace[]=new int[10];
		    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
		    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
		    			     {
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
		    			    		 gradeSpace[0]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
		    			    		 gradeSpace[1]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
		    			    		 gradeSpace[2]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
		    			    		 gradeSpace[3]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
		    			    		 gradeSpace[4]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
		    			    		 gradeSpace[5]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
		    			    		 gradeSpace[6]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
		    			    		 gradeSpace[7]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
		    			    		 gradeSpace[8]++;
		    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
		    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
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
		    			        p.add(bc, 0, 3);
		    			        
		    				 tp.setContent(p);
		    				 tp.setExpanded(false);
		    				 tp.setPrefWidth(1000);
		    				 grid.add(tp, 0, j);
		    				 j++;
		    			 }
		    		 }
		    	 }
		    	 }
		    	 if(selected1.size()>0) grid.add(new Label("Course reports"), 0, j);//creating course reports
		    	 j++;
			    	 for(int g=0;g<selected1.size();g++)
			    	 {
			    	 for(int i=0;i<dick.size();i++)
			    	 {
			    		 if((dick.get(i).getTest().getCode().charAt(2)+""+dick.get(i).getTest().getCode().charAt(3)).equals(selected1.get(g)))
			    		 {
			    			 if(dick.get(i).getSign()==0)
			    			 {
			    				 TitledPane tp = new TitledPane();
			    				 tp.setText(selected.get(g)+" "+dick.get(i).getTest().getCode());
			    				 GridPane p=new GridPane();
			    				 int avarage=0;
			    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
			    				 {
			    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
			    				 }
			    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
			    				 p.add(new Label("avarage score: "+avarage),0, 0);
			    				 int median=0;
			    				 int middle = dick.get(i).getGradeList().size();
			    				 if(dick.get(i).getGradeList().size()!=0) {
			    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
			    			             median=Integer.parseInt(dick.get(i).getGradeList().get(middle-1));
			    			        } else {
			    			           median=Integer.parseInt(dick.get(i).getGradeList().get(middle));
			    			        }
			    				 }
			    			     p.add(new Label("median: "+median),0, 1);
			    			     int gradeSpace[]=new int[10];
			    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
			    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
			    			     {
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
			    			    		 gradeSpace[0]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
			    			    		 gradeSpace[1]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
			    			    		 gradeSpace[2]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
			    			    		 gradeSpace[3]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
			    			    		 gradeSpace[4]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
			    			    		 gradeSpace[5]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
			    			    		 gradeSpace[6]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
			    			    		 gradeSpace[7]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
			    			    		 gradeSpace[8]++;
			    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
			    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
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
			    			        p.add(bc, 0, 3);
			    			        
			    				 tp.setContent(p);
			    				 tp.setExpanded(false);
			    				 tp.setPrefWidth(1000);
			    				 grid.add(tp, 0, j);
			    				 j++;
		    	             }
			    		 }
			    	 }
			    	 }
			    	 if(selected2.size()>0) grid.add(new Label("Student reports"), 0, j);//creating student reports
			    	 j++;
				    	 for(int g=0;g<selected2.size();g++)
				    	 {
				    		 for(int i=0;i<dick.size();i++)
					    	 {
					    		 for(int k=0;k<dick.get(i).getSignUpList().size();k++){
					    		 if(dick.get(i).getSignUpList().get(k).equals(selected2.get(g)))
					    		 {
					    			 if(dick.get(i).getSign()==0)
					    			 {
					    				 TitledPane tp = new TitledPane();
					    				 tp.setText(dick.get(i).getSignUpList().get(k)+" "+dick.get(i).getTest().getCode());
					    				 GridPane p=new GridPane();
					    				 int avarage=0;
					    				 for(int b=0;b<dick.get(i).getGradeList().size();b++)
					    				 {
					    					 avarage+=Integer.parseInt(dick.get(i).getGradeList().get(b));
					    				 }
					    				 if(dick.get(i).getGradeList().size()!=0)  avarage=avarage/dick.get(i).getGradeList().size();
					    				 p.add(new Label("avarage score: "+avarage),0, 0);
					    				 int median=0;
					    				 int middle = dick.get(i).getGradeList().size();
					    				 if(dick.get(i).getGradeList().size()!=0) {
					    			        if (dick.get(i).getGradeList().size() % 2 == 1) {
					    			             median=Integer.parseInt(dick.get(i).getGradeList().get(middle-1));
					    			        } else {
					    			           median=Integer.parseInt(dick.get(i).getGradeList().get(middle));
					    			        }
					    				 }
					    			     p.add(new Label("median: "+median),0, 1);
					    			     int gradeSpace[]=new int[10];
					    			     for(int d=0;d<10;d++)gradeSpace[d]=0;
					    			     for(int b=0;b<dick.get(i).getFInishedNum();b++)
					    			     {
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=10 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>=0)
					    			    		 gradeSpace[0]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=20 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>10)
					    			    		 gradeSpace[1]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=30 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>20)
					    			    		 gradeSpace[2]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=40 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>30)
					    			    		 gradeSpace[3]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=50 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>40)
					    			    		 gradeSpace[4]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=60 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>50)
					    			    		 gradeSpace[5]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=70 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>60)
					    			    		 gradeSpace[6]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=80 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>70)
					    			    		 gradeSpace[7]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=90 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>80)
					    			    		 gradeSpace[8]++;
					    			    	 if(Integer.parseInt(dick.get(i).getGradeList().get(b))<=100 && 
					    			    			 Integer.parseInt(dick.get(i).getGradeList().get(b))>90)
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
					    			        p.add(bc, 0, 3);
					    			        
					    				 tp.setContent(p);
					    				 tp.setExpanded(false);
					    				 tp.setPrefWidth(1000);
					    				 grid.add(tp, 0, j);
					    				 j++;
					    			 }
					    		 }
					    		 }
				    	 }
				    	 }
		    	  Scene scene = new Scene(grid,1000,1000);
				  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				  Main_stage.setScene(scene);
				  Main_stage.setTitle("report view window");
				  Main_stage.show();
		     
				  }
		});
	    Button B2=new Button("Back");//back to main
	    B2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				      Main_stage.close();
				  }
		});
	    HBox box=new HBox();
	    box.getChildren().addAll(B2,B1);
	    pM.setBottom(box);
    	Scene scene = new Scene(pM,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main_stage.setScene(scene);
		Main_stage.setTitle("Custom report window");
		Main_stage.show();
    }
}
