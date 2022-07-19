import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientInfo extends JFrame{
    private JPanel Pan;
    private JButton xButton;
    private JButton edytujButton;
    private JButton zobaczButton1;
    private JButton zobaczButton;
    private JLabel LabelImie;
    private JLabel LabelNazwisko;
    private JLabel LabelPesel;

    public PatientInfo(Patient patient,Doctor doctor){
        LabelImie.setText(patient.getFirstName());
        LabelNazwisko.setText(patient.getLastName());
        LabelPesel.setText(patient.getPesel());

        setContentPane(Pan);
        setTitle("PatientInfo");
        setLocation(500,200);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        edytujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditMedicationList(patient,doctor);
            }
        });
        zobaczButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MedicationList(patient,doctor);
            }
        });

        zobaczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PatientStory(patient,doctor);
            }
        });
    }
}
