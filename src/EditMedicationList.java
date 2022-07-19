import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditMedicationList extends JFrame {

    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JLabel headLabel;
    private JButton deleteButton;
    private JButton addButton;
    private JButton backButton;
    private JTable table;
    private JTable checkTable;
    private TableModel model;

    private JLabel nameIntr;
    private JTextPane name;

    public EditMedicationList(Patient patient,Doctor doctor){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400,350);

        Medication[] medications = new Medication[patient.getMedications().size()];

        Object rows[][] = new Object[medications.length][3];
        String columns[] = {"Nazwa","Przechowywanie","Boolean"};

        int i=0;
        for (Medication m:patient.getMedications()) {
            medications[i] = m;
            rows[i][0] = m.getName();
            rows[i][1] = m.getStorageWay();
            rows[i][2] = Boolean.FALSE;
            i++;
        }

        model = new DefaultTableModel(rows,columns){
            @Override
            public String getColumnName(int column) {
                return columns[column];
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return rows[0][columnIndex].getClass();
            }
        };
        table = new JTable(model);
        table.setShowGrid(false);
        table.setFont(new Font("Arial Narrow",Font.ITALIC,20));
        table.setForeground(Color.DARK_GRAY);
        table.setEnabled(true);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane jScrollPane = new JScrollPane(table);
        name = new JTextPane();
        name.setFont(new Font("Arial Narrow",Font.ITALIC,16));

        nameIntr = new JLabel("Nazwa leku: ");
        nameIntr.setFont(new Font("Arial Narrow",Font.BOLD,16));
        nameIntr.setForeground(Color.BLACK);

        headLabel = new JLabel("Edycja leków pacjenta "+patient.getFirstName()+" "+patient.getLastName());
        headLabel.setFont(new Font("Arial Narrow",Font.BOLD,30));

        tablePanel = new JPanel(new GridLayout());
        tablePanel.add(jScrollPane);

        textPanel = new JPanel(new GridLayout());
        textPanel.add(headLabel);

        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        backButton = new JButton("Powrót");
        buttonPanel = new JPanel(new GridLayout());

        buttonPanel.setBackground(new Color(182,182,182));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        buttonPanel.add(name,FlowLayout.LEFT);
        buttonPanel.add(nameIntr,FlowLayout.LEFT);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textPanel,BorderLayout.NORTH);
        mainPanel.add(tablePanel,BorderLayout.CENTER);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setTitle("EditMedicationList");
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!name.getText().equals("")){
                    String nameString=name.getText();

                    if(doctor.getMed(nameString)==null){
                        JOptionPane.showMessageDialog(null, "Nie ma takiego leku w magazynach.");
                    }else {
                        String mes = doctor.addMedicationToList(patient,doctor.getMed(nameString));
                        dispose();
                        JOptionPane.showMessageDialog(null, mes);
                        new EditMedicationList(patient, doctor);
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Medication> checkedMedications = new ArrayList<>();
                for (int i =0;i<medications.length;i++){
                    if(model.getValueAt(i,2).equals(true)){
                        checkedMedications.add(medications[i]);
                    }
                }
                String mes="";
                for (Medication m:checkedMedications) {
                    mes = doctor.removeMedicationFromList(patient,m);
                }
                if(!mes.equals("")) {
                    dispose();
                    JOptionPane.showMessageDialog(null, mes);
                    new EditMedicationList(patient,doctor);
                }else JOptionPane.showMessageDialog(null, "Nic nie zaznaczono.");
            }
        });
    }
}
