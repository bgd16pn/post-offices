//package test;
//
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import component.IdGenerator;
//import model.Adresa;
//import model.ArticolPostal;
//import model.Colet;
//import model.MandatPostal;
//import model.OficiuPostal;
//import model.ScrisoareRecomandata;
//
//public class TestOficiuPostal {
//
//	@Test
//	public void testAdresa() {
//
//		Adresa adr1 = new Adresa("Observatorului", "34", "Camin 7", "1", "528", "Cluj-Napoca", "Cluj", "400066",
//				"Romania");
//
//		Assert.assertEquals(new String("Observatorului"), adr1.getStrada());
//		Assert.assertEquals(new String("Cluj-Napoca"), adr1.getOras());
//		Assert.assertEquals(new String("528"), adr1.getApartament());
//
//		// System.out.print(adr1.getAdresa());
//
//		Adresa adr2 = new Adresa("Observatorului", "34", "Cluj-Napoca", "Cluj", "400066", "Romania");
//		Assert.assertNull(adr2.getBloc());
//		Assert.assertEquals(new String("Cluj"), adr2.getJudet());
//		Assert.assertNotNull(adr2.getTara());
//
//		// System.out.print(adr2.getAdresa());
//	}
//
//	@Test
//	public void testArticolePostale() {
//
//		Adresa adrGigi = new Adresa("Observatorului", "34", "Camin 7", "1", "528", "Cluj-Napoca", "Cluj", "400066",
//				"Romania");
//		Adresa adrIonel = new Adresa("G-ral Dr. Cernatescu Ion", "7", "A12", "1", "8", "Craiova", "Dolj", "200093",
//				"Romania");
//
//		ArticolPostal colet1 = new Colet(IdGenerator.getNextId(), new Date(), "Gigi Expeditorul", adrGigi,
//				"Ionel Destinatarul", adrIonel, 100f, 20f, 1f, false, false, false);
//		Assert.assertNull(colet1.getDataRidicare());
//		// System.out.println(colet1.getStatus());
//
//		ArticolPostal recomandata1 = new ScrisoareRecomandata(IdGenerator.getNextId(), new Date(), "Ionel", adrIonel,
//				"Gigi", adrGigi, 0f, 0f, 20f);
//
//		recomandata1.setDataRidicare(new Date());
//		// System.out.println(recomandata1.getStatus());
//
//		ArticolPostal mandat1 = new MandatPostal(IdGenerator.getNextId(), new Date(), "Ionel", adrIonel, "Gigel",
//				adrGigi, 100.0f, "Cadou de ziua ta");
//
//		mandat1.setAvizat(true);
//		
//		Assert.assertTrue(mandat1.isAvizat());
//		// System.out.println(mandat1.getStatus());
//
//	}
//	
//	@Test
//	public void testAdaugaArticol() {
//		
//		OficiuPostal op1 = new OficiuPostal();
//		op1.setNumeOficiu("13 Marasti");
//		op1.setTelefonContact("0725 907 350");
//		
//		Assert.assertEquals("13 Marasti", op1.getNumeOficiu());
//		
//		// System.out.println(op1.getStatus());
//		
//		Adresa adrGigi = new Adresa("Observatorului", "34", "Camin 7", "1", "528", "Cluj-Napoca", "Cluj", "400066",
//				"Romania");
//		Adresa adrIonel = new Adresa("G-ral Dr. Cernatescu Ion", "7", "A12", "1", "8", "Craiova", "Dolj", "200093",
//				"Romania");
//		
//		ArticolPostal mandat1 = new MandatPostal(IdGenerator.getNextId(), new Date(), "Ionel", adrIonel, "Gigel",
//				adrGigi, 100.0f, "Cadou de ziua ta");
//		
//		op1.inregistreazaArticolPostal(mandat1);
//		
//		Assert.assertEquals(mandat1, op1.getArticolePostaleNeridicate()[0]);
//		Assert.assertEquals(1, op1.getNumarArticoleNeridicate());
//		
//		// System.out.println(op1.getArticolePostaleNeridicate()[0].getStatus());
//				
//	}
//	
//	@Test
//	public void testRidicaArticol() {
//		
//		OficiuPostal op1 = new OficiuPostal();
//		op1.setNumeOficiu("13 Marasti");
//		op1.setTelefonContact("0725 907 350");
//		
//		Assert.assertEquals("13 Marasti", op1.getNumeOficiu());
//		
//		// System.out.println(op1.getStatus());
//		
//		Adresa adrGigi = new Adresa("Observatorului", "34", "Camin 7", "1", "528", "Cluj-Napoca", "Cluj", "400066",
//				"Romania");
//		Adresa adrIonel = new Adresa("G-ral Dr. Cernatescu Ion", "7", "A12", "1", "8", "Craiova", "Dolj", "200093",
//				"Romania");
//		
//		Date dataAzi = new Date();
//		
//		ArticolPostal mandat1 = new MandatPostal(IdGenerator.getNextId(), dataAzi, "Ionel", adrIonel, "Gigel",
//				adrGigi, 100.0f, "Cadou de ziua ta");
//		
//		op1.inregistreazaArticolPostal(mandat1);
//		
//		op1.ridicaArticolPostal(dataAzi, "Gigel");
//		
//		Assert.assertEquals(mandat1, op1.getArticolePostaleRidicate()[0]);
//		Assert.assertEquals(0, op1.getNumarArticoleNeridicate());
//		Assert.assertEquals(1,  op1.getNumarArticoleRidicate());
//		
//		// System.out.println(op1.getArticolePostaleNeridicate()[0].getStatus());
//				
//	}
//
//	
//}
