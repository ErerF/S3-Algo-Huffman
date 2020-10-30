package Huffman;

public class Arbre {
	private Caractere info;
	private Arbre filsGauche;
	private Arbre filsDroit;
	
	//constructeur
	public Arbre(Caractere i)
	{
		info=i;
	}
	public Arbre(Caractere i,Arbre fg,Arbre fd)
	{
		info=i;
		filsGauche=fg;
		filsDroit=fd;
	}
	
	//getter
	public Arbre getFg()
	{
		return this.filsGauche;
	}
	public Arbre getFd()
	{
		return this.filsDroit;
	}
	
	public Caractere info()
	{
		return this.info;
	}
	
	public String toString()
	{
		String res="";
		return res+"racine:"+info+"   fg(de "+info+"):"+filsGauche+"   fd(de "+info+"):"+filsDroit+"\n";
	}
}
