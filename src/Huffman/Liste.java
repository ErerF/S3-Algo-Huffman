package Huffman;
import java.util.Collections;
import java.util.Scanner;

public class Liste {
	private Caractere info;
	private Liste reste;
	
	//constructeur
	public Liste(){}
	public Liste(Caractere i)
	{
		info=i;
	}
	public Liste(Caractere i,Liste r)
	{
		info=i;
		reste=r;
	}
	
	//getter
	public Caractere info()	
	{
		return this.info;
	}
	public Liste reste()
	{
		return this.reste;
	}
	
	//pour inserer le caractere dans la tete de la liste donnee
	public Liste prefixer(Caractere i)
	{
		return new Liste(i,this);
	}
	public Liste insererOrd(Caractere c)
	{
		if(this.info==null)
			return this.prefixer(c);
		else
		{
			if(c.getFreq()>=this.info.getFreq())
				return this.reste.insererOrd(c).prefixer(this.info);
			else
				return this.prefixer(c);
		}
	}
	public Liste supprimerOrd(Caractere c)
	{
		if(this==null)
			return this;
		else
		{
			if(this.info.getFreq()<c.getFreq())
				return this;
			else if(this.info.getFreq()>c.getFreq())
				return this.reste.supprimerOrd(c).prefixer(this.info);
			else
				return this.reste;
		}
	}
	public Caractere chercheChar(char c)
	{
		if(this.info.getLettre()==c)
			return this.info;
		else
			return this.reste.chercheChar(c);
	}
	
	public String toString()
	{
		String res="";
		if(info!=null)
		{
			res+=info+"     "+reste;
		}
			
		return res;
	}
}
