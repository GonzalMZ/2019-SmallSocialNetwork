package phase2;

import phase1.Student;
import phase1.StudentsList;

public class ManageNetworkTree implements IManageNetworkTree {

	
	/**
	 * It takes an object of the StudentsTree class 
	 * and an object of the StudentsList class (phase 1),
	 * and inserts each student from the list into the tree. 
	 * The method does not return anything. 
	 * @param tree
	 * @param list
	 */
	public void copySocialNetwork(StudentsTree tree, StudentsList list) {
		if(list.isEmpty()) {//Si esta vacia la lista no hace nada
			System.out.println("The network is empty!");
		}
		else {//Si no va recorriendo la lista insertando cada elemento en el arbol
			for(int i=0;i<list.getSize();i++) {
				tree.insertStudent(list.getAt(i));
			}
		}
	}
	/**
	 * This takes an object of the StudentsTree class and returns an object of the StudentsList class
	 *  containing all the students in the tree ordered by their email. 
	 *  Hint: You can develop an auxiliary and recursive method  
	 *  which takes a BSTNode object and a StudentsList object. 
	 *  You cannot use any sort algorithm to sort the resulting list. 
	 *  To obtain the list, you must traverse the tree (or subtree) in a recursive way. 
	 * @return
	 */
	public StudentsList getOrderedList(StudentsTree tree){
		
        StudentsList sL = new StudentsList();
        if(tree.root==null) {//Si el arbol esta vacio devuelve la lista vacia
			System.out.println("The tree is empty!");
			return sL; 
		}
        else {//Si no va añadiendo estudiante a estudiante por orden de email
        	int j=tree.getSize();
        	for(int i=0;i<j;i++) {
        		Student nextSt=findNext(tree.root);//Primero busca cual es el siguiente
        		sL.addLast(nextSt);//Lo añade a la lista
        		tree.removeStudent(nextSt.email);//Y lo borra del arbol
        	}
        }
        return sL;
    }
	//Hemos creado otro metodo auxiliar recursivo que busca cual es el siguiente, empezando por la raiz
	public static Student findNext(BSTNode currentNode) {
		if(currentNode.left!=null)//Siempre que haya uin nodo izquierdo, vuelve a hacer el metodo
			return findNext(currentNode.left);
		else {//Si no hay izquierdo significa que ese es el mas pequeño
			return currentNode.oStudent;//Y lo devuelve, para ser borrado mas tarde
		}
	}


	
	/**
	 * This class has a parameter n as input and removes all students having a number of blocks equal or greater than n.
	 * @param num
	 */
	
	public void deleteByNumberOfBlocks(StudentsTree tree,int num) {
		if(tree.root==null) {//Si el arbol esta vacio no hace nada
			System.out.println("The tree is empty!");
		}
		else {
			StudentsList sL = new StudentsList();
			findListByNumberOfBlocks(tree.root,num,sL);//Si no crea una lista y utiliza el metodo recursivo
			for(int i=0;i<sL.getSize();i++) {
				tree.removeStudent(sL.getAt(i).email);
			}
	    }
	}
	//Este metodo va buscando por ramas los elementos que tengan mas o igual bloqueos y los mete en la lista
	public StudentsList findListByNumberOfBlocks(BSTNode currentNode, int n, StudentsList lst) {
		if(currentNode==null)//Cuando llega a una hoja nula, ya devuelve la lista
			return lst;
		else {
			if(currentNode.oStudent.blocks>=n)//Si es mayor o igual lo inserta en la lista
				lst.addLast(currentNode.oStudent);
			lst=findListByNumberOfBlocks( currentNode.left, n, lst);//Despues vuelve a hacer el metodo con la rama izquierda
			return 	findListByNumberOfBlocks(currentNode.right, n, lst);//Y con la derecha, que ya lo devuelve
		}
	}
	

}
