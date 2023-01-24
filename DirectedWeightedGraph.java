package dijkstra_algo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")

public class DirectedWeightedGraph extends JFrame implements ActionListener{
		Demo a = new Demo();
		//Input Field
		JLabel lblInput = new JLabel("Enter Source Vertex");
		JTextField txtInput = new JTextField(15);
		
		//Output Field Label
		JLabel lblSrcVertex = new JLabel("Source Vertex");
						
		//Output box
		JTextField txtSrcVertex = new JTextField(15);
				
		//buttons
		JButton btnEnter = new JButton("Enter");
		JButton btnNext = new JButton("Next City");
		JButton btnReset = new JButton("Reset");
		
   public final int MAX_VERTICES = 30;	
   
   String source;
   int nextCityCounter = 0;
   int n;           
   int e;           
   int [][] adj; 
   Vertex [] vertexList;
  
   private static final int TEMPORARY = 1;
   private static final int PERMANENT = 2;
   private static final int NIL = -1;
   private static final int INFINITY = 99999; 
   
   public DirectedWeightedGraph(){
        adj = new int [MAX_VERTICES][MAX_VERTICES];
        vertexList = new Vertex[MAX_VERTICES];
        setLayout(new FlowLayout());
		Container cont = getContentPane();
		
		//getting the content of text boxes
		cont.add(lblInput);cont.add(txtInput);
		cont.add(lblSrcVertex);cont.add(txtSrcVertex);
		
		//setting output boxes to non-editable
		txtInput.setEditable(true);
		txtSrcVertex.setEditable(false);
		
		//setting buttons to be clickable
		cont.add(btnEnter);btnEnter.addActionListener(this);
		cont.add(btnNext);btnNext.addActionListener(this);
		cont.add(btnReset);btnReset.addActionListener(this);
		
		//size and visibility of the frame
		setVisible(true);
		setSize(300,150);	
   }
   
   public void progStart() {
	   //just to go from main to here
   }
   
   public void actionPerformed(ActionEvent x) {
	   source = (txtInput.getText());
	   int s = getIndex(source);
	   dijkstra(s);//call the dijstra algo starting from source vertex
	   
	   if(x.getSource() == btnEnter) {
		   //System.out.println("Source Vertex : " + source + "\n");
		   txtSrcVertex.setText(source);//display Source Vertex when Enter button is pressed
		   btnEnter.setEnabled(false);//make the button unclickable
	   }
	   else if(x.getSource()==btnNext){
		   if(nextCityCounter <= 8) {
			   System.out.println("Destination Vertex : " + vertexList[nextCityCounter]);
			   if( vertexList[nextCityCounter].pathLength == INFINITY )
		            System.out.println("There is no path from " + source + " to vertex " + vertexList[nextCityCounter] + "\n");
			   else
					findPath(s,nextCityCounter);
			   nextCityCounter++;
		   }
		   else
			   System.out.println("End of Cities");
	   }
	   else if(x.getSource()==btnReset){
		   	txtInput.setText("");
		   	txtSrcVertex.setText("");
			btnEnter.setEnabled(true);
			System.out.println("------------------------------------------------------------------------");
			nextCityCounter = 0;
	   }
   }
   
   private void dijkstra(int s){
   	   int v,c;
   	
   	   for(v=0; v<n; v++){ 
   		  vertexList[v].status = TEMPORARY;
   		  vertexList[v].pathLength = INFINITY;
   		  vertexList[v].predecessor =  NIL;
   	   }
   	
   	   vertexList[s].pathLength = 0;
   	   
   	   while(true){
   		 c=tempVertexMinPL();
   	
   		 if(c==NIL)
   			return;
   		
   		 vertexList[c].status = PERMANENT;

   		 for(v=0; v<n; v++) {
   			if(isAdjacent(c,v) && vertexList[v].status == TEMPORARY)
   				if( vertexList[c].pathLength + adj[c][v] < vertexList[v].pathLength ){	
   					vertexList[v].predecessor = c;  
   					vertexList[v].pathLength = vertexList[c].pathLength + adj[c][v];    
   				}
   		  }
   	   }
   }
   
   private int tempVertexMinPL( ){
	   int min=INFINITY;
	   int x=NIL;  
	   for(int v=0; v<n; v++){
		   if(vertexList[v].status == TEMPORARY && vertexList[v].pathLength < min){
			   min=vertexList[v].pathLength;
			   x=v;
		   }
	   }
	   return x;
   }
  
   private void findPath(int s, int v){
   	    int i,u;
   	    int [] path = new int[n]; 
   	    int sd=0;	     
   	    int count=0;	

   	   	
   	    while(v!=s){
   		   count++;
   		   path[count] = v;
   	       u = vertexList[v].predecessor;
   		   sd += adj[u][v];
   		   v=u;	
   	     }
   	     count++;
   	     path[count]=s;

   	    System.out.print("Shortest Path is : ");
   	    for(i=count; i>=1; i--)	
   	    	System.out.print(path[i] + " ");
 
   	    	System.out.println("\n Shortest distance is : " + sd + "\n");
   	}

 
   private int getIndex(String s){
	   for(int i=0; i<n; i++)
	      if(s.equals(vertexList[i].name))
	   		   return i;
	   throw new RuntimeException("Invalid Vertex");
   }
   
   public void insertVertex(String name){  
	   vertexList[n++] = new Vertex(name);  
   }
     
  
   private boolean isAdjacent(int u, int v){
	   return (adj[u][v]!=0);
   }
 
   public void insertEdge(String s1, String s2, int wt) {
	   int u = getIndex(s1);
	   int v = getIndex(s2);
	   if(u==v)
	      throw new IllegalArgumentException("Not a valid edge");
      
       if(adj[u][v] !=0 )
    	  System.out.print("Edge already present");
       else  {
         adj[u][v]=wt;
         e++;
       }
   }   
}      
