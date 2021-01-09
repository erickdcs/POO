import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener,  KeyListener{
	int idJugador = 0;
	int action = 0;
	
	JPanel container;
    JPanel panel1;
    JPanel panel1_1;
    JPanel panel1_2;
    JPanel panel2;
    JPanel panel2_1;
   
    JTextArea rondaActual;
    JTextArea rondasAnteriores;
    JTextField respuesta;
    JScrollPane rondaActualScroll;
    JScrollPane rondasAnterioresScroll;
    
    JButton boton1;
    JButton boton2;
    JButton boton3;
    JButton boton4;
    JButton boton5;
    JButton boton6;
    JButton boton7;
    
    public GUI(int id){
    	idJugador = id;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setTitle("Videojuego");
        this.setLocationRelativeTo(null);
        
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
        
        estadoJugador(idJugador);
        this.setVisible(true);
        
    }
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==boton1) {
			action = 1;
			rondaActual.setText(null);
		}
		if(e.getSource()==boton2) {
			rondaActual.setText(null);
			if(comprobarSiHayObjetoEnSala(idJugador) && !comprobarSiJugadorTieneObjeto(idJugador)) {
				action = 2;
				cogerObjetoEnSalaImprimir(idJugador);
			}
			else if(comprobarSiJugadorTieneObjeto(idJugador)) {
				rondaActual.append("Ya posees un Objeto, debes dejarlo primero en la Sala.");
			}
			else {
				rondaActual.append("No hay ningún Objeto en la Sala para coger.");
			}
		}
		if(e.getSource()==boton3) {
			rondaActual.setText(null);
			if(comprobarSiJugadorTieneObjeto(idJugador)) {
				action = 3;
				dejarObjetoEnSalaImprimir(idJugador);
			}
			else {
				rondaActual.append("No posees ningún Objeto.");
			}
		}
		if(e.getSource()==boton4) {
			action = 4;
			rondaActual.setText(null);
		}
		if(e.getSource()==boton5) {
			action = 5;
			rondaActual.setText(null);
		}
		if(e.getSource()==boton6) {
			action = 0;
			rondaActual.setText(null);
			estadoJugador(idJugador);
		}
		if(e.getSource()==boton7) {
			action = 6;
			rondaActual.setText(null);
			System.exit(0);
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) {
	       	if(action == 2) {
	        	boolean entradaValida = false;
	        	for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
	        		if(GestorPartida.getObjetoSala()[i].getNombreObjeto().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala())){
		    			entradaValida = true;		   				
		   				rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + GestorPartida.getJugadores()[idJugador].getSala() + "\n");
		   				Jugador.cogerObjeto(idJugador, GestorPartida.getObjetoSala()[i]);
		   				respuesta.setText(null);
		        		estadoJugador(idJugador);
		        		action = 0;
		        		break;		    			
		        	}
		    	}
		       	if(!entradaValida) {
		       		rondaActual.setText("**Por favor, escriba una entrada valida**\n");
		       		cogerObjetoEnSalaImprimir(idJugador);
		       		respuesta.setText(null);
		       	}
	       	}
	       	if(action == 3) {
	       	   if(respuesta.getText().equalsIgnoreCase("Si")) {
	       		   for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
	       				if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == idJugador) {       					
	       					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + GestorPartida.getJugadores()[idJugador].getSala() + "\n");
	       					Jugador.dejarObjeto(idJugador, GestorPartida.getObjetoJugador()[i]);
	       					respuesta.setText(null);
			        		estadoJugador(idJugador);
			        		action = 0;
			        		break;
	        			}
	        		}
	       	   }
	       	   else {
	       		   rondaActual.setText("**Por favor, escriba una entrada valida**\n");
	       		   dejarObjetoEnSalaImprimir(idJugador);
	       		   respuesta.setText(null);
	       	   }
	       	}
	       	if(action == 0) {
        		respuesta.setText(null);
	       	}
	     }
	}
	
	public boolean comprobarSiJugadorTieneObjeto(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarSiHayObjetoEnSala(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				return true;
			}
		}
		return false;
	}
	
	public void cogerObjetoEnSalaImprimir(int id) {
		rondaActual.append("Escribe el Objeto que deseas coger:");
		for(int i =0; i< GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append("\n- " + GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
	}
	
	public void dejarObjetoEnSalaImprimir(int id) {
		for(int i =0; i< GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append("¿Estas seguro de querer dejar el Objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + "?");
				rondaActual.append("\nEscribe Si para dejarlo.");
			}
		}
	}

	public void estadoJugador(int id) {
		rondaActual.setText(null);
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
		for(int i =0; i< GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append(GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			}
		}
		if(!comprobarSiJugadorTieneObjeto(id)) {
			rondaActual.append("Ninguno");
		}
	}
	
	public void objetosEnSala(int id) {
		rondaActual.append("\n\nObjeto en Sala: ");
		for(int i =0; i< GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getObjetoSala()[i].getNombreObjeto() + " ");
			}
		}
		if(!comprobarSiHayObjetoEnSala(id)) {
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}