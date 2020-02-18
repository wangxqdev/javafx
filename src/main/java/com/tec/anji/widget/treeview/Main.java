package com.tec.anji.widget.treeview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TreeView
 */
public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Map<String, List<String>> addresses = new HashMap<>();
        List<String> bj = Stream.of("朝阳区", "海淀区").collect(Collectors.toList());
        List<String> tj = Stream.of("和平区", "武清区").collect(Collectors.toList());
        List<String> sh = Stream.of("杨浦区", "宝山区").collect(Collectors.toList());
        List<String> cq = Stream.of("万州区", "涪陵区").collect(Collectors.toList());
        addresses.put("北京市", bj);
        addresses.put("天津市", tj);
        addresses.put("上海市", sh);
        addresses.put("重庆市", cq);

        TreeItem<String> country = new TreeItem<>("中国");
        ObservableList<TreeItem<String>> cities = country.getChildren();
        addresses.forEach((k, v) ->
        {
            TreeItem<String> city = new TreeItem<>(k);
            ObservableList<TreeItem<String>> districts = city.getChildren();
            v.forEach(district -> districts.add(new TreeItem<>(district)));
            cities.add(city);
        });
        TreeView<String> treeView = new TreeView<>(country);
//        非叶子节点默认展开
        setTreeViewExpanded(treeView.getRoot());
        treeView.setStyle("-fx-max-height: 200px");

        treeView.setEditable(true);
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());

        HBox root = new HBox(treeView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TreeView");
        primaryStage.show();

//        根节点添加监听事件
//        country.addEventHandler(TreeItem.branchCollapsedEvent(), e -> {});
    }

    private void setTreeViewExpanded(TreeItem<String> treeItem)
    {
        if (!treeItem.isLeaf())
        {
            treeItem.setExpanded(true);
            treeItem.getChildren().forEach(this::setTreeViewExpanded);
        }
    }
}
