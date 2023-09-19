package app.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import app.model.FilesFromFolder;

public class FrameDocManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnComprovaErrors, btnTornar, btnCarregaDocuments;
	
	//fitxer en floder arrel = 1; fitxer en folder error = 2
	private Integer tipusFitxer;

	/**
	 * Create the frame.
	 */
	public FrameDocManagement(String userNameAD) {
		setTitle("GESTIO DE DOCUMENTS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 300);
		
		setIconImage(new ImageIcon(getClass().getResource("/images/grupo.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnComprovaErrors = new JButton("COMPROVA DOCS AMB ERROR");
		btnComprovaErrors.setBackground(Color.RED);
		btnComprovaErrors.setForeground(Color.WHITE);
		btnComprovaErrors.setBounds(111, 100, 235, 39);
		btnComprovaErrors.setFocusable(false);
		contentPane.add(btnComprovaErrors);
		
		btnTornar = new JButton("Pantalla Anterior");
		btnTornar.setBounds(305, 182, 140, 42);
		contentPane.add(btnTornar);
		
		btnCarregaDocuments= new JButton("CARREGA DOCUMENTS ARREL");
		btnCarregaDocuments.setBackground(Color.DARK_GRAY);
		btnCarregaDocuments.setForeground(Color.WHITE);
		btnCarregaDocuments.setBounds(111, 32, 235, 39);
		btnCarregaDocuments.setFocusable(false);
		contentPane.add(btnCarregaDocuments);
		
		addListeners(this, userNameAD);
	}

	private void addListeners(FrameDocManagement frameDocManagement, String userNameAD) {
		
		btnTornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameDocManagement.setVisible(false);
//				FrameUsers frameUsers = new FrameUsers(userNameAD);
//				frameUsers.setVisible(true);
			}
		});
		
		btnCarregaDocuments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Map<Integer, List<File>> fileMatrix = new HashMap<>();
				tipusFitxer = 1;
//				FilesFromFolder ff = new FilesFromFolder(tipusFitxer);
//				fileMatrix = ff.checkFiles(tipusFitxer);
				
				Integer fitxersOK = !fileMatrix.isEmpty() && !fileMatrix.get(1).isEmpty() ? fileMatrix.get(1).size():0; 
				Integer fitxersKO= !fileMatrix.isEmpty() && !fileMatrix.get(2).isEmpty() ? fileMatrix.get(2).size():0;
				
				JOptionPane.showMessageDialog(rootPane, 
						"Fitxers moguts correctament amb usuari registrat: " + fitxersOK
						+"\nFitxers moguts a carpeta error per usuari NO registrat: "+fitxersKO);
			}
		});
		
		btnComprovaErrors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<Integer, List<File>> fileMatrix = new HashMap<>();
				tipusFitxer = 2;
//				FilesFromFolder ff = new FilesFromFolder(tipusFitxer);
//				fileMatrix = ff.checkFiles(tipusFitxer);

				Integer fitxersOK = !fileMatrix.isEmpty() && !fileMatrix.get(1).isEmpty() ? fileMatrix.get(1).size()
						: 0;
				Integer fitxersKO = !fileMatrix.isEmpty() && !fileMatrix.get(2).isEmpty() ? fileMatrix.get(2).size()
						: 0;

				if (fitxersOK == 0 && fitxersKO == 0) {
					JOptionPane.showMessageDialog(rootPane,
							"Fitxers moguts de carpeta ERROR(fitxers sense usuari registrat): "
									+ fitxersOK + " fitxers .pdf moguts");
				} else {
					JOptionPane.showMessageDialog(rootPane,
							"Fitxers moguts a carpeta CARREGA(fitxers d'usuari registrat),\ndes de carpeta ERROR(fitxers sense usuari registrat): "
									+ fitxersOK + " fitxers .pdf"
									+ "\n\nFitxers NO moguts de la carpeta ERROR per pertanyer a usuari NO registrat: "
									+ fitxersKO + " fitxers .pdf");
				}
			}
			});
	}
}
