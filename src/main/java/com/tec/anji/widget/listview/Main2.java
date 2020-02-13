package com.tec.anji.widget.listview;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.AllArgsConstructor;

/**
 * ListView的4种可编辑方式
 */
public class Main2 extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        HBox root = new HBox();
        ObservableList<Node> nodes = root.getChildren();
        for (int i = 0; i < 4; i++)
        {
            ListView<Data> listView = new ListView<>(FXCollections.observableArrayList());
            ObservableList<Data> datum = listView.getItems();
            ObservableList<Data> copy = FXCollections.observableArrayList();
            for (int j = 0; j < 8; j++)
            {
                Data data = new Data(String.valueOf((char) (97 + j)), false);
//                设置监听
                data.singleProperty().addListener((observable, oldValue, newValue) -> System.out.println(String.format("%s是否单身 = %s", data.getName(), newValue)));
                datum.add(data);
                copy.add(data);
            }
            listView.setEditable(true);
            listView.setStyle("-fx-pref-width: 200px; -fx-pref-height: 150px");
            Label label = new Label();
            switch (i)
            {
                case 0:
                {
                    label.setText("CheckBoxListCell");
                    listView.setCellFactory(CheckBoxListCell.forListView(param ->
                    {
                        if ("B".equalsIgnoreCase(param.getName()))
                        {
                            param.setSingle(true);
                        }
                        return param.singleProperty();
                    }, new StringConverter<Data>()
                    {
                        @Override
                        public String toString(Data object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Data fromString(String string)
                        {
                            return null;
                        }
                    }));
                    break;
                }
                case 1:
                {
                    label.setText("ChoiceBoxListCell");
                    listView.setCellFactory(ChoiceBoxListCell.forListView(new StringConverter<Data>()
                    {
                        @Override
                        public String toString(Data object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Data fromString(String string)
                        {
                            return null;
                        }
                    }, copy));
                    break;
                }
                case 2:
                {
                    label.setText("ComboBoxListCell");
                    listView.setCellFactory(ComboBoxListCell.forListView(new StringConverter<Data>()
                    {
                        @Override
                        public String toString(Data object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Data fromString(String string)
                        {
                            return null;
                        }
                    }, copy));
                    break;
                }
                case 3:
                {
                    label.setText("TextFieldListCell");
                    listView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Data>()
                    {
                        @Override
                        public String toString(Data object)
                        {
                            return object.getName();
                        }

                        @Override
                        public Data fromString(String string)
                        {
                            return copy.stream().filter(i -> string.equals(i.getName())).findFirst().orElse(new Data(
                                    "", false));
                        }
                    }));
                }
            }
            label.setStyle("-fx-font: bold 16px sans-serif");

            VBox vBox = new VBox(label, listView);
            vBox.setStyle("-fx-spacing: 10px; -fx-alignment: center");
            nodes.add(vBox);
        }
        root.setStyle("-fx-pref-width: 900px; -fx-pref-height: 300px; -fx-spacing: 10px; -fx-alignment: center;");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Editable");
        primaryStage.show();
    }

    private class Data
    {
        private String name;

        private SimpleBooleanProperty single = new SimpleBooleanProperty();

        public Data(String name, boolean isSingle)
        {
            this.name = name;
            this.single.set(isSingle);
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public boolean isSingle()
        {
            return single.get();
        }

        public SimpleBooleanProperty singleProperty()
        {
            return single;
        }

        public void setSingle(boolean single)
        {
            this.single.set(single);
        }
    }
}
