package com.tec.anji.widget.menu;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomMenuItem
 */
public class Main extends Application
{
    private List<ImageView> icons = new ArrayList<ImageView>()
    {{
        add(initImage("/img/skype.png"));
        add(initImage("/img/ins.png"));
        add(initImage("/img/wechat.png"));
    }};

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Text center = new Text();
        center.setStyle("-fx-font-size: 14px");

        Menu menu1 = new Menu("Menu1");
        ObservableList<MenuItem> menu1Items = menu1.getItems();
        for (int i = 0; i < 3; i++)
        {
//            菜单项图标
            MenuItem item = new MenuItem(String.format("Item%s", i + 1), icons.get(i));
            if (i == 0)
            {
                item.setOnAction(e -> center.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm" +
                        ":ss"))));
//                快捷方式
                item.setAccelerator(KeyCombination.valueOf("ctrl + shift + y"));
            }
            else
            {
//                菜单项分隔符
                menu1Items.add(new SeparatorMenuItem());
            }
            menu1Items.add(item);
        }

        Menu menu2 = new Menu("Menu2");
        ObservableList<MenuItem> menu2Items = menu2.getItems();
//        单选菜单
        ToggleGroup group = new ToggleGroup();
        for (int i = 1; i <= 3; i++)
        {
            RadioMenuItem rdoItem = new RadioMenuItem(String.format("RdoItem%s", i));
            rdoItem.setToggleGroup(group);
            menu2Items.add(rdoItem);
        }

        Menu menu3 = new Menu("Menu3");
        ObservableList<MenuItem> menu3Items = menu3.getItems();
//        多选菜单
        for (int i = 1; i <= 3; i++)
        {
            CheckMenuItem chkItem = new CheckMenuItem(String.format("ChkItem%s", i));
            menu3Items.add(chkItem);
        }

        Menu menu4 = new Menu("Menu4");
        ObservableList<MenuItem> menu4Item4 = menu4.getItems();
//        自定义菜单项
        for (int i = 1; i <= 3; i++)
        {
            CustomMenuItem cstItem = new CustomMenuItem(new ProgressBar((double) i / 3));
            menu4Item4.add(cstItem);
        }

//        MenuButton
        MenuButton menuBtn = new MenuButton("MenuButton");
        ObservableList<MenuItem> menuBtnItems = menuBtn.getItems();
        for (int i = 1; i <= 3; i++)
        {
            MenuItem item = new MenuItem(String.format("MenuItem%s", i));
            menuBtnItems.add(item);
        }
        VBox left = new VBox(menuBtn);
        left.setStyle("-fx-alignment: center");

//        SplitMenuButton
        SplitMenuButton sMenuBtn = new SplitMenuButton();
        sMenuBtn.setText("SplitMenuButton");
        ObservableList<MenuItem> sMenuBtnItems = sMenuBtn.getItems();
        for (int i = 1; i <= 3; i++)
        {
            MenuItem item = new MenuItem(String.format("MenuItem%s", i));
            sMenuBtnItems.add(item);
        }
        VBox right = new VBox(sMenuBtn);
        right.setStyle("-fx-alignment: center");

//        ContextMenu
        ContextMenu ctxMenu = new ContextMenu();
        ObservableList<MenuItem> ctxMenuItems = ctxMenu.getItems();
        for (int i = 1; i <= 3; i++)
        {
            MenuItem item = new MenuItem(String.format("MenuItem%s", i));
            ctxMenuItems.add(item);
        }

        MenuBar top = new MenuBar(menu1, menu2, menu3, menu4);
        top.setContextMenu(ctxMenu);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setRight(right);
        root.setLeft(left);
        root.setCenter(center);
        root.widthProperty().addListener((observable, oldValue, newValue) -> top.setPrefWidth(newValue.doubleValue()));

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    private ImageView initImage(String path)
    {
        ImageView imageView = new ImageView(path);
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        return imageView;
    }
}
