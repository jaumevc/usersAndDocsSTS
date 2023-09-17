package com.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

//import app.beans.User;
//import app.dao.UserDAO;
//import app.model.UtilsLog;
//import app.model.UtilsTime;
import java.awt.SystemColor;

public class FrameUsers extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JLabel lblNif, lblCognom1, lblCognom2, lblNom, lblLogin, lblChecked;
	
	private JTextField textFieldNif, textFieldCognom1, textFieldCognom2, textFieldNom, textFieldLogin;
	
	private JCheckBox cBChecked;
	
	private JButton btnAfegirUsuari, btnCheckDocs;

	/**
	 * Create the frame.
	 * @param usuari 
	 */
	public FrameUsers(String usuari) {
		setTitle("GESTIO D'USUARIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 616);
		
//		setIconImage(new ImageIcon(getClass().getResource("/images/grupo.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNif = getLabelNif("NIF:");
		contentPane.add(lblNif);
		textFieldNif = getTexteFieldNif();
		contentPane.add(textFieldNif);
		
		lblCognom1 = getLabelCognom1("Cognom1:"); 
		contentPane.add(lblCognom1);
		
		textFieldCognom1 = getTextFieldCognom1();
		contentPane.add(textFieldCognom1);
		
		lblCognom2 = getLblCognom2("Cognom2:"); 
		contentPane.add(lblCognom2);
		
		textFieldCognom2 = getTfCognom2();
		contentPane.add(textFieldCognom2);

		lblNom = getLabelNom("Nom:");
		contentPane.add(lblNom);
		
		textFieldNom = getTextFieldNom(); 
		contentPane.add(textFieldNom);
		
		lblLogin = getLblLogin("Login:"); 
		contentPane.add(lblLogin);
		
		textFieldLogin = getTfLogin();
		contentPane.add(textFieldLogin);
		
		lblChecked = getLblCheckBox("Checked:");
		contentPane.add(lblChecked);
		
		cBChecked = getChecBoxForm(); 
		contentPane.add(cBChecked);
		
		btnAfegirUsuari = getBotoAddUser("Afegir Usuari");
		
		btnCheckDocs = getBotoCheckDocs("Comprova Docs");
		
		contentPane.add(btnAfegirUsuari);
		contentPane.add(btnCheckDocs);
		
		addListeners(usuari, this); 
	}
	
	

	private void addListeners(String userName, FrameUsers frameUsers) {
		
		textFieldNif.addFocusListener(new FocusListener()  {
			 @Override
			 public void focusLost(FocusEvent e) {
				 	Pattern patro = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
					Matcher mat = patro.matcher(textFieldNif.getText());
					if("".equals(textFieldNif.getText()))
						textFieldNif.requestFocus();
					else if(textFieldNif.getText().length()!=9 && !mat.matches()) {
						JOptionPane.showMessageDialog(rootPane, "FORMAT DEL NIF  INCORRECTE!!\n(Ha d'estar format per 8 numeros + Lletra,\nsense espais ni punts)");
						textFieldNif.requestFocus();
					}
				}

			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		textFieldCognom1.addFocusListener(new FocusListener()  {
			@Override
			public void focusLost(FocusEvent e) {
					
					Pattern patro = Pattern.compile("^[A-Za-zñÑáàéèíóòúÁÀÉÈÍÓÒÚ\\s]*$");
					Matcher mat = patro.matcher(textFieldCognom1.getText());
					if(!textFieldCognom1.getText().isEmpty() 
							&& (textFieldCognom1.getText().length()>50 || !mat.matches())) {
						JOptionPane.showMessageDialog(rootPane, "EL FORMAT DEL COGNOM ÉS INCORRECTE.\n(El cognom no pot tenis més de 50 caracters i tampoc pot tenir números ni signes de puntuació)");
						textFieldCognom1.requestFocus();
					}
				}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		textFieldCognom2.addFocusListener(new FocusListener()  {
			@Override
			public void focusLost(FocusEvent e) {
					Pattern patro = Pattern.compile("^[A-Za-zñÑáàéèíóòúÁÀÉÈÍÓÒÚ\\s]*$");
					Matcher mat = patro.matcher(textFieldCognom2.getText());
					if(!textFieldCognom2.getText().isEmpty() 
							&& ( textFieldCognom2.getText().length()>50 || !mat.matches())) {
						JOptionPane.showMessageDialog(rootPane, "EL FORMAT DEL COGNOM ÉS INCORRECTE.\n(El Cognom no pot tenis més de 50 caracters i tampoc pot tenir números ni signes de puntuació)");
						textFieldCognom2.requestFocus();
					}
				}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		textFieldNom.addFocusListener(new FocusListener()  {
			@Override
			public void focusLost(FocusEvent e) {
				Pattern patro = Pattern.compile("^[A-Za-zñÑáàéèíóòúÁÀÉÈÍÓÒÚ\\s]*$");
				Matcher mat = patro.matcher(textFieldNom.getText());
				if(!textFieldNom.getText().isEmpty() 
						&& (textFieldNom.getText().length()>50 || !mat.matches())) {
					JOptionPane.showMessageDialog(rootPane, "EL FORMAT DEL NOM ÉS INCORRECTE.\n(El Cognom no pot tenis més de 50 caracters i tampoc pot tenir números ni signes de puntuació)");
					textFieldNom.requestFocus();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		textFieldLogin.addFocusListener(new FocusListener()  {
			@Override
			public void focusLost(FocusEvent e) {
				Pattern patro = Pattern.compile("^[A-Za-zñÑáàéèíóòúÁÀÉÈÍÓÒÚ\\s]*$");
				Matcher mat = patro.matcher(textFieldNom.getText());
				if(textFieldLogin.getText().isEmpty() 
						|| textFieldLogin.getText().length()>20 
						|| !mat.matches()) {
					JOptionPane.showMessageDialog(rootPane, "EL FORMAT DEL LOGIN ÉS INCORRECTE.\n(El Cognom no pot tenis més de 50 caracters i tampoc pot tenir números ni signes de puntuació)");
					textFieldLogin.requestFocus();
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
		
		cBChecked.addItemListener(new ItemListener() {
			@SuppressWarnings("static-access")
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == e.SELECTED) {
					cBChecked.setSelected(true);
				}else {
					cBChecked.setSelected(false);
				}
			}
		});

		btnAfegirUsuari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNif.getText().isEmpty()
						&& !textFieldCognom1.getText().isEmpty()
						&& !textFieldCognom2.getText().isEmpty()
						&& !textFieldNom.getText().isEmpty()
						&& !textFieldLogin.getText().isEmpty()) {
					
//					User usuari=new User();
//					usuari.setNif(textFieldNif.getText().toUpperCase());
//					usuari.setCognom1(textFieldCognom1.getText().toUpperCase());
//					usuari.setCognom2(textFieldCognom2.getText().toUpperCase());
//					usuari.setNom(textFieldNom.getText().toUpperCase());
//					usuari.setLogin(textFieldLogin.getText());
					
					if(cBChecked.isSelected()) {
//						usuari.setChecked(true);
					} else {
//						usuari.setChecked(false);
					}
					
//					UserDAO daoUser =  new UserDAO();
//					try {
//						String registre = daoUser.registrarUser(usuari);
//						JOptionPane.showMessageDialog(rootPane, registre);
//						UtilsLog.crearFitxer("\\\\sarroca\\comu-inf$\\Suport\\Logs\\arxiu_registre_"+userName+"_"+UtilsTime.nowName()+".log", "L'usuari: "+userName+" ha creat el registre: "+registre+"\n"+UtilsTime.now());
						netejaTextFields();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				}
			}
		});
		
		btnCheckDocs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				netejaTextFields();
				//Li passo el nom d'usuari que s'ha loguejat en la sessio, per si s'han de fer accions de LOG
				FrameDocManagement checkFrame = new FrameDocManagement(userName);
				frameUsers.setVisible(false);
				checkFrame.setVisible(true);
			}
		});
		
	}

	private JCheckBox getChecBoxForm() {
		JCheckBox cbEnable= new JCheckBox();
		cbEnable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbEnable.setSelected(true);
		cbEnable.setBounds(182, 426, 21, 26);
		return cbEnable;
	}

	private JLabel getLblCheckBox(String checBoxname) {
		JLabel checBoxLbl = new JLabel(checBoxname);
		checBoxLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		checBoxLbl.setBounds(63, 426, 92, 26);
		return checBoxLbl;
	}

	private JTextField getTfLogin() {
		JTextField txfLogin = new JTextField();
		txfLogin.setToolTipText("");
		txfLogin.setColumns(20);
		txfLogin.setBounds(182, 366, 160, 20);
		
		return txfLogin;
	}

	private JLabel getLblLogin(String login) {
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(63, 362, 92, 26);
		return lblLogin;
	}

	private JTextField getTextFieldNom() {
		JTextField txfNom = new JTextField();
		txfNom.setToolTipText("");
		txfNom.setColumns(50);
		txfNom.setBounds(182, 306, 307, 20);
		return txfNom;
	}

	private JLabel getLabelNom(String nom) {
		JLabel lblNom = new JLabel(nom);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNom.setBounds(63, 302, 92, 26);
		return lblNom;
	}

	private JTextField getTfCognom2() {
		JTextField tfCognom2= new JTextField();
		tfCognom2.setToolTipText("");
		tfCognom2.setColumns(50);
		tfCognom2.setBounds(182, 245, 307, 20);
		
		return tfCognom2;
	}

	private JLabel getLblCognom2(String cognom2) {
		JLabel lblCognom2 = new JLabel(cognom2);
		lblCognom2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCognom2.setBounds(63, 241, 92, 26);
		return lblCognom2;
	}

	private JTextField getTextFieldCognom1() {
		JTextField cognom1Tf= new JTextField();
		cognom1Tf.setToolTipText("");
		cognom1Tf.setColumns(50);
		cognom1Tf.setBounds(182, 184, 307, 20);
		
		return cognom1Tf;
	}

	private JLabel getLabelCognom1(String cognom) {
		JLabel cognomLbl = new JLabel(cognom);
		cognomLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cognomLbl.setBounds(63, 180, 92, 26);
		
		return cognomLbl;
	}

	private JLabel getLabelNif(String nif) {
		JLabel nifLbel= new JLabel(nif);
		nifLbel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nifLbel.setBounds(63, 126, 57, 26);
		return nifLbel;
	}

	private JTextField getTexteFieldNif() {
		JTextField nifTf= new JTextField();
		nifTf.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		nifTf.setToolTipText("");
		nifTf.setBounds(182, 130, 160, 20);
		nifTf.setColumns(10);
		
		return nifTf;
	}

	private JButton getBotoAddUser(String texteBto) {
		JButton btn = new JButton(texteBto);
		btn.setBounds(354, 496, 171, 37);
		btn.setEnabled(true);
		return btn;
	}
	
	private JButton getBotoCheckDocs(String txtBtoCheckDocs) {
		JButton btnCheckDocs = new JButton(txtBtoCheckDocs);
		btnCheckDocs.setBackground(SystemColor.textHighlight);
		btnCheckDocs.setForeground(Color.WHITE);
		btnCheckDocs.setBounds(354, 30, 171, 37);
		btnCheckDocs.setFocusable(false);
		btnCheckDocs.setEnabled(true);
		return btnCheckDocs;
	}

	private void netejaTextFields() {
		textFieldNif.setText("");
		textFieldCognom1.setText("");
		textFieldCognom2.setText("");
		textFieldNom.setText("");
		textFieldLogin.setText("");
		cBChecked.setSelected(true);
	}
}
