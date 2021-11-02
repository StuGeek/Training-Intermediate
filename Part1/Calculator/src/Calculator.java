import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame {
    // 计算器字体
    private static String fontName = "TimesRoman";
    private static int fontSize = 15;
    // 计算器界面组件
    private JFrame jframe;
    private JPanel jpanel;
    private JTextField firstNum;
    private JTextField secondNum;
    private JLabel operator;
    private JLabel equal;
    private JLabel result;
    private JButton plus;
    private JButton minus;
    private JButton multiply;
    private JButton divide;
    private JButton ok;

    // 创建特定字体格式和对齐方式的JTextField
    private JTextField creatJTextField() {
        JTextField jTextField = new JTextField();
        jTextField.setFont(new Font(fontName, Font.PLAIN, fontSize));
        jTextField.setHorizontalAlignment(SwingConstants.CENTER);
        return jTextField;
    }

    // 创建特定字体格式、对齐方式和边框样式的JLabel
    private JLabel creatJLabel(String text) {
        JLabel jLabel = new JLabel(text, SwingConstants.CENTER);
        // 使jLabel的边框为样例中蓝色的标题边框
        jLabel.setBorder(BorderFactory.createTitledBorder(""));
        jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
        return jLabel;
    }

    // 判断字符串是否为符合格式的数字
    public static boolean isNumeric(String str) {
        return str.matches("^[-+]?(([0-9]+)([.][0-9]+)?)$");
    }

    // 计算得到结果
    private void getResult() {
        // 获取数字输入框中的字符串
        String number1 = firstNum.getText();
        String number2 = secondNum.getText();
        // 如果数字输入框中没有内容，那么不能计算提示数字缺失
        if (number1.equals("") || number2.equals("")) {
            result.setText("<html>Number<br/>Missing</html>");
            return;
        }
        // 如果数字输入框中的内容不符合数字格式，那么不能计算提示数字错误
        else if (!isNumeric(number1) || !isNumeric(number2)) {
            result.setText("<html>Not<br/>Number</html>");
            return;
        }
        double res = 0;
        // 将数字字符串转为数字
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        // 获取计算符号
        String symbol = operator.getText();

        // 根据不同的计算符号，计算不同的结果
        switch (symbol) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
            default:
                // 如果没有计算符号，那么不能计算提示没有符号
                result.setText("<html>Symbol<br/>Missing</html>");
                return;
        }
        // 在结果框中显示计算结果，保留2位小数
        result.setText(String.format("%2.2f", res));
    }

    // 创建特定字体格式、点击事件的JButton
    private JButton creatJButton(String symbol) {
        JButton jButton = new JButton(symbol);
        jButton.setFont(new Font(fontName, Font.PLAIN, fontSize));
        // 根据不同的按钮，设计不同的点击事件
        jButton.addActionListener(e -> {
                switch (symbol) {
                    // 计算符号按钮，在符号框显示计算符号
                    case "+":
                        operator.setText("+");
                        break;
                    case "-":
                        operator.setText("-");
                        break;
                    case "*":
                        operator.setText("*");
                        break;
                    case "/":
                        operator.setText("/");
                        break;
                    // 显示结果按钮，计算结果并将结果显示到结果框
                    case "OK":
                        getResult();
                        break;
                    default:
                        break;
                }
            });
        return jButton;
    }

    // 创建各个组件
    public void creatComponent() {
        firstNum = creatJTextField();
        operator = creatJLabel("");
        secondNum = creatJTextField();
        equal = creatJLabel("=");
        result = creatJLabel("");
        plus = creatJButton("+");
        minus = creatJButton("-");
        multiply = creatJButton("*");
        divide = creatJButton("/");
        ok = creatJButton("OK");
    }

    // 将特定组件添加到JPanel中
    public void addJPanel() {
        jpanel.add(firstNum);
        jpanel.add(operator);
        jpanel.add(secondNum);
        jpanel.add(equal);
        jpanel.add(result);
        jpanel.add(plus);
        jpanel.add(minus);
        jpanel.add(multiply);
        jpanel.add(divide);
        jpanel.add(ok);
    }

    // 构造函数，初始化计算器
    public Calculator() {
        jframe = new JFrame("Easy Calculator");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jpanel = new JPanel();
        // 设计布局，2行5列，并设置间距
        jpanel.setLayout(new GridLayout(2, 5, 5, 10));
        jpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        creatComponent();
        addJPanel();

        jframe.getContentPane().add(jpanel);
        jframe.setSize(400, 200);
        jframe.setVisible(true);
    }

    public static void main(String[] agrs) {
        // 创建计算器
        new Calculator();
    }
}
