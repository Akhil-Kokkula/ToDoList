import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.*; 
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.scene.control.ChoiceBox;




public class ToDoList extends Application {
    
    private ArrayList<Scene> scenes = new ArrayList<Scene>(); 
    private String choosen;
    private ScrollPane sp = new ScrollPane();
    private ScrollPane list = new ScrollPane();
    private ArrayList<Button> categoryList = new ArrayList<Button>();
    private String nameOfBtn;
    private int daySelected;
    private String monthSelected;
    private int yearSelected;
    private String chooseImportant;
    private int listImportant = 100;
    private String forImportant; 
    private int monthNumSelected;
    private String titleOfTask;
    private String descriptionOfTask;
    private ArrayList<Button> buttonStorage = new ArrayList<Button>();
    private HashMap<Button, VBox> buttonVBox = new HashMap<Button, VBox>();
    private HashMap<Button, Organize> buttonOrganize = new HashMap<Button, Organize>();
    private HashMap<Button, String> otherButtonName = new HashMap<Button, String>();
    private String storeRbRemove;
    private int storeOccr;
    ArrayList<ListInfo> listTask = new ArrayList<ListInfo>();
    private Button recentButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("To Do List");
        //top,center,bottom
        BorderPane border = new BorderPane();
        
        //Center
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20,0,0,20));
        vbox.setSpacing(5);
       
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setContent(vbox);

        //top
        GridPane topPane = new GridPane();
        Label topTitle = new Label("To Do List");
        topTitle.setFont(Font.font("Sans Serfif", 20));
        GridPane.setHalignment(topTitle, HPos.CENTER);
        topPane.setHgap(5);
        topPane.setVgap(5);
        topPane.setMinSize(350,50);
        topPane.add(topTitle,25,3);

        //bottom 
        HBox bottom = new HBox();
        bottom.getStyleClass().add("hbox");
        bottom.setId("hbox-custom");
        FileInputStream plus = new FileInputStream("plus.png");
        Image sign = new Image(plus);
        ImageView displayAdd = new ImageView(sign);
        FileInputStream minus = new FileInputStream("minus.png");
        Image remSign = new Image(minus);
        ImageView displayRemove = new ImageView(remSign);
        Button remove = new Button();
        remove.setGraphic(displayRemove);
        Button add = new Button();
        add.setGraphic(displayAdd);
        
        bottom.getChildren().addAll(remove, add);
        bottom.setPadding(new Insets(5,5,5,5));
        bottom.setSpacing(230);
        bottom.setStyle("-fx-background-color: #dcdcdc;") ;

        //setting up borderpane
        border.setTop(topPane);
        border.setCenter(sp);
        border.setBottom(bottom);
        
        //homeScene
        Scene homeScene = new Scene(border,350,500);
        scenes.add(homeScene);
        homeScene.getStylesheets().add("HomeStyle.css");
        primaryStage.setScene(homeScene);
        primaryStage.show(); 

        
        
        //BorderPane for listScreen
        BorderPane borderList = new BorderPane();
        
        //center
        VBox listBox = new VBox();
        listBox.setPadding(new Insets(20,0,0,20));
        listBox.setSpacing(10);
        listBox.setMinSize(350,50);

        list.setHbarPolicy(ScrollBarPolicy.NEVER);
        list.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        list.setContent(listBox);

        //top
        GridPane categoryPane = new GridPane();
        Label categoryTitle = new Label();
        Button removeItems = new Button("Clear All Tasks");
        categoryTitle.setFont(Font.font("Sans Serfif", 20));
        GridPane.setHalignment(categoryTitle, HPos.CENTER);
        categoryPane.setHgap(5);
        categoryPane.setVgap(5);
        categoryPane.setMinSize(350,50);
        categoryPane.add(categoryTitle,4,5);
        categoryPane.add(removeItems, 25,5);
        
        //bottom
        HBox addItem = new HBox();
        addItem.getStyleClass().add("hbox");
        addItem.setId("hbox-custom");
        FileInputStream plusList = new FileInputStream("plus.png");
        Image signList = new Image(plusList);
        ImageView displayAddList = new ImageView(signList);
        Button addList = new Button();
        addList.setGraphic(displayAddList);
        FileInputStream back = new FileInputStream("back.png");
        Image backSign = new Image(back);
        ImageView displayBack = new  ImageView(backSign); 
        Button goBack = new Button();
        goBack.setGraphic(displayBack);
        addItem.getChildren().addAll(goBack,addList);
        addItem.setPadding(new Insets(5,5,5,5));
        addItem.setSpacing(230);
        addItem.setStyle("-fx-background-color: #dcdcdc;") ;

        //setting up borderPaneList
        borderList.setTop(categoryPane);
        borderList.setBottom(addItem);

        //listScene
        Scene listScene = new Scene(borderList, 350, 500);
        scenes.add(listScene);
        listScene.getStylesheets().add("HomeStyle.css");
        
        remove.setOnAction(r -> {
            BorderPane removeSetUp = new BorderPane();
            VBox rbCategories = new VBox();
            GridPane forTitle = new GridPane();
            Label title = new Label("Which One Would You Like To Remove?");
            title.setFont(Font.font("Sans Serfif", 14));
            forTitle.add(title,0,0);
            title.setTextAlignment(TextAlignment.JUSTIFY);
            title.setWrapText(true);
            rbCategories.getChildren().add(title);
            rbCategories.setPadding(new Insets(20,20,20,20));
            rbCategories.setSpacing(20);
            ToggleGroup tg = new ToggleGroup();
            for(int i = 0; i < buttonStorage.size(); i++) {
                RadioButton cb = new RadioButton(buttonStorage.get(i).getText());
                cb.setToggleGroup(tg);
                rbCategories.getChildren().add(cb);
                final int index = i;
                tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    public void changed(ObservableValue<? extends Toggle> ov,
                      Toggle old_val, Toggle new_val) {
                        if(cb.isSelected()) {
                            storeRbRemove = cb.getText();
                            storeOccr = index;
                            System.out.println(storeRbRemove);  
                        }   
                   }
                 });           
            }
            ScrollPane scroll = new ScrollPane();
            scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
            scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            scroll.setContent(rbCategories);   
            GridPane forDone = new GridPane();             
            Button doneRem = new Button("Done");
            forDone.setPadding(new Insets(20,20,20,20));
            forDone.setHgap(10);
            forDone.setVgap(10);
            forDone.add(doneRem, 10, 1);
            removeSetUp.setTop(forTitle);
            removeSetUp.setCenter(scroll);
            removeSetUp.setBottom(forDone);
            Scene removeList = new Scene(removeSetUp, 310, 350);
            removeList.getStylesheets().add("HomeStyle.css");
            Stage disRemove = new Stage();
            disRemove.setTitle("Remove a Category");
            disRemove.setScene(removeList);
            disRemove.show();  
            
            doneRem.setOnAction(d -> {
                for(int i = 0; i < buttonStorage.size(); i++) {
                    if(buttonStorage.get(i).getText().equals(storeRbRemove) && storeOccr == i) {
                        buttonVBox.remove(buttonStorage.get(i));
                        buttonOrganize.remove(buttonStorage.get(i));
                        otherButtonName.remove(buttonStorage.get(i));
                        buttonStorage.remove(i);   
                    }
                }
                vbox.getChildren().clear();

                for(int i = 0; i < buttonStorage.size(); i++) {
                    vbox.getChildren().add(buttonStorage.get(i));
                }
                disRemove.close();
            });    
        });

        //goBack function
        goBack.setOnAction(i -> {

            System.out.println();
            primaryStage.setScene(homeScene);
            primaryStage.show();
        });

        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        HashMap<String, Integer> monthNum = new HashMap<String, Integer>();
        for (int i = 1; i < 13; i++) {
            monthNum.put(months[i-1], i);
        }
        
        //addList Function
        addList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField task = new TextField();
                TextField description = new TextField();
                Label important = new Label("Important");
                Label displayDay = new Label("Day:");
                Label displayMonth = new Label("Month:");
                Label displayYear = new Label("Year:");
                task.setPromptText("Enter task");
                description.setPromptText("Enter description");
                final ToggleGroup tOrF = new ToggleGroup();
                RadioButton t = new RadioButton("true");
                t.setToggleGroup(tOrF);
                t.requestFocus();
                RadioButton f = new RadioButton("false");
                f.requestFocus();
                f.setToggleGroup(tOrF);
                ArrayList<Integer> days = new ArrayList<Integer>();
                ArrayList<Integer> years = new ArrayList<Integer>();
                
                for (int i = 0; i < 31; i++) {
                    days.add(i+1);
                }
                for (int i = 0; i < 11; i++) {
                    years.add(i+2020);
                }
                ChoiceBox<Integer> day = new ChoiceBox<Integer>(FXCollections.observableArrayList(days));
                ChoiceBox<String> month = new ChoiceBox<String>(FXCollections.observableArrayList(months));
                ChoiceBox<Integer> year = new ChoiceBox<Integer>(FXCollections.observableArrayList(years));
                day.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ov, 
                        Number old_val, Number new_val) -> {
                            daySelected = days.get(new_val.intValue()); 
                });
                month.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ov, 
                        Number old_val, Number new_val) -> {
                            monthSelected = months[new_val.intValue()]; 
                });
                
                year.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ov, 
                        Number old_val, Number new_val) -> {
                            yearSelected = years.get(new_val.intValue()); 
                });
                tOrF.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    public void changed (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
                    Toggle new_toggle) {
                        RadioButton rb = (RadioButton) tOrF.getSelectedToggle();
                        if (rb != null) {
                            chooseImportant = rb.getText();    
                        }
                    }
                });
                
                Button enter = new Button("Enter");
                GridPane taskInfo = new GridPane(); 
                taskInfo.setPadding(new Insets(10, 10, 10, 10));
                taskInfo.setMinSize(150, 150);
                taskInfo.setVgap(10);
                taskInfo.setHgap(10);
                taskInfo.add(displayDay, 2,4);
                taskInfo.add(displayMonth, 2,5);
                taskInfo.add(displayYear, 2, 6);
                taskInfo.add(day, 3,4);
                taskInfo.add(month, 3,5);
                taskInfo.add(year, 3, 6);
                taskInfo.add(description, 2,3);
                taskInfo.add(task, 2,2);
                taskInfo.add(important,2,7);
                taskInfo.add(enter,2,10);
                taskInfo.add(t, 3,8);
                taskInfo.add(f, 3,9);
                Scene enterTaskInfoScene = new Scene(taskInfo, 300, 350);
                Stage enterTaskInput = new Stage();
                enterTaskInput.setTitle("Info for Task");
                enterTaskInput.setScene(enterTaskInfoScene);
                enterTaskInput.show();  
                
                

                enter.setOnAction(e -> {
                    titleOfTask = task.getText();
                    descriptionOfTask = description.getText();
                    monthNumSelected = monthNum.get(monthSelected); 
                    if(chooseImportant.equals("true")) {
                        listImportant--;
                    } else {
                        listImportant = 100;
                    }
                    
                    ListInfo l1 = new ListInfo(titleOfTask, descriptionOfTask, yearSelected, monthNumSelected, daySelected, listImportant, false, "");
                    buttonOrganize.get(recentButton).addOrganize(l1);
                    listTask = buttonOrganize.get(recentButton).getList();
                    buttonVBox.get(recentButton).getChildren().clear();
                    
                    for(int i = 0; i < listTask.size(); i++) {
                        VBox tasks = new VBox();
                        tasks.setPadding(new Insets(0,0,5,5));
                        tasks.setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;");
                        CheckBox cb = new CheckBox();
                        final int index = i;
                        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> ov,
                              Boolean old_val, Boolean new_val) {
                                if(cb.getText().equals(listTask.get(index).getTask() + "\n" +  listTask.get(index).getDescription() + "\n" + "Date: " + listTask.get(index).getMonth() + "/" + listTask.get(index).getDay()+ "/" + listTask.get(index).getYear() + " " + "\n" + "Important: " + listTask.get(index).getDisImportant())) {
                                    listTask.get(index).setIsSet(true);
                                  } 
                           }
                         });
                        cb.setStyle("-fx-font-size: 13;");
                        if(listTask.get(i).getIsSet()) {
                            cb.setSelected(true);
                        }
                        if(listTask.get(i).getImportant() != 100) {
                            forImportant = "IMPORTANT";
                            listTask.get(i).setDisImportant(forImportant);
                        } else {
                            forImportant = "No";
                            listTask.get(i).setDisImportant(forImportant);
                        }
                        cb.setText(listTask.get(i).getTask() + "\n" +  listTask.get(i).getDescription() + "\n" + "Date: " + listTask.get(i).getMonth() + "/" + listTask.get(i).getDay()+ "/" + listTask.get(i).getYear() + " " + "\n" + "Important: " + listTask.get(i).getDisImportant());  
                        tasks.getChildren().addAll(cb);  
                        buttonVBox.get(recentButton).getChildren().add(tasks);      
                    }
                    
                    enterTaskInput.close();   
                    
                });
            }

        });

        
        removeItems.setOnAction(i->{
            buttonVBox.get(recentButton).getChildren().clear();
            buttonOrganize.get(recentButton).clearList();
        });
        
        //add button leads to popup
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final ToggleGroup group = new ToggleGroup();
                RadioButton grocery = new RadioButton("Groceries");
                grocery.setToggleGroup(group);
                grocery.requestFocus();
                RadioButton chores = new RadioButton("Chores");
                chores.requestFocus();
                chores.setToggleGroup(group);
                RadioButton work = new RadioButton("Work");
                work.setToggleGroup(group);
                work.requestFocus();
                RadioButton other = new RadioButton("Other");
                other.setToggleGroup(group);
                other.requestFocus();
                BorderPane popUp = new BorderPane();
                ImageView display = new ImageView();
                HBox icon = new HBox();
                icon.setPadding(new Insets(20,20,20,20));
                icon.getChildren().add(display);
                VBox categories = new VBox();
                HBox button = new HBox();
                Button done = new Button("Done");
                button.setPadding(new Insets(20,20,20,20));
                button.getChildren().add(done);
                categories.setPadding(new Insets(20,20,20,20));
                categories.setSpacing(5);
                categories.getChildren().addAll(grocery, chores, work, other);
                popUp.setLeft(categories);
                popUp.setRight(icon);
                popUp.setBottom(button);
                Scene newPopUp = new Scene(popUp, 300, 250);
                Stage window = new Stage();
                
                //popUp display
                window.setTitle("Choose a Category");
                window.setScene(newPopUp);
                window.show();
                
                //radio buttons
                group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    public void changed (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
                    Toggle new_toggle) {
                        RadioButton rb = (RadioButton) group.getSelectedToggle();
                        if (rb != null) {
                            choosen = rb.getText();
                            System.out.println(choosen);
                            String forImage = choosen + ".png";
                            final Image image = new Image(forImage);
                            display.setImage(image);
                        }
                    }
                });
                
                

                //done button on popup to add category
                done.setOnAction(e->{
                    System.out.println(choosen);
                    if(choosen.equals("Groceries")) {
                        Button groceryDis = new Button("Grocery");
                        buttonStorage.add(groceryDis);
                        Organize groceryOrganize = new Organize();
                        VBox groceryOnly = new VBox();
                        groceryOnly.setPadding(new Insets(20,0,0,20));
                        groceryOnly.setSpacing(20);
                        groceryOnly.setMinSize(350,50);
                        ScrollPane groceryScroll = new ScrollPane();
                        groceryScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                        groceryScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                        groceryScroll.setContent(groceryOnly);    
                        buttonVBox.put(groceryDis, groceryOnly); 
                        buttonOrganize.put(groceryDis, groceryOrganize);

                        System.out.println(groceryDis);
                         
                        groceryDis.setPrefSize(300,70);
                        String groceryStyles = "-fx-background-image: url('items.jpg');" + "-fx-text-fill: white;" + "-fx-font-size: 1.5em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: black;";
                        groceryDis.setStyle(groceryStyles);
                        vbox.getChildren().add(groceryDis);
                        categoryList.add(groceryDis);
                        groceryDis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle (ActionEvent event) {
                                recentButton = groceryDis;
                                categoryTitle.setText("Grocery"); 
                                ScrollPane forLists = new ScrollPane();
                                forLists.setHbarPolicy(ScrollBarPolicy.NEVER);
                                forLists.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                                forLists.setContent(buttonVBox.get(recentButton));
                                borderList.setCenter(forLists);
                                primaryStage.setScene(listScene);
                                primaryStage.show();
                            }
                        });
                    } else if(choosen.equals("Chores")) {
                        Button choresDis = new Button("Chores");
                        buttonStorage.add(choresDis);
                        Organize choresOrganize = new Organize();
                        VBox choresOnly = new VBox();
                        choresOnly.setPadding(new Insets(20,0,0,20));
                        choresOnly.setSpacing(20);
                        choresOnly.setMinSize(350,50);
                        ScrollPane choresScroll = new ScrollPane();
                        choresScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                        choresScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                        choresScroll.setContent(choresOnly);       
                        buttonVBox.put(choresDis, choresOnly);
                        buttonOrganize.put(choresDis, choresOrganize);
                        choresDis.setPrefSize(300,70);
                        String choreStyles = "-fx-background-image: url('room.jpg');" + "-fx-text-fill: white;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: black;";
                        choresDis.setStyle(choreStyles);
                        vbox.getChildren().add(choresDis);
                        categoryList.add(choresDis);
                        choresDis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle (ActionEvent event) {
                                recentButton = choresDis;
                                categoryTitle.setText("Chores");
                                ScrollPane forLists = new ScrollPane();
                                forLists.setHbarPolicy(ScrollBarPolicy.NEVER);
                                forLists.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                                forLists.setContent(buttonVBox.get(recentButton));
                                borderList.setCenter(forLists);
                                primaryStage.setScene(listScene);
                                primaryStage.show();
                            }
                        });
                    } else if(choosen.equals("Work")) {
                        Button workDis = new Button("Work");
                        buttonStorage.add(workDis);
                        Organize workOrganize = new Organize();
                        VBox workOnly = new VBox();
                        workOnly.setPadding(new Insets(20,0,0,20));
                        workOnly.setSpacing(20);
                        workOnly.setMinSize(350,50);
                        ScrollPane choresScroll = new ScrollPane();
                        choresScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                        choresScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                        choresScroll.setContent(workOnly);       
                        buttonVBox.put(workDis, workOnly);
                        buttonOrganize.put(workDis, workOrganize);
                        workDis.setPrefSize(300,70);
                        String workStyles = "-fx-background-image: url('office.jpg');" + "-fx-text-fill: white;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: black;";
                        workDis.setStyle(workStyles);
                        vbox.getChildren().add(workDis);
                        categoryList.add(workDis);
                        workDis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle (ActionEvent event) {
                                recentButton = workDis;
                                ScrollPane forLists = new ScrollPane();
                                forLists.setHbarPolicy(ScrollBarPolicy.NEVER);
                                forLists.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                                forLists.setContent(buttonVBox.get(recentButton));
                                borderList.setCenter(forLists);
                                categoryTitle.setText("Work");
                                primaryStage.setScene(listScene);
                                primaryStage.show();
                            }
                        });
                    } else if(choosen.equals("Other")) { 
                        TextField textField = new TextField();
                        TextField color = new TextField();
                        textField.setPromptText("Choose name");
                        color.setPromptText("Choose color: red, blue, green, white");
                        color.setPrefWidth(250);
                        Button finished = new Button("Finish");
                        GridPane pane = new GridPane(); 
                        pane.setPadding(new Insets(10, 10, 10, 10));
                        pane.setMinSize(150, 120);
                        pane.setVgap(5);
                        pane.setHgap(5);
                        pane.add(color, 2,3);
                        pane.add(textField, 2,2);
                        pane.add(finished,2,4);
                        Scene scene = new Scene(pane, 300, 120);
                        Stage enterInput = new Stage();
                        enterInput.setTitle("Info for Other");
                        enterInput.setScene(scene);
                        enterInput.show();
                        Button otherDis = new Button();
                        finished.setOnAction(a -> {
                            nameOfBtn = textField.getText();
                            System.out.println(nameOfBtn);
                            otherDis.setText(nameOfBtn);
                            buttonStorage.add(otherDis);
                            otherButtonName.put(otherDis, nameOfBtn);
                            if(color.getText().equalsIgnoreCase("red")) {
                                String otherStyles = "-fx-text-fill: white;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: #ff4545;";
                                otherDis.setStyle(otherStyles);
                            } else if(color.getText().equalsIgnoreCase("blue")) {
                                String otherStyles = "-fx-text-fill: white;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: #5e5eff;";
                                otherDis.setStyle(otherStyles);
                            } else if(color.getText().equalsIgnoreCase("green")) {
                                String otherStyles = "-fx-text-fill: white;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: #26b566;";
                                otherDis.setStyle(otherStyles);
                            } else if(color.getText().equalsIgnoreCase("white")) {
                                String otherStyles = "-fx-text-fill: black;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: #ffffff;" ;
                                otherDis.setStyle(otherStyles);
                            } else {
                                String otherStyles = "-fx-text-fill: black;" + "-fx-font-size: 2em;" + "-fx-font-weight: bold;" + "-fx-font-family:'Sans Serfif';" + "-fx-background-color: #ffffff;" ;
                                otherDis.setStyle(otherStyles);
                            }
                            Organize otherOrganize = new Organize();
                            VBox otherOnly = new VBox();
                            otherOnly.setPadding(new Insets(20,0,0,20));
                            otherOnly.setSpacing(20);
                            otherOnly.setMinSize(350,50);
                            ScrollPane groceryScroll = new ScrollPane();
                            groceryScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                            groceryScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                            groceryScroll.setContent(otherOnly);    
                            buttonVBox.put(otherDis, otherOnly); 
                            buttonOrganize.put(otherDis, otherOrganize);    
                            otherDis.setPrefSize(300,70);
                            vbox.getChildren().add(otherDis);
                            categoryList.add(otherDis);
                            enterInput.close();
                        });
                        otherDis.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle (ActionEvent event) {
                                recentButton = otherDis;
                                ScrollPane forLists = new ScrollPane();
                                forLists.setHbarPolicy(ScrollBarPolicy.NEVER);
                                forLists.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                                forLists.setContent(buttonVBox.get(recentButton));
                                borderList.setCenter(forLists);
                                categoryTitle.setText(otherButtonName.get(otherDis));
                                primaryStage.setScene(listScene);
                                primaryStage.show();
                            }
                        });
                        
                    }
                    

                    window.close();
                });
                
            }
        });

        
        



    }
}