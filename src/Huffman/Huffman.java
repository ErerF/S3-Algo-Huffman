package Huffman;
import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {
	private final static int NB_CHAR=256;
	
	//construire l'arbre des feuilles,
	//cad l'arbre dont l'info est une feuille et ses fils sont null
	public static void construireArbre(Liste l,ArrayList a)
	{
		if(l.info()!=null) 
		{
			a.add(new Arbre(l.info()));
			construireArbre(l.reste(),a);
		}
	}
	public static Arbre chercheArbre(ArrayList a,Caractere c)
	{
		int i=0;
		while(a.get(i)!=null)
		{
			if(((Arbre)a.get(i)).info().equals(c))
				return (Arbre)a.get(i);
			i++;
		}
		return null;
	}
	public static void coderLettre(Arbre a)
	{
		char g='0';
		char d='1';
		if(a!=null && a.getFg()!=null && a.getFd()!=null )
		{
			if(a.info().getCode()==null)
			{
				a.getFg().info().setCode(g);
				a.getFd().info().setCode(d);
			}
			else
			{
				a.getFg().info().setCode(a.info().getCode(),g);
				a.getFd().info().setCode(a.info().getCode(),d);
			}
			coderLettre(a.getFg());
			coderLettre(a.getFd());
		}
	}
	public static void afficherCode(Liste l)
	{
		if(l.info()!=null)
		{
			System.out.println(l.info().getLettre()+":"+l.info().getCode()+"    ");
			afficherCode(l.reste());
		}
	}
	public static String encoder(String s,Liste l)
	{
		String res="";
		for(int i=0;i<s.length();i++)
		{
			res+=l.chercheChar(s.charAt(i)).getCode();
		}
		return res;
	}
	public static String decoder(String code,Arbre a,Arbre aTotal,int index)
	{
		String res="";
		
		if(a.getFg()==null&&a.getFd()==null)
		{	
			res=res+a.info().getLettre()+decoder(code,aTotal,aTotal,index);
			return res;
		}
		else
		{
			if(index<code.length())
			{
				if(code.charAt(index)=='0')		
					return res+=decoder(code,a.getFg(),aTotal,index+1);
				else
					return res+=decoder(code,a.getFd(),aTotal,index+1);
			}
			return res;
		}
	}
	
	public static void main(String[] args){
		String texte;
		int[] freq=new int[NB_CHAR];
		char[] lettre=new char[NB_CHAR];
		
		System.out.println("Veuillez saisir le texte svp :");
		Scanner sc=new Scanner(System.in);
		texte=sc.nextLine();
		
		//remplir les tableaux lettre[] et freq[]
		boolean trouver;
		int i,j;
		for(i=0;i<texte.length();i++)
		{
			j=0;
			trouver=false;
			while(lettre[j]!='\0' && !trouver)
			{
				if(lettre[j]==texte.charAt(i))
				{
					trouver=true;
					freq[j]++;
				}
				j++;
			}
			if(!trouver)
			{
				lettre[j]=texte.charAt(i);
				freq[j]=1;
			}
		}
		
		//afficher les lettres et les frequences:
		//creer les caracteres correspondants et les saisir dans la liste
		i=0;
		Liste l=new Liste();
		ArrayList a=new ArrayList();
		while(i<lettre.length && lettre[i]!='\0')
		{
			System.out.println(lettre[i]+":"+freq[i]);
			Caractere c=new Caractere(lettre[i],freq[i]);
			l=l.insererOrd(c);
			i++;
		}
		System.out.println("Voici la liste des caracteres:");
		System.out.println(l);
		
		construireArbre(l,a);
		
		//completer l'arbre
		Liste tmp=l;
		Arbre fg;//une variable pour aider a trouver l'arbre( chercheArbre() )
		Arbre fd;
		while(tmp.info()!=null && tmp.reste().info()!=null)
		{
			Caractere noeud=new Caractere(tmp.info().getFreq()+tmp.reste().info().getFreq());
			
			fg=chercheArbre(a,tmp.info());
			a.remove(fg);
			fd=chercheArbre(a,tmp.reste().info());
			a.remove(fd);
			a.add(new Arbre( noeud , fg , fd ));
			
			tmp=tmp.insererOrd(noeud);
			tmp=tmp.supprimerOrd(tmp.info());
			tmp=tmp.supprimerOrd(tmp.info());
		}
		System.out.println("Arbre:");
		System.out.println(a.get(0));
		
		//calculer le codage de chaque lettre
		coderLettre((Arbre)a.get(0));
		//afficher les codes des lettres
		System.out.println("Voici les codes des lettres :");
		afficherCode(l);
		
		//afficher le code du texte
		String codeT=encoder(texte,l);
		System.out.println("Le code du texte est :");
		System.out.println(codeT);
		//afficher le resultat de decoder
		System.out.println("Decoder ce code, le resultat est :");
		System.out.println(decoder(codeT,(Arbre)a.get(0),(Arbre)a.get(0),0));
	}
}
