import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicationList extends JFrame {

    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel headLabel;
    private JButton editButton;
    private JButton backButton;
    private JTable table;
    private TableModel model;

    public MedicationList(Patient patient,Doctor doctor){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400,350);

        Medication[] medications = new Medication[patient.getMedications().size()];

        Object rows[][] = new Object[medications.length][2];
        String columns[] = {"Nazwa","Przechowywanie"};

        int i=0;
        for (Medication m:patient.getMedications()) {
            medications[i] = m;
            rows[i][0] = m.getName();
            rows[i][1] = m.getStorageWay();
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


        headLabel = new JLabel("Leki pacjenta "+patient.getFirstName()+" "+patient.getLastName());
        headLabel.setFont(new Font("Arial Narrow",Font.BOLD,30));

        tablePanel = new JPanel(new GridLayout());
        tablePanel.add(jScrollPane);

        textPanel = new JPanel(new GridLayout());
        textPanel.add(headLabel);

        editButton = new JButton("Edytuj");
        backButton = new JButton("Powrót");
        buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(editButton);
        buttonPanel.add(backButton);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textPanel,BorderLayout.NORTH);
        mainPanel.add(tablePanel,BorderLayout.CENTER);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setTitle("MedicationList");
        setLocation(500,200);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PatientInfo(patient,doctor);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditMedicationList(patient,doctor);
            }
        });

    }
}
