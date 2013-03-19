package view.menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import data.Context;

import view.MainFrame;

public class RiskDialog extends JDialog {

    private JButton jbtnUpdate = new JButton("Update");
    private JButton jbtnCancel = new JButton("Cancel");
    
    private HashMap<String, JTextArea> riskGuiMap = new HashMap<String, JTextArea>();


    public RiskDialog(MainFrame view) {

        super(view, true);

        JLabel headerName = new JLabel("Risk Level");
        JLabel headerLevel = new JLabel("Risk percentage (%)");
        add(headerName);
        add(headerLevel);

        HashMap<String, Integer> riskMap = Context.getRiskLevel();

        setLayout(new GridLayout(riskMap.size() + 2, 3));


        for(String riskName : riskMap.keySet()) {
            JLabel jlblRiskName = new JLabel(riskName);
            JTextArea jtxtRiskLevel = new JTextArea(riskMap.get(riskName) + "");
            riskGuiMap.put(riskName, jtxtRiskLevel);
            add(jlblRiskName);
            add(jtxtRiskLevel);
        }

        add(jbtnUpdate);
        add(jbtnCancel);


        pack();
        setLocationRelativeTo(view);
        setTitle("Manage Risk");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addController(ActionListener listener) {
        jbtnUpdate.addActionListener(listener);
        jbtnCancel.addActionListener(listener);
    }

    public HashMap<String, Integer> getInputMap() {
        HashMap<String, Integer> output = new HashMap<String, Integer>();
        for(String name : riskGuiMap.keySet()) {
            output.put(name, Integer.parseInt(riskGuiMap.get(name).getText()));
        }
        return output;
    }
    
}
