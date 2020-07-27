
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class FlyingSaucerSpecialCharactersTest {

    @Test
    public void templateShouldRenderJapaneseCharacters() throws DocumentException, IOException, URISyntaxException {
        final String textPre = "Following text will contain japanese characters";
        final String japaneseText = "これはテストです";
        final String textPost = "End of japanese characters";

        final int expectedBytes = 1636;

        final String html = "<html>\n"
            + "<head>\n"
            + "    <style>\n"
            + "        body{\n"
            + "            font: 15px 'STSong-Light-H', sans-serif;\n"
            + "        }\n"
            + "    </style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p>" + textPre + "</p>\n"
            + "<p>" + japaneseText + "</p>\n"
            + "<p>" + textPost + "</p>\n"
            + "</body>\n"
            + "</html>";
        byte[] pdfContents = convertToPdf(html);
        String filename = "/tmp/japanese-" + new Date().toString() + ".pdf";
        writePdf(pdfContents, filename);
        Assert.assertEquals(expectedBytes, pdfContents.length);
    }

    @Test
    public void templateShouldRenderPolishCharacters() throws DocumentException, IOException, URISyntaxException {
        final String textPre = "Following text will contain polish characters";
        final String polishText = "Zażółć gęślą jaźń";
        final String textPost = "End of polish characters";

        final int expectedBytes = 9499;

        final String fontWithPolishSupport = "Abhaya Libre";

        final String html = "<html>\n"
            + "<head>\n"
            + "    <style>\n"
            + "        body{\n"
            + "            font: 15px '" + fontWithPolishSupport + "', sans-serif;\n"
            + "        }\n"
            + "    </style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<p>" + textPre + "</p>\n"
            + "<p>" + polishText + "</p>\n"
            + "<p>" + textPost + "</p>\n"
            + "</body>\n"
            + "</html>";
        byte[] pdfContents = convertToPdf(html);
        String filename = "/tmp/polish-" + new Date().toString() + ".pdf";
        writePdf(pdfContents, filename);

        Assert.assertEquals(expectedBytes, pdfContents.length);
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
        return new File(FlyingSaucerSpecialCharactersTest.class.getResource("/fonts").toURI());
    }

    private static class Constants {

        private static final boolean FINISH_PDF = true;
    }

    private void writePdf(byte[] pdfContents, String pdfFileName) {
        try (FileOutputStream fos = new FileOutputStream(pdfFileName)) {
            fos.write(pdfContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
