package com.vupt.CLTPF;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

@Controller
public class MyController {
    @FXML
    TextField tfLink;
    @FXML
    TextField tfFrame;
    @FXML
    Label lbMessage;
    @FXML
    Button btnConvert;
    @Autowired
    ModelMapper modelMapper;

    public static void loadView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyController.class.getResource("/sample.fxml"));
        loader.setControllerFactory(Main.getApplicationContext()::getBean);
        Parent view = loader.load();
        stage.setTitle("Convert Link To Frame");
        stage.setScene(new Scene(view));
        stage.show();
    }
    @FXML
    public void convert(){
//
        String[] url=tfLink.getText().split("\"");
        String filePath=url[1];
      String fileName=filePath.substring(filePath.lastIndexOf('/')+1);
       String frameTag=String.format("<iframe src=\"%s\" width=\"%s\" height=\"%s\" >%s</iframe>",filePath,"100%","700px",fileName);
       tfFrame.setText(frameTag);

      lbMessage.setText("Convert success!");
    }
    @FXML
    public void copyToClipboard(){
        StringSelection stringSelectionObj = new StringSelection(tfFrame.getText());
        Clipboard clipboardObj = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboardObj.setContents(stringSelectionObj, null);

       lbMessage.setText("Copied to clipboard!");
    }
}
