package notebook;

import java.util.ArrayList;
import java.util.HashSet;

class Value{
	private int i;
	public void set(int i) {this.i = i;}
	public int get() {return i;}
	public String toString() {return ""+i;}
}

public class NoteBook {
	public ArrayList<String> notes = new ArrayList<String>();
	
	public void add(String s) {
		notes.add(s);
	}
	
	public void add(String s, int index) {
		notes.add(index, s);
	}
	
	public int getSize() {
		return notes.size();
	}
	
	public String getNote(int index) {
		return notes.get(index);
	}
	
	public void removeNote(int index) {
		notes.remove(index);
	}
	
	public String[] list() {
		String[] a = new String[notes.size()];
		notes.toArray(a);
		return a;
	}
	public static void main(String[] args) { 
		Value v = new Value();
		v.set(10);
		System.out.println(v);
		
		ArrayList<String> a = new ArrayList<String>();
		a.add("first");
		a.add("second");
		a.add("third");
		System.out.println(a);
		System.out.println("-----------------");
		HashSet<String> s = new HashSet<String>();
		s.add("first");
		s.add("second");
		s.add("first");
		System.out.println(s);
		}
	} 

