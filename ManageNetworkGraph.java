package phase3;

import java.util.LinkedList;



public class ManageNetworkGraph implements IManageNetworkGraph {
	
	
	
	public LinkedList<String> students;
	LinkedList<LinkedList<Integer>> lst_of_lstAdjacents;//Utilizamos una lista de listas
		
	public ManageNetworkGraph(String[] students) {//El constructor está hecho ya
			this.students=new LinkedList<String>();
			for(int k=0; k < students.length; k++) {
				this.students.add(students[k]);
			}
			
			//we must initialize each Integer list
			//each index i corresponds to a student, the function
			//getIndex is used to obtain the correspondence
			lst_of_lstAdjacents=new LinkedList<LinkedList<Integer>>();
			int num=this.students.size();
			for (int i=0; i<num; i++) {
				lst_of_lstAdjacents.addLast(new LinkedList<Integer>());
			}
	}
	
	//searches the student and returns its index
	public int getIndex(String student) {//Para ayudarnos con los demas metodos hemos creado este
		int index=0;
		while(index<students.size()&&!this.students.get(index).equals(student)) {
			index++;
		}
		if(index==students.size()) {
			System.out.println("It's not in the network");
			return -1;
		}
		return index;
	}
	
	
	//checks if index is right and returns its associated city
	public String checkVertex(int index) {//Este tambien
		if(index<0||index>=students.size()) {
			System.out.println("It's not a correct index");
			return null;
		}
		return students.get(index);
	}
	
	/**
	 * It takes taking two students (emails) as input and 
	 * creates a friendship relation between them. 
	 * Keep in mind that friendship relation is a symmetric relationship.
	 */
	public void addStudent(String student) {
		if(student==null)//Si es null termina
			return;
		if(getIndex(student)!=-1)//Si ya esta tambien termina
			throw new IllegalArgumentException("It's already in the network");
		students.addLast(student);//Mete el email
		lst_of_lstAdjacents.addLast(new LinkedList<Integer>());//Y crea una lista mas
	}
	
	/**
	 * It takes two students (emails) as input and creates a friendship 
	 * relation between them. Keep in mind that friendship relation is a symmetric relationship.
	 * @param studentA
	 * @param studentB
	 */
	public void areFriends(String studentA, String studentB) {
		if(studentA==null||studentB==null)//Si alguno es null termina
			return;
		if(getIndex(studentA)==-1)//Si no estan se les añade primero
			addStudent(studentA);
		if(getIndex(studentB)==-1)
			addStudent(studentB);
		int a=getIndex(studentA);
		int b=getIndex(studentB);
		if(lst_of_lstAdjacents.get(a).contains(b))//Si ya son amigos termina
			return;
		lst_of_lstAdjacents.get(a).add(b);//Como es simetrico, deben ser amigos a de b
		lst_of_lstAdjacents.get(b).add(a);//Y b de a
	}

	/**
	 * This takes a student (email), and returns an object of LinkedList<String>, 
	 * which contains the emails of his/her direct friends.
	 * @param studentA
	 * @return
	 */
	public LinkedList<String> getDirectFriends(String studentA){
		LinkedList<String> lDirectFriends = new LinkedList<String>();
		if(getIndex(studentA)==-1)//Si no esta devuelve la lista vacia
			return lDirectFriends; 
		int i=getIndex(studentA);
		for(int j=0;j<lst_of_lstAdjacents.get(i).size();j++)
			lDirectFriends.addLast(checkVertex(lst_of_lstAdjacents.get(i).get(j)));//va añadiendo a la lista los emails de los amigos
		return lDirectFriends;
	}
	
	
	
	public int[] getAdjacents(int i) {//Este tambien es un metodo de apoyo
		if(checkVertex(i)==null)
			return null;
		int[]res=new int[this.lst_of_lstAdjacents.get(i).size()];
		for(int j=0;j<res.length;j++)
			res[j]=this.lst_of_lstAdjacents.get(i).get(j);
		return res;
	}
	
	
	
	public LinkedList<String> suggestedFriends(String studentA){
		LinkedList<String> lSuggestedFriends = new LinkedList<String>();
		if(studentA==null||getIndex(studentA)==-1)//Si es null o no esta devuelve la lista vacia
			return lSuggestedFriends;
		boolean[]visited=new boolean[students.size()];//Para ir guardando cuales hemos visitado
		LinkedList<Integer> adj=depth(getIndex(studentA),visited);
		for(int j=0;j<adj.size();j++)
			lSuggestedFriends.add(checkVertex(adj.get(j)));
		return lSuggestedFriends;
	}
	
	public LinkedList<Integer> depth(int i, boolean[] visited) {
		LinkedList<Integer> path=new LinkedList<Integer>();
		visited[i]=true;//El inicial
		int[]fri=getAdjacents(i);
		for(int j=0;j<fri.length;j++) {
			visited[fri[j]]=true;//Y todos sus amigos directos se ponen como true para que no se añadan a la lista
		}
		for(int l=0;l<fri.length;l++) {//Luego para cada amigo directo añade los amigos potenciales que se van sumando todos a la lista
			path=depth(fri[l],visited,path);
		}
		return path;
	}
	

	protected LinkedList<Integer> depth(int i,boolean[] visited, LinkedList<Integer> path) {
		if(!visited[i]==true)//Si no esta en true el vertice lo añade a la lista
			path.add(i);
		visited[i]=true;
		int[]adj=getAdjacents(i);//Genera la lista de adyacentes al vertice en el que esta
		for(int adjV:adj) {
			if(!visited[adjV])//Y si no ha sido visitado  se llama a si mismo para recorrerlo
				return depth(adjV,visited,path);
		}
		return path;
	}
	
	public void show() {
		//to complate
	}
	
	public static void main(String args[]) {
		
	}
	
}
