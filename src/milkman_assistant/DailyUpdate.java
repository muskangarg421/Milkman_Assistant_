package milkman_assistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DailyUpdate
{
	Connection con;
	PreparedStatement pst,pst1;
	
	Text lblCQ,lblBQ;
	TextField txtCQ,txtBQ;
	ListView<String> lst;
	HBox h1;
	VBox v1,v2;
	GridPane grd;
	Alert alert;
	ArrayList<String> ary=new ArrayList<String>();
	ImageView lblH,btnF,btnM,btnP;
	DropShadow ds;
	
	public DailyUpdate()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/milkman","root","bce");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		lblH=new ImageView(new Image(DailyUpdate.class.getResourceAsStream("cooltext250855961867084.png")));
		
		lblCQ=new Text("       CowQuantity");
		lblCQ.setFont(Font.font(15));
		lblBQ=new Text("     BuffaloQuantity");
		lblBQ.setFont(Font.font(15));
		
		txtCQ=new TextField();
		txtCQ.setPromptText("Enter CowMilk quantity");
		txtBQ=new TextField();
		txtBQ.setPromptText("Enter BuffaloMilk quantity");
		
		btnF=new ImageView(new Image(DailyUpdate.class.getResourceAsStream("pse.png")));
		btnM=new ImageView(new Image(DailyUpdate.class.getResourceAsStream("missed.png")));
		btnP=new ImageView(new Image(DailyUpdate.class.getResourceAsStream("post.png")));
		
		ds=new DropShadow();
		
		btnF.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnF.setEffect(ds);
			}
			
		});
		btnF.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnF.setEffect(null);
			}
			
		});
		
		btnM.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnM.setEffect(ds);
			}
			
		});
		btnM.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnM.setEffect(null);
			}
			
		});
		
		btnP.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnP.setEffect(ds);
			}
			
		});
		btnP.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnP.setEffect(null);
			}
			
		});
		
		v1=new VBox();
		v1.setSpacing(10);
		v1.getChildren().addAll(lblCQ,txtCQ);
		
		v2=new VBox();
		v2.setSpacing(10);
		v2.getChildren().addAll(lblBQ,txtBQ);
		
		h1=new HBox();
		h1.setSpacing(20);
		h1.getChildren().addAll(v1,v2);
		
		lst=new ListView<String>();
		lst.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		fillName();
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setAlignment(Pos.CENTER);
		grd.setPadding(new Insets(30));
		
		GridPane.setConstraints(lblH, 0, 0, 2, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(lblH);
		GridPane.setConstraints(btnF, 1, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(btnF);
		GridPane.setConstraints(btnM, 1, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(btnM);
		GridPane.setConstraints(btnP, 1, 5, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(btnP);
		
		GridPane.setConstraints(h1, 1, 3, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(h1);
		
		GridPane.setConstraints(lst, 0, 1, 1, 7, HPos.CENTER,VPos.CENTER, null, null,new Insets(5));
		grd.getChildren().add(lst);
		
		btnF.setOnMouseReleased(e->doFull());
		btnM.setOnMouseReleased(e->doMiss());
		btnP.setOnMouseReleased(e->doPost());
	
		Scene scene=new Scene(grd,700,600);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		doDblClick();
	}
	void doDblClick()
	{
		lst.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2)
				{
					System.out.println(lst.getSelectionModel().getSelectedItem());
					try {
						pst=con.prepareStatement("select * from customers where cname=?");
						pst.setString(1, lst.getSelectionModel().getSelectedItem());
						ResultSet rs=pst.executeQuery();
						while(rs.next())
						{
							txtCQ.setText(rs.getString("cowq"));
							txtBQ.setText(rs.getString("bufq"));
						}						
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
	}
	void fillName()
	{
		
		try {
			pst=con.prepareStatement("select distinct cname from customers");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String u=rs.getString("cname");
				ary.add(u);
			}
			lst.getItems().addAll(ary);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doFull()
	{
		ObservableList<String> obs=lst.getSelectionModel().getSelectedItems();
		ary.removeAll(obs);
		System.out.println(ary);
		try 
		{
			for(String s:ary)
			{
				pst=con.prepareStatement("insert into dailyupdate values(?,curdate(),?,?)");
				pst.setString(1, s);
				pst1=con.prepareStatement("select * from customers where cname=?");
				pst1.setString(1, s);
				ResultSet rs=pst1.executeQuery();
				if(rs.next())
				{
					float c=rs.getFloat("cowq");
					float b=rs.getFloat("bufq");
				
					pst.setFloat(2, c);
					pst.setFloat(3, b);
				}
				pst.executeUpdate();
			}
			
			lst.getItems().removeAll(ary);
			System.out.println("Record saved");
			doShow("Record Saved");
		} 
		catch (SQLException e)
		{	
			e.printStackTrace();
		}
		
	}
	void doMiss()
	{
		ObservableList<String> obs=lst.getSelectionModel().getSelectedItems();
		lst.getItems().removeAll(obs);
		System.out.println("Record saved");
		doShow("Record Saved");
	}
	void doPost()
	{
		if(txtCQ.getText().equals(""))
		{
			 doShow("Plzz enter cow quantity");
		}
		else
			if(txtBQ.getText().equals(""))
			{
				 doShow("Plzz enter buffalo quantity");
			}
			else
			{
				try 
				{
					ObservableList<String> obs=lst.getSelectionModel().getSelectedItems();
					pst=con.prepareStatement("insert into dailyupdate values(?,curdate(),?,?)");
					pst.setString(1, lst.getSelectionModel().getSelectedItem());
					pst.setString(2, txtCQ.getText());
					pst.setString(3, txtBQ.getText());
					
					
					pst.executeUpdate();
					lst.getItems().removeAll(obs);
					System.out.println("Record saved");
					doShow("Record Saved");
					txtCQ.clear();
					txtBQ.clear();
				}
				catch (SQLException e)
				{	
					e.printStackTrace();
				}
			}
	}
	void doShow(String msg)
	{
		alert=new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
}
