import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Final extends JFrame
{
    JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();
    JLabel headerLabel1 = new JLabel();
    JLabel headerLabel2 = new JLabel();
    JLabel end=new JLabel();
    JLabel re=new JLabel();
    JButton exit;
    String in[]=new String[3];

    public Final(String name,String phone,String phone1,int attempt,int correct,int incorrect,int result,String time)
    {
        in[0]=name;
        in[1]=phone;
        in[2]=phone1;
        try
        {
            BufferedWriter b=new BufferedWriter(new FileWriter("Score.txt",true));
            b.append("Score:"+result+" Time:"+time+" Name:"+in[0]+" Phone Number 1:"+in[1]+" Phone Number 2:"+in[2]);
            b.newLine();
            b.close();
        }catch(Exception e){}
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(400, 300));
            setTitle("Result");
            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel1.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel2.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            headerLabel.setText("Number Of Question Attmepted-->"+attempt);
            headerLabel1.setText("Number Of Correct Answers-->"+correct);
            headerLabel2.setText("Number Of Incorrect Answers-->"+incorrect);
            re.setText("Your Result is-->"+result);
            re.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
            end.setText("Kindly Submit Your Score To the Coordinator");
            end.setFont(new java.awt.Font("Times Roman", Font.BOLD, 16));
            end.setForeground(Color.white);
            JPanel p=new JPanel(new GridLayout(3,1));
            p.add(headerLabel);
            p.add(headerLabel1);
            p.add(headerLabel2);
            p.add(re);
            JPanel p1=new JPanel(new BorderLayout());
            JPanel ppp=new JPanel(new GridLayout(3,1));
            ppp.add(new JPanel());
            ppp.add(new JPanel());
            ppp.add(end);
            p1.add(ppp,BorderLayout.EAST);
            contentPane.add(p, java.awt.BorderLayout.NORTH);
            //contentPane.add(re,java.awt.BorderLayout.CENTER);
            contentPane.add(p1,java.awt.BorderLayout.SOUTH);
            ImageIcon ii = new ImageIcon("MainIcon/thanx.gif");
            imageLabel.setIcon(ii);
            JPanel pp=new JPanel(new GridLayout(1,5));
            pp.add(new JPanel());
            pp.add(new JPanel());
            pp.add(imageLabel);
            pp.add(new JPanel());
            pp.add(new JPanel());
            contentPane.add(pp, java.awt.BorderLayout.CENTER);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}