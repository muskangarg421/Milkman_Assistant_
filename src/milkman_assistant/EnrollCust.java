package milkman_assistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EnrollCust
{
	Connection con;
	PreparedStatement pst;
	
	Text lblN,lblM,lblA,lblL,lblCQ,lblBQ,lblD;
	TextField txtM,txtL,txtCQ,txtBQ;
	TextArea txtA;
	ComboBox<String> comboN;
	//ComboBox<Integer> comboDD,comboMM,comboYY;
	Button btnS,btnN,btnSv,btnU,btnD,btnP;
	GridPane grd;
	HBox h2,h3,h4;
	CheckBox chkC,chkB;
	static Alert alert=new Alert(AlertType.INFORMATION);
	DatePicker dp;
	ImageView lblH,i1,i2,i3,i4,i5,img,img1;
	DropShadow ds;
	ArrayList<String> ary;
	FileChooser f;
	
	public EnrollCust()
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
		Calendar cal=Calendar.getInstance();
		dp=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)));
		
		lblH=new ImageView(new Image(EnrollCust.class.getResourceAsStream("h1.png")));

        lblN=new Text("Name");
        lblN.setFont(Font.font(20));
        lblN.setFill(Color.RED);
		lblM=new Text("Mobile");
		lblM.setFont(Font.font(20));
		lblM.setFill(Color.RED);
		lblA=new Text("Address");
		lblA.setFont(Font.font(20));
		lblA.setFill(Color.RED);
		lblL=new Text("Location/City");
		lblL.setFont(Font.font(20));
		lblL.setFill(Color.RED);
		lblCQ=new Text("Quantity");
		lblCQ.setFont(Font.font(15));
		
		lblBQ=new Text("Quantity");
		lblBQ.setFont(Font.font(15));
		
		lblD=new Text("Date of start");
		lblD.setFont(Font.font(20));
		lblD.setFill(Color.RED);
		
		txtM=new TextField();
		txtM.setPromptText("Enter Mobile no.");
		txtA=new TextArea();
		txtA.setPromptText("Enter Address");
		txtA.setMaxHeight(50);
		txtA.setMaxWidth(200);
		txtL=new TextField();
		txtL.setPromptText("Enter Location");
		txtCQ=new TextField();
		txtCQ.setPromptText("Enter CowMilk quantity");
		txtBQ=new TextField();
		txtBQ.setPromptText("Enter BuffaloMilk quantity");
		
		img=new ImageView(new Image(EnrollCust.class.getResourceAsStream("img.jpg")));
		img.setFitHeight(120);
		img.setFitWidth(100);
		
		img1=new ImageView(new Image(EnrollCust.class.getResourceAsStream("img.jpg")));
		img1.setFitHeight(20);
		img1.setFitWidth(20);
		
		i1=new ImageView(new Image(EnrollCust.class.getResourceAsStream("Button_15-128.png")));
		i1.setFitHeight(20);
		i1.setFitWidth(20);
		
		i2=new ImageView(new Image(EnrollCust.class.getResourceAsStream("add-128.png")));
		i2.setFitHeight(20);
		i2.setFitWidth(20);
		
		i3=new ImageView(new Image(EnrollCust.class.getResourceAsStream("save-icon.png")));
		i3.setFitHeight(20);
		i3.setFitWidth(20);
		
		i4=new ImageView(new Image(EnrollCust.class.getResourceAsStream("refresh-icon-28.png")));
		i4.setFitHeight(20);
		i4.setFitWidth(20);
		
		i5=new ImageView(new Image(EnrollCust.class.getResourceAsStream("blue-delete-button-png-29.png")));
		i5.setFitHeight(20);
		i5.setFitWidth(20);
		
		btnP=new Button("Upload",img1);
		btnS=new Button("Search",i1);
		btnN=new Button("New",i2);
		btnSv=new Button("Save",i3);
		btnU=new Button("Update",i4);
		btnD=new Button("Delete",i5);
		btnN.setPrefSize(100, 40);
		btnP.setPrefSize(100, 40);
		btnS.setPrefSize(100, 40);
		btnSv.setPrefSize(100, 40);
		btnU.setPrefSize(100, 40);
		btnD.setPrefSize(100, 40);
		
		ds=new DropShadow();
		
		img.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				img.setEffect(ds);
			}
			
		});
		img.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				img.setEffect(null);
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
		
		btnS.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnS.setEffect(ds);
			}
			
		});
		btnS.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnS.setEffect(null);
			}
			
		});
		btnN.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnN.setEffect(ds);
			}
			
		});
		btnN.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnN.setEffect(null);
			}
			
		});
		btnSv.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnSv.setEffect(ds);
			}
			
		});
		btnSv.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnSv.setEffect(null);
			}
			
		});
		btnU.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnU.setEffect(ds);
			}
			
		});
		btnU.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnU.setEffect(null);
			}
			
		});
		btnD.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnD.setEffect(ds);
			}
			
		});
		btnD.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				btnD.setEffect(null);
			}
			
		});
		
		comboN=new ComboBox<String>();
		comboN.setEditable(true);
		comboN.setPromptText("Name");
		comboN.getSelectionModel().select(0);
		comboN.setMaxWidth(200);
		fillName();
	
		h2=new HBox();
		h2.setSpacing(20);
		h2.getChildren().addAll(lblCQ,txtCQ);
		
		h3=new HBox();
		h3.setSpacing(20);
		h3.getChildren().addAll(lblBQ,txtBQ);
		
		h4=new HBox();
		h4.setSpacing(50);
		h4.getChildren().addAll(btnN,btnSv,btnU,btnD);
		
		txtCQ.setDisable(true);
		txtBQ.setDisable(true);
		
		chkC=new CheckBox("Cow");
		chkC.setFont(Font.font(15));
		chkB=new CheckBox("Buffalo");
		chkB.setFont(Font.font(15));
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setPadding(new Insets(20));
		grd.setAlignment(Pos.CENTER);
		grd.setGridLinesVisible(false);
		
		GridPane.setConstraints(lblH, 0, 0, 4, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(20,20,20,80));
		grd.getChildren().add(lblH);
		GridPane.setConstraints(lblN, 0, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(lblN);
		GridPane.setConstraints(lblM, 0, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(lblM);
		GridPane.setConstraints(lblA, 0, 3, 1, 1, HPos.CENTER,VPos.CENTER, null,null,new Insets(10));
		grd.getChildren().add(lblA);
		GridPane.setConstraints(lblL, 0, 4, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(lblL);
		GridPane.setConstraints(lblD, 0, 7, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(lblD);
		
		GridPane.setConstraints(txtM, 1, 2, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(txtM);
		GridPane.setConstraints(txtA, 1, 3, 1, 1, HPos.CENTER,VPos.CENTER, null,null,new Insets(10));
		grd.getChildren().add(txtA);
		GridPane.setConstraints(txtL, 1, 4, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(txtL);
		
		GridPane.setConstraints(dp, 1, 7, 2, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(dp);
		GridPane.setConstraints(h2, 1, 5, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(h2);
		GridPane.setConstraints(h3, 1, 6, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(h3);
		GridPane.setConstraints(h4, 0, 8, 5, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(h4);
		
		GridPane.setConstraints(btnS, 2, 1, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(btnS);
		GridPane.setConstraints(btnP, 2, 5, 1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(btnP);
		
		GridPane.setConstraints(comboN, 1, 1,1, 1, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(comboN);
		
		GridPane.setConstraints(chkC, 0, 5, 1, 1, HPos.LEFT,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(chkC);
		GridPane.setConstraints(chkB, 0, 6, 1, 1, HPos.LEFT,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(chkB);
		
		GridPane.setConstraints(img, 2, 2, 2, 3, HPos.CENTER,VPos.CENTER, null, null,new Insets(10));
		grd.getChildren().add(img);
		
		chkC.setOnAction(e->docheckC());
		chkB.setOnAction(e->docheckB());
		
		btnS.setOnAction(e->doSearch());
		btnSv.setOnAction(e->doSave());
		btnN.setOnAction(e->doNew());
		btnD.setOnAction(e->doDelete());
		btnU.setOnAction(e->doUpdate());
		
		Scene scene=new Scene(grd,1200,600);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		btnP.setOnAction(e->doUpload(stage));
		comboN.requestFocus();
		
	}
	void docheckC()
	{
			if(chkC.isSelected())
			{
				txtCQ.setDisable(false);
			}		
	}
	void docheckB()
	{
			if(chkB.isSelected())
			{
				txtBQ.setDisable(false);
			}
			
				
	}
	void fillName()
	{
		ary=new ArrayList<String>();
		try {
			pst=con.prepareStatement("select distinct cname from customers");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String u=rs.getString("cname");
				ary.add(u);
			}
			comboN.getItems().addAll(ary);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doSearch()
	{
		try 
		{
			pst=con.prepareStatement("select * from customers where cname=?");
			pst.setString(1, comboN.getSelectionModel().getSelectedItem());
			
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				String mob=rs.getString("mob");
				String adr=rs.getString("adr");
				String loc=rs.getString("loc");
				String cowq=rs.getString("cowq");
				String bufq=rs.getString("bufq");
				Date date=rs.getDate("dos");
				String pic=rs.getString("pic");
				
				/*String d[]=date.split("/");
				System.out.println(d[0]+" "+d[1]+" "+d[2]+" ");*/
				
				txtM.setText(mob);
				txtA.setText(adr);
				txtL.setText(loc);
				txtCQ.setText(cowq);
				txtBQ.setText(bufq);
				dp.setValue(date.toLocalDate());
				if(pic==null)
				{
					image=new Image(EnrollCust.class.getResourceAsStream("img.jpg"));	
				}
				else
					image=new Image(new FileInputStream(pic));
				
				img.setImage(image);
				
				/*comboDD.getSelectionModel().select(Integer.parseInt(d[0])-1);
				comboMM.getSelectionModel().select(Integer.parseInt(d[1])-1);
				System.out.println(d[2]);
				comboYY.getSelectionModel().select(Integer.parseInt(d[1])-1);*/
				
			}
			else
				System.out.println("Invalid name");
			} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	void doNew()
	{
		comboN.getSelectionModel().select(null);
		txtM.clear();
		txtA.clear();
		txtL.clear();
		txtCQ.clear();
		txtBQ.clear();
		dp.setValue(null);
		chkB.setSelected(false);
		chkC.setSelected(false);
		image=new Image(EnrollCust.class.getResourceAsStream("img.jpg"));
		img.setImage(image);
	}
	void doSave()
	{
		LocalDate local=dp.getValue();
		Date date=Date.valueOf(local);
		int i=0;
		if(comboN.getSelectionModel().getSelectedItem()==null)
		{
			doShow("Plzz select customer name!");
		}
		else{try 
		{
			for(String s: ary)
			{
				if(comboN.getSelectionModel().getSelectedItem().equals(s))
				{
					doShow("This customer already exists!");
					i=1;
				}
			}
			if(i==0)
			{
				if(txtM.getText().equals("") && txtA.getText().equals("") && txtL.getText().equals(""))
				{
					doShow("Plzz enter all the details!!");
				}
				else
				{
				pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
				pst.setString(1, comboN.getSelectionModel().getSelectedItem());
				pst.setString(2, txtM.getText());
				pst.setString(3, txtA.getText());
				pst.setString(4, txtL.getText());
				
				if(chkC.isSelected())
				{
					pst.setString(5, txtCQ.getText());
				}
				else
					pst.setString(5,"0");
				
				if(chkB.isSelected())
				{
					pst.setString(6, txtBQ.getText());
				}
				else
					pst.setString(6,"0");
				pst.setDate(7, date);
				pst.setString(8, s1);
				
				pst.executeUpdate();
				
				System.out.println("Record Saved");
				doShow("Record Saved...");
				fillName();
				comboN.getItems().clear();
				doNew();
				fillName();
				}
			}
			else
			{
				doNew();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		}
	}
	void doDelete()
	{
		try 
		{
			pst=con.prepareStatement("delete from customers where cname=?");
			
			pst.setString(1, comboN.getSelectionModel().getSelectedItem());
			
			int k=pst.executeUpdate();
			if(k==0)
			{
				System.out.println("Invalid Name");
				doShow("Invalid Name");
			}
			else
			{
				System.out.println("Record deleted");
				doShow("Record deleted");
			}
			fillName();
			comboN.getItems().clear();
			doNew();
			fillName();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void doUpdate()
	{
		LocalDate local=dp.getValue();
		Date date=Date.valueOf(local);
		try 
		{
			pst=con.prepareStatement("update customers set mob=?, adr=?, loc=?, cowq=?, bufq=?, dos=?, pic=? where cname=?");
			
			//String d=comboDD.getSelectionModel().getSelectedItem()+"//"+comboMM.getSelectionModel().getSelectedItem()+"//"+comboYY.getSelectionModel().getSelectedItem();
			
			pst.setString(1, txtM.getText());
			pst.setString(2, txtA.getText());
			pst.setString(3, txtL.getText());
			pst.setString(4, txtCQ.getText());
			pst.setString(5, txtBQ.getText());
			pst.setDate(6,date);
			pst.setString(7, s1);
			pst.setString(8, comboN.getSelectionModel().getSelectedItem());
			
			int k=pst.executeUpdate();
			if(k==0)
			{
				System.out.println("Invalid Name");
				doShow("Invalid Name");
			}
			else
			{
				System.out.println("Record updated");
				doShow("Record updated");
			}
			fillName();
			comboN.getItems().clear();
			doNew();
			fillName();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	String s1=null;
	Image image;
	void doUpload(Stage stage)
	{
		f=new FileChooser();
		f.setTitle("Open Resource File");
		File f1=f.showOpenDialog(stage);
		if(f1==null)
		{
		img.setImage(null);
		return;
		}
		s1=new String(f1.getAbsolutePath());
		try {
			image = new Image(new FileInputStream(f1.getAbsolutePath()));
			img.setImage(image);
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}
	static void doShow(String msg)
	{
		alert.setContentText(msg);
		alert.show();
	}
}

