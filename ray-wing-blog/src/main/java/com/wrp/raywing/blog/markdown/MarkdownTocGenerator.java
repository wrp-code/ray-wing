package com.wrp.raywing.blog.markdown;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * markdown提取带锚点链接的目录
 * @author wrp
 * @since 2025年08月18日 16:30
 **/
public class MarkdownTocGenerator {

    public static List<String> generateTocWithLinks(String filePath) throws IOException {
        List<String> tocEntries = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        Pattern pattern = Pattern.compile("^(#{1,6})\\s+(.*)$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String level = matcher.group(1);
            String title = matcher.group(2).trim();

            // 生成锚点ID（替换空格和特殊字符）
            String anchorId = title.toLowerCase()
                    .replaceAll("[^a-z0-9\\- ]", "")
                    .replaceAll(" ", "-");

            // 计算缩进
            int indent = (level.length() - 1) * 2;
            String indentStr = " ".repeat(indent);

            // 创建带链接的目录项
            tocEntries.add(String.format("%s- [%s](#%s)", indentStr, title, anchorId));
        }

        return tocEntries;
    }

    public static void main(String[] args) {
        try {
            String filePath = "E:\\code\\my\\java\\wrp-learning\\docs\\java高并发编程详解笔记.md";
            List<String> toc = generateTocWithLinks(filePath);

            System.out.println("生成的目录(带链接):");
            toc.forEach(System.out::println);

            Files.write(Paths.get("toc_with_links.md"), toc);
            System.out.println("带链接的目录已写入 toc_with_links.md 文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}