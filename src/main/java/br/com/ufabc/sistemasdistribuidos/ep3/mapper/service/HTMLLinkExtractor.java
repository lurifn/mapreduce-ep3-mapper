package br.com.ufabc.sistemasdistribuidos.ep3.mapper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLLinkExtractor {

	private Pattern patternTag, patternLink;
	private Matcher matcherTag, matcherLink;

	private static final String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	private static final String HTML_A_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"(http[^\"]*\")|'http[^']*'|([^'\">\\s]+))";

	public HTMLLinkExtractor() {
		patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	}

	/**
	 * Validate html with regular expression
	 * 
	 * @param html html content for validation
	 * @return Vector links and link text
	 */
	public List<String> grabHTMLLinks(final String html) {

		Vector<HtmlLink> linkVector = new Vector<HtmlLink>();

		matcherTag = patternTag.matcher(html);

		while (matcherTag.find()) {

			String href = matcherTag.group(1); // href
			String linkText = matcherTag.group(2); // link text

			matcherLink = patternLink.matcher(href);

			while (matcherLink.find()) {

				String link = matcherLink.group(1); // link
				HtmlLink obj = new HtmlLink();
				obj.setLink(link);
				obj.setLinkText(linkText);

				linkVector.add(obj);

			}

		}

		List<String> links = new ArrayList<String>();
		for(int i =0; i< linkVector.size(); i++) {
			links.add(linkVector.get(i).getLink());
		}
		
		return links;
	}

}
