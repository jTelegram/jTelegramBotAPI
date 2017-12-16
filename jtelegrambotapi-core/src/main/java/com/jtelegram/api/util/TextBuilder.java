package com.jtelegram.api.util;

import com.jtelegram.api.requests.message.send.SendText;

/**
 * This class allows developers to format
 * their text in a simple manner with automatic
 * basic escaping; creating a more elegant
 * solution without weird markdown escaping or
 * manually writing HTML-formatted text.
 *
 * All public methods will escape their input
 * (except TextBuilder#html(String)) and format
 * accordingly.
 *
 * Keep in mind that while most places should
 * accept an instance of this builder as valid
 * input, using TextBuilder#toHtml() requires
 * that you specify the parse mode to HTML in
 * the request you're sending.
 *
 * @see SendText.SendTextBuilder#text(TextBuilder)
 */
public class TextBuilder {
    private final StringBuilder message = new StringBuilder();

    public TextBuilder create() {
        return new TextBuilder();
    }

    private TextBuilder() {
    }

    private String htmlEscaped(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private TextBuilder element(String e, String content) {
        // for the closing element, split(" ")[0] is used in case of parameters
        // that are used like linking
        message.append("<").append(e).append(">").append(htmlEscaped(content)).append("</").append(e.split(" ")[0]).append(">");
        return this;
    }

    public TextBuilder plain(String text) {
        return escaped(text);
    }

    public TextBuilder escaped(String text) {
        return html(htmlEscaped(text));
    }

    public TextBuilder html(String html) {
        message.append(html);
        return this;
    }

    public TextBuilder bold(String text) {
        return element("b", text);
    }

    public TextBuilder italics(String text) {
        return element("i", text);
    }

    public TextBuilder link(String text, String link) {
        return element("a href=\"" + link + "\"", text);
    }

    public TextBuilder code(String text) {
        return element("code", text);
    }

    public TextBuilder preformatted(String text) {
        return element("pre", text);
    }

    public TextBuilder space() {
        message.append(' ');
        return this;
    }

    public TextBuilder newLine() {
        message.append("\n");
        return this;
    }

    public TextBuilder nextLine() {
        return newLine();
    }

    public String toHtml() {
        return message.toString();
    }

    @Override
    public String toString() {
        return message.toString();
    }
}
