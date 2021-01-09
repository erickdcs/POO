import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUICambiarSala extends JFrame implements ActionListener{
	JPanel container;
    JPanel panel1;
    int idJugador = 0; 
    int j = 0;
    
    JButton []boton = new JButton[10];
	
	public GUICambiarSala(int id, int i) {
		idJugador = id;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setTitle("Cambio de Sala");

        container = new JPanel();
        panel1 = new JPanel();
       
        
        container.add(new JLabel("Selecciona la sala a la que deseas moverte"));
        panel1.setPreferredSize(new Dimension(300,220));
       
       
        panel1.setLayout(new GridLayout((GestorPartida.getSalas()[i].getSalasVecinas().length/2), (GestorPartida.getSalas()[i].getSalasVecinas().length/2)));
        
        
	    for( j =0; j < GestorPartida.getSalas()[i].getSalasVecinas().length; j++) {
	    	boton[j] =  new JButton(GestorPartida.getSalas()[i].getSalasVecinas()[j]);
	    	boton[j].addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			GestorPartida.getJugadores()[id].cambiarSala(GestorPartida.getSalas()[i].getSalasVecinas()[j]);
	    			System.exit(0);
	    		}
	    	});
	    	panel1.add(boton[j]);
	    }
	    
	    container.add(panel1);
	    this.add(container);
	    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		
		idJugador =2;
	}
}
