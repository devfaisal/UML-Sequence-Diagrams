

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableColumn;
 
public class gui extends JPanel{
	private JButton jcomp1;
	private JButton jcomp2;
	private JButton jcomp3;
	private JButton jcomp4;
	private JButton jcomp5;
	private JButton jcomp7;
	Object[][] obj;
	private JLabel jcomp6;
	private JTextArea jcomp8;
	private JButton jcomp9;
	private JButton checkformat;
	private JScrollPane jcomp10;
	private JTextArea jcomp11;
	private JTextArea jcomp19_3;
	private JTextArea jcomp19_4;	
	private JScrollPane scrollPane;
	private File file;
	private JTextArea jcomp12;
	static int cout=0;
	static String sg[]=new String[1000];
	String pc[]=new String[1000];
	int count=0;
	String sloop[]=new String[100];
	int cloop=0;
	String gnrits[]=new String[1000];
	int cout11=0;
	private JButton jcomp19;
	static String scen[]=new String[1000];
	static int csc=0;
	String vst[][]=new String[100][3];
	File f ;
	Node startNode;
	ArrayList<Table1> table1array ;
	ArrayList<String> doorarray = new ArrayList<String>();
	//String test11= JOptionPane.showInputDialog("Enter directory containing files");
	//File folder = new File(test11);
	//File[] listOfFiles = folder.listFiles();
	//JComboBox[][] jcb=new JComboBox[1000][listOfFiles.length];
	//private JTextArea jcomp13=new JTextArea();
	//private JTextArea jcomp14=new JTextArea();
	//private JTextArea jcomp15=new JTextArea();   
	public gui(){
	    jcomp2 = new JButton ("Step-I");
	    jcomp3 = new JButton ("Step-II");
	    jcomp4 = new JButton ("Step-III");
	    jcomp5 = new JButton ("Step-IV");
	    jcomp1 = new JButton ("Home");
	    jcomp7 = new JButton ("Browse");
	    jcomp6 = new JLabel ("Select Use-Case Description File");
	    jcomp8 = new JTextArea();
	    jcomp10 = new JScrollPane(jcomp8);
	    jcomp9 = new JButton("Proceed");
	    jcomp11=new JTextArea();
	    jcomp12=new JTextArea();
	    scrollPane = new JScrollPane(jcomp8);
	    jcomp19=new JButton("Proceed to 1.b");
	    checkformat = new JButton("Check Format");
	    setPreferredSize (new Dimension (1000, 700));
	    setLayout (null);

	    add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);

	    jcomp1.setBounds (0, 10, 100, 25);
	    jcomp2.setBounds (125, 10, 100, 25);
	    jcomp3.setBounds (250, 10, 100, 25);
	    jcomp4.setBounds (375, 10, 100, 25);
	    jcomp5.setBounds (500, 10, 100, 25);
	    add (checkformat);
	    //jcomp1.setBackground(new Color(59, 89, 182));
		add (jcomp6);
	    add (jcomp7);
	    add (scrollPane);
	    add (jcomp9);
	    add (jcomp10);
	    add (jcomp11);
	    add (jcomp12);
	    jcomp6.setBounds (0, 50, 250, 25);
	    jcomp7.setBounds(470, 50, 100, 25);
	    checkformat.setBounds(600, 50, 200, 25);
	    LineFile l1 = new LineFile();
	    jcomp7.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	JFileChooser chooser = new JFileChooser();	
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Select Use-Case Description File to process");
	            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            chooser.setAcceptAllFileFilterUsed(false);

	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	            	jcomp11.setText(chooser.getSelectedFile().getName());
	            	jcomp8.setText("");
	                f= chooser.getSelectedFile();
	                l1.dispLines(f, jcomp8);
	            } else {
	                System.out.println("No Selection ");
	            }
	        }
	    });
	    checkformat.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
	    		    	jcomp8.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	    		    	l1.errorCheck(jcomp8);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );	
	    scrollPane.setBounds(0, 100, 800, 300);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jcomp9.setBounds(700, 670, 100, 25);
	    jcomp9.setEnabled(false);
	    jcomp11.setBounds(250, 50, 200, 25);
	    jcomp12.setBounds(0, 450, 800, 200);
	    /*jcomp2.setEnabled(false);
	    jcomp3.setEnabled(false);
	    jcomp4.setEnabled(false);
	    jcomp5.setEnabled(false);*/
	    jcomp19_3=new JTextArea();
	    jcomp19_3.setBounds(550,75,80,20);
	    jcomp19_4=new JTextArea();
		jcomp19_4.setBounds(550,100,80,20);
		JButton btnCompButton = new JButton("Browse");
		btnCompButton.setBounds(650, 400, 100, 25);
		add(btnCompButton);
		JTextPane OkTxt = new JTextPane();
		btnCompButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            JFileChooser chooser = new JFileChooser();
	            chooser.setCurrentDirectory(new java.io.File("."));
	            chooser.setDialogTitle("Select Component Folder to process");
	            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            chooser.setAcceptAllFileFilterUsed(false);
	            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
	            {
	                System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
	                jcomp12.setText("");
	                listFilesForFolder(chooser.getCurrentDirectory(),jcomp12, OkTxt, jcomp9);
	            }
	           else 
	           	{
	                System.out.println("No Selection ");
	            }
	        }
	    });
		jcomp9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
	       {
	        	//tabbedPane.setSelectedComponent(Step1);
	        	//thread.start();
	        	startCode();
	        	obj = new Object[table1array.size()][3];
	        	System.out.println("thisherr\n");
	        	obj = convertTable1Array();
	        	for(int i=0;i<table1array.size();i++){
	        		System.out.println(obj[i][0].toString()+"he;llo");
	        	}
	        	selectionButtonPressed3();
	        	//table_1.set = new JTable(obj, columnNames);
	       }
		});
		
	    jcomp1.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    selectionButtonPressed();
	    		  } 
	    		} );
	    jcomp2.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
	    		    	selectionButtonPressed3();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );
	    jcomp3.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    try {
	    		    	selectionButtonPressed4();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	    		  } 
	    		} );
	}
	public void selectionButtonPressed4(){
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
	    add(jcomp19_3);
	    add(jcomp19_4);
	    jcomp1.setBackground(null);
		//jcomp2.setBackground(new Color(59, 89, 182));
		JTextArea jcomp13=new JTextArea();
		JTextArea jcomp14=new JTextArea();
		JTextArea jcomp15=new JTextArea();
		jcomp15.setEditable(false);
		JScrollPane j1=new JScrollPane(jcomp13);
		JScrollPane j2=new JScrollPane(jcomp14);
		JScrollPane j3=new JScrollPane(jcomp15);
		j1.setBounds(50, 80, 80, 40);
	    j1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j2.setBounds(130, 80, 150, 40);
	    j2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j3.setBounds(280, 80, 400, 40);
	    j3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    JButton generatebutton = new JButton("Generate Action Sequences");
	    JLabel jcomp199=new JLabel("Generate Action Sequences");
		JLabel jcomp19=new JLabel("Time:");
		JLabel jcomp19_1=new JLabel("Build Action Count Graph");
		JLabel jcomp19_2=new JLabel("Build Action Sequences");
		//JTextArea jcomp19_3=new JTextArea();
		//JTextArea jcomp19_4=new JTextArea();
		jcomp19_4.setBounds(550,100,80,20);	
		JLabel loops = new JLabel("No. of Loops");
		loops.setBounds(450,200,100,20);
		Object[] columnNames = {"S No.",
		        "Loop Action",
		        "Loop Line No.",
		        "No."};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5)},
			    {"John", "Doe",
			     "Rowing", new Integer(3)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10)}
			};
		JTable table = new JTable(data,columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10,250,900,300);
		add(scrollPane);
		JLabel oklabel = new JLabel("OK");
		oklabel.setBounds(10,600,50,20);
		oklabel.setForeground(Color.red);
		generatebutton.setBounds(350,150,270,20);
		jcomp199.setBounds(400,40,800,15);
		jcomp19.setBounds(300,75,80,30);
		jcomp19_1.setBounds(350,75,200,20);
		jcomp19_2.setBounds(350,100,200,20);
		//jcomp19_3.setBounds(550,75,80,20);
		//jcomp19_4.setBounds(550,100,80,20);	
		JButton proceed2 = new JButton("Proceed");
		proceed2.setBounds(700, 650, 100, 25);
		proceed2.setEnabled(false);
		add(loops);
		add(proceed2);
		add(oklabel);
		add(generatebutton);
		add(jcomp199);
		add(jcomp19);
		add(jcomp19_1);
		add(jcomp19_2);
		
		//add(jcomp19_4);
		JTextArea jcomp16=new JTextArea();
		JTextArea jcomp17=new JTextArea();
		JTextArea jcomp18=new JTextArea();
		
		JScrollPane j4=new JScrollPane(jcomp16);
		JScrollPane j5=new JScrollPane(jcomp17);
		JScrollPane j6=new JScrollPane(jcomp18);
		//j4.setBounds(50, 80+(linecount*40), 80, 40);
	    j4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //j5.setBounds(130, 80+(linecount*40), 150, 40);
	    j5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //j6.setBounds(280, 80+(linecount*40), 400, 40);
	    j6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	    add(j4);
	    add(j6);
	    add(j5);
	}
	
                
	public void selectionButtonPressed3(){
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
	    
	    jcomp1.setBackground(null);
		//jcomp2.setBackground(new Color(59, 89, 182));
		JTextArea jcomp13=new JTextArea();
		JTextArea jcomp14=new JTextArea();
		JTextArea jcomp15=new JTextArea();
		jcomp15.setEditable(false);
		JScrollPane j1=new JScrollPane(jcomp13);
		JScrollPane j2=new JScrollPane(jcomp14);
		JScrollPane j3=new JScrollPane(jcomp15);
		j1.setBounds(50, 80, 80, 40);
	    j1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j2.setBounds(130, 80, 150, 40);
	    j2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j3.setBounds(280, 80, 400, 40);
	    j3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    Object[] columnNames = {"Action No",
                "Line No",
                "Action Name",
                "Door"
             };
	    Object[][] data = obj;
	    JTable table = new JTable(data,columnNames);
	    TableColumn sportColumn = table.getColumnModel().getColumn(3);
	    JComboBox comboBox = new JComboBox();
	    System.out.println(doorarray.get(0));
	    for (int i=0;i<doorarray.size();i++){
	    	comboBox.addItem(doorarray.get(i));
	    	//comboBox.setSelectedIndex(0);
	    }
	    System.out.println(comboBox.getSelectedIndex()+comboBox.getSelectedItem().toString());
	    //comboBox.setSelectedIndex(0);
	    comboBox.setSelectedIndex(2);
	    sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    //comboBox.setSelectedIndex(0);
	    JScrollPane scrollPane = new JScrollPane(table);
	    //comboBox.setSelectedIndex(0);
	    scrollPane.setBounds(10,100,900,400);
	    add(scrollPane);
	    //comboBox.setSelectedIndex(0);
	    //add(table, BorderLayout.CENTER);
	    JLabel timerlabel = new JLabel("Timer");
	    JTextArea timertext = new JTextArea();
	    JLabel timerlabel2 = new JLabel("Time taken");
	    JTextArea timertext2 = new JTextArea();
	    JButton oklabel = new JButton("OK");
	    //oklabel.setBackground(Color.RED);
	    oklabel.setForeground(Color.RED);
	    JLabel jcomp199=new JLabel("Master Action Sequence Table");
		timerlabel.setBounds(800, 20, 40, 30);
		timerlabel2.setBounds(10, 600, 80, 30);
		oklabel.setBounds(10,550,70,20);
		jcomp199.setBounds(350,80,800,15);
		timertext.setBounds(840,20,50,30);
		timertext2.setBounds(100,600,50,30);
		JButton proceed2 = new JButton("Proceed");
		timertext.setEnabled(false);
		proceed2.setBounds(700, 650, 100, 25);
		proceed2.setEnabled(false);
		add(proceed2);
		add(timerlabel);
		add(timertext);
		add(timerlabel2);
		add(timertext2);
		add(oklabel);
		add(jcomp199);
		
		JTextArea jcomp16=new JTextArea();
		JTextArea jcomp17=new JTextArea();
		JTextArea jcomp18=new JTextArea();
		
		JScrollPane j4=new JScrollPane(jcomp16);
		JScrollPane j5=new JScrollPane(jcomp17);
		JScrollPane j6=new JScrollPane(jcomp18);
		//j4.setBounds(50, 80+(linecount*40), 80, 40);
	    j4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //j5.setBounds(130, 80+(linecount*40), 150, 40);
	    j5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    //j6.setBounds(280, 80+(linecount*40), 400, 40);
	    j6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    /*j7.setBounds(680, 80+((linecount-1)*40), 80, 40);
	    j7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    j8.setBounds(760, 80+((linecount-1)*40), 80, 40);
	    j8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    j8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    */add(j4);
	    add(j6);
	    add(j5);
	    Thread thread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            long start = System.currentTimeMillis();
	            while(true){
	                    long now=System.currentTimeMillis()-start;
	                    timertext.setText(Long.toString(now));
	                    //repaint();
	                }  
	      
	            }
	     
		});
	    thread.start();
	    oklabel.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    thread.stop();
	    		    oklabel.setForeground(Color.green);
	    		    proceed2.setEnabled(true);
	    		  } 
	    		} );
	    //add(j7);
	    //add(j8);
	    //jcomp16.append((aname[count-1]));
		//jcomp17.append((line2.replace("null", "")));
		//jcomp18.append((((pc[count-1].replace("null", ""))).replace(",,",",")).replace("-,",""));
		//jcomp24.append(vst[cloud++-1][2]);
		//linecount++;
		//line2=in1.readLine();
	
	//add(jcomp19);
	//jcomp19.setBounds(1050,650,150 , 25);
	/*jcomp19.addActionListener(new ActionListener() { 
    	  public void actionPerformed(ActionEvent e) { 
    		    try {
					selectionButtonPressed4();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
    		  } 
    		} );
}catch(Exception e){}*/
}
	public void selectionButtonPressed(){
		removeAll();
		revalidate();
		repaint();
		add (jcomp1);
	    add (jcomp2);
	    add (jcomp3);
	    add (jcomp4);
	    add (jcomp5);
		//jcomp1.setBackground(new Color(59, 89, 182));
		add (jcomp6);
	    add (jcomp7);
	    add (scrollPane);
	    add (jcomp9);
	    add (jcomp10);
	    add (jcomp11);
	    add (jcomp12);
	    jcomp6.setBounds (0, 50, 250, 25);
	    jcomp7.setBounds(470, 50, 100, 25);
	    scrollPane.setBounds(0, 100, 800, 300);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jcomp9.setBounds(700, 670, 100, 25);
	    jcomp11.setBounds(250, 50, 200, 25);
	    jcomp12.setBounds(0, 450, 800, 200);	    
	    
	}
	   
	    
	    public static void main (String[] args){
	    	JFrame frame = new JFrame("SOFTWARE FAULT TREE ANALYSIS OF USE CASES");
	    	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    	gui b=new gui();
	    	JScrollPane scrollPane = new JScrollPane(b);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        frame.add(scrollPane);
	        frame.pack();
	        frame.setVisible (true);
	    	}
	    public void listFilesForFolder(File folder, JTextArea textArea_2, JTextPane okTxt, JButton btnProceed) 
		{
		    for (final File fileEntry : folder.listFiles()) 
		    {
		        if (fileEntry.isDirectory()) 
		        {
		        	listFilesForFolder(fileEntry,textArea_2,okTxt, btnProceed);
		        } 
		        else 
		        {
		        	textArea_2.append(fileEntry.getName()+" : ");
		        	dispFileContents(fileEntry.getAbsoluteFile(), textArea_2);
		        }
		    }
		    okTxt.setText("OK");
		    okTxt.setForeground(Color.green);
		    btnProceed.setEnabled(true);
		}
	    public void dispFileContents(File file, JTextArea textArea)
		{
			 try 
			 {
				 BufferedReader br = new BufferedReader(new FileReader (file));
				 String         line = null;
				 try {
					while ((line = br.readLine()) != null)
					    {
					        textArea.append(","+line);
					        doorarray.add(line);
					    }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			          
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 textArea.append("\n");
			 
		}
	    public void startCode()
		{
			//Scanner sc = new Scanner(System.in);
	        ArrayList<Integer> whileSet = new ArrayList<Integer>();
	        //ArrayList<Integer> lpkount = new ArrayList<Integer>();
	        LineFile l1 = new LineFile();
	        int i;
	        l1.makeLines(f);
	        Flow flow = new Flow();
	        startNode = createUseCaseGraph(l1.getLines(), whileSet);
	        System.out.printf(table1array.get(0).getActioname());
	     }
	    public Object[][] convertTable1Array(){
			int len = table1array.size();
			Object[][] obj = new Object[len][4];
			int i=0;
			for (i=0;i<len;i++){
				System.out.printf(table1array.get(i).getActiono());
				obj[i][0]= table1array.get(i).getActiono();
				obj[i][1]= table1array.get(i).getLineno();
				obj[i][2]= table1array.get(i).getActioname();
				obj[i][3]= null;
			}
			return obj;
		}
	    public Node createUseCaseGraph(ArrayList<Line> linf, ArrayList<Integer> whileSet) {
	        Node startNode = new Node(0,"Start",0);
	        Node endNode = new Node(1000,"Start",20);
	        endNode.setTrue_part(null);
	        Node currentNode = startNode;
	        Iterator<Line> li = linf.iterator();
	        Stack<Node> ifstack = new Stack<Node>();
	        Stack<Node> whilestack = new Stack<Node>();
	        Stack<Node> lastvisitstack = new Stack<Node>();
	        Table1 table1;
	        table1array = new ArrayList<Table1>();
	        while (li.hasNext()){
	            Line ln = li.next();
	            if (ln.getLine_descrip().startsWith("IF")) {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),2);
	                ifstack.push(n);
	                currentNode.setTrue_part(n);
	                currentNode = n;
	                table1= new Table1(ln.getLineno(),"IF",1);
	                table1array.add(table1);
	                
	            }
	            else if (ln.getLine_descrip().startsWith("ELSE")) {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),3);
	                if (!ifstack.isEmpty()){
	                    ifstack.pop().setElse_part(n);
	                    table1= new Table1(ln.getLineno(),"ELSE",0);
	                    table1array.add(table1);
	                }
	                
	                else {
	                    System.out.print("Error1");
	                }
	                lastvisitstack.push(currentNode);
	                currentNode = n;
	            }
	            else if (ln.getLine_descrip().startsWith("ENDIF")) {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),4);
	                currentNode.setTrue_part(n);
	                if (!lastvisitstack.isEmpty()){
	                lastvisitstack.pop().setTrue_part(n); 
	                table1= new Table1(ln.getLineno(),"ENDIF",0);
	                table1array.add(table1);
	                }
	                else {
	                    System.out.print("Error2");
	                }
	                currentNode = n;
	            }
	           
	            else if (ln.getLine_descrip().startsWith("WHILE")) {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),5);
	                whilestack.push(n);
	                currentNode.setTrue_part(n);
	                currentNode = n;
	                whileSet.add(ln.getLineno()+1);
	                System.out.printf("here while \n");
	                table1= new Table1(ln.getLineno(),"WHILE",1);
	                table1array.add(table1);
	            }
	            else if (ln.getLine_descrip().startsWith("ENDWHILE")) {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),6);
	                if (!whilestack.isEmpty()){
	                    Node w =whilestack.pop();
	                    w.setElse_part(n);
	                    currentNode.setTrue_part(w);
	                    currentNode = n;
	                    table1= new Table1(ln.getLineno(),"ENDWHILE",0);
	                    table1array.add(table1);
	                }
	                else {
	                    System.out.print("Error4");
	                }
	              
	            }
	            else if (ln.getLine_descrip().startsWith("END")) {
	                if (whilestack.isEmpty() && ifstack.isEmpty()){
	                    Node n = new Node(ln.getLineno(),ln.getLine_descrip(),7);
	                    currentNode.setTrue_part(n);
	                    currentNode = n;
	                    n.setTrue_part(endNode);
	                    table1= new Table1(ln.getLineno(),"END",1);
	                    table1array.add(table1);
	                }
	                else {
	                    System.out.print("Error5");
	                }
	            }
	            else if (ln.getLine_descrip().startsWith("EXIT")) {
	                if (currentNode.getType()==5){
	                    currentNode.setElse_part(endNode);
	                }
	                else if (currentNode.getType()==3){
	                    currentNode.setElse_part(endNode);
	                }
	                else {
	                    currentNode.setTrue_part(endNode);
	                }
	                table1= new Table1(ln.getLineno(),"EXIT",1);
	                table1array.add(table1);
	            }
	            else {
	                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),1);
	                currentNode.setTrue_part(n);
	                currentNode = n;
	                table1= new Table1(ln.getLineno(),ln.getLine_descrip(),1);
	                //System.out.println(table1.getActioname());
	                table1array.add(table1);
	                System.out.println(table1array.get(0).getActioname()+"here");
	            }
	        }
	        /*Iterator<String> itr = whileSet.iterator();
	        while(itr.hasNext())
	        {
	            System.out.println(itr.next());
	        }**/
	        System.out.println("1THIS-> "+startNode);
	        return startNode;
	    }
	}