import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener,  KeyListener{
	
	int idJugador = 0;
	int action = 0;
	int numeroDeEntrada = 0;
	
	String historiaCompleta = "";
	
	String nombre;
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
        
        GestorPartida.instanciarCreencias();
		GestorPartida.actualizarCreencias(idJugador);
		GestorPartida.setRondas(1);
		GestorPartida.setJugadorActivo(0);
		
        estadoJugador(idJugador);
        this.setVisible(true);
        
    }
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == boton1) {
			String[] salasVecinas;
			action = 1;
			
			rondaActual.setText(null);
			salasVecinas= GestorPartida.verSalasVecinas(idJugador);
			int contVecinas;
			contVecinas=salasVecinas.length;
			rondaActual.append("Sus salas vecinas son:\n");
			for(int j=0;j<contVecinas;j++) {
				rondaActual.append(j+1+" " + salasVecinas[j] + "\n");
			}

			rondaActual.append("Escriba el nombre de la sala destino:\n");
		}
		
		if(e.getSource() == boton2) {
			rondaActual.setText(null);
			if(comprobarSiHayObjetoEnSala(idJugador) && !comprobarSiJugadorTieneObjeto(idJugador)) {
				action = 2;
				cogerObjetoEnSalaImprimir(idJugador);
			}
			else if(comprobarSiJugadorTieneObjeto(idJugador)) {
				rondaActual.append("Ya posees un Objeto, debes dejarlo primero en la Sala.");
			}
			else {
				rondaActual.append("No hay ningun Objeto en la Sala para coger.");
			}
		}
		
		if(e.getSource() == boton3) {
			rondaActual.setText(null);
			if(comprobarSiJugadorTieneObjeto(idJugador)) {
				action = 3;
				dejarObjetoEnSalaImprimir(idJugador);
			}
			else {
				rondaActual.append("No posees ningun Objeto.");
			}
		}
		
		if(e.getSource() == boton4) {
			rondaActual.setText(null);
			if(comprobarJugadoresConObjetosEnSala(idJugador)) {
				action = 4;
				objetosEnJugador(idJugador);
				rondaActual.append("\nIndica el nombre del Jugador al que deseas pedir un Objeto:");
	    		
			}
			else if(!GestorPartida.jugadorEnSala(idJugador)){
				rondaActual.append("No hay ningun Jugador en la misma Sala que tu.");
			}
			else {
				rondaActual.append("Ningun Jugador de la sala tiene un Objeto.");
			}
			
		}
		
		if(e.getSource() == boton5) {
			rondaActual.setText(null);
			if(comprobarSiJugadorTieneObjeto(idJugador)) {
				if(comprobarSiExistePeticionJugadorEnSala(idJugador)) {
					if(comprobarJugadoresSinObjetosEnSala(idJugador)) {
						action = 5;
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
		
		if(e.getSource() == boton6) {
			action = 0;
			rondaActual.setText(null);
			estadoJugador(idJugador);
		}
		
		if(e.getSource( )== boton7) {
			action = 7;
			rondaActual.setText("¿Seguro que deseas saltar tu Turno?");
			rondaActual.append("\n\nEscribe Si para continuar.");
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) {
	    	 
	    	if(action == 1) {
	    		cambiarSala(idJugador);
	    		
	    	}
	    	
	       	if(action == 2) {
	        	boolean entradaValida = false;
	        	for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
	        		if(GestorPartida.getObjetoSala()[i].getNombreObjeto().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala())){
		    			entradaValida = true;
		    			historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + GestorPartida.getJugadores()[idJugador].getSala() + "\n";
		   				rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha cogido " + GestorPartida.getObjetoSala()[i].getNombreObjeto() + " de " + GestorPartida.getJugadores()[idJugador].getSala() + "\n");
		   				Jugador.cogerObjeto(idJugador, GestorPartida.getObjetoSala()[i]);
		   				respuesta.setText(null);
		        		
		        		cambioDeJugador();
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
	       					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + GestorPartida.getJugadores()[idJugador].getSala() + "\n";
	       					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha dejado " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + " en " + GestorPartida.getJugadores()[idJugador].getSala() + "\n");
	       					Jugador.dejarObjeto(idJugador, GestorPartida.getObjetoJugador()[i]);
	       					respuesta.setText(null);			        		
			        		cambioDeJugador();
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
	       	
	    	if(action == 4) {
	    		boolean entradaValida = false;
	    		boolean objeto = false;
	    		  		
	    		if(numeroDeEntrada == 0) {
	    			for(int i = 0; i < GestorPartida.getContJugadores(); i++) {
		        		if(GestorPartida.getJugadores()[i].getNombre().equalsIgnoreCase(respuesta.getText())&& GestorPartida.getJugadores()[i].getSala().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getSala()) && i != idJugador){
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
	    		
	    		else if(numeroDeEntrada == 1) {	    		   			
	    			rondaActual.setText(nombre);
	    			
	    			for(int j = 0; j < GestorPartida.getContObjetosSala(); j++) {
	    				if(GestorPartida.getObjetoSala()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText())) {
	    					objeto = true;	    					
	    					GestorPartida.getJugadores()[idJugador].hacerPeticion(nombre, respuesta.getText());
	    					numeroDeEntrada = 0;
	    					respuesta.setText(null);
	    					cambioDeJugador();	    					
	    					action = 0;
				       		estadoJugador(idJugador);
	    					break;
	    				}
	    			}
	    			for(int j = 0; j < GestorPartida.getContObjetosJugador(); j++) {
	    				if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText())) {	    					
	    					objeto = true;
	    					GestorPartida.getJugadores()[idJugador].hacerPeticion(nombre, respuesta.getText());
	    					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha pedido el Objeto " + respuesta.getText()  + " a " + nombre + "\n";
	    					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha pedido el Objeto " + respuesta.getText()  + " a " + nombre + "\n");
	    					numeroDeEntrada = 0;
	    					respuesta.setText(null);
	    					cambioDeJugador();	    					
	    					action = 0;
				       		estadoJugador(idJugador);
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
	    	
	    	if(action == 5) {
	    		boolean entradaValida = false;
	    		boolean objeto = false;
	    		boolean peticionValida = false;
	    		String objetoPosee = nombreDelObjetoQuePosee(idJugador);
	    		rondaActual.append(objetoPosee + "\n");
	    		int i = 0;
	    		int peticion = 0;
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
						numeroDeEntrada =0;
						respuesta.setText(null);
	    			}
	    		}
	    		
	    		else if(numeroDeEntrada == 1) {
	    			
	    			for(int j = 0; j < GestorPartida.getContObjetosJugador(); j++) {
	    				if(GestorPartida.getObjetoJugador()[j].getNombreObjeto().equalsIgnoreCase(respuesta.getText()) && GestorPartida.getObjetoJugador()[j].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[idJugador].getNombre())) {
	    					objeto = true;
	    					
	    					GestorPartida.getJugadores()[idJugador].darObjeto(nombre, respuesta.getText());
	    					historiaCompleta = historiaCompleta + GestorPartida.getJugadores()[idJugador].getNombre() + " ha dado el Objeto " +respuesta.getText()  + " a " + nombre + "\n";
	    					rondasAnteriores.append(GestorPartida.getJugadores()[idJugador].getNombre() + " ha dado el Objeto " +respuesta.getText()  + " a " + nombre + "\n");
	    					respuesta.setText(null);
	    					numeroDeEntrada = 0;
	    					cambioDeJugador();
	    					action = 0;
	    					estadoJugador(idJugador);
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
	    	if(action == 7) {
	    		if(respuesta.getText().equalsIgnoreCase("si")) {
	    			cambioDeJugador();			
					estadoJugador(idJugador);
					action = 0;
	    		}
	    		else {
	    			rondaActual.setText("**Por favor, escriba una entrada valida**\n");
	    			rondaActual.append("¿Seguro que deseas saltar tu Turno?");
	    			rondaActual.append("\n\nEscribe Si para continuar.");
		       		respuesta.setText(null);
	    		}
	    	}
	       	if(action == 0) {	       		
        		respuesta.setText(null);
	       	}
	     }
	}
	
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
				for(int j =0;GestorPartida.getJugadores()[id].getPeticiones()[j]!=null && j < 10; j++) {
					if(GestorPartida.getJugadores()[id].getPeticiones()[j].getJugadorPide().equalsIgnoreCase(GestorPartida.getJugadores()[i].getNombre()) && GestorPartida.getJugadores()[id].getPeticiones()[j].getObjeto().equalsIgnoreCase(nombreObjeto)) {
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean comprobarSiJugadorTieneObjeto(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				return true;
			}
		}
		return false;
	}
	
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
	
	public boolean comprobarSiHayObjetoEnSala(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				return true;
			}
		}
		return false;
	}
	public void cambiarSala(int id) {
		String[] salasVecinas;
		String menu=new String();
		int i=0;
		int opcion=0;
		
		salasVecinas= GestorPartida.verSalasVecinas(id);
		int contVecinas;
		contVecinas=salasVecinas.length;
		menu =respuesta.getText();
		respuesta.setText(null);
		for(i=0;i<contVecinas;i++) {
			if(menu.equalsIgnoreCase(salasVecinas[i])) {
				opcion++;
				break;
			}
		}
		if (opcion!=0) {
			GestorPartida.getJugadores()[id].setSala(salasVecinas[i]);
			rondasAnteriores.append("Ahora " +GestorPartida.getJugadores()[id].getNombre()+" está en "+ GestorPartida.getJugadores()[id].getSala()+"\n");
			historiaCompleta = historiaCompleta + "Ahora " +GestorPartida.getJugadores()[id].getNombre()+" está en "+ GestorPartida.getJugadores()[id].getSala()+"\n";
			cambioDeJugador();
			action = 0;
			estadoJugador(idJugador);
		}
		else {
			rondaActual.append("Nombre no valido\n");
		}
	}
	
	public String nombreDelObjetoQuePosee(int id) {
		for(int i = 0; i < GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getNombre())) {
				return GestorPartida.getObjetoJugador()[i].getNombreObjeto();
			}
		}
		return null;
	}
	
	public void cogerObjetoEnSalaImprimir(int id) {
		rondaActual.append("Escribe el nombre del Objeto que deseas coger:");
		for(int i =0; i< GestorPartida.getContObjetosSala(); i++) {
			if(GestorPartida.getObjetoSala()[i].getSala().getNombre().equalsIgnoreCase(GestorPartida.getJugadores()[id].getSala())) {
				rondaActual.append("\n" + GestorPartida.getObjetoSala()[i].getNombreObjeto());
			}
		}
	}
	
	public void dejarObjetoEnSalaImprimir(int id) {
		for(int i =0; i< GestorPartida.getContObjetosJugador(); i++) {
			if(GestorPartida.getObjetoJugador()[i].getJugador().getId() == id) {
				rondaActual.append("¿Estas seguro de querer dejar el Objeto " + GestorPartida.getObjetoJugador()[i].getNombreObjeto() + "?");
				rondaActual.append("\n\nEscribe Si para dejarlo.");
			}
		}
	}
	
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
	
	public void salasVecinas(int id) {
		rondaActual.append("\n\nSalas vecinas: ");
		String salas[] =  GestorPartida.verSalasVecinas(GestorPartida.getJugadores()[id].getId());
		for(int i = 0; i < salas.length; i++) {
			rondaActual.append(salas[i] + " ");
		}
	}
	
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
						rondaActual.append(GestorPartida.getObjetoJugador()[j].getNombreObjeto());
						tiene = true;
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
		rondaActual.append("\nObjeto objetivo: " + GestorPartida.getJugadores()[id].getObjetivoObjeto());
		rondaActual.append("\nSala objetivo: " + GestorPartida.getJugadores()[id].getObjetivoSala());
	}
	
	public void mostrarPeticiones(int id) {
		rondaActual.append("Peticiones: ");
		for(int i =0; i < 10 && GestorPartida.getJugadores()[id].getPeticiones()[i]!= null; i++) {
			rondaActual.append("\n"+GestorPartida.getJugadores()[id].getPeticiones()[i].getJugadorPide() + " te ha pedido el Objeto " + GestorPartida.getJugadores()[id].getPeticiones()[i].getObjeto() );
		}
		if(GestorPartida.getJugadores()[id].getPeticiones()[0] == null) {
			rondaActual.append("Ninguna");
		}
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
	
	public void cambioDeJugador() {
		if(idJugador == GestorPartida.getContJugadores()-1) {
			idJugador = 0;
		}else {
			idJugador++;
		}
		for(int i =0; i < GestorPartida.getContJugadores(); i++) {
			GestorPartida.actualizarCreencias(i);
		}
		cambioDeRonda();
		
	}
	
	public void cambioDeRonda() {
		if(GestorPartida.getJugadorActivo() == GestorPartida.getContJugadores()-1) {
			GestorPartida.setJugadorActivo(0);
			GestorPartida.setRondas(GestorPartida.getRondas()+1);
		}
		else {
			GestorPartida.setJugadorActivo(GestorPartida.getJugadorActivo()+1);
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