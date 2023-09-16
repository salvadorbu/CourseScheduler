package com.example.CourseScheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RegistrationTimetable {
    private String termYear;


    public RegistrationTimetable(String termYear) throws IOException {
        this.termYear = termYear;
    }

    public void getCourses(String subject, String subjectID) throws IOException {
        Connection.Response response = Jsoup.connect("https://apps.es.vt.edu/ssb/HZSKVTSC.P_ProcRequest")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Cache-Control", "max-age=0")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .cookie("JSESSIONID", "CE08BD4200D1F4F8C6FBD1C6DF028668")
                .cookie("SESSID", "T0hVSDdCMTI5MDc5ODM")
                .cookie("_gcl_au", "1.1.1740623690.1688754846")
                .cookie("_ga_SD43QX7XJF", "GS1.1.1690136574.4.1.1690136592.0.0.0")
                .cookie("style", "default")
                .cookie("_fbp", "fb.1.1692900375999.608525575")
                .cookie("_ga_D99RC0R2WH", "GS1.1.1693423786.1.1.1693423819.0.0.0")
                .cookie("_ga_QEQ6BRGQ8X", "GS1.2.1693423823.1.1.1693423824.0.0.0")
                .cookie("_ga_MT0L4G1S43", "GS1.1.1693423855.2.1.1693423863.0.0.0")
                .cookie("_ga_ZNSTZ2YGVJ", "GS1.1.1693423874.1.1.1693423882.0.0.0")
                .cookie("_ga_JH204F297Y", "GS1.1.1693423976.2.1.1693424066.0.0.0")
                .cookie("_ga_ZJEQ62D8H3", "GS1.1.1693423797.1.1.1693424371.0.0.0")
                .cookie("_ga_28REPJ3J7C", "GS1.1.1693423797.1.1.1693424371.0.0.0")
                .cookie("_ga_1HMZZXCML9", "GS1.1.1693877982.1.0.1693878977.0.0.0")
                .cookie("_ga_4M1PDYJDXD", "GS1.1.1694049830.1.0.1694049835.0.0.0")
                .cookie("_gid", "GA1.2.1017126560.1694378263")
                .cookie("_ga_JHMTZTGLR9", "GS1.1.1694408671.5.1.1694408673.0.0.0")
                .cookie("_ga_6EG7YSHYLZ", "GS1.1.1694544289.1.0.1694544292.57.0.0")
                .cookie("_ga_6NH85V357P", "GS1.2.1694544304.1.0.1694544304.0.0.0")
                .cookie("_ga_3KYJBSR9WZ", "GS1.1.1694544304.2.0.1694544309.0.0.0")
                .cookie("_hp2_id.3001039959", "%7B%22userId%22%3A%221452475606665952%22%2C%22pageviewId%22%3A%221294729460907501%22%2C%22sessionId%22%3A%22470442909988332%22%2C%22identity%22%3Anull%2C%22trackerVersion%22%3A%224.0%22%7D")
                .cookie("_ga_VPVQ2Q69QH", "GS1.2.1694821088.73.1.1694821094.0.0.0")
                .cookie("_ce.s", "v~08beacc3bb8f4fb898f060a84b714492daa4cf7e~vpv~7~lcw~1694544290120~lcw~1694838284544")
                .cookie("_ce.clock_event", "1")
                .cookie("_ce.clock_data", "112%2C45.3.77.115%2C1%2C3357fadb0316939352bbdd4d5360a97f")
                .cookie("_ga_5Z60EH83Q9", "GS1.1.1694838284.18.0.1694838287.57.0.0")
                .cookie("_ga_DTEQ1JM2SG", "GS1.1.1694838284.18.0.1694838287.0.0.0")
                .cookie("X-Route-ssomgr-prod", "977b4c9f690002e1")
                .cookie("_gat_gtag_UA_161333490_1", "1")
                .cookie("_ga", "GA1.1.199173471.1670260661")
                .cookie("IDMSESSID", "41ED62DEF0B8F02CA26C834EC014759A6C6BCC52F56BF14DCBA3B14E9FEB610B60D4CD6BDEA9AFE1E354CAB23DEB08864472F7A42CD18C13E2FCA0F215589208")
                .cookie("X-Route-ssb-ords-banner-prod", "f336d2ca584a6727")
                .cookie("_ga_T9PY1ZDFJ5", "GS1.1.1694878612.53.1.1694878618.0.0.0")
                .cookie("__utma", "15161830.199173471.1670260661.1694845305.1694878619.12")
                .cookie("__utmc", "15161830")
                .cookie("__utmz", "15161830.1694878619.12.6.utmcsr")
                .cookie("__utmt", "1")
                .cookie("__utmb", "15161830.3.10.1694878619")
                .header("Origin", "https://apps.es.vt.edu")
                .header("Referer", "https://apps.es.vt.edu/ssb/HZSKVTSC.P_DispRequest")
                .header("Sec-Fetch-Dest", "document")
                .header("Sec-Fetch-Mode", "navigate")
                .header("Sec-Fetch-Site", "same-origin")
                .header("Sec-Fetch-User", "?1")
                .header("Upgrade-Insecure-Requests", "1")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                .header("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .requestBody("CAMPUS=0&TERMYEAR=202309&CORE_CODE=AR%25&subj_code=AOE&SCHDTYPE=%25&CRSE_NUMBER=&crn=&open_only=&disp_comments_in=Y&sess_code=%25&BTN_PRESSED=FIND+class+sections&inst_name=")
                .method(org.jsoup.Connection.Method.POST)
                .ignoreContentType(true)
                .execute();
        Document doc = response.parse();
        Elements timeElements = doc.select("html > body > center > div.class1 > table.dataentrytable > tbody > tr > td.deright");
        Elements descElements = doc.select("html > body > center > div.class1 > table.dataentrytable > tbody > tr > td.deleft");
        Elements dayElements = doc.select("html > body > center > div.class1 > table.dataentrytable > tbody > tr > td.dedefault");
        Element[] courseArr = (Element[])descElements.toArray(new Element[0]);

        for (Element element : timeElements) {
            System.out.println(element.text());
        }

        for (int i = 2; i < courseArr.length - 1; i++) {
            String instructor = courseArr[i].text();
            String[] location = courseArr[i+1].text().split(" ");
            String building = "";
            String room = "";
            if (location.length == 2) {
                building = location[0];
                room = location[1];
            }

        }

        for (Element element : dayElements) {
            System.out.println(element.text());
        }
    }
}
