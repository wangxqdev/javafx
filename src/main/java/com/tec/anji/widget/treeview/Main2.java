package com.tec.anji.widget.treeview;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CheckBoxTreeCell
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        List<Data> bj = Stream.of(new Data("朝阳区"), new Data("海淀区")).collect(Collectors.toList());
        List<Data> tj = Stream.of(new Data("和平区"), new Data("武清区")).collect(Collectors.toList());
        List<Data> sh = Stream.of(new Data("杨浦区"), new Data("宝山区")).collect(Collectors.toList());
        List<Data> cq = Stream.of(new Data("万州区"), new Data("涪陵区")).collect(Collectors.toList());
        Map<Data, List<Data>> addresses = new HashMap<>();
        addresses.put(new Data("北京市"), bj);
        addresses.put(new Data("天津市"), tj);
        addresses.put(new Data("上海市"), sh);
        addresses.put(new Data("重庆市"), cq);

        Button button = new Button("查询");

        TreeItem<Data> country = new TreeItem<>(new Data("中国"));
        ObservableList<TreeItem<Data>> cities = country.getChildren();
        addresses.forEach((k, v) ->
        {
            TreeItem<Data> city = new TreeItem<>(k);
            ObservableList<TreeItem<Data>> districts = city.getChildren();
            v.forEach(district -> districts.add(new TreeItem<>(district)));
            cities.add(city);
        });
        TreeView<Data> treeView = new TreeView<>(country);
//        非叶子节点默认展开
        setTreeViewExpanded(treeView.getRoot());
        treeView.setStyle("-fx-max-width: 300px; -fx-max-height: 200px");

        treeView.setEditable(true);
        treeView.setCellFactory(CheckBoxTreeCell.forTreeView(param -> param.getValue().checkedProperty(),
                new StringConverter<TreeItem<Data>>()
        {
            @Override
            public String toString(TreeItem<Data> object)
            {
                return object.getValue().getName();
            }

            @Override
            public TreeItem<Data> fromString(String string)
            {
                return null;
            }
        }));

        VBox root = new VBox(button, treeView);
        root.setStyle("-fx-pref-width: 500px; -fx-pref-height: 400px; -fx-spacing: 10px; -fx-alignment: center");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("TreeView");
        primaryStage.show();
    }

    private void setTreeViewExpanded(TreeItem<Data> treeItem)
    {
        if (!treeItem.isLeaf())
        {
            treeItem.setExpanded(true);
            treeItem.getChildren().forEach(this::setTreeViewExpanded);
        }
    }

    @EqualsAndHashCode
    public class Data
    {
        private SimpleStringProperty name = new SimpleStringProperty();

        private SimpleBooleanProperty checked = new SimpleBooleanProperty();

        public Data(String name)
        {
            this.name.set(name);
            this.checked.set(false);
        }

        public String getName()
        {
            return name.get();
        }

        public SimpleStringProperty nameProperty()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name.set(name);
        }

        public boolean isChecked()
        {
            return checked.get();
        }

        public SimpleBooleanProperty checkedProperty()
        {
            return checked;
        }

        public void setChecked(boolean checked)
        {
            this.checked.set(checked);
        }
    }
}
