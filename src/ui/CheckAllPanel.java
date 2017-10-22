package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import enumeration.Version;





public class CheckAllPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField cmdJTextField;
	private JButton exeJButton;
	private JButton fileJButton;
	private JButton pythonJButton;
	private JTextPane textPane;
	private String host;
	private Version version;
	private String message="";  
	private  File targetFile; 

	public CheckAllPanel() {
		setSize(600, 460);

		JLabel cmdJLabel = new JLabel("ÅúÁ¿ÎÄ¼þÂ·¾¶");

		cmdJTextField = new JTextField();
		cmdJTextField.setColumns(7);
		fileJButton = new JButton("ÅúÁ¿ÎÄ¼þÑ¡Ôñ");
		pythonJButton = new JButton("python");
		Font f=new Font("ËÎÌå", Font.PLAIN, 10);
		pythonJButton.setFont(f);
		exeJButton = new JButton("Ö´ÐÐ");
		exeJButton.addActionListener(this);
		cmdJTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					new Thread(new Runnable() {
						@Override
						public void run() {
							request();
						}
					}).start();

				}
			}
		});
		
		fileJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				jButton1ActionActionPerformed(event);  
			}
		});
		
		
		
		pythonJButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					pythonActionPerformed(event);
				} catch (IOException | BadLocationException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
			}
			
		});
		textPane = new JTextPane();
		textPane.setText("******±¾Èí¼þ½öÏÞÓÃÓÚÑ§Ï°½»Á÷½ûÖ¹ÓÃÓÚÈÎºÎ·Ç·¨ÐÐÎª******\n±¾°æ±¾Ö§³Öweblogic·´ÐòÁÐ»¯Â©¶´ÃüÁîÖ´ÐÐ¼°ÎÄ¼þÉÏ´«£¬elasticsearch javaÓïÑÔÔ¶³ÌÃüÁîÖ´ÐÐ¼°ÎÄ¼þÉÏ´«\nelasticsearchgroovÓïÑÔÔ¶³ÌÃüÁîÖ´ÐÐ¼°ÎÄ¼þÉÏ´«\nstruts2-005¡¢struts2-009¡¢struts2-013¡¢struts2-016¡¢struts2-019¡¢struts2-020¡¢struts2-devmode¡¢\nstruts2-032¡¢struts2-033¡¢struts2-037¡¢struts2-045¡¢struts2-048¡¢struts2-052 ³ýstruts2-053È«²¿RCEÂ©¶´ÑéÖ¤²¢Ö§³ÖÅúÁ¿ÑéÖ¤¡£\nStruts2Â©¶´ÑéÖ¤ÐèÒªpython»·¾³²¢ÐèÒªÏà¹ØÀà¿âÖ§³Ö.µã»÷python°´Å¥³õÊ¼»¯³õÊ¼»¯pythonÀà¿â\n\nÈç¹û³õÊ¼»¯Ê§°ÜÇë°´ÕÕÈçÏÂ²½Öè°²×°Àà¿â£¬\n1¡¢Ö´ÐÐ $[python]/Scrips/easy_install pip\n2¡¢requestsÄ£¿é °²×°·½·¨ pip install requests\n3¡¢termcolorÄ£¿é°²×°·½·¨£º pip install termcolor");
		textPane.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textPane);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(cmdJLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(cmdJTextField, GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE).addGap(20)
								.addComponent(pythonJButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(20)
								.addComponent(fileJButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE).addGap(20)
								.addComponent(exeJButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(cmdJLabel)
								.addComponent(cmdJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
										.addComponent(pythonJButton)
										.addComponent(fileJButton)
								.addComponent(exeJButton))
						.addGap(10).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addContainerGap()));
		setLayout(groupLayout);
		setVisible(true);
	}
	//³õÊ¼»¯python»·¾³
	private void pythonActionPerformed(ActionEvent event) throws IOException, BadLocationException, InterruptedException{
		textPane.setText("¿ªÊ¼°²×°  pip..........\n");
		String prefix = process("python -c \"import sys; print sys.prefix\" ");
		String eastInstall = prefix +"/Scripts/easy_install";
		
//		textPane.setText("¿ªÊ¼°²×°  pip¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£");
		processWritePane(eastInstall+" pip");
		
		Thread.sleep(1000);
		System.out.println("¿ªÊ¼°²×°  requests¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£");
//		textPane.setText("¿ªÊ¼°²×°  requests¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£");
		processWritePane("pip install requests ");
		
		Thread.sleep(1000);
//		textPane.setText("¿ªÊ¼°²×°  termcolor¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£");
		System.out.println("¿ªÊ¼°²×°  termcolor¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£¡£");
		processWritePane("pip install  termcolor ");
		
	}
	
	private String process(String cmd) throws IOException{
		
		Process process = Runtime.getRuntime().exec(cmd);  
		InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,12);
        StyleConstants.setForeground(red, Color.RED);
        Document docs = textPane.getDocument();//»ñµÃÎÄ±¾¶ÔÏó
        String line = null;
        String str = null;
        
        try {
                while ((line = bufferedReader.readLine()) != null) {
                	System.out.println(line);
                	str =  line;
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		return str;
	}
	
	private String processWritePane(String cmd) throws IOException, BadLocationException{
		
		Process process = Runtime.getRuntime().exec(cmd);  
		InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,12);
        StyleConstants.setForeground(red, Color.RED);
        Document docs = textPane.getDocument();//»ñµÃÎÄ±¾¶ÔÏó
        String line = null;
        
        try {
                while ((line = bufferedReader.readLine()) != null) {
                	docs.insertString(docs.getLength(), line+"\n", attrset);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
                	System.out.println(line);
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		return line;
	}
	
	//¶ÁÈ¡ÎÄ¼þ  
    private void jButton1ActionActionPerformed(ActionEvent event) {  
        JFileChooser dlg = new JFileChooser();   
        /** 
         * ÉèÖÃ¶Ô»°¿òÀàÐÍ,Æä´ò¿ª·½·¨Ò²Òª¶ÔÓ¦ÕýÈ·£¬´ò¿ª·½·¨¿ØÖÆÏÔÊ¾½çÃæ 
         */  
        dlg.setDialogType(JFileChooser.OPEN_DIALOG);  
        //Éè¶¨ºÃÀàÐÍºóºÍdlg.showOpenDialog(null)·½·¨ÅäÌ×Ê¹ÓÃ²Å»áÏÔÊ¾±£´æ¶Ô»°¿ò  
//      dlg.setDialogType(JFileChooser.SAVE_DIALOG);  
        //Éè¶¨ºÃÀàÐÍºóºÍdlg.showDialog(null, "haha")·½·¨ÅäÌ×Ê¹ÓÃ£¬²¢ÇÒµÚÒ»¸ö°´Å¥ÏÔÊ¾ÎªµÚ¶þ¸ö²ÎÊý"haha".  
//      dlg.setDialogType(JFileChooser.CUSTOM_DIALOG);  
          
          
        //µÚÒ»¸ö²ÎÊý¸¸×é¼þ£¬µÚ2 ¸ö²ÎÊýÊÇµÚÒ»¸ö°´Å¥µÄÃû×Ö£¨²»ÔÙÊÇ´ò¿ª»ò±£´æÁË£©  
//      dlg.showDialog(null, "haha");  
       int value =  dlg.showOpenDialog(null);  
//      dlg.showSaveDialog(null);  
       if(value==JFileChooser.APPROVE_OPTION){
    	   File file=dlg.getSelectedFile(); 
           if(file==null){  
           	JOptionPane.showMessageDialog(this, "ÎÄ¼þ²»´æÔÚ");
           }else{  
               //µÃµ½Õâ¸öÎÄ¼þºó£¬Ïë¸ÉÂï¸ÉÂï  
               System.out.println("getAbsolutePath--"+file.getAbsolutePath());  
               System.out.println("getName--"+file.getName());  
               System.out.println("isFile--"+file.isFile());  
               System.out.println("isDirectory--"+file.isDirectory());  
               System.out.println("getPath--"+file.getPath());  
               System.out.println("getParent--"+file.getParent());  
               cmdJTextField.setText(file.getPath());
               this.targetFile=file;  
               this.message=file.getPath();  
                 
           }  
       }else{
    	   return;
       }   
       
    } 

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == exeJButton) {
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
			textPane.setText("");
			String command = cmdJTextField.getText().trim();
			if (host != null ) {

				textPane.setText("ÇëÉÔµÈ...\n");

//				ExploitService service = (ExploitService) Class
//						.forName("com.axtx.service.impl." + version.name() + "ExploitServiceImpl").newInstance();

//				textPane.setText(service.doExecuteCMD(host, command));
				if(version.name().equals("CheckAll")){
//					URL resource = getClass().getResource("/resource/s-scan.py");
					 InputStream sourceURL = getClass().getResourceAsStream("/resource/s-scan.py");
//					File file = new File(sourceURL);
//					readFileByLines(file);
					File acc = new File("e:/acc.py");
					if(!acc.exists()){
						copyFile(sourceURL,"e:/acc.py");
						Thread.sleep(500);
					}
					
//					String url = getClass().getClassLoader().getResource("/").getPath();
//					String url = resource.getPath();
					Process process = Runtime.getRuntime().exec("python "+ "e:/acc.py" +" "+host);  
					InputStream inputStream = process.getInputStream();
			        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			        SimpleAttributeSet attrset = new SimpleAttributeSet();
			        SimpleAttributeSet red = new SimpleAttributeSet();
			        StyleConstants.setFontSize(attrset,12);
			        StyleConstants.setForeground(red, Color.RED);
			        
			        
			        Document docs = textPane.getDocument();//»ñµÃÎÄ±¾¶ÔÏó
			        String line;
			        
			        try {
			                while ((line = bufferedReader.readLine()) != null) {
			                	System.out.println(line);
			                	if(line.startsWith("[32m")){
			                		line= line.replace("[32m", "").replace("[0m", "");
			                		String newStr = new String(line.getBytes(), "UTF-8");  
			                		docs.insertString(docs.getLength(), newStr+"\n", attrset);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}else if(line.startsWith("[31m")){
			                		line= line.replace("[31m", "").replace("[0m", "");
			                		String newStr = new String(line.getBytes(), "UTF-8");  
			                		docs.insertString(docs.getLength(), newStr+"\n", red);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}else{
			                		String newStr = new String(line.getBytes(), "UTF-8");  
			                		docs.insertString(docs.getLength(), newStr+"\n", red);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}
			                	
			                	
			                }
			        } catch (IOException e) {
			                e.printStackTrace();
			        }
					process.waitFor();  
				}else if(version.name().equals("Batch")){
//					URL resource = getClass().getResource("/resource/s-scan.py");
					InputStream sourceURL = getClass().getResourceAsStream("/resource/s-scan.py");
					
//					File file = new File(resource.toURI());
//					readFileByLines(file);
					File acc = new File("e:/acc.py");
					if(!acc.exists()){
						copyFile(sourceURL,"e:/acc.py");
						Thread.sleep(500);
					}
					
					
					Process process = Runtime.getRuntime().exec("python "+ "e:/acc.py" +" -f " +" "+cmdJTextField.getText());
					InputStream inputStream = process.getInputStream();
			        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			        SimpleAttributeSet attrset = new SimpleAttributeSet();
			        SimpleAttributeSet red = new SimpleAttributeSet();
			        StyleConstants.setFontSize(attrset,12);
			        StyleConstants.setForeground(red, Color.RED);
			        Document docs = textPane.getDocument();//»ñµÃÎÄ±¾¶ÔÏó
			        String line;
			        
			        try {
			                while ((line = bufferedReader.readLine()) != null) {
			                	System.out.println(line);
			                	if(line.startsWith("[32m")){
			                		
			                		line= line.replace("[32m", "").replace("[0m", "");
			                		String newStr = new String(line.getBytes(), "UTF-8"); 
			                		docs.insertString(docs.getLength(), newStr+"\n", attrset);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}else if(line.startsWith("[31m")){
			                		line= line.replace("[31m", "").replace("[0m", "");
			                		String newStr = new String(line.getBytes(), "UTF-8"); 
			                		docs.insertString(docs.getLength(), newStr+"\n", red);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}else{
			                		line= line.replace("[32m", "").replace("[0m", "").replace("[36m", "");
			                		String newStr = new String(line.getBytes(), "UTF-8"); 
			                		docs.insertString(docs.getLength(), newStr+"\n", attrset);//¶ÔÎÄ±¾½øÐÐ×·¼Ó
			                	}
			                	
			                	
			                }
			        } catch (IOException e) {
			                e.printStackTrace();
			        }
					process.waitFor(); 
				}

			} else {
				JOptionPane.showMessageDialog(this, "ÇëÊäÈëÖ´ÐÐÃüÁî");
			}
		} catch (Exception exp) {
			textPane.setText(exp.getMessage());
		}
	}

	public void setReqestUrl(String host) {
		this.host = host;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
	
	 public static void readFileByLines(File file) {
//	        File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            System.out.println("ÒÔÐÐÎªµ¥Î»¶ÁÈ¡ÎÄ¼þÄÚÈÝ£¬Ò»´Î¶ÁÒ»ÕûÐÐ£º");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // Ò»´Î¶ÁÈëÒ»ÐÐ£¬Ö±µ½¶ÁÈënullÎªÎÄ¼þ½áÊø
	            while ((tempString = reader.readLine()) != null) {
	                // ÏÔÊ¾ÐÐºÅ
	                System.out.println("line " + line + ": " + tempString);
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }
	 
	 
	 public static void copyFile( InputStream inStream, String newPath) { 
	       try { 
	           int bytesum = 0; 
	           int byteread = 0; 
//	               InputStream inStream = new FileInputStream(oldfile);      //¶ÁÈëÔ­ÎÄ¼þ 
	               FileOutputStream fs = new FileOutputStream(newPath); 
	               byte[] buffer = new byte[1444]; 
	               int length; 
	               while ( (byteread = inStream.read(buffer)) != -1) { 
	                   bytesum += byteread;            //×Ö½ÚÊý ÎÄ¼þ´óÐ¡ 
	                   System.out.println(bytesum); 
	                   fs.write(buffer, 0, byteread); 
	               } 
	               inStream.close(); 
	       }  catch (Exception e) { 
	           System.out.println("¸´ÖÆµ¥¸öÎÄ¼þ²Ù×÷³ö´í"); 
	           e.printStackTrace(); 
	       } 
	   } 
}
