package milkman_assistant;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About 
{
	Text lblH,lblM,lblS,lblG;
	ImageView imgM,imgS,h;
	GridPane grd;
	About()
	{
		lblH=new Text("Developed By");
		lblH.setFont(Font.font("Broadway",FontWeight.BOLD, 20));
		lblM=new Text("Name: \t\t\t\tMuskan Garg\nRollNo.: \t\t\t\t15103056\nBranch: \t\t\t\tCSE\nMobNo.:\t\t\t\t8054264627\n");
		lblS=new Text("Name:\t\t\t\tRajesh K. Bansal\nAcademic Qualification:  M.Sc(IT), MCA (Master Of Computer Application)\nTraining Exp.:\t\t\t16yrs\nExpert In:\t\t\t\tCore & Advance Java, Android, Spring, Hibernate, PHP, AngularJS, Node.js, C++, etc\nSun Certified:       \t\tSun Certified Java Programmer\nMicrosoft Certified:       \tMicrosoft Certified Specialist\nFounder & Director of: \tBanglore Computer Education\nTraining Head at: \t\tSun-Soft Technologies\nAuthor Of Book: \t\tReal Java\n");
		lblG=new Text("Under the guidance of");
		lblG.setFont(Font.font("Broadway",FontWeight.BOLD, 20));
		
		h=new ImageView(new Image(About.class.getResourceAsStream("hAboutme.png")));
		h.setFitHeight(100);
		h.setFitWidth(300);
		
		imgM=new ImageView(new Image(About.class.getResourceAsStream("add-128.png")));
		imgM.setFitHeight(150);
		imgM.setFitWidth(150);
		
		imgS=new ImageView(new Image(About.class.getResourceAsStream("itsme.jpg")));
		imgS.setFitHeight(150);
		imgS.setFitWidth(150);
		
		grd=new GridPane();
		grd.setVgap(5);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setAlignment(Pos.CENTER);
		
		GridPane.setConstraints(h, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(h);
		GridPane.setConstraints(lblH, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(lblH);
		GridPane.setConstraints(imgM, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(imgM);
		GridPane.setConstraints(lblM, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(lblM);
		GridPane.setConstraints(lblG, 0, 3, 2, 1, HPos.CENTER, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(lblG);
		GridPane.setConstraints(imgS, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(imgS);
		GridPane.setConstraints(lblS, 1, 4, 1, 1, HPos.LEFT, VPos.CENTER	, null, null, new Insets(10));
		grd.getChildren().add(lblS);
		
		Scene scene=new Scene(grd,800,700);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
		
	}
}
