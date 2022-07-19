import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

public class PatientStory extends JFrame {
    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel tablePanel;
    private JPanel polePanel;
    private JPanel buttonPanel;
    private JLabel headLabel;
    private JButton editButton;
    private JButton backButton;
    private JTable table;
    private TableModel model;
    private JTextPane dataPane;
    private JTextPane textPane;
    private JLabel doctorLabel;

    public PatientStory(Patient patient,Doctor doctor){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400,350);

        HealthStory[] healthStories = new HealthStory[patient.getHealthStories().size()];

        Object[][] rows = new Object[healthStories.length][3];
        String[] columns = {"Data","Opis","Lekarz"};

        int i=0;
        for (HealthStory h:patient.getHealthStories()) {
            healthStories[i] = h;
            rows[i][0] = h.getDate();
            rows[i][1] = h.getText();
            rows[i][2] = h.getDoctor().getLastName();
            i++;
        }

        model = new DefaultTableModel(rows,columns);
        table = new JTable(model);
        table.setShowGrid(false);
        table.setFont(new Font("Arial Narrow",Font.ITALIC,20));
        table.setForeground(Color.DARK_GRAY);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane jScrollPane = new JScrollPane(table);

        headLabel = new JLabel("Historia zdrowia pacjenta  "+patient.getFirstName()+" "+patient.getLastName());
        headLabel.setFont(new Font("Arial Narrow",Font.BOLD,30));

        dataPane = new JTextPane();
        dataPane.setSize(10,2);
        textPane = new JTextPane();
        doctorLabel = new JLabel(doctor.getFirstName()+" "+doctor.getLastName());
        doctorLabel.setFont(new Font("Arial Narrow",Font.ITALIC,20));

        tablePanel = new JPanel(new GridLayout());
        tablePanel.add(jScrollPane);

        textPanel = new JPanel(new GridLayout());
        textPanel.add(headLabel);

        polePanel = new JPanel(new BorderLayout());

        JLabel l1 = new JLabel("Doktor ");
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(l1,BorderLayout.NORTH);
        p1.add(doctorLabel,BorderLayout.SOUTH);

        JLabel l2 = new JLabel("Data (RRRR-MM-DD)");
        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(l2,BorderLayout.CENTER);
        p2.add(dataPane,BorderLayout.SOUTH);

        JLabel l3 = new JLabel("Opis ");
        JPanel p3 = new JPanel(new BorderLayout());
        p3.add(l3,BorderLayout.CENTER);
        p3.add(textPane,BorderLayout.SOUTH);

        polePanel.add(p1,BorderLayout.NORTH);
        polePanel.add(p2,BorderLayout.CENTER);
        polePanel.add(p3,BorderLayout.SOUTH);



        editButton = new JButton("Dodaj");
        backButton = new JButton("Powrót");
        buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(editButton);
        buttonPanel.add(backButton);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textPanel,BorderLayout.NORTH);
        mainPanel.add(tablePanel,BorderLayout.CENTER);
        mainPanel.add(polePanel,BorderLayout.EAST);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setTitle("MedicationList");
        setLocation(500,200);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String text = textPane.getText();
                    LocalDate localDate = LocalDate.parse(dataPane.getText());
                    if (!text.equals("")) {
                        String com = doctor.modifyPatientStory(patient,localDate,text);
                        dispose();
                        JOptionPane.showMessageDialog(null, com);
                        new PatientStory(patient,doctor);
                    } else JOptionPane.showMessageDialog(null, "Popraw dane.");
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Popraw dane.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PatientInfo(patient,doctor);
            }
        });
    }
}
