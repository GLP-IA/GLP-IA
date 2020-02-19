package test;
import process.QTable;
import process.QFonction;
public class TestQtable {

	public static void main(String[] args) {
		QTable q = new QTable(9);
		q.afficher();
		QFonction f=new QFonction(q,0.6,0.7);
		f.update(0,0,0,1,50);
		q.afficher();
	}

}
