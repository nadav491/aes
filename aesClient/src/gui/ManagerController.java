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
import test.Test;


public class ManagerController {

	public final static Stage Manager_change_window=new Stage();
	private Stage Main_stage;
	private String Question_select;
	private ArrayList<String> Course_list;
	private ArrayList<Question> Q_list;
	private ArrayList<Test> test_list;
	public ManagerController()
	{
		Course_list=new ArrayList<String>();
		Course_list.add("20");
		Course_list.add("14");
		Course_list.add("12");
		Q_list=new ArrayList<Question>();
		Client client = new Client(Main.HOST_IP,Main.HOST_PORT);
		Q_list=client.getAllQuestion();
		test_list=new ArrayList<Test>();
		Main_stage=new Stage();
		Question_select="0";
	}
	public void SystemReport()
	{
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
		for(int i=0;i<Q_list.size();i++) {
		Q_view=Q_list.get(i);
		 txt1=new Text("Question "+(i+1)+":\n");
		txt1.setFont(new Font(15));
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
		list.addAll(txt1,txt2,txt3,txt10,txt4,txt5,txt6,txt7,txt8,txt9);
		list.add(new Text("\n"));
		}
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
    	
    	for(int k=0;k<test_list.size();k++) {
    	Test Chosen=new Test();
    	Chosen=test_list.get(k);
    	Label L1=new Label("Test: "+Chosen.getCode());
    	Text_edit.add(L1, 0, j);
    	j++;
    	L1=new Label("Written by: "+Chosen.getOwner());
    	Text_edit.add(L1, 0, j);
    	j++;
    	L1=new Label("Test duration: "+Chosen.getTime()+" minuts");
    	Text_edit.add(L1, 0, j);
    	j++;
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
        	j++;
    	}
    	}
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
 	public void Report()
	{
		BorderPane root=new BorderPane();
		Button B1=new Button("get report by teacher");
		 B1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {     
				     BorderPane pane=new BorderPane();
				     ScrollPane sp=new ScrollPane();
				     ObservableList<String> data = FXCollections.observableArrayList();

				     ListView<String> listView = new ListView<String>(data);
				     listView.setPrefSize(300, 250);
				     for(int i=0;i<Main.TController.length;i++)data.add(Main.TController[i].Owner_name[i]);

				     listView.setItems(data);
				     listView.getSelectionModel().selectedItemProperty().addListener(
				        (ObservableValue<? extends String> ov, String old_val, 
				            String new_val) -> {
				                Question_select=new_val;
				                
				          });
				      sp.setContent(listView);
				      pane.setLeft(sp);
				      Button B1=new Button("get report");
				      B1.setOnAction(new EventHandler<ActionEvent>()
						{
							 @Override
							  public void handle(ActionEvent e)
								  {     
								     if(Question_select!="0")
								     {
								    	 GridPane grid=new GridPane();
								    	 int j=0;
								    	 for(int i=0;i<Main.ExecuteList.size();i++)
								    	 {
								    		 if(Main.ExecuteList.get(i).getTest().getOwner().equals(Question_select))
								    		 {
								    			 if(Main.ExecuteList.get(i).getSign()==0)
								    			 {
								    				 TitledPane tp = new TitledPane();
								    				 tp.setText(Main.ExecuteList.get(i).getTest().getCode());
								    				 GridPane p=new GridPane();
								    				 int avarage=0;
								    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
								    				 {
								    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
								    				 }
								    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
								    				 p.add(new Label("avarage score: "+avarage),0, 0);
								    				 int median=0;
								    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
								    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
								    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
								    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get((middle)/2));
								    			        } else {
								    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get((middle-1)/2));
								    			        }
								    				 }
								    			     p.add(new Label("median: "+median),0, 1);
								    			     int gradeSpace[]=new int[10];
								    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
								    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
								    			     {
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
								    			    		 gradeSpace[0]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
								    			    		 gradeSpace[1]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
								    			    		 gradeSpace[2]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
								    			    		 gradeSpace[3]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
								    			    		 gradeSpace[4]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
								    			    		 gradeSpace[5]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
								    			    		 gradeSpace[6]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
								    			    		 gradeSpace[7]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
								    			    		 gradeSpace[8]++;
								    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
								    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
		Button B2=new Button("get report by course");
		B2.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {     
				 BorderPane pane=new BorderPane();
			     ScrollPane sp=new ScrollPane();
			     ObservableList<String> data = FXCollections.observableArrayList();

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
			      Button B1=new Button("get report");
			      B1.setOnAction(new EventHandler<ActionEvent>()
					{
						 @Override
						  public void handle(ActionEvent e)
							  {     
							 if(Question_select!="0")
						     {
						    	 GridPane grid=new GridPane();
						    	 int j=0;
						    	 for(int i=0;i<Main.ExecuteList.size();i++)
						    	 {
						    		 if((Main.ExecuteList.get(i).getTest().getCode().charAt(2)+""+Main.ExecuteList.get(i).getTest().getCode().charAt(3)).equals(Question_select))
						    		 {
						    			 if(Main.ExecuteList.get(i).getSign()==0)
						    			 {
						    				 TitledPane tp = new TitledPane();
						    				 tp.setText(Main.ExecuteList.get(i).getTest().getCode());
						    				 GridPane p=new GridPane();
						    				 int avarage=0;
						    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
						    				 {
						    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
						    				 }
						    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
						    				 p.add(new Label("avarage score: "+avarage),0, 0);
						    				 int median=0;
						    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
						    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
						    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
						    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle-1));
						    			        } else {
						    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle));
						    			        }
						    				 }
						    			     p.add(new Label("median: "+median),0, 1);
						    			     int gradeSpace[]=new int[10];
						    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
						    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
						    			     {
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
						    			    		 gradeSpace[0]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
						    			    		 gradeSpace[1]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
						    			    		 gradeSpace[2]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
						    			    		 gradeSpace[3]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
						    			    		 gradeSpace[4]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
						    			    		 gradeSpace[5]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
						    			    		 gradeSpace[6]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
						    			    		 gradeSpace[7]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
						    			    		 gradeSpace[8]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
		Button B3=new Button("get report by student");
		B3.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {     
				 BorderPane pane=new BorderPane();
			     ScrollPane sp=new ScrollPane();
			     ObservableList<String> data = FXCollections.observableArrayList();

			     ListView<String> listView = new ListView<String>(data);
			     listView.setPrefSize(300, 250);
			     for(int i=0;i<Main.Controller.length;i++)data.add(Main.Controller[i].Owner_name[i]);

			     listView.setItems(data);
			     listView.getSelectionModel().selectedItemProperty().addListener(
			        (ObservableValue<? extends String> ov, String old_val, 
			            String new_val) -> {
			                Question_select=new_val;
			                
			          });
			      sp.setContent(listView);
			      Button B1=new Button("get report");
			      B1.setOnAction(new EventHandler<ActionEvent>()
					{
						 @Override
						  public void handle(ActionEvent e)
							  {     
							 if(Question_select!="0")
						     {
						    	 GridPane grid=new GridPane();
						    	 int j=0;
						    	 for(int i=0;i<Main.ExecuteList.size();i++)
						    	 {
						    		 for(int k=0;k<Main.ExecuteList.get(i).getSignUpList().size();k++){
						    		 if(Main.ExecuteList.get(i).getSignUpList().get(k).equals(Question_select))
						    		 {
						    			 if(Main.ExecuteList.get(i).getSign()==0)
						    			 {
						    				 TitledPane tp = new TitledPane();
						    				 tp.setText(Main.ExecuteList.get(i).getTest().getCode());
						    				 GridPane p=new GridPane();
						    				 int avarage=0;
						    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
						    				 {
						    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
						    				 }
						    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
						    				 p.add(new Label("avarage score: "+avarage),0, 0);
						    				 int median=0;
						    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
						    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
						    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
						    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle-1));
						    			        } else {
						    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle));
						    			        }
						    				 }
						    			     p.add(new Label("median: "+median),0, 1);
						    			     int gradeSpace[]=new int[10];
						    			     for(int d=0;d<10;d++)gradeSpace[d]=0;
						    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
						    			     {
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
						    			    		 gradeSpace[0]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
						    			    		 gradeSpace[1]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
						    			    		 gradeSpace[2]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
						    			    		 gradeSpace[3]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
						    			    		 gradeSpace[4]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
						    			    		 gradeSpace[5]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
						    			    		 gradeSpace[6]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
						    			    		 gradeSpace[7]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
						    			    		 gradeSpace[8]++;
						    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
						    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
		  Button back=new Button("back");
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
    public void handleTimeChangeRequest(int idx , String explenation , String time)
    {
    	GridPane grid=new GridPane();
    	Label L1=new Label("Time change request for test: "+Main.ExecuteList.get(idx).getTest().getCode());
    	grid.add(L1, 0, 0);
        L1=new Label("Reason: "+explenation);
    	grid.add(L1, 0, 1);
    	L1=new Label("time add: "+time);
    	grid.add(L1, 0, 2);
    	Button B1=new Button("accept");
    	B1.setOnAction(new EventHandler<ActionEvent>()
 		{
 			 @Override
 			  public void handle(ActionEvent e)
 				  { 
 				     
 				      for(int i=0;i<Main.ExecuteList.get(idx).getSignUpList().size();i++)
 				      {
 				    	  for(int j=0;j<Main.Controller.length;j++)
 				    	  {
 				    		  if(Main.ExecuteList.get(idx).getSignUpList().get(i).equals(Main.Controller[j].Owner_name[j]))
 				    		  {
 				    			  if(StudentController.closestage.get(i).isShowing())
 				    			  {
 				    				  StudentController.time1[j].setDelay(Duration.minutes
 				    						  (Integer.parseInt(Main.ExecuteList.get(idx).getCurrentTime())+Integer.parseInt(time)));
 				    				  GridPane p=new GridPane();
 				    				  p.add(new Label("test duration was extanded by "+time+" minuts"), 0, 0);
 				    				 Scene scene = new Scene(p,400,200);
 				    				 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 				    				 StudentController.Messagestage.get(j).setScene(scene);
 				    				 StudentController.Messagestage.get(j).setTitle("notification window");
 				    				 StudentController.Messagestage.get(j).show();
 				    			  }
 				    		  }
 				    	  }
 				      }
 				     Main.ExecuteList.get(idx).setCurrentTime(time);
 				  }
 		});
    	grid.add(B1, 0, 3);
    	Button B2=new Button("reject");
    	 B2.setOnAction(new EventHandler<ActionEvent>()
 		{
 			 @Override
 			  public void handle(ActionEvent e)
 				  { 
 				     Manager_change_window.close();
 				  }
 		});
    	grid.add(B2, 1, 3);
    	Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Manager_change_window.setScene(scene);
		Manager_change_window.setTitle("System report view window");
		Manager_change_window.show();
    }
    public void Custom_report()
    {
    	BorderPane pM=new BorderPane();
    	GridPane grid=new GridPane();
    	TitledPane tp = new TitledPane();
	    tp.setText("Select Teacher partion");
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
	    for(int i=0;i<Main.TController.length;i++)
	    {
	    	candidates.add(Main.TController[i].Owner_name[i]);
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
	    tp1.setText("Select Course partion");
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
	    for(int i=0;i<Course_list.size();i++)
	    {
	    	candidates1.add(Course_list.get(i));
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
	    tp2.setText("Select student partion");
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
	    for(int i=0;i<Main.Controller.length;i++)
	    {
	    	candidates2.add(Main.Controller[i].Owner_name[i]);
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
	    Button B1=new Button("get report");
	    B1.setOnAction(new EventHandler<ActionEvent>()
		{
			 @Override
			  public void handle(ActionEvent e)
				  {
				 
				 GridPane grid=new GridPane();
		    	 int j=0;
		    	 if(selected.size()>0)grid.add(new Label("Teacher reports"), 0, 0);
		    	 j++;
		    	 for(int g=0;g<selected.size();g++) {
		    	 for(int i=0;i<Main.ExecuteList.size();i++)
		    	 {
		    		 if(Main.ExecuteList.get(i).getTest().getOwner().equals(selected.get(g)))
		    		 {
		    			 if(Main.ExecuteList.get(i).getSign()==0)
		    			 {
		    				 TitledPane tp = new TitledPane();
		    				 tp.setText(Main.ExecuteList.get(i).getexecuter()+" "+Main.ExecuteList.get(i).getTest().getCode());
		    				 GridPane p=new GridPane();
		    				 int avarage=0;
		    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
		    				 {
		    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
		    				 }
		    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
		    				 p.add(new Label("avarage score: "+avarage),0, 0);
		    				 int median=0;
		    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
		    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
		    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
		    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get((middle)/2));
		    			        } else {
		    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get((middle-1)/2));
		    			        }
		    				 }
		    			     p.add(new Label("median: "+median),0, 1);
		    			     int gradeSpace[]=new int[10];
		    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
		    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
		    			     {
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
		    			    		 gradeSpace[0]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
		    			    		 gradeSpace[1]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
		    			    		 gradeSpace[2]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
		    			    		 gradeSpace[3]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
		    			    		 gradeSpace[4]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
		    			    		 gradeSpace[5]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
		    			    		 gradeSpace[6]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
		    			    		 gradeSpace[7]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
		    			    		 gradeSpace[8]++;
		    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
		    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
		    	 if(selected1.size()>0) grid.add(new Label("Course reports"), 0, j);
		    	 j++;
			    	 for(int g=0;g<selected1.size();g++)
			    	 {
			    	 for(int i=0;i<Main.ExecuteList.size();i++)
			    	 {
			    		 if((Main.ExecuteList.get(i).getTest().getCode().charAt(2)+""+Main.ExecuteList.get(i).getTest().getCode().charAt(3)).equals(selected1.get(g)))
			    		 {
			    			 if(Main.ExecuteList.get(i).getSign()==0)
			    			 {
			    				 TitledPane tp = new TitledPane();
			    				 tp.setText(selected.get(g)+" "+Main.ExecuteList.get(i).getTest().getCode());
			    				 GridPane p=new GridPane();
			    				 int avarage=0;
			    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
			    				 {
			    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
			    				 }
			    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
			    				 p.add(new Label("avarage score: "+avarage),0, 0);
			    				 int median=0;
			    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
			    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
			    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
			    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle-1));
			    			        } else {
			    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle));
			    			        }
			    				 }
			    			     p.add(new Label("median: "+median),0, 1);
			    			     int gradeSpace[]=new int[10];
			    			     for(int k=0;k<10;k++)gradeSpace[k]=0;
			    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
			    			     {
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
			    			    		 gradeSpace[0]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
			    			    		 gradeSpace[1]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
			    			    		 gradeSpace[2]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
			    			    		 gradeSpace[3]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
			    			    		 gradeSpace[4]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
			    			    		 gradeSpace[5]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
			    			    		 gradeSpace[6]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
			    			    		 gradeSpace[7]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
			    			    		 gradeSpace[8]++;
			    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
			    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
			    	 if(selected2.size()>0) grid.add(new Label("Student reports"), 0, j);
			    	 j++;
				    	 for(int g=0;g<selected2.size();g++)
				    	 {
				    		 for(int i=0;i<Main.ExecuteList.size();i++)
					    	 {
					    		 for(int k=0;k<Main.ExecuteList.get(i).getSignUpList().size();k++){
					    		 if(Main.ExecuteList.get(i).getSignUpList().get(k).equals(selected2.get(g)))
					    		 {
					    			 if(Main.ExecuteList.get(i).getSign()==0)
					    			 {
					    				 TitledPane tp = new TitledPane();
					    				 tp.setText(Main.ExecuteList.get(i).getSignUpList().get(k)+" "+Main.ExecuteList.get(i).getTest().getCode());
					    				 GridPane p=new GridPane();
					    				 int avarage=0;
					    				 for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
					    				 {
					    					 avarage+=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b));
					    				 }
					    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0)  avarage=avarage/Main.ExecuteList.get(i).getGradeList().size();
					    				 p.add(new Label("avarage score: "+avarage),0, 0);
					    				 int median=0;
					    				 int middle = Main.ExecuteList.get(i).getGradeList().size();
					    				 if(Main.ExecuteList.get(i).getGradeList().size()!=0) {
					    			        if (Main.ExecuteList.get(i).getGradeList().size() % 2 == 1) {
					    			             median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle-1));
					    			        } else {
					    			           median=Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(middle));
					    			        }
					    				 }
					    			     p.add(new Label("median: "+median),0, 1);
					    			     int gradeSpace[]=new int[10];
					    			     for(int d=0;d<10;d++)gradeSpace[d]=0;
					    			     for(int b=0;b<Main.ExecuteList.get(i).getGradeList().size();b++)
					    			     {
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=10 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>=0)
					    			    		 gradeSpace[0]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=20 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>10)
					    			    		 gradeSpace[1]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=30 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>20)
					    			    		 gradeSpace[2]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=40 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>30)
					    			    		 gradeSpace[3]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=50 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>40)
					    			    		 gradeSpace[4]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=60 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>50)
					    			    		 gradeSpace[5]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=70 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>60)
					    			    		 gradeSpace[6]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=80 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>70)
					    			    		 gradeSpace[7]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=90 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>80)
					    			    		 gradeSpace[8]++;
					    			    	 if(Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))<=100 && 
					    			    			 Integer.parseInt(Main.ExecuteList.get(i).getGradeList().get(b))>90)
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
	    Button B2=new Button("Back");
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
