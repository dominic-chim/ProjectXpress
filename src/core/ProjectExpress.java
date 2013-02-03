package core;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controllers.MainController;

import view.MainFrame;

/**
 * program entrance
 * TODO create controller and model 
 * 
 * @author Bob
 *
 */
public class ProjectExpress {

    public static void main(String[] args) {
        
         // set look and feel
        try {
            // using Nimbus look and feel
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot use Nimbus!");
        }
        
        // create view 
        MainFrame view = new MainFrame();

        // create controller
        MainController controller = new MainController(view);
        
        view.setVisible(true);
    }
}
