package de.otto.jlineup.report;

import de.otto.jlineup.file.FileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static java.util.Collections.singletonList;
import static org.mockito.MockitoAnnotations.initMocks;

public class HTMLReportGeneratorTest {

    private HTMLReportGenerator testee;

    @Mock
    private FileService fileServiceMock;

    @Before
    public void setup() {
        initMocks(this);
        testee = new HTMLReportGenerator(fileServiceMock);
    }

    @Test
    public void shouldWriteHTMLReport() throws Exception {
        ScreenshotComparisonResult screenshotComparisonResult =
                new ScreenshotComparisonResult("url", 1337, 1338, 0d, "before", "after", "difference");

        String expectedHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>JLineup Comparison Report</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "    <META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"/>\n" +
                "    <META HTTP-EQUIV=\"Expires\" CONTENT=\"-1\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "\n" +
                "    <style>\n" +
                "\n" +
                "        body {\n" +
                "            background-color: white;\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            margin-left: 10px;\n" +
                "            margin-top: 10px;\n" +
                "        }\n" +
                "\n" +
                "        table tr:nth-child(even) {\n" +
                "            background-color: #eee;\n" +
                "        }\n" +
                "\n" +
                "        table tr:nth-child(odd) {\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "\n" +
                "        table th {\n" +
                "            color: white;\n" +
                "            background-color: black;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            padding: 0 0 0 0;\n" +
                "            border: 1px solid;\n" +
                "            border-collapse: collapse;\n" +
                "            vertical-align: top;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            padding: 0 0 15px 0;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            padding: 5px;\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"report\">\n" +
                "    <div class=\"context\">\n" +
                "        <h3>url (Browser window width: 1337)</h3>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <th>Info</th>\n" +
                "                <th>Before</th>\n" +
                "                <th>After</th>\n" +
                "                <th>Difference</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <p><a href=\"url\" target=\"_blank\" title=\"url\">url</a><br/>\n" +
                "                        Width: 1337<br/>\n" +
                "                        Scroll pos: 1338<br/>\n" +
                "                        Difference: 0.00%\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <a href=\"before\" target=\"_blank\">\n" +
                "                        <img width=\"350\" src=\"before\" />\n" +
                "                    </a>\n" +
                "                    \n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <a href=\"after\" target=\"_blank\">\n" +
                "                        <img width=\"350\" src=\"after\" />\n" +
                "                    </a>\n" +
                "                    \n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <a href=\"difference\" target=\"_blank\">\n" +
                "                        <img width=\"350\" src=\"difference\" />\n" +
                "                    </a>\n" +
                "                    \n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        testee.renderReport("report", singletonList(screenshotComparisonResult));

        Mockito.verify(fileServiceMock).writeHtmlReport(expectedHtml);
    }

}