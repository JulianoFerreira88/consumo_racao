package com.github.consumo_racao;

import com.github.consumo_racao.view.MainFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        Properties p = new Properties();
        p.load(new FileInputStream(new File("C:/db.properties")));
        Connection connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("pass"));
        MainFrame f = new MainFrame(connection);
        f.setVisible(true);
    }

}
