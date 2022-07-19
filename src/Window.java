import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Window extends JFrame{
    private JButton xButton;
    private JTextField textField3;
    private JButton szukajButton;
    private JPanel Pan;
    private JLabel JLabeleResult;
    private JLabel Label_docInfo;

    public Window(Doctor doctor){


        Label_docInfo.setText(Label_docInfo.getText()+" "+doctor.getFirstName()+" "+doctor.getLastName());

        setContentPane(Pan);
        setTitle("Window");
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
        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pesel = textField3.getText();
                if(Patient.searchPatient(pesel)!=null){
                    Patient patient = Patient.searchPatient(pesel);
                    dispose();
                    new PatientInfo(patient,doctor);

                }else {
                    JLabeleResult.setForeground(Color.RED);
                    JLabeleResult.setText("Nie ma takiego pacjenta.");
                }
            }
        });
    }

}
