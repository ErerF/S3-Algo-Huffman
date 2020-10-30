package Huffman;
import java.util.ArrayList;

public class Caractere {
	private char lettre;
	private int frequence;
	private String code="";	
	
	//constructeur
	public Caractere(char l,int f)
	{
		lettre=l;
		frequence=f;
	}
	public Caractere(int nb)
	{
		this.lettre='\0';
		this.frequence=nb;
	}

	//setter
	public void setCode(char c)
	{
		code+=c;
	}
	public void setCode(String s,char c)
	{
		String tmp=s;
		code=tmp+c;
	}
	
	//getter
	public char getLettre()
	{
		return this.lettre;
	}
	public int getFreq()
	{
		return this.frequence;
	}
	public String getCode()
	{
		if(this.code==null)
			System.out.println(frequence+":null1");
		return this.code;
	}
	
	public boolean equals(Caractere c)
	{
		return (this.lettre==c.lettre && this.frequence==c.frequence);
	}
	
	public String toString()
	{
		String res="";
		if(this.lettre!='\0')
			return res+lettre+":"+frequence;
		else
			return res+frequence;
	}
}
