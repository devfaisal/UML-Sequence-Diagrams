import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Line
{
	private int lineno;
	private String line_descrip;
	
	Line()
	{
		setLineno(0);
		setLine_descrip("");
	}

	public String getLine_descrip() {
		return line_descrip;
	}

	public void setLine_descrip(String line_descrip) {
		this.line_descrip = line_descrip;
	}

	public int getLineno() {
		return lineno;
	}

	public void setLineno(int lineno) {
		this.lineno = lineno;
	}
}
	
class Node
{
	private int node_no;
	private String node_descrip;
	private int type;
	private Node true_part;
	private Node else_part;
	
	Node(int node_no, String node_descrip,int type)
	{
		this.setNode_no(node_no+1);
		this.setNode_descrip(node_descrip);
		this.setType(type);
		this.true_part=null;
		this.else_part=null;
	}

	public int getNode_no() {
		return node_no;
	}

	public void setNode_no(int node_no) {
		this.node_no = node_no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNode_descrip() {
		return node_descrip;
	}

	public void setNode_descrip(String node_descrip) {
		this.node_descrip = node_descrip;
	}

	public Node getTrue_part() {
		return true_part;
	}

	public void setTrue_part(Node true_part) {
		this.true_part = true_part;
	}

	public Node getElse_part() {
		return else_part;
	}

	public void setElse_part(Node else_part) {
		this.else_part = else_part;
	}
	
}

class LineFile
{
	private ArrayList<Line> lines = new ArrayList<Line>();
	
	public void makeLines() 
	{
		int count = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("file3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			return;
		}
		try 
		{
			String sentn, s1;
			while ((sentn = br.readLine()) != null) 
			{
				s1 = sentn.toUpperCase();
				Line x = new Line();
				x.setLineno(count);
				x.setLine_descrip(s1);
				lines.add(x);
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}

	
	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
}
	

public class Flow
{
	public static void main(String args[])
	{
		LineFile l1 = new LineFile();
		l1.makeLines();
		Flow f = new Flow();
		f.createUseCaseGraph(l1.getLines());
	}
	public void createUseCaseGraph(ArrayList<Line> linf) {
        Node startNode = new Node(0,"Start",0);
        Node endNode = new Node(1000,"Start",20);
        endNode.setTrue_part(null);
        Node currentNode = startNode;
        Iterator<Line> li = linf.iterator();
        Stack<Node> ifstack = new Stack<Node>();
        Stack<Node> whilestack = new Stack<Node>();
        Stack<Node> lastvisitstack = new Stack<Node>();
        while (li.hasNext()){
            Line ln = li.next();
            if (ln.getLine_descrip().startsWith("IF")) {
                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),2);
                ifstack.push(n);
                currentNode.setTrue_part(n);
                currentNode = n;
            }
            else if (ln.getLine_descrip().startsWith("ELSE")) {
                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),3);
                if (!ifstack.isEmpty()){
                    ifstack.pop().setElse_part(n);
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
            }
            else if (ln.getLine_descrip().startsWith("ENDWHILE")) {
                Node n = new Node(ln.getLineno(),ln.getLine_descrip(),6);
                if (!whilestack.isEmpty()){
                    Node w =whilestack.pop();
                    w.setElse_part(n);
                    currentNode.setTrue_part(w);
                    currentNode = n;
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
                    endNode =n ;
                }
                else {
                    System.out.print("Error5");
                }
            }
            else {
            	Node n = new Node(ln.getLineno(),ln.getLine_descrip(),1);
                currentNode.setTrue_part(n);
                currentNode = n;
            }
        }
        ArrayList<String> str = generateScenarios(startNode);
        print(str);
    }
	public void print(ArrayList<String> str)
	{
		
		/*Node cnode = start;
		while (cnode!=null){
			 System.out.print(cnode.getNode_descrip()+"-->");
			 cnode=cnode.getTrue_part();
		}**/
		int i = 1;
		Iterator<String> itr = str.iterator();
		while(itr.hasNext())
		{
            System.out.println("Scenario no. "+i+": "+itr.next());
            i++;
		}
	}

	
	public ArrayList<String> generateScenarios(Node startNode)
	{
		String scenario = "";
		Node cNode = startNode.getTrue_part();
		Stack<Node> newStack = new Stack<Node>();
		Stack<String> scenarioStack = new Stack<String>();
		ArrayList<String> scenarioList = new ArrayList<String>();
		
		while(true)
		{
			switch(cNode.getType())
			{
				case 1: scenario = scenario+ cNode.getNode_no()+",";
						cNode = cNode.getTrue_part();
						break;
				case 2: scenario = scenario+ cNode.getNode_no()+"(T),";
						scenarioStack.push(scenario + cNode.getNode_no() +"(F),");
						newStack.push(cNode);
						cNode = cNode.getTrue_part();
						break;
				case 5:	
						if(newStack.search(cNode) == -1)			//Node not there
						{
							scenario = scenario+ cNode.getNode_no()+"(T),";
							scenarioStack.push(scenario + cNode.getNode_no() +"(F),");
							newStack.push(cNode);
							cNode = cNode.getTrue_part();
						}
						else
						{
							scenario = scenario+ cNode.getNode_no()+"(F),";
							cNode = cNode.getElse_part();
						}
						break;
				case 3:
				case 4:
				case 6:	cNode = cNode.getTrue_part();
						break;
				case 7: scenarioList.add(scenario);
						if(scenarioStack.isEmpty())
						return scenarioList;
						else
						{
							scenarioStack.pop();
							Node temp = newStack.pop();
							cNode = temp.getElse_part();
						}
			}
		}
	}
}