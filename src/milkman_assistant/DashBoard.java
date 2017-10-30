package milkman_assistant;

import java.net.URL;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashBoard
{
	ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i,h,home;
	Text l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	VBox v1,v2,v3,v4,v5,v6,v7,v8,v9;
	DropShadow ds;
	GridPane grd;
	Stage stage;
	URL url;
	Media media;
	MediaPlayer mp;
	
	DashBoard()
	{
		l1=new Text("   Enroll Customer");
		l2=new Text("\t   Daily Update");
		l3=new Text("Bill Generator");
		l4=new Text(" Bill Collector");
		l5=new Text("Bill Watcher");
		l6=new Text("Customer Detail");
		l7=new Text("Routine History");
		l8=new Text("Date History");
		l9=new Text("    About Me");
		l1.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l2.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l3.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l4.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l5.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l6.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l7.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l8.setFont(Font.font("Arial",FontWeight.BOLD,15));
		l9.setFont(Font.font("Arial",FontWeight.BOLD,15));
		
		h=new ImageView(new Image(DashBoard.class.getResourceAsStream("hDB.png")));
		h.setFitWidth(400);
		h.setFitHeight(90);
		
		home=new ImageView(new Image(DashBoard.class.getResourceAsStream("home.png")));
		home.setFitWidth(100);
		home.setFitHeight(90);
		
		i=new ImageView(new Image(DashBoard.class.getResourceAsStream("head.png")));
		i.setFitWidth(300);
		i.setFitHeight(90);
		
		i1=new ImageView(new Image(DashBoard.class.getResourceAsStream("enroll2.png")));
		i1.setFitWidth(150);
		i1.setFitHeight(90);
		
		i2=new ImageView(new Image(DashBoard.class.getResourceAsStream("dailyupdate.png")));
		i2.setFitWidth(200);
		i2.setFitHeight(100);
		
		i3=new ImageView(new Image(DashBoard.class.getResourceAsStream("bill.png")));
		i3.setFitWidth(100);
		i3.setFitHeight(80);
		
		i4=new ImageView(new Image(DashBoard.class.getResourceAsStream("Bill-Pay.png")));
		i4.setFitWidth(100);
		i4.setFitHeight(100);
		
		i5=new ImageView(new Image(DashBoard.class.getResourceAsStream("billwatcher.png")));
		i5.setFitWidth(100);
		i5.setFitHeight(100);
		
		i6=new ImageView(new Image(DashBoard.class.getResourceAsStream("customerview.png")));
		i6.setFitWidth(100);
		i6.setFitHeight(100);
		
		i7=new ImageView(new Image(DashBoard.class.getResourceAsStream("routine.png")));
		i7.setFitWidth(120);
		i7.setFitHeight(80);
		
		i8=new ImageView(new Image(DashBoard.class.getResourceAsStream("datehistory.png")));
		i8.setFitWidth(100);
		i8.setFitHeight(100);
		
		i9=new ImageView(new Image(DashBoard.class.getResourceAsStream("aboutme.png")));
		i9.setFitWidth(150);
		i9.setFitHeight(90);
				
		ds=new DropShadow();
		
		home.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				home.setEffect(ds);
			}
			
		});
		home.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				home.setEffect(null);
			}
			
		});
		i1.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i1.setEffect(ds);
			}
			
		});
		i1.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i1.setEffect(null);
			}
			
		});
		i2.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i2.setEffect(ds);
			}
			
		});
		i2.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i2.setEffect(null);
			}
			
		});
		i3.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i3.setEffect(ds);
			}
			
		});
		i3.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i3.setEffect(null);
			}
			
		});
		i4.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i4.setEffect(ds);
			}
			
		});
		i4.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i4.setEffect(null);
			}
			
		});
		i5.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i5.setEffect(ds);
			}
			
		});
		i5.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i5.setEffect(null);
			}
			
		});
		i6.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i6.setEffect(ds);
			}
			
		});
		i6.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i6.setEffect(null);
			}
			
		});
		i7.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i7.setEffect(ds);
			}
			
		});
		i7.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i7.setEffect(null);
			}
			
		});
		i8.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i8.setEffect(ds);
			}
			
		});
		i8.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i8.setEffect(null);
			}
			
		});
		i9.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i9.setEffect(ds);
			}
			
		});
		i9.setOnMouseExited(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event) 
			{
				i9.setEffect(null);
			}
			
		});
		v1=new VBox();
		v1.setSpacing(5);
		v1.getChildren().addAll(i1,l1);
		
		v2=new VBox();
		v2.setSpacing(5);
		v2.getChildren().addAll(i2,l2);
		
		v3=new VBox();
		v3.setSpacing(5);
		v3.getChildren().addAll(i3,l3);
		
		v4=new VBox();
		v4.setSpacing(5);
		v4.getChildren().addAll(i4,l4);
		
		v5=new VBox();
		v5.setSpacing(5);
		v5.getChildren().addAll(i5,l5);
		
		v6=new VBox();
		v6.setSpacing(5);
		v6.getChildren().addAll(i6,l6);
		
		v7=new VBox();
		v7.setSpacing(5);
		v7.getChildren().addAll(i7,l7);
		
		v8=new VBox();
		v8.setSpacing(10);
		v8.getChildren().addAll(i8,l8);
		
		v9=new VBox();
		v9.setSpacing(5);
		v9.getChildren().addAll(i9,l9);
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setStyle("-fx-background-color:THISTLE");
		grd.setAlignment(Pos.CENTER);
		
		GridPane.setConstraints(h, 2, 1, 7, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20));
		grd.getChildren().add(h);
		GridPane.setConstraints(i, 3, 2, 5, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(i);
		GridPane.setConstraints(v1, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v1);
		GridPane.setConstraints(v2, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v2);
		GridPane.setConstraints(v3, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v3);
		GridPane.setConstraints(v4, 4, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v4);
		GridPane.setConstraints(v5, 5, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v5);
		GridPane.setConstraints(v6, 6, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v6);
		GridPane.setConstraints(v7, 7, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v7);
		GridPane.setConstraints(v8, 8, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v8);
		GridPane.setConstraints(v9, 9, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(v9);
		GridPane.setConstraints(home, 9, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(home);
		
		i1.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new EnrollCust();
		});
		i2.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new DailyUpdate();
		});
		i3.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new Bill();
		});
		i4.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new BillCollector();
		});
		i5.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new BillWatcher();
		});
		i6.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new CustDetail();
		});
		i7.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new RoutineHistory();
		});
		i8.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new DateHistory();
		});
		i9.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			new About();
		});
		home.setOnMouseClicked(e->{
			url=getClass().getResource("button.wav");
			media=new Media(url.toString());
			mp=new MediaPlayer(media);
			mp.play();
			stage.hide();
		});
		
		Scene scene=new Scene(grd,2000,700);
		stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
	}
	
}
