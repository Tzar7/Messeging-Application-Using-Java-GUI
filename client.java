package coderspacket.messenger;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*; 

/**
 *
 * @author Riyanshu
 */
public class client  implements ActionListener {
    static DataOutputStream dout; 
    JTextField text;
    static JPanel a1;
    static Box Vertical=Box.createVerticalBox();
    static JFrame f=new JFrame();
    
    client(){
        f.setLayout(null);
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(24, 154, 180));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1=new  ImageIcon(ClassLoader.getSystemResource("icons/left.png"));
        Image i2= i1.getImage().getScaledInstance(25,35,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel left=new JLabel(i3);
        left.setBounds(5,20,25,25);
        p1.add(left);
        
        
        left.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
            
        });
        
        ImageIcon i4=new  ImageIcon(ClassLoader.getSystemResource("icons/profile.png"));
        Image i5= i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel man=new JLabel(i6);
        man.setBounds(40,10,50,50);
        p1.add(man);
        
        ImageIcon i7=new  ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8= i7.getImage().getScaledInstance(25,35,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(290,20,60,30);
        p1.add(video);
        
        
        ImageIcon i10=new  ImageIcon(ClassLoader.getSystemResource("icons/menu.png"));
        Image i11= i10.getImage().getScaledInstance(25,35,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel menu=new JLabel(i12);
        menu.setBounds(380,20,50,35);
        p1.add(menu);
        
        ImageIcon i13=new  ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i14= i13.getImage().getScaledInstance(25,35,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel phone=new JLabel(i15);
        phone.setBounds(340,20,50,35);
        p1.add(phone);
        
        JLabel name=new JLabel("Riyanshu");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF",Font.BOLD,16));
        p1.add(name);
        
        JLabel status=new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.GREEN);
        status.setFont(new Font("SAN SERIF",Font.BOLD,14));
        p1.add(status);
        
        a1=new JPanel();
        a1.setBackground(new Color(212, 241, 244));
        a1.setBounds(5,73,440,570);
        f.add(a1);
        
        text=new JTextField();
        text.setBounds(5,650,310,40);
        text.setFont(new Font("SAN SERIF",Font.BOLD,15));
        f.add(text);
        
        JButton send=new JButton("Send ");
        send.setBackground(new Color(24, 154, 180));
        send.setFont(new Font("SAN SERIF",Font.BOLD,15));
        send.setBounds(320,650,123,40);
        send.addActionListener(this);
        send.setForeground(Color.WHITE);
        f.add(send);
        
        
        
        f.setSize(450,700);
        f.setLocation(800,70);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.lightGray);
        
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String out=text.getText();
        a1.setLayout(new BorderLayout());
        
        JPanel p2=formatLabel(out);
        
        
        JPanel right =new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        Vertical.add(right);
        Vertical.add(Box.createVerticalStrut(15));
        
        a1.add(Vertical,BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output=new JLabel("<html><p style=\"width:150px\" >"+ out +"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(24, 154, 180));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        
        panel.add(output);
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:MM");
        
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    public static void main(String[] args){
        new client();
        
        try{
            Socket s=new Socket("127.0.0.1",6001);
            DataInputStream din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
             while(true){
                 a1.setLayout(new BorderLayout());
                String msg=din.readUTF();
                JPanel panel=formatLabel(msg);
                    
                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                Vertical.add(left);
                Vertical.add(Box.createVerticalStrut(15));
                a1.add(Vertical,BorderLayout.PAGE_START);
                
                f.validate();
                    
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
