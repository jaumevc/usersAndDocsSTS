package app.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.ldap.LdapContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import app.security.ActiveDirectory;
import app.service.UtilsLog;
import app.service.UtilsTime;

public class FrameLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtFieldUser;
	private JPasswordField passField;
	private JButton btnValidar, btnReset;;

	/**
	 * Create the frame.
	 */
	public FrameLogin(JdbcTemplate jdbcTemplate) {
		setTitle("LOGIN GESTIO D'USUARIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 300);
//		setIconImage(new ImageIcon(getClass().getResource("/images/grupo.png")).getImage());
		setIconImage(new ImageIcon(getClass().getResource("/static/images/grupo.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuari = new JLabel("Usuari");
		lblUsuari.setBounds(40, 53, 74, 14);
		contentPane.add(lblUsuari);
		
		txtFieldUser = new JTextField();
		txtFieldUser.setBounds(148, 50, 158, 20);
		contentPane.add(txtFieldUser);
		txtFieldUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(40, 105, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		passField = new JPasswordField();
		passField.setBounds(148, 102, 158, 20);
		contentPane.add(passField);
		
		btnValidar = new JButton("Validar");
		btnValidar.setBounds(232, 177, 74, 23);
		contentPane.add(btnValidar);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(148, 177, 74, 23);
		contentPane.add(btnReset);
		
		addListeners(this, jdbcTemplate);
	}

	private void addListeners(FrameLogin frameLogin, JdbcTemplate jdbcTemplate) {
		btnValidar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				// TODO casa: comentar el if i deixar el try-catch de dins del else
				if(StringUtils.equals(txtFieldUser.getText(), "Jaume")) {
					
					JOptionPane.showMessageDialog(rootPane, "Validació OK!!");
					UtilsLog.crearFitxer(
							"C:/sarroca/comu-inf$/Suport/Logs/arxiu_loginOK_" + txtFieldUser.getText() + "_"
									+ UtilsTime.nowName() + ".log",
							txtFieldUser.getText() + ", login OK\n" + UtilsTime.now());

					frameLogin.setVisible(false);

					FrameUsers frame = new FrameUsers(txtFieldUser.getText(), jdbcTemplate);
					frame.setVisible(true);
					
				} else {

					try {
						LdapContext ctx = ActiveDirectory.getConnection(txtFieldUser.getText(), passField.getText());
						ctx.close();

						JOptionPane.showMessageDialog(rootPane, "Validació OK!!");

						UtilsLog.crearFitxer("\\\\sarroca\\comu-inf$\\Suport\\Logs\\arxiu_loginOK_"+txtFieldUser.getText()+"_"+UtilsTime.nowName()+".log", txtFieldUser.getText()+", login OK\n"+UtilsTime.now());

						frameLogin.setVisible(false);

						FrameUsers frame = new FrameUsers(txtFieldUser.getText(), jdbcTemplate);
						frame.setVisible(true);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(rootPane, "Validació KO!!\n" + ex.getMessage());
						UtilsLog.crearFitxer("\\\\sarroca\\comu-inf$\\Suport\\Logs\\arxiu_loginKO_"+txtFieldUser.getText()+"_"+UtilsTime.nowName()+".log", txtFieldUser.getText()+", login KO\n"+UtilsTime.now());
						txtFieldUser.setText("");
						passField.setText("");
						txtFieldUser.requestFocus();
					}
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldUser.setText("");
				passField.setText("");
				txtFieldUser.requestFocus();
			}
		});
		
	}
}
