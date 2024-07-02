package com.example.MySite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class Tess4jUtils {

    public void getWord() {
        ITesseract tess = new Tesseract();
        // ① tessdataフォルダのパス指定
        tess.setDatapath("C:\\eclipse\\pleiades\\workspace_MySite\\MySite\\src\\main\\webapp\\WEB-IN\\lib\\tessdata");
        tess.setLanguage("eng");
        // ② 画像ファイルのパス
		
		  File target = new File("src/main/resources/static/TEST.jpg"); try { BufferedImage bi =
		  ImageIO.read(target);
		  
		  List word = tess.getWords(bi, TessPageIteratorLevel.RIL_BLOCK); String doOcr
		  = tess.doOCR(bi); System.out.println(word); System.out.println(doOcr);
		  
		  } catch (IOException e) { e.printStackTrace(); } catch (TesseractException e)
		  { e.printStackTrace(); }
		 

    }
}