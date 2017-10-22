package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;



import service.ExploitService;
import enumeration.Version;

public class ZuploadPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField pathJTextField;
	private JButton uploadJButton;
	private JTextPane textPane;

	private String host;
	private Version version;

	public ZuploadPanel() {
		setSize(600, 460);
		setVisible(true);

		JLabel cmdJLabel = new JLabel("PATH");

		pathJTextField = new JTextField("/tmp/file.txt");
		pathJTextField.setColumns(10);

		uploadJButton = new JButton("上传");
		uploadJButton.addActionListener(this);

		textPane = new JTextPane();

		JScrollPane scrollPane = new JScrollPane(textPane);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(cmdJLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(pathJTextField, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE).addGap(20)
								.addComponent(uploadJButton, GroupLayout.PREFERRED_SIZE, 60,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(cmdJLabel)
								.addComponent(pathJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(uploadJButton))
						.addGap(10).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addContainerGap()));
		setLayout(groupLayout);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == uploadJButton) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					request();
				}
			}).start();
		}
	}

	private void request() {
		try {
			String path = pathJTextField.getText().trim();
			if (host != null && path.length() > 0) {

				ExploitService service = (ExploitService) Class
						.forName("service.impl." + version.name() + "ExploitServiceImpl").newInstance();
				System.out.println("host: "+host+" ; path: "+path+" textPane:"+textPane.getText());
				System.out.println("12312312312");
				boolean flag = service.doCustomUplaod(host, path, textPane.getText());

				if (flag) {
					JOptionPane.showMessageDialog(this, "上传成功，别忘了删除!", "消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "上传失败!", "消息", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(this, "请输入文件路径");
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			JOptionPane.showMessageDialog(this, "上传失败!!!!!!!!!!", "消息", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setReqestUrl(String host) {
		this.host = host;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
}
