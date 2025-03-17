import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FrmEstadistica extends JFrame{

    private JTextField txtDato;
    private JList lstMuestra;
    private JComboBox cmbEstadistica;
    private JTextField txtEstadistica;

    // Metodo Constructor
    public FrmEstadistica() {
        setSize(500, 300);
        setTitle("Estadistica");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDato = new JLabel("Dato:");   
        lblDato.setBounds(10, 10, 100, 25);
        getContentPane().add(lblDato);

        txtDato = new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(110, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(110, 70, 100, 25);
        getContentPane().add(btnQuitar);

        JLabel lblMuestra = new JLabel("Muestra:");
        lblMuestra.setBounds(220, 10, 100, 25);
        lblMuestra.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblMuestra);

        lstMuestra = new JList();
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(220, 40, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnEstadistica = new JButton("Estadistica");
        btnEstadistica.setBounds(10,200,100,25);
        getContentPane().add(btnEstadistica);

        cmbEstadistica = new JComboBox();
        String[] opciones = new String[]{"Sumatoria", "Promedio", "Desviacion Estandar", "Maximo", "Minimo", "Moda"};
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(opciones);
        cmbEstadistica.setModel(dcm);
        cmbEstadistica.setBounds(110,200,100,25);
        getContentPane().add(cmbEstadistica);

        txtEstadistica = new JTextField();
        txtEstadistica.setBounds(220, 200, 100, 25);
        txtEstadistica.setEditable(false);
        getContentPane().add(txtEstadistica);

        //Crear Eventos
        btnAgregar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });

        btnQuitar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDato();
            }
        });

        btnEstadistica.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadistica();
            }
        });

    }

    private int MAXIMO_DATOS = 1000;
    private double[] muestra = new double[MAXIMO_DATOS];
    private int totalDatos = 0;

    private void agregarDato(){
        if(totalDatos<MAXIMO_DATOS) {
            muestra[totalDatos] = Double.parseDouble(txtDato.getText());
            totalDatos++;
            mostrarDatos();

            System.out.println(totalDatos);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas datos");
        }
    }

    private void mostrarDatos(){
        String[] strMuestra = new String[totalDatos];
        for (int i = 0; i < totalDatos; i++) {
            strMuestra[i] = String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato(){
        if(lstMuestra.getSelectedIndex()>=0){
            for(int i = lstMuestra.getSelectedIndex(); i<totalDatos-1; i++){
                muestra[i] = muestra[i+1];
            }
            totalDatos--;
            mostrarDatos();
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar el dato a quitar");
        }
    }

    private double sumatoria(){
        double suma = 0;
        for (int i = 0; i < totalDatos; i++) {
            suma += muestra[i];
        }
        return suma;
    }

    private double promedio(){
        return totalDatos>0?sumatoria()/totalDatos:0;
    }

    private double desviacionEstandar(){
        if (totalDatos>0) {
            double promedio = promedio();
            double suma = 0;
            for (int i = 0; i < totalDatos; i++) {
                suma += Math.abs(promedio-muestra[i]);
            }
            return suma / (totalDatos-1);
        }
    }

    private double maximo(){
        double max = muestra[0];
        for (int i = 1; i < totalDatos; i++) {
            if (muestra[i]>max) {
                max = muestra[i];
            }
        }
        return max;
    }


    private void calcularEstadistica(){
        switch (cmbEstadistica.getSelectedIndex()) {
            case 0:
                txtEstadistica.setText(String.valueOf(sumatoria()));
                break;
            case 1:
                txtEstadistica.setText(String.valueOf(promedio()));
                break;
            case 2:
                txtEstadistica.setText(String.valueOf(desviacionEstandar()));
                break;
            case 3:
                txtEstadistica.setText(String.valueOf(maximo()));
                break;
        }
    }
}

// TERMINAR EL CODIGO DE LA CLASE FrmEstadistica
