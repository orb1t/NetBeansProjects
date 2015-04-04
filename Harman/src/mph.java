
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class mph extends JFrame
{
    JRoundButton hr;
    JRoundButton manager1;
    JRoundButton projecthead;
    Toolkit tk = Toolkit.getDefaultToolkit();
    int w=(int) tk.getScreenSize().getWidth();
    int h=(int) tk.getScreenSize().getHeight();
    public mph()
    {
        initComponents();
    }

    private void initComponents()
    {
        look();
        manager1 = new JRoundButton("Manager 1",null);
        projecthead = new JRoundButton("Project Head",null);
        hr = new JRoundButton("HR",null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        manager1.setToolTipText("Manager 1");
        projecthead.setToolTipText("Project Head");
        hr.setToolTipText("HR");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(manager1)
                .addGap(37, 37, 37)
                .addComponent(projecthead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(hr)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(manager1)
                    .addComponent(projecthead)
                    .addComponent(hr))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        setTitle("Choose Role");
        setIconImage(Toolkit.getDefaultToolkit().getImage("Bin\\img\\r.gif"));
        setLocation(h/2,w/4);
        setResizable(false);
        pack();
    }

    public void look()
    {
         try
         {
             //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
             //UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
              //UIManager.setLookAndFeel("com.easynth.lookandfeel.EaSynthLookAndFeel");
             UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
             /*System.setProperty("Quaqua.tabLayoutPolicy","wrap");
             UIManager.setLookAndFeel(
                  ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel()
              );*/
         }catch(Exception e){}
    }
}
