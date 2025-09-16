package com.noob.base.batchDataHandler.docHandle.skill;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.toc.TocException;
import org.docx4j.toc.TocGenerator;
import org.docx4j.wml.P;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument.Factory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
public class DocxUtil {

    public static final String TOC_STYLE_MASK = "TOC%s";
    public static final String HEADING1 = "Heading1";
    public static final String HEADING2 = "Heading2";
    public static final String HEADING3 = "Heading3";

    public static void main(String[] args) throws Exception {

        // String tarFilename = "D:/word/尽调底稿违法违规生成_" + DateUtils.nowDate() + "_" + System.currentTimeMillis() + ".docx";
        String tarFilename = "D:/word/尽调底稿违法违规生成_" + 20250902 + "_" + System.currentTimeMillis() + ".docx";
        createDraft(tarFilename);

    }

    // todo 解析FidelityRPAProcessResultDto中的fileId和webName
    private static void createDraft(String targetFilePath) throws Exception {
        int imageCount = 0;

        // create file
        WordprocessingMLPackage wordMLPackage = DocxUtil.createDocument();

        // add cover
        DocxUtil.addParagraphTextWithStyle(wordMLPackage, "我是尽调目录封面", DocxUtil.HEADING1);

        // add website
        DocxUtil.addParagraphTextWithStyle(wordMLPackage, "1.website1", DocxUtil.HEADING1);
        // add image
        // byte[] imgBytes = null;//todo 存储网关获取imgBytes
        String fileId = "D:/word/img/emoji_1.png";
        byte[] imgBytes = DocxUtil.getFileBytes(fileId);
        DocxUtil.addImage(wordMLPackage, imgBytes, ++imageCount, 2000L);

        // add website
        String fileId2 = "D:/word/img/emoji_2.png";
        DocxUtil.addParagraphTextWithStyle(wordMLPackage, "2.website2", DocxUtil.HEADING3);
        // add image
        DocxUtil.addImage(wordMLPackage, DocxUtil.getFileBytes(fileId2), ++imageCount, 2000L);

        // todo add toc
        // init style
        DocxUtil.initTocStyle(wordMLPackage);
        DocxUtil.addToc(wordMLPackage, 1, "TOC \\o \"1-3\" \\h \\z \\u ");

        // todo set
        DocxUtil.saveDocument(wordMLPackage, targetFilePath);
    }


    public static WordprocessingMLPackage createDocument() throws InvalidFormatException {
        return WordprocessingMLPackage.createPackage();
    }

    public static void initTocStyle(WordprocessingMLPackage wordMLPackage) {
        for (int i = 1; i < 10; i++) {
            wordMLPackage.getMainDocumentPart().getPropertyResolver().activateStyle(String.format(TOC_STYLE_MASK, i));
        }
    }

    public static P addParagraphText(WordprocessingMLPackage wordMLPackage, String text) {
        P para = wordMLPackage.getMainDocumentPart().createParagraphOfText(text);
        wordMLPackage.getMainDocumentPart().getContent().add(para);
        return para;
    }

    public static P addParagraphTextWithStyle(WordprocessingMLPackage wordMLPackage, String text, String styleId) {
        if (StringUtils.isBlank(styleId)) {
            styleId = "Normal";
        }
        P para = wordMLPackage.getMainDocumentPart().addStyledParagraphOfText(styleId, text);
        return para;
    }

    public static P addImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, Integer imageId, Long cx) throws Exception {
        if (imageId == null) throw new Exception("图片id不允许为空，且不能重复！");
        if (cx != null) {
            P pic = DocxUtil.newImage(wordMLPackage, bytes, "", "", imageId, imageId, cx);
            wordMLPackage.getMainDocumentPart().addObject(pic);
            return pic;
        } else {
            P pic = DocxUtil.newImage(wordMLPackage, bytes, "", "", imageId, imageId);
            wordMLPackage.getMainDocumentPart().addObject(pic);
            return pic;
        }

    }

    public static void addToc(WordprocessingMLPackage wordMLPackage, int index, String instruction) throws TocException {
        TocGenerator tocGenerator = new TocGenerator(wordMLPackage);
        tocGenerator.generateToc(index, instruction, false);
    }

    public static void saveDocument(WordprocessingMLPackage wordMLPackage, String targetFilepath) throws Docx4JException, UnsupportedEncodingException {
//        String targetFile = new String(targetFilepath.getBytes(System.getProperty("sun.jnu.encoding")),"UTF-8");
        File file = new File(targetFilepath);
        log.info("file.getName:::{}", file.getName());
//        log.info("file.getAbsolutePath:::{}",file.getAbsolutePath());
//        Path path = Paths.get(targetFile);
//        if(!Files.exists(path)){
//            File file = path.toFile();
//            log.info("file.getName:::{}",file.getName());
//        }
        wordMLPackage.save(file);
    }


    //---------------------------------------------------------------------------

    public static byte[] getFileBytes(String filepath) throws IOException {
        File file = new File(filepath);

        // Our utility method wants that as a byte array
        long length = file.length();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        try (java.io.InputStream is = new java.io.FileInputStream(file)) {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                System.out.println("Could not completely read file " + file.getName());
            }
        } catch (IOException e) {
            log.error("getFileBytes error: ", e);
        }
        return bytes;
    }

    /**
     * Create image, without specifying width
     */
    private static P newImage(WordprocessingMLPackage wordMLPackage,
                              byte[] bytes,
                              String filenameHint, String altText,
                              int id1, int id2) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline(filenameHint, altText,
                id1, id2, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory;
        factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        org.docx4j.wml.R run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

    /**
     * Create image, specifying width in twips
     */
    private static P newImage(WordprocessingMLPackage wordMLPackage,
                              byte[] bytes,
                              String filenameHint, String altText,
                              int id1, int id2, long cx) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, cx, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        org.docx4j.wml.R run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

    /**
     * 获取docx批注（批注正文、批注内容、批注作者、批注时间）
     *
     * @param file
     * @return
     */
    public static List<Map<String, Object>> getDocxComments(File file) throws Exception {
        if (file == null) {
            throw new Exception("Exception: File is null");
        }

        class CommentsInnerDto {
            private File file;
            /** Word document */
            private XWPFDocument docx;
            /** 批注内容数组 */
            private XWPFComment[] comments;//
            /** 批注引用正文map,结构-<批注Id,正文text> */
            private Map<String, String> commentRefs;
            /** 批注所引用正文装配Map完毕标识 */
            private static final String COMMENT_REF_FILLED_OK = "OK";
            /** 批注最大下标 */
            private String maxCommentIndex;

            public CommentsInnerDto(File file) throws Exception {
                this.file = file;
                initAttributes();
            }

            /**
             * 初始化成员变量
             * @throws Exception Word缺陷导入异常
             */
            private void initAttributes() throws Exception {
                try {
                    /*if("doc".equals(type)){
                        String path = file.getPath();
                        Document document = new Document(path);
                        document.saveToFile(path + "x", FileFormat.Docx);//doc转docx
                        document.close();
                        file.delete();
                        file = new File(path + "x");
                    }*/
                    docx = new XWPFDocument(POIXMLDocument.openPackage(file.getCanonicalPath()));
                    comments = docx.getComments();
                    maxCommentIndex = getMaxCommentIndex();
                    commentRefs = new HashMap<>();
                    fillCommentRef(docx.getDocument().getDomNode(), new StringBuilder(), new StringBuilder(), new StringBuilder(), commentRefs);
                } catch (Exception e) {
                    throw new Exception(new StringBuilder().append("Word文件格式错误")
                            .append("-").append(e.getMessage()).toString(), e);
                }
            }

            private String getMaxCommentIndex() {
                String maxIndex = "0";
                if (comments.length > 0) {
                    for (XWPFComment comment : comments) {
                        if (Integer.valueOf(comment.getId()) > Integer.valueOf(maxIndex)) {
                            maxIndex = comment.getId();
                        }
                    }
                }
                return maxIndex;
            }

            /* 获取批注日期List */
            private Map<String, Date> getSubmitDateList() {
                Map<String, Date> dateMap = new HashMap<>();
                Map<String, Date> dateRs = new HashMap<>();
                try {
                    Iterator<POIXMLDocumentPart> iter = docx.getRelations().iterator();
                    do {
                        if (!iter.hasNext())
                            break;
                        POIXMLDocumentPart p = iter.next();
                        CommentsDocument commentsDoc;
                        try {
                            commentsDoc = Factory.parse(p.getPackagePart().getInputStream());
                        } catch (Exception e) {
                            continue;
                        }
                        List<CTComment> commentList = commentsDoc.getComments().getCommentList();
                        int len = commentList.size();
                        int j = 0;
                        while (j < len) {
                            CTComment ctcomment = commentList.get(j);

                            //获取的时间是UTC时间与本地时间存在时差，国内领先8小时，以下API获取时间时会加上8小时，这里减去
                            Calendar date = ctcomment.getDate();
                            date.add(Calendar.HOUR_OF_DAY, -8);

                            dateMap.put(ctcomment.getId().toString(), date.getTime());
                            j++;
                        }
                    } while (true);

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                if (dateMap != null) {
                    for (XWPFComment comment : comments) {
                        dateRs.put(comment.getId(), dateMap.get(comment.getId()));
                    }
                }
                return dateRs;
            }

            /**
             * 组装批注引用文本Map,Map结构-<commentId,text>
             * @param node WordProcessingML node
             * @param id 批注ID
             * @param value 批注引用正文文本
             * @param convertOK 正文组装完毕标识 ,组装完毕 = "OK"
             * @param map 要填充的目标Map
             */
            private void fillCommentRef(Node node, StringBuilder id, StringBuilder value, StringBuilder convertOK, Map<String, String> map) throws Exception {
                // fillCommentRef方法要求所有参数不能为null,如果为null,抛出异常
                if (!insureNotNull(node, id, value, convertOK, map)) {
                    throw new IllegalArgumentException(new StringBuilder()
                            .append(this.getClass().getName())
                            .append("fillCommentRef(").append(node).append(",")
                            .append(id).append(",").append(value).append(",")
                            .append(convertOK).append(",").append(map).append(")")
                            .toString());
                }
                /*
                 * docx文件批注所引用的正文保存在document.xml中,可以通过重命名xx.docx为xx.zip来查看
                 * 其中如果某段正文文本内容有批注,那么会在document.xml这样保存 <w:commentRangeStart w:id="0" />
                 * <w:t>正文文本</w:t> </w:r> <w:commentRangeEnd w:id="0" />
                 * 如果被批注的是在图片上加批注,那么会在document
                 * .xml中这样保存(仅限真正docx文件,如果是doc文件另存为docx文件,<wp:docPr节点中是没有属性的)
                 * <w:commentRangeStart w:id="1" /> <wp:docPr id="1" name="xxx"
                 * descr="yyy.png" /> <w:commentRangeEnd w:id="1" /> *
                 * 1)id初始值为空,如果解析到节点w:commentRangeStart,就代表是有批注的部分,需要把参数id设为节点的id属性值
                 * 2)顺次解析下面节点
                 * ,如果此时的id不为空,就代表进入批注引用部分,w:t是文本内容,直接append;wp:docPr是图片内容,用"[xxx]"
                 * 来区分是图片,然后append.
                 * 3)如果解析到节点w:commentRangeEnd,就代表一个批注引用完毕,这时需要向Map中put(id,value)值;
                 * 判断当前的批注Id是不是最大
                 * ,如果为最大批注Id,convertOK置为"OK",用此标识来说明批注引用提取完毕,退出节点for循环?例如一个很大的Word文件
                 * ,只在第2页做了一个批注,前面的做法会很有用;
                 * 同时还要做好一条批注引用解析完毕的收尾工作:id清空,代表下面节点又是无批注的部分;value清空,待下次新的批注append.
                 */
                if ("w:t".equals(node.getNodeName()) && id.length() > 0) {
                    value.append(node.getFirstChild().getNodeValue());
                } else if ("wp:docPr".equals(node.getNodeName()) && id.length() > 0) {
                    value.append("[").append(getAttribute(node, "name")).append("]");
                } else if ("w:commentRangeStart".equals(node.getNodeName())) {
                    id.setLength(0);
                    id.append(getAttribute(node, "w:id"));
                    value.setLength(0);
                } else if ("w:commentRangeEnd".equals(node.getNodeName()) && id.length() > 0) {
                    if (id.toString().equals(getAttribute(node, "w:id"))) {
                        map.put(id.toString(), value.toString());
                        if (id.toString().equals(maxCommentIndex)) {
                            convertOK.setLength(0);
                            convertOK.append(COMMENT_REF_FILLED_OK);
                            id.setLength(0);
                            value.setLength(0);
                        }
                    }
                }
                if (node.hasChildNodes()) {
                    NodeList temp = node.getChildNodes();
                    for (int i = 0; i < temp.getLength(); i++) {
                        if (convertOK.toString().endsWith(COMMENT_REF_FILLED_OK)) {
                            break;
                        }
                        fillCommentRef(temp.item(i), id, value, convertOK, map);
                    }
                }
            }

            /***
             * @param node 当前的Node
             * @param attName 要获取的属性名
             * @return 属性值, 没有该属性时返回null
             */
            private String getAttribute(Node node, String attName) {
                return (node.hasAttributes() && node.getAttributes().getNamedItem(attName) != null) ?
                        node.getAttributes().getNamedItem(attName).getNodeValue() : null;
            }

            /**
             * 确保此方法的所有参数均不为空
             * @param objects 对象参数
             * @return 所有参数均不为空返回true 否则为false
             */
            private boolean insureNotNull(Object... objects) {
                for (Object object : objects) {
                    if (object == null) {
                        return false;
                    }
                }
                return true;
            }
        }

        CommentsInnerDto commentsDto = new CommentsInnerDto(file);
        XWPFComment[] comments = commentsDto.comments;
        Map<String, Date> dateMap = commentsDto.getSubmitDateList();
        Map<String, String> commentRefs = commentsDto.commentRefs;
        commentsDto.docx.close();
        commentsDto.file.delete();

        List<Map<String, Object>> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < comments.length; i++) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("id", comments[i].getId());
            tempMap.put("author", comments[i].getAuthor());
            tempMap.put("commentContent", comments[i].getText());
            Date date = dateMap.get(comments[i].getId());
            tempMap.put("date", date == null ? "" : sdf.format(date));
            tempMap.put("commentText", commentRefs.get(comments[i].getId()));
            result.add(tempMap);
        }

        return result;
    }
}
