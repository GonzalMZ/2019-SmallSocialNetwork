package phase1;

import java.time.LocalDate;


public class ManageNetworkList implements IManageNetworkList{

	
	/**
	 *  The methods must join two social networks into a single social network. 
	 *  The method takes two objects of the StudentsList and returns a new list containing 
	 *  first the students from the first list followed by the students from the second list. 
	 * @param lst1
	 * @param lst2
	 * @return
	 */
	public StudentsList union(StudentsList lst1, StudentsList lst2) {
		if(lst1.isEmpty()&&lst2.isEmpty()) {//Si las dos listas estan vacias devuelve null
			System.out.println("Both networks are empty!");
			return null;
		}
		if(lst1.isEmpty()|| lst1.equals(lst2))//Si la primera esta vacia o las dos son iguales 
			return lst2;//devuelve la segunda
		if(lst2.isEmpty())//Si la segunda esta vacia
			return lst1;//devuelve la primera
		else {
			StudentsList resultList=new StudentsList();
			for(int i=0;i<lst1.size;i++) {//Si no va añadiendo cada elemento primero de la primera
				resultList.addLast(lst1.getAt(i));
			}
			for(int i=0;i<lst2.size;i++) {//Y luego de la segunda
				resultList.addLast(lst2.getAt(i));
			}
			return resultList;
		}
		
	}
	
	
	
	/**
	 * 3. This methods takes a social network as input and an integer parameter opc so that:
		- If opc =1: the method must return a StudentsList containing all the students residing
			in the same city that the campus where they are studying.
		- If opc =2: the method must return a StudentsList containing all the students residing
			in cities different that the one where their campus is located.
	 * @param lst1
	 * @param lst2
	 * @param opc
	 * @return
	 */
	public StudentsList getCampusCity(StudentsList lst, int opc) {
		if(lst.isEmpty()) {//Si esta vacia devuelve la lista vacia
			System.out.println("The network is empty!");
			return lst;
		}
		else {
			if(opc!=1&&opc!=2) {//Si no ha elegido la opcion correcta devuelve null
				System.out.println("There are only two opctions, 1 or 2, choose a correct option");
				return null;
			}
			StudentsList l=new StudentsList();
			if(opc==1) {//En la primera si el campus es el mismo que la ciudad lo añade
				for(int i=0;i<lst.size;i++) {
					if(lst.getAt(i).campus.name().equals(lst.getAt(i).city))
						l.addLast(lst.getAt(i));
				}
			}
			else {//En la segunda si el campus es distinto que la ciudad lo añade
				for(int i=0;i<lst.size;i++) {
					if(!lst.getAt(i).campus.name().equals(lst.getAt(i).city))
						l.addLast(lst.getAt(i));
				}
			}
			return l;
		}
	}

	/**
	 * 4. This method takes a social network as input and a integer parameter opc so that: 
			- If opc=1, the method returns a list of students sorted by ascending
			order according to their full name.
			- If opc=2, the method returns a lit of students sorted by descending
			order according to their full name. 
		Note 1. You must implement your own sort method based on some of the sorted algorithms (such as bubble, sort). 
		Note 2: Remember that you cannot modify or extend the StudentsList class. Therefore, if you need an auxiliary method that help you to sort the list, please include this method into the ManageNetwork class. 
		Note 3. The input list cannot be modified. The method must return a new list where the students are sorted.
	 * @param lst
	 * @param opc
	 * @return
	 */
	public StudentsList orderBy(StudentsList lst, int opc) {//Hemos utilizado el metodo de inserccion directa
		if(lst.isEmpty()) {//Si esta vacia devuelve la lista vacia
			System.out.println("The network is empty!");
			return lst;
		}
		else {
			if(opc!=1&&opc!=2) {//Si no ha elegido la opcion correcta devuelve null
				System.out.println("There are only two opctions, 1 or 2, choose a correct option");
				return null;
			}
			else {//Da igual la opcion, crea una nueva lista copiando cada atributo
				StudentsList l=new StudentsList();//Para que no se modifique la antigua
				l.header=lst.header;
				l.size=lst.size;
				l.trailer=lst.trailer;
				int p,j;
				Student aux;
				if(opc==1) {//Para la primera utiliza la insercion directa en orden ascendente
					for (p=1;p<l.size;p++){
				    	aux=l.getAt(p);
					    j=p-1;
					    while((j>=0)
					    	&& (aux.email.compareTo(l.getAt(j).email)<0)){
					    	l.insertAt(j+1, l.getAt(j));
					    	l.removeAt(j+2);
					    	j--;
					    }
					    l.insertAt(j+1, aux);
				    	l.removeAt(j+2);
				    }
				}
				else {//Para la segunda utiliza la insercion directa en orden descendente
					for (p=1;p<l.size;p++){
				    	aux=l.getAt(p);
					    j=p-1;
					    while((j>=0)
					    	&& (aux.email.compareTo(l.getAt(j).email)>0)){
					    	l.insertAt(j+1, lst.getAt(j));
					    	l.removeAt(j+2);
					    	j--;
					    }
					    l.insertAt(j+1, aux);
				    	l.removeAt(j+2);
				    }
				}
				return l;
			}
		}
	}
	
	
	
	
	/**
	 * This methods takes a social network (that is an object of StudentsList class) 
	 * and a city name as input and returns a list containing all the students 
	 * (that is, an object of the StudentsList class) who live in that city.
	 * @param lst
	 * @param city
	 * @return
	 */
	public StudentsList locateByCity(StudentsList lst, String city) {
		if(lst.isEmpty()) {//Si esta vacia devuelve la lista vacia
			System.out.println("The network is empty!");
			return lst;
		}
		else{//Si no, va añadiendo a una nueva lista los que vivan en la ciudad parametro
			StudentsList l = new StudentsList();
			for(int i=0;i<lst.size;i++) {
				if(lst.getAt(i).city.equals(city))
					l.addLast(lst.getAt(i));
			}
			return l;
		}
		
	}
	
	/**
	 * This takes a social network (an object of the StudentsList class) and two dates 
	 * and returns the list of all students from the input list 
	 * whose registration dates are in the interval of input dates. 
	 * Please, take into account the following comments:
		- start <= end. 
		- Both dates are included into the interval.
		- The order in the resulting list must be the same that in the input list.

	 * @param lst
	 * @param start
	 * @param end
	 * @return
	 */
	public StudentsList getStudentsByDateInterval(StudentsList lst, LocalDate start, LocalDate end) {
		if(lst.isEmpty()) {//Si esta vacia devuelve la lista vacia
			System.out.println("The network is empty!");
			return lst;
		}
		StudentsList resultList=new StudentsList();
		for(int i=0;i<lst.size;i++) {//Si no va añadiendo los que se registraron entre esas fechas
			if((lst.getAt(i).date_sign_in.isAfter(start) ||lst.getAt(i).date_sign_in.isEqual(start)) 
					&&(lst.getAt(i).date_sign_in.isBefore(end) ||lst.getAt(i).date_sign_in.isEqual(end)))
				resultList.addLast(lst.getAt(i));
		}
		return resultList;
	
	}
	
	
	
	public static void main(String[] args) {
		
		
	}

}
