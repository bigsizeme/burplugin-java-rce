package burp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import ui.MainPanel;

public class BurpExtender implements IBurpExtender, ITab, IHttpListener,IScopeChangeListener
{
  private IExtensionHelpers helpers;
  private JPanel mainPanel;

  public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
  {
    this.helpers = callbacks.getHelpers();
//    callbacks.registerHttpListener(arg0);
//    callbacks.registerContextMenuFactory(new SnifferContextMenuFactory(this.helpers));

    callbacks.setExtensionName("java-rce");
    callbacks.registerHttpListener(this);
//    callbacks.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        BurpExtender.this.mainPanel = new MainPanel();
        callbacks.customizeUiComponent(BurpExtender.this.mainPanel);
        callbacks.addSuiteTab(BurpExtender.this);
      }
    });
  }

  public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
  {
  }

  public String getTabCaption()
  {
    return "java-rce";
  }

  public Component getUiComponent()
  {
    return this.mainPanel;
  }
  
 

@Override
public void scopeChanged() {
	// TODO Auto-generated method stub
	
}
  
}