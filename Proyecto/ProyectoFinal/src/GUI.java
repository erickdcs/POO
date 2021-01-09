import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener,  KeyListener{
	int idJugador =0;
	JPanel container;
    JPanel panel1;
    JPanel panel1_1;
    JPanel panel1_2;
    JPanel panel2;
    JPanel panel2_1;
   
    JTextArea rondaActual;
    JTextArea rondasAnteriores;
    JScrollPane rondaActualScroll;
    JScrollPane rondasAnterioresScroll;
    
    JButton boton1;
    JButton boton2;
    JButton boton3;
    JButton boton4;
    JButton boton5;
    JButton boton6;
    JButton boton7;
    JTextField respuesta;
    int action = 0;
    public GUI(int id){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setTitle("Videojuego");

        container = new JPanel();
        panel1 = new JPanel();
        panel1_1 = new JPanel();
        panel1_2 = new JPanel();
        panel2 = new JPanel();
        panel2_1 = new JPanel();
        respuesta = new JTextField();
        rondaActual = new JTextArea(13,28);
        rondasAnteriores = new JTextArea(15,30);
        rondaActualScroll = new JScrollPane(rondaActual);
        rondasAnterioresScroll = new JScrollPane(rondasAnteriores);
        idJugador = id;
        boton1 = new JButton("Moverse de Sala");
        boton2 = new JButton("Coger Objeto");
        boton3 = new JButton("Dejar Objeto");
        boton4 = new JButton("Pedir Objeto");
        boton5 = new JButton("Dar Objeto");
        boton6 = new JButton("Mostrar Informacion");
        boton7 = new JButton("Pasar Turno");
        
        panel2.add(new JLabel("Acciones"));
        panel1_1.add(new JLabel("Ronda Actual"));
        panel1_2.add(new JLabel("Rondas Anteriores"));
        
        panel1_1.setBackground(new Color(240,60,20));    
        panel1_2.setBackground(new Color(20,220,240));   
        panel2.setBackground(new Color(20,240,40));  
        
        container.setLayout(new GridLayout(1,2));        
        this.add(container);
        
        container.add(panel1);
        container.add(panel2);
        
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(panel1_1);
        panel1.add(panel1_2);
        
        rondaActual.setLineWrap(true);
        rondaActual.setWrapStyleWord(true);
        rondaActual.setEditable(false);
        
        rondasAnteriores.setLineWrap(true);
        rondasAnteriores.setWrapStyleWord(true);
        rondasAnteriores.setEditable(false);
        
        respuesta.setPreferredSize(new Dimension(323, 20));
        respuesta.addKeyListener(this);     
        
        panel1_1.add(rondaActualScroll);
        panel1_2.add(rondasAnterioresScroll);
        panel1_1.add(respuesta);     
        panel2_1.setPreferredSize(new Dimension(300, 525));
        panel2.add(panel2_1);

        panel2_1.setLayout(new GridLayout(7, 1));
        panel2_1.add(boton1);
        panel2_1.add(boton2);
        panel2_1.add(boton3);
        panel2_1.add(boton4);
        panel2_1.add(boton5);
        panel2_1.add(boton6);
        panel2_1.add(boton7);
        
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        boton4.addActionListener(this);
        boton5.addActionListener(this);
        boton6.addActionListener(this);
        boton7.addActionListener(this);
        
        this.setVisible(true);
        
    }
    //public static void main(String argv[]) {
    	//salasVecinas();
    //}
	
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==boton1) {
			action = 1;
			rondaActual.setText(null);
		}
		if(e.getSource()==boton2) {

		}
		if(e.getSource()==boton3) {

		}
		if(e.getSource()==boton4) {

		}
		if(e.getSource()==boton5) {

		}
		if(e.getSource()==boton6) {
			rondaActual.setText(null);
			estadoJugador(idJugador);
		}
		if(e.getSource()==boton7) {
			rondaActual.setText(null);
			System.exit(0);
		}
		
		
		
	}
	public void estadoJugador(int id) {
		rondaActual.append("Nombre del Jugador: " + GestorPartida.getJugadores()[id].getNombre());
		objetoJugador(id);
		rondaActual.append("\n\nSala: " + GestorPartida.getJugadores()[id].getSala());
		salasVecinas(id);
		objetosEnSala(id);
		objetosEnJugador(id);
		objetivosJugador(id);
		creenciasJugador(id);
	}
	public void salasVecinas(int id) {
		rondaActual.append("\n\nSalas vecinas: ");
		String salas[] =  GestorPartida.verSalasVecinas(GestorPartida.getJugadores()[id].getId());
		for(int i =0; i< salas.length; i++) {
			rondaActual.append(salas[i] + " ");
		}
	}
	public void objetoJugador(int id) {
		rondaActual.append("\n\nObjeto: ");
		boolean Tiene = false;
		for(int i =0; i< GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append(GestorPartida.getObjetoJugador()[i].getNombreObjeto());
				Tiene = true;
			}
		}
		if(Tiene == false) {
			rondaActual.append("Ninguno");
		}
	}
	public void objetosEnSala(int id) {
		rondaActual.append("\n\nObjeto en Sala: ");
		boolean Tiene = false;
		for(int i =0; i< GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getObjetoSala()[i].getNombreObjeto() + " ");
				Tiene = true;
			}
		}
		if(Tiene == false) {
			rondaActual.append("Ninguno");
		}
	}
	public void objetosEnJugador(int id) {
		rondaActual.append("\n\nJugadores en la misma sala:\n");
		boolean tiene = false;
		boolean hayJugadores = false;
		for (int i =0; i < GestorPartida.getContJugadores(); i++) {
			tiene = false;
			if(GestorPartida.getJugadores()[i].getId() != id && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getJugadores()[i].getNombre() + ": ");
				for(int j =0; j< GestorPartida.getContObjetosJugador();j++) {
					if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						rondaActual.append(GestorPartida.getObjetoJugador()[j].getNombreObjeto());
						tiene = true;
						hayJugadores = true;
					}
				}
				if(tiene ==false) {
					rondaActual.append("Ninguno");
				}
				rondaActual.append("\n");
			}
			
		}
		if(hayJugadores == false) {
			rondaActual.append("Ninguno");
		}
	}
	public void objetivosJugador(int id) {
		rondaActual.append("\n\nObjeto objetivo: " + GestorPartida.getJugadores()[id].getObjetivoObjeto());
		rondaActual.append("\nSala objetivo: " + GestorPartida.getJugadores()[id].getObjetivoSala());
	}
	public void creenciasJugador(int id) {
		rondaActual.append("\n\nCreencias:");
		for(int i = 0; i < GestorPartida.getContJugadores()-1; i++) {
			rondaActual.append("\nJugador: " + GestorPartida.getJugadores()[id].getCreencias().getNombrePersona()[i]);
			if(GestorPartida.getJugadores()[id].getCreencias().getSalaPersona()[i] == null) {
				rondaActual.append("   Sala: Desconocida");
			}else {
				rondaActual.append("   Sala: " + GestorPartida.getJugadores()[id].getCreencias().getSalaPersona()[i]);
			}
		}
		for(int i = 0; i < GestorPartida.getContObjetosSala() + GestorPartida.getContObjetosJugador(); i++) {
			rondaActual.append("\nObjeto: " + GestorPartida.getJugadores()[id].getCreencias().getNombreObjeto()[i]);
			if(GestorPartida.getJugadores()[id].getCreencias().getLugarObjeto()[i] == null) {
				rondaActual.append("   Lugar: Desconocido");
			}else {
			rondaActual.append("   Lugar: " + GestorPartida.getJugadores()[id].getCreencias().getLugarObjeto()[i]);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
	        if (key == KeyEvent.VK_ENTER) {
	           if(action == 1) {
	        	   
	           }
	        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


/*rondaActual.append("\nJugadores en Sala: ");
boolean Tiene = false;
for(int i =0; i< GestorPartida.getContJugadores(); i++) {
	if(i != id && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
		rondaActual.append(GestorPartida.getJugadores()[i].getNombre() + " : ");
		for(int j =0; j< GestorPartida.getContObjetosJugador(); j++) {
			if(GestorPartida.getObjetoJugador()[j].getJugador().getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getObjetoJugador()[j].getNombreObjeto() + " ");
				Tiene = true;
			}
		}
		if(Tiene == false) {
			rondaActual.append("Ninguno");
		}
	}
	
	
}*/