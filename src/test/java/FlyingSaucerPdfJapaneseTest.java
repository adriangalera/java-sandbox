
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class FlyingSaucerPdfJapaneseTest {

    @Test
    public void templateShouldRenderJapaneseCharacters() throws DocumentException, IOException, URISyntaxException {
        String html = "<html>\n"
            + "<head>\n"
            + "    <style>\n"
            + "        body{\n"
            + "            font: 15px 'STSong-Light-H', sans-serif;\n"
            + "        }\n"
            + "    </style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p>Following text will contain japanese characters</p>\n"
            + "<p>これはテストです</p>\n"
            + "<p>End of japanese characters</p>\n"
            + "</body>\n"
            + "</html>";
        byte[] pdfContents = convertToPdf(html);
        writePdf(pdfContents);


    }

    private byte[] convertToPdf(String html) throws DocumentException, IOException, URISyntaxException {
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        renderPdf(html, pdfStream);
        return pdfStream.toByteArray();
    }

    private void renderPdf(String html, ByteArrayOutputStream os)
        throws IOException, DocumentException, URISyntaxException {
        ITextRenderer renderer = setupRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(os, Constants.FINISH_PDF);
    }

    private ITextRenderer setupRenderer() throws IOException, DocumentException, URISyntaxException {
        ITextRenderer renderer = new ITextRenderer();
        injectFonts(renderer);
        return renderer;
    }

    private void injectFonts(ITextRenderer renderer) throws DocumentException, IOException, URISyntaxException {
        for (File font : getFontsDirectory().listFiles()) {
            renderer.getFontResolver().addFont(font.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
    }

    private File getFontsDirectory() throws URISyntaxException {
        return new File(FlyingSaucerPdfJapaneseTest.class.getResource("/fonts").toURI());
    }

    private static class Constants {

        private static final boolean FINISH_PDF = true;
    }

    private void writePdf(byte[] pdfContents) {
        final String pdfFileName = "generated" + new Date().toString() + ".pdf";
        try (FileOutputStream fos = new FileOutputStream(pdfFileName)) {
            fos.write(pdfContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
