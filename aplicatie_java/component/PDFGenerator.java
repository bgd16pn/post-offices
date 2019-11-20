package component;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	private SimpleDateFormat df = new SimpleDateFormat("DD-MM-YYYY HH:mm:ss");

	public PDFGenerator(String exportPath, String[] columnNames, String[][] data, float[] rowWidths, String username,
			String role) {

		Document document = new Document();
		try {
			document.setPageSize(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(exportPath.concat("\\Log_Report.pdf")));

			document.open();

			/** TITLU */
			Paragraph title = new Paragraph("RAPORT ACTIVITATE", new Font(FontFamily.HELVETICA, 30, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			/** DATA EMITERII */
			Paragraph subtitle = new Paragraph(df.format(Calendar.getInstance().getTime()),
					new Font(FontFamily.HELVETICA, 14, Font.NORMAL));
			subtitle.setAlignment(Element.ALIGN_CENTER);
			document.add(subtitle);
			
			/** CREATOR	 */
			Paragraph creator = new Paragraph(username + " : " + role,
					new Font(FontFamily.HELVETICA, 10, Font.BOLD));
			creator.setAlignment(Element.ALIGN_CENTER);
			document.add(creator);

			document.add(Chunk.NEWLINE);

			/** TABEL */
			PdfPTable table = new PdfPTable(columnNames.length);
			table.setHeaderRows(1);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			table.setWidths(rowWidths);

			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setFixedHeight(20);

			Font textFont = new Font(FontFamily.HELVETICA, 14, Font.BOLD);

			PdfPCell c1 = null;
			for (int i = 0; i < columnNames.length; i++) {
				c1 = new PdfPCell(new Phrase(new Chunk(columnNames[i], textFont)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				c1.setFixedHeight(50);
				c1.setRowspan(1);
				table.addCell(c1);
			}

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < columnNames.length; j++) {
					table.addCell(data[i][j]);
				}
			}

			document.add(table);

			System.out.println("Report generated successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	public static void main(String[] args) {
		String[] columnNames = { "Id", "Username", "Parola", "Email", "Telefon", "Adresa" };
		String[][] data = { { "1", "user_1", "pass_1", "email_1", "telefon_1", "adresa_1" },
				{ "2", "user_2", "pass_2", "email_2", "telefon_2", "adresa_2" },
				{ "3", "user_3", "pass_3", "email_3", "telefon_3", "adresa_3" },
				{ "4", "user_4", "pass_4", "email_4", "telefon_4", "adresa_4" } };

		float[] rows = { 30f, 50f, 50f, 50f, 50f, 100f };

		new PDFGenerator("C:\\Users\\Bogdan\\Desktop", columnNames, data, rows, "Admin", "Administrator");
	}

}
