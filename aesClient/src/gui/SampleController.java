package gui;

import java.util.ArrayList;
import client.Client;
import question.Course;
import question.Question;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SampleController {
	Course L = null;
	private Stage primaryStage;
	private ArrayList<Question> Q_list;
	private ScrollPane Scroll;
	public String Question_select="0";
	int start=0;
	Question Q_Update;
	int idx_C;
	public void Q_print()
	{
		Client client = new Client(Main.HOST_IP,Main.HOST_PORT);		
		if(start==0) {
			Q_list = client.getAllQuestion();
			start++;
		}
		primaryStage=new Stage();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Q.fxml"));
			Scroll=addScrollPane();
			root.setCenter(Scroll);
			Button B1,B2;
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
			    	
                    Label L1=new Label("instructions");
                    Text_edit.add(L1, 0, 0);
                    TextArea F1=new TextArea();
                    F1.setPrefColumnCount(30);
                    F1.setPrefRowCount(2);
                    F1.setText(Q_list.get(idx_C).getInstruction());
                    Text_edit.add(F1, 1,0);
                    Label L2=new Label("Question");
                    Text_edit.add(L2, 0, 1);
                    TextArea F2=new TextArea();
                    F2.setPrefColumnCount(30);
                    F2.setPrefRowCount(5);
                    F2.setText(Q_list.get(idx_C).getBody());
                    Text_edit.add(F2, 1,1);
                    Label L3=new Label("Answer 1");
                    Text_edit.add(L3, 0, 2);
                    TextArea F3=new TextArea();
                    F3.setPrefColumnCount(30);
                    F3.setPrefRowCount(1);
                    F3.setText(Q_list.get(idx_C).getAnswer1());
                    Text_edit.add(F3, 1,2);
                    Label L4=new Label("Answer 2");
                    Text_edit.add(L4, 0, 3);
                    TextArea F4=new TextArea();
                    F4.setPrefColumnCount(30);
                    F4.setPrefRowCount(1);
                    F4.setText(Q_list.get(idx_C).getAnswer2());
                    Text_edit.add(F4, 1,3);
                    Label L5=new Label("Answer 3");
                    Text_edit.add(L5, 0, 4);
                    TextArea F5=new TextArea();
                    F5.setPrefColumnCount(30);
                    F5.setPrefRowCount(1);
                    F5.setText(Q_list.get(idx_C).getAnswer3());
                    Text_edit.add(F5, 1,4);
                    Label L6=new Label("Answer 4");
                    Text_edit.add(L6, 0, 5);
                    TextArea F6=new TextArea();
                    F6.setPrefColumnCount(30);
                    F6.setPrefRowCount(1);
                    F6.setText(Q_list.get(idx_C).getAnswer4());
                    Text_edit.add(F6, 1,5);
                    Label L7=new Label("Correct");
                    Text_edit.add(L7, 0, 6);
                    TextArea F7=new TextArea();
                    F7.setPrefColumnCount(1);
                    F7.setPrefRowCount(1);
                    F7.setText(Q_list.get(idx_C).getCorrect()+"");
                    Text_edit.add(F7, 1,6);
                    Button B3,B4;
                    B3=new Button("Cancel");
        			B3.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
        			    	   primaryStage.close();
        			    	   Q_print();
        			      }
        			       
        		    });
        			B4=new Button("Submit");
        			B4.setOnAction(new EventHandler<ActionEvent>()
        		    {
        			       @Override
        			       public void handle(ActionEvent e)
        				  {
        			    	   if(F1.getText().length()>0) Q_list.get(idx_C).setInstruction(F1.getText());
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
        			    	   }
        			    	   client.modifyQuestion(Q_list.get(idx_C));
        			    	   primaryStage.close();
        			    	   Q_print();
        			    	  
        			    	   
        			      }
        			       
        		    });
        			Text_edit.add(B3, 0, 7);
        			Text_edit.add(B4, 1, 7);
			    	Scene scene2 = new Scene(Text_edit,600,600);
			    	primaryStage.setScene(scene2);
					primaryStage.show();
					}
			      }
			       
		    });
			VBox buttons = new VBox();
			// Add the Buttons to the VBox
			buttons.getChildren().addAll(B1,B2);
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
				txt5 = null,txt6 = null,txt7 = null,txt8 = null,txt9 = null;
		 txt1=new Text("Question "+Question_select+":\n");
		txt1.setFont(new Font(15));
	    for(int i=0;i<Q_list.size();i++)
	    {
	    	if(Question_select==Q_list.get(i).getCode())Q_view=Q_list.get(i);
	    }
		 txt2=new Text("Written by: "+Q_view.getOwner()+"\n");
		txt2.setFont(new Font(15));
		 txt3=new Text("Instructions: "+Q_view.getInstruction()+"\n");
		txt3.setFont(new Font(15));
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
		list.addAll(txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9);
		Scene do1=new Scene(Flowtxt);
		Stage board=new Stage();
		board.setScene(do1);
		board.show();
	}
	public void Back_button_Press(ActionEvent event) {
		Stage closer=(Stage) ((Node) event.getSource()).getScene().getWindow();
		closer.close();
	}
}
