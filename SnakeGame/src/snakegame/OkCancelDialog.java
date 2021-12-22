package snakegame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class OkCancelDialog extends JDialog {

    public static final int OK = 1;     // OK gombbal történő lezárás kódja
    public static final int CANCEL = 0; // Mégsem gombbal történő lezárás kódja

    protected int btnCode;            // a megnyomott gomb kódja

    protected JPanel btnPanel;         // az OK, Mégsem gombok elhelyezésére szolgáló panel

    protected JButton btnOK;             // OK gomb

    protected JButton btnCancel;         // Mégsem gomb

    protected OkCancelDialog(JFrame frame, String name) {
        super(frame, name, true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        btnCode = CANCEL;
        btnOK = new JButton(actionOK);
        btnOK.setMnemonic('O');
        btnOK.setPreferredSize(new Dimension(90, 25));
        btnCancel = new JButton(actionCancel);
        // Az Esc billentyű hozzárendelése a mégsem gombhoz (Esc lenyomása a párbeszédablakon belül -> mégsem gomb lenyomása
        KeyStroke mégsemKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap inputMap = btnCancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnCancel.getActionMap();
        if (inputMap != null && actionMap != null) {
            inputMap.put(mégsemKeyStroke, "cancel");
            actionMap.put("cancel", actionCancel);
        }
        btnCancel.setPreferredSize(new Dimension(90, 25));
        getRootPane().setDefaultButton(btnOK);
        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnOK);
        btnPanel.add(btnCancel);
    }

    public int getButtonCode() {
        return btnCode;
    }

    protected abstract boolean processOK();

    protected abstract void processCancel();

    private AbstractAction actionOK = new AbstractAction("Ok") {
        public void actionPerformed(ActionEvent e) {
            if (processOK()) {
                btnCode = OK;
                OkCancelDialog.this.setVisible(false);
            }
        }
    };

    private AbstractAction actionCancel = new AbstractAction("Cancel") {
        public void actionPerformed(ActionEvent e) {
            processCancel();
            btnCode = CANCEL;
            OkCancelDialog.this.setVisible(false);
        }
    };
}
