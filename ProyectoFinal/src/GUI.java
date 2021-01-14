import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener,  KeyListener{
	
	//Definici�n de las variables para la acci�n de un jugador en la interfaz gr�fica.
	private int idJugador = 0;
	private int accion = 0;
	private int numeroDeEntrada = 0;
	private int turnosSaltados = 0;
	
	private String historiaCompleta = "";
	
	//Definici�n de las variables que conforman la interfaz gr�fica.
	private String nombre;
	private JPanel container;
	private JPanel panel1;
	private JPanel panel1_1;
	private JPanel panel1_2;
	private JPanel panel2;
	private JPanel panel2_1;
   
	private JTextArea rondaActual;
	private JTextArea rondasAnteriores;
	private JTextField respuesta;
	private JScrollPane rondaActualScroll;
	private JScrollPane rondasAnterioresScroll;
    
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	private JButton boton7;
    
	//Creaci�n y definici�n de las caracter�sticas de la interfaz gr�fica.
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
         
        GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(idJugador);
		GestorPartida.setRondas(1);
		GestorPartida.setJugadorActivo(0);
		
        estadoJugador(idJugador);
        this.setVisible(true);
        
    }
	
    //Definici�n de las acciones seg�n el bot�n pulsado.
	public void actionPerformed(ActionEvent e) {
		//Este if permite accionar los botones cuando la partida no se haya finalizado.
		if(accion != -1) {
			//Acci�n para cambiar de sala.
			if(e.getSource() == boton1) {
				
				rondaActual.setText(null);
				cambiarSalaImprimir(idJugador);
				accion = GestorPartida.getJugadores()[idJugador].dameAccion("Moverse");
			}
			//Acci�n para coger un objeto.
			if(e.getSource() == boton2) {
				rondaActual.setText(null);
				if(comprobarSiHayObjetoEnSala(idJugador) && !comprobarSiJugadorTieneObjeto(idJugador)) {
					accion = GestorPartida.getJugadores()[idJugador].dameAccion("Coger");;
					cogerObjetoEnSalaImprimir(idJugador);
				}
				else if(comprobarSiJugadorTieneObjeto(idJugador)) {
					rondaActual.append("Ya posees un Objeto, debes dejarlo primero en la Sala.");
				}
				else {
					rondaActual.append("No hay ningun Objeto en la Sala para coger.");
				}
			}
			//Acci�n para dejar un objeto.
			if(e.getSource() == boton3) {
				rondaActual.setText(null);
				if(comprobarSiJugadorTieneObjeto(idJugador)) {
					
					accion = GestorPartida.getJugadores()[idJugador].dameAccion("Dejar");
					dejarObjetoEnSalaImprimir(idJugador);
				}
				else {
					rondaActual.append("No posees ningun Objeto.");
				}
			}
			//Acci�n para pedir un objeto a otro jugador.
			if(e.getSource() == boton4) {
				rondaActual.setText(null);
				if(comprobarJugadoresConObjetosEnSala(idJugador)) {
					accion = GestorPartida.getJugadores()[idJugador].dameAccion("Pedir");
					objetosEnJugador(idJugador);
					rondaActual.append("\nIndica el nombre del Jugador al que deseas pedir un Objeto:");
		    		
				}
				else if(!GestorPartida.jugadorEnSala(idJugador)){
					rondaActual.append("No hay ningun Jugador en la misma Sala que tu.");
				}
				else {
					rondaActual.append("Ningun Jugador de la Sala tiene un Objeto.");
				}
				
			}
			//Acci�n para dar el objeto que haya pedido otro jugador.
			if(e.getSource() == boton5) {
				rondaActual.setText(null);
				if(comprobarSiJugadorTieneObjeto(idJugador)) {
					if(comprobarSiExistePeticionJugadorEnSala(idJugador)) {
						if(comprobarJugadoresSinObjetosEnSala(idJugador)) {
							accion = GestorPartida.getJugadores()[idJugador].dameAccion("Dar");
							mostrarPeticiones(idJugador);
							rondaActual.append("\n\nIndica el nombre del Jugador al que deseas dar un Objeto:");
				    		
						}
						else if(!GestorPartida.jugadorEnSala(idJugador)){
							rondaActual.append("No hay ningun Jugador en la misma Sala que tu.");
						}
						else {
							rondaActual.append("No hay ningun Jugador en la misma Sala que no posea un Objeto.");
						}
					}
					else {
						rondaActual.append("No posees Peticiones accesibles ahora mismo.");
					}
				}
				else {
					rondaActual.append("No posees ningun Objeto.");
				}
				
			}
			//Acci�n para imprimir toda la informaci�n actual de la ronda.
			if(e.getSource() == boton6) {
				accion = GestorPartida.getJugadores()[idJugador].dameAccion("Info");
				rondaActual.setText(null);
				estadoJugador(idJugador);
			}
			//Acci�n para pasar el turno
			if(e.getSource( )== boton7) {
				accion = GestorPartida.getJugadores()[idJugador].dameAccion("Pasar");
				rondaActual.setText("�Seguro que deseas saltar tu Turno?");
				rondaActual.append("\n\nEscribe Si para continuar.");
			}
		}
	}
	
	//Lectura de las entradas escritas por el jugador para interactuar con las diferentes acciones, tras haber presionado la tecla ENTER.
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) {
	    	 //Toda entrada del usuario es revisada para comprobar que esta sea correcta, en caso de que no lo sea se muestra un mesaje de error y se vuelte a pedir la entrada.
	    	 
	    	 //Desplazamiento del jugador a la sala introducida.
	    	if(accion == 1) {
	    		cambiarSala(idJugador);	    		
	    	}
	    	
	    	//Transformaci�n del objeto de sala introducido por el jugador a un objeto de su jugador.
	       	if(accion == 2) {
	        	boolean entradaValida = false;
	        	
	        	for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
	        		if(GestorPartida.getObjetoSala()[i].getNombreObjeto().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala())){
		    			entradaValida = true;
		    			historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + GestorPartida.getJugadores()[idJugador].getSala() + ".\n";
		   				rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + GestorPartida.getJugadores()[idJugador].getSala() + ".\n");
		   				Jugador.cogerObjeto(idJugador, GestorPartida.getObjetoSala()[i]);
		   				respuesta.setText(null);	
		   				turnosSaltados = 0;
		   				finalDeTurno();
		        		break;		    			
		        	}
		    	}
		       	if(!entradaValida) {
		       		rondaActual.setText("**Por favor, escriba una entrada valida**\n");
		       		cogerObjetoEnSalaImprimir(idJugador);
		       		respuesta.setText(null);
		       		
		       	}
	       	}
	       	
	      //Transformaci�n del objeto del jugador en un objeto de la sala en la que se encuentra.
	       	if(accion == 3) {
	       	   if(respuesta.getText().equalsIgnoreCase("Si")) {
	       		   for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
	       				if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == idJugador) {       
	       					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + GestorPartida.getJugadores()[idJugador].getSala() + ".\n";
	       					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + GestorPartida.getJugadores()[idJugador].getSala() + ".\n");
	       					Jugador.dejarObjeto(idJugador, GestorPartida.getObjetoJugador()[i]);
	       					respuesta.setText(null);	
	       					turnosSaltados = 0;
	       					finalDeTurno();
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
	       	
	       	//Creaci�n de una petici�n de un objeto a otro jugador, ambos datos son introducidos por el usuario
	    	if(accion == 4) {
	    		boolean entradaValida = false;
	    		boolean objeto = false;
	    		
	    		//En esta primera parte, se pide al usuario el jugador al que desea realizar la petici�n.
	    		if(numeroDeEntrada == 0) {
	    			for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
		        		if(GestorPartida.getJugadores()[i].getNombre().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala()) && i != idJugador){
		        			entradaValida = true;	        			
		        			nombre = respuesta.getText();
		        			respuesta.setText(null);
		        			numeroDeEntrada = 1;
		        			rondaActual.setText(null);
		        			objetosEnJugador(idJugador);
		        			rondaActual.append("\nIndica el nombre del Objeto que deseas pedir:");
		        			break;
		        			
			        	}
		        		
			    	}
		    		if(!entradaValida) {
			       		rondaActual.setText("**Por favor, escriba una entrada valida**\n");
			       		objetosEnJugador(idJugador);
						rondaActual.append("\nIndica el nombre del Jugador al que deseas pedir un Objeto:");
						respuesta.setText(null);
		    		}
	    		}
	    		
	    		//En esta segunda parte, se pide al usuario el objeto que desea pedir.
	    		else if(numeroDeEntrada == 1) {	    		   			
	    			rondaActual.setText(nombre);
	    			
	    			for(int j = 0; j < GestorPartida.getContObjetosSala(); j++) {
	    				if(GestorPartida.getObjetoSala()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText())) {
	    					objeto = true;	    					
	    					GestorPartida.getJugadores()[idJugador].hacerPeticion(nombre, respuesta.getText());
	    					numeroDeEntrada = 0;
	    					respuesta.setText(null);
	    					turnosSaltados = 0;
	    					finalDeTurno();
	    					break;
	    				}
	    			}
	    			for(int j = 0; j < GestorPartida.getContObjetosJugador(); j++) {
	    				if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText())) {	    					
	    					objeto = true;
	    					GestorPartida.getJugadores()[idJugador].hacerPeticion(nombre, respuesta.getText());
	    					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha pedido el Objeto " + respuesta.getText()  + " a " + nombre + ".\n";
	    					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha pedido el Objeto " + respuesta.getText()  + " a " + nombre + ".\n");
	    					numeroDeEntrada = 0;
	    					respuesta.setText(null);
	    					turnosSaltados = 0;
	    					finalDeTurno();
	    					break;
	    				}
	    			}
	    			if(!objeto) {	    				
			       		rondaActual.setText("**Por favor, escriba una entrada valida**\n");
			       		objetosEnJugador(idJugador);
			       		rondaActual.append("\nIndica el nombre del Objeto que deseas pedir:");
			       		respuesta.setText(null);			       		
			       	}	    			
	    		}	    		
	    	}
	    	
	    	//Tratamiento de una petici�n realizada por otro jugador para aceptarla, introduciendo el jugador y el objeto que se desea dar.
	    	if(accion == 5) {
	    		boolean entradaValida = false;
	    		boolean objeto = false;
	    		boolean peticionValida = false;
	    		String objetoPosee = nombreDelObjetoQuePosee(idJugador);
	    		rondaActual.append(objetoPosee + "\n");
	    		int i = 0;
	    		int peticion = 0;
	    		
	    		//En esta primera parte, se pide al usuario el jugador al que desea entregar el objeto pedido.
	    		if(numeroDeEntrada == 0) {
	    			peticionValida = false;
	    				    			
	    			for(i = 0; i < GestorPartida.getContJugadores(); i++) {
			        	if(!GestorPartida.getJugadores()[idJugador].getNombre().equalsIgnoreCase(respuesta.getText()) &&GestorPartida.getJugadores()[i].getNombre().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala())){
			        		
			       			for(peticion = 0; peticion < 10 && GestorPartida.getJugadores()[idJugador].getPeticiones()[peticion] != null; peticion++) {
				   				if(GestorPartida.getJugadores()[idJugador].getPeticiones()[peticion].getJugadorPide().equalsIgnoreCase(respuesta.getText()) && objetoPosee.equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getPeticiones()[peticion].getObjeto())) {
				   					peticionValida = true;
				   					break;
			    				}
			    			}
		        			if(peticionValida) {
		        				entradaValida = true;	
				       			nombre = respuesta.getText();
				       			
				       			respuesta.setText(null);
				       			numeroDeEntrada = 1;
				       			rondaActual.setText(null);
				       			mostrarPeticiones(idJugador);
				       			rondaActual.append("\n\nIndica el nombre del Objeto que deseas dar:");
				       			break;	
		        			}		        			
				        }		        		
				    }
	    			 			
	    			if(!entradaValida) {
	    				rondaActual.setText("**Por favor, escriba una entrada valida**\n");
			       		mostrarPeticiones(idJugador);
			       		rondaActual.append("\n\nIndica el nombre del Jugador al que deseas dar un Objeto:");
						respuesta.setText(null);
						numeroDeEntrada = 0;
		    		}
	    			else if(!peticionValida) {
	    				rondaActual.setText("Entrada no valida, no posees el Objeto que te pidio ese Jugador");
	    				mostrarPeticiones(idJugador);
	    				rondaActual.append("\n\nIndica el nombre del Jugador al que deseas dar un Objeto:");
						numeroDeEntrada = 0;
						respuesta.setText(null);
	    			}
	    		}
	    		
	    		//En esta segunda parte, se pide al usuario el objeto que desea dar.
	    		else if(numeroDeEntrada == 1) {	    			
	    			for(int j = 0; j < GestorPartida.getContObjetosJugador(); j++) {
	    				if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getNombre())) {
	    					objeto = true;
	    					
	    					GestorPartida.getJugadores()[idJugador].darObjeto(nombre, respuesta.getText());
	    					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha dado el Objeto " +respuesta.getText()  + " a " + nombre + ".\n";
	    					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha dado el Objeto " +respuesta.getText()  + " a " + nombre + ".\n");
	    					respuesta.setText(null);
	    					numeroDeEntrada = 0;
	    					turnosSaltados = 0;
	    					finalDeTurno();
	    					break;
	    				}
	    			}
	    			if(!objeto) {
	    				
			       		rondaActual.setText("**Por favor, escriba una entrada valida**\n");
			       		mostrarPeticiones(idJugador);
			    		rondaActual.append("\n\nIndica el nombre del Objeto que deseas dar:");
			       		respuesta.setText(null);
			       		
			       	}
	    			
	    		}
	    		
	    	}
	    	
	    	//Salto del turno cuando el usuario introduce 'Si' a la pregunta mostrada por pantalla.
	    	if(accion == 7) {
	    		if(respuesta.getText().equalsIgnoreCase("si")) {
	    			rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha saltado su Turno.\n");
	    			historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha saltado su Turno.\n" ;
					turnosSaltados++;
					//En el caso de que todos los jugadores hayan pasado su turno, se finaliza automaticamente la partida.
					if(turnosSaltados == GestorPartida.getContJugadores()) {
						rondaActual.setText("**Todos los Jugadores han saltado su Turno**\n\n");
						finDePartida();
					}
					else {
						finalDeTurno();
						accion = 0;						
					}
	    		}
	    		else {
	    			rondaActual.setText("**Por favor, escriba una entrada valida**\n");
	    			rondaActual.append("�Seguro que deseas saltar tu Turno?");
	    			rondaActual.append("\n\nEscribe Si para continuar.");
		       		respuesta.setText(null);
	    		}
	    	}
	    	//Esta acci�n impide que el jugador escriba entradas que puedan afectar al funcionamiento del videojuego cuando estas no se piden.
	       	if(accion == 0) {	       		
        		respuesta.setText(null);
	       	}
	     }
	}
	
	//M�todo que devuelve true si existe alguna pedicion de un jugador en la misma sala.
	public boolean comprobarSiExistePeticionJugadorEnSala(int id) {
		String nombreObjeto = null;
		
		if(GestorPartida.getJugadores()[id].getPeticiones()[0] ==null) {
			return false;
		}
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				nombreObjeto = GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		
		for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala()) && i != id) {
				for(int j = 0; GestorPartida.getJugadores()[id].getPeticiones()[j]!=null && j < 10; j++) {
					if(GestorPartida.getJugadores()[id].getPeticiones()[j].getJugadorPide().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre()) && GestorPartida.getJugadores()[id].getPeticiones()[j].getObjeto().equalsIgnoreCase(nombreObjeto)) {
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//M�todo que devuelve true si el jugador posee un objeto.
	public boolean comprobarSiJugadorTieneObjeto(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	//M�todo que devuelve true si existen otros jugador con objetos en la misma sala.
	public boolean comprobarJugadoresConObjetosEnSala(int id) {
		for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala()) && i != id) {
				if(comprobarSiJugadorTieneObjeto(GestorPartida.getJugadores()[i].getId())) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Preguntar
	public boolean comprobarJugadoresSinObjetosEnSala(int id) {
		for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala()) && i != id) {
				for(int j =0; j < GestorPartida.getContObjetosJugador(); j++) {
					if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//M�todo que devuelve true si hay objetos en la misma sala en la que se encuentra el jugador.
	public boolean comprobarSiHayObjetoEnSala(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				return true;
			}
		}
		return false;
	}
	
	//M�todo que imprime por pantalla los datos necesarios para llevar a cabo la acci�n de desplazarse de sala.
	public void cambiarSalaImprimir(int id) {
		String[] salasVecinas;
					
		salasVecinas= GestorPartida.verSalasVecinas(id);
		int contVecinas;
		contVecinas=salasVecinas.length;
		rondaActual.append("Las Salas Vecinas son:\n");
		for(int j=0;j<contVecinas;j++) {
			rondaActual.append(salasVecinas[j] + "\n");
		}

		rondaActual.append("\nEscribe el nombre de la Sala a la que deseas desplazarte:\n");
	}
	
	//M�todo que lleva a cabo la acci�n de desplazarse de sala.
	public void cambiarSala(int id) {
		String[] salasVecinas;
		String menu=new String();
		int i=0;
		int opcion=0;
		
		salasVecinas= GestorPartida.verSalasVecinas(id);
		int contVecinas;
		contVecinas = salasVecinas.length;
		menu = respuesta.getText();
		respuesta.setText(null);
		
		for(i=0;i<contVecinas;i++) {
			if(menu.equalsIgnoreCase(salasVecinas[i])) {
				opcion++;
				break;
			}
		}
		if (opcion!=0) {
			GestorPartida.getJugadores()[id].setSala(salasVecinas[i]);
			rondasAnteriores.append(GestorPartida.getJugadores()[id].getNombre()+" se ha desplazado a la Sala "+ GestorPartida.getJugadores()[id].getSala()+".\n");
			historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[id].getNombre()+" se ha desplazado a la Sala "+ GestorPartida.getJugadores()[id].getSala()+".\n";
			turnosSaltados = 0;
			finalDeTurno();
		}
		else {
			rondaActual.setText("**Por favor, escriba una entrada valida**\n");
			cambiarSalaImprimir(id);
		}
	}
	
	//M�todo que devuelve el nombre del objeto que posee un jugador, si no posee ninguno se devuelve null.
	public String nombreDelObjetoQuePosee(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getNombre())) {
				return GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		return null;
	}
	
	//M�todo que imprime por pantalla los datos necesarios para llevar a cabo la acci�n de coger un objeto.
	public void cogerObjetoEnSalaImprimir(int id) {
		rondaActual.append("Objetos en tu Sala:");
		for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append("\n" + GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
		rondaActual.append("\n\nEscribe el nombre del Objeto que deseas coger:");
	}
	
	//M�todo que imprime por pantalla los datos necesarios para llevar a cabo la acci�n de dejar un objeto.
	public void dejarObjetoEnSalaImprimir(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append("�Estas seguro de querer dejar el Objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + "?");
				rondaActual.append("\n\nEscribe Si para dejarlo.");
			}
		}
	}
	
	//M�todo que imprime toda la informaci�n de la ronda actual.
	public void estadoJugador(int id) {
		rondaActual.setText(null);
		
		rondaActual.append("Ronda: " + GestorPartida.getRondas() + "\n\n");
		rondaActual.append("Nombre del Jugador: " + GestorPartida.getJugadores()[id].getNombre());
		objetoJugador(id);
		rondaActual.append("\n\nSala: " + GestorPartida.getJugadores()[id].getSala());
		salasVecinas(id);
		objetosEnSala(id);
		objetosEnJugador(id);
		objetivosJugador(id);
		rondaActual.append("\n\n");
		mostrarPeticiones(id);
		creenciasJugador(id);
		
		rondaActual.setCaretPosition(0);
		rondasAnteriores.setCaretPosition(0);
	}
	
	//M�todo que imprime las salas vecinas.
	public void salasVecinas(int id) {
		rondaActual.append("\n\nSalas vecinas: ");
		String salas[] =  GestorPartida.verSalasVecinas(GestorPartida.getJugadores()[id].getId());
		for(int i = 0; i < salas.length; i++) {
			rondaActual.append(salas[i] + " ");
		}
	}
	
	//M�todo que imprime el objeto que posee el jugador.
	public void objetoJugador(int id) {
		rondaActual.append("\n\nObjeto: ");
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append(GestorPartida.getObjetoJugador()[i].getNombreObjeto());
			}
		}
		if(!comprobarSiJugadorTieneObjeto(id)) {
			rondaActual.append("Ninguno");
		}
	}
	
	//M�todo que imprime los objeto que se encuentran en la misma sala que el jugador.
	public void objetosEnSala(int id) {
		rondaActual.append("\n\nObjeto en Sala: ");
		for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getObjetoSala()[i].getNombreObjeto() + " ");
			}
		}
		if(!comprobarSiHayObjetoEnSala(id)) {
			rondaActual.append("Ninguno");
		}
		rondaActual.append("\n\n");
	}
	
	//M�todo que imprime el resto de jugadores que se encuentran en la misma sala y el objeto que poseen actualmente.
	public void objetosEnJugador(int id) {
		rondaActual.append("Jugadores en la misma Sala:\n");
		boolean tiene = false;
		boolean hayJugadores = false;
		
		for (int i = 0; i < GestorPartida.getContJugadores(); i++) {
			tiene = false;
			if(GestorPartida.getJugadores()[i].getId() != id && GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append(GestorPartida.getJugadores()[i].getNombre() + " : ");
				hayJugadores = true;
				for(int j = 0; j < GestorPartida.getContObjetosJugador();j++) {
					if(GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre())) {
						rondaActual.append(GestorPartida.getObjetoJugador()[j].getNombreObjeto() + "\n");
						tiene = true;
					}
				}
				
				if(tiene == false) {
					rondaActual.append("Ninguno\n");
					
				}
			}
			
		}
		if(hayJugadores == false) {
			rondaActual.append("Ninguno\n");
		}
	}
	
	//M�todo que imprime los objetivos del jugador.
	public void objetivosJugador(int id) {
		rondaActual.append("\nObjeto objetivo: " + GestorPartida.getJugadores()[id].getObjetivoObjeto());
		rondaActual.append("\nSala objetivo: " + GestorPartida.getJugadores()[id].getObjetivoSala());
	}
	
	//M�todo que imprime las peticiones de objeto recibidas por el jugador.
	public void mostrarPeticiones(int id) {
		rondaActual.append("Peticiones: ");
		for(int i =0; i < 10 && GestorPartida.getJugadores()[id].getPeticiones()[i]!= null; i++) {
			rondaActual.append("\n" + GestorPartida.getJugadores()[id].getPeticiones()[i].getJugadorPide() + " te ha pedido el Objeto " + GestorPartida.getJugadores()[id].getPeticiones()[i].getObjeto() );
		}
		if(GestorPartida.getJugadores()[id].getPeticiones()[0] == null) {
			rondaActual.append("Ninguna");
		}
	}
	
	//M�todo que imprime las creencias del jugador respecto a la localizacion del resto de jugadores y objetos.
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
	
	//M�todo encargado de realizar el final de un turno.
	public void finalDeTurno() {
		accion = 0;
		cambioDeJugador();	
		//Permitimos unicamente que se procesen las siguientes acciones siempre y cuando la partida no se haya acabado.
		if(accion != -1) {
			estadoJugador(idJugador);
			objetivosCumplidos();
		}
	}
	
	//M�todo encargado de realizar el cambio entre los turnos de distintos jugadores, si este es una inteligencia artificial, la acci�n se 
	//realizara automaticamente, si no, el usuario deber� de encargarse de desarrollar su turno a traves de la interfaz gr�fica.
	public void cambioDeJugador() {		
		do{
			if(idJugador == GestorPartida.getContJugadores()-1) {
				idJugador = 0;
			}
			
			else {
				idJugador++;
			}
			
			for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
				GestorPartida.actualizarCreencias(i);
			}
			
			cambioDeRonda();
			
			if(idJugador > GestorPartida.getContUsuarios()-1) {	
				GestorPartida.getJugadores()[idJugador].dameAccion(null);
				finalDeTurno();
				//Volvemos a comprobar si todos los jugadores han pasado turno y, si se da el caso, terminar la partida.
				if(turnosSaltados == GestorPartida.getContJugadores()) {
					rondaActual.setText("**Todos los Jugadores han saltado su Turno**\n\n");
					finDePartida();
				}
			}
		}while(idJugador > GestorPartida.getContUsuarios()-1);
		GestorPartida.actualizarCreencias(0);
	}
	
	//M�todo encargado de comprobar si todos los jugadores han llevado su turno y, si se da el caso, pasar a la siguiente ronda.
	public void cambioDeRonda() {
		if(GestorPartida.getJugadorActivo() == GestorPartida.getContJugadores()-1) {
			GestorPartida.setJugadorActivo(0);
			GestorPartida.setRondas(GestorPartida.getRondas()+1);
		}
		else {
			GestorPartida.setJugadorActivo(GestorPartida.getJugadorActivo()+1);
		}
		
	}
	
	//M�todo encargado de comprobar si al finalizar un turno, alguno de los jugadores ha cumplido todos sus objetivos, para asi finalizar la partida.
	public void objetivosCumplidos() {
		for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
			if(GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[i].getObjetivoSala())) {
				for(int j = 0; j < GestorPartida.getContObjetosJugador(); j++) {
					if(GestorPartida.getJugadores()[i] == GestorPartida.getObjetoJugador()[j].getJugador()) {
						if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(GestorPartida.getJugadores()[i].getObjetivoObjeto())) {
							rondaActual.setText("**" + GestorPartida.getJugadores()[i].getNombre() + " posee el Objeto " + GestorPartida.getJugadores()[i].getObjetivoObjeto() + 
									" y se encuentra en la Sala " + GestorPartida.getJugadores()[i].getObjetivoSala() + "**\n\n");
							finDePartida();
							break;
						}
					}
				}
			}
		}	
	}
	
	//M�todo encargado de finalizar la partida, impidiento que el usuario pueda realizar ninguna acci�n m�s.
	public void finDePartida() {
		respuesta.setText(null);
		rondaActual.append("**Partida Finalizada**");
		respuesta.setEditable(false);
		System.out.println(historiaCompleta);
		accion = -1;
	}	
	
	public int getTurnosSaltados() {
		return turnosSaltados;
	}

	public void setTurnosSaltados(int turnosSaltados) {
		this.turnosSaltados = turnosSaltados;
	}

	public JTextArea getRondasAnteriores() {
		return rondasAnteriores;
	}

	public void setRondasAnteriores(JTextArea rondasAnteriores) {
		this.rondasAnteriores = rondasAnteriores;
	}

	public String getHistoriaCompleta() {
		return historiaCompleta;
	}
	public void setHistoriaCompleta(String historiaCompleta) {
		this.historiaCompleta = historiaCompleta;
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
}