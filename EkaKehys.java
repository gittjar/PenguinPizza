import javax.swing.*;  // tarvitaan käyttöliittymäkomponentteihin J-alk.
import java.awt.event.*; // tapahtumien kiinniotto
import java.awt.*;  // tarvittiin värin vaihtoon
import javax.swing.JTextField;
import java.util.*;

//Jarno Keränen harjoitustyö - Olio-ohjelmointi
public class EkaKehys
{

	private JFrame kehys;
	private JPanel paneli;
	private JButton nappi;
	
	private JLabel kuvapenguin;
	private JTextField syote;
	
	private JLabel labeli1; //anna pizzan nimi label
	private JLabel labeli2; //pizzalista label
	private Vector pizzaVector;//pizzalista
	
	private JCheckBox kebab;  // valintaruutu1
	private JCheckBox jalapeno;
	private JCheckBox ananas;
	private JCheckBox tacokastike;
	private JCheckBox salami;
	
	private JList pizzaluettelo;
	//private JScrollPane scrollPane;
	private JButton tulostaNappi;
	private JButton poistaNappi;
	private DefaultListModel listaMalli;
	private JLabel pizzaOtsikko;
	private JButton vaihda; //vaihda taustaväri
	
	
	//private JLabel tayteOtsikko;
	
	public EkaKehys()   // tätä kutsutaan kun ohjelma
	{					// käynnistyy
		alustaKomponentit();
		kehys.setSize(800,400);
		kehys.setVisible(true);	
		kehys.setTitle("Pizza - Harjoitustyö");
		kehys.setLocation(250,200);
			
		pizzaVector = new Vector();
		
	
		
	}

	/***************************************************************/
	private void alustaKomponentit()
	{
		ImageIcon kuva1 = new ImageIcon("penguinpizza.jpg");
		
		
		
		kehys = new JFrame();
		paneli = new JPanel();
		nappi = new JButton("Lisää");
		tulostaNappi = new JButton("Päivitä");
		poistaNappi = new JButton("Poista");
		vaihda = new JButton("Taustaväri");
		
		// liitetään painikkeeseen toimintaa
		nappi.addActionListener(new NappiKuuntelija());
		vaihda.addActionListener(new Kuuntelija());
		
		
		poistaNappi.addActionListener(new PoistaKuuntelija());
		
		kehys.setLayout(null);
		paneli.setLayout(null);
		
		paneli.setBounds(0,0,800,400);
						// eka 0 = etäisyys vasemmasta laidasta
						// toka 0 = etäisyys yläreunasta
						// eka 300 = leveys
						// toka 300 = korkeus
		nappi.setBounds(200,330,100,30);
		
		poistaNappi.setBounds(400,330,100,30);
		vaihda.setBounds(500,330,100,30);
		
		
		
		// ja tähän lisättiin kuvaa JLabeliin
		kuvapenguin = new JLabel("",kuva1,JLabel.CENTER);
		kuvapenguin.setBounds(50,0,100,100);
		
		
		labeli1 = new JLabel ("Syötä pizzan nimi");
		labeli1.setBounds(50,100,100,30);
		
		labeli2 = new JLabel ("  Pizzalista - Kaikki 6€");
		labeli2.setBounds(200,50,200,30);
		
		syote = new JTextField ("");
		syote.setBounds(50,130,100,25);
		
		kebab = new JCheckBox("Kebab");
		kebab.setBounds(50,160,100,30);
		
		jalapeno = new JCheckBox("Jalapeno");
		jalapeno.setBounds(50,190,100,30);
		
		ananas = new JCheckBox("Ananas");
		ananas.setBounds(50,220,100,30);
		
		
		tacokastike = new JCheckBox("Tacokastike");
		tacokastike.setBounds(50,250,100,30);
		
		salami = new JCheckBox("Salami");
		salami.setBounds(50,280,100,30);
		
		pizzaluettelo = new JList();
		tulostaNappi = new JButton("Päivitä");
		tulostaNappi.addActionListener(new TehdaanLuettelo());
		
	
		
		
		
		//pizzaluettelo.setBounds(200,300,150,150);
		
		//scrollPane = new JScrollPane(pizzaluettelo);
		//scrollPane.setBounds(200,50,150,150);
		
		//paneli
		pizzaOtsikko = new JLabel("Nimi");
		pizzaOtsikko.setBounds(200,10,150,30);
		//tayteOtsikko = new JLabel("Täytteet");
		//tayteOtsikko.setBounds(250,10,150,30);
		pizzaluettelo.setBounds(200,80,400,230);
		tulostaNappi.setBounds(300,330,100,30);
		
		paneli.add(nappi);   // painikkeen liittäminen paneliin
		paneli.add(tulostaNappi);
		paneli.add(poistaNappi);
		paneli.add(kuvapenguin);
		paneli.add(kebab);
		paneli.add(jalapeno);
		paneli.add(tacokastike);
		paneli.add(ananas);
		paneli.add(salami);
		paneli.add(syote);
		paneli.add(labeli1);
		paneli.add(labeli2);
		paneli.add(pizzaluettelo);
		//paneli.add(scrollPane);
		paneli.add(vaihda);
		
		
		paneli.setBackground(Color.lightGray);
		
		kehys.getContentPane().add(paneli);
		// taustavärin vaihto  - ei onnistu, koska paneli peittää
		// kehyksen
		
		//kehys.getContentPane().setBackground(Color.yellow);
		//lisätään tooltiptextejä
		nappi.setToolTipText("Lisää Pitza muistiin ja paina Päivitä-nappia");
		tulostaNappi.setToolTipText("Päivittää tekemäsi muutokset");
		kuvapenguin.setToolTipText("PenguinPizza");
		poistaNappi.setToolTipText("Poista Pizza listasta valitsemalla Pizza ja paina Päivitä-nappia");
		
		kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/***************************************************************/
public static void main(String[] args)
{
	new EkaKehys();

}

// kirjoitetaan sisäluokka tapahtumienkäsittelyyn
class NappiKuuntelija implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		
		Pizza hMuuttuja = new Pizza(); // luodaan yksi henkilö
		// tekstikentissä olevat tiedot siirretään jäsenmuuttujiin
		if (syote.getText() != "")
			hMuuttuja = new Pizza(syote.getText());
		
		if ( kebab.isSelected())
			hMuuttuja.lisaaTayte("  " + kebab.getText());
		if ( jalapeno.isSelected())
			hMuuttuja.lisaaTayte("  " + jalapeno.getText());
		if ( salami.isSelected())
			hMuuttuja.lisaaTayte("  " + salami.getText());
		if ( ananas.isSelected())
			hMuuttuja.lisaaTayte("  " + ananas.getText());
		if ( tacokastike.isSelected())
			hMuuttuja.lisaaTayte("  " + tacokastike.getText());
		
		//kebab.setSelected(false);
		//hMuuttuja.setKebab(true.getLabel());
		//hMuuttuja.setKebab(kebab);
		//hMuuttuja.setSotu(sotu.getText());
		
		pizzaVector.addElement(hMuuttuja);
		// tyhjennä tekstikentät ja checkboksit
		syote.setText("");
		kebab.setSelected(false);
		jalapeno.setSelected(false);
		salami.setSelected(false);
		ananas.setSelected(false);
		tacokastike.setSelected(false);
		
	}
}
//poistanappiin toimintoja, poistetaan listasta
//poistanappi poistaa vektorista valitun objektin
//ja päivitänappi poistaa valitun objektin listasta

class PoistaKuuntelija implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		Pizza h;
		for ( int i = 0; i < pizzaVector.size(); i++)
		{
			
			// otetaan yksi pizza ja täytteet vektorista
			h = (Pizza)pizzaVector.elementAt(i);
			if (h.getSyote().equals(pizzaluettelo.getSelectedValue()))
				
					{
			pizzaVector.removeElementAt(i);
					
			for ( int j = 0; j < pizzaVector.size(); j++)
			{
				// otetaan yksi pizza ja täytteet vektorista
				h = (Pizza)pizzaVector.elementAt(j);
				listaMalli.addElement(h.getSyote());	//pizzannimi	
				listaMalli.addElement(h.getTaytteet());//täyte
			}
				}
		}
	}
}
class TehdaanLuettelo implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		DefaultListModel listaMalli = new DefaultListModel();
		Pizza h;
		//tutkitaan vektoria
		for ( int i = 0; i < pizzaVector.size(); i++)
		{
			// otetaan yksi pizza ja täytteet vektorista
			h = (Pizza)pizzaVector.elementAt(i);
			listaMalli.addElement(h.getSyote());	//pizzannimi	
			listaMalli.addElement(h.getTaytteet());//täyte
		}
		pizzaluettelo.setModel(listaMalli);

}

}
//värin vaihto
class Kuuntelija implements  ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		Object aiheuttaja = ae.getSource();
		if ( aiheuttaja == vaihda)			// tehtävä 3
		{
			Color vari = JColorChooser.showDialog(
					vaihda, "Vaihda taustaväri", vaihda.getBackground());
		 
				if (vari != null)
				{
					vaihda.setBackground(vari);
					paneli.setBackground(vari);
					kehys.getContentPane().add(paneli);
					kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
		}
	}
				public void valueChanged()
				{

				}
}

	}




// päättää koko ohjelman








