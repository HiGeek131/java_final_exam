import  javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    static final String db_url = "jdbc:mysql://higeekstudio.cn:3306/java_test?useSSL=false";
    static final String db_user = "gary";
    static final String db_passwd = "827289014Aa131";
    static SqlDB sqlDB;

    public static void main(String[] args) {
        JFrame frame = new JFrame("学生成绩管理-孙伟");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);

        sqlDB = new SqlDB(db_url, db_user, db_passwd);
        if (sqlDB.sqlConnect()) {
            System.out.println("数据库连接成功！");
            JOptionPane.showMessageDialog(null, "数据库连接成功！");
        }
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel idLabel = new JLabel("学号：");
        idLabel.setBounds(80, 20, 50, 20);
        panel.add(idLabel);

        JTextField idTextField = new JTextField(20);
        idTextField.setBounds(120, 20, 200, 20);
        panel.add(idTextField);

        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(80, 70, 50, 20);
        panel.add(nameLabel);

        JTextField nameTextField = new JTextField(20);
        nameTextField.setBounds(120, 70, 200, 20);
        panel.add(nameTextField);

        JLabel javaLabel = new JLabel("java：");
        javaLabel.setBounds(80, 120, 50, 20);
        panel.add(javaLabel);

        JTextField javaTextField = new JTextField(20);
        javaTextField.setBounds(120, 120, 200, 20);
        panel.add(javaTextField);

        JLabel mathLabel = new JLabel("math：");
        mathLabel.setBounds(80, 170, 50, 20);
        panel.add(mathLabel);

        JTextField mathTextField = new JTextField(20);
        mathTextField.setBounds(120, 170, 200, 20);
        panel.add(mathTextField);

        JButton saveButton = new JButton("保存");
        saveButton.setBounds(70, 240, 100, 30);
        panel.add(saveButton);

        JButton findButton = new JButton("查找");
        findButton.setBounds(220, 240, 100, 30);
        panel.add(findButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = idTextField.getText();
                String name = nameTextField.getText();
                double javaData;
                double mathData;
                try {
                    javaData = Double.parseDouble(javaTextField.getText());
                    mathData = Double.parseDouble(mathTextField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "请输入正确的值！");
                    return;
                }
                String sql = String.format("INSERT table1 (num, name, java, math) VALUES (%s, %s, %.2f, %.2f);", id, name, javaData, mathData);
                System.out.println(sql);
                if (sqlDB.sqlExecuteUpdate(sql) != -1) {
                    JOptionPane.showMessageDialog(null, "保存成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "保存失败！");
                }
            }
        });
    }
}
