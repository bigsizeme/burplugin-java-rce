package ui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import enumeration.Version;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField urlJTextField;
	private JComboBox<String> versionJcomboBox;
	private JLabel urlJLabel ;
	 
	
	
	public MainPanel(){
		
		urlJLabel = new JLabel("URL");

		urlJTextField = new JTextField("http://192.168.144.22:9200");
		urlJTextField.setColumns(10);

		versionJcomboBox = new JComboBox<String>();

		for (Version version : Version.values()) {
			versionJcomboBox.addItem(version.name());
		}
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		final CmdPanel cmdPanel = new CmdPanel();

		final ZuploadPanel zuploadPanel = new ZuploadPanel();

		final CheckAllPanel cap = new CheckAllPanel();
		
		tabbedPane.add("全系列漏洞校验", cap);
		tabbedPane.add("命令执行", cmdPanel);
		tabbedPane.add("自定义上传", zuploadPanel);

		urlJTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);

				if (!urlJTextField.getText().trim().startsWith("https://")
						&& !urlJTextField.getText().trim().startsWith("http://")) {
					urlJTextField.setText("http://" + urlJTextField.getText().trim());
				}

				cmdPanel.setReqestUrl(urlJTextField.getText().trim());
				cmdPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				zuploadPanel.setReqestUrl(urlJTextField.getText().trim());
				zuploadPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				cap.setReqestUrl(urlJTextField.getText().trim());
				cap.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));

			}
		});
		
		versionJcomboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);

				if (!urlJTextField.getText().trim().startsWith("https://")
						&& !urlJTextField.getText().trim().startsWith("http://")) {
					urlJTextField.setText("http://" + urlJTextField.getText().trim());
				}

				cmdPanel.setReqestUrl(urlJTextField.getText().trim());
				cmdPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				zuploadPanel.setReqestUrl(urlJTextField.getText().trim());
				zuploadPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				cap.setReqestUrl(urlJTextField.getText().trim());
				cap.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));

			}
		});
		
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);

				if (!urlJTextField.getText().trim().startsWith("https://")
						&& !urlJTextField.getText().trim().startsWith("http://")) {
					urlJTextField.setText("http://" + urlJTextField.getText().trim());
				}
				cmdPanel.setReqestUrl(urlJTextField.getText().trim());
				cmdPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				zuploadPanel.setReqestUrl(urlJTextField.getText().trim());
				zuploadPanel.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				cap.setReqestUrl(urlJTextField.getText().trim());
				cap.setVersion(Version.valueOf(versionJcomboBox.getSelectedItem().toString()));
				

			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(urlJLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(urlJTextField, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(versionJcomboBox, GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10))
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(urlJLabel)
										.addComponent(urlJTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(versionJcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));
		this.setLayout(groupLayout);

	
	}
	
}
