package io.jenkins.plugins.jquery3.api;

import java.io.IOException;
import java.util.logging.Level;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.JenkinsRule.WebClient;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link JQuery3Api}.
 *
 * @author Ullrich Hafner
 */
public class JQuery3ApiITest {
    /** Jenkins rule per suite. */
    @Rule
    public final JenkinsRule jenkins = new JenkinsRule();

    @Test @Ignore
    public void shouldBla() {
        HtmlPage rootPage = getRootPage();

        assertThat(rootPage.asText()).contains("Bla");

    }

    private HtmlPage getRootPage() {
        try {
            return getWebClient().goTo("");
        }
        catch (SAXException | IOException e) {
            throw new AssertionError("Unexpected I/O Exception", e);
        }
    }

    private JenkinsRule.WebClient getWebClient() {
        WebClient webClient = jenkins.createWebClient();
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE);
        webClient.setIncorrectnessListener((s, o) -> {
        });

        webClient.setJavaScriptEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setCssEnabled(true);

        webClient.getOptions().setDownloadImages(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        return webClient;
    }

}
