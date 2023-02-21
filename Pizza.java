import java.util.Vector;
public class Pizza {

	private String syote;
	private Vector hMuuttuja;
	//vektori tähän
	public Pizza()
	{
		this.syote = "";
		this.hMuuttuja = new Vector();
	}
	public Pizza(String syote)
	{
		this.syote = syote;
		this.hMuuttuja = new Vector();		
	}
	public void lisaaTayte (String h)
	{
		this.hMuuttuja.addElement(h);		
	}
	public Vector getTaytteet()
	{
		return this.hMuuttuja;		
	}
	public String getSyote()
	{
		return this.syote;
	}
	//public String getTiedot()
	//{
		//return "Pizza: " + this.syote +
		//"\n" + "Täytteet: " + this.hMuuttuja;
		
//	}

}
