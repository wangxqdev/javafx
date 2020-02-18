package com.tec.anji.widget.treeview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CheckBoxTreeItem
 */
public class Main3 extends Application
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

        CheckBoxTreeItem<String> country = new CheckBoxTreeItem<>("中国");
        ObservableList<TreeItem<String>> cities = country.getChildren();
        addresses.forEach((k, v) ->
        {
            CheckBoxTreeItem<String> city = new CheckBoxTreeItem<>(k);
            ObservableList<TreeItem<String>> districts = city.getChildren();
            v.forEach(district -> districts.add(new CheckBoxTreeItem<>(district)));
            cities.add(city);
        });
        TreeView<String> treeView = new TreeView<>(country);
//        非叶子节点默认展开
        setTreeViewExpanded(treeView.getRoot());
        treeView.setStyle("-fx-max-height: 200px");

        treeView.setEditable(true);
        treeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        HBox root = new HBox(treeView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TreeView");
        primaryStage.show();
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
