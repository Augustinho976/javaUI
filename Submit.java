package work;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Submit {
    private JButton submit;
    private JTextField name;
    private JTextField book_name;
    private JPanel panel1;

    public Submit() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String myname = name.getText();
                String number = book_name.getText();

                Connection connection = null;
                String host="localhost";
                String port="5432";
                String db_name="postgres";
                String username="postgres";
                String password="33744525";
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
                    if (connection != null) {
                        String query="insert into books values('"+myname+"', '"+number+"')";
                        Statement statement= connection.createStatement();
                        int x = statement.executeUpdate(query);
                        if(x==0){
                            JOptionPane.showMessageDialog(submit,"Book exists");
                        }
                        else{
                            JOptionPane.showMessageDialog(submit,"saved successfully");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("saves");
        frame.setContentPane(new Submit().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
