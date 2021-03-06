/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import Services.Add;
import datojava.jcalendar.DJFechasEspInv;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import medical.Medico;
import Services.Leer;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


/**
 *
 * @author Yanir
 */
public class FrameCita extends javax.swing.JFrame  implements ActionListener{

    /**
     * Creates new form Cita
     */
     
    /////////////Panel Historia
    
    
    //////////////Lecturas y escrituras de la BD
    Leer rutasLeer;
    Add rutasAdd;
    
    ////////////// ELEMENTOS DEL PANEL DETALLE
    Font font;
    Color colorTextFields;
    Color colorBackGround;
    Color colorDelPapa;
    Color colorBotones;
    Color colorActivo;
    JPanel Botones;
    JPanel BotonAtras;
    JPanel BotonBuscar;
    
    //Labels - Fijos, no se modifican
    JLabel fechaL;
    JLabel horaL;
    JLabel nombreL;
    JLabel apellidoL;
    JLabel cedulaL;
    JLabel direccionL;
    JLabel motivosL;
    JLabel telefonoCasaL;
    JLabel telefonoCelularL;
    JLabel correoL;
    JLabel horaCitas;//
      
    //JTextFields - seran para ingreso o impresion de datos segun sea el caso
    JTextField fechaJ;
    JTextField horaJ;
    JTextField nombreJ;
    JTextField apellidoJ;
    JTextField cedulaJ;
    JTextField direccionJ;
    JTextField telefonoCasaJ;
    JTextField telefonoCelularJ;
    JTextField correoJ;
    
    
    
    //JTextArea - para ingreso o impresion de motivos
    JTextArea motivosTA;
    
    //Botones - Se activaran segun accion necesaria
    JButton modificarB;
    JButton eliminarB;
    JButton agregarB;
    JButton atrasB;
    JButton buscarB;
    
    // Elementos Panel Pacientes
    JLabel paciente_mensaje;
    JLabel paciente_titulo;
    
    JLabel Historial_titulo;
    
    
    JScrollPane scrollPanelCita; //Scroll del panel Cita
    JScrollPane scrollPanelHistorial; //Scroll del panel historial
    Leer testl;
    Add addu;
    
    //Medico en sesion
    Medico medico;
    Citas citas[];
    Pacientes pacientes[];
    Historial historial[];
    //Para los minutos y horas
    int min;
    int hora;
    int total;   
    boolean comparteInfo;
    SimpleDateFormat formato;
    boolean agregarPaciente;//Si esta en verdadero entonces se debe agregar el paciente, sino no.
    int idPaciente;
    ////////////// ELEMENTOS DEL PANEL DETALLE
    
    
        Integer id_Paciente;
        String CedulaPaciente;
        String NombrePaciente;
        String ApellidoPaciente;
        String DireccionPaciente;
        String CorreoPaciente;
        String TlfCasaPaciente;
        String TlfCelularPaciente;
        
        String tratamiento;
        String diagnostico;
    
    public static String cedula;
    public static String nombre;
    public static String apellido;
    
    
    public FrameCita() throws ClientProtocolException, IOException, JSONException, ParseException, java.text.ParseException {
        
        initComponents();
        
        JSONArray medicoArray = new JSONArray();
        JSONObject objMedico = new JSONObject();
        JSONObject objHorario = new JSONObject();
        System.out.println(Login.username);
        this.getContentPane().setLayout(new GridBagLayout());
        rutasLeer = new Leer();
        rutasAdd = new Add();
        formato = new SimpleDateFormat("yyyy-MM-dd");
        medicoArray = rutasLeer.leer(Login.rutaBase+"Medicos/configuracion/"+Login.username);
        objMedico = (JSONObject) ((JSONObject) medicoArray.get(0)).get("medico");
        objHorario = (JSONObject) ((JSONObject) medicoArray.get(0)).get("horario");
        int horaini = (objHorario.getString("horainicio").charAt(1))-48;
        int horatotal = (objHorario.getString("horafin").charAt(1))-48;
        System.out.println("HORAAAAAAA hora "+horaini+"");
        System.out.println("HORAAAAAAA total fin y todo "+horatotal+"");
        
        medico = new Medico(horaini,0,horatotal-horaini,objMedico.getInt("tiempoatencion")); //MEDICO EN SESIOOON
         System.out.println("HORAAAAAAA total fin y todo "+medico.getMAXhorasDeAtencionxdia()+"");
        //medico = new Medico(objMedico.getString("horainicio").charAt(0),0,3,objMedico.getInt("tiempoatencion")); //MEDICO EN SESIOOON
        System.out.println("HORAAAAAAA 1 "+objHorario.getString("horainicio").charAt(0)+"");
        System.out.println("HORAAAAAAA 2 "+objHorario.getString("horainicio").charAt(1)+"");
        
        
        Image icon = new ImageIcon(getClass().getResource("/Iconos/medicos-de-tampico.png")).getImage();
        setIconImage(icon);
        citas = new Citas [medico.cantidadDeCitasxDia(10, 30)];
        comparteInfo=true;//Esto deber[a ser buscado en la base de datos
        agregarPaciente = true;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Sertig.otf"));
            font  = font.deriveFont(Font.BOLD, 11);
        } catch (FontFormatException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setTitle("Citas");
        GridBagConstraints gbc = new GridBagConstraints();
        if(comparteInfo){
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.BOTH;
            this.getContentPane().add(PanelCalendar, gbc);
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 1.0;
            this.getContentPane().add(PanelCita, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbc.weightx = 0.50;
            gbc.weighty = 1.0;
            this.getContentPane().add(PanelPacientes, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbc.weightx = 0.80;
            gbc.weighty = 1.0;
            this.getContentPane().add(PanelDetalle, gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.gridheight = 2;
            gbc.weightx = 2.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            this.getContentPane().add(panelHistoria, gbc); 
        }else {
            
            panelHistoria.setVisible(false);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.BOTH;
            this.getContentPane().add(PanelCalendar, gbc);
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 1.0;
            this.getContentPane().add(PanelCita, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            this.getContentPane().add(PanelDetalle, gbc);
        }
              
        
        ////////////////// PANEL DETALLES
        
        //Colores
        colorBackGround=new Color(hex("8FA8F6"));//Color de los BackGround 
        colorTextFields = new Color (hex("CDCBA6"));//Color de los JTextFields
        colorDelPapa = new Color (hex("2980b9"));//Color del backgorud del papa
        colorBotones = new Color (hex ("2C3E50"));//Color d elos botonte
        colorActivo = new Color (hex ("81CFE0"));//Color de los jTextFild cuando los activan
        //Colores

        
        //Inicializaciones
        
        this.setBackground(colorDelPapa);//Color del backgroud
        //BOTONES INICIALIZACION
        modificarB = new JButton("Modificar Paciente");
        eliminarB = new JButton("Eliminar");
        agregarB = new JButton("Agregar");
        atrasB= new JButton("Atras");
        buscarB = new JButton ("Buscar");
        Botones = new JPanel();  //PAnel donde van los botones Agregar, Modificar y Eliminar
        BotonAtras = new JPanel();  //PAnel donde va el boton atras
        BotonBuscar = new JPanel();  //PAnel donde va el boton buscar
        agregarB.addActionListener(this);
        modificarB.addActionListener(this);
        eliminarB.addActionListener(this);
        atrasB.addActionListener(this);
        buscarB.addActionListener(this);
        // FIN BOTONES INICIALIZACION
        
        PanelCita.setLayout(new GridBagLayout());
        
        disenoBotones(modificarB);
        disenoBotones(eliminarB);
        disenoBotones(agregarB);
        disenoBotones(atrasB);
        disenoBotones(buscarB);
        PanelDetalle.setLayout(new GridBagLayout());          //Panel Papá (PanelDetalle)
        cambiarColorPanel(PanelCalendar,colorDelPapa);
        cambiarColorPanel(PanelCita,colorDelPapa);
        cambiarColorPanel(PanelDetalle,colorDelPapa);
        cambiarColorPanel(panelHistoria,colorDelPapa);
        cambiarColorPanel(PanelPacientes,colorDelPapa);
        
        
        //LABELS
        horaCitas = new JLabel();
        fechaL=new JLabel("<HTML> Fecha &nbsp</HTML>");
        horaL=new JLabel("<HTML> Hora &nbsp</HTML>");
        nombreL=new JLabel("<HTML> Nombre &nbsp &nbsp </HTML>");
        apellidoL=new JLabel("<HTML> Apellido &nbsp &nbsp &nbsp</HTML>");
        cedulaL=new JLabel("<HTML> Cedula &nbsp &nbsp &nbsp &nbsp &nbsp</HTML>");
        direccionL=new JLabel("<HTML> Direccion &nbsp &nbsp</HTML>");
        motivosL=new JLabel("<HTML> Motivos &nbsp &nbsp &nbsp &nbsp</HTML>");
        telefonoCasaL=new JLabel("<HTML> Tlfn Casa&nbsp </HTML>");
        telefonoCelularL=new JLabel("<HTML> Tlfn Celular</HTML>");
        correoL=new JLabel("<HTML> Correo &nbsp &nbsp &nbsp</HTML>");
        
        font=font  = font.deriveFont(Font.BOLD, 11);
        disenoLabel(horaCitas);
        disenoLabel(fechaL);
        disenoLabel(horaL);
        disenoLabel(nombreL);
        disenoLabel(apellidoL);
        disenoLabel(cedulaL);
        disenoLabel(direccionL);
        disenoLabel(motivosL);
        disenoLabel(telefonoCasaL);
        disenoLabel(telefonoCelularL);
        disenoLabel(correoL);
        
        
        //JTEXTFIELDS y TEXAREA
        
        fechaJ=new JTextField("");
        horaJ=new JTextField("");
        nombreJ=new JTextField("");
        apellidoJ=new JTextField("");
        cedulaJ=new JTextField("");
        direccionJ=new JTextField();
        motivosTA=new JTextArea("");
        telefonoCasaJ=new JTextField("");
        telefonoCelularJ=new JTextField("");
        correoJ=new JTextField("");
        //COLORES
        /*fechaJ.setBackground(colorTextFields);
        horaJ.setBackground(colorTextFields);
        nombreJ.setBackground(colorTextFields);
        apellidoJ.setBackground(colorTextFields);
        cedulaJ.setBackground(colorTextFields);
        direccionJ.setBackground(colorTextFields);
        motivosTA .setBackground(colorTextFields);
        telefonoCasaJ.setBackground(colorTextFields);
        telefonoCelularJ.setBackground(colorTextFields);
        correoJ.setBackground(colorTextFields);
*/
       //Añadir componentes al PanelDetalle
       GridBagConstraints constraints = new GridBagConstraints();
       //Caracteristicas globales del grid
       constraints.fill = GridBagConstraints.BOTH;
       constraints.weighty = 1.0;
       constraints.insets.set(5, 0, 5,10 );
       //Fin Caracteristicas Globales del grid
       
       
       
       constraints.gridx = 1;
       constraints.gridy = 0;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add(new JSeparator(SwingConstants.HORIZONTAL),constraints);
       constraints.gridx = 1;
       constraints.gridy = 8;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add(new JSeparator(SwingConstants.HORIZONTAL),constraints);
       
       
       constraints.gridx = 0;
       constraints.gridy = 1; 
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (cedulaL, constraints);
       
       
       constraints.gridx = 1; //Necesita estirarse
       constraints.gridy = 1;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (cedulaJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 2; 
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (nombreL, constraints);
       
       
       constraints.gridx = 1; //Necesita estirarse
       constraints.gridy = 2;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (nombreJ, constraints);
       constraints.weightx = 0.0;
       
       
       constraints.gridx = 0;
       constraints.gridy = 3;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (apellidoL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 3;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (apellidoJ, constraints);
       constraints.weightx = 0.0;
         
       constraints.gridx = 0;
       constraints.gridy = 4;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (direccionL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 4;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (direccionJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 5;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (telefonoCasaL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 5;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (telefonoCasaJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 6;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (telefonoCelularL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 6;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (telefonoCelularJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 7;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weighty = 1.0;
       PanelDetalle.add (correoL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 7;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       PanelDetalle.add (correoJ, constraints);
       constraints.weightx = 0.0;
       
       
       
       
       
       constraints.gridx = 0;
       constraints.gridy = 9;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (fechaL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 9;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (fechaJ, constraints);
       constraints.weightx = 0.0;
        
              constraints.gridx = 0;
       constraints.gridy = 10;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       PanelDetalle.add (horaL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 10;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       PanelDetalle.add (horaJ, constraints);
       constraints.weightx = 0.0;
       
       constraints.gridx = 0;
       constraints.gridy = 11;
       constraints.gridwidth = 1;
       constraints.gridheight = 2;
       PanelDetalle.add (motivosL, constraints);
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 11;
       constraints.gridwidth = 1;
       constraints.gridheight = 2;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       PanelDetalle.add (motivosTA, constraints);
       constraints.weightx = 0.0;
       
       //PANEL DE LOS BOTONES
       
       constraints.insets.set(30, 0, 10,10 );
       
       
       constraints.gridx = 1;//Necesita estirarse
       constraints.gridy = 13;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 0.0;
        FlowLayout flowLayout1=new FlowLayout();
	Botones.setLayout(flowLayout1);
        Botones.add(agregarB);
        Botones.add(modificarB);
        Botones.add(eliminarB);
        PanelDetalle.add(Botones, constraints);
       
       
       
       //Boton Buscar
       constraints.anchor=GridBagConstraints.NORTH;
       constraints.insets.set(0, 0, 0,0 );
       constraints.gridx = 2;//Necesita estirarse
       constraints.gridy = 1;
       constraints.gridwidth = 1;
       constraints.gridheight = 0;
       constraints.weightx = 0.0;
	BotonBuscar.setLayout(flowLayout1);
        BotonBuscar.add(buscarB);
        BotonBuscar.setBackground(colorBackGround);
       PanelDetalle.add(BotonBuscar, constraints);
        constraints.insets.set(30, 0, 10,10 );
       
     
      
       //BOTON ATRAS
       
       

       
       constraints.insets.set(30, 0, 10,10 );
       constraints.gridx = 2;//Necesita estirarse
       constraints.gridy = 12;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 0.0;
       
	BotonAtras.setLayout(flowLayout1);
        //BotonAtras.add(atrasB);
        
        PanelDetalle.add(BotonAtras, constraints);
        
        cambiarColorPanel(Botones,colorDelPapa);
        cambiarColorPanel(BotonBuscar,colorDelPapa);
        cambiarColorPanel(BotonAtras,colorDelPapa);
        
        PanelDetalle.setVisible(true);
        
        // Inicio componentes Panel de Pacientes //
        
        
        
        this.PanelPacientes.setLayout(new GridBagLayout());
        this.paciente_titulo = new JLabel("<HTML> PACIENTES DEL MEDICO "+Login.username.toUpperCase()+"</HTML>");
        disenoLabel(paciente_titulo);
        JPanel p[] = new JPanel[10];
                
        p[0] = new JPanel();
        p[1] = new JPanel();
        p[2] = new JPanel();
        p[3] = new JPanel();
        p[4] = new JPanel();
        
       cambiarColorPanel(p[0],colorDelPapa);
       cambiarColorPanel(p[1],colorDelPapa);
       cambiarColorPanel(p[2],colorDelPapa);
       cambiarColorPanel(p[3],colorDelPapa);
       cambiarColorPanel(p[4],colorDelPapa);
       
       JButton b = new JButton();
       b.setPreferredSize(new Dimension(100,20));
       
       constraints.gridx = 0;//Necesita estirarse
       constraints.gridy = 0;
       constraints.gridwidth = 4;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       constraints.anchor = GridBagConstraints.NORTHWEST;
       PanelPacientes.add (paciente_titulo, constraints);
       
       constraints.gridx = 0;//Necesita estirarse
       constraints.gridy = 1;
       constraints.gridwidth = 4;
       constraints.gridheight = 8;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       constraints.anchor = GridBagConstraints.NORTHWEST;
       PanelPacientes.add (p[3], constraints);
       
       constraints.gridx = 0;//Necesita estirarse
       constraints.gridy = 9;
       constraints.gridwidth = 1;
       constraints.gridheight = 1;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       constraints.anchor = GridBagConstraints.CENTER;
       PanelPacientes.add (p[4], constraints);
           
       
      // this.PanelPacientes;
       
//       this.PanelPacientesScroll.setLayout(new GridBagLayout());
       
       p[3].setLayout(new GridBagLayout());
       this.PanelPacientesScroll.setPreferredSize(new Dimension(420,300));
       
       constraints.gridx = 0;//Necesita estirarse
       constraints.gridy = 0;
       constraints.gridwidth = 4;
       constraints.gridheight = 8;
       constraints.weightx = 1.0;
       constraints.weighty = 1.0;
       constraints.anchor = GridBagConstraints.NORTHWEST;
       p[3].add (this.PanelPacientesScroll, constraints);
       
       this.PanelPacientesScroll.setViewportView(p[2]);
       
       p[2].setLayout(new GridBagLayout());
       JSONArray pacientesMedico = new JSONArray();
       JSONObject temp = new JSONObject();
       pacientesMedico = rutasLeer.leer(Login.rutaBase+"Medicos/get_CPacientes/"+Login.username);
       pacientes = new Pacientes[pacientesMedico.length()];
       
        
        
            this.paciente_mensaje = new JLabel("<HTML> NO HA ATENDIDO A NINGUN PACIENTE &nbsp &nbsp </HTML>");
        disenoLabel(paciente_mensaje);
      
        if(pacientesMedico.toString().equals("[]")){
            constraints.gridx = 0;//Necesita estirarse
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            constraints.gridheight = 1;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.anchor = GridBagConstraints.NORTHEAST;
            p[2].add (paciente_mensaje, constraints);
        }else{
            for (int i = 0;i<pacientesMedico.length();i++){
                temp = (JSONObject)pacientesMedico.get(i);

                id_Paciente = temp.getInt("id");
                CedulaPaciente = temp.getString("cedula");
                NombrePaciente = temp.getString("nombre");
                ApellidoPaciente = temp.getString("apellido");
                DireccionPaciente = temp.getString("direccion");
                CorreoPaciente = temp.getString("correo");
                TlfCasaPaciente = temp.getString("tlfncasa");
                TlfCelularPaciente = temp.getString("tlfncelular");

                pacientes[i] = new Pacientes(id_Paciente, CedulaPaciente, NombrePaciente, ApellidoPaciente, DireccionPaciente, CorreoPaciente, TlfCasaPaciente, TlfCelularPaciente);
                
                 cambiarColorPanel(pacientes[i],colorDelPapa);
                 constraints.gridx = 0;//Necesita estirarse
                 constraints.gridy = i;
                 constraints.gridwidth = 4;
                 constraints.gridheight = 1;
                 constraints.weightx = 1.0;
                 constraints.weighty = 0.2;
                 constraints.anchor = GridBagConstraints.NORTHWEST;
                 p[2].add (pacientes[i], constraints);
                 
                 
                this.panelHistoria.setLayout(new GridBagLayout());
                this.Historial_titulo = new JLabel("<HTML> HISTORIAL MEDICO DEL PACIENTE "+Login.username.toUpperCase()+"</HTML>");
                disenoLabel(Historial_titulo); 
                 cambiarColorPanel(panelHistoria,colorDelPapa);
                p[5] = new JPanel();
                p[6] = new JPanel();
                p[7] = new JPanel();
                
                 cambiarColorPanel(p[6],colorDelPapa);
                 cambiarColorPanel(p[5],colorDelPapa);
                 cambiarColorPanel(p[7],colorDelPapa);
                
                constraints.gridx = 0;//Necesita estirarse
                constraints.gridy = 0;
                constraints.gridwidth = 4;
                constraints.gridheight = 1;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.anchor = GridBagConstraints.NORTHWEST;
                panelHistoria.add (Historial_titulo, constraints);

                constraints.gridx = 0;//Necesita estirarse
                constraints.gridy = 1;
                constraints.gridwidth = 4;
                constraints.gridheight = 8;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.anchor = GridBagConstraints.NORTHWEST;
                panelHistoria.add (p[5], constraints);

                constraints.gridx = 0;//Necesita estirarse
                constraints.gridy = 9;
                constraints.gridwidth = 1;
                constraints.gridheight = 1;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.anchor = GridBagConstraints.CENTER;
                panelHistoria.add (p[6], constraints);
                
                
                p[5].setLayout(new GridBagLayout());
                FrameCita.this.PanelHistorialScroll.setPreferredSize(new Dimension(420,300));

                constraints.gridx = 0;//Necesita estirarse
                constraints.gridy = 0;
                constraints.gridwidth = 4;
                constraints.gridheight = 8;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.anchor = GridBagConstraints.NORTHWEST;
                p[5].add (FrameCita.this.PanelHistorialScroll, constraints);

                FrameCita.this.PanelHistorialScroll.setViewportView(p[7]);

                p[7].setLayout(new GridBagLayout());
                 pacientes[i].addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
                        
                        JSONArray HistorialPaciente = new JSONArray();
                        JSONObject tempHistory = new JSONObject();
                        try {
                            HistorialPaciente = rutasLeer.leer(Login.rutaBase+"Citas/porCedula/"+CedulaPaciente);
                            System.out.println("Json historia: "+ HistorialPaciente);
                        } catch (IOException ex) {
                            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JSONException ex) {
                            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        historial = new Historial[HistorialPaciente.length()];
                        for (int j = 0;j<HistorialPaciente.length();j++){
                            
                            try {
                                tempHistory = (JSONObject)HistorialPaciente.get(j);
                                diagnostico = tempHistory.getString("diagnostico");
                                tratamiento = tempHistory.getString("tratamiento");
                                System.out.println("Json diagnistico: "+ diagnostico);
                                System.out.println("Json tratamiento: "+ tratamiento);
                            } catch (JSONException ex) {
                                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                historial[j] = new Historial(CedulaPaciente, NombrePaciente, ApellidoPaciente, DireccionPaciente, CorreoPaciente, TlfCasaPaciente, TlfCelularPaciente, tratamiento, diagnostico);
                            } catch (IOException ex) {
                                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            cambiarColorPanel(historial[j],colorDelPapa);
                            constraints.gridx = 0;//Necesita estirarse
                            constraints.gridy = j;
                            constraints.gridwidth = 4;
                            constraints.gridheight = 1;
                            constraints.weightx = 1.0;
                            constraints.weighty = 0.2;
                            constraints.anchor = GridBagConstraints.NORTHWEST;
                            p[7].add (historial[j], constraints);
                        }
                        panelHistoria.repaint();
                        panelHistoria.revalidate();
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }
                });
            }
        }
       
       
       
       
       
       this.PanelPacientes.setVisible(false);
       
        
        // Fin componentes Panel de Pacientes //
        
        
      //  this.PanelDetalle.setVisible(false);
        
        //////////////////////ULTIMAS MOTIFICACIONES PRIMER INCREMENTO
        atrasB.setEnabled(false);
        buscarB.setEnabled(false);
        modificarB.setEnabled(false);
        agregarB.setEnabled(false);
        eliminarB.setEnabled(false);
        fechaJ.setEditable(false);
        horaJ.setEditable(false);
        nombreJ.setEditable(false);
        apellidoJ.setEditable(false);
        cedulaJ.setEditable(false);
        direccionJ.setEditable(false);
        motivosTA.setEditable(false);
        telefonoCasaJ.setEditable(false);
        telefonoCelularJ.setEditable(false);
        correoJ.setEditable(false);
        
        /////////////////////FIN ULTIMAS MOTIFICACIONES PRIMER INCREMENTO
        
        
        /////////////////Para enero
        
  
        
        dibujarPanelCita(medico);//Dibuja la "libreta" de las citas
        setCitas();
  
        
        /////// fin para ENERO
        
  
        //jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo
        jCalendar1.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    try {
                        setCitas();
                    } catch (ClientProtocolException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.text.ParseException ex) {
                        Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    
    private void disenoLabel(JLabel actual){
        actual.setFont(font);
    }
    
    
    
 private void disenoBotones(JButton actual){
       actual.setBackground(colorBotones);
       font  = font.deriveFont(Font.TYPE1_FONT, 13);
       actual.setFont(font);
       actual.setBorderPainted(false);
       actual.setFocusPainted(false);
        //actual.setContentAreaFilled(false);
        
       actual.setOpaque(false);
   }
    
    public void setCitas() throws IOException, ClientProtocolException, JSONException, ParseException, java.text.ParseException{
        total=0;
        JSONArray citasporfecha;
        JSONArray pacienteporid;
        JSONObject paciente;
        jCalendar1.setTodayButtonVisible(false);  
        jCalendar1.setForeground(Color.BLUE);//Pinta todas las fechas en azul, las que estan ocupadas se pintaran de rojo abajo
        jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo
        BorrarTextFields();
        PanelCita.removeAll();
        PanelCita.revalidate();
        PanelCita.repaint();
        PanelCita.setLayout(new GridBagLayout());
        FechaLbl.setText(formato.format(jCalendar1.getDate()));
         dibujarPanelCita(medico);//Dibuja la "libreta" de las citas
        agregarPaciente = true; //Por defecto si se debe agregar el paciente
        
        font=font  = font.deriveFont(Font.BOLD, 17);
        disenoLabel(FechaLbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        PanelCita.add(FechaLbl,gbc);
        //Aqui se busca esta fecha (jCalendar1.getDate()) en la base de datos y se traen las citas 
        System.out.println("LA FECHA:  " + formato.format(jCalendar1.getDate()));
        citasporfecha = rutasLeer.leer(Login.rutaBase+"Citas/porFecha/"+formato.format(jCalendar1.getDate())+"/"+Login.username);
        
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.BOTH;
            for (int i = 0;i<citasporfecha.length();i++) {  
                JSONObject obj = (JSONObject)citasporfecha.get(i);
                System.out.println("ENTRE EN EL FOR " + i + ": "+ obj.toString());
                
                for (int j = 0 ; j < citas.length ; j++){
                    System.out.println(citas[j].getHora() +"");
                    if(citas[j].getHora().equals(obj.get("hora").toString())){
                        pacienteporid = rutasLeer.leer(Login.rutaBase+"Pacientes/porId/"+obj.get("paciente"));
                        paciente = (JSONObject) pacienteporid.get(0);
                        citas[j].motivo=obj.get("motivo").toString();
                        citas[j].Paciente=obj.get("paciente").toString();
                        citas[j].paciente=paciente;
                        citas[j].setText(citas[j].getText()+" "+obj.get("paciente")+ " "+ paciente.get("cedula"));
                        total++;
                         //if(citas [i].getHora() == citasporfecha.get("id"))
                        System.out.println("voy a agregar las citas");
                        //citas [i] = new Citas (i);  
                        citas[j].setBorder(BorderFactory.createLineBorder(Color.black));
                        citas[j].setOpaque(true);
                        
                    
                    }
                }
               
                
                
                
                gbc.gridy = i+1;
                PanelCita.add (citas[i],gbc);  


            }  

            jCalendar1.setDate(jCalendar1.getDate());
            jCalendar1.revalidate();
            jCalendar1.repaint();
            PanelCita.revalidate();
            PanelCita.repaint();
    }
    
    private void dibujarPaciente(){
    }
    
     private void dibujarPanelCita(Medico medico){
        min = medico.getMinutosDeAtencionxPaciente(); 
        int horaInicio;
        int minInicio;
        int horaActual;
        int minActual;
        
        GridBagConstraints gbc = new GridBagConstraints();
       
        horaInicio=medico.getHoraInicio();
        horaActual=medico.getHoraInicio();
        minInicio=medico.getMinInicio();
        minActual=medico.getMinInicio();
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        
        for (int i = 0;i< medico.getCitasPorDia() ;i++) {
            minActual+=medico.getMinutosDeAtencionxPaciente();
            if(minActual==60){
                horaActual+=1;
                minActual=0;
            }
            gbc.gridy = i+1;
            System.out.print(String.format("%02d",horaInicio));
            citas [i]=new Citas (("" +String.format("%02d",horaInicio)+":" + String.format("%02d",minInicio)  + " - " + String.format("%02d",horaActual) + ":" + String.format("%02d",minActual)),("" +String.format("%02d",horaInicio)+":" + String.format("%02d",minInicio) + ":" +  String.format("%02d",0)));
            citas [i].setBorder(BorderFactory.createLineBorder(Color.black));
            citas [i].setOpaque(true);
            citas[i].Paciente=null;
            citas[i].fecha=formato.format(jCalendar1.getDate());
            citas [i].addMouseListener(new MouseListener() {              
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Citas seleccion = new Citas();
                                seleccion = (Citas) e.getComponent();
                                System.out.println("Label  clickeado sin cita" + seleccion.getText());
                        try {
                            acciones(seleccion);
                        } catch (JSONException ex) {
                            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            PanelCita.add (citas[i] ,gbc);
            System.out.print(i);
            horaInicio=horaActual;
            minInicio=minActual;
            }
        
    }
    
    
    

        public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==agregarB) {
            try {
                //Cita
                JSONObject cita=new JSONObject();
                cita.put("fecha",fechaJ.getText());
                cita.put("hora",horaJ.getText());
                
                //Paciente
                if(agregarPaciente){
                    JSONObject paciente=new JSONObject();
                    paciente.put("cedula",cedulaJ.getText());
                    paciente.put("nombre",nombreJ.getText());
                    paciente.put("apellido",apellidoJ.getText());
                    paciente.put("direccion",direccionJ.getText());
                    paciente.put("correo",correoJ.getText());
                    paciente.put("tlfncasa",telefonoCasaJ.getText());
                    paciente.put("tlfncelular",telefonoCelularJ.getText());
                    System.out.println("Entre en el IF y agregare paciente" + paciente);
                    rutasAdd.add(Login.rutaBase+"Pacientes/create", paciente);
                    JSONArray aux = new JSONArray();
                    JSONObject pacienteDentro = new JSONObject();
                    aux = (rutasLeer.leer(Login.rutaBase+"Pacientes/porCedula/"+cedulaJ.getText()));
                    pacienteDentro = (JSONObject) aux.get(0);
                    cita.put("paciente",pacienteDentro.getInt("id"));//Le a;ado el id del paciente a la cita segun el que se le acaba de crear
                    
                    
                }else{
                    cita.put("paciente",idPaciente);//Le a;ado el id del paciente que fue buscado
                }
                
                    cita.put("medicos","1");
                    cita.put("tratamiento","tratamiento");
                    cita.put("diagnostico","diagnostico");
                    cita.put("motivo", motivosTA.getText());
                    System.out.print(cita);
                
                rutasAdd.add(Login.rutaBase+"Citas/create", cita);
                
                setCitas();
                if(total==medico.getCitasPorDia()){
                JSONObject fecha = new org.json.JSONObject();
                fecha.put("diasOcupados", formato.format(jCalendar1.getDate()));
                System.out.println("Esta es la fecha a a;adir" + fecha.toString());
                rutasAdd.add(Login.rutaBase+"Diasocupados/insertarfecha", fecha);
                jCalendar1.getDayChooser().addDateEvaluator(new DJFechasEspInv());//Pinta las Fechas ocupadas en rojo 
                jCalendar1.setDate(jCalendar1.getDate());
            }
            } catch (IOException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
            
        }
        
        if (e.getSource()==modificarB) {
            
            
            modificarB.setEnabled(false);
            return;
        }
        if (e.getSource()==eliminarB) {
            //rutasAdd.add("http://localhost/API_Citas/public/Pacientes/create", paciente);
            
            return;
        }
        if (e.getSource()==atrasB) {
           
 
            
            
        }
        if (e.getSource()==buscarB) {
            JSONArray aux = new JSONArray();
            JSONObject paciente = new JSONObject();
            try {
                aux = (rutasLeer.leer(Login.rutaBase+"Pacientes/porCedula/"+cedulaJ.getText()));
            } catch (IOException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(aux.toString());
            if(aux.toString().equals("[]")){//Si no se encontro el paciente en la BD se setea agregarPAciente para que se agregue a la BD
                agregarPaciente = true;
                BorrarTextFields();
                String[] opciones = {"Aceptar"};
                int opcion = JOptionPane.showOptionDialog(
                        null //componente
                        , "Cedula no pertenece a ningun paciente registrado" // Mensaje
                        , "Paciente no encontrado" // Titulo en la barra del cuadro
                        , JOptionPane.DEFAULT_OPTION // Tipo de opciones
                        , JOptionPane.WARNING_MESSAGE // Tipo de mensaje (icono)
                        , null // Icono (ninguno)
                        , opciones // Opciones personalizadas
                        , null // Opcion por defecto
                );
            }else{
                try {
                    modificarB.setEnabled(true);
                    agregarPaciente=false;//Si se consigue, entonces no se debe a;adir.
                    paciente =(JSONObject) aux.get(0);
                    idPaciente=paciente.getInt("id");
                    cedulaJ.setText(paciente.getString("cedula"));
                    nombreJ.setText(paciente.getString("nombre"));
                    apellidoJ.setText(paciente.getString("apellido"));
                    direccionJ.setText(paciente.getString("direccion"));
                    correoJ.setText(paciente.getString("correo"));
                    telefonoCasaJ.setText(paciente.getString("tlfncasa"));
                    telefonoCelularJ.setText(paciente.getString("tlfncelular"));
                } catch (JSONException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
        
        
        
    }
        
        
        
    
        
    private void BorrarTextFields(){
        
            fechaJ.setText("");
            horaJ.setText("");
            nombreJ.setText("");
            apellidoJ.setText("");
            cedulaJ.setText("");
            direccionJ.setText("");
            telefonoCasaJ.setText("");
            telefonoCelularJ.setText("");
            correoJ.setText("");
            motivosTA.setText("");
        
    }
   
     private void acciones(Citas cita) throws JSONException{
        
        BorrarTextFields();
        if(cita.Paciente==null){
            atrasB.setEnabled(true);
            buscarB.setEnabled(true);
            modificarB.setEnabled(false);
            agregarB.setEnabled(true);
            eliminarB.setEnabled(false);
            fechaJ.setEditable(false);
            horaJ.setEditable(false);
            nombreJ.setEditable(true);
            apellidoJ.setEditable(true);
            cedulaJ.setEditable(true);
            direccionJ.setEditable(true);
            telefonoCasaJ.setEditable(true);
            telefonoCelularJ.setEditable(true);
            correoJ.setEditable(true); 
            motivosTA.setEditable(true);
            nombreJ.setText(cita.Paciente);
            cedulaJ.setText(cita.Cedula);
            fechaJ.setText(cita.fecha);
            horaJ.setText(cita.Hora);
            
        }else{
            
            eliminarB.setEnabled(true);
            atrasB.setEnabled(false);
            buscarB.setEnabled(false);
            modificarB.setEnabled(false);
            agregarB.setEnabled(false);
            fechaJ.setText(cita.fecha);
            horaJ.setText(cita.Hora);
            nombreJ.setText(cita.Paciente);
            cedulaJ.setText(cita.Cedula);
     
            cedulaJ.setText(cita.paciente.getString("cedula"));
            nombreJ.setText(cita.paciente.getString("nombre"));
            apellidoJ.setText(cita.paciente.getString("apellido"));
            direccionJ.setText(cita.paciente.getString("direccion"));
            correoJ.setText(cita.paciente.getString("correo"));
            telefonoCasaJ.setText(cita.paciente.getString("tlfncasa"));
            telefonoCelularJ.setText(cita.paciente.getString("tlfncelular"));
            motivosTA.setText(cita.motivo);
            
            fechaJ.setEditable(false);
            horaJ.setEditable(false);
            nombreJ.setEditable(false);
            apellidoJ.setEditable(false);
            cedulaJ.setEditable(false);
            direccionJ.setEditable(false);
            telefonoCasaJ.setEditable(false);
            telefonoCelularJ.setEditable(false);
            correoJ.setEditable(false);  
            motivosTA.setEditable(false);
        }
         
         
         
         
        
    }
    
    
    private void cambiarColorPanel(JPanel panel, Color color){
        
        panel.setBackground(color);
        
    }
            
    
    private int hex( String color_hex )
    {
        return Integer.parseInt(color_hex,  16 );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        PanelCalendar = new javax.swing.JPanel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        PanelCita = new javax.swing.JPanel();
        FechaLbl = new javax.swing.JLabel();
        PanelDetalle = new javax.swing.JPanel();
        panelHistoria = new javax.swing.JPanel();
        PanelHistorialScroll = new javax.swing.JScrollPane();
        PanelPacientes = new javax.swing.JPanel();
        PanelPacientesScroll = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 500));
        setPreferredSize(new java.awt.Dimension(1300, 600));

        PanelCalendar.setBorder(javax.swing.BorderFactory.createTitledBorder("Calendario"));
        PanelCalendar.setLayout(new java.awt.BorderLayout());

        jCalendar1.setMaxSelectableDate(new java.util.Date(2524627865000L));
        jCalendar1.setMinSelectableDate(new java.util.Date(1420090265000L));
        jCalendar1.setSundayForeground(new java.awt.Color(164, 0, 6));
        jCalendar1.setTodayButtonText("");
        jCalendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCalendar1MouseClicked(evt);
            }
        });
        PanelCalendar.add(jCalendar1, java.awt.BorderLayout.CENTER);

        PanelCita.setBorder(javax.swing.BorderFactory.createTitledBorder("Citas"));
        PanelCita.setAlignmentX(0.0F);
        PanelCita.setAlignmentY(0.0F);
        PanelCita.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PanelCita.setLayout(new java.awt.GridBagLayout());

        FechaLbl.setBackground(new java.awt.Color(255, 255, 255));
        FechaLbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FechaLbl.setForeground(new java.awt.Color(204, 0, 0));
        FechaLbl.setText("08/Diciembre/2015");
        PanelCita.add(FechaLbl, new java.awt.GridBagConstraints());

        PanelDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        javax.swing.GroupLayout PanelDetalleLayout = new javax.swing.GroupLayout(PanelDetalle);
        PanelDetalle.setLayout(PanelDetalleLayout);
        PanelDetalleLayout.setHorizontalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        PanelDetalleLayout.setVerticalGroup(
            PanelDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        panelHistoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Historia"));

        javax.swing.GroupLayout panelHistoriaLayout = new javax.swing.GroupLayout(panelHistoria);
        panelHistoria.setLayout(panelHistoriaLayout);
        panelHistoriaLayout.setHorizontalGroup(
            panelHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoriaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(PanelHistorialScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelHistoriaLayout.setVerticalGroup(
            panelHistoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoriaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(PanelHistorialScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        PanelPacientes.setBorder(javax.swing.BorderFactory.createTitledBorder("Pacientes"));

        javax.swing.GroupLayout PanelPacientesLayout = new javax.swing.GroupLayout(PanelPacientes);
        PanelPacientes.setLayout(PanelPacientesLayout);
        PanelPacientesLayout.setHorizontalGroup(
            PanelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPacientesLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(PanelPacientesScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        PanelPacientesLayout.setVerticalGroup(
            PanelPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPacientesLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(PanelPacientesScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jMenu1.setText("Citas");

        jMenuItem4.setText("Ir a panel de citas");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Configuración");

        jMenuItem3.setText("Ir a configuración");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Usuario");

        jMenuItem2.setText("Crear usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem6.setText("jMenuItem6");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("jMenuItem7");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(PanelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(PanelCita, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PanelPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(PanelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelCita, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(panelHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PanelPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        panelHistoria.getAccessibleContext().setAccessibleName("Historial");
        panelHistoria.getAccessibleContext().setAccessibleDescription("Historial");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCalendar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCalendar1MouseClicked
      
    }//GEN-LAST:event_jCalendar1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Usuario user = null;
        try {
            user = new Usuario();
        } catch (IOException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            FrameCita PanelCitas = new FrameCita();
            PanelCitas.setVisible(true);
        } catch (IOException | JSONException | ParseException | java.text.ParseException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
        try {
            Configuracion configuracion;
            configuracion = new Configuracion();
            configuracion.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.PanelPacientes.setVisible(true);
        this.PanelDetalle.setVisible(false);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
         this.PanelPacientes.setVisible(false);
        this.PanelDetalle.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameCita().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(FrameCita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FechaLbl;
    private javax.swing.JPanel PanelCalendar;
    private javax.swing.JPanel PanelCita;
    private javax.swing.JPanel PanelDetalle;
    private javax.swing.JScrollPane PanelHistorialScroll;
    private javax.swing.JPanel PanelPacientes;
    private javax.swing.JScrollPane PanelPacientesScroll;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel panelHistoria;
    // End of variables declaration//GEN-END:variables
}
