package com.tec.anji.widget.treeview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 自定义TreeView
 */
public class Main4 extends Application
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
        treeView.setCellFactory(param -> new TreeCell<String>()
        {
            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(item, empty);

                if (!empty)
                {
                    Label label = new Label(item);
                    HBox hBox = new HBox(label);
                    this.setGraphic(hBox);

//                    设置展开折叠图标
                    if (this.getTreeItem().isExpanded())
                    {
                        this.setDisclosureNode(new Label("－"));
                    }
                    else
                    {
                        this.setDisclosureNode(new Label("＋"));
                    }
                    return;
                }
                this.setGraphic(null);
                this.setDisclosureNode(null);
            }

            @Override
            public void startEdit()
            {
                super.startEdit();

                TextField textField = new TextField(this.getItem());
                HBox hBox = new HBox(textField);
                this.setGraphic(hBox);

                textField.requestFocus();
                textField.setOnKeyReleased(e ->
                {
                    if (KeyCode.ENTER.equals(e.getCode()))
                    {
                        if (!textField.getText().isEmpty())
                        {
                            this.commitEdit(textField.getText());
                            return;
                        }
                        this.cancelEdit();
                    }
                });
            }

            @Override
            public void cancelEdit()
            {
                super.cancelEdit();

                Label label = new Label(this.getItem());
                HBox hBox = new HBox(label);
                this.setGraphic(hBox);
            }
        });

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
